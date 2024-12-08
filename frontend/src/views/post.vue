<template>
  <div class="post">
    <!-- 左侧侧边栏 -->
    <el-aside class="post-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="post-body">
      <!-- 顶部导航栏 -->
      <div class="post-header">
        <h2>我的动态</h2>
      </div>

      <!-- 动态列表 -->
      <el-row :gutter="20" class="post-list">
        <el-col v-for="post in posts" :key="post.id" :span="24">
          <el-card class="post-card">
            <div class="post-content">
              <p class="content">{{ post.content }}</p>
              <img
                v-if="post.mediaUrl"
                :src="post.mediaUrl"
                alt="动态图片"
                class="post-image"
              />
            </div>
            <div class="post-meta">
              <el-button type="text" size="small">
                点赞：{{ post.likeCount }}
              </el-button>
              <el-button
                type="text"
                size="small"
                @click="viewComments(post.id)"
              >
                评论：{{ post.commentCount }}
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
import PostAPI from "@/api/postAPI";
import { mapActions, mapState } from "vuex";
import UserAPI from "@/api/userAPI";

export default {
  name: "userPostPage",
  components: { AppSideBar },
  computed: {
    ...mapState("user", ["userInfo"]), // 映射 Vuex 的 userInfo 状态
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
        console.warn("用户信息未加载，无法加载动态");
        return;
      }

      try {
        const res = await PostAPI.getPostByUserId(this.userInfo.id);
        this.posts = res.data.data || [];
      } catch (error) {
        console.error("加载动态失败:", error);
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

    // 查看评论
    viewComments(postId) {
      window.location.href = `/post/${postId}/comments`;
    },
  },

  data() {
    return {
      posts: [], // 动态列表
    };
  },
};
</script>

<style scoped>
.post {
  display: flex; /* 使用 Flexbox 布局 */
  background-color: #f9f9f9; /* 背景色 */
  height: 100vh; /* 全屏高度 */
}
/* 左侧侧边栏样式 */
.post-aside {
  padding: 10px;
  border-right: 1px solid #ddd; /* 分隔线 */
}
/* 主内容区域样式 */
.post-body {
  flex: 1;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
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
