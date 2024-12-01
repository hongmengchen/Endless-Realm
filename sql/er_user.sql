CREATE TABLE er_user (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户 ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（加密存储）',
    email VARCHAR(100) UNIQUE COMMENT '邮箱',
    phone VARCHAR(20) UNIQUE COMMENT '手机号',
    avatar VARCHAR(255) DEFAULT NULL COMMENT '用户头像 URL',
    status TINYINT DEFAULT 1 COMMENT '用户状态（1-正常，0-禁用）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息最后更新时间'
) COMMENT '用户表';

INSERT INTO er_user (username, password, email, phone, avatar, status) VALUES
('张三', '123456', 'zhang@example.com', '1234567890', NULL, 1),
('李四', '456789', 'li@example.com', '0987654321', NULL, 1),
('王五', '147852', 'wang@example.com', '1122334455', NULL, 0); -- 被禁用用户
