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
