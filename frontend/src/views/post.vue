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
      <el-button type="primary" @click="togglePostForm">发布动态</el-button>

      <!-- 发布动态表单 -->
      <el-card v-if="showPostForm" class="post-form">
        <el-form :model="newPost">
          <el-form-item label="动态内容">
            <el-input
              v-model="newPost.content"
              type="textarea"
              placeholder="请输入动态内容"
            ></el-input>
          </el-form-item>
          <el-form-item label="上传图片">
            <el-upload action="上传图片的后端地址" list-type="picture-card">
              <el-button>上传图片</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="publishPost">发布</el-button>
            <el-button @click="togglePostForm">取消</el-button>
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
              <!-- 删除按钮 -->
              <el-button
                type="danger"
                size="small"
                @click="confirmDelete(post.id)"
                >删除</el-button
              >
              <!-- 跳转详情页面的按钮 -->
              <el-button
                type="primary"
                size="small"
                @click="viewPostDetail(post.id)"
              >
                查看详情
              </el-button>
            </div>
            <div class="post-date">
              发布于：{{ formatDate(post.createdAt) }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </div>
</template>
<script>
import AppSideBar from "@/components/AppSideBar.vue";
import UserAPI from "@/api/userAPI";
import PostAPI from "@/api/postAPI";
import { mapActions } from "vuex";
import { ElMessage, ElMessageBox } from "element-plus";
import { ChatLineRound, Star } from "@element-plus/icons-vue";

export default {
  name: "userPostPage",
  components: { ChatLineRound, Star, AppSideBar },
  computed: {
    userInfo() {
      return this.$store.state.user?.userInfo;
    },
    // 对动态进行排序的计算属性
    sortedPosts() {
      return [...this.posts].sort(
        (a, b) =>
          this.getDateFromCreatedAt(b.createdAt) -
          this.getDateFromCreatedAt(a.createdAt)
      );
    },
  },
  data() {
    return {
      posts: [], // 动态列表
      showPostForm: false, // 控制表单显示与隐藏
      newPost: {
        userId: null, // 用户 ID
        content: "", // 动态内容
        mediaUrl: "", // 上传成功后的图片地址
        likeCount: 0, // 点赞数
        commentCount: 0, // 评论数
        status: 1, // 状态（默认为 1: 公开）
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

    // 删除动态确认
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

    // 获取 Cookie 的方法
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);

      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },

    // 格式化日期
    formatDate(date) {
      // 提取日期时间信息
      const year = date.year;
      const month = String(date.monthValue).padStart(2, "0"); // 补全两位
      const day = String(date.dayOfMonth).padStart(2, "0");
      const hour = String(date.hour).padStart(2, "0");
      const minute = String(date.minute).padStart(2, "0");
      const second = String(date.second).padStart(2, "0");

      // 拼接成标准格式
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    },

    // 查看详情
    viewPostDetail(postId) {
      // 使用 Vue Router 跳转到动态详情页面
      this.$router.push(`/post/${postId}`);
    },

    // 从 createdAt 对象创建 Date 对象
    getDateFromCreatedAt(createdAt) {
      return new Date(
        createdAt.year,
        createdAt.monthValue - 1,
        createdAt.dayOfMonth,
        createdAt.hour,
        createdAt.minute,
        createdAt.second
      );
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
  },
};
</script>

<style scoped>
/* 动态列表样式 */
.post-list {
  width: 100%;
  max-width: 800px;
}

/* 单个动态卡片样式 */
.post-card {
  margin-bottom: 20px;
}

/* 动态内容样式 */
.post-content {
  margin-bottom: 10px;
}

.post-content .content {
  font-size: 16px;
  margin-bottom: 10px;
}

.post-content .post-image {
  max-width: 100%;
  border-radius: 5px;
  margin-top: 10px;
}

/* 动态状态样式 */
.post-status {
  margin-top: 10px;
  display: flex;
  align-items: center;
  color: #888;
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
</style>
