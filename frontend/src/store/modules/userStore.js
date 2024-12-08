// 用户状态管理模块
export default {
  namespaced: true, // 命名空间模块
  state: {
    userInfo: null, // 用户信息
  },
  mutations: {
    // 更新用户信息
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo;
    },
  },
  actions: {
    // 异步更新用户信息
    updateUserInfo({ commit }, userInfo) {
      commit("SET_USER_INFO", userInfo);
    },
  },
  getters: {
    // 获取用户信息
    userInfo(state) {
      return state.userInfo;
    },
  },
};
