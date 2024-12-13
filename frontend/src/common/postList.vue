<template>
  <div class="postList">
    <el-menu
      default-active="1"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">正常动态</el-menu-item>
      <el-menu-item index="2">违规动态</el-menu-item>
    </el-menu>

    <!--正常动态列表-->
    <el-table
      v-if="mode == 1"
      :data="onlinePosts"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column
        prop="createdAt"
        label="发布日期"
        width="200"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="userId"
        label="发布用户"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="content"
        label="动态内容"
        width="100"
        show-overflow-tooltip
      >
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
          <el-button type="danger" @click="makeOfflinePost(scope.$index)"
            >违规下架
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-table
      v-show="mode == 2"
      :data="OfflinePosts"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column
        prop="createdAt"
        label="发布日期"
        width="200"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="userId"
        label="发布用户"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="content"
        label="动态内容"
        width="100"
        show-overflow-tooltip
      >
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
          <el-button type="danger" @click="deletePost(scope.$index)"
            >永久删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import PostAPI from "@/api/postAPI";

export default {
  name: "postList",
  data() {
    return {
      mode: 1,
      nowPage: 1,
      onlinePosts: [],
      OfflinePosts: [],
      status: 1,
    };
  },
  created() {
    this.getOnlinePosts();
  },
  methods: {
    // 菜单切换
    handleSelect(val) {
      if (this.mode !== val) {
        this.mode = val;
        if (val == 1) {
          this.nowPage = 1;
          this.getOnlinePosts();
        }
        if (val == 2) {
          this.nowPage = 1;
          this.getOfflinePosts();
        }
      }
    },
    // 违规下架
    makeOfflinePost(i) {
      this.$api
        .updateGoods({
          id: this.onlineGoods[i].id,
          status: "inactive",
        })
        .then((res) => {
          if (res.status_code == 1) {
            this.getOnlineGoods();
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 删除
    deletePost(i) {
      this.$api
        .updateGoods({
          id: this.OfflineGoods[i].id,
          status: 0,
        })
        .then((res) => {
          if (res.status_code == 1) {
            this.getOfflineGoods();
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 获取正常动态列表
    async getOnlinePosts() {
      const res = await PostAPI.getPostByStatus(1);
      if (res.data.status_code === 1) {
        this.onlinePosts = res.data.data;
        this.$nextTick(() => {
          // 确保 DOM 更新已完成
        });
      } else {
        this.$message.error(res.msg);
      }
    },
    // 获取违规动态列表
    async getOfflinePosts() {
      const res = await PostAPI.getPostByStatus(0);
      if (res.data.status_code === 1) {
        this.OfflinePosts = res.data.data;
        this.$nextTick(() => {
          // 确保 DOM 更新已完成
        });
      } else {
        this.$message.error(res.msg);
      }
    },
  },
};
</script>

<style scoped>
.postList {
  background-color: #fff;
  padding: 10px 30px;
  box-shadow: 0 1px 15px -6px rgba(0, 0, 0, 0.5);
  border-radius: 5px;
}

.block {
  display: flex;
  justify-content: center;
  padding-top: 15px;
  padding-bottom: 10px;
  width: 100%;
}
</style>
