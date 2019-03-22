/*
Navicat MySQL Data Transfer

Source Server         : 106.15.36.5
Source Server Version : 50717
Source Host           : 106.15.36.5:3306
Source Database       : rds

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-22 16:44:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rds_login_log
-- ----------------------------
DROP TABLE IF EXISTS `rds_login_log`;
CREATE TABLE `rds_login_log` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `log_type` char(1) COLLATE utf8_bin DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` char(15) COLLATE utf8_bin DEFAULT NULL,
  `expleror_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for rds_menu
-- ----------------------------
DROP TABLE IF EXISTS `rds_menu`;
CREATE TABLE `rds_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` char(5) COLLATE utf8_bin NOT NULL,
  `url` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pid` char(5) COLLATE utf8_bin DEFAULT '',
  `menu_name` varchar(50) COLLATE utf8_bin DEFAULT '',
  `menu_type` char(6) COLLATE utf8_bin DEFAULT '',
  `order_num` tinyint(4) DEFAULT '0',
  `percode` varchar(50) COLLATE utf8_bin DEFAULT '',
  `icon` char(8) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_menu
-- ----------------------------
INSERT INTO `rds_menu` VALUES ('2', '101', '/rds_menu/rdsMenuList', '1', '菜单管理', 'page', '1', 'rdsMenu:list', null);
INSERT INTO `rds_menu` VALUES ('3', '102', '/rds_role/rdsRoleList', '1', '角色管理', 'page', '3', 'rdsRole:list', null);
INSERT INTO `rds_menu` VALUES ('4', '103', '/rds_user/rdsUserList', '1', '用户管理', 'page', '4', 'rdsUser:list', null);
INSERT INTO `rds_menu` VALUES ('10', '1', '', '0', '系统管理', 'module', '1', '/', null);
INSERT INTO `rds_menu` VALUES ('15', '10101', '', '101', '查询', 'button', '1', 'rdsMenu:search', '');
INSERT INTO `rds_menu` VALUES ('16', '10102', '', '101', '新增', 'button', '2', 'rdsMenu:add', null);
INSERT INTO `rds_menu` VALUES ('17', '10103', '', '101', '修改', 'button', '3', 'rdsMenu:update', null);
INSERT INTO `rds_menu` VALUES ('18', '10104', '', '101', '删除', 'button', '4', 'rdsMenu:delete', null);
INSERT INTO `rds_menu` VALUES ('23', '10201', '', '102', '查询', 'button', '1', 'rdsRole:search', '');
INSERT INTO `rds_menu` VALUES ('24', '10202', '', '102', '新增', 'button', '2', 'rdsRole:add', '');
INSERT INTO `rds_menu` VALUES ('25', '10203', '', '102', '修改', 'button', '3', 'rdsRole:update', '');
INSERT INTO `rds_menu` VALUES ('26', '10204', '', '102', '删除', 'button', '4', 'rdsRole:delete', '');
INSERT INTO `rds_menu` VALUES ('27', '10301', '', '103', '查询', 'button', '1', 'rdsUser:search', '');
INSERT INTO `rds_menu` VALUES ('28', '10302', '', '103', '新增', 'button', '2', 'rdsUser:add', '');
INSERT INTO `rds_menu` VALUES ('29', '10303', '', '103', '修改', 'button', '3', 'rdsUser:update', '');
INSERT INTO `rds_menu` VALUES ('30', '10304', '', '103', '删除', 'button', '4', 'rdsUser:delete', '');
INSERT INTO `rds_menu` VALUES ('31', '10305', '', '103', '重置密码', 'button', '5', 'rdsUser:reset', '');
INSERT INTO `rds_menu` VALUES ('32', '10306', '', '103', '分配角色', 'button', '6', 'rdsUser:setRole', '');

-- ----------------------------
-- Table structure for rds_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `rds_operator_log`;
CREATE TABLE `rds_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moudle_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sql` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `rows_affected` smallint(6) DEFAULT NULL,
  `login_ip` char(15) COLLATE utf8_bin DEFAULT NULL,
  `expleror_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `param` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_operator_log
-- ----------------------------

-- ----------------------------
-- Table structure for rds_role
-- ----------------------------
DROP TABLE IF EXISTS `rds_role`;
CREATE TABLE `rds_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` char(5) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(50) COLLATE utf8_bin DEFAULT '',
  `role_desc` varchar(100) COLLATE utf8_bin DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_role
-- ----------------------------
INSERT INTO `rds_role` VALUES ('1', 'admin', '管理员', '超级管理员', '2019-03-07 10:56:28', '2019-03-07 10:56:33');
INSERT INTO `rds_role` VALUES ('9', 'ceshi', '测试', '测试', '2019-03-13 11:21:33', '2019-03-13 11:27:04');
INSERT INTO `rds_role` VALUES ('11', 'cs1', '测试', '321', '2019-03-20 02:24:01', '2019-03-20 02:36:31');

-- ----------------------------
-- Table structure for rds_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `rds_role_menu`;
CREATE TABLE `rds_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` char(5) COLLATE utf8_bin NOT NULL,
  `menu_id` char(5) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_role_menu
-- ----------------------------
INSERT INTO `rds_role_menu` VALUES ('106', 'admin', '1');
INSERT INTO `rds_role_menu` VALUES ('107', 'admin', '101');
INSERT INTO `rds_role_menu` VALUES ('108', 'admin', '10101');
INSERT INTO `rds_role_menu` VALUES ('109', 'admin', '10102');
INSERT INTO `rds_role_menu` VALUES ('110', 'admin', '10103');
INSERT INTO `rds_role_menu` VALUES ('111', 'admin', '10104');
INSERT INTO `rds_role_menu` VALUES ('112', 'admin', '102');
INSERT INTO `rds_role_menu` VALUES ('113', 'admin', '10204');
INSERT INTO `rds_role_menu` VALUES ('114', 'admin', '10301');
INSERT INTO `rds_role_menu` VALUES ('115', 'admin', '10302');
INSERT INTO `rds_role_menu` VALUES ('116', 'admin', '10303');
INSERT INTO `rds_role_menu` VALUES ('117', 'admin', '10304');
INSERT INTO `rds_role_menu` VALUES ('118', 'admin', '10305');
INSERT INTO `rds_role_menu` VALUES ('119', 'admin', '10306');
INSERT INTO `rds_role_menu` VALUES ('120', 'admin', '103');
INSERT INTO `rds_role_menu` VALUES ('121', 'admin', '10201');
INSERT INTO `rds_role_menu` VALUES ('122', 'admin', '10202');
INSERT INTO `rds_role_menu` VALUES ('123', 'admin', '10203');
INSERT INTO `rds_role_menu` VALUES ('196', 'ceshi', '1');
INSERT INTO `rds_role_menu` VALUES ('197', 'ceshi', '101');
INSERT INTO `rds_role_menu` VALUES ('198', 'ceshi', '10101');
INSERT INTO `rds_role_menu` VALUES ('199', 'ceshi', '10102');
INSERT INTO `rds_role_menu` VALUES ('200', 'ceshi', '10103');
INSERT INTO `rds_role_menu` VALUES ('201', 'ceshi', '10104');
INSERT INTO `rds_role_menu` VALUES ('202', 'ceshi', '102');
INSERT INTO `rds_role_menu` VALUES ('203', 'ceshi', '10201');
INSERT INTO `rds_role_menu` VALUES ('204', 'ceshi', '10202');
INSERT INTO `rds_role_menu` VALUES ('205', 'ceshi', '10203');
INSERT INTO `rds_role_menu` VALUES ('206', 'ceshi', '10204');
INSERT INTO `rds_role_menu` VALUES ('207', 'ceshi', '103');
INSERT INTO `rds_role_menu` VALUES ('208', 'ceshi', '10301');
INSERT INTO `rds_role_menu` VALUES ('209', 'ceshi', '10302');
INSERT INTO `rds_role_menu` VALUES ('210', 'ceshi', '10303');
INSERT INTO `rds_role_menu` VALUES ('211', 'ceshi', '10304');
INSERT INTO `rds_role_menu` VALUES ('212', 'ceshi', '10305');
INSERT INTO `rds_role_menu` VALUES ('213', 'ceshi', '10306');

-- ----------------------------
-- Table structure for rds_sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `rds_sys_dic`;
CREATE TABLE `rds_sys_dic` (
  `id` bigint(20) NOT NULL,
  `para_id` char(10) COLLATE utf8_bin DEFAULT NULL,
  `para_value` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `para_zh_name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `para_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `para_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_sys_dic
-- ----------------------------

-- ----------------------------
-- Table structure for rds_user
-- ----------------------------
DROP TABLE IF EXISTS `rds_user`;
CREATE TABLE `rds_user` (
  `id` bigint(20) NOT NULL COMMENT '自增长ID',
  `user_id` varchar(10) COLLATE utf8_bin NOT NULL,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` char(32) COLLATE utf8_bin DEFAULT NULL,
  `salt` char(5) COLLATE utf8_bin DEFAULT NULL COMMENT '进行MD5加密使用，每个用户可以使用不同的盐',
  `department` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '存储部门ID，数据字典翻译',
  `job` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '可以根据不同的工作岗位ID，进行数据字典翻译',
  `is_use` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0：未启动 1：启用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_user
-- ----------------------------
INSERT INTO `rds_user` VALUES ('2', '122', '12', null, null, null, null, null, null, null);
INSERT INTO `rds_user` VALUES ('1', 'admin', '管理员', '0e901ca5ccf43bacfc35c0a24dd75680', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for rds_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rds_user_role`;
CREATE TABLE `rds_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `user_id` char(5) COLLATE utf8_bin NOT NULL,
  `role_id` char(5) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rds_user_role
-- ----------------------------
INSERT INTO `rds_user_role` VALUES ('1', 'admin', 'admin');
INSERT INTO `rds_user_role` VALUES ('8', '122', 'admin');
INSERT INTO `rds_user_role` VALUES ('9', '122', 'ceshi');
