-- Drop existing database first
DROP DATABASE IF EXISTS dorering;

-- Create database
CREATE DATABASE dorering;

-- Schema connection
USE dorering;

-- Drop existing tables first
DROP TABLE IF EXISTS `fairytale_detail`;
DROP TABLE IF EXISTS `custom_service`;
DROP TABLE IF EXISTS `fairytale_video_info`;
DROP TABLE IF EXISTS `basic_voice`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `user_info`;
DROP TABLE IF EXISTS `fairytale_info`;

-- Create tables
CREATE TABLE `fairytale_info` (
                                  `fairytale_file_code` INTEGER NOT NULL AUTO_INCREMENT,
                                  `fairytale_code` VARCHAR(255),
                                  `fairytale_thumb` VARCHAR(255),
                                  `fairytale_story` VARCHAR(255),
                                  `summary` VARCHAR(255),
                                  `fairytale_title` VARCHAR(255),
                                  PRIMARY KEY (`fairytale_file_code`)
);

CREATE TABLE `fairytale_video_info` (
                                        `pro_video_code` INTEGER NOT NULL AUTO_INCREMENT,
                                        `video_path` VARCHAR(255),
                                        PRIMARY KEY (`pro_video_code`)
);

CREATE TABLE `custom_service` (
                                  `inquiry_no` INTEGER NOT NULL AUTO_INCREMENT,
                                  `user_code` INTEGER NOT NULL,
                                  `inquiry_title` VARCHAR(255),
                                  `inquiry_detail` VARCHAR(255),
                                  `inquiry_date` VARCHAR(255),
                                  `inquiry_result` VARCHAR(255),
                                  PRIMARY KEY (`inquiry_no`)
);

CREATE TABLE `basic_voice` (
                               `basic_voice_code` INTEGER NOT NULL AUTO_INCREMENT,
                               `category_code` INTEGER NOT NULL,
                               `Field` VARCHAR(255),
                               PRIMARY KEY (`basic_voice_code`)
);

CREATE TABLE `category` (
                            `basic_voice_code` INTEGER NOT NULL AUTO_INCREMENT,
                            `basic_voice_1` VARCHAR(255),
                            `basic_voice_2` VARCHAR(255),
                            `basic_voice_3` VARCHAR(255),
                            PRIMARY KEY (`basic_voice_code`)
);

CREATE TABLE `fairytale_detail` (
                                    `detail_code` INTEGER NOT NULL AUTO_INCREMENT,
                                    `video_code` INTEGER NOT NULL,
                                    `fairytale_code` INTEGER NOT NULL,
                                    `basic_voice` INTEGER NOT NULL,
                                    `user_code` INTEGER NOT NULL,
                                    PRIMARY KEY (`detail_code`)
);

CREATE TABLE `user_info` (
                             `user_code` INTEGER NOT NULL AUTO_INCREMENT,
                             `name` VARCHAR(255) NOT NULL,
                             `id` VARCHAR(255),
                             `pwd` VARCHAR(255),
                             `phone` VARCHAR(255),
                             `enroll_date` DATE,
                             `token` VARCHAR(255),
                             `withdrawal_status` VARCHAR(255),
                             `voice_id` VARCHAR(255),
                             `withdrawal_date` DATE,
                             PRIMARY KEY (`user_code`)
);

-- Add Foreign Key Constraints
ALTER TABLE `custom_service` ADD CONSTRAINT `FK_custom_service_user_info` FOREIGN KEY (`user_code`) REFERENCES `user_info` (`user_code`);
ALTER TABLE `Fairytale_detail` ADD CONSTRAINT `FK_Fairytale_detail_Fairytale_video_info` FOREIGN KEY (`video_code`) REFERENCES `Fairytale_video_info` (`pro_video_code`);
ALTER TABLE `Fairytale_detail` ADD CONSTRAINT `FK_Fairytale_detail_fairytale_info` FOREIGN KEY (`fairytale_code`) REFERENCES `fairytale_info` (`fairytale_file_code`);
ALTER TABLE `Fairytale_detail` ADD CONSTRAINT `FK_Fairytale_detail_basic_voice` FOREIGN KEY (`basic_voice`) REFERENCES `basic_voice` (`basic_voice_code`);
ALTER TABLE `Fairytale_detail` ADD CONSTRAINT `FK_Fairytale_detail_user_info` FOREIGN KEY (`user_code`) REFERENCES `user_info` (`user_code`);