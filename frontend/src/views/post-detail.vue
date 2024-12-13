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
        <el-button link @click="likePost">
          <el-icon><Star /></el-icon>
          点赞：{{ post.likeCount }}
        </el-button>
        <el-button link @click="handleCommentClick">
          <el-icon><ChatLineRound /></el-icon>
          评论：{{ post.commentCount }}
        </el-button>
      </div>
    </el-card>

    <!-- 评论表单 -->
    <el-collapse-transition>
      <div v-if="showCommentForm" class="comment-form">
        <el-input
          v-model="newComment.content"
          type="textarea"
          :rows="4"
          placeholder="请输入评论内容"
        />
        <div class="form-actions">
          <el-button type="primary" @click="submitComment">提交评论</el-button>
          <el-button @click="cancelComment">取消</el-button>
        </div>
      </div>
    </el-collapse-transition>

    <!-- 评论列表 -->
    <el-divider content-position="left">评论</el-divider>

    <!-- 评论为空时显示 -->
    <el-empty v-if="!comments.length" description="暂无评论" />

    <!-- 评论列表 -->
    <el-timeline v-else>
      <el-timeline-item
        v-for="comment in comments"
        :key="comment.id"
        :timestamp="formatDate(comment.createdAt)"
        placement="top"
      >
        <el-card class="comment-card">
          <template #header>
            <div class="comment-header">
              <span>{{ comment.author }}</span>
              <!-- 判断是否为当前用户的评论 -->
              <el-button
                v-if="comment.userId === userInfo.id"
                type="text"
                icon="el-icon-delete"
                @click="deleteComment(comment.id)"
                style="color: red"
              >
                删除
              </el-button>
            </div>
          </template>
          <p>{{ comment.content }}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import PostAPI from "@/api/postAPI";
import commentAPI from "@/api/commentAPI";
import { ChatLineRound, Star } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";

export default {
  name: "postDetailPage",
  components: { ChatLineRound, Star },
  computed: {
    userInfo() {
      return this.$store.state.user?.userInfo;
    },
  },
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
    try {
      const [postRes, commentRes] = await Promise.all([
        PostAPI.getPostDetail(this.$route.params.postId),
        commentAPI.getAllComment(this.$route.params.postId),
      ]);
      this.post = postRes.data.data;
      this.comments = commentRes.data.data || [];
    } catch (error) {
      this.$message.error("加载数据失败，请稍后再试");
    }
  },
  methods: {
    // 删除评论
    async deleteComment(commentId) {
      // 判断是否是自己的评论
      if (
        this.userInfo.id !==
        this.comments.find((c) => c.id === commentId).userId
      ) {
        ElMessage.error("你不能删除别人的评论");
        return;
      }

      // 提交删除请求
      try {
        const res = await commentAPI.deleteComment(commentId);
        if (res.data.status_code === 1) {
          ElMessage.success("评论删除成功");
          // 删除成功后更新评论列表
          this.comments = this.comments.filter(
            (comment) => comment.id !== commentId
          );
          this.post.commentCount--;
        } else {
          ElMessage.error("评论删除失败");
        }
      } catch (error) {
        ElMessage.error("网络错误，请稍后再试");
      }
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

    // 返回主页
    goHome() {
      this.$router.push("/"); // 跳转到主页
    },

    // 点赞
    async likePost() {
      // 检查是否已登录
      if (!this.userInfo) {
        ElMessage.error("请先登录");
        return;
      }
      // 提交点赞请求
      try {
        const res = await commentAPI.likeComment({
          ...this.newComment,
          userId: this.userInfo.id,
          type: 2,
        });
        if (res.data.status_code === 1) {
          ElMessage.success("点赞成功");
          this.post.likeCount++;
        } else {
          ElMessage.error("点赞失败");
        }
      } catch (error) {
        ElMessage.error("网络错误，请稍后再试");
      }
    },

    // 打开评论表单
    handleCommentClick() {
      if (!this.userInfo) {
        ElMessage.error("请先登录");
        return;
      }
      if (this.userInfo.status === 0) {
        ElMessage.error("您的账号已被封禁，暂时无法评论");
        return;
      }
      this.showCommentForm = !this.showCommentForm;
    },

    // 提交评论
    async submitComment() {
      if (!this.newComment.content.trim()) {
        ElMessage.warning("评论内容不能为空");
        return;
      }
      if (!this.userInfo) {
        ElMessage.error("请先登录");
        return;
      }
      if (this.userInfo.status === 0) {
        ElMessage.error("您的账号已被封禁，暂时无法评论");
        return;
      }
      try {
        const res = await commentAPI.likeComment({
          ...this.newComment,
          userId: this.userInfo.id,
          type: 1,
        });
        if (res.data.status_code === 1) {
          ElMessage.success("评论成功");
          this.showCommentForm = false;
          this.newComment.content = "";
          // 刷新评论
          const commentRes = await commentAPI.getAllComment(
            this.$route.params.postId
          );
          this.comments = commentRes.data.data || [];
          this.post.commentCount++;
        } else {
          ElMessage.error("评论提交失败");
        }
      } catch (error) {
        ElMessage.error("网络错误，请稍后再试");
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
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.back-button {
  margin-bottom: 20px;
}

.post-card {
  margin-bottom: 20px;
}

.post-content {
  margin-bottom: 15px;
}

.post-text {
  font-size: 16px;
  line-height: 1.6;
}

.post-image {
  max-width: 100%;
  border-radius: 4px;
  margin-top: 10px;
}

.post-meta {
  display: flex;
  justify-content: flex-start;
  gap: 20px;
}

.comment-form {
  margin-top: 20px;
}

.form-actions {
  margin-top: 10px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.comment-card {
  margin-bottom: 15px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
