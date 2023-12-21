-- 创建 user_id 字段的索引
CREATE INDEX idx_user_id ON post (user_id);

-- 创建 color 字段的索引
CREATE INDEX idx_color ON post (color);

-- 创建 exist 字段的索引
CREATE INDEX idx_exist ON post (exist);

-- 创建 email 字段的索引
CREATE INDEX idx_email ON user (email);