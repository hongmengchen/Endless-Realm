<template>
  <div class="main-border">
    <el-menu
      default-active="1"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#f7f7f7"
      text-color="#666"
      active-text-color="#ffd04b"
      border="none"
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
            :size="50"
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
      />
      <el-table-column
        prop="email"
        label="用户邮箱"
        show-overflow-tooltip
        min-width="200"
        width="200"
      />
      <el-table-column
        prop="phone"
        label="用户电话"
        show-overflow-tooltip
        min-width="150"
        width="150"
      />
      <el-table-column
        prop="createdAt"
        label="注册时间"
        show-overflow-tooltip
        width="250"
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="danger" size="small" @click="sealUser(scope.$index)"
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
            :size="50"
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
      />
      <el-table-column
        prop="email"
        label="用户邮箱"
        show-overflow-tooltip
        min-width="200"
        width="200"
      />
      <el-table-column
        prop="phone"
        label="用户电话"
        show-overflow-tooltip
        min-width="150"
        width="150"
      />
      <el-table-column
        prop="createdAt"
        label="注册时间"
        show-overflow-tooltip
        width="250"
      >
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            type="success"
            size="small"
            @click="unsealUser(scope.$index)"
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
      />
      <el-table-column prop="createdAt" label="创建时间">
        <template v-slot="scope">
          {{ formatDate(scope.row.createdAt) || "未知" }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import AdminAPI from "@/api/adminAPI";

export default {
  name: "userList",
  data() {
    return {
      mode: 1,
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
  },
};
</script>

<style scoped>
.main-border {
  background-color: #fff;
  padding: 20px 30px;
  box-shadow: 0 1px 15px -6px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  margin-top: 20px;
}

.el-menu-demo {
  border-radius: 8px;
  margin-bottom: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-table-column {
  text-align: center;
}

.el-button {
  border-radius: 4px;
  font-size: 14px;
  padding: 6px 16px;
}

.el-avatar {
  border-radius: 8px;
}

.el-table-column .cell {
  padding: 10px;
  text-align: center;
}

.el-button:hover {
  opacity: 0.8;
}

.el-menu-item {
  font-size: 16px;
  padding: 10px 20px;
  border-radius: 6px;
}

.el-menu-item:hover {
  background-color: #4081ff;
  color: #fff;
}
</style>
