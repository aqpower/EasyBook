use easybook;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    user_name VARCHAR(10),
    password VARCHAR(16),
    avatar TINYINT NOT NULL,
    role TINYINT DEFAULT 3
) AUTO_INCREMENT = 10000000;
CREATE TABLE blacklist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    blcak_user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id), 
    FOREIGN KEY (blcak_user_id) REFERENCES user(id) 
);
CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content_text TEXT NOT NULL,
    color SMALLINT NOT NULL, 
    lyrics VARCHAR(255) DEFAULT '',
    like_num INT DEFAULT 0,
    collection_num INT DEFAULT 0,
    ip VARCHAR(25) NOT NULL,
    violation_num INT DEFAULT 0
) AUTO_INCREMENT = 1;


CREATE TABLE image (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    user_id INT,
    post_id INT,
    url VARCHAR(255) NOT NULL, 
    FOREIGN KEY (user_id) REFERENCES user(id), 
    FOREIGN KEY (post_id) REFERENCES post(id) 
);
CREATE TABLE comment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    comment_time TIMESTAMP NOT NULL,
    user_id INT NOT NULL,
    commented_id INT NOT NULL,
    type TINYINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (commented_id) REFERENCES post(id)
);

CREATE TABLE violation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    violation_time TIMESTAMP NOT NULL,
    violation_reason TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (post_id) REFERENCES post(id)
);

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(16) NOT NULL,
    role TINYINT DEFAULT 2
) AUTO_INCREMENT = 10000;
CREATE TABLE collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_id INT,
    collection_time TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id), 
    FOREIGN KEY (post_id) REFERENCES post(id) 
);

CREATE TABLE easylike (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_id INT,
    like_time TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id), 
    FOREIGN KEY (post_id) REFERENCES post(id) 
);

CREATE TABLE review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT,
    violation_id INT,
    review_time TIMESTAMP NOT NULL,
    review_result TINYINT NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES admin(id), 
    FOREIGN KEY (violation_id) REFERENCES violation(id) 
);

