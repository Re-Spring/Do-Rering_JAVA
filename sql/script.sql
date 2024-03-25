create table exp_voice_id
(
    no       int auto_increment primary key,
    voice_id varchar(20) not null
);

create table user_info
(
    user_code         int auto_increment primary key,
    name              varchar(10) null,
    id                varchar(20) null,
    pwd               varchar(255) null,
    phone             varchar(20) null,
    role              varchar(20) null,
    withdrawal_status varchar(20) null,
    voice_id          varchar(20) null,
    withdrawal_date   date null,
    enroll_date       date null
);

create table customer_service
(
    board_code   int auto_increment primary key,
    title        varchar(45) not null,
    posted_date  datetime not null,
    content      varchar(255) not null,
    user_code    int not null,
    comment_date datetime null,
    comment      varchar(255) null,
    constraint customer_service_ibfk_1 foreign key (user_code) references user_info (user_code)
);

create table fairytale_info
(
    fairytale_code    int auto_increment primary key,
    user_code         int not null,
    fairytale_summary varchar(5000) null,
    fairytale_title   varchar(255) null,
    fairytale_genre   varchar(255) null,
    fairytale_thumb   varchar(255) null,
    constraint fairytale_info_ibfk_1 foreign key (user_code) references user_info (user_code)
);

create index user_code on fairytale_info (user_code);

create table fairytale_video_info
(
    video_file_code int auto_increment primary key,
    fairytale_code  int not null,
    video_path      varchar(255) null,
    constraint fairytale_video_info_ibfk_1 foreign key (fairytale_code) references fairytale_info (fairytale_code)
);

create index fairytale_code on fairytale_video_info (fairytale_code);

create definer = dbmasteruser@`%` event update_rejoin_status on schedule
    every 1 DAY
        starts '2024-03-21 00:00:00'
    enable
    do
DELETE FROM user_info
WHERE withdrawal_status = 'Y' AND withdrawal_date <= CURDATE() - INTERVAL 1 MONTH;

create definer = dbmasteruser@`%` event withdraw_user_event on schedule
    every 1 DAY
        starts '2024-03-19 00:00:00'
    enable
    do
BEGIN
INSERT INTO exp_voice_id (voice_id)
SELECT voice_id FROM user_info
WHERE withdrawal_status = 'Y'
  AND withdrawal_date <= CURDATE() - INTERVAL 1 MONTH
  AND voice_id IS NOT NULL;

DELETE FROM user_info
WHERE withdrawal_status = 'Y' AND withdrawal_date <= CURDATE() - INTERVAL 1 MONTH;
END;
