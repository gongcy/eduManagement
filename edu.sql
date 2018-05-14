/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-12-31 22:47:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_menu
-- ----------------------------
DROP TABLE IF EXISTS `a_menu`;
CREATE TABLE `a_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display` int(11) NOT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `psn` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_menu
-- ----------------------------
INSERT INTO `a_menu` VALUES ('1', '1', '#', null, '系统管理', '10', null, null, 'root', 'xi_tong_guan_li_', '1');
INSERT INTO `a_menu` VALUES ('2', '1', '#', 'glyphicon-tags', '系统配置', '10', '1', null, 'xi_tong_guan_li_', 'AppConfigController', '1');
INSERT INTO `a_menu` VALUES ('3', '1', '/admin/appConfig/index', 'glyphicon glyphicon-cog', '系统配置', '1', '2', null, 'AppConfigController', 'AppConfigController.index', '1');
INSERT INTO `a_menu` VALUES ('4', '1', '#', null, '权限管理', '10', null, null, 'root', 'quan_xian_guan_li_', '1');
INSERT INTO `a_menu` VALUES ('5', '1', '#', 'glyphicon-tags', '菜单管理', '1', '4', null, 'quan_xian_guan_li_', 'MenuController', '1');
INSERT INTO `a_menu` VALUES ('6', '1', '/admin/menu/list', 'icon-list', '菜单列表', '1', '5', null, 'MenuController', 'MenuController.list', '1');
INSERT INTO `a_menu` VALUES ('7', '1', '/admin/menu/rebuildMenus', 'glyphicon-tags', '重构菜单', '3', '5', null, 'MenuController', 'MenuController.rebuildMenus', '2');
INSERT INTO `a_menu` VALUES ('8', '1', '/admin/menu/updateSort', 'glyphicon-tags', '菜单序号', '4', '5', null, 'MenuController', 'MenuController.updateSort', '2');
INSERT INTO `a_menu` VALUES ('9', '1', '#', 'glyphicon-tags', '角色管理', '2', '4', null, 'quan_xian_guan_li_', 'RoleController', '1');
INSERT INTO `a_menu` VALUES ('10', '1', '/admin/role/list', 'icon-list', '角色列表', '1', '9', null, 'RoleController', 'RoleController.list', '1');
INSERT INTO `a_menu` VALUES ('11', '1', '/admin/role/menus', 'glyphicon-tags', '角色授权', '5', '9', null, 'RoleController', 'RoleController.menus', '2');
INSERT INTO `a_menu` VALUES ('12', '1', '/admin/role/add', 'icon-plus', '添加角色', '2', '9', null, 'RoleController', 'RoleController.add', '1');
INSERT INTO `a_menu` VALUES ('13', '1', '/admin/role/update', 'glyphicon-tags', '修改角色', '3', '9', null, 'RoleController', 'RoleController.update', '2');
INSERT INTO `a_menu` VALUES ('14', '1', '/admin/role/delete', 'glyphicon-tags', '删除角色', '4', '9', null, 'RoleController', 'RoleController.delete', '2');
INSERT INTO `a_menu` VALUES ('15', '1', '#', 'glyphicon-tags', '用户管理', '3', '4', null, 'quan_xian_guan_li_', 'UserController', '1');
INSERT INTO `a_menu` VALUES ('16', '1', '/admin/user/list', 'icon-list', '用户列表', '1', '15', null, 'UserController', 'UserController.list', '1');
INSERT INTO `a_menu` VALUES ('17', '1', '/admin/user/roles', 'glyphicon-tags', '用户授权', '5', '15', null, 'UserController', 'UserController.roles', '2');
INSERT INTO `a_menu` VALUES ('18', '1', '/admin/user/add', 'icon-plus', '添加用户', '2', '15', null, 'UserController', 'UserController.add', '1');
INSERT INTO `a_menu` VALUES ('19', '1', '/admin/user/update', 'glyphicon-tags', '修改用户', '3', '15', null, 'UserController', 'UserController.update', '2');
INSERT INTO `a_menu` VALUES ('20', '1', '/admin/user/delete', 'glyphicon-tags', '删除用户', '4', '15', null, 'UserController', 'UserController.delete', '2');
INSERT INTO `a_menu` VALUES ('21', '1', '#', 'glyphicon-tags', '人事管理', '2', '1', null, 'xi_tong_guan_li_', 'BookController', '1');
INSERT INTO `a_menu` VALUES ('22', '1', '/admin/book/list', 'icon-list', '图书列表', '1', '21', null, 'BookController', 'BookController.list', '1');
INSERT INTO `a_menu` VALUES ('23', '1', '/admin/book/add', 'icon-plus', '添加图书', '2', '21', null, 'BookController', 'BookController.add', '1');
INSERT INTO `a_menu` VALUES ('24', '1', '/admin/book/update', 'glyphicon-tags', '修改图书', '3', '21', null, 'BookController', 'BookController.update', '2');
INSERT INTO `a_menu` VALUES ('25', '1', '/admin/book/delete', 'glyphicon-tags', '删除图书', '4', '21', null, 'BookController', 'BookController.delete', '2');
INSERT INTO `a_menu` VALUES ('26', '1', '#', 'glyphicon-tags', '借阅管理', '5', '1', null, 'xi_tong_guan_li_', 'BorrowController', '1');
INSERT INTO `a_menu` VALUES ('27', '1', '/admin/borrow/list', 'icon-list', '分类列表', '1', '26', null, 'BorrowController', 'BorrowController.list', '1');
INSERT INTO `a_menu` VALUES ('28', '1', '/admin/borrow/add', 'icon-plus', '图书外借', '2', '26', null, 'BorrowController', 'BorrowController.add', '2');
INSERT INTO `a_menu` VALUES ('29', '1', '/admin/borrow/back', 'glyphicon-tags', '归还书籍', '5', '26', null, 'BorrowController', 'BorrowController.back', '2');
INSERT INTO `a_menu` VALUES ('30', '1', '#', 'glyphicon-tags', '人事职位管理', '1', '1', null, 'xi_tong_guan_li_', 'CategoryController', '1');
INSERT INTO `a_menu` VALUES ('31', '1', '/admin/category/list', 'icon-list', '分类列表', '1', '30', null, 'CategoryController', 'CategoryController.list', '1');
INSERT INTO `a_menu` VALUES ('32', '1', '/admin/category/add', 'icon-plus', '添加分类', '2', '30', null, 'CategoryController', 'CategoryController.add', '1');
INSERT INTO `a_menu` VALUES ('33', '1', '/admin/category/update', 'glyphicon-tags', '修改分类', '3', '30', null, 'CategoryController', 'CategoryController.update', '2');
INSERT INTO `a_menu` VALUES ('34', '1', '/admin/category/delete', 'glyphicon-tags', '删除分类', '4', '30', null, 'CategoryController', 'CategoryController.delete', '2');
INSERT INTO `a_menu` VALUES ('35', '1', '#', 'glyphicon-tags', '学生管理', '3', '1', null, 'xi_tong_guan_li_', 'ReaderController', '1');
INSERT INTO `a_menu` VALUES ('36', '1', '/admin/reader/list', 'icon-list', '学生列表', '1', '35', null, 'ReaderController', 'ReaderController.list', '1');
INSERT INTO `a_menu` VALUES ('37', '1', '/admin/reader/add', 'icon-plus', '添加学生', '2', '35', null, 'ReaderController', 'ReaderController.add', '1');
INSERT INTO `a_menu` VALUES ('38', '1', '/admin/reader/update', 'glyphicon-tags', '修改读者', '3', '35', null, 'ReaderController', 'ReaderController.update', '2');
INSERT INTO `a_menu` VALUES ('39', '1', '/admin/reader/delete', 'glyphicon-tags', '删除读者', '4', '35', null, 'ReaderController', 'ReaderController.delete', '2');

-- ----------------------------
-- Table structure for a_role
-- ----------------------------
DROP TABLE IF EXISTS `a_role`;
CREATE TABLE `a_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_division` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `owner_code` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_role
-- ----------------------------
INSERT INTO `a_role` VALUES ('1', null, '超级管理员角色', null, null, null, 'ROLE_SUPER_ADMIN');

-- ----------------------------
-- Table structure for a_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `a_role_menu`;
CREATE TABLE `a_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_role_menu
-- ----------------------------
INSERT INTO `a_role_menu` VALUES ('1', '3', '1');
INSERT INTO `a_role_menu` VALUES ('2', '6', '1');
INSERT INTO `a_role_menu` VALUES ('3', '7', '1');
INSERT INTO `a_role_menu` VALUES ('4', '8', '1');
INSERT INTO `a_role_menu` VALUES ('5', '10', '1');
INSERT INTO `a_role_menu` VALUES ('6', '11', '1');
INSERT INTO `a_role_menu` VALUES ('7', '12', '1');
INSERT INTO `a_role_menu` VALUES ('8', '13', '1');
INSERT INTO `a_role_menu` VALUES ('9', '14', '1');
INSERT INTO `a_role_menu` VALUES ('10', '16', '1');
INSERT INTO `a_role_menu` VALUES ('11', '17', '1');
INSERT INTO `a_role_menu` VALUES ('12', '18', '1');
INSERT INTO `a_role_menu` VALUES ('13', '19', '1');
INSERT INTO `a_role_menu` VALUES ('14', '20', '1');
INSERT INTO `a_role_menu` VALUES ('15', '22', '1');
INSERT INTO `a_role_menu` VALUES ('16', '23', '1');
INSERT INTO `a_role_menu` VALUES ('17', '24', '1');
INSERT INTO `a_role_menu` VALUES ('18', '25', '1');
INSERT INTO `a_role_menu` VALUES ('19', '27', '1');
INSERT INTO `a_role_menu` VALUES ('20', '28', '1');
INSERT INTO `a_role_menu` VALUES ('21', '29', '1');
INSERT INTO `a_role_menu` VALUES ('22', '31', '1');
INSERT INTO `a_role_menu` VALUES ('23', '32', '1');
INSERT INTO `a_role_menu` VALUES ('24', '33', '1');
INSERT INTO `a_role_menu` VALUES ('25', '34', '1');
INSERT INTO `a_role_menu` VALUES ('26', '36', '1');
INSERT INTO `a_role_menu` VALUES ('27', '37', '1');
INSERT INTO `a_role_menu` VALUES ('28', '38', '1');
INSERT INTO `a_role_menu` VALUES ('29', '39', '1');

-- ----------------------------
-- Table structure for a_user
-- ----------------------------
DROP TABLE IF EXISTS `a_user`;
CREATE TABLE `a_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `last_editdate` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `owner_code` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user
-- ----------------------------
INSERT INTO `a_user` VALUES ('1', '2016-12-26 17:39:05', null, null, '系统管理员', null, null, null, 'a66abb5684c45962d887564f08346e8d', '1', 'admin');
INSERT INTO `a_user` VALUES ('2', '2017-12-31 13:57:04', null, null, 'gongcy', null, null, null, 'a66abb5684c45962d887564f08346e8d', '1', 'gongcy');

-- ----------------------------
-- Table structure for a_user_role
-- ----------------------------
DROP TABLE IF EXISTS `a_user_role`;
CREATE TABLE `a_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user_role
-- ----------------------------
INSERT INTO `a_user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for b_app_config
-- ----------------------------
DROP TABLE IF EXISTS `b_app_config`;
CREATE TABLE `b_app_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) DEFAULT NULL,
  `app_version` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `index_page` varchar(255) DEFAULT NULL,
  `init_flag` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_app_config
-- ----------------------------
INSERT INTO `b_app_config` VALUES ('1', null, null, '2016-12-26 17:39:05', null, '1', null);

-- ----------------------------
-- Table structure for b_book
-- ----------------------------
DROP TABLE IF EXISTS `b_book`;
CREATE TABLE `b_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `cate_id` int(11) DEFAULT NULL,
  `cate_name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `money` float DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_full_no` varchar(255) DEFAULT NULL,
  `name_no` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `pos` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `pub` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_full_no` (`name_full_no`),
  KEY `name_no` (`name_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_book
-- ----------------------------
INSERT INTO `b_book` VALUES ('1', '55', 'asdasd', '1', 'asd', '2017-12-31 17:23:00', '59', 'asdasd', 'asdasd', 'asdasd', 'asdasd', 'asdasd', '55', 'asdasd', 'asd');

-- ----------------------------
-- Table structure for b_borrow
-- ----------------------------
DROP TABLE IF EXISTS `b_borrow`;
CREATE TABLE `b_borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `back_date` datetime DEFAULT NULL,
  `back_long` bigint(20) DEFAULT NULL,
  `back_opt_id` int(11) DEFAULT NULL,
  `back_opt_name` varchar(255) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `book_name_no` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `date_long` bigint(20) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `operator_name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `reader_id` int(11) DEFAULT NULL,
  `reader_identity` varchar(255) DEFAULT NULL,
  `reader_name` varchar(255) DEFAULT NULL,
  `reader_no` varchar(255) DEFAULT NULL,
  `reader_phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_money` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_name_no` (`book_name_no`),
  KEY `reader_no` (`reader_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_borrow
-- ----------------------------

-- ----------------------------
-- Table structure for b_category
-- ----------------------------
DROP TABLE IF EXISTS `b_category`;
CREATE TABLE `b_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_category
-- ----------------------------
INSERT INTO `b_category` VALUES ('1', 'asd');
INSERT INTO `b_category` VALUES ('2', 'eee');

-- ----------------------------
-- Table structure for b_reader
-- ----------------------------
DROP TABLE IF EXISTS `b_reader`;
CREATE TABLE `b_reader` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `card_no` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_full_no` varchar(255) DEFAULT NULL,
  `name_no` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_reader
-- ----------------------------
INSERT INTO `b_reader` VALUES ('1', '大保健', '222222222222222222', '2017-12-31 17:24:04', '222222222222222222', '业兴旺', 'ye xing wang ', 'yxw', '18888888888');
