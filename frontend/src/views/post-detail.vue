<template>
  <div class="post-detail">
    <!-- 显示动态内容 -->
    <div v-if="post">
      <p>内容：{{ post.content }}</p>
      <img v-if="post.mediaUrl" :src="post.mediaUrl" alt="动态图片" />
      <p>点赞数：{{ post.likeCount }}</p>
      <p>评论数：{{ post.commentCount }}</p>
    </div>

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
  },
};
</script>

<style scoped>
.comment-card {
  margin-bottom: 15px;
}

.comment-time {
  font-size: 12px;
  color: #888;
}
</style>
