CREATE TABLE er_recommendation (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '记录 ID',
    user_id INT NOT NULL COMMENT '用户 ID',
    action_type VARCHAR(20) NOT NULL COMMENT '用户行为类型（浏览、点赞等）',
    target_id INT NOT NULL COMMENT '关联的目标 ID（如动态 ID）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '行为发生时间',
    FOREIGN KEY (user_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '推荐数据表';

INSERT INTO er_recommendation (user_id, action_type, target_id) VALUES
(1, '浏览', 2), -- 张三 浏览了 李四 的动态
(2, '点赞', 1), -- 李四 点赞了 张三 的动态
(3, '评论', 1); -- 王五 评论了 张三 的动态
