<template>
  <div class="main-border">
    <el-menu
      default-active="1"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <span v-show="this.mode != 3" class="app-title">
        <el-input
          placeholder="用户账号..."
          v-model="searchValue"
          @keyup.enter="searchUser"
        >
          <el-button icon="el-icon-search" @click="searchUser"></el-button>
        </el-input>
      </span>
      <el-menu-item index="1">正常用户</el-menu-item>
      <el-menu-item index="2">违规用户</el-menu-item>
      <el-menu-item index="3">管理员</el-menu-item>
      <div v-show="this.mode === 3" class="addAdminButton">
        <el-button type="success" @click="adminRegVisible = true"
          >添加管理员</el-button
        >
        <el-dialog
          title="添加管理员"
          v-model:visible="adminRegVisible"
          width="25%"
        >
          <span style="margin-left: 10px">新增管理员名称</span>
          <el-input
            v-model="adminName"
            maxlength="8"
            placeholder="请输入管理员名称"
            style="padding: 10px 0"
            clearable
            required
          ></el-input>
          <span style="margin-left: 10px">新增管理员账户</span>
          <el-input
            v-model="adminAccount"
            minlength="8"
            maxlength="10"
            placeholder="请输入管理员账户"
            style="padding: 10px 0"
            clearable
            required
          ></el-input>
          <span style="margin-left: 10px">新增管理员密码</span>
          <el-input
            v-model="adminPassword"
            minlength="8"
            placeholder="请输入管理员密码"
            style="padding: 10px 0"
            show-password
            required
          ></el-input>
          <span style="margin-left: 10px">确认管理员密码</span>
          <el-input
            v-model="adminRePassword"
            minlength="10"
            placeholder="请再次输入管理员密码"
            style="padding: 10px 0"
            show-password
            required
          ></el-input>
          <span class="dialog-footer">
            <el-button type="primary" @click="regAdmin">添加</el-button>
          </span>
        </el-dialog>
      </div>
    </el-menu>

    <!-- 正常用户列表 -->
    <el-table
      v-show="this.mode == 1"
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
      v-show="this.mode == 2"
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
        prop="accountNumber"
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
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="success" @click="unsealUser(scope.$index)"
            >解封
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-table
      v-show="this.mode == 3"
      :data="userManage"
      stripe
      style="width: 100%; color: #5a5c61"
    >
      <el-table-column
        prop="accountNumber"
        label="管理员账号"
        show-overflow-tooltip
        width="200"
      >
      </el-table-column>
      <el-table-column prop="name" label="管理名称"> </el-table-column>
      <el-table-column prop="createdAt" label="创建时间"> </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :modelValue="nowPage"
        :page-size="7"
        background
        layout="prev, pager, next,jumper"
        :total="total"
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
      nowPage: 1,
      adminRegVisible: false, // 是否显示注册对话框
      userData: [], // 正常用户数据
      badUserData: [], // 违规用户数据
      userManage: [], // 管理员数据
      searchValue: "",
    };
  },
  created() {
    this.getUserData();
  },
  methods: {
    // 分页切换
    handleCurrentChange(val) {
      this.nowPage = val;
      if (this.mode == 1) {
        this.getUserData();
      }
      if (this.mode == 2) {
        this.getBadUserData();
      }
      if (this.mode == 3) {
        this.getUserManage();
      }
    },
    // 菜单切换
    handleSelect(val) {
      if (this.mode !== val) {
        this.mode = val;
        if (val == 1) {
          this.nowPage = 1;
          this.getUserData();
        }
        if (val == 2) {
          this.nowPage = 1;
          this.getBadUserData();
        }
        if (val == 3) {
          this.nowPage = 1;
          this.getUserManage();
        }
      }
    },
    // 获取用户数据--正常用户
    async getUserData() {
      const res = await AdminAPI.getAllUser(1);
      if (res.data.status_code === 1) {
        this.userData = res.data.data;
      }
    },
    // 获取用户数据--违规用户
    getBadUserData() {
      this.$api
        .getUserData({
          page: this.nowPage,
          nums: 8,
          status: 2,
        })
        .then((res) => {
          if (res.status_code == 1) {
            this.badUserData = res.data.userList;
            this.total = res.data.total;
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 获取管理员数据
    getUserManage() {
      this.$api
        .getUserManage({
          page: this.nowPage,
          nums: 8,
        })
        .then((res) => {
          if (res.status_code === 1) {
            this.userManage = res.data.adminList;
            this.total = res.data.count;
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 封号
    sealUser(i) {
      console.log(this.userData[i].id);
      this.$api
        .updateUserStatus({
          id: this.userData[i].id,
          status: 2,
        })
        .then((res) => {
          if (res.status_code == 1) {
            this.getUserData();
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 解封
    unsealUser(i) {
      this.$api
        .updateUserStatus({
          id: this.badUserData[i].id,
          status: 1,
        })
        .then((res) => {
          if (res.status_code == 1) {
            this.getBadUserData();
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 添加管理员
    regAdmin() {
      if (this.adminPassword === this.adminRePassword) {
        this.$api
          .regAdministrator({
            name: this.adminName,
            accountNumber: this.adminAccount,
            password: this.adminPassword,
          })
          .then((res) => {
            if (res.status_code == 1) {
              this.total = this.total + 1;
              this.nowPage = Math.ceil(this.total / 8);
              console.log(this.nowPage);
              this.getUserManage();
            } else {
              this.$message.error(res.msg);
            }
          })
          .catch((e) => {
            console.log(e);
            this.$message.error("添加失败，账号重复或网络异常");
          });
        this.adminRegVisible = false;
      } else {
        this.$message.error("两次输入的密码不一致");
      }
    },
    // 搜索用户
    searchUser() {
      this.$api
        .queryUser({
          searchValue: this.searchValue,
          mode: this.mode,
          page: this.nowPage,
          nums: 8,
        })
        .then((res) => {
          if (res.status_code == 1) {
            if (this.mode == 1) {
              this.userData = res.data.userList;
              this.total = res.data.total;
            } else {
              this.badUserData = res.data.userList;
              this.total = res.data.total;
            }
          } else {
            this.$message.error(res.msg);
          }
        })
        .catch((e) => {
          console.error("查询用户失败:", e);
          this.$message.error("请求失败，请稍后再试");
        });
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
