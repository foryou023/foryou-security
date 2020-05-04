/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-04-17 18:47:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(64) NOT NULL,
  `url` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('user', '419d1b+yJNAGii2Lqcx8Ww==', '+KBSJh9L92J/EC4CiptI1A==', '2020-03-31 01:17:21');
INSERT INTO `persistent_logins` VALUES ('user', 'FycbOkZIbDDmVA8ufv+2Iw==', 'OScF4xZQ48sr1DZI2M2Xbw==', '2020-03-31 21:03:47');
INSERT INTO `persistent_logins` VALUES ('user', 'jf1g0CV8grYAEdA1R0Is0A==', '62qdsUNio4AKcSgWYlhfYw==', '2020-03-29 23:03:51');
INSERT INTO `persistent_logins` VALUES ('user', 'JO2quRPDoUrQpvwhPu7g+g==', 'vkyGCqBBCQgF1/nioJSjDQ==', '2020-03-29 22:40:42');
INSERT INTO `persistent_logins` VALUES ('user', 'mSH5I6TgPWVPF8r3z5O0gA==', 'H3ck5LAqqJs82xJkbjmQ8Q==', '2020-03-31 21:04:50');
INSERT INTO `persistent_logins` VALUES ('user', 'WyI617gNfFzMQWk9hf9ciQ==', 'O4pwSYqSvnvi9AvWNOtBAg==', '2020-03-30 17:11:08');
INSERT INTO `persistent_logins` VALUES ('user', 'Y601RyrWouRZVgVN+pXGmA==', 's+AHopirMynm+FfBHPt4IQ==', '2020-03-29 22:59:54');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `permissionname` varchar(64) NOT NULL,
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(64) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ROLE_USER', '1', '普通用户');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`rid`,`pid`),
  KEY `pid` (`pid`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `permission` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `password` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('user', 'user', '1');
INSERT INTO `users` VALUES ('12345', 'admin', '2');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
