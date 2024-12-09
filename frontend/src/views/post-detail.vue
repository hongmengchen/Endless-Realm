<template>
  <div class="post-detail">
    <div v-if="post">
      <p>内容：{{ post.content }}</p>
      <img v-if="post.mediaUrl" :src="post.mediaUrl" alt="动态图片" />
      <p>点赞数：{{ post.likeCount }}</p>
      <p>评论数：{{ post.commentCount }}</p>
    </div>
  </div>
</template>

<script>
import PostAPI from "@/api/postAPI";

export default {
  name: "post-detail",
  data() {
    return {
      post: null,
    };
  },
  async created() {
    const postId = this.$route.params.postId;
    const res = await PostAPI.getPostDetail(postId);
    this.post = res.data.data;
  },
};
</script>

<style scoped></style>
