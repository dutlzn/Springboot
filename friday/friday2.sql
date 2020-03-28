/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 47.98.209.48:3306
 Source Schema         : friday

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/03/2020 19:37:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parentId` int(0) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `css` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `href` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '用户管理', 'fa-users', '', 1, '', 1);
INSERT INTO `sys_permission` VALUES (2, 1, '用户查询', 'fa-user', '/api/getPage?pageName=user/user-list', 1, '', 2);
INSERT INTO `sys_permission` VALUES (3, 2, '查询', '', '', 2, 'sys:user:query', 100);
INSERT INTO `sys_permission` VALUES (4, 2, '新增', '', '', 2, 'sys:user:add', 100);
INSERT INTO `sys_permission` VALUES (5, 2, '删除', NULL, NULL, 2, 'sys:user:del', 100);
INSERT INTO `sys_permission` VALUES (6, 1, '修改密码', 'fa-pencil-square-o', '/api/getPage?pageName=user/user-change-password', 1, 'sys:user:password', 4);
INSERT INTO `sys_permission` VALUES (7, 0, '系统设置', 'fa-gears', '', 1, '', 5);
INSERT INTO `sys_permission` VALUES (8, 7, '菜单', 'fa-cog', '/api/getPage?pageName=permission/permission-list', 1, '', 6);
INSERT INTO `sys_permission` VALUES (9, 8, '查询', '', '', 2, 'sys:menu:query', 100);
INSERT INTO `sys_permission` VALUES (10, 8, '新增', '', '', 2, 'sys:menu:add', 100);
INSERT INTO `sys_permission` VALUES (11, 8, '删除', '', '', 2, 'sys:menu:del', 100);
INSERT INTO `sys_permission` VALUES (12, 7, '角色', 'fa-user-secret', '/api/getPage?pageName=role/role-list', 1, '', 7);
INSERT INTO `sys_permission` VALUES (13, 12, '查询', '', '', 2, 'sys:role:query', 100);
INSERT INTO `sys_permission` VALUES (14, 12, '新增', '', '', 2, 'sys:role:add', 100);
INSERT INTO `sys_permission` VALUES (15, 12, '删除', '', '', 2, 'sys:role:del', 100);
INSERT INTO `sys_permission` VALUES (16, 0, '文件管理', 'fa-folder-open', '/api/getPage?pageName=file/file-list', 1, '', 8);
INSERT INTO `sys_permission` VALUES (17, 16, '查询', '', '', 2, 'sys:file:query', 100);
INSERT INTO `sys_permission` VALUES (18, 16, '删除', '', '', 2, 'sys:file:del', 100);
INSERT INTO `sys_permission` VALUES (19, 0, '数据源监控', 'fa-eye', 'druid/index.html', 1, '', 9);
INSERT INTO `sys_permission` VALUES (20, 0, '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', 1, '', 10);
INSERT INTO `sys_permission` VALUES (21, 0, '代码生成', 'fa-wrench', '/api/getPage?pageName=generate/edit', 1, 'generate:edit', 11);
INSERT INTO `sys_permission` VALUES (22, 0, '日志查询', 'fa-reorder', '/api/getPage?pageName=log/log-list', 1, 'sys:log:query', 13);
INSERT INTO `sys_permission` VALUES (23, 8, '修改', NULL, NULL, 2, 'sys:menu:edit', 100);
INSERT INTO `sys_permission` VALUES (24, 12, '修改', NULL, NULL, 2, 'sys:role:edit', 100);
INSERT INTO `sys_permission` VALUES (25, 2, '修改', NULL, NULL, 2, 'sys:user:edit', 100);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员', '2017-05-01 13:25:39', '2019-06-04 02:25:13');
INSERT INTO `sys_role` VALUES (2, 'USER', '普通用户', '2017-08-01 21:47:31', '2019-05-30 09:08:24');
INSERT INTO `sys_role` VALUES (3, 'TEACHER', '', '2019-03-27 02:10:23', '2019-05-23 07:48:01');
INSERT INTO `sys_role` VALUES (4, 'test', 'test', '2019-04-29 02:16:48', '2019-05-22 09:51:26');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `roleId` int(0) NOT NULL,
  `permissionId` int(0) NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE,
  INDEX `fk_sysrolepermission_permissionId`(`permissionId`) USING BTREE,
  CONSTRAINT `fk_permission_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_sysrolepermission_permissionId` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (3, 1);
INSERT INTO `sys_role_permission` VALUES (4, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (3, 2);
INSERT INTO `sys_role_permission` VALUES (4, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (3, 3);
INSERT INTO `sys_role_permission` VALUES (4, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (3, 4);
INSERT INTO `sys_role_permission` VALUES (4, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (3, 5);
INSERT INTO `sys_role_permission` VALUES (4, 5);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (3, 6);
INSERT INTO `sys_role_permission` VALUES (4, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (3, 7);
INSERT INTO `sys_role_permission` VALUES (4, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (3, 8);
INSERT INTO `sys_role_permission` VALUES (4, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (3, 9);
INSERT INTO `sys_role_permission` VALUES (4, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (3, 10);
INSERT INTO `sys_role_permission` VALUES (4, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (3, 11);
INSERT INTO `sys_role_permission` VALUES (4, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (3, 12);
INSERT INTO `sys_role_permission` VALUES (4, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (3, 13);
INSERT INTO `sys_role_permission` VALUES (4, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (3, 14);
INSERT INTO `sys_role_permission` VALUES (4, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (3, 15);
INSERT INTO `sys_role_permission` VALUES (4, 15);
INSERT INTO `sys_role_permission` VALUES (3, 16);
INSERT INTO `sys_role_permission` VALUES (4, 16);
INSERT INTO `sys_role_permission` VALUES (3, 17);
INSERT INTO `sys_role_permission` VALUES (4, 17);
INSERT INTO `sys_role_permission` VALUES (3, 18);
INSERT INTO `sys_role_permission` VALUES (4, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (3, 19);
INSERT INTO `sys_role_permission` VALUES (4, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (2, 20);
INSERT INTO `sys_role_permission` VALUES (3, 20);
INSERT INTO `sys_role_permission` VALUES (4, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (2, 21);
INSERT INTO `sys_role_permission` VALUES (3, 21);
INSERT INTO `sys_role_permission` VALUES (4, 21);
INSERT INTO `sys_role_permission` VALUES (3, 22);
INSERT INTO `sys_role_permission` VALUES (4, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (3, 23);
INSERT INTO `sys_role_permission` VALUES (4, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (3, 24);
INSERT INTO `sys_role_permission` VALUES (4, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (2, 25);
INSERT INTO `sys_role_permission` VALUES (3, 25);
INSERT INTO `sys_role_permission` VALUES (4, 25);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `userId` int(0) NOT NULL,
  `roleId` int(0) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE,
  INDEX `fk_roleId`(`roleId`) USING BTREE,
  CONSTRAINT `fk_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1);
INSERT INTO `sys_role_user` VALUES (45, 1);
INSERT INTO `sys_role_user` VALUES (2, 2);
INSERT INTO `sys_role_user` VALUES (18, 2);
INSERT INTO `sys_role_user` VALUES (27, 2);
INSERT INTO `sys_role_user` VALUES (28, 2);
INSERT INTO `sys_role_user` VALUES (29, 2);
INSERT INTO `sys_role_user` VALUES (30, 2);
INSERT INTO `sys_role_user` VALUES (41, 2);
INSERT INTO `sys_role_user` VALUES (3, 3);
INSERT INTO `sys_role_user` VALUES (26, 3);
INSERT INTO `sys_role_user` VALUES (32, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `headImgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$lV7cclJplNARyq7z8jLokuZF57fj9C3l/ZNBI6Wb6Xnk5mUNcS3t6', '管理员', NULL, NULL, '158784879852', '12@qq.com', '2020-03-20', 0, 1, '2019-04-08 00:20:51', '2020-03-27 15:51:29');
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$1j4KRfJgkY2.4uAkP9HJTuiudZy2WAsuwpGDJYPSIjv/KCNM.0TDS', '用户', NULL, NULL, '1111111111', '11@qq.com', '2019-03-29', 1, 1, '2019-04-09 06:44:50', '2020-03-27 15:53:55');
INSERT INTO `sys_user` VALUES (3, 'alex', '$2a$10$W.d2kZwTWMSvg8.u7IH3l.bzDH0qqjokWTE3iM3t4rupKbEMNUMH2', '讲师', NULL, '', '13245698712', 'alex@qq.com', '2019-03-31', 1, 1, '2019-03-27 02:27:35', '2019-04-09 07:57:17');
INSERT INTO `sys_user` VALUES (18, 'user1', '$2a$10$AS3iDTAyx79SVtBMCKDmaOHGc1sDSRBVKCfs0A/rbthygv8ioU9q6', '111', NULL, NULL, '123455432123', '134@qq.com', '2019-05-11', 0, 1, '2019-05-14 04:44:22', '2020-03-27 15:53:24');
INSERT INTO `sys_user` VALUES (26, 'user2', '$2a$10$FlWEoEusZrlBPJqx0GqLW.I3p1klSHaVK6H8YnfyRLgceh5LXdTfm', 'user2', NULL, NULL, '09876567890', 'aa@QQ.com', '2019-05-11', 0, 1, '2019-05-15 02:22:21', '2020-03-27 15:53:29');
INSERT INTO `sys_user` VALUES (27, 'user3', '$2a$10$YF/NqZIGrVhItra/9M0N4ODA248v0fCKU.4YiErJjITyG.VSayMbK', 'user3', NULL, NULL, '44366758876586578', 'bb@qq.com', '2019-05-13', 1, 1, '2019-05-15 02:23:51', '2020-03-27 15:53:33');
INSERT INTO `sys_user` VALUES (28, 'user4', '$2a$10$Cj7abK.dLF.7U9xNYXbLlOCd6akIGpIqy1qACTb5vDjIUc0yuAb8e', 'user4', NULL, NULL, '2143323543456876', 'cc@qq.com', '2019-04-30', NULL, 1, '2019-05-15 02:24:22', '2019-05-15 02:24:22');
INSERT INTO `sys_user` VALUES (29, 'user5', '$2a$10$3VpKvHqMvmOqAY.YCDGKSeITijwoy6f3Hh6lrGCOVFCC3GrRC7A06', 'user5', NULL, NULL, '1221344234565', 'dd@qq.com', '2018-12-03', NULL, 1, '2019-05-15 02:24:49', '2019-05-15 02:24:49');
INSERT INTO `sys_user` VALUES (30, 'user6', '$2a$10$yxKSjwtHQpCtYl1Ou5PDievSM0mmU63y/S4VEvbGw2NtRkub5XrMG', 'user6', NULL, NULL, '123213215135453', 'ee@qq.coom', '2019-05-15', NULL, 1, '2019-05-15 02:25:16', '2019-05-21 03:08:26');
INSERT INTO `sys_user` VALUES (32, 'user7', '$2a$10$aa1TdGlGEJGEkYMumvxKRulH5L/on8QhKw5o2Rjj9PgoH3xjvz/8G', 'user7', NULL, NULL, '21345457980765', 'tt@qq.com', '2019-05-20', NULL, 1, '2019-05-15 06:16:32', '2019-05-21 03:08:37');
INSERT INTO `sys_user` VALUES (41, 'user67', '$2a$10$4ha6n.lZanC4lysck3yBx.VQ.T7m.bhaD10FRuGfZSk8Ygh3NxKkC', 'user67', NULL, NULL, '123456324568', 'asdsa@qq.com', '2019-05-14', NULL, 1, '2019-05-16 08:39:11', '2019-05-16 08:39:11');
INSERT INTO `sys_user` VALUES (43, 'alex-s', '$2a$10$RSjgPKFSTwfYwGeimLFo5exJmnsHhM1yW2LgjLVzxMoVXdiC0anKy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2019-05-29 06:07:53', '2019-05-29 06:07:53');
INSERT INTO `sys_user` VALUES (45, 'luzhenning', '$2a$10$0IIHe/ZpIqZcIfXzjFLPHO/l6xrhnTSXCk9ZrIHKhAp1s.iiAED4i', 'lzn', NULL, NULL, '15011111111', '1@qq.com', '2020-03-15', 1, 1, '2020-03-28 13:28:54', '2020-03-28 13:28:54');

SET FOREIGN_KEY_CHECKS = 1;
