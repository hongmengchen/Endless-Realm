<template>
  <div class="container">
    <!-- 左侧侧边栏 -->
    <el-aside class="container-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧主要内容区域 -->
    <el-main class="container-body">
      <!-- 页面标题 -->
      <div class="post-header">
        <h2>首页</h2>
      </div>

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
            <!-- 动态元信息 -->
            <div class="post-meta">
              <span>
                <el-icon><Star /></el-icon> {{ post.likeCount }}
              </span>
              <span>
                <el-icon><ChatLineRound /></el-icon> {{ post.commentCount }}
              </span>
              <!-- 跳转详情页面的按钮 -->
              <el-button
                type="primary"
                size="small"
                @click="viewPostDetail(post.id)"
              >
                查看详情
              </el-button>
            </div>
            <!-- 发布日期 -->
            <div class="post-date">
              发布于：{{ formatDate(post.updatedAt) }}
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
import { mapActions, mapState } from "vuex";
import { ElMessage } from "element-plus";
import { ChatLineRound, Star } from "@element-plus/icons-vue";

export default {
  name: "HomeIndex",
  components: {
    ChatLineRound,
    Star,
    AppSideBar,
  },
  computed: {
    ...mapState("user", ["userInfo"]), // 映射 Vuex 的 userInfo 状态
    // 对动态进行排序的计算属性
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
        ElMessage.error("加载用户信息失败");
        console.error("Error loading user info:", error);
      }
    },

    // 加载动态
    async loadPosts() {
      if (!this.userInfo || !this.userInfo.id) {
        ElMessage.warning("用户信息未加载，无法加载动态");
        return;
      }
      // 使用 Vuex 的 getters 获取所有动态
      try {
        const res = await PostAPI.getPostByStatus(1);
        this.posts = res.data.data || [];
      } catch (error) {
        ElMessage.error("加载动态失败");
        console.error("加载动态失败:", error);
      }
    },

    // 查看详情
    viewPostDetail(postId) {
      // 使用 Vue Router 跳转到动态详情页面
      this.$router.push(`/post/${postId}`);
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
  margin-bottom: 15px;
}

.post-image {
  max-width: 100%;
  border-radius: 4px;
  margin-top: 10px;
}

/* 动态元信息样式 */
.post-meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
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
