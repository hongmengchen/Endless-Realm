CREATE TABLE er_comment (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '评论或点赞记录 ID',
    post_id INT NOT NULL COMMENT '关联的动态 ID',
    user_id INT NOT NULL COMMENT '评论或点赞的用户 ID',
    type TINYINT NOT NULL COMMENT '记录类型（1-评论，2-点赞）',
    content TEXT DEFAULT NULL COMMENT '评论内容，对点赞为 NULL',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间',
    FOREIGN KEY (post_id) REFERENCES er_post(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '评论与点赞表';

INSERT INTO er_comment (post_id, user_id, type, content) VALUES
(1, 2, 1, '真漂亮！希望我也能看到这么美的日落！'),
(1, 3, 2, NULL), -- 点赞
(2, 1, 1, '谢谢分享，我会尝试一下！'),
(2, 3, 2, NULL); -- 点赞
