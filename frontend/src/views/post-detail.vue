<template>
  <div class="post-detail">
    <!-- 返回主页按钮 -->
    <el-button type="primary" @click="goHome" class="back-button">
      返回主页
    </el-button>

    <!-- 显示动态内容 -->
    <el-card v-if="post" class="post-card">
      <div class="post-content">
        <p class="post-text">{{ post.content }}</p>
        <img
          v-if="post.mediaUrl"
          :src="post.mediaUrl"
          alt="动态图片"
          class="post-image"
        />
      </div>
      <div class="post-meta">
        <el-button type="text" size="small" class="post-like">
          点赞：{{ post.likeCount }}
        </el-button>
        <el-button type="text" size="small" class="post-comment">
          评论：{{ post.commentCount }}
        </el-button>
      </div>
    </el-card>

    <!-- 显示评论列表 -->
    <el-divider></el-divider>
    <div v-if="comments.length > 0">
      <h3>评论</h3>
      <el-card
        v-for="comment in comments"
        :key="comment.id"
        class="comment-card"
      >
        <p>
          <strong>{{ comment.author }}:</strong> {{ comment.content }}
        </p>
        <p class="comment-time">{{ formatDate(comment.createdAt) }}</p>
      </el-card>
    </div>
    <div v-else>
      <p>暂无评论</p>
    </div>
  </div>
</template>

<script>
import PostAPI from "@/api/postAPI";
import commentAPI from "@/api/commentAPI";

export default {
  name: "post-detail",
  data() {
    return {
      post: null,
      comments: [], // 存储评论列表
    };
  },
  async created() {
    // 获取动态详情
    const postId = this.$route.params.postId;
    const res = await PostAPI.getPostDetail(postId);
    this.post = res.data.data;

    // 获取评论列表
    const commentRes = await commentAPI.getAllComment(postId); // 使用 commentAPI 获取评论
    this.comments = commentRes.data.data || [];
  },
  methods: {
    // 格式化日期为 "年 月 日 时间"
    formatDate(date) {
      const year = date.year;
      const month = date.monthValue; // 月份数字 (1-12)
      const day = date.dayOfMonth; // 日期
      const hour = String(date.hour).padStart(2, "0"); // 小时
      const minute = String(date.minute).padStart(2, "0"); // 分钟
      const second = String(date.second).padStart(2, "0"); // 秒

      return `${year} 年 ${month} 月 ${day} 日 ${hour}:${minute}:${second}`;
    },

    // 返回主页
    goHome() {
      this.$router.push("/"); // 跳转到主页
    },
  },
};
</script>

<style scoped>
/* 页面容器样式 */
.post-detail {
  padding: 20px;
}

/* 返回主页按钮 */
.back-button {
  margin-bottom: 20px;
}

/* 动态卡片样式 */
.post-card {
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 动态文本样式 */
.post-text {
  font-size: 16px;
  line-height: 1.5;
  margin-bottom: 10px;
}

/* 图片样式 */
.post-image {
  max-width: 100%;
  border-radius: 8px;
  margin-top: 10px;
}

/* 动态元信息样式 */
.post-meta {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.post-like,
.post-comment {
  font-size: 14px;
  color: #666;
}

/* 评论卡片样式 */
.comment-card {
  margin-bottom: 15px;
  padding: 15px;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
}

/* 评论时间样式 */
.comment-time {
  font-size: 12px;
  color: #888;
  margin-top: 5px;
}
</style>
