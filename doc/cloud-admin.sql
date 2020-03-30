/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Schema         : cloud-admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/03/2020 08:24:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails`  (
  `appId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resourceIds` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appSecret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grantTypes` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `redirectUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additionalInformation` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoApproveScopes` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spring security client details' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authentication` blob NULL,
  `refresh_token` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spring security  保存token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`  (
  `userId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expiresAt` timestamp(0) NULL DEFAULT NULL,
  `lastModifiedAt` timestamp(0) NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spring security  ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Security ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', '', '$2a$10$p/QW.3yaFnnlashm1pYtl.2k6ueV/ukvG2E/hfDC59JAT1MZepsfS', 'app', 'password,client_credentials,authorization_code,refresh_token', '', '', 684000, 684000, '{\"country\":\"CN\",\"country_code\":\"086\"}', 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Security ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authentication` blob NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Security ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication` blob NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Security ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pdman_db_version
-- ----------------------------
DROP TABLE IF EXISTS `pdman_db_version`;
CREATE TABLE `pdman_db_version`  (
  `DB_VERSION` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VERSION_DESC` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_TIME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdman_db_version
-- ----------------------------
INSERT INTO `pdman_db_version` VALUES ('v0.0.0', '默认版本，新增的版本不能低于此版本', '2020-02-15 13:40:48');
INSERT INTO `pdman_db_version` VALUES ('v1.0.0', '初始化', '2020-02-15 13:41:30');
INSERT INTO `pdman_db_version` VALUES ('v1.0.2', '系统权限角色以及菜单', '2020-02-19 13:13:35');
INSERT INTO `pdman_db_version` VALUES ('v1.0.4', '角色表重命名', '2020-02-19 13:56:04');
INSERT INTO `pdman_db_version` VALUES ('v1.0.5', '重建用户表', '2020-02-20 11:25:52');
INSERT INTO `pdman_db_version` VALUES ('v1.0.6', '重新创建路由表结构', '2020-02-21 13:33:53');
INSERT INTO `pdman_db_version` VALUES ('v1.0.7', '新增排序', '2020-02-25 12:36:24');
INSERT INTO `pdman_db_version` VALUES ('v1.0.8', '新增系统组织部门以及岗位', '2020-03-08 14:13:34');
INSERT INTO `pdman_db_version` VALUES ('v1.0.9', '岗位表重命名', '2020-03-08 14:14:20');
INSERT INTO `pdman_db_version` VALUES ('v1.0.10', '组织dept新增父id', '2020-03-14 12:33:56');
INSERT INTO `pdman_db_version` VALUES ('v1.0.11', '新增组织编码', '2020-03-14 14:03:14');
INSERT INTO `pdman_db_version` VALUES ('v1.0.12', '岗位与组织绑定', '2020-03-14 17:13:50');
INSERT INTO `pdman_db_version` VALUES ('v1.0.13', '岗位新增描述', '2020-03-15 10:09:34');
INSERT INTO `pdman_db_version` VALUES ('v1.0.14', '新增dept和post绑定表', '2020-03-15 11:34:46');
INSERT INTO `pdman_db_version` VALUES ('v1.0.15', '新增组织角色绑定', '2020-03-16 10:45:49');
INSERT INTO `pdman_db_version` VALUES ('v1.0.16', '新增用户组织', '2020-03-16 16:15:22');
INSERT INTO `pdman_db_version` VALUES ('v1.0.17', '更新组织', '2020-03-16 16:16:25');
INSERT INTO `pdman_db_version` VALUES ('v1.0.18', '用户岗位', '2020-03-16 17:16:13');

-- ----------------------------
-- Table structure for t_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_dept`;
CREATE TABLE `t_system_dept`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `order` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `ancestors` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `leader` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统部门 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_dept
-- ----------------------------
INSERT INTO `t_system_dept` VALUES (1, '2020-03-14 15:05:53', NULL, NULL, 1, 0, 1, 1238723005621633026, -1, 'cs001', '测试', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1, '2020-03-14 15:27:02', NULL, NULL, 2, 0, 1, 1238728324540186626, 1238723005621633026, 'cs002', '测试02', 0, NULL, '测试', NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1, '2020-03-14 16:16:07', NULL, NULL, 1, 0, 1, 1238740680074784770, 1238723005621633026, 'cs003', '测试03', 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1, '2020-03-14 16:16:38', NULL, NULL, 1, 0, 1, 1238740807799730178, 1238723005621633026, 'cs004', '测试04', 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1, '2020-03-14 16:16:56', NULL, NULL, 1, 1, 1, 1238740885025255426, 1238728324540186626, 'cs00201', '测试0201', 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1, '2020-03-16 18:40:12', NULL, NULL, 1, 0, 1, 1239501714045726722, -1, 'c00001', '强强科技', 0, NULL, '猪大强', NULL, NULL);
INSERT INTO `t_system_dept` VALUES (1239759022420127746, '2020-03-18 19:04:23', NULL, NULL, 1, 1, 1, 1240232576735379458, 1239501714045726722, 'c0101', 'ceshi', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `enname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文菜单名称',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父类菜单',
  `has_child` int(2) NULL DEFAULT NULL COMMENT '是否有下级',
  `is_root` int(2) NULL DEFAULT NULL COMMENT '是否根节点',
  `level` int(11) NULL DEFAULT NULL COMMENT '菜单级别',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1241216053769285634 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
INSERT INTO `t_system_menu` VALUES (1, '2020-02-26 13:19:58', 1239759022420127746, '2020-03-19 14:54:07', NULL, 0, 1, 1232535754990641154, '系统设置', 'system', '/system', 'fa fa-cog', -1, 1, 1, 1, NULL, 1);
INSERT INTO `t_system_menu` VALUES (1, '2020-02-26 13:22:41', 1239759022420127746, '2020-03-19 15:35:42', NULL, 0, 1, 1232536438213402625, '路由管理', 'routerManager', '/router', 'fa fa-share-alt', 1232535754990641154, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-02-26 13:23:26', 1239759022420127746, '2020-03-18 18:50:55', NULL, 0, 1, 1232536626407628802, '菜单管理', 'menu', '/menu', 'el-icon-menu', 1232535754990641154, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-02-26 13:24:41', 1239759022420127746, '2020-03-21 11:56:30', NULL, 0, 1, 1232536940116402178, '主页', 'index', '/index', 'fa fa-home', -1, 1, 1, 1, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 10:17:29', 1239759022420127746, '2020-03-19 15:32:45', 5, 0, 1, 1239375199958097922, '权限管理', 'PermissionManager', '/permission', 'fa fa-key', 1232535754990641154, 1, 1, 1, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 10:21:11', 1239759022420127746, '2020-03-19 15:24:22', 4, 0, 1, 1239376133786329090, '用户中心', 'UserCenter', '/userCenter', 'fa fa-users', -1, 1, 1, 1, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 10:22:30', 1239759022420127746, '2020-03-19 15:29:18', 4, 0, 1, 1239376463815139330, '组织管理', 'DeptManager', '/dept', 'fa fa-sitemap', 1239376133786329090, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 10:23:02', 1239759022420127746, '2020-03-19 15:26:18', 3, 0, 1, 1239376600469757953, '岗位管理', 'postManager', '/post', 'el-icon-postcard', 1239376133786329090, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 10:23:47', 1239759022420127746, '2020-03-19 15:25:59', 5, 0, 1, 1239376788206804994, '角色管理', 'RoleManager', '/role', 'fa fa-user-secret', 1239376133786329090, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1, '2020-03-16 13:03:13', 1239759022420127746, '2020-03-19 15:25:18', 4, 0, 1, 1239416908121268226, '用户管理', 'UserManager', '/user', 'fa fa-user', 1239376133786329090, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1239759022420127746, '2020-03-18 18:46:54', 1239759022420127746, '2020-03-18 18:54:16', 2, 1, 1, 1240228175035691010, '测试权限', 'testPermission', '/testPermission', NULL, 1232536940116402178, 1, 0, 2, NULL, 0);
INSERT INTO `t_system_menu` VALUES (1239759022420127746, '2020-03-21 12:12:22', 1239759022420127746, '2020-03-21 12:26:00', 1, 1, 1, 1241216053769285633, 'test', NULL, '/test', NULL, 1232536940116402178, 1, 0, 2, NULL, 0);

-- ----------------------------
-- Table structure for t_system_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限英文名称',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权路径',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1241224795021844483 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_permission
-- ----------------------------
INSERT INTO `t_system_permission` VALUES (1, '2020-02-28 17:05:41', 1, '2020-03-16 09:25:36', NULL, 0, 1, 1233317333434363905, NULL, '路由新增', 'routerAdd', 'router:add', '新增');
INSERT INTO `t_system_permission` VALUES (1, '2020-02-29 11:42:44', 1, '2020-03-16 09:25:56', NULL, 0, 1, 1233598448384589825, NULL, '路由查询', 'routerQuery', 'router:query', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-02-29 11:45:40', 1, '2020-03-16 09:26:04', NULL, 0, 1, 1233599186682757122, NULL, '路由修改', 'routerUpdate', 'router:update', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-02-29 12:54:29', NULL, NULL, NULL, 1, 1, 1233616504750043137, NULL, '测试', NULL, 'delete', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-17 18:25:10', NULL, NULL, NULL, 0, 1, 1239860320746258433, NULL, '用户修改', 'userUpdate', 'user:update', '用户修改');
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:19:19', NULL, NULL, NULL, 0, 1, 1240145736623910914, NULL, '新增菜单', 'menuAdd', 'menu:save', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:19:46', NULL, NULL, NULL, 0, 1, 1240145849547157505, NULL, '菜单查询', 'menQuery', 'menu:query', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:20:09', NULL, NULL, NULL, 0, 1, 1240145947077308418, NULL, '菜单修改', 'menuUpdate', 'menu:update', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:20:36', NULL, NULL, NULL, 0, 1, 1240146061539864578, NULL, '菜单删除', 'menuRemove', 'menu:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:21:03', NULL, NULL, NULL, 0, 1, 1240146171363520514, NULL, '权限新增', 'permissionAdd', 'permission:save', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:21:25', NULL, NULL, NULL, 0, 1, 1240146266293202945, NULL, '权限修改', 'permissionUpdate', 'permission:update', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:21:40', NULL, NULL, NULL, 0, 1, 1240146328645726210, NULL, '权限查询', 'permissionQuery', 'permission:query', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:22:01', NULL, NULL, NULL, 0, 1, 1240146416986157057, NULL, '权限删除', 'permissionRemove', 'permission:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:22:36', 1239759022420127746, '2020-03-18 17:48:02', NULL, 0, 1, 1240146564894093313, NULL, '组织新增', 'deptSave', 'dept:save', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:23:00', NULL, NULL, NULL, 0, 1, 1240146663686729730, NULL, '岗位查询', 'postQuery', 'post:query', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:23:27', NULL, NULL, NULL, 0, 1, 1240146777977319425, NULL, '角色查询', 'roleQuery', 'role:query', NULL);
INSERT INTO `t_system_permission` VALUES (1, '2020-03-18 13:23:53', NULL, NULL, NULL, 0, 1, 1240146885477330945, NULL, '用户查询', 'userQuery', 'user:query', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:39:29', 1239759022420127746, '2020-03-18 17:44:52', NULL, 1, 1, 1240211211148034049, NULL, '测试', 'test', 'test', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:45:37', NULL, NULL, 1, 0, 1, 1240212755255889921, NULL, '路由删除', 'routerRemove', 'router:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:46:44', NULL, NULL, 1, 0, 1, 1240213036249092097, NULL, '组织查询', 'deptQuery', 'dept:query', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:47:02', NULL, NULL, 1, 0, 1, 1240213109343227906, NULL, '组织修改', 'deptUpdate', 'dept:update', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:47:33', NULL, NULL, 1, 0, 1, 1240213241795153921, NULL, '组织删除', 'deptRemove', 'dept:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:48:37', NULL, NULL, 1, 0, 1, 1240213506774503426, NULL, '岗位新增', 'postSave', 'post:save', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:48:56', NULL, NULL, 1, 0, 1, 1240213586537582593, NULL, '岗位更新', 'postUpdate', 'post:update', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:49:15', NULL, NULL, 1, 0, 1, 1240213666728480769, NULL, '岗位删除', 'postRemove', 'post:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:49:42', NULL, NULL, 1, 0, 1, 1240213781199425537, NULL, '角色新增', 'roleSave', 'role:save', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:50:01', NULL, NULL, 1, 0, 1, 1240213860752789506, NULL, '角色更新', 'roleUpdate', 'role:update', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:50:23', NULL, NULL, 1, 0, 1, 1240213952691933185, NULL, '角色删除', 'roleRemove', 'role:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:50:47', NULL, NULL, 1, 0, 1, 1240214051463598081, NULL, '用户新增', 'userSave', 'user:save', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:51:01', NULL, NULL, 1, 0, 1, 1240214112910151681, NULL, '用户删除', 'userRemove', 'user:remove', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 17:59:02', NULL, NULL, 1, 0, 1, 1240216130269712385, NULL, '角色权限绑定', 'bindingRolePermission', 'binding:role:permission', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-18 18:14:01', NULL, NULL, 1, 0, 1, 1240219902375071746, NULL, '角色数据范围', 'bindingRoleDept', 'binding:role:dept', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-21 12:36:15', NULL, NULL, 1, 1, 1, 1241222062617337858, NULL, '测试', 'test', 'test:111', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-21 12:38:06', 1239759022420127746, '2020-03-21 12:38:13', 1, 1, 1, 1241222528373825537, NULL, '测试', 'test', 'test:111', NULL);
INSERT INTO `t_system_permission` VALUES (1239759022420127746, '2020-03-21 12:47:07', 1239759022420127746, '2020-03-21 12:47:16', 1, 1, 1, 1241224795021844482, NULL, '测试', 'test', '1231', '1231');

-- ----------------------------
-- Table structure for t_system_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission_menu`;
CREATE TABLE `t_system_permission_menu`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1241224796145852419 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_permission_menu
-- ----------------------------
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-02-28 17:05:41', NULL, NULL, NULL, 0, 1, 1233317335695040513, 1233317333434363905, 1232536438213402625, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-02-29 11:42:45', NULL, NULL, NULL, 0, 1, 1233598451819663361, 1233598448384589825, 1232536438213402625, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-02-29 11:45:40', NULL, NULL, NULL, 0, 1, 1233599187823546369, 1233599186682757122, 1232536438213402625, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-02-29 12:54:30', NULL, NULL, NULL, 1, 1, 1233616507874758657, 1233616504750043137, 1232536940116402178, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-17 18:25:12', NULL, NULL, NULL, 0, 1, 1239860326257586178, 1239860320746258433, 1239416908121268226, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:19:20', NULL, NULL, NULL, 0, 1, 1240145741472530434, 1240145736623910914, 1232536626407628802, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:19:46', NULL, NULL, NULL, 0, 1, 1240145850520240130, 1240145849547157505, 1232536626407628802, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:20:10', NULL, NULL, NULL, 0, 1, 1240145948192997377, 1240145947077308418, 1232536626407628802, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:20:37', NULL, NULL, NULL, 0, 1, 1240146062533918722, 1240146061539864578, 1232536626407628802, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:21:03', NULL, NULL, NULL, 0, 1, 1240146172475015169, 1240146171363520514, 1239375199958097922, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:21:26', NULL, NULL, NULL, 0, 1, 1240146267371143170, 1240146266293202945, 1239375199958097922, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:21:40', NULL, NULL, NULL, 0, 1, 1240146329719472130, 1240146328645726210, 1239375199958097922, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:22:01', NULL, NULL, NULL, 0, 1, 1240146417925685250, 1240146416986157057, 1239375199958097922, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:22:37', NULL, NULL, NULL, 0, 1, 1240146565871370242, 1240146564894093313, 1239376463815139330, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:23:00', NULL, NULL, NULL, 0, 1, 1240146664634646530, 1240146663686729730, 1239376600469757953, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:23:28', NULL, NULL, NULL, 0, 1, 1240146778912653313, 1240146777977319425, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1, '2020-03-18 13:23:53', NULL, NULL, NULL, 0, 1, 1240146886483968001, 1240146885477330945, 1239416908121268226, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:39:30', NULL, NULL, NULL, 1, 1, 1240211215774257153, 1240211211148034049, 1232536438213402625, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:45:38', NULL, NULL, NULL, 0, 1, 1240212756472164353, 1240212755255889921, 1232536438213402625, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:46:45', NULL, NULL, NULL, 0, 1, 1240213037305982977, 1240213036249092097, 1239376463815139330, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:47:02', NULL, NULL, NULL, 0, 1, 1240213110303649793, 1240213109343227906, 1239376463815139330, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:47:34', NULL, NULL, NULL, 0, 1, 1240213242696855554, 1240213241795153921, 1239376463815139330, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:48:37', NULL, NULL, NULL, 0, 1, 1240213507705565185, 1240213506774503426, 1239376600469757953, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:48:56', NULL, NULL, NULL, 0, 1, 1240213587665776641, 1240213586537582593, 1239376600469757953, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:49:15', NULL, NULL, NULL, 0, 1, 1240213667856674817, 1240213666728480769, 1239376600469757953, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:49:42', NULL, NULL, NULL, 0, 1, 1240213782306648065, 1240213781199425537, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:50:01', NULL, NULL, NULL, 0, 1, 1240213861792903169, 1240213860752789506, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:50:23', NULL, NULL, NULL, 0, 1, 1240213953652355073, 1240213952691933185, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:50:47', NULL, NULL, NULL, 0, 1, 1240214052495323137, 1240214051463598081, 1239416908121268226, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:51:01', NULL, NULL, NULL, 0, 1, 1240214113878962177, 1240214112910151681, 1239416908121268226, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 17:59:02', NULL, NULL, NULL, 0, 1, 1240216131334991873, 1240216130269712385, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-18 18:14:02', NULL, NULL, NULL, 0, 1, 1240219903708786690, 1240219902375071746, 1239376788206804994, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-21 12:36:17', NULL, NULL, NULL, 1, 1, 1241222068275388417, 1241222062617337858, 1232536940116402178, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-21 12:38:06', NULL, NULL, NULL, 1, 1, 1241222529397170177, 1241222528373825537, 1232536940116402178, NULL);
INSERT INTO `t_system_permission_menu` VALUES (1239759022420127746, '2020-03-21 12:47:07', NULL, NULL, NULL, 1, 1, 1241224796145852418, 1241224795021844482, 1232536940116402178, NULL);

-- ----------------------------
-- Table structure for t_system_post
-- ----------------------------
DROP TABLE IF EXISTS `t_system_post`;
CREATE TABLE `t_system_post`  (
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NULL DEFAULT NULL COMMENT 'id',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位编码',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `describe` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_post
-- ----------------------------
INSERT INTO `t_system_post` VALUES (1, '2020-03-15 10:52:07', 1, '2020-03-15 11:13:00', 2, 1, 1, 1239021528246300673, 'cs001', '测试01', NULL, NULL);
INSERT INTO `t_system_post` VALUES (1, '2020-03-15 11:13:55', 1, '2020-03-15 13:34:48', 7, 0, 1, 1239027014035292161, 'cs001', '测试01', NULL, '测试');
INSERT INTO `t_system_post` VALUES (1, '2020-03-16 18:40:38', NULL, NULL, 1, 0, 1, 1239501824276217857, 'c00001', '董事长', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_post_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_post_dept`;
CREATE TABLE `t_system_post_dept`  (
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '组织',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统组织岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_post_dept
-- ----------------------------
INSERT INTO `t_system_post_dept` VALUES (1, '2020-03-15 13:11:27', NULL, NULL, 1, 1, 1, 1239056592535502850, 1238740807799730178, 1239027014035292161);
INSERT INTO `t_system_post_dept` VALUES (1, '2020-03-15 13:47:15', NULL, NULL, 1, 1, 1, 1239065604366716930, 1238740885025255426, 1239027014035292161);
INSERT INTO `t_system_post_dept` VALUES (1, '2020-03-15 13:47:15', NULL, NULL, 1, 1, 1, 1239065604379299841, 1238728324540186626, 1239027014035292161);
INSERT INTO `t_system_post_dept` VALUES (1, '2020-03-15 13:48:54', NULL, NULL, 1, 0, 1, 1239066019229519874, 1238740807799730178, 1239027014035292161);
INSERT INTO `t_system_post_dept` VALUES (1, '2020-03-16 18:40:45', NULL, NULL, 1, 0, 1, 1239501854080946178, 1239501714045726722, 1239501824276217857);

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色英文名称',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1240223061390589954 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES (1, '2020-02-20 12:05:18', 1, '2020-03-16 10:40:44', NULL, 0, 1, 1230342636581093377, NULL, '系统管理员', 'ADMIN', '测试系统管理员');
INSERT INTO `t_system_role` VALUES (1, '2020-03-01 14:37:42', 1, '2020-03-01 15:04:14', NULL, 1, 1, 1234004866719698945, NULL, '测试', 'test', '---');
INSERT INTO `t_system_role` VALUES (1, '2020-03-17 10:21:16', NULL, NULL, 1, 1, 1, 1239738541524586497, NULL, '管理员', 'admin', '管理员');
INSERT INTO `t_system_role` VALUES (1239759022420127746, '2020-03-18 18:26:35', 1239759022420127746, '2020-03-18 18:28:03', 2, 0, 1, 1240223061390589953, NULL, '测试角色', 'TEST', '测试');

-- ----------------------------
-- Table structure for t_system_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_dept`;
CREATE TABLE `t_system_role_dept`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '组织id'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色组织 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_dept
-- ----------------------------
INSERT INTO `t_system_role_dept` VALUES (1, '2020-03-16 11:56:45', NULL, NULL, 1, 1, 1, 1239400180033236993, 1230342636581093377, 1238740885025255426);
INSERT INTO `t_system_role_dept` VALUES (1, '2020-03-16 11:56:45', NULL, NULL, 1, 1, 1, 1239400180050014209, 1230342636581093377, 1238728324540186626);
INSERT INTO `t_system_role_dept` VALUES (1, '2020-03-16 12:03:19', NULL, NULL, 1, 1, 1, 1239401835273347074, 1230342636581093377, 1238740680074784770);
INSERT INTO `t_system_role_dept` VALUES (1, '2020-03-16 12:03:19', NULL, NULL, 1, 1, 1, 1239401835294318593, 1230342636581093377, 1238740807799730178);
INSERT INTO `t_system_role_dept` VALUES (1, '2020-03-16 12:03:19', NULL, NULL, 1, 1, 1, 1239401835294318594, 1230342636581093377, 1238723005621633026);
INSERT INTO `t_system_role_dept` VALUES (1239759022420127746, '2020-03-18 18:16:17', NULL, NULL, 1, 1, 1, 1240220471495905281, 1230342636581093377, 1239501714045726722);
INSERT INTO `t_system_role_dept` VALUES (1239759022420127746, '2020-03-18 18:24:51', NULL, NULL, 1, 0, 1, 1240222625837248513, 1230342636581093377, 1239501714045726722);

-- ----------------------------
-- Table structure for t_system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_permission`;
CREATE TABLE `t_system_role_permission`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色 ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1240517343292829703 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_permission
-- ----------------------------
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 16:56:00', NULL, NULL, NULL, 1, 1, 1236213998038171649, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 16:56:00', NULL, NULL, NULL, 1, 1, 1236213998059143170, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 16:56:00', NULL, NULL, NULL, 1, 1, 1236213998059143171, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 17:20:46', NULL, NULL, NULL, 1, 1, 1236220234662088706, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 17:20:46', NULL, NULL, NULL, 1, 1, 1236220234683060226, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-07 17:20:46', NULL, NULL, NULL, 1, 1, 1236220234691448833, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:39:49', NULL, NULL, NULL, 1, 1, 1239380822602993666, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:39:49', NULL, NULL, NULL, 1, 1, 1239380822628159489, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:39:49', NULL, NULL, NULL, 1, 1, 1239380822628159490, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:40:21', NULL, NULL, NULL, 1, 1, 1239380953570136065, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:40:21', NULL, NULL, NULL, 1, 1, 1239380953578524674, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-16 10:40:21', NULL, NULL, NULL, 1, 1, 1239380953586913281, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-17 18:25:57', NULL, NULL, NULL, 1, 1, 1239860513864642562, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-17 18:25:57', NULL, NULL, NULL, 1, 1, 1239860513898196993, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-17 18:25:57', NULL, NULL, NULL, 1, 1, 1239860513898196994, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-17 18:25:57', NULL, NULL, NULL, 1, 1, 1239860513906585601, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006650773505, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006680133634, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006680133635, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006688522242, 1230342636581093377, 1240145736623910914);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006696910850, 1230342636581093377, 1240145849547157505);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006705299457, 1230342636581093377, 1240145947077308418);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006709493762, 1230342636581093377, 1240146061539864578);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006717882369, 1230342636581093377, 1240146171363520514);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006717882370, 1230342636581093377, 1240146266293202945);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006717882371, 1230342636581093377, 1240146328645726210);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006726270978, 1230342636581093377, 1240146416986157057);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006726270979, 1230342636581093377, 1240146564894093313);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006726270980, 1230342636581093377, 1240146663686729730);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006734659586, 1230342636581093377, 1240146777977319425);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006734659587, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1, '2020-03-18 13:24:22', NULL, NULL, NULL, 1, 1, 1240147006734659588, 1230342636581093377, 1240146885477330945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542279659522, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542304825345, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542304825346, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542313213954, 1230342636581093377, 1240212755255889921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542321602562, 1230342636581093377, 1240145736623910914);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542325796865, 1230342636581093377, 1240145849547157505);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542334185474, 1230342636581093377, 1240145947077308418);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542334185475, 1230342636581093377, 1240146061539864578);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542342574082, 1230342636581093377, 1240146171363520514);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542342574083, 1230342636581093377, 1240146266293202945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542342574084, 1230342636581093377, 1240146328645726210);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542342574085, 1230342636581093377, 1240146416986157057);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542350962689, 1230342636581093377, 1240146564894093313);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542350962690, 1230342636581093377, 1240213036249092097);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542359351297, 1230342636581093377, 1240213109343227906);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542359351298, 1230342636581093377, 1240213241795153921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542359351299, 1230342636581093377, 1240146663686729730);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542371934209, 1230342636581093377, 1240213506774503426);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542380322817, 1230342636581093377, 1240213586537582593);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542380322818, 1230342636581093377, 1240213666728480769);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542384517121, 1230342636581093377, 1240146777977319425);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542384517122, 1230342636581093377, 1240213781199425537);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542384517123, 1230342636581093377, 1240213860752789506);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542384517124, 1230342636581093377, 1240213952691933185);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542392905729, 1230342636581093377, 1240216130269712385);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542392905730, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542397100034, 1230342636581093377, 1240146885477330945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542397100035, 1230342636581093377, 1240214051463598081);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:08:37', NULL, NULL, NULL, 1, 1, 1240218542397100036, 1230342636581093377, 1240214112910151681);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983601897473, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983610286081, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983618674690, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983618674691, 1230342636581093377, 1240212755255889921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983618674692, 1230342636581093377, 1240145736623910914);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983618674693, 1230342636581093377, 1240145849547157505);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983618674694, 1230342636581093377, 1240145947077308418);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063297, 1230342636581093377, 1240146061539864578);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063298, 1230342636581093377, 1240146171363520514);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063299, 1230342636581093377, 1240146266293202945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063300, 1230342636581093377, 1240146328645726210);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063301, 1230342636581093377, 1240146416986157057);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983627063302, 1230342636581093377, 1240146564894093313);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451905, 1230342636581093377, 1240213036249092097);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451906, 1230342636581093377, 1240213109343227906);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451907, 1230342636581093377, 1240213241795153921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451908, 1230342636581093377, 1240146663686729730);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451909, 1230342636581093377, 1240213506774503426);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983635451910, 1230342636581093377, 1240213586537582593);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840514, 1230342636581093377, 1240213666728480769);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840515, 1230342636581093377, 1240146777977319425);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840516, 1230342636581093377, 1240213781199425537);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840517, 1230342636581093377, 1240213860752789506);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840518, 1230342636581093377, 1240213952691933185);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983643840519, 1230342636581093377, 1240216130269712385);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983652229121, 1230342636581093377, 1240219902375071746);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983652229122, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983656423425, 1230342636581093377, 1240146885477330945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983656423426, 1230342636581093377, 1240214051463598081);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-18 18:14:21', NULL, NULL, NULL, 1, 1, 1240219983656423427, 1230342636581093377, 1240214112910151681);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116131340289, 1230342636581093377, 1240146564894093313);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116152311809, 1230342636581093377, 1240213036249092097);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116152311810, 1230342636581093377, 1240213109343227906);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116152311811, 1230342636581093377, 1240213241795153921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116160700417, 1230342636581093377, 1240146663686729730);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116160700418, 1230342636581093377, 1240213506774503426);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116169089025, 1230342636581093377, 1240213586537582593);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116169089026, 1230342636581093377, 1240213666728480769);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477634, 1230342636581093377, 1240146777977319425);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477635, 1230342636581093377, 1240213781199425537);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477636, 1230342636581093377, 1240213860752789506);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477637, 1230342636581093377, 1240213952691933185);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477638, 1230342636581093377, 1240216130269712385);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116177477639, 1230342636581093377, 1240219902375071746);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116185866241, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116185866242, 1230342636581093377, 1240146885477330945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116185866243, 1230342636581093377, 1240214051463598081);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 11:00:12', NULL, NULL, NULL, 1, 1, 1240473116194254850, 1230342636581093377, 1240214112910151681);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343250886658, 1230342636581093377, 1233317333434363905);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275265, 1230342636581093377, 1233598448384589825);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275266, 1230342636581093377, 1233599186682757122);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275267, 1230342636581093377, 1240212755255889921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275268, 1230342636581093377, 1240145736623910914);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275269, 1230342636581093377, 1240145849547157505);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343259275270, 1230342636581093377, 1240145947077308418);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343267663874, 1230342636581093377, 1240146061539864578);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343267663875, 1230342636581093377, 1240146171363520514);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858177, 1230342636581093377, 1240146266293202945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858178, 1230342636581093377, 1240146328645726210);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858179, 1230342636581093377, 1240146416986157057);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858180, 1230342636581093377, 1240146564894093313);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858181, 1230342636581093377, 1240213036249092097);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858182, 1230342636581093377, 1240213109343227906);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343271858183, 1230342636581093377, 1240213241795153921);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343280246786, 1230342636581093377, 1240146663686729730);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343280246787, 1230342636581093377, 1240213506774503426);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343280246788, 1230342636581093377, 1240213586537582593);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343284441090, 1230342636581093377, 1240213666728480769);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343284441091, 1230342636581093377, 1240146777977319425);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343284441092, 1230342636581093377, 1240213781199425537);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343284441093, 1230342636581093377, 1240213860752789506);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343284441094, 1230342636581093377, 1240213952691933185);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829697, 1230342636581093377, 1240216130269712385);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829698, 1230342636581093377, 1240219902375071746);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829699, 1230342636581093377, 1239860320746258433);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829700, 1230342636581093377, 1240146885477330945);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829701, 1230342636581093377, 1240214051463598081);
INSERT INTO `t_system_role_permission` VALUES (1239759022420127746, '2020-03-19 13:55:57', NULL, NULL, NULL, 0, 1, 1240517343292829702, 1230342636581093377, 1240214112910151681);

-- ----------------------------
-- Table structure for t_system_router
-- ----------------------------
DROP TABLE IF EXISTS `t_system_router`;
CREATE TABLE `t_system_router`  (
  `create_user_id` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `filters` varchar(3072) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由过滤器(JSON)',
  `predicates` varchar(3072) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '断言(json)',
  `uri` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转发的目标uri',
  `order` int(11) NULL DEFAULT NULL COMMENT '执行顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统路由 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_router
-- ----------------------------
INSERT INTO `t_system_router` VALUES (1, '2020-02-21 15:49:25', 1, '2020-02-23 14:09:19', NULL, 1, 1, 1, '系统用户角色', '[]', '[{\"args\":{\"\":\"/v1/server/system/user/role/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-role-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-12 15:49:25', 1, '2020-02-23 14:10:45', NULL, 1, 1, 2, '认证中心', '[]', '[{\"args\":{\"\":\"/oauth/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-oauth2-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-21 16:42:05', 1239759022420127746, '2020-03-18 17:21:14', NULL, 0, 1, 1230774680817659906, '系统路由', '[]', '[{\"args\":{\"\":\"/v1/server/system/router/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-router-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-21 17:16:49', 1, '2020-02-22 21:13:43', NULL, 0, 1, 1230783420602150914, '用户服务', '[]', '[{\"args\":{\"\":\"/v1/server/system/user/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-21 17:19:26', 1, '2020-02-22 21:16:20', NULL, 0, 1, 1230784077379825666, '认证中心（登录/注销）', '[]', '[{\"args\":{\"\":\"/v1/server/system/oauth2/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-oauth2-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-22 18:52:10', 1239759022420127746, '2020-03-18 18:35:32', NULL, 1, 0, 1231169804848545794, '测试案例', '[]', '[{\"args\":{\"pattern\":\"/test\"},\"name\":\"Path\"}]', 'https://baidu.com', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-23 11:43:57', 1239759022420127746, '2020-03-18 18:38:33', NULL, 0, 0, 1231424429535252481, '二次测试', '[]', '[{\"args\":{\"pattern\":\"/blog\"},\"name\":\"Path\"}]', 'https://blog.hb0730.com', 1);
INSERT INTO `t_system_router` VALUES (1, '2020-02-23 13:23:14', NULL, NULL, NULL, 0, 1, 1231449413968146434, '系统角色', NULL, '[{\"args\":{\"\":\"/v1/server/system/role/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-role-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-23 14:10:07', 1, '2020-03-17 10:34:24', NULL, 1, 1, 1231461210255147009, '系统用户角色', NULL, '[{\"args\":{\"\":\"/v1/server/system/user/role/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-role-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-23 14:11:19', NULL, NULL, NULL, 0, 1, 1231461515600478209, '认证中心', NULL, '[{\"args\":{\"\":\"/oauth/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-oauth2-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-24 18:14:20', NULL, NULL, NULL, 0, 1, 1231885057324367873, '系统菜单', NULL, '[{\"args\":{\"\":\"/v1/server/system/menu/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-menu-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-26 14:17:51', NULL, NULL, NULL, 0, 1, 1232550323402199042, '系统权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/permission/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-permission-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-26 14:18:05', 1, '2020-02-29 13:34:57', NULL, 1, 1, 1232550381837242370, '系统权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/permission/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-permission-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-26 14:21:39', 1, '2020-02-29 13:34:30', NULL, 1, 1, 1232551279938392066, '系统权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/permission/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-permission-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-02-27 19:32:22', 1, '2020-02-28 18:52:56', NULL, 0, 1, 1232991862351921153, '系统菜单权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/permissionmenu/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-permission-menu-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-07 16:21:03', NULL, NULL, NULL, 0, 1, 1236205204054966274, '系统角色权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/rolepermission/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-role-permission-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-14 13:27:41', NULL, NULL, 1, 0, 1, 1238698292459491329, '系统组织', NULL, '[{\"args\":{\"\":\"/v1/server/system/dept/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-dept-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-15 09:36:56', NULL, NULL, 1, 0, 1, 1239002608944676866, '系统岗位', NULL, '[{\"args\":{\"\":\"/v1/server/system/post/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-post-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-15 12:15:29', NULL, NULL, 2, 0, 1, 1239042508901449730, '系统组织岗位绑定', NULL, '[{\"args\":{\"\":\"/v1/server/system/postDept/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-post-dept-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-16 11:01:49', NULL, NULL, 1, 0, 1, 1239386357356212225, '系统角色权限', NULL, '[{\"args\":{\"\":\"/v1/server/system/roleDept/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-role-dept-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-17 10:32:00', 1, '2020-03-17 11:03:16', 3, 0, 1, 1239741243117760513, '系统用户组织', NULL, '[{\"args\":{\"\":\"/v1/server/system/userDept/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-dept-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-17 10:32:55', NULL, NULL, 1, 0, 1, 1239741472357445633, '系统用户岗位', NULL, '[{\"args\":{\"\":\"/v1/server/system/userPost/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-post-server', 0);
INSERT INTO `t_system_router` VALUES (1, '2020-03-17 10:33:52', NULL, NULL, 1, 0, 1, 1239741710921068545, '系统用户角色', NULL, '[{\"args\":{\"\":\"/v1/server/system/user/role/**\"},\"name\":\"Path\"}]', 'lb://cloud-admin-user-role-server', 0);

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `portraits` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1239837076672638979 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES (NULL, NULL, 1, '2020-02-24 15:07:33', NULL, 0, 1, 1, '超级管理员', 'test1', '$2a$10$MCkStBA1QilHHPBW3QUvp.FnP0HC/dKTRMDCtPhJfY4hzBvov1ljK', '17673654989', NULL, NULL);
INSERT INTO `t_system_user` VALUES (1, '2020-02-20 12:10:09', NULL, NULL, NULL, 0, 1, 4, '测试2', 'test2', '$2a$10$kMyNtm68UQM9Fr5fPGJhTeOeTum5bOomQv4uxPJpruB0VKLpY.5pq', '1234567891', 'test@qq.com', NULL);
INSERT INTO `t_system_user` VALUES (1, '2020-03-17 11:42:39', 1, '2020-03-17 15:04:32', NULL, 0, 1, 1239759022420127746, '超级管理员', 'Administrator', '$2a$10$l0QyBbQL4oQliBHNS6on1O1pOyBzEkhIPQzY4T9A.1fQj8sbIsfy.', NULL, '1278032416@qq.com', NULL);
INSERT INTO `t_system_user` VALUES (1, '2020-03-17 15:17:05', 1, '2020-03-17 15:18:13', NULL, 0, 1, 1239812983982288897, '测试02', 'test02', '$2a$10$jOlaS6V9iRWT0MgH9JQf2OiXVixAxOi.bCY0mm8lmcWmMBeh17B6m', NULL, 'test@gz.com', NULL);
INSERT INTO `t_system_user` VALUES (1, '2020-03-17 16:52:49', NULL, NULL, NULL, 1, 1, 1239837076672638978, '测试', 'test', '$2a$10$Hy2SFoIhZHJyiq26IyxuGeLWuladeLeCD5pGpMKN35ca8m21d3Du6', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_system_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_dept`;
CREATE TABLE `t_system_user_dept`  (
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户组织 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_dept
-- ----------------------------
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 11:42:40', NULL, NULL, 1, 1, 1, 1239759026916388865, 1239759022420127746, 1239501714045726722);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:04:33', NULL, NULL, 1, 1, 1, 1239809830905896961, 1239759022420127746, 1239501714045726722);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:06:10', NULL, NULL, 1, 1, 1, 1239810238797766658, 1239759022420127746, 1239501714045726722);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:12:01', NULL, NULL, 1, 0, 1, 1239811709308166145, 1239759022420127746, 1239501714045726722);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:17:05', NULL, NULL, 1, 1, 1, 1239812985345466369, 1239812983982288897, 1238723005621633026);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:18:13', NULL, NULL, 1, 1, 1, 1239813272151973889, 1239812983982288897, 1238723005621633026);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 15:24:58', NULL, NULL, 1, 1, 1, 1239814968257208321, 1239812983982288897, 1238740885025255426);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 16:38:33', NULL, NULL, 1, 1, 1, 1239833486923079681, 1239812983982288897, 1238740885025255426);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 16:52:12', NULL, NULL, 1, 0, 1, 1239836924297814018, 1239812983982288897, 1238740885025255426);
INSERT INTO `t_system_user_dept` VALUES (1, '2020-03-17 16:52:49', NULL, NULL, 1, 1, 1, 1239837078144884738, 1239837076672638978, 1238728324540186626);

-- ----------------------------
-- Table structure for t_system_user_post
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_post`;
CREATE TABLE `t_system_user_post`  (
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_post
-- ----------------------------
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 11:42:41', NULL, NULL, 1, 1, 1, 1239759029441384450, 1239759022420127746, 1239501824276217857);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:04:34', NULL, NULL, 1, 1, 1, 1239809834865356802, 1239759022420127746, 1);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:06:11', NULL, NULL, 1, 1, 1, 1239810240945287169, 1239759022420127746, 1239501824276217857);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:12:01', NULL, NULL, 1, 0, 1, 1239811712424570881, 1239759022420127746, 1239501824276217857);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:17:05', NULL, NULL, 1, 1, 1, 1239812987258105858, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:18:14', NULL, NULL, 1, 1, 1, 1239813274182053889, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 15:24:59', NULL, NULL, 1, 1, 1, 1239814971470082049, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 16:38:34', NULL, NULL, 1, 1, 1, 1239833490207240193, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 16:47:06', NULL, NULL, 1, 1, 1, 1239835638395531265, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 16:52:13', NULL, NULL, 1, 0, 1, 1239836926411763713, 1239812983982288897, 1239027014035292161);
INSERT INTO `t_system_user_post` VALUES (1, '2020-03-17 16:52:50', NULL, NULL, 1, 1, 1, 1239837080153976833, 1239837076672638978, 1239027014035292161);

-- ----------------------------
-- Table structure for t_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role`  (
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `is_delete` int(2) NULL DEFAULT 0 COMMENT '是否删除',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户 ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1239837082079076354 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_role
-- ----------------------------
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 11:42:42', NULL, NULL, 1, 1, 1, 1239759033371389953, 1239759022420127746, 1230342636581093377);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:04:35', NULL, NULL, 1, 1, 1, 1239809838027829249, 1239759022420127746, 1230342636581093377);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:06:11', NULL, NULL, 1, 1, 1, 1239810243054989314, 1239759022420127746, 1230342636581093377);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:12:02', NULL, NULL, 1, 0, 1, 1239811714509107201, 1239759022420127746, 1230342636581093377);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:17:06', NULL, NULL, 1, 1, 1, 1239812989296504833, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:18:14', NULL, NULL, 1, 1, 1, 1239813276367253505, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:18:14', NULL, NULL, 1, 1, 1, 1239813276375642114, 1239812983982288897, 1230342636581093377);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 15:24:59', NULL, NULL, 1, 1, 1, 1239814973743362049, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 16:38:35', NULL, NULL, 1, 1, 1, 1239833493570985986, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 16:47:06', NULL, NULL, 1, 1, 1, 1239835640450654209, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 16:52:13', NULL, NULL, 1, 0, 1, 1239836928399777793, 1239812983982288897, 1239738541524586497);
INSERT INTO `t_system_user_role` VALUES (1, '2020-03-17 16:52:50', NULL, NULL, 1, 1, 1, 1239837082079076353, 1239837076672638978, 1239738541524586497);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分布式事务seata' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES (16, 2036737877, '47.96.79.54:8091:2036737870', 'serializer=jackson', 0x7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E756E646F2E4272616E6368556E646F4C6F67222C22786964223A2234372E39362E37392E35343A383039313A32303336373337383730222C226272616E63684964223A323033363733373837372C2273716C556E646F4C6F6773223A5B226A6176612E7574696C2E41727261794C697374222C5B7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E756E646F2E53514C556E646F4C6F67222C2273716C54797065223A22494E53455254222C227461626C654E616D65223A22745F73797374656D5F757365725F64657074222C226265666F7265496D616765223A7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E5461626C655265636F72647324456D7074795461626C655265636F726473222C227461626C654E616D65223A22745F73797374656D5F757365725F64657074222C22726F7773223A5B226A6176612E7574696C2E41727261794C697374222C5B5D5D7D2C226166746572496D616765223A7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E5461626C655265636F726473222C227461626C654E616D65223A22745F73797374656D5F757365725F64657074222C22726F7773223A5B226A6176612E7574696C2E41727261794C697374222C5B7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E526F77222C226669656C6473223A5B226A6176612E7574696C2E41727261794C697374222C5B7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A226372656174655F757365725F6964222C226B657954797065223A224E554C4C222C2274797065223A342C2276616C7565223A317D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A226372656174655F74696D65222C226B657954797065223A224E554C4C222C2274797065223A39332C2276616C7565223A5B226A6176612E73716C2E54696D657374616D70222C5B313538343431353331333030302C305D5D7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A227570646174655F757365725F6964222C226B657954797065223A224E554C4C222C2274797065223A342C2276616C7565223A6E756C6C7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A227570646174655F74696D65222C226B657954797065223A224E554C4C222C2274797065223A39332C2276616C7565223A6E756C6C7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A2276657273696F6E222C226B657954797065223A224E554C4C222C2274797065223A342C2276616C7565223A6E756C6C7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A2269735F64656C657465222C226B657954797065223A224E554C4C222C2274797065223A342C2276616C7565223A307D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A2269735F656E61626C6564222C226B657954797065223A224E554C4C222C2274797065223A342C2276616C7565223A317D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A226964222C226B657954797065223A225072696D6172794B6579222C2274797065223A2D352C2276616C7565223A5B226A6176612E6C616E672E4C6F6E67222C313233393735333739353231343131343831385D7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A22757365725F6964222C226B657954797065223A224E554C4C222C2274797065223A2D352C2276616C7565223A5B226A6176612E6C616E672E4C6F6E67222C313233393735333737363735393235303934365D7D2C7B2240636C617373223A22696F2E73656174612E726D2E64617461736F757263652E73716C2E7374727563742E4669656C64222C226E616D65223A22646570745F6964222C226B657954797065223A224E554C4C222C2274797065223A2D352C2276616C7565223A5B226A6176612E6C616E672E4C6F6E67222C313233393530313731343034353732363732325D7D5D5D7D5D5D7D7D5D5D7D, 0, '2020-03-17 11:21:53', '2020-03-17 11:21:53', NULL);

SET FOREIGN_KEY_CHECKS = 1;
