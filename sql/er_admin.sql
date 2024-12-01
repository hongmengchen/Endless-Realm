CREATE TABLE er_admin (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '管理员 ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '管理员用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（加密存储）',
    role VARCHAR(20) DEFAULT '普通管理员' COMMENT '管理员角色（如超级管理员）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '管理员表';

INSERT INTO er_admin (username, password, role) VALUES
('admin', 'password', '超级管理员'),
('moderator', 'moderator_password', '普通管理员');
