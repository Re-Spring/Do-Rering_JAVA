USE dorering;

CREATE TABLE `user_info` (
                             `user_code` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `name` VARCHAR(10),
                             `id` VARCHAR(20),
                             `pwd` VARCHAR(20),
                             `phone` VARCHAR(20),
                             `role` VARCHAR(20),
                             `withdrawal_status` VARCHAR(20),
                             `voice_id` VARCHAR(20),
                             `withdrawal_date` DATE,
                             `enroll_date` DATE
);

CREATE TABLE `fairytale_info` (
                                  `fairytale_code` INTEGER NOT NULL PRIMARY KEY,
                                  `user_code` INTEGER NOT NULL,
                                  `fairytale_summary` VARCHAR(5000),
                                  `fairytale_title` VARCHAR(255),
                                  `fairytale_genre` VARCHAR(255),
                                  `fairytale_thumb` VARCHAR(255),
                                  `fairytale_image` VARCHAR(255),
                                  FOREIGN KEY (`user_code`) REFERENCES `user_info` (`user_code`)
);
CREATE TABLE `voice` (
                         `voice_code` INTEGER NOT NULL PRIMARY KEY,
                         `fairytale_code` INTEGER NOT NULL,
                         `user_code` INTEGER NOT NULL,
                         FOREIGN KEY (`fairytale_code`) REFERENCES `fairytale_info` (`fairytale_code`),
                         FOREIGN KEY (`user_code`) REFERENCES `user_info` (`user_code`)
);




CREATE TABLE `Fairytale_video_info` (
                                        `pro_video_code` INTEGER NOT NULL,
                                        `fairytale_code` INTEGER NOT NULL,
                                        `video_path` VARCHAR(255),
                                        PRIMARY KEY (`pro_video_code`, `fairytale_code`),
                                        FOREIGN KEY (`fairytale_code`) REFERENCES `fairytale_info` (`fairytale_code`)
);

