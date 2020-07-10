/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : questionnaire

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-10 10:39:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `options_id` bigint(20) DEFAULT NULL COMMENT '选项id',
  `text` varchar(255) DEFAULT NULL COMMENT '答案',
  `username` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `complete_time` varchar(128) DEFAULT NULL COMMENT '答题时长',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='答案表';

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `text` varchar(255) DEFAULT NULL COMMENT '问题选项',
  `display` varchar(255) DEFAULT '' COMMENT '后续问题对应的问题id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `qid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question_name` varchar(255) DEFAULT NULL COMMENT '问题题目',
  `type` varchar(128) DEFAULT NULL COMMENT '问题类型',
  `description` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `subTitles` varchar(255) DEFAULT NULL COMMENT '选项分类（多个选项分类以，隔开）',
  `required` varchar(10) DEFAULT NULL COMMENT '是否为必填',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='问题表';

-- ----------------------------
-- Table structure for question_options
-- ----------------------------
DROP TABLE IF EXISTS `question_options`;
CREATE TABLE `question_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `options_id` bigint(20) DEFAULT NULL COMMENT '选项id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for survey
-- ----------------------------
DROP TABLE IF EXISTS `survey`;
CREATE TABLE `survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '名称',
  `prefix` varchar(255) DEFAULT NULL COMMENT '说明',
  `pages` varchar(255) DEFAULT NULL COMMENT '问卷数据',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';

-- ----------------------------
-- Table structure for survey_question
-- ----------------------------
DROP TABLE IF EXISTS `survey_question`;
CREATE TABLE `survey_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `survey_id` bigint(20) DEFAULT NULL COMMENT '问卷id',
  `question_id` bigint(11) DEFAULT NULL COMMENT '问题id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除、1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='问卷题目表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `head_img` varchar(255) DEFAULT NULL,
  `wechat_id` varchar(128) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` int(11) DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(2) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
