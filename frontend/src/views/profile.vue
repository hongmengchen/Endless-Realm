<template>
  <div class="profile-container">
    <el-aside class="container-aside" width="collapse">
      <!-- 侧边栏 -->
      <AppSideBar />
    </el-aside>
    <!-- 用户信息区域 -->
    <div class="user-info">
      <el-row :gutter="20" align="middle">
        <el-col :span="6">
          <el-avatar :size="100" :src="userInfo.avatar" />
        </el-col>
        <el-col :span="18">
          <h2>{{ userInfo.username }}</h2>
          <div class="user-stats">
            <div class="stat-item">
              <div class="number">{{ userInfo.posts }}</div>
              <div class="label">帖子</div>
            </div>
            <div class="stat-item">
              <div class="number">{{ userInfo.followers }}</div>
              <div class="label">粉丝</div>
            </div>
            <div class="stat-item">
              <div class="number">{{ userInfo.following }}</div>
              <div class="label">关注</div>
            </div>
          </div>
          <p class="bio">{{ userInfo.bio }}</p>
          <el-button type="primary">编辑资料</el-button>
        </el-col>
      </el-row>
    </div>

    <!-- 图片网格区域 -->
    <div class="posts-grid">
      <el-row :gutter="2">
        <el-col v-for="post in posts" :key="post.id" :span="8">
          <div class="grid-item" @click="showPost(post)">
            <el-image :src="post.imageUrl" fit="cover" />
            <div class="hover-stats">
              <span
                ><el-icon><Star /></el-icon> {{ post.likes }}</span
              >
              <span
                ><el-icon><ChatRound /></el-icon> {{ post.comments }}</span
              >
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { Star, ChatRound } from "@element-plus/icons-vue";
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "userProfile",
  components: {
    AppSideBar,
    Star,
    ChatRound,
  },
  data() {
    return {
      userInfo: {
        username: "用户名",
        avatar:
          "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
        posts: 0,
        followers: 0,
        following: 0,
        bio: "这是我的个人简介",
      },
      posts: [
        {
          id: 1,
          imageUrl:
            "https://raw.githubusercontent.com/hongmengchen/hongmeng-images/main/blog-images/Database-Notes-1_top_img.png",
          description: "动态的描述",
          likes: 0,
          comments: 0,
        },
      ],
    };
  },
  methods: {
    showPost(post) {
      // 处理点击动态的逻辑
      console.log("查看动态:", post);
    },
  },
};
</script>

<style scoped>
.profile-container {
  display: flex;
  height: 100vh; /* 使容器占满整个视口高度 */
}

.container-aside {
  padding: 20px; /* 可选：设置内边距 */
  background-color: #ded8d8;
}

.user-info {
  margin-bottom: 44px;
}

.user-stats {
  display: flex;
  gap: 40px;
  margin: 20px 0;
}

.stat-item {
  text-align: center;
}

.number {
  font-weight: bold;
}

.bio {
  margin: 20px 0;
}

.posts-grid {
  margin-top: 20px;
}

.grid-item {
  position: relative;
  aspect-ratio: 1;
  cursor: pointer;
}

.grid-item .el-image {
  width: 100%;
  height: 100%;
}

.hover-stats {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  opacity: 0;
  transition: opacity 0.3s;
}

.grid-item:hover .hover-stats {
  opacity: 1;
}

.hover-stats span {
  display: flex;
  align-items: center;
  gap: 5px;
}
</style>
