/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-05-30 09:40:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `blog_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `blog_title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `blog_appreciation` bit(1) DEFAULT b'1' COMMENT '是否开启点赞',
  `blog_commentabled` bit(1) DEFAULT b'1' COMMENT '是否开启评论',
  `blog_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '文章内容',
  `blog_cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `blog_descri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章描述',
  `blog_firstpic` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '首图地址',
  `blog_flag` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章来源',
  `blog_published` bit(1) DEFAULT b'0' COMMENT '是否已发布',
  `blog_recommend` bit(1) DEFAULT b'0' COMMENT '是否推荐',
  `blog_statement` bit(1) DEFAULT NULL COMMENT '转载状态',
  `blog_updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `blog_views` int DEFAULT NULL COMMENT '观看次数',
  `blog_ref_typeid` int DEFAULT NULL,
  `blog_ref_userid` tinyint DEFAULT NULL,
  PRIMARY KEY (`blog_id`),
  KEY `blog_reftype` (`blog_ref_typeid`),
  KEY `blog_refuser` (`blog_ref_userid`),
  CONSTRAINT `blog_reftype` FOREIGN KEY (`blog_ref_typeid`) REFERENCES `t_type` (`type_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `blog_refuser` FOREIGN KEY (`blog_ref_userid`) REFERENCES `t_user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('2', 'Nginx部署过程', '', '', '#### 1. 查看开放端口\r\n\r\n```c\r\nfirewall -cmd --list-ports			//防火墙开启状态下\r\n```\r\n\r\n\r\n\r\n#### 2. 查看端口是被被调用\r\n\r\n```c\r\nnetstat -nap | grep XXX 			//XXX为端口号\r\n```\r\n\r\n\r\n\r\n#### 3. 修改项目端口号为服务器中未被调用的某个接口\r\n\r\n#### 4. 打包项目并且上传到服务器\r\n\r\n#### 5. 启动项目\r\n\r\n```c\r\nnohup java -jar XXX.jar > XXX.txt &		//XXX为项目名\r\n```\r\n\r\n#### 6. 修改nginx配置文件\r\n\r\n```properties\r\nserver{\r\n		listen 80;\r\n		server_name kanban.dtsum.com;\r\n		location /{\r\n			proxy_set_header Host  $host:80;\r\n			proxy_pass http://localhost:8803;\r\n			proxy_set_header X-Forwarded-For  $remote_addr;\r\n			client_max_body_size 100m;\r\n		}\r\n	}\r\n```\r\n\r\n#### 7. 重启nginx\r\n\r\nCD切换到nginx目录下的sbin文件夹\r\n\r\n```c\r\nnginx -s reload\r\n```\r\n\r\n', '2022-05-27 08:53:51', 'Ngrix配置SpringBoot项目基本流程，前后端分离依然适用', 'https://picsum.photos/id/1/800/450', '转载', '', '', '', '2022-05-27 10:02:30', '0', '1', '1');

-- ----------------------------
-- Table structure for t_blogtag
-- ----------------------------
DROP TABLE IF EXISTS `t_blogtag`;
CREATE TABLE `t_blogtag` (
  `ref_blog_id` bigint NOT NULL COMMENT '对应关系-博客ID',
  `ref_tag_id` int NOT NULL COMMENT '对应关系-标签ID',
  KEY `re_blog` (`ref_blog_id`) USING BTREE,
  KEY `ref_tag` (`ref_tag_id`),
  CONSTRAINT `ref_blog_id` FOREIGN KEY (`ref_blog_id`) REFERENCES `t_blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ref_tag_id` FOREIGN KEY (`ref_tag_id`) REFERENCES `t_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_blogtag
-- ----------------------------
INSERT INTO `t_blogtag` VALUES ('2', '1');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论用户昵称',
  `comment_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论用户邮箱',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '评论内容',
  `comment_avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论用户头像',
  `comment_cretime` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_blogid` bigint NOT NULL COMMENT '评论对应blog',
  `comment_parentid` bigint DEFAULT NULL COMMENT '评论父类',
  PRIMARY KEY (`comment_id`),
  KEY `comment_blog` (`comment_blogid`),
  KEY `comment_parent` (`comment_parentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', 'SpringBoot');
INSERT INTO `t_tag` VALUES ('2', 'MySQL');
INSERT INTO `t_tag` VALUES ('3', 'Redis');
INSERT INTO `t_tag` VALUES ('4', 'MyBatis');
INSERT INTO `t_tag` VALUES ('5', 'Docker');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `type_id` int NOT NULL AUTO_INCREMENT COMMENT '类型ID',
  `type_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '技术分享');
INSERT INTO `t_type` VALUES ('2', '时事新闻');
INSERT INTO `t_type` VALUES ('3', '人生感悟');
INSERT INTO `t_type` VALUES ('4', '生活经历');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` tinyint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `user_avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_cretime` datetime DEFAULT NULL COMMENT '用户账户生成日期',
  `user_email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户邮箱',
  `user_nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `user_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户密码',
  `user_type` bit(1) DEFAULT NULL COMMENT '用户级别',
  `user_updtime` datetime DEFAULT NULL COMMENT '用户账户更新日期',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'jiangwei', 'https://picsum.photos/id/1002/100/100', '2022-05-25 10:20:57', '1398972584@qq.com', 'RockJ', 'ca822e69a79bf415397e0c20a3e6ae14', '', '2022-05-25 10:21:46');
