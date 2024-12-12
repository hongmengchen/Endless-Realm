// 管理员状态管理模块
export default {
  namespaced: true, // 命名空间模块
  state: {
    adminInfo: null, // 用户信息
  },
  mutations: {
    // 更新管理员信息
    SET_ADMIN_INFO(state, adminInfo) {
      state.adminInfo = adminInfo;
    },
  },
  actions: {
    // 异步更新管理员信息
    updateAdminInfo({ commit }, adminInfo) {
      commit("SET_ADMIN_INFO", adminInfo);
    },
  },
  getters: {
    // 获取管理员信息
    adminInfo(state) {
      return state.adminInfo;
    },
  },
};
