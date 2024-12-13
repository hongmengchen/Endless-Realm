<template>
  <div class="postList">
    <el-menu
      default-active="1"
      mode="horizontal"
      @select="handleSelect"
      background-color="#f7f7f7"
      text-color="#666"
      active-text-color="#409EFF"
      class="menu-bar"
    >
      <el-menu-item index="1">正常动态</el-menu-item>
      <el-menu-item index="2">违规动态</el-menu-item>
    </el-menu>

    <!-- 正常动态列表 -->
    <el-table
      v-if="mode == 1"
      :data="onlinePosts"
      stripe
      style="width: 100%; color: #5a5c61"
      border
      size="medium"
      class="post-table"
    >
      <el-table-column
        prop="createdAt"
        label="发布日期"
        width="250"
        show-overflow-tooltip
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="userId"
        label="发布用户"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column prop="content" label="动态内容" width="300">
      </el-table-column>
      <el-table-column
        prop="mediaUrl"
        label="动态图片"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="likeCount"
        label="点赞量"
        show-overflow-tooltip
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="commentCount"
        label="评论量"
        show-overflow-tooltip
        width="100"
      >
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            type="danger"
            @click="makeOfflinePost(scope.$index)"
            size="small"
            round
          >
            违规下架
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 违规动态列表 -->
    <el-table
      v-show="mode == 2"
      :data="OfflinePosts"
      stripe
      style="width: 100%; color: #5a5c61"
      border
      size="medium"
      class="post-table"
    >
      <el-table-column
        prop="createdAt"
        label="发布日期"
        width="250"
        show-overflow-tooltip
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="userId"
        label="发布用户"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column prop="content" label="动态内容" width="300">
      </el-table-column>
      <el-table-column
        prop="mediaUrl"
        label="动态图片"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="likeCount"
        label="点赞量"
        show-overflow-tooltip
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="commentCount"
        label="评论量"
        show-overflow-tooltip
        width="100"
      >
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            type="danger"
            @click="deletePost(scope.$index)"
            size="small"
            round
          >
            永久删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import PostAPI from "@/api/postAPI";
import { ElMessage } from "element-plus";

export default {
  name: "postList",
  data() {
    return {
      mode: 1,
      onlinePosts: [], // 正常动态
      OfflinePosts: [], // 违规动态
      status: 1,
    };
  },
  created() {
    this.getOnlinePosts();
  },
  methods: {
    handleSelect(val) {
      if (this.mode !== val) {
        this.mode = val;
        if (val == 1) {
          this.getOnlinePosts();
        }
        if (val == 2) {
          this.getOfflinePosts();
        }
      }
    },
    async makeOfflinePost(i) {
      const res = await PostAPI.updatePostStatus({
        id: this.onlinePosts[i].id,
        status: 0,
      });
      if (res.data.status_code === 1) {
        await this.getOnlinePosts();
        ElMessage.success("下架成功");
      } else {
        ElMessage.error(res.msg);
      }
    },
    async deletePost(i) {
      const res = await PostAPI.deletePost(this.OfflinePosts[i].id);
      if (res.data.status_code === 1) {
        await this.getOfflinePosts();
        ElMessage.success("删除成功");
      } else {
        ElMessage.error(res.msg);
      }
    },
    async getOnlinePosts() {
      const res = await PostAPI.getPostByStatus(1);
      if (res.data.status_code === 1) {
        this.onlinePosts = res.data.data;
        this.$nextTick(() => {});
      } else {
        ElMessage.error(res.msg);
      }
    },
    async getOfflinePosts() {
      const res = await PostAPI.getPostByStatus(0);
      if (res.data.status_code === 1) {
        this.OfflinePosts = res.data.data;
        this.$nextTick(() => {});
      } else {
        ElMessage.error(res.msg);
      }
    },
    formatDate(dateArray) {
      const date = new Date(
        Date.UTC(
          dateArray[0],
          dateArray[1] - 1,
          dateArray[2],
          dateArray[3],
          dateArray[4],
          dateArray[5]
        )
      );
      return date.toLocaleString("zh-CN", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: false,
        timeZone: "UTC",
      });
    },
  },
};
</script>

<style scoped>
.postList {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.1);
}

.menu-bar {
  border-bottom: 2px solid #f1f1f1;
  margin-bottom: 20px;
}

.post-table {
  margin-top: 20px;
  border-radius: 8px;
}

.el-button {
  border-radius: 20px;
  transition: all 0.3s ease;
}

.el-button:hover {
  background-color: #f56c6c;
  border-color: #f56c6c;
}
</style>
