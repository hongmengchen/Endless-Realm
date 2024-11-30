# 无垠之界 (Endless Realm)

**Endless Realm (ER)** 是一个基于 Java EE - SSM 技术栈开发的社交平台，旨在打造一个开放、智能、未来感的社交空间。平台提供无界限的社交互动空间，支持多设备、跨平台访问，连接全球用户，带来更加多元化、智能化的社交体验。

---

## 项目背景

无垠之界（Endless Realm）以“开放、智能、未来、无限”为核心价值，致力于通过社交互动打破地域和时间的限制，提供创新、智能化的社交体验。平台支持多种社交功能，包括动态发布、即时通讯、好友推荐、智能推荐等，旨在为用户提供无缝连接人与人、人与信息的体验。

---

## 技术栈

- **后端**：Java EE（Spring、Spring MVC、MyBatis）
- **前端**：Vue.js + Element UI
- **数据库**：MySQL
- **API 工具**：Apifox
- **版本管理**：Git + GitHub
- **开发工具**：
  - 后端：IntelliJ IDEA 2024
  - 前端：WebStorm

---

## 功能模块

### 基础模块

1. **用户模块**：
   - 用户注册、登录（邮箱/手机验证）
   - 个人资料管理、密码找回、权限验证

2. **内容模块**：
   - 动态发布、评论、点赞
   - 支持图片/视频上传与展示

3. **好友关系模块**：
   - 关注/取消关注、好友推荐、好友列表

4. **即时通讯模块**：
   - 文本聊天、文件/图片发送
   - 基于 WebSocket 实现实时聊天功能

### 高级功能

- **推荐系统**：根据用户兴趣标签或行为数据推荐内容与好友
- **社交分析与数据统计**：分析用户行为与活跃度
- **内容审核系统**：管理员后台审核内容
- **多媒体支持**：图片、视频上传与播放

---

## 数据库设计

- **用户表**：保存用户信息、权限等
- **动态表**：保存用户发布的动态内容，支持图片/视频
- **评论与点赞表**：保存动态下的评论与点赞记录
- **好友关系表**：记录用户之间的关注、朋友关系
- **聊天记录表**：保存即时通讯的聊天记录
- **推荐数据表**：保存用户行为数据，用于个性化推荐
- **通知表**：保存用户通知与消息记录

---

## 项目结构

- `backend/`: 后端代码，基于 Spring 和 MyBatis 开发，包含 API 接口、服务逻辑、数据库操作等
- `frontend/`: 前端代码，基于 Vue.js 与 Element UI 实现，包含页面交互与布局
- `docs/`: 项目文档，包括需求文档、架构设计、数据库设计等
- `resources/`: 资源文件，如图片、视频等

---

## 开发与调试

### 前端开发

- 使用 Vue.js 和 Element UI 实现响应式页面设计
- 使用 Axios 调用后端 RESTful API，动态展示数据
- WebSocket 用于实现实时聊天功能

### 后端开发

- 使用 Spring 和 Spring MVC 搭建后端，集成 MyBatis 进行数据操作
- 使用 RESTful API 规范进行前后端交互
- 配置 Java EE 中的安全机制进行用户权限管理

### 开发工具

- **IDE**：使用 IntelliJ IDEA 开发后端，WebStorm 开发前端
- **调试**：利用 IDE 的调试工具快速发现并解决问题
- **版本管理**：通过 Git 管理代码，使用 GitHub 进行代码托管

---

## 安装与使用

### 前端部署

1. 克隆仓库并进入前端目录：
   ```bash
   git clone https://github.com/yourusername/Endless-Realm.git
   cd frontend
   ```

2. 安装依赖：
   ```bash
   npm install
   ```

3. 启动开发服务器：
   ```bash
   npm run serve
   ```

### 后端部署

1. 克隆仓库并进入后端目录：
   ```bash
   git clone https://github.com/yourusername/Endless-Realm.git
   cd backend
   ```

2. 配置数据库连接：
   - 编辑数据库配置文件，设置 MySQL 连接信息。

3. 启动后端服务：
   - 使用 Java EE 进行部署，选择适合的应用服务器（如 Tomcat）。

4. 访问平台：
   - 后端服务启动后，前端页面通过接口访问后端提供的 API。

---

## 贡献

我们欢迎开源社区的贡献！如果你有改进意见或功能建议，欢迎提交 **Issue** 或 **Pull Request**。

---

## 许可证

该项目使用 [MIT 许可证](LICENSE)。

---

## 学习资源

- [Spring 官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [MyBatis 官方文档](https://mybatis.org)
- [Vue.js 官方文档](https://vuejs.org)
- [Element UI 官方文档](https://element.eleme.io)
- [Apifox 官方文档](https://www.apifox.cn)

---

希望这个 README 文件能够帮助开发者快速了解项目架构、技术栈和部署流程。如果有任何问题或建议，欢迎提出！