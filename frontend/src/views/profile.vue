<template>
  <div class="container">
    <!-- 左侧边栏 -->
    <el-aside class="container-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="container-body">
      <!-- 用户信息展示 -->
      <div v-if="userInfo && userInfo.username" class="profile-container">
        <!-- 头像 -->
        <div class="avatar-section">
          <el-avatar
            :size="100"
            :src="
              'http://localhost:8080/backend_war_exploded' + userInfo.avatar ||
              'https://via.placeholder.com/100'
            "
            alt="用户头像"
          />
        </div>

        <!-- 用户详细信息 -->
        <div class="info-section">
          <h2>{{ userInfo.username || "未登录" }}</h2>
          <p><strong>邮箱：</strong> {{ userInfo.email || "未绑定" }}</p>
          <p><strong>手机号：</strong> {{ userInfo.phone || "未绑定" }}</p>
          <p>
            <strong>状态：</strong>
            <el-tag :type="userInfo.status === 1 ? 'success' : 'danger'">
              {{ userInfo.status === 1 ? "启用" : "禁用" }}
            </el-tag>
          </p>
          <p>
            <strong>注册时间：</strong>
            {{ formatDate(userInfo.createdAt) || "未知" }}
          </p>
        </div>

        <!-- 操作按钮 -->
        <div class="action-section">
          <el-button type="primary" @click="openEditDialog">编辑资料</el-button>
          <el-button type="danger" @click="logout">退出登录</el-button>
        </div>
      </div>

      <!-- 如果未登录，显示提示信息 -->
      <div v-else class="login-prompt">
        <p>请先登录以查看个人信息。</p>
      </div>

      <!-- 编辑资料的弹窗 -->
      <el-dialog
        v-model="editDialogVisible"
        title="编辑资料"
        width="400px"
        :close-on-click-modal="false"
      >
        <el-form :model="editForm" ref="editForm" label-width="100px">
          <!-- 头像上传 -->
          <el-form-item label="头像">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/backend_war_exploded/file/upload"
              :show-file-list="false"
              :before-upload="beforeUpload"
              :on-success="handleAvatarSuccess"
              accept="image/*"
            >
              <el-button size="small" type="primary">点击上传新头像</el-button>
            </el-upload>
          </el-form-item>

          <!-- 用户名输入框 -->
          <el-form-item label="用户名">
            <el-input v-model="editForm.username" placeholder="请输入用户名" />
          </el-form-item>

          <!-- 邮箱输入框 -->
          <el-form-item label="邮箱">
            <el-input v-model="editForm.email" placeholder="请输入邮箱" />
          </el-form-item>

          <!-- 手机号输入框 -->
          <el-form-item label="手机号">
            <el-input v-model="editForm.phone" placeholder="请输入手机号" />
          </el-form-item>
        </el-form>

        <!-- 弹窗底部操作 -->
        <span class="dialog-footer">
          <el-button @click="editDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitEditProfile">保存</el-button>
        </span>
      </el-dialog>
    </el-main>
  </div>
</template>

<script>
import UserAPI from "@/api/userAPI";
import { mapActions, mapState } from "vuex";
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "userProfilePage",
  components: { AppSideBar },
  computed: {
    ...mapState("user", ["userInfo"]), // 映射 Vuex 的 userInfo 状态
  },
  data() {
    return {
      // 控制弹窗显示的变量
      editDialogVisible: false,

      // 编辑资料的表单数据
      editForm: {
        username: this.userInfo ? this.userInfo.username : "", // 默认填充用户名
        email: this.userInfo ? this.userInfo.email : "", // 默认填充邮箱
        phone: this.userInfo ? this.userInfo.phone : "", // 默认填充手机号
        avatar: this.userInfo ? this.userInfo.avatar : null,
      },
    };
  },
  created() {
    // 如果用户信息不存在，则尝试加载
    if (!this.userInfo) {
      this.loadUserInfo();
    }
  },
  methods: {
    ...mapActions("user", ["updateUserInfo"]), // 从 Vuex 注册 actions

    // 打开编辑资料弹窗
    openEditDialog() {
      console.log("打开编辑资料弹窗");
      // 填充当前用户信息到表单
      this.editForm.username = this.userInfo.username;
      this.editForm.email = this.userInfo.email;
      this.editForm.phone = this.userInfo.phone;

      // 显示弹窗
      this.editDialogVisible = true;
    },

    // 提交编辑资料
    async submitEditProfile() {
      try {
        const updatedUserInfo = { ...this.userInfo, ...this.editForm }; // 合并原用户信息和修改后的信息

        console.log("编辑后的用户信息", updatedUserInfo);
        // 发送请求到后端更新用户信息
        const res = await UserAPI.updateUserInfo(updatedUserInfo);

        if (res.data.status_code === 1) {
          // 如果返回成功，更新 Vuex 中的用户信息
          this.updateUserInfo(updatedUserInfo);
          this.$message.success("资料更新成功"); // 提示更新成功
          this.editDialogVisible = false; // 关闭弹窗
        } else {
          this.$message.error("更新失败，请重试"); // 提示更新失败
        }
      } catch (error) {
        this.$message.error("更新失败，请重试"); // 捕获错误并提示
      }
    },
    // 上传头像前的验证
    beforeUpload(file) {
      const isImage = file.type.startsWith("image/");
      if (!isImage) {
        this.$message.error("请上传图片文件");
      }
      return isImage;
    },
    // 上传头像成功后的处理
    handleAvatarSuccess(response) {
      console.log("头像上传成功", response);
      this.editForm.avatar = response.data;
      this.$message.success("头像上传成功");
    },

    // 退出登录
    logout() {
      console.log("退出登录");
      document.cookie =
        "shUserId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      location.reload();
    },

    // 从后端加载用户信息
    async loadUserInfo() {
      try {
        // 获取 Cookie 中的 shUserId
        const shUserId = this.getCookie("shUserId");

        // 使用获取到的 shUserId 发送请求获取用户信息
        const res = await UserAPI.getUserInfo(shUserId);
        console.log(res);
        if (res.data.status_code === 1) {
          // 更新用户信息到 Vuex
          this.updateUserInfo(res.data.data);
        }
      } catch (error) {
        console.error("Error loading user info:", error);
      }
    },

    // 获取 Cookie 的函数
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },

    // 格式化日期
    formatDate(dateArray) {
      // 将 [2024, 12, 13, 15, 46, 29] 转换为 JavaScript Date 对象
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

      // 使用 toLocaleString 方法格式化日期
      return date.toLocaleString("zh-CN", {
        weekday: "long", // 星期几
        year: "numeric", // 年份
        month: "long", // 月份（完整）
        day: "numeric", // 日期
        hour: "2-digit", // 小时
        minute: "2-digit", // 分钟
        second: "2-digit", // 秒
        hour12: false, // 24小时制
      });
    },
  },
};
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  background-color: #ffffff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.avatar-section {
  margin-bottom: 20px;
}

.info-section {
  margin-bottom: 30px;
  line-height: 1.8;
  color: #333;
}

.action-section {
  display: flex;
  gap: 10px;
}

.login-prompt {
  text-align: center;
  color: #888;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
}

.el-button {
  border-radius: 20px;
}

.el-avatar {
  border: 2px solid #85b7ee;
}
</style>
