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
        <el-button link size="small" class="post-like" @click="likePost">
          点赞：{{ post.likeCount }}
        </el-button>
        <el-button
          link
          size="small"
          class="post-comment"
          @click="toggleCommentForm"
        >
          评论：{{ post.commentCount }}
        </el-button>
      </div>
    </el-card>

    <!-- 显示评论列表 -->
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

    <!-- 评论表单 -->
    <div v-if="showCommentForm" class="comment-form">
      <el-input
        type="textarea"
        v-model="newComment.content"
        placeholder="请输入评论内容"
        rows="4"
      ></el-input>
      <el-button type="primary" @click="submitComment">提交评论</el-button>
      <el-button @click="cancelComment">取消</el-button>
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
      showCommentForm: false, // 控制评论表单的显示
      newComment: {
        content: "",
        postId: this.$route.params.postId, // 动态ID
        userId: null,
        type: 0,
      },
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

    // 点赞
    async likePost() {
      try {
        // 检查 userInfo 是否存在
        if (!this.$store.state.user || !this.$store.state.user.userInfo) {
          this.$message.error("用户信息未加载，请登录后再试！");
          return;
        }
        this.newComment.userId = this.$store.state.user.userInfo.id;
        this.newComment.type = 2;
        const res = await commentAPI.likeComment(this.newComment);
        if (res.data.status_code === 1) {
          console.log("点赞成功！");
          // 点赞成功后刷新页面
          this.$router.go(0); // 使用Vue Router重新加载当前页面
        } else {
          this.$message.error("点赞失败！");
        }
      } catch (error) {
        this.$message.error("网络错误，请稍后再试！");
      }
    },

    // 切换评论表单的显示与隐藏
    toggleCommentForm() {
      this.showCommentForm = !this.showCommentForm;
    },

    // 提交评论
    async submitComment() {
      if (!this.newComment.content.trim()) {
        this.$message.warning("评论内容不能为空！");
        return;
      }
      try {
        // 检查 userInfo 是否存在
        if (!this.$store.state.user || !this.$store.state.user.userInfo) {
          this.$message.error("用户信息未加载，请登录后再试！");
          return;
        }

        this.newComment.userId = this.$store.state.user.userInfo.id;
        this.newComment.type = 1;
        const res = await commentAPI.likeComment(this.newComment);
        if (res.data.status_code === 1) {
          this.showCommentForm = false; // 隐藏评论表单
          this.newComment.content = ""; // 清空评论内容
          console.log("评论成功！");
          // 评论成功后刷新页面
          this.$router.go(0); // 使用Vue Router重新加载当前页面
        } else {
          this.$message.error("评论提交失败！");
        }
      } catch (error) {
        this.$message.error("网络错误，请稍后再试！");
        console.error(error); // 打印错误信息
      }
    },

    // 取消评论
    cancelComment() {
      this.showCommentForm = false;
      this.newComment.content = ""; // 清空评论内容
    },
  },
};
</script>

<style scoped>
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

/* 评论表单样式 */
.comment-form {
  margin-top: 20px;
}
</style>
