<template>
  <div class="main-border">
    <el-menu
      default-active="1"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">正常用户</el-menu-item>
      <el-menu-item index="2">违规用户</el-menu-item>
      <el-menu-item index="3">管理员</el-menu-item>
    </el-menu>

    <!-- 正常用户列表 -->
    <el-table
      v-if="mode == 1"
      :data="userData"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column label="头像" width="100">
        <template v-slot="scope">
          <el-avatar
            shape="square"
            :size="23"
            :src="scope.row.avatar"
          ></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户账号"
        show-overflow-tooltip
        min-width="150"
        width="150"
      >
      </el-table-column>
      <el-table-column
        prop="email"
        label="用户邮箱"
        show-overflow-tooltip
        min-width="200"
        width="200"
      >
      </el-table-column>
      <el-table-column
        prop="phone"
        label="用户电话"
        show-overflow-tooltip
        min-width="150"
        width="150"
      >
      </el-table-column>
      <el-table-column
        prop="createdAt"
        label="注册时间"
        show-overflow-tooltip
        width="200"
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="danger" @click="sealUser(scope.$index)"
            >封号
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 违规用户列表 -->
    <el-table
      v-if="mode == 2"
      :data="badUserData"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column label="头像" width="100px">
        <template v-slot="scope">
          <el-avatar
            shape="square"
            :size="23"
            :src="scope.row.avatar"
          ></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户账号"
        show-overflow-tooltip
        min-width="150"
        width="150"
      >
      </el-table-column>
      <el-table-column
        prop="email"
        label="用户邮箱"
        show-overflow-tooltip
        min-width="200"
        width="200"
      >
      </el-table-column>
      <el-table-column
        prop="phone"
        label="用户电话"
        show-overflow-tooltip
        min-width="150"
        width="150"
      >
      </el-table-column>
      <el-table-column
        prop="createdAt"
        label="注册时间"
        show-overflow-tooltip
        width="200"
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="success" @click="unsealUser(scope.$index)"
            >解封
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 管理员列表 -->
    <el-table
      v-if="mode == 3"
      :data="adminData"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column
        prop="username"
        label="管理员账号"
        show-overflow-tooltip
        width="200"
      >
      </el-table-column>
      <el-table-column prop="createdAt" label="创建时间">
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :modelValue="nowPage"
        background
        layout="prev, pager, next,jumper"
        @update:modelValue="nowPage = $event"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import AdminAPI from "@/api/adminAPI";

export default {
  name: "userList",
  data() {
    return {
      mode: 1,
      adminRegVisible: false, // 是否显示注册对话框
      userData: [], // 正常用户数据
      badUserData: [], // 违规用户数据
      adminData: [], // 管理员数据
    };
  },
  created() {
    this.getUserData();
  },
  methods: {
    // 菜单切换
    handleSelect(val) {
      if (this.mode !== val) {
        this.mode = val;
        if (val == 1) {
          this.getUserData();
        }
        if (val == 2) {
          this.getBadUserData();
        }
        if (val == 3) {
          this.getAdminData();
        }
      }
    },
    // 获取用户数据--正常用户
    async getUserData() {
      const res = await AdminAPI.getAllUser(1);
      if (res.data.status_code === 1) {
        this.userData = res.data.data;
        this.$nextTick(() => {
          // 确保 DOM 更新已完成
        });
      } else {
        this.$message.error(res.msg);
      }
    },
    // 获取用户数据--违规用户
    async getBadUserData() {
      const res = await AdminAPI.getAllUser(0);
      if (res.data.status_code === 1) {
        this.badUserData = res.data.data;
        this.$nextTick(() => {
          // 确保 DOM 更新已完成
        });
      } else {
        this.$message.error(res.msg);
      }
    },
    // 获取管理员数据
    async getAdminData() {
      const res = await AdminAPI.getAllAdmin();
      if (res.data.status_code === 1) {
        this.adminData = res.data.data;
        this.$nextTick(() => {
          // 确保 DOM 更新已完成
        });
      } else {
        this.$message.error(res.msg);
      }
    },
    // 封号
    async sealUser(i) {
      console.log("封号:" + this.userData[i].id);
      const res = await AdminAPI.updateUserStatus({
        id: this.userData[i].id,
        status: 0,
      });
      if (res.data.status_code === 1) {
        await this.getUserData();
      } else {
        this.$message.error(res.msg);
      }
    },
    // 解封
    async unsealUser(i) {
      console.log("解封:" + this.badUserData[i].id);
      const res = await AdminAPI.updateUserStatus({
        id: this.badUserData[i].id,
        status: 1,
      });
      if (res.data.status_code === 1) {
        await this.getBadUserData();
      } else {
        this.$message.error(res.msg);
      }
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
  },
};
</script>

<style scoped>
.main-border {
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

.addAdminButton {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 60px;
  outline: none;
}
</style>
