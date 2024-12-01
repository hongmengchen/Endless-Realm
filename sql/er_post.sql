CREATE TABLE er_post (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '动态 ID',
    user_id INT NOT NULL COMMENT '发布动态的用户 ID',
    content TEXT NOT NULL COMMENT '动态内容',
    media_url VARCHAR(255) DEFAULT NULL COMMENT '图片/视频 URL',
    like_count INT DEFAULT 0 COMMENT '点赞数量',
    comment_count INT DEFAULT 0 COMMENT '评论数量',
    status TINYINT DEFAULT 1 COMMENT '动态状态（1-公开，0-屏蔽）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '动态最后更新时间',
    FOREIGN KEY (user_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '动态表';

INSERT INTO er_post (user_id, content, media_url, like_count, comment_count, status) VALUES
(1, '今天的日落太美了！', NULL, 10, 5, 1),
(2, '分享一个好用的学习技巧：专注时间管理法！', NULL, 25, 10, 1),
(1, '周末约一场篮球比赛吧！', NULL, 15, 3, 0); -- 已屏蔽动态
