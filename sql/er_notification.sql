CREATE TABLE er_notification (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '通知 ID',
    user_id INT NOT NULL COMMENT '接收通知的用户 ID',
    message TEXT NOT NULL COMMENT '通知内容',
    is_read TINYINT DEFAULT 0 COMMENT '是否已读（0-未读，1-已读）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '通知时间',
    FOREIGN KEY (user_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '通知表';

INSERT INTO er_notification (user_id, message, is_read) VALUES
(1, '您的动态已收到 10 个点赞！', 1), -- 已读通知
(2, '张三 评论了您的动态：谢谢分享！', 0), -- 未读通知
(3, '您发布的动态未通过审核，请修改后重新提交。', 0); -- 未读通知
