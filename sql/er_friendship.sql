CREATE TABLE er_friendship (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '记录 ID',
    user_id INT NOT NULL COMMENT '用户 ID',
    friend_id INT NOT NULL COMMENT '好友 ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '添加好友时间',
    FOREIGN KEY (user_id) REFERENCES er_user(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '好友关系表';

INSERT INTO er_friendship (user_id, friend_id) VALUES
(1, 2), -- 张三 和 李四 是好友
(1, 3), -- 张三 和 王五 是好友
(2, 1); -- 李四 关注了 张三
