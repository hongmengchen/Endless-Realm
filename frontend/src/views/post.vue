<template>
  <div class="container">
    <!-- 左侧侧边栏 -->
    <el-aside class="container-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="container-body">
      <!-- 动态发布 -->
      <div class="post-header">
        <h2>我的动态</h2>
      </div>

      <!-- 发布动态按钮 -->
      <el-button
        class="publish-button"
        type="primary"
        size="large"
        @click="togglePostForm"
      >
        <el-icon><Plus /></el-icon>
        发布动态
      </el-button>

      <!-- 发布动态表单 -->
      <el-card v-if="showPostForm" class="post-form">
        <el-form :model="newPost">
          <el-form-item label="动态内容">
            <el-input
              v-model="newPost.content"
              type="textarea"
              rows="4"
              show-word-limit
              placeholder="请输入动态内容"
            ></el-input>
          </el-form-item>
          <el-form-item label="上传图片" label-width="90px">
            <el-upload action="上传图片的后端地址" list-type="picture-card">
              <el-button>上传图片</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button
              class="button-submit"
              type="primary"
              @click="publishPost"
              size="large"
            >
              发布
            </el-button>
            <el-button
              class="button-cancel"
              @click="togglePostForm"
              size="large"
            >
              取消
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 动态列表 -->
      <el-row :gutter="20" class="post-list">
        <el-col v-for="post in sortedPosts" :key="post.id" :span="24">
          <el-card class="post-card" shadow="hover">
            <!-- 动态内容 -->
            <div class="post-content">
              <p>{{ post.content }}</p>
              <el-image
                v-if="post.mediaUrl"
                :src="post.mediaUrl"
                fit="cover"
                alt="动态图片"
                class="post-image"
              />
            </div>

            <!-- 动态状态 -->
            <div class="post-status">
              <el-tag :type="getStatusTagType(post.status)" class="status-tag">
                <span class="status-text">{{
                  getStatusLabel(post.status)
                }}</span>
              </el-tag>
            </div>

            <!-- 动态元信息 -->
            <div class="post-meta">
              <span>
                <el-icon><Star /></el-icon> {{ post.likeCount }}
              </span>
              <span>
                <el-icon><ChatLineRound /></el-icon> {{ post.commentCount }}
              </span>
              <!-- 修改按钮 -->
              <el-button
                type="primary"
                size="small"
                @click="editPost(post)"
                class="action-button"
                >修改
              </el-button>
              <!-- 删除按钮 -->
              <el-button
                type="danger"
                size="small"
                @click="confirmDelete(post.id)"
                class="action-button"
                >删除</el-button
              >
              <!-- 跳转详情页面的按钮 -->
              <el-button
                type="primary"
                size="small"
                @click="viewPostDetail(post.id)"
                class="action-button"
              >
                查看详情
              </el-button>
            </div>
            <div class="post-date">
              发布于：{{ formatDate(post.updatedAt) }}
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 修改动态表单 -->
      <el-dialog v-model="showEditForm" title="修改动态">
        <el-form :model="editPostData">
          <el-form-item label="动态内容">
            <el-input
              v-model="editPostData.content"
              type="textarea"
              rows="4"
              placeholder="请输入新的动态内容"
            ></el-input>
          </el-form-item>
          <el-form-item label="上传图片" label-width="90px">
            <el-upload action="上传图片的后端地址" list-type="picture-card">
              <el-button>上传图片</el-button>
            </el-upload>
          </el-form-item>
          <div class="dialog-footer">
            <el-button @click="showEditForm = false">取消</el-button>
            <el-button
              type="primary"
              @click="confirmEdit"
              class="button-submit"
            >
              提交</el-button
            >
          </div>
        </el-form>
      </el-dialog>
    </el-main>
  </div>
</template>
<script>
import AppSideBar from "@/components/AppSideBar.vue";
import UserAPI from "@/api/userAPI";
import PostAPI from "@/api/postAPI";
import { mapActions } from "vuex";
import { ElMessage, ElMessageBox } from "element-plus";
import { ChatLineRound, Plus, Star } from "@element-plus/icons-vue";

export default {
  name: "userPostPage",
  components: { Plus, ChatLineRound, Star, AppSideBar },
  computed: {
    userInfo() {
      return this.$store.state.user?.userInfo;
    },
    // 对动态进行排序的计算属性
    sortedPosts() {
      return [...this.posts].sort((a, b) => {
        const dateA = new Date(
          Date.UTC(
            a.updatedAt[0],
            a.updatedAt[1] - 1,
            a.updatedAt[2],
            a.updatedAt[3],
            a.updatedAt[4],
            a.updatedAt[5]
          )
        );
        const dateB = new Date(
          Date.UTC(
            b.updatedAt[0],
            b.updatedAt[1] - 1,
            b.updatedAt[2],
            b.updatedAt[3],
            b.updatedAt[4],
            b.updatedAt[5]
          )
        );
        return dateB - dateA; // 降序排列，最新的动态排在最前面
      });
    },
  },
  data() {
    return {
      posts: [], // 动态列表
      showPostForm: false, // 显示发布动态表单
      showEditForm: false, // 显示修改动态表单
      newPost: {
        userId: null, // 用户 ID
        content: "", // 动态内容
        mediaUrl: "", // 上传成功后的图片地址
        likeCount: 0, // 点赞数
        commentCount: 0, // 评论数
        status: 1, // 状态（默认为 1: 公开）
      },
      editPostData: {
        id: null,
        content: "", // 动态内容
        mediaUrl: "", // 上传成功后的图片地址
      },
    };
  },
  created() {
    // 如果用户信息不存在，则尝试加载
    if (!this.userInfo) {
      this.loadUserInfo();
    } else {
      this.loadPosts();
    }
  },

  methods: {
    ...mapActions("user", ["updateUserInfo"]),

    // 加载用户信息
    async loadUserInfo() {
      try {
        const shUserId = this.getCookie("shUserId");
        const res = await UserAPI.getUserInfo(shUserId);

        if (res.data.status_code === 1) {
          this.updateUserInfo(res.data.data);
          this.loadPosts(); // 加载动态
        }
      } catch (error) {
        console.error("Error loading user info:", error);
      }
    },

    // 加载动态
    async loadPosts() {
      if (!this.userInfo || !this.userInfo.id) {
        ElMessage.warning("用户信息未加载，无法加载动态");
        return;
      }
      // 使用 Vuex 的 getters 获取当前用户的 ID
      try {
        const res = await PostAPI.getPostByUserId(this.userInfo.id);
        this.posts = res.data.data || [];
      } catch (error) {
        ElMessage.error("加载动态失败");
        console.error("加载动态失败:", error);
      }
    },

    // 显示/隐藏发布动态表单
    togglePostForm() {
      this.showPostForm = !this.showPostForm;
    },

    // 发布动态
    async publishPost() {
      if (!this.newPost.content) {
        ElMessage.warning("动态内容不能为空！");
        return;
      }
      if (!this.userInfo) {
        ElMessage.error("请先登录！");
        return;
      }
      if (this.userInfo.status === 0) {
        ElMessage.error("您的账号已被封禁，暂时无法发布动态！");
        return;
      }
      try {
        this.newPost.userId = this.userInfo.id;
        const res = await PostAPI.addPost(this.newPost);
        if (res.data.status_code === 1) {
          ElMessage.success("动态发布成功！");
          this.showPostForm = false;
          this.newPost = { content: "", mediaUrl: "" }; // 重置表单
          this.loadPosts(); // 重新加载动态
        } else {
          ElMessage.error(res.data.msg);
        }
      } catch (error) {
        console.error("动态发布失败:", error);
        ElMessage.error("动态发布失败，请稍后再试！");
      }
    },

    // 修改动态
    editPost(post) {
      this.editPostData = { ...post }; // 初始化表单数据
      this.showEditForm = true;
    },
    async confirmEdit() {
      try {
        const res = await PostAPI.updatePost(this.editPostData);
        if (res.data.status_code === 1) {
          ElMessage.success("动态修改成功！");
          this.showEditForm = false;
          this.loadPosts(); // 重新加载动态
        } else {
          this.showError(res.data.msg || "修改动态失败！");
        }
      } catch (error) {
        this.showError("动态修改失败，请稍后再试！");
      }
    },
    // 删除动态
    async deletePost(postId) {
      try {
        const res = await PostAPI.deletePost(postId);
        if (res.data.status_code === 1) {
          ElMessage.success("动态删除成功！");
          this.loadPosts(); // 重新加载动态
        } else {
          ElMessage.error("删除动态失败！");
        }
      } catch (error) {
        console.error("删除动态失败:", error);
        ElMessage.error("删除动态失败，请稍后再试！");
      }
    },
    confirmDelete(postId) {
      ElMessageBox.confirm("您确定要删除这条动态吗?", "删除确认", {
        confirmButtonText: "删除",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deletePost(postId); // 确认删除
        })
        .catch(() => {
          ElMessage.info("删除操作已取消");
        });
    },

    // 获取 Cookie 的方法
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);

      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },

    // 格式化日期
    formatDate(dateArray) {
      // 将 [2024, 12, 13, 15, 46, 29] 转换为 JavaScript Date 对象
      const date = new Date(
        Date.UTC(
          dateArray[0], // 年份
          dateArray[1] - 1, // 月份（JavaScript 中月份从 0 开始）
          dateArray[2], // 日期
          dateArray[3], // 小时
          dateArray[4], // 分钟
          dateArray[5] // 秒
        )
      );

      // 使用 toLocaleString 方法格式化日期，并确保使用 UTC 时区
      return date.toLocaleString("zh-CN", {
        weekday: "long", // 星期几
        year: "numeric", // 年份
        month: "long", // 月份（完整）
        day: "numeric", // 日期
        hour: "2-digit", // 小时
        minute: "2-digit", // 分钟
        second: "2-digit", // 秒
        hour12: false, // 24小时制
        timeZone: "UTC", // 指定时区为 UTC
      });
    },

    // 查看详情
    viewPostDetail(postId) {
      // 使用 Vue Router 跳转到动态详情页面
      this.$router.push(`/post/${postId}`);
    },

    // 根据状态获取动态状态标签
    getStatusLabel(status) {
      const statusLabels = {
        1: "公开", // 公开状态
        0: "私密", // 私密状态
      };
      return statusLabels[status] || "未知状态";
    },

    // 根据状态获取对应的标签类型
    getStatusTagType(status) {
      const statusTypes = {
        1: "success", // 公开状态
        0: "warning", // 私密状态
      };
      return statusTypes[status] || "info"; // 默认"info"类型
    },

    // 工具方法：显示错误消息
    showError(message) {
      ElMessage.error(message);
    },
  },
};
</script>

<style scoped>
/* 动态列表样式 */
.post-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

/* 发布按钮 */
.publish-button {
  margin-top: 20px;
  margin-bottom: 20px;
}

/* 单个动态卡片样式 */
.post-card {
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

/* 悬浮效果 */
.post-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

/* 动态内容样式 */
.post-content {
  margin-bottom: 15px;
}

.content-text {
  font-size: 16px;
  line-height: 1.5;
}

.post-image {
  max-width: 100%;
  border-radius: 8px;
  margin-top: 10px;
}

/* 动态状态样式 */
.post-status {
  margin-top: 10px;
  display: flex;
  align-items: center;
}

.status-tag {
  font-size: 14px;
  padding: 5px 10px;
}

.status-text {
  font-weight: bold;
  text-transform: capitalize;
}

/* 动态元信息样式 */
.post-meta {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

/* 动态日期样式 */
.post-date {
  font-size: 14px;
  color: #888;
  margin-top: 10px;
  text-align: right;
}

/* 按钮样式 */
.action-button {
  margin-left: 10px;
}

.button-submit {
  background-color: #409eff;
}

.button-cancel {
  background-color: #f56c6c;
}

/* 发布动态表单 */
.post-form {
  max-width: 600px;
  margin: 20px auto;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
}
</style>
