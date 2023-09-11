DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user`
(
    `ID`          bigint NOT NULL,
    `USER_TYPE`   varchar(20) NULL DEFAULT NULL,
    `ACCOUNT`     varchar(30) NULL DEFAULT NULL,
    `MOBILE`      varchar(11) NULL DEFAULT NULL,
    `EMAIL`       varchar(50) NULL DEFAULT NULL,
    `PASSWORD`    varchar(100) NULL DEFAULT NULL,
    `REAL_NAME`   varchar(20) NULL DEFAULT NULL,
    `NICK_NAME`   varchar(30) NULL DEFAULT NULL,
    `GENDER`      varchar(4) NULL DEFAULT NULL,
    `CREATE_TIME` datetime NULL DEFAULT NULL,
    `UPDATE_TIME` datetime NULL DEFAULT NULL,
    `ENABLE`      tinyint(1) NULL DEFAULT NULL,
    `VERSION`     bigint NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE,
    UNIQUE INDEX `UN_INDEX_ACCOUNT`(`ACCOUNT`) USING BTREE,
    UNIQUE INDEX `UN_INDEX_MOBILE`(`MOBILE`) USING BTREE,
    UNIQUE INDEX `UN_INDEX_EMAIL`(`EMAIL`) USING BTREE
)COMMENT = 'Oauth2用户表';

-- ----------------------------
-- Records of oauth_user
-- ----------------------------
INSERT INTO `oauth_user`
VALUES (10000, 'SUPER_ADMIN', 'Admin', '17719540702', '281344730@qq.com',
        '{MD5}{WuncQOEYAxwNHMgolNnqZEmRswrgDntwNVc4gEHsyLQ=}5872882b5d23f7524b1a9bed4c75b244', 'NiuChang',
        '淡淡丶奶油味', '男', '2023-09-08 17:15:16', '2023-09-08 17:15:20', 1, 1);

