use pasila;
CREATE TABLE Members (
                         `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `email` VARCHAR(320) NULL,
                         `name` VARCHAR(10) NULL,
                         `channel` VARCHAR(10) NULL,
                         `channel_desc` VARCHAR(100) NULL,
                         `password` VARCHAR(100) NULL,
                         `phone` VARCHAR(11) NULL,
                         `address` VARCHAR(50) NULL,
                         `address_detail` VARCHAR(50) NULL,
                         `gender` TINYINT NULL,
                         `birth` DATE NULL,
                         `bank` VARCHAR(10) NULL,
                         `account` VARCHAR(20) NULL,
                         `profile` VARCHAR(2083) NULL,
                         `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                         `login_type` ENUM('google', 'kakao', 'naver', 'email') NULL,
                         `token` VARCHAR(256) NULL
);

CREATE TABLE LargeCategory (
                               `id` INT NOT NULL PRIMARY KEY,
                               `name` VARCHAR(10) NULL,
                               `imageUrl` VARCHAR(2083) NULL
);
CREATE TABLE MiddleCategory (
                                `id` INT NOT NULL PRIMARY KEY,
                                `category_id` INT NOT NULL,
                                `name` VARCHAR(20) NULL,
                                FOREIGN KEY (`category_id`) REFERENCES LargeCategory (`id`)
);

CREATE TABLE DetailCategory (
                                `id` INT NOT NULL PRIMARY KEY,
                                `name` VARCHAR(20) NULL,
                                `middlecategory_id` INT NOT NULL,
                                FOREIGN KEY (`middlecategory_id`) REFERENCES MiddleCategory (`id`)
);

CREATE TABLE Product (
                         `id` VARCHAR(15) NOT NULL PRIMARY KEY,
                         `seller_id` INT NOT NULL,
                         `largecategory_id` INT NOT NULL,
                         `middlecategory_id` INT NOT NULL,
                         `detailcategory_id` INT NOT NULL,
                         `name` VARCHAR(30) NULL,
                         `desc` VARCHAR(10000) NULL,
                         `created_at` DATETIME NULL,
                         `updated_at` DATETIME NULL,
                         `thumbnail` VARCHAR(2083) NULL,
                         FOREIGN KEY (`seller_id`) REFERENCES Members (`id`),
                         FOREIGN KEY (`largecategory_id`) REFERENCES LargeCategory (`id`),
                         FOREIGN KEY (`middlecategory_id`) REFERENCES MiddleCategory (`id`),
                         FOREIGN KEY (`detailcategory_id`) REFERENCES DetailCategory (`id`)
);

CREATE TABLE Orders (
                        `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        `user_id` INT NOT NULL,
                        `product_id` VARCHAR(15) NOT NULL,
                        `option_id` INT NOT NULL,
                        `order_cnt` INT NULL,
                        `name` VARCHAR(10) NULL,
                        `address` VARCHAR(50) NULL,
                        `status` VARCHAR(10) NULL,
                        `created_at` DATETIME NULL,
                        `deleted_at` DATETIME NULL,
                        `price` INT NULL,
                        FOREIGN KEY (`user_id`) REFERENCES Members (`id`),
                        FOREIGN KEY (`product_id`) REFERENCES Product (`id`)
);



CREATE TABLE Live (
                      `id` VARCHAR(15) NOT NULL PRIMARY KEY,
                      `user_id` INT NOT NULL,
                      `product_id` VARCHAR(15) NOT NULL,
                      `title` VARCHAR(30) NULL,
                      `on` DATETIME NULL,
                      `off` DATETIME NULL,
                      `script` TEXT NULL,
                      `full_video` VARCHAR(2083) NULL,
                      `short_video` VARCHAR(2083) NULL,
                      `like_cnt` INT NULL,
                      `created_at` DATETIME NULL,
                      `is_on` TINYINT NULL,
                      FOREIGN KEY (`user_id`) REFERENCES Members (`id`),
                      FOREIGN KEY (`product_id`) REFERENCES Product (`id`)
);

CREATE TABLE Chatbot (
                         `id` INT NOT NULL PRIMARY KEY,
                         `live_id` VARCHAR(15) NOT NULL,
                         `keyword` VARCHAR(20) NULL,
                         `answer` VARCHAR(300) NULL,
                         FOREIGN KEY (`live_id`) REFERENCES Live (`id`)
);


CREATE TABLE ProductOption (
                               `id` INT NOT NULL PRIMARY KEY,
                               `product_id` VARCHAR(15) NOT NULL,
                               `name` VARCHAR(20) NULL,
                               `stock` INT NULL,
                               `price` INT NULL,
                               `discountPrice` INT NULL,
                               FOREIGN KEY (`product_id`) REFERENCES Product (`id`)
);

CREATE TABLE Livelog (
                         `id` INT NOT NULL PRIMARY KEY,
                         `live_id` VARCHAR(15) NOT NULL,
                         `start` DATETIME NULL,
                         `end` DATETIME NULL,
                         `title` VARCHAR(30) NULL,
                         `subtitle` VARCHAR(30) NULL,
                         FOREIGN KEY (`live_id`) REFERENCES Live (`id`)
);

CREATE TABLE Shortping (
                           `id` VARCHAR(15) NOT NULL PRIMARY KEY,
                           `product_id` VARCHAR(15) NOT NULL,
                           `title` VARCHAR(30) NULL,
                           `like_cnt` INT NULL,
                           `created_at` DATETIME NULL,
                           FOREIGN KEY (`product_id`) REFERENCES Product (`id`)
);

