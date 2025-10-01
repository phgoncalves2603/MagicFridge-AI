CREATE TABLE Food_Item (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       quantity INT NOT NULL,
       expiration DATE NOT NULL,
       category VARCHAR(50)
);