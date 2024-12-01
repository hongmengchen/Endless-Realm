CREATE TABLE er_message (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '消息 ID',
    sender_id INT NOT NULL COMMENT '发送者用户 ID',
    receiver_id INT NOT NULL COMMENT '接收者用户 ID',
    content TEXT NOT NULL COMMENT '消息内容',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
    FOREIGN KEY (sender_id) REFERENCES er_user(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES er_user(id) ON DELETE CASCADE
) COMMENT '聊天记录表';

INSERT INTO er_message (sender_id, receiver_id, content) VALUES
(1, 2, 'Hi, 最近怎么样？'),
(2, 1, '挺好的，你呢？'),
(3, 1, '张三，你有空聊聊吗？');
