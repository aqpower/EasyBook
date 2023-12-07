CREATE TABLE user (
    id INT PRIMARY KEY, -- 主键
    email VARCHAR(255),
    user_name VARCHAR(10),
    password VARCHAR(16),
    avatar TINYINT,
    role TINYINT
);
CREATE TABLE blacklist (
    id INT PRIMARY KEY, -- 主键
    user_id INT,
    blcak_user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (blcak_user_id) REFERENCES user(id) -- 外键
);
CREATE TABLE post (
    id INT PRIMARY KEY, -- 主键
    type INT,
    title VARCHAR(255),
    content_text TEXT,
    color VARCHAR(255),
    lyrics TEXT,
    like_num INT,
    collection_num INT,
    ip INT,
    violation_num INT
);

CREATE TABLE image (
    id INT PRIMARY KEY, -- 主键
    user_id INT,
    post_id INT,
    url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (post_id) REFERENCES post(id) -- 外键
);
CREATE TABLE comment (
    id INT PRIMARY KEY, -- 主键
    content TEXT,
    comment_time TIMESTAMP,
    user_id INT,
    commented_id INT,
    type TINYINT,
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (commented_id) REFERENCES post(id) -- 外键
);
CREATE TABLE violation (
    id INT PRIMARY KEY, -- 主键
    user_id INT,
    post_id INT,
    violation_time TIMESTAMP,
    violation_reason TEXT,
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (post_id) REFERENCES post(id) -- 外键
);
CREATE TABLE admin (
    id INT PRIMARY KEY, -- 主键
    name VARCHAR(255),
    password VARCHAR(16),
    role TINYINT
);
CREATE TABLE collection (
    id INT PRIMARY KEY, -- 主键
    user_id INT,
    post_id INT,
    collection_time TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (post_id) REFERENCES post(id) -- 外键
);
CREATE TABLE like (
    id INT PRIMARY KEY, -- 主键
    user_id INT,
    post_id INT,
    like_time TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id), -- 外键
    FOREIGN KEY (post_id) REFERENCES post(id) -- 外键
);
CREATE TABLE review (
    id INT PRIMARY KEY, -- 主键
    admin_id INT,
    violation_id INT,
    review_time TIMESTAMP,
    review_result TINYINT,
    FOREIGN KEY (admin_id) REFERENCES admin(id), -- 外键
    FOREIGN KEY (violation_id) REFERENCES violation(id) -- 外键
);

