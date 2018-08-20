/*
Navicat MySQL Data Transfer

Source Server         : lilin
Source Server Version : 50722
Source Host           : 172.168.4.84:3306
Source Database       : lilin_property_service

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-01 17:04:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for common_query
-- ----------------------------
DROP TABLE IF EXISTS `common_query`;
CREATE TABLE `common_query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `type` varchar(50) NOT NULL COMMENT '常用查询类别',
  `content` varchar(2000) NOT NULL COMMENT '详细内容',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  `project_id` varchar(400) DEFAULT NULL COMMENT '项目ID',
  `phone` varchar(40) DEFAULT NULL COMMENT '电话',
  `address` varchar(40) DEFAULT NULL COMMENT '地址',
  `status` int(2) DEFAULT '1' COMMENT '默认0 发布  1 代表未发布',
  `del_flag` int(2) DEFAULT '0' COMMENT '默认0 代表未删除 1 代表已删除',
  `project_name` varchar(400) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='常用查询';

-- ----------------------------
-- Records of common_query
-- ----------------------------
INSERT INTO `common_query` VALUES ('57', '孤鸿寡鹄', '食堂', '大锅饭', '2018-06-27 00:05:11', '2018-07-07 00:15:41', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '234', null, '0', '1', '东方别墅');
INSERT INTO `common_query` VALUES ('58', '共和国', '学校', '半年报', '2018-07-04 17:25:39', '2018-07-07 00:17:55', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '678', null, '1', '1', '东方别墅');
INSERT INTO `common_query` VALUES ('59', '更好566', '学校', '大锅饭大幅度发给股份', '2018-07-05 14:30:06', '2018-07-07 00:18:17', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '4578', null, '0', '1', '东方别墅');
INSERT INTO `common_query` VALUES ('60', '风格更好', '食堂', '发广告', '2018-07-04 13:25:29', '2018-07-07 00:38:43', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '5677', null, '0', '0', '东方别墅');
INSERT INTO `common_query` VALUES ('61', '换个', '银行', '发过火', '2018-07-09 12:20:45', '2018-07-07 00:38:58', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '677', null, '1', '0', '东方别墅');
INSERT INTO `common_query` VALUES ('62', '会更好', '食堂', '风格更好', '2018-07-10 17:25:42', '2018-07-07 01:14:53', 'liufp', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', '5677', null, '0', '0', 'ie9测2试22');
INSERT INTO `common_query` VALUES ('63', '暑假放假通知1', '学校', '暑假来临，放假，请大家保持安全1', '2018-07-10 10:00:46', '2018-07-10 22:01:39', 'liufp', '3850A167-D309-4582-BD76-0C3CAB4C008E', '13855386270', null, '0', '1', '东方别墅');
INSERT INTO `common_query` VALUES ('64', '餐费补贴', '食堂', '每人补贴三元', '2018-07-11 01:25:00', '2018-07-14 01:12:38', 'syj', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '13978900987', null, '0', '1', '测试开发环境');
INSERT INTO `common_query` VALUES ('65', '韩国国会', '工厂', '符合规划共和国更换合格更好', '2018-07-18 17:05:03', '2018-07-16 17:53:25', 'syj', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '15980765678', null, '0', '1', '测试开发环境');

-- ----------------------------
-- Table structure for complaint_suggestion
-- ----------------------------
DROP TABLE IF EXISTS `complaint_suggestion`;
CREATE TABLE `complaint_suggestion` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(40) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `building_id` varchar(40) DEFAULT NULL COMMENT '楼栋id',
  `building_name` varchar(100) DEFAULT NULL COMMENT '楼栋名称',
  `room_id` varchar(40) DEFAULT NULL COMMENT '房号id',
  `room_name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `resident_id` varchar(40) DEFAULT NULL COMMENT '联系人id',
  `resident_name` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式（手机号码）',
  `complaint_content` text COMMENT '投诉内容',
  `processing_content` text COMMENT '处理内容',
  `complaint_time` datetime DEFAULT NULL COMMENT '投诉时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(11) DEFAULT NULL COMMENT '更新人',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：Y 已处理 N 未处理',
  `del_flag` int(2) DEFAULT '0' COMMENT '0 表示未删除  1表示已删除',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='投诉建议';

-- ----------------------------
-- Records of complaint_suggestion
-- ----------------------------
INSERT INTO `complaint_suggestion` VALUES ('1', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '投诉建议测试', null, '2018-07-15 10:52:21', '2018-07-15 10:52:21', null, null, null, 'Y', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('2', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '反馈建议1', null, '2018-07-15 13:04:02', '2018-07-15 13:04:02', null, null, null, 'Y', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('3', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '意见', '韩国国会', '2018-07-15 13:33:12', '2018-07-15 13:33:12', null, null, null, 'Y', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('4', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '建议测试1', '规划环境', '2018-07-15 13:33:40', '2018-07-15 13:33:40', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('5', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '投诉建议测试', null, '2018-07-15 10:52:21', '2018-07-15 10:52:21', null, null, null, 'Y', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('6', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '反馈建议1', null, '2018-07-15 13:04:02', '2018-07-15 13:04:02', null, null, null, 'Y', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('7', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '意见', null, '2018-07-15 13:33:12', '2018-07-15 13:33:12', null, null, null, 'N', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('8', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '建议测试1', null, '2018-07-15 13:33:40', '2018-07-15 13:33:40', null, null, null, 'Y', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('9', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '你好啊', '投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议投诉建议', '2018-07-15 17:17:21', '2018-07-15 17:17:21', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('10', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', null, null, 'dffd的覆盖广泛低分段', '法规和韩国', '2018-07-15 19:25:34', '2018-07-15 19:25:34', null, null, null, 'N', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('11', '” 3850A167-D309-4582-BD76-0C3CAB4C008E” ', null, null, null, null, null, '”1”', null, null, '投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议投诉ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss投诉建议', null, '2018-07-16 09:05:55', '2018-07-16 09:05:55', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('12', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '测试投诉', '', '2018-07-16 09:38:59', '2018-07-16 09:38:59', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('13', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一投诉建议一', null, '2018-07-16 10:00:19', '2018-07-16 10:00:19', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('14', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '投诉建议测试', null, '2018-07-16 10:20:21', '2018-07-16 10:20:21', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('15', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '投诉建议测试', null, '2018-07-16 10:20:39', '2018-07-16 10:20:39', null, null, null, 'N', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('16', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '风格更好发给', '', '2018-07-16 10:40:56', '2018-07-16 10:40:56', null, null, null, 'Y', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('17', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', 'gjhjhjh话就hi集合', '处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容处理内容', '2018-07-16 14:04:17', '2018-07-16 14:04:17', null, null, null, 'Y', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('18', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', null, null, '二二二团', null, '2018-07-17 10:37:38', '2018-07-17 10:37:38', null, null, null, 'N', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('19', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', null, null, '撒大声地', null, '2018-07-17 10:39:15', '2018-07-17 10:39:15', null, null, null, 'N', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('20', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', null, null, '的方法的广告费', 'rtr对方', '2018-07-17 10:48:20', '2018-07-17 10:48:20', null, null, null, 'N', '1', '标题');
INSERT INTO `complaint_suggestion` VALUES ('21', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '的股份冠福股份哈哈复合肥发过火发付付付付付付付发给发广告过过过或分隔符', '<p><img src=\"http://172.168.4.84/group1/M00/00/02/rKgEVFtRm-GARLa4AACLb-FA9og488.png\" style=\"width: 304.88px; height: 427px;\"></p><p>内容</p><p><iframe webkitallowfullscreen=\"\" mozallowfullscreen=\"\" allowfullscreen=\"\" frameborder=\"0\" height=\"310\" width=\"500\" src=\"http://v.qq.com/iframe/player.html?vid=z14274s1cwg&amp;amp;auto=0\" class=\"note-video-clip\"></iframe></p>', '2018-07-17 11:07:45', '2018-07-17 11:07:45', null, null, null, 'Y', '0', '标题');
INSERT INTO `complaint_suggestion` VALUES ('24', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '快递很慢', null, '2018-07-26 09:57:02', '2018-07-26 09:57:02', null, null, null, 'N', '0', '快递问题');
INSERT INTO `complaint_suggestion` VALUES ('25', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'BFDEB257-AC45-4158-89F4-B126945714FD', '2017.12.2293', 'B0AAE7F2-6743-4AB6-96FC-71D221563DD0', '4990', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '大文件测试', '18753700852', '很慢', null, '2018-07-26 09:59:16', '2018-07-26 09:59:16', null, null, null, 'N', '0', '快递');

-- ----------------------------
-- Table structure for cost_record
-- ----------------------------
DROP TABLE IF EXISTS `cost_record`;
CREATE TABLE `cost_record` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `cost_id` bigint(40) DEFAULT NULL COMMENT '物业缴费记录Id 对应物业费主键id',
  `payment` decimal(16,2) DEFAULT '0.00' COMMENT '缴费金额',
  `project_id` varchar(50) DEFAULT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `create_time` date DEFAULT NULL COMMENT '缴费时间',
  `del_flag` int(4) DEFAULT NULL COMMENT '删除标记 0 未删除 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='缴费记录';

-- ----------------------------
-- Records of cost_record
-- ----------------------------
INSERT INTO `cost_record` VALUES ('1', '1532335329151', '200.00', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', 'AAA', '2018-07-23', '0');
INSERT INTO `cost_record` VALUES ('2', '1532334818021', '500.00', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', 'AAA', '2018-06-15', '0');
INSERT INTO `cost_record` VALUES ('3', '1532328071885', '2000.00', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', 'AAA', '2018-08-10', '0');
INSERT INTO `cost_record` VALUES ('4', '1532328071885', '5000.00', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', 'AAA', '2018-08-31', '0');
INSERT INTO `cost_record` VALUES ('5', '1532328071885', '1000.00', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', 'AAA', '2018-09-13', '0');
INSERT INTO `cost_record` VALUES ('6', '1532335329152', '5000.00', '3850A167-D309-4582-BD76-0C3CAB4C008E', 'AAA', '2018-08-31', '0');
INSERT INTO `cost_record` VALUES ('7', '1532422761509', '1000.00', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', 'AAA', '2018-09-13', '0');

-- ----------------------------
-- Table structure for decorate_management
-- ----------------------------
DROP TABLE IF EXISTS `decorate_management`;
CREATE TABLE `decorate_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(50) DEFAULT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `building_id` varchar(50) DEFAULT NULL COMMENT '楼栋ID',
  `building_name` varchar(100) DEFAULT NULL COMMENT '楼栋名字',
  `room_id` varchar(50) DEFAULT NULL COMMENT '房间ID',
  `room_name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `resident_id` varchar(50) DEFAULT NULL COMMENT '业主ID',
  `resident_name` varchar(100) DEFAULT NULL COMMENT '业主姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '业主联系电话',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `complet_time` date DEFAULT NULL COMMENT '竣工时间',
  `construct_company` varchar(30) DEFAULT NULL COMMENT '施工方公司',
  `construct_manager` varchar(30) DEFAULT NULL COMMENT '施工负责人',
  `construct_contact` varchar(30) DEFAULT NULL COMMENT '施工方联系方式',
  `decorate_content` varchar(2000) DEFAULT NULL COMMENT '装修方案说明',
  `decorate_file` varchar(2000) DEFAULT NULL COMMENT '装修相关文件附件',
  `status` int(2) DEFAULT '0' COMMENT '是否审批 0 未审批 1 代表已审批',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表已删除',
  `file_name` varchar(500) DEFAULT NULL COMMENT '装修相关文件附件名称',
  `apply_time` date DEFAULT NULL COMMENT '申请时间',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `order_number` bigint(40) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='装修管理';

-- ----------------------------
-- Records of decorate_management
-- ----------------------------
INSERT INTO `decorate_management` VALUES ('1', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, null, null, '发广告', '1', '发给', '13855386270', '2018-07-25', '2018-07-19', 'gfgfg的对方', '对方', '13855386270', '是cvb gh房号', 'group1/M00/00/01/rKgEVFtCzoOAOSYxAASMVA50hxQ416.jpg', '1', '0', 'syj2.jpg', '2018-07-12', null, null);
INSERT INTO `decorate_management` VALUES ('2', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1E', null, '房号', '1', '人头狗', '13855386270', '2018-07-09', '2018-07-09', '分隔符', '电饭锅', '13855386270', '冠福股份哈哈', 'group1/M00/00/01/rKgEVFtC_PqAPJF7AAD_veMBJds848.jpg', '1', '0', 'syj.jpg', '2018-07-12', null, null);
INSERT INTO `decorate_management` VALUES ('3', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1H', null, '孤鸿寡鹄1', '1', '二个的', '13855386271', '2018-07-18', '2018-07-11', 'dffg个1', '规范的', '13855386271', '德国法国恢复好好1', 'group1/M00/00/01/rKgEVFtC_NqAHMT-AASMVA50hxQ283.jpg', '0', '0', 'syj2.jpg', '2018-07-11', null, null);
INSERT INTO `decorate_management` VALUES ('4', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, '1E', null, '低分段', '1', '东风风光', '13859235670', '2018-07-09', null, 'VB更换合格', '低分段', '13855386270', '班从v', '', '0', '0', '', '2018-06-27', null, null);
INSERT INTO `decorate_management` VALUES ('5', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, '1H', null, '对方1', '1', '梵蒂冈', '13855386270', '2018-07-09', null, '电饭锅', '广告费', '13855386270', '发过火发', 'group1/M00/00/01/rKgEVFtC3w6ACMMTAAD_veMBJds288.jpg', '1', '0', 'syj.jpg', '2018-07-28', null, null);
INSERT INTO `decorate_management` VALUES ('6', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1H', null, '对方2', '1', '事宜均', '15980765467', '2018-07-28', null, '规范', '低分段', '15980976789', '电饭锅', '', '0', '0', '', '2018-07-04', null, null);
INSERT INTO `decorate_management` VALUES ('7', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1E', null, '发广告', '1', '发给', '15980764279', '2018-07-12', null, '分隔符', '电放费', '13878900987', '分隔符', 'group1/M00/00/01/rKgEVFtG_meAaFOQAASMVA50hxQ903.jpg', '0', '0', 'syj2.jpg', '2018-07-18', null, null);
INSERT INTO `decorate_management` VALUES ('8', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1H', null, '发的', '1', '大概', '15980765678', '2018-07-12', '2018-08-01', '广告费', '对方', '13567890987', '发给', '', '1', '0', '', '2018-07-24', null, null);
INSERT INTO `decorate_management` VALUES ('9', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, '1H', null, '发广告', '1', '施舍', '13878900987', '2018-07-12', '2018-07-28', '规范合格', '发货共和国', '14678900987', '东风风光', 'group1/M00/00/01/rKgEVFtHAXaADflKAASMVA50hxQ650.jpg', '1', '0', 'syj2.jpg', '2018-07-27', null, null);
INSERT INTO `decorate_management` VALUES ('10', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '分隔符', '1', '电饭锅', '14678900987', '2018-07-12', null, '方法', '丰东股份', '13678900987', '法规规范和', '', '0', '0', '', '2018-07-25', null, null);
INSERT INTO `decorate_management` VALUES ('11', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '非官方个', '1', '非官方个', '15980764378', '2018-07-12', null, '分隔符', '覆盖广泛', '15980766789', '发给个', '', '0', '0', '', '2018-08-10', null, null);
INSERT INTO `decorate_management` VALUES ('12', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '规范', '1', '分隔符', '15980767890', '2018-07-12', null, '23', '发给', '13456789087', '分隔符', '', '0', '0', '', '2018-07-19', null, null);
INSERT INTO `decorate_management` VALUES ('13', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '大概', '1', '风格更好', '14567890098', '2018-07-12', null, '低分段', '东方饭店', '13456789087', '发挥好', '', '0', '0', '', '2018-07-18', null, null);
INSERT INTO `decorate_management` VALUES ('14', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '对方', '1', '苟富贵', '13456789098', '2018-07-12', null, '对方的发的', '对方', '13456789098', '东方饭店', '', '0', '0', '', '2018-07-13', null, null);
INSERT INTO `decorate_management` VALUES ('15', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '对方', '1', '苟富贵', '13456789098', '2018-07-13', null, '对方的发的', '对方', '13456789098', '东方饭店', '', '0', '0', '', '2018-07-13', null, null);
INSERT INTO `decorate_management` VALUES ('16', 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', 'ie9测2试22', null, null, null, '发的', '1', '分隔符', '13456789098', '2018-07-13', null, '东方饭店', '低分段', '13456789098', '对方', '', '0', '0', '', '2018-07-20', '德国法国', null);
INSERT INTO `decorate_management` VALUES ('17', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, null, null, '903', '1', '覆盖广泛', '13456789098', '2018-07-13', '2018-10-17', '中铁一局', '小施', '13456789098', '全部装修', 'group1/M00/00/01/rKgEVFtHC1-AL1A2AASMVA50hxQ933.jpg', '1', '0', 'syj2.jpg', '2018-08-15', '海沧万科城19栋 903', null);
INSERT INTO `decorate_management` VALUES ('18', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, null, null, '对方', '1', '小任', '15890987890', '2018-07-12', '2018-07-20', '发给', '发广告', '13456789087', '复合肥', 'group1/M00/00/01/rKgEVFtHHS-AewH6AASMVA50hxQ405.jpg', '1', '0', 'syj2.jpg', '2018-07-26', '虚词', '678909876');
INSERT INTO `decorate_management` VALUES ('19', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, null, '1', '风格更好', '13456789087', '2018-07-12', '2018-07-27', null, null, null, '辅导费', null, '0', '0', null, '2018-07-12', 'CVBS回家', '1531388904465');
INSERT INTO `decorate_management` VALUES ('20', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, null, '1', '风格更好', '13456789087', '2018-07-12', '2018-07-27', null, null, null, '辅导费', null, '0', '0', null, '2018-07-12', 'CVBS回家', '1531389027196');
INSERT INTO `decorate_management` VALUES ('21', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, null, '1', '风格更好', '13456789087', '2018-07-12', '2018-07-27', null, null, null, '辅导费', null, '0', '0', null, '2018-07-12', 'CVBS回家', '1531389067778');
INSERT INTO `decorate_management` VALUES ('22', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', null, null, null, '对方', '1', '对方', '15980764279', '2018-07-12', null, '费', '是的', '13456780987', '消息称', '', '0', '0', '', '2018-07-24', '不过', '36');
INSERT INTO `decorate_management` VALUES ('23', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, null, '1', '分隔符', '15980765467', '2018-07-13', '2018-07-13', null, null, null, '大锅饭', null, '0', '0', null, '2018-07-13', '苟富贵复合弓', '1531444606890');
INSERT INTO `decorate_management` VALUES ('24', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, null, '1', '分隔符风', '15980765467', '2018-07-13', '2019-07-09', null, null, null, '大锅饭变更规划共和国', null, '0', '0', null, '2018-07-17', '让他改变', '1531444717453');
INSERT INTO `decorate_management` VALUES ('25', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, null, null, null, '低分段', '1', '施以军', '15987900987', '2018-07-13', '2020-07-24', '电放费', '水电费', '15890987890', '要精装加上豪华装修', 'group1/M00/00/01/rKgEVFtIAGeAN9aBAASMVA50hxQ336.jpg', '1', '0', 'syj2.jpg', '2016-07-01', '万科金域华府', '1531445186215');
INSERT INTO `decorate_management` VALUES ('26', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '小施', '15678989087', '2018-07-13', '2020-08-13', null, null, null, '豪华检修', null, '0', '0', null, '2019-07-13', '金域华府19栋1202', '1531469966914');
INSERT INTO `decorate_management` VALUES ('27', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '小施', '15678989087', '2018-07-13', '2018-07-13', null, null, null, '豪华检修234', null, '1', '0', null, '2018-07-13', '金域华府19栋1202', '1531470234569');
INSERT INTO `decorate_management` VALUES ('28', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, '802', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '李大大', '13556789087', '2018-07-13', '2018-07-11', '厦门万科', '王石', '15678909876', '电饭锅法规规范和和规划共和国工会和共和国风高放火湖广会馆发广告法规发个非官方个方法更好回复回复房号发个非官方个', 'group1/M00/00/01/rKgEVFtIYw-AXJBoAASMVA50hxQ361.jpg', '0', '0', 'syj2.jpg', '2018-07-13', '超过', '1531470290042');
INSERT INTO `decorate_management` VALUES ('29', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '苏伟', '15980764209', '2018-07-18', '2018-07-18', null, null, null, '1234', null, '0', '0', null, '2018-07-18', '地址', '1531900748250');
INSERT INTO `decorate_management` VALUES ('30', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境', null, '2017.12.849', null, '1802', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '小施', '13978980987', '2018-07-19', '2018-07-28', '厦门万科', '王石', '15789099876', '必定需要按照合格的要求就装修，必须慎重', 'group1/M00/00/02/rKgEVFtQUf2ABUZKAASMVA50hxQ543.jpg', '1', '0', 'syj2.jpg', '2018-07-20', '福建省厦门市海沧区海沧万科城18栋1802', '3456789');
INSERT INTO `decorate_management` VALUES ('31', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '电放费', '13456789087', '2018-07-19', '2018-07-19', null, null, null, '辅导费', null, '0', '0', null, '2018-07-19', '电饭锅', '1531990895943');
INSERT INTO `decorate_management` VALUES ('32', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, null, null, null, null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '小习', '13855386270', '2018-07-24', '2018-08-30', '厦门万科', '王石', '13789099876', '房间需要报修', 'group1/M00/00/02/rKgEVFtW4PCACsuaAAyvPJ83YaY620.png', '0', '0', 'b4557ac95bfaf52da6336368ee6aa210_01c53f5567f0930000016756edc878.jpg@1280w_1l_2o_100sh.png', '2018-07-25', '北京中南海', '1532419259016');

-- ----------------------------
-- Table structure for guide_affairs
-- ----------------------------
DROP TABLE IF EXISTS `guide_affairs`;
CREATE TABLE `guide_affairs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '办事标题',
  `type` varchar(50) NOT NULL COMMENT '办事类别',
  `content` varchar(2000) NOT NULL COMMENT '详细内容',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `project_id` varchar(400) DEFAULT NULL COMMENT '项目ID',
  `status` int(2) DEFAULT '1' COMMENT '默认0 发布  1 代表未发布',
  `del_flag` int(2) DEFAULT '0' COMMENT '默认0 代表未删除 1 代表已删除',
  `project_name` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='办事指南';

-- ----------------------------
-- Records of guide_affairs
-- ----------------------------
INSERT INTO `guide_affairs` VALUES ('1', '政务办理', '政府办理', '办理房产过户信息', '2018-07-02 10:02:16', '2018-07-03 10:02:21', 'admin', '2018-07-02 10:02:33', 'admin', '1', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('2', '政务办理1', '消费', '办理房产过户信息1', '2018-07-02 10:02:16', '2018-07-03 10:02:21', 'admin', '2018-07-02 10:02:33', 'admin', '2', '1', '1', null);
INSERT INTO `guide_affairs` VALUES ('3', '政务办理2', '政府水电费', '办理房产过户信息2', '2018-07-02 10:02:16', '2018-07-03 10:02:21', 'admin', '2018-07-02 10:02:33', 'admin', '3', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('4', '政务办理3', '舒服服', '办理房产过户信息3', '2018-07-02 10:02:16', '2018-07-03 10:02:21', 'admin', '2018-07-02 10:02:33', 'admin', '4', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('26', '67', '2', '很高', null, null, 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('27', '67', '2', '很高', null, null, 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('28', '风格更好', '2', '打工', null, null, 'admin', null, null, '7631DC55-E164-4378-A9DD-D9011CD345E0', '1', '1', null);
INSERT INTO `guide_affairs` VALUES ('29', '过户', '2', '刚刚', null, null, 'admin', null, null, '227A4092-A60E-4C3B-99EF-B2114527E62D', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('30', '感觉好', '1', '哈哈', null, null, 'admin', null, null, 'BED4E494-0775-45F9-971C-74AE6F470E35', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('31', '感觉好', '1', '哈哈', null, null, 'admin', null, null, 'BED4E494-0775-45F9-971C-74AE6F470E35', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('32', '感觉', '2', '过户', null, null, 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('33', '感觉', '2', '过户', null, null, 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('34', '过户', '2', '符合规划', null, null, 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('35', '过户', '2', '符合规划', null, null, 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('36', '过户', '2', '符合规划', null, null, 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('37', '发给', '2', '发发', null, null, 'admin', null, null, 'D386E319-E0D4-4987-AB26-D1B840294CE9', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('38', '发给', '2', '东风股份和发发', '2018-07-03 17:15:13', null, 'admin', null, null, '930031D0-803A-4804-8F30-E2EB033297EA', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('40', '打工', '2', '的广告费', '2018-07-04 14:25:42', '2018-07-03 17:35:06', 'admin', null, null, '754BD838-A33A-4BAD-9ADE-3F55D2BE3E6B', '1', '1', null);
INSERT INTO `guide_affairs` VALUES ('41', '打工', '2', '地方郭德纲', '2018-07-05 14:00:27', '2018-07-03 17:36:58', 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '1', '1', null);
INSERT INTO `guide_affairs` VALUES ('42', '规范合格', '2', '东风股份和很高很高', '2018-07-03 17:55:47', '2018-07-03 17:58:08', 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('44', '复古风格', '政务办理', '的覆盖广泛', '2018-07-04 18:55:05', '2018-07-03 18:59:25', 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('45', '11地方', '政策通知', '11发布VB', '2018-07-06 18:05:55', '2018-07-03 19:03:12', 'admin', null, null, '人间_A项目', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('46', '的方法更过分过分法规法规规范23', '政策通知', '地方法规规范复古风格22', '2018-07-05 18:30:41', '2018-07-03 21:50:06', 'admin', null, null, '水郡湖', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('47', 'gh', '政策通知', 'fggf', '2018-07-05 18:50:30', '2018-07-04 21:50:49', 'admin', null, null, '大文件上传测试', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('48', 'bvb', '政策通知', 'dfggf', '2018-07-04 13:25:51', '2018-07-04 21:56:05', 'admin', null, null, '大文件上传测试', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('49', 'hgh刚刚', '政策通知', '规划', '2018-07-04 13:25:25', '2018-07-05 01:44:42', 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('50', '不给发货吧', '政策通知', '规划', '2018-07-05 14:30:59', '2018-07-05 01:47:18', 'admin', null, null, '425F1A0D-31FD-47D7-9917-12F91550E0BD', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('51', '刚刚', '政务办理', '还好', '2018-07-04 18:50:06', '2018-07-05 01:52:29', 'admin', null, null, '425F1A0D-31FD-47D7-9917-12F91550E0BD', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('52', 'vvc', '政策通知', '成本', '2018-07-05 01:50:31', '2018-07-05 01:53:44', 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('53', '风格更好', '政策通知', '法国恢复', '2018-07-04 13:25:26', '2018-07-05 02:01:07', 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('54', '地方', '政策通知', '发给', '2018-07-04 13:25:18', '2018-07-05 02:03:28', 'admin', null, null, 'C94EFEBE-9828-45EE-8C60-961879C5CA62', '0', '1', '室内分机测试项目');
INSERT INTO `guide_affairs` VALUES ('55', '发给', '政策通知', '共和国和', '2018-07-02 12:00:05', '2018-07-05 02:04:19', 'admin', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', '大文件上传测试');
INSERT INTO `guide_affairs` VALUES ('56', '德国', '政策通知', '的非官方', '2018-07-03 13:25:20', '2018-07-05 02:16:31', 'admin', null, null, '425F1A0D-31FD-47D7-9917-12F91550E0BD', '0', '1', '人间_A项目');
INSERT INTO `guide_affairs` VALUES ('57', '建议低分段', '食堂', '地方过分过分电饭锅', '2018-07-05 14:30:40', '2018-07-07 00:13:23', 'liufp', null, null, null, '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('58', '建议低分段', '食堂', '地方过分过分电饭锅', '2018-07-05 14:30:40', '2018-07-07 00:13:35', 'liufp', null, null, null, '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('59', '共和国', '食堂', '苟富贵发给', '2018-06-25 08:05:02', '2018-07-07 00:14:21', 'liufp', null, null, null, '0', '1', null);
INSERT INTO `guide_affairs` VALUES ('60', '非共和国11', '政策通知', '风格更好111', '2018-07-04 17:25:03', '2018-07-07 01:00:14', 'liufp', null, null, '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '东方别墅');
INSERT INTO `guide_affairs` VALUES ('61', '冠福股份11', '政务办理', '广告费11', '2018-07-05 18:50:19', '2018-07-07 01:00:28', 'liufp', null, null, '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '东方别墅');
INSERT INTO `guide_affairs` VALUES ('62', '发给', '政务办理', '覆盖广泛', '2018-07-03 17:25:43', '2018-07-07 01:04:03', 'liufp', null, null, 'FA042CD9-106A-4A02-81E2-01BC8119DAD8', '0', '0', 'ie9测2试22');
INSERT INTO `guide_affairs` VALUES ('63', '政府人才补贴', '政策通知', '只要是硕士毕业，在厦门有社保，可以申请三万补贴', '2018-07-16 08:00:52', '2018-07-15 23:50:39', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('64', '身份证异地可以补办', '政务办理', '为了方便群众，此后身份证可以异地补办啦', '2018-07-17 09:25:23', '2018-07-16 01:20:07', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('65', '风格更好', '政策通知', '发广告规范合格', '2018-07-10 17:25:08', '2018-07-16 01:23:19', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('66', '任天野', '政策通知', '发个方法很符合孤鸿寡鹄更好共和国', '2018-07-03 13:25:21', '2018-07-16 01:23:32', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('67', '复合弓', '政策通知', '都会更好', '2018-07-11 17:25:22', '2018-07-16 17:02:35', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('68', '计划经济2', '政务办理', '为了扩大生产，我们需要计划经济2', '2018-07-17 17:25:45', '2018-07-17 00:28:19', 'syj', null, null, 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1', '0', 'AAA');
INSERT INTO `guide_affairs` VALUES ('69', '二胎政策', '政策通知', '没生一胎奖励五千', '2018-07-20 14:35:32', '2018-07-19 14:37:03', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('70', '三胎证券', '政策通知', '水电费', '2018-07-10 09:45:56', '2018-07-19 14:38:23', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('71', '出国留学23', '政务办理', '出国留学回来又欧皇第三方23', '2018-07-19 18:50:30', '2018-07-19 18:45:19', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('72', '电放费', '政策通知', '东方饭店', '2018-07-19 18:50:20', '2018-07-19 18:45:41', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('73', '大锅饭', '政策通知', '', '2018-07-20 17:01:24', '2018-07-20 16:57:38', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('74', '对方', '政策通知', '', '2018-07-20 17:01:40', '2018-07-20 17:01:40', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('75', '对方', '政策通知', '', '2018-07-20 17:04:08', '2018-07-20 17:04:08', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('76', '对方', '政策通知', '', '2018-07-20 17:05:18', '2018-07-20 17:05:18', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('77', '的非官方', '政务办理', '<p>的广告费规范地方地方郭德纲非官方个韩国</p>', '2018-07-20 17:09:07', '2018-07-20 17:08:57', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '测试开发环境');
INSERT INTO `guide_affairs` VALUES ('78', 'fg', '政策通知', '<p>df</p>', '2018-07-21 09:11:17', '2018-07-21 09:10:59', 'syj', null, null, 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', '测试开发环境');

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '公告标题',
  `content` text COMMENT '公告内容',
  `attachment_name` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `attachment` varchar(200) DEFAULT NULL COMMENT '附件',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `type` varchar(10) DEFAULT NULL COMMENT '发布类别',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(11) DEFAULT NULL COMMENT '更新人',
  `project_id` varchar(40) DEFAULT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：Y发布 N未发布',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='社区公告';

-- ----------------------------
-- Records of notify
-- ----------------------------
INSERT INTO `notify` VALUES ('78', 'ceshi', '<p><b>测试</b><img src=\"http://172.168.4.84/group1/M00/00/02/rKgEVFtRVO6AM1eCAABCCz29bhA817.jpg\" style=\"width: 326px;\">233333333333333</p>', 't019ffe68eb65f668ae.jpg', 'group1/M00/00/02/rKgEVFtRVPSAVOXIAABCCz29bhA184.jpg', '2018-07-20 11:19:09', '3', '2018-07-20 11:19:09', '22', '2018-07-25 16:30:05', '33', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'Y', '0');
INSERT INTO `notify` VALUES ('79', '测试', '<p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">[外交部：中方为避免贸易摩擦升级尽了最大努力]新华社北京7月19日电（记者闫子敏）针对有美官员称美中未能达成解决贸易分歧协议的责任在中国，外交部发言人华春莹19日表示，中方一直在以最大诚意和耐心推动双方通过对话协商解决分歧，为避免贸易摩擦升级尽了最大努力。美方有关官员如此颠倒黑白、倒打一耙，超出了一般人的想象，令人感到震惊。</p><p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">　　当日例行记者会上，有记者问：有报道称，美国白宫国家经济委员会主任库德洛称，美中未能达成解决贸易分歧的协议，责任在中国。现在球在中国一边，只要中方拿出令人满意的方案，美方可以停止对中国商品加征关税。中方对此有何评论？</p><div id=\"ad_44086\" class=\"otherContent_01\" style=\"margin: 10px 20px 10px 0px; padding: 4px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px; float: left; overflow: hidden; clear: both; width: 300px; height: 250px;\"><ins class=\"sinaads sinaads-done\" id=\"Sinads49447\" data-ad-pdps=\"PDPS000000044086\" data-ad-status=\"done\" style=\"display: block; overflow: hidden;\"><ins style=\"margin: 0px auto; width: 300px; display: block; position: relative; overflow: hidden;\"><a href=\"http://saxn.sina.com.cn/dsp/click?t=MjAxOC0wNy0yMCAxMToyODo0Ni4yODgJMjcuMTUyLjE0My4xOTIJMTE3LjI5LjE2MC4xNDZfMTUzMjA1NzMyMy40MTQyMTgJNTU5MGUwNTEtZTUwNi00NTU5LTgwYmItYTk2MzQ0NDE0M2QyCTMyNTM0NTQJNjE4NDQ5MDQ0NV9QSU5QQUktQ1BDCTMxMTkxNDYJNTQ1MjIJOC42MjA2ODk2RS00CTEJdHJ1ZQlQRFBTMDAwMDAwMDQ0MDg2CTM1Mzc3MTAJUEMJaW1hZ2UJLQkwfDFDTFcyTldTWjFsZklHUFZEeUkyWER8bnVsbHxudWxsfGJqfDMyNTM0NTR8Nm1zZjlienA5QlU1Q09UYjVna1B4WHwwfHx8bm9ybWFsfEMJbnVsbAkxCS0JLQktCTAJMTE3LjI5LjE2MC4xNDZfMTUzMjA1NzMyMy40MTQyMTgJUENfSU1BR0UJLQlub3JtYWx8dXZmbS1ydAktCXVzZXJfdGFnOjIxMjc1OjAuMjg1MTU2Mjh8dXNlcl9hZ2U6NjAzOjAuMHx1c2VyX2dlbmRlcjo1MDI6MC4wfHZfem9uZTozMDQwMDI6MC4wfGNyb3dkczp8X2Nyb3dkczoJMAk1ODAwMAk1MDAwMAktCS0JMA==&amp;userid=117.29.160.146_1532057323.414218&amp;auth=8b6beffa442320a3&amp;p=J5%2FhU9SmPLqLalEBl20AVRdf8mSLfGJCh%2BvxQQ%3D%3D&amp;url=http%3A%2F%2Fsax.sina.com.cn%2Fclick%3Ftype%3D2%26t%3DMjc5ZmUxNTMtZDRhNi0zY2JhLThiNmEtNTEwMTk3NmQwMDU1CTE3CVBEUFMwMDAwMDAwNDQwODYJMzUzNzcxMAkxCVJUQgktCQk%253D%26id%3D17%26url%3Dhttp%253A%252F%252Fwww.yhzqjs.com%252FHZ07Gpc%26sina_sign%3D67704c71691b6352&amp;sign=e1597b3f4bec4862\" target=\"_blank\" data-ss=\"2684ceb4f3b7951e=ngis&amp;2536b19617c40776D3%ngis_anis62%cpG70ZHF252%moc.sjqzhy.wwwF252%F252%A352%ptthD3%lru62%71D3%di62%D352%kQCtkgQUJVCxkAMxczNzUzMJYDOwQDNwADMwADMwMFUEBVC3ETC1UDMwQmN3kTMwETNtEmNihTLhJ2Yz0iNhRDZtMTNxUmZ5cjMD3%t62%2D3%epytF3%kcilcF2%nc.moc.anis.xasF2%F2%A3%ptth=lru&amp;D3%D3%QQxvB2%hCJGfLSm8fdRVA02lBElaLqLPmS9UhF2%5J=p&amp;3a023244affeb6b8=htua&amp;812414.3237502351_641.061.92.711=diresu&amp;==AMJ0SCtkAMwADM1kAMwADO1kAMJozckd3byN2X8pzckd3byNGfw4CM6IDMwQDMzoTZu9mefZHfw4CM6IDM1ojclRmbld2XyV2c1xHMuAjOzAjN6U2Zh9lclNXd8hjM2UTM1gjMuAjO1cjMxIjOnFGdfJXZzVXCtkAdy1SbmZXd8xWYtJ3bulQLJU0RB1USfNEUJgTMyQTM04yMyMzN1AjMzUTMfZDNx4CM2EjL5IjL3ETMJATCtkQLJ0SCxkAbsVnbJMEfsFWby9mb8xHfwwHW4B1anVjYU90Q1UlQ5AneiljZz1mN8RTN0MTNyMDfqJGfsxWduxHbsVnb8REWykUeEZFUHlkZsFjWTdlTycFTDFDfwkQLJU2Zh1WaJMEUJATM3czM1MTC2gDM0QDMwADMwADMTBFRQlQZ1JHdJETC00SR2kDO2AjM24COJIjM1QTNJYDNxkTMxMTCDB1QtkUQQ5USQ9VN0QDM5QDN4EjNJQTN0MTNyMTCyQ2M0EDN0QzM2kTYtImYwgTL5UTN00iNwUTZtETNwUGM5UTNJgTMyQTM04yMyMzN1AjMzUTMfZDNx4CM2EjL5IjL3ETMJITOx4yM0EjLyUTMucjMJgDOy4iN0oDOyoTMxACMy0yNw0COxAjM=t?kcilc/psd/nc.moc.anis.nxas//:ptth\" style=\"margin: 0px; padding: 0px; color: rgb(51, 51, 51); outline: none; display: block; line-height: 0;\"><img border=\"0\" src=\"http://s3.pfp.sina.net/ea/ad/5/4/3567983a6bacbee9bdad2132444d1319.jpg\" alt=\"//s3.pfp.sina.net/ea/ad/5/4/3567983a6bacbee9bdad2132444d1319.jpg\" style=\"margin: 0px; padding: 0px; vertical-align: top; width: 300px; height: 250px;\"></a><div style=\"margin: 0px; padding: 0px; width: 26px; height: 13px; position: absolute; right: 1px; bottom: 1px; z-index: 99; background: url(&quot;//d2.sina.com.cn/litong/zhitou/sinaads/release/ad_logo_update_IAB.gif&quot;) no-repeat;\"></div></ins></ins></div><p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">　　华春莹说，中国商务部7月12日声明已经完整还原了中美就经贸问题进行接触磋商的全过程，中方一直在以最大诚意和耐心推动双方通过对话协商解决分歧，为避免贸易摩擦升级尽了最大努力。全世界关注中美经贸关系的人也都通过媒体全程跟踪了事态发展的整个过程。</p><p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">　　“当着全世界人民的面，美方有关官员如此颠倒黑白、倒打一耙，令人感到震惊。”她说，真的假不了，假的真不了。美方行为只能再次严重损害自身信誉，也完全无益于解决问题。</p><p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">　　华春莹表示，中方在中美经贸摩擦问题上的立场和态度是一贯和明确的。从一开始，中方就表明了“不想打、也不怕打”的坚定立场。美方高举大棒，胁迫讹诈，蛮横无理，反复无常，出尔反尔，言而无信，是导致事态发展升级的直接和根本原因。美方应该清醒认识到，这是经济全球化深入发展的21世纪，这次它面对的是中国。“美方某些人不应再沉迷于做17世纪的堂吉诃德。”</p><p style=\"margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: &quot;Microsoft Yahei&quot;, &quot;\\\\5FAE软雅黑&quot;, &quot;STHeiti Light&quot;, &quot;\\\\534E文细黑&quot;, SimSun, &quot;\\\\5B8B体&quot;, Arial, sans-serif; font-size: 18px; letter-spacing: 1px;\">　　“我们有信心、有底气、有条件，也有足够的能力，维护自身正当合法利益，同世界各国一道维护国际规则和多边贸易体系。”华春莹说。</p>', 't019ffe68eb65f668ae.jpg', 'group1/M00/00/02/rKgEVFtRVw2AU087AABCCz29bhA228.jpg', '2018-07-20 11:28:06', '3', '2018-07-20 11:28:06', '22', '2018-07-20 11:28:06', '22', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', 'Y', '0');
INSERT INTO `notify` VALUES ('80', '对方', '<p>是的第三方对方的答复</p>', '', '', '2018-07-20 17:03:17', '3', '2018-07-20 17:03:17', '34', '2018-07-20 17:03:17', '34', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境', 'Y', '0');
INSERT INTO `notify` VALUES ('81', 'df', '<p><br></p>', '', '', '2018-07-20 17:05:32', '3', '2018-07-20 17:05:32', '34', '2018-07-20 17:05:32', '34', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境', 'Y', '0');

-- ----------------------------
-- Table structure for notify_detail
-- ----------------------------
DROP TABLE IF EXISTS `notify_detail`;
CREATE TABLE `notify_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `notify_id` bigint(11) DEFAULT NULL COMMENT '公告社区表id',
  `build_id` varchar(40) DEFAULT NULL COMMENT '楼栋ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='社区公告明细';

-- ----------------------------
-- Records of notify_detail
-- ----------------------------
INSERT INTO `notify_detail` VALUES ('5', '60', '247F3650-E239-4CB9-A890-84B671789F0B');
INSERT INTO `notify_detail` VALUES ('6', '61', '247F3650-E239-4CB9-A890-84B671789F0B');
INSERT INTO `notify_detail` VALUES ('7', '61', '933C0BD9-E76C-4925-A09E-AE92F68768BC');
INSERT INTO `notify_detail` VALUES ('15', '58', '247F3650-E239-4CB9-A890-84B671789F0B');
INSERT INTO `notify_detail` VALUES ('16', '58', '933C0BD9-E76C-4925-A09E-AE92F68768BC');
INSERT INTO `notify_detail` VALUES ('24', '62', '247F3650-E239-4CB9-A890-84B671789F0B');
INSERT INTO `notify_detail` VALUES ('25', '57', '247F3650-E239-4CB9-A890-84B671789F0B');
INSERT INTO `notify_detail` VALUES ('26', '57', '933C0BD9-E76C-4925-A09E-AE92F68768BC');
INSERT INTO `notify_detail` VALUES ('28', '64', '46C4DA5F-408E-4505-9878-ABCCD98FA693');
INSERT INTO `notify_detail` VALUES ('29', '64', '4CA9109B-88C2-480B-BACA-F6C86BEAD030');
INSERT INTO `notify_detail` VALUES ('30', '64', 'A3A13657-620E-4DBB-A4CC-1337C68FA289');
INSERT INTO `notify_detail` VALUES ('59', '63', '5F7A2B15-D991-45E9-8065-00040CA005E9');
INSERT INTO `notify_detail` VALUES ('60', '63', 'A7657433-B686-4B8F-BD14-18627866AD79');
INSERT INTO `notify_detail` VALUES ('61', '63', '3ABBF9C2-A147-4DAF-A87E-C66BE2388DE2');
INSERT INTO `notify_detail` VALUES ('62', '63', 'B38B5799-CD61-45C2-B139-5D88CD98BA74');
INSERT INTO `notify_detail` VALUES ('63', '63', '87238703-533F-4B37-8A95-3FFE89945DC1');
INSERT INTO `notify_detail` VALUES ('64', '63', 'A3A13657-620E-4DBB-A4CC-1337C68FA289');
INSERT INTO `notify_detail` VALUES ('65', '63', '4CA9109B-88C2-480B-BACA-F6C86BEAD030');
INSERT INTO `notify_detail` VALUES ('66', '63', 'D3415676-073D-4AE3-A680-57FE2B159E11');
INSERT INTO `notify_detail` VALUES ('67', '63', '46C4DA5F-408E-4505-9878-ABCCD98FA693');
INSERT INTO `notify_detail` VALUES ('75', '66', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('77', '65', 'B65EBAD6-24EE-442C-8811-E4D33B580F7B');
INSERT INTO `notify_detail` VALUES ('78', '67', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('79', '68', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390');
INSERT INTO `notify_detail` VALUES ('82', '69', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('83', '69', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390');
INSERT INTO `notify_detail` VALUES ('84', '71', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('85', '72', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('86', '72', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390');
INSERT INTO `notify_detail` VALUES ('87', '73', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390');
INSERT INTO `notify_detail` VALUES ('88', '74', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('89', '75', 'A7657433-B686-4B8F-BD14-18627866AD79');
INSERT INTO `notify_detail` VALUES ('90', '76', 'D5F1E218-9729-4F1B-A5B0-00ACBCFA0A4D');
INSERT INTO `notify_detail` VALUES ('91', '76', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390');
INSERT INTO `notify_detail` VALUES ('92', '77', '3ABBF9C2-A147-4DAF-A87E-C66BE2388DE2');
INSERT INTO `notify_detail` VALUES ('97', '79', 'B38B5799-CD61-45C2-B139-5D88CD98BA74');
INSERT INTO `notify_detail` VALUES ('98', '79', '87238703-533F-4B37-8A95-3FFE89945DC1');
INSERT INTO `notify_detail` VALUES ('99', '80', 'A7657433-B686-4B8F-BD14-18627866AD79');
INSERT INTO `notify_detail` VALUES ('100', '81', 'A7657433-B686-4B8F-BD14-18627866AD79');
INSERT INTO `notify_detail` VALUES ('101', '78', '3ABBF9C2-A147-4DAF-A87E-C66BE2388DE2');
INSERT INTO `notify_detail` VALUES ('102', '78', 'B38B5799-CD61-45C2-B139-5D88CD98BA74');

-- ----------------------------
-- Table structure for property_cost
-- ----------------------------
DROP TABLE IF EXISTS `property_cost`;
CREATE TABLE `property_cost` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(50) NOT NULL COMMENT '项目ID',
  `cost_id` bigint(40) DEFAULT NULL COMMENT '费用Id',
  `room_number` varchar(100) DEFAULT NULL COMMENT '房间号',
  `payment` decimal(16,2) DEFAULT '0.00' COMMENT '应缴金额',
  `month` date DEFAULT NULL COMMENT '开始时间',
  `status` int(2) DEFAULT '0' COMMENT '0 代表未缴费  1 代表已缴费',
  `del_flag` int(2) DEFAULT '0' COMMENT '0 代表未删除 1 代表已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `resident_name` varchar(30) DEFAULT NULL COMMENT '业主姓名',
  `resident_id` varchar(50) DEFAULT NULL COMMENT '业主id',
  `type` varchar(20) DEFAULT NULL COMMENT '费用类型',
  `building_id` varchar(50) DEFAULT NULL COMMENT '楼栋',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_001` (`cost_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='物业费用表';

-- ----------------------------
-- Records of property_cost
-- ----------------------------
INSERT INTO `property_cost` VALUES ('1', '3850A167-D309-4582-BD76-0C3CAB4C008E', '561', '太阳花67', '56.68', '2018-07-11', '0', '0', '2018-07-08 21:43:06', '东方别墅', null, '0', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('2', '3850A167-D309-4582-BD76-0C3CAB4C008E', '7899', '霍元甲', '678.00', '2018-07-19', '0', '0', '2018-07-08 21:43:50', '东方别墅', null, '0', '电费', null, null);
INSERT INTO `property_cost` VALUES ('3', '3850A167-D309-4582-BD76-0C3CAB4C008E', '564', '45', '5644.69', '2018-07-21', '1', '0', '2018-07-08 21:44:06', '东方别墅', null, '1', '水费', null, null);
INSERT INTO `property_cost` VALUES ('4', '3850A167-D309-4582-BD76-0C3CAB4C008E', '77', '66', '1000.00', '2018-05-10', '0', '0', '2018-07-08 21:45:01', '东方别墅', null, '1', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('5', '3850A167-D309-4582-BD76-0C3CAB4C008E', '67', '567', '678.00', '2018-10-20', '0', '0', '2018-07-08 22:15:44', '东方别墅', null, '1', '电费', null, null);
INSERT INTO `property_cost` VALUES ('6', '3850A167-D309-4582-BD76-0C3CAB4C008E', '56789', '457', '10000.00', '2018-10-09', '1', '0', '2018-07-08 22:38:02', '东方别墅', null, '1', '水费', null, null);
INSERT INTO `property_cost` VALUES ('7', '3850A167-D309-4582-BD76-0C3CAB4C008E', '12345678', '345', '456.00', '2018-08-07', '0', '0', '2018-07-12 18:13:25', '东方别墅', null, '1', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('8', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1598066', '56', '200.00', '2018-10-12', '0', '0', '2018-07-12 18:35:38', '东方别墅', null, '1', '电费', null, null);
INSERT INTO `property_cost` VALUES ('10', '3850A167-D309-4582-BD76-0C3CAB4C008E', '5678956', '678', '400.00', '2018-07-28', '0', '0', '2018-07-12 18:36:05', '东方别墅', null, '1', '水费', null, null);
INSERT INTO `property_cost` VALUES ('11', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1532335329152', '222', '200.00', '2017-05-11', '0', '0', '2018-07-12 20:23:29', '东方别墅', '的广泛覆盖', '1', '物业费', '', null);
INSERT INTO `property_cost` VALUES ('12', '3850A167-D309-4582-BD76-0C3CAB4C008E', '221', '221', '221.00', '2018-06-12', '0', '0', '2018-07-12 20:23:51', '东方别墅', null, '1', '电费', null, null);
INSERT INTO `property_cost` VALUES ('13', '3850A167-D309-4582-BD76-0C3CAB4C008E', '223', '223', '223.00', '2018-06-12', '0', '0', '2018-07-12 20:24:07', '东方别墅', null, '1', '水费', null, null);
INSERT INTO `property_cost` VALUES ('14', '3850A167-D309-4582-BD76-0C3CAB4C008E', '22211', '2221', '200.00', '2017-05-11', '1', '0', '2018-07-12 20:23:29', '东方别墅', '', '1', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('15', '3850A167-D309-4582-BD76-0C3CAB4C008E', '22111', '2211', '221.00', '2018-06-12', '1', '0', '2018-07-12 20:23:51', '东方别墅', '', '1', '电费', null, null);
INSERT INTO `property_cost` VALUES ('16', '3850A167-D309-4582-BD76-0C3CAB4C008E', '22131', '2231', '223.00', '2018-06-12', '1', '0', '2018-07-12 20:24:07', '东方别墅', '', '1', '水费', null, null);
INSERT INTO `property_cost` VALUES ('17', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '5656', '802', '2000.00', '2018-07-26', '0', '0', '2018-07-14 00:34:06', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('18', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '5657', '801', '1000.00', '2018-06-13', '0', '0', '2018-07-14 00:34:37', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '物业费', null, null);
INSERT INTO `property_cost` VALUES ('19', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '5658', '800', '3000.00', '2018-08-13', '1', '0', '2018-07-14 00:41:01', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '电费', null, null);
INSERT INTO `property_cost` VALUES ('20', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '5659', '8000', '4000.00', '2018-01-13', '0', '0', '2018-07-14 00:41:35', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '电费', null, null);
INSERT INTO `property_cost` VALUES ('22', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '5660', '799', '5000.00', '2018-07-21', '0', '1', '2018-07-14 00:48:29', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '水费', null, null);
INSERT INTO `property_cost` VALUES ('23', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '567866', '799', '4000.00', '2017-07-04', '0', '1', '2018-07-14 00:48:51', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '水费', null, null);
INSERT INTO `property_cost` VALUES ('24', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '56', '802', '5000.00', '2018-07-23', '0', '1', '2018-07-23 09:42:48', '测试开发环境', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '电费', null, null);
INSERT INTO `property_cost` VALUES ('25', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1532327642312', '2703', '800.00', '2018-07-24', '0', '0', '2018-07-23 14:34:02', 'AAA', 'xxx', '5593A82D-0B9A-4551-B01B-8328A78A4470', '电费', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390', '1875369856511');
INSERT INTO `property_cost` VALUES ('26', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1532327670923', '2703', '1000.00', '2018-07-27', '0', '0', '2018-07-23 14:34:30', 'AAA', 'xxx', '5593A82D-0B9A-4551-B01B-8328A78A4470', '物业费', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390', '1875369856511');
INSERT INTO `property_cost` VALUES ('27', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1532327705258', '3270', '2000.00', '2018-08-01', '0', '0', '2018-07-23 14:35:05', '测试开发环境', '大文件测试', '3CCE81A8-D768-4972-8B3E-0A076DC3C085', '电费', '1BD41C9F-16EC-4EC7-99B9-0023BB5A27C4', '18753699132');
INSERT INTO `property_cost` VALUES ('28', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1532328071885', '5678', '8000.00', '2018-08-24', '0', '0', '2018-07-23 14:41:11', 'AAA', '挥', 'F204F481-97EB-4FA3-84B5-009A895C75A2', '电费', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390', '13888883965');
INSERT INTO `property_cost` VALUES ('29', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1532334818021', '67', '500.00', '2018-06-14', '0', '0', '2018-07-23 16:33:38', 'AAA', '挥', 'F204F481-97EB-4FA3-84B5-009A895C75A2', '电费', 'D8BA3E7D-A6AC-4FF1-AAD5-32A97617C390', '13888883965');
INSERT INTO `property_cost` VALUES ('30', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1532335329151', '2703', '200.00', '2017-07-01', '0', '0', '2018-07-23 16:42:09', 'AAA', 'xxx', '5593A82D-0B9A-4551-B01B-8328A78A4470', '水费', 'D8BA3E7D-A6AC-4FF1-AAD5-323A97617C390', '1875369856511');
INSERT INTO `property_cost` VALUES ('31', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1532422761509', '2846', '20000.00', '2018-07-26', '0', '0', '2018-07-24 16:59:21', '测试开发环境', '大文件测试', '12D4541F-9886-4248-98DD-08C9516765E4', '水费', '5F7A2B15-D991-45E9-8065-00040CA005E9', '18753698708');

-- ----------------------------
-- Table structure for property_repair
-- ----------------------------
DROP TABLE IF EXISTS `property_repair`;
CREATE TABLE `property_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(100) NOT NULL COMMENT '报修单号',
  `type` varchar(50) NOT NULL COMMENT '报修类别',
  `repair_owner` varchar(200) NOT NULL COMMENT '报修业主',
  `phone` varchar(40) NOT NULL COMMENT '手机号',
  `content` varchar(2000) NOT NULL COMMENT '报修内容',
  `release_time` datetime DEFAULT NULL COMMENT '预约时间',
  `create_time` datetime DEFAULT NULL COMMENT '报修时间',
  `address` varchar(400) DEFAULT NULL COMMENT '地址',
  `project_id` varchar(400) DEFAULT NULL COMMENT '项目ID',
  `status` int(2) DEFAULT '0' COMMENT '默认0 未处理  1 已处理',
  `del_flag` int(2) DEFAULT '0' COMMENT '默认0 代表未删除 1 代表已删除',
  `service_status` int(2) DEFAULT '0' COMMENT '是否已退修  0 未退修 1 已退修',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `resident_id` varchar(500) DEFAULT NULL COMMENT '报修业主id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_01` (`order_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='物业报修';

-- ----------------------------
-- Records of property_repair
-- ----------------------------
INSERT INTO `property_repair` VALUES ('1', '111111', '1', '11', '11111', '1dfd', '2018-07-17 18:45:28', '2018-07-18 18:45:32', 'dffddf', '788', '1', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('2', '111111111df', '1', '11', '11111', '1dfd', '2018-07-17 18:45:28', '2018-07-18 18:45:32', 'dffddf', '788', '1', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('47', '4566', '水电报修', '非共和国', '134567', '共和国和规划个哈哈哈哈哈哈哈哈哈哈哈', '2018-07-04 14:25:40', '2018-07-05 03:30:29', '宝贝帮你', '室内分机测试项目', '1', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('48', '发广告', '水电报修', '的非官方', '覆盖广泛', '刚刚', '2018-07-04 03:05:30', '2018-07-05 03:44:53', '覆盖广泛', '人间_A项目', '1', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('49', '167', '水电报修', '说过话呢', '1234', '官方刚刚还好', '2018-07-12 18:30:12', '2018-07-05 16:40:07', '风高放火', '大文件上传测试', '1', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('50', '561', '水电报修', '规范', '446', '覆盖广泛', '2018-07-03 19:25:44', '2018-07-07 19:41:04', '发过的', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '0', '东方别墅', null);
INSERT INTO `property_repair` VALUES ('51', '562', '水电报修', '规范', '4567', '覆盖广泛', '2018-07-04 13:25:50', '2018-07-07 19:42:15', '地方电饭锅', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '0', '东方别墅', null);
INSERT INTO `property_repair` VALUES ('52', '67', '水电报修', '更换合格', '678', '发给', '2018-06-26 13:25:54', '2018-07-07 23:20:10', '发给', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', '东方别墅', null);
INSERT INTO `property_repair` VALUES ('53', '678', '水电报修', '56', '5677', '大锅饭', '2018-06-27 13:25:35', '2018-07-07 23:20:58', 'v打发打发', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '0', '东方别墅', '1');
INSERT INTO `property_repair` VALUES ('54', '56', '水电报修', 'fg ', '5564', 'hgghd对方', '2018-06-28 01:05:48', '2018-07-09 01:48:15', 'fggh', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '1', '0', '大文件上传测试', '1');
INSERT INTO `property_repair` VALUES ('55', '45663', '阳台报修', '中南海', '15980764367', '阳台破裂坏了', '2018-07-11 14:05:20', '2018-07-10 23:03:38', '厦门市集美区产业研究院', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '0', '东方别墅', '1');
INSERT INTO `property_repair` VALUES ('56', '1531297909899', '水电报修', '慧芳', '15980764279', '发个非官方', '2018-07-12 00:31:49', '2018-07-12 00:31:49', '的非官方个', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('57', '1531298026605', '水电报修', '慧芳', '15980764279', '发个非官方', '2018-07-12 00:33:27', '2018-07-12 00:33:28', '的非官方个', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, null);
INSERT INTO `property_repair` VALUES ('58', '1531298469312', '水电报修', '发广告', '15980764279', '对方对方的对方', '2018-07-12 00:41:09', '2018-07-12 00:41:09', '对方', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('59', '1531298691277', '水电报修', '返回刚好', '15980764270', '成功改变v棒棒v吧vv棒棒 ', '2018-07-12 00:44:51', '2018-07-12 00:44:51', '多公分高', '3850A167-D309-4582-BD76-0C3CAB4C008E', '1', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('60', '1531299297276', '水电报修', '收费大幅度', '18559759381', '分管工会', '2018-07-12 00:54:57', '2018-07-12 00:54:57', '天津市 市辖区 和平区', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('61', '1531299675863', '阳台报修', '风格更好', '15980764279', '对方更方便', '2018-07-12 01:01:15', '2018-07-12 01:01:15', '风格更好', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('62', '1531357705323', '水电报修', '东风风光', '15980764279', '法规和韩国', '2018-07-12 17:08:25', '2018-07-12 17:08:25', '不吧VB', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('63', '1531357744002', '阳台报修', '未额', '15980764279', '电放费', '2018-07-12 17:09:04', '2018-07-12 17:09:04', '冠福股份', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('64', '1531358092180', '水电报修', '刚刚', '15980764279', '冠福股份', '2018-07-12 17:14:52', '2018-07-12 17:14:52', '发给', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('65', '1531358732437', '水电报修', '发给', '15980764279', '东风风光', '2018-07-12 17:25:32', '2018-07-12 17:25:32', '从v', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('66', '1531358781439', '水电报修', '发给', '15980764279', '对方', '2018-07-12 17:26:21', '2018-07-12 17:26:21', '相处', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('67', '1531362036804', '水电报修', '发给', '15980764279', '东方饭店', '2018-07-12 18:20:36', '2018-07-12 18:20:36', '大锅饭', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('68', '1531362190805', '阳台报修', '发给', '15980764279', '东方饭店', '2018-07-12 18:23:10', '2018-07-12 18:23:10', '发给', '3850A167-D309-4582-BD76-0C3CAB4C008E', '0', '0', '0', null, '1');
INSERT INTO `property_repair` VALUES ('69', '5678904533', '水电报修', '小施', '15980764279', '水管坏了，需要维修', '2018-07-14 23:20:09', '2018-07-13 23:24:07', '厦门集美产业研究院', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', '测试开发环境', '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('70', '4567890', '阳台报修', '对方', '15678909876', '电饭锅', '2018-07-11 17:25:00', '2018-07-13 23:25:23', '发给', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', '1', '测试开发环境', null);
INSERT INTO `property_repair` VALUES ('71', '1531466955432', '阳台报修', '小任', '13859235670', '学校大门口破损', '2018-07-13 23:29:15', '2018-07-13 23:29:15', '厦门诚意学院', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('72', '123456', '电梯报修', '习大大', '13767898703', '电梯出现故障', '2018-07-18 17:05:09', '2018-07-17 00:34:20', '北京中南海', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', '0', '测试开发环境', null);
INSERT INTO `property_repair` VALUES ('75', '123456789', '电梯报修', '习大大', '15878980987', '电梯发现故障，比较晃动，需要维修', '2018-07-18 13:25:13', '2018-07-17 00:44:51', '北京中南海', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', '测试开发环境', '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('76', '987663', '电梯报修', '周大大', '13456766789', '西花厅电梯有问题', '2018-07-18 17:05:14', '2018-07-17 00:46:00', '北京西花厅', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', '测试开发环境', '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('77', '1531730954731', '电梯报修', '毛大大', '13456789087', '天安门电梯发现问题需要维修', '2018-07-17 00:49:14', '2018-07-17 00:49:14', '北京天安门', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('78', '1531800450591', '电梯报修', '德国法国', '15878909876', '的风高放火韩国国会', '2018-07-17 20:07:30', '2018-07-17 20:07:30', '法规和韩国', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('79', '1531800526585', '电梯报修', '大风刮过', '15980765478', '法规和共和国', '2018-07-17 20:08:46', '2018-07-17 20:08:46', '复合弓', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('80', '1531800727089', '电梯报修', '大风刮过', '15980765478', '法规和共和国', '2018-07-17 20:12:07', '2018-07-17 20:12:07', '复合弓', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('81', '1531800770782', '电梯报修', '东风风光', '14678987890', '风格更好', '2018-07-17 20:12:50', '2018-07-17 20:12:50', '规范', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('82', '1531800775826', '电梯报修', '东风风光', '14678987890', '风格更好', '2018-07-17 20:12:55', '2018-07-17 20:12:55', '规范', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('83', '1531800778483', '电梯报修', '东风风光', '14678987890', '风格更好', '2018-07-17 20:12:58', '2018-07-17 20:12:58', '规范', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '1', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('84', '1531879646864', '阳台报修', '苏伟', '18559759388', '123师傅00', '2018-07-21 12:14:48', '2018-07-21 12:14:46', '天津市 市辖区 和平', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('85', '1531879675117', '水电报修', '权威', '18559759381', '123', '2018-07-18 18:07:55', '2018-07-18 18:07:55', '河北省 唐山市 xx镇100号', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('86', '1531879694379', '水电报修', '权威', '18559759381', '123', '2018-07-18 18:08:14', '2018-07-18 18:08:14', '河北省 唐山市 xx镇100号', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('87', '1531880182671', '阳台报修', '苏伟小施', '18559759380', '阳台需要更换', '2018-07-21 11:26:46', '2018-07-21 11:26:43', '产业研究院', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('88', '1531880251877', '水电报修', '哎呀速度', '13323322120', '电梯出现毛病对方1100', '2018-07-21 11:19:50', '2018-07-21 11:19:54', 'aaaa00', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '0', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('89', 'D78E8B91-7FEF', '水电报修', '小陈', '13878900980', '电梯坏了0', '2018-07-21 12:00:19', '2018-07-21 12:00:17', '集美产业研究院0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '0', '0', '测试开发环境', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157');
INSERT INTO `property_repair` VALUES ('92', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '水电报修', '小离', '13878900987', '水电故障', '2018-07-03 05:05:22', '2018-07-19 16:34:10', '福建省厦门市集美区产业研究院', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '1', '0', '测试开发环境', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157');
INSERT INTO `property_repair` VALUES ('93', '1532307797177', '水电报修', '小马', '13855386376', '家里电力不足，厨房又漏水，需要及时维修', '2018-07-23 09:03:17', '2018-07-23 09:03:17', '安徽芜湖思明区五一广场和顺路北岛之路89号', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '1', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('94', '1532418956152', '电梯报修', '小习', '13855386270', '电梯日常维护', '2018-07-24 15:55:56', '2018-07-24 15:55:56', '北京中南海', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '1', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');
INSERT INTO `property_repair` VALUES ('95', '1532419067198', '电梯报修', '小习', '13855386270', '电梯日常维护', '2018-07-24 15:57:47', '2018-07-24 15:57:47', '北京中南海', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '1', '0', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598');

-- ----------------------------
-- Table structure for property_repair_man
-- ----------------------------
DROP TABLE IF EXISTS `property_repair_man`;
CREATE TABLE `property_repair_man` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL COMMENT '姓名',
  `phone` varchar(40) NOT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `project_id` varchar(100) DEFAULT NULL COMMENT '项目ID',
  `del_flag` int(2) DEFAULT '0' COMMENT '默认0 代表未删除 1 代表已删除',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='维修师傅管理';

-- ----------------------------
-- Records of property_repair_man
-- ----------------------------
INSERT INTO `property_repair_man` VALUES ('93', '对方', '13987900987', '2018-07-20 13:38:12', '2018-07-20 13:38:12', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('94', '对方', '13987900987', '2018-07-20 13:38:33', '2018-07-20 13:38:33', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('95', '儿童如果', '13987900987', '2018-07-20 13:57:20', '2018-07-20 14:14:30', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('96', '非共和国', '13778900987', '2018-07-20 13:45:10', '2018-07-20 14:11:11', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('97', '对方', '13768900987', '2018-07-20 13:48:07', '2018-07-20 13:48:07', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('98', '对方发给', '13768900987', '2018-07-20 14:06:00', '2018-07-20 14:06:00', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('99', '发给发给', '13768900987', '2018-07-20 14:06:57', '2018-07-20 14:08:57', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('100', '电饭锅', '13987900987', '2018-07-20 14:14:41', '2018-07-20 14:14:41', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '0', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('101', '发给发给', '13987900988', '2018-07-20 14:19:07', '2018-07-20 14:19:31', 'EF594E9B-17A7-439F-B3F4-88F0693AAFA4', '1', 'AAA');
INSERT INTO `property_repair_man` VALUES ('102', '小施', '13959235677', '2018-07-21 12:18:24', '2018-07-21 12:30:28', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '1', '测试开发环境');
INSERT INTO `property_repair_man` VALUES ('103', '小白', '13678900987', '2018-07-23 09:11:16', '2018-07-23 09:11:16', '56', '1', 'df');

-- ----------------------------
-- Table structure for property_service
-- ----------------------------
DROP TABLE IF EXISTS `property_service`;
CREATE TABLE `property_service` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `repair_id` bigint(40) NOT NULL COMMENT '报修ID',
  `name` varchar(50) NOT NULL COMMENT '维修师傅',
  `phone` varchar(50) NOT NULL COMMENT '维修师傅联系电话',
  `content` varchar(2000) DEFAULT NULL COMMENT '维修内容',
  `order_time` datetime DEFAULT NULL COMMENT '接单时间',
  `status` int(2) DEFAULT '0' COMMENT '默认0 代表维修未完成  1 代表维修已完成',
  `del_flag` int(2) DEFAULT '0' COMMENT '默认0 代表未删除 1 代表已删除',
  `project_id` varchar(50) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='物业维修';

-- ----------------------------
-- Records of property_service
-- ----------------------------
INSERT INTO `property_service` VALUES ('47', '49', '规划', 'g677', '德国法国官方', '2018-07-05 19:05:02', '0', '0', '大文件上传测试', null);
INSERT INTO `property_service` VALUES ('48', '49', '的方法', '地方', '等丰富的', '2018-07-04 14:30:02', '0', '0', '大文件上传测试', null);
INSERT INTO `property_service` VALUES ('49', '49', '的方法', '地方', '等丰富的', '2018-07-04 14:30:02', '0', '0', '大文件上传测试', null);
INSERT INTO `property_service` VALUES ('51', '49', '很高', '5677', '复古风格', '2018-07-12 18:30:15', '1', '0', '大文件上传测试', null);
INSERT INTO `property_service` VALUES ('54', '48', 'ghhg ', '6788', 'fgfh', '2018-07-05 14:30:16', '0', '0', '人间_A项目', null);
INSERT INTO `property_service` VALUES ('55', '47', '666', '666', '666', '2018-07-03 17:45:15', '1', '0', '室内分机测试项目', null);
INSERT INTO `property_service` VALUES ('56', '2', '不错吧v', 'vbh', '宝宝', '2018-07-02 13:25:02', '1', '0', '788', null);
INSERT INTO `property_service` VALUES ('57', '51', '分分合合', '56个', '非官方个', '2018-07-04 19:25:49', '1', '0', '3850A167-D309-4582-BD76-0C3CAB4C008E', null);
INSERT INTO `property_service` VALUES ('58', '53', '分隔符', '123456', '风高放火', '2018-07-03 23:25:35', '1', '0', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅');
INSERT INTO `property_service` VALUES ('59', '55', '覆盖广泛', '15980764279', '从从v ', '2018-07-11 18:25:58', '0', '0', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅');
INSERT INTO `property_service` VALUES ('60', '59', '非共和国', '15980764279', 'VB不v', '2018-07-10 13:25:27', '0', '0', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅');
INSERT INTO `property_service` VALUES ('61', '69', '苏伟', '18678900987', '水管需要维修', '2018-07-19 18:25:16', '0', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('62', '71', '小黄', '13855456789', '学校大门破损需要及时处理', '2018-07-16 17:30:07', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('63', '77', '小施', '13567867890', '电梯维修', '2018-07-17 01:45:44', '0', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('64', '76', '小施', '13546789876', '天地电费', '2018-07-18 22:50:33', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('65', '83', 'gfg', '15980765678', 'fdf', '2018-07-17 20:25:42', '0', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null);
INSERT INTO `property_service` VALUES ('66', '88', '98', '13323322120', '风高放火覆盖广泛00', '2018-07-21 11:20:08', '0', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('67', '87', '99', '18559759380', '马上更换阳台', '2018-07-21 11:27:07', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('68', '92', '102', '13959235677', 'fdfgd双方都', '2018-07-21 11:54:25', '0', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('69', '89', '100', '13878900980', 'ddg对方01', '2018-07-21 12:01:54', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('70', '84', '100', '13959235678', '的发给冠福股份88', '2018-07-21 12:14:57', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('71', '93', '102', '13959235677', '水电维修，特别是厨房', '2018-07-24 09:05:09', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');
INSERT INTO `property_service` VALUES ('72', '95', '102', '13959235677', '马上处理维修<b>水电水电<span style=\"background-color: rgb(255, 255, 0);\">水电水电费</span></b>', '2018-07-26 10:00:00', '1', '0', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '测试开发环境');

-- ----------------------------
-- Table structure for repair_pic
-- ----------------------------
DROP TABLE IF EXISTS `repair_pic`;
CREATE TABLE `repair_pic` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(500) NOT NULL COMMENT '文件路径',
  `repair_id` bigint(40) NOT NULL COMMENT '物业保修id 一对多关系',
  `project_id` varchar(50) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `del_flag` int(4) DEFAULT '0' COMMENT '0 代表未删除 1 代表删除',
  `pic_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='存放图片路径';

-- ----------------------------
-- Records of repair_pic
-- ----------------------------
INSERT INTO `repair_pic` VALUES ('1', 'group1/M00/00/00/rKgEVFs_PSGAMq68AABCCz29bhA384.jpg', '45663', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('2', 'group1/M00/00/00/rKgEVFs_PUaAIpidAAAYUQrnGKI268.png', '45663', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('3', 'group1/M00/00/00/rKgEVFtAMueAcPUpAABCCz29bhA578.jpg', '47', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('4', 'group1/M00/00/00/rKgEVFtALtWABvCoAABCCz29bhA587.jpg', '48', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('5', 'group1/M00/00/00/rKgEVFtAMueAcPUpAABCCz29bhA578.jpg', '47', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('6', 'group1/M00/00/00/rKgEVFtALtWABvCoAABCCz29bhA587.jpg', '48', '3850A167-D309-4582-BD76-0C3CAB4C008E', '东方别墅', '0', null);
INSERT INTO `repair_pic` VALUES ('7', 'group1/M00/00/01/rKgEVFtFw4OAQyVCAAD2L8cp2yg536.png', '1531298691277', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', '3.png');
INSERT INTO `repair_pic` VALUES ('8', 'group1/M00/00/01/rKgEVFtFw4SAOfTZAAkD0hUJup8860.png', '1531298691277', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', '4.png');
INSERT INTO `repair_pic` VALUES ('9', 'group1/M00/00/01/rKgEVFtFw4SAKz_xAAkD0hUJup8469.png', '1531298691277', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', '4.png');
INSERT INTO `repair_pic` VALUES ('10', 'group1/M00/00/01/rKgEVFtFxeGATtHuAADT9sTXMKQ646.jpg', '1531299297276', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'u=4101145629,2049603356&fm=27&gp=0.jpg');
INSERT INTO `repair_pic` VALUES ('11', 'group1/M00/00/01/rKgEVFtFxeGAJWY2AADT9sTXMKQ239.jpg', '1531299297276', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'u=4101145629,2049603356&fm=27&gp=0.jpg');
INSERT INTO `repair_pic` VALUES ('12', 'group1/M00/00/01/rKgEVFtFxeGASa6WAAGc8NTmXSE640.jpg', '1531299297276', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', '77i58PICzwn.jpg');
INSERT INTO `repair_pic` VALUES ('13', 'group1/M00/00/01/rKgEVFtGuvSAeEC6AAD_veMBJds007.jpg', '1531362036804', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('14', 'group1/M00/00/01/rKgEVFtGu46ADqGFAAD_veMBJds854.jpg', '1531362190805', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('15', 'group1/M00/00/01/rKgEVFtGu46AZIO3AALHiZc_vuc918.jpg', '1531362190805', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj1.jpg');
INSERT INTO `repair_pic` VALUES ('16', 'group1/M00/00/01/rKgEVFtGu46AEUEdAASMVA50hxQ592.jpg', '1531362190805', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('17', 'group1/M00/00/01/rKgEVFtH_8KAScuFAAD_veMBJds391.jpg', '1531445186215', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('18', 'group1/M00/00/01/rKgEVFtH_8KABXyJAALHiZc_vuc170.jpg', '1531445186215', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj1.jpg');
INSERT INTO `repair_pic` VALUES ('19', 'group1/M00/00/01/rKgEVFtH_8OABEz0AASMVA50hxQ146.jpg', '1531445186215', '3850A167-D309-4582-BD76-0C3CAB4C008E', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('20', 'group1/M00/00/01/rKgEVFtIVMuABH51AAD_veMBJds281.jpg', '1531466955432', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('21', 'group1/M00/00/01/rKgEVFtIVMuAWG9GAASMVA50hxQ611.jpg', '1531466955432', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('22', 'group1/M00/00/01/rKgEVFtIVMuACYHNAALHiZc_vuc496.jpg', '1531466955432', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj1.jpg');
INSERT INTO `repair_pic` VALUES ('23', 'group1/M00/00/01/rKgEVFtIYZqAIQ3zAALHiZc_vuc967.jpg', '1531470234569', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj1.jpg');
INSERT INTO `repair_pic` VALUES ('24', 'group1/M00/00/01/rKgEVFtIYZqAXUbmAASMVA50hxQ706.jpg', '1531470234569', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('25', 'group1/M00/00/02/rKgEVFtMXA-AI4n-AAD_veMBJds720.jpg', '1531730954731', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('26', 'group1/M00/00/02/rKgEVFtMXA-AfocJAASMVA50hxQ360.jpg', '1531730954731', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('27', 'group1/M00/00/02/rKgEVFtNa4SAdsNZAAD_veMBJds361.jpg', '1531800450591', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('28', 'group1/M00/00/02/rKgEVFtNa4SAGguDAASMVA50hxQ368.jpg', '1531800450591', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('29', 'group1/M00/00/02/rKgEVFtNa9CAamoWAAD_veMBJds505.jpg', '1531800526585', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('30', 'group1/M00/00/02/rKgEVFtNbJmALBZOAAD_veMBJds459.jpg', '1531800727089', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj.jpg');
INSERT INTO `repair_pic` VALUES ('31', 'group1/M00/00/02/rKgEVFtOoSmAZratAAJmaEyl4Vo805.jpg', '1531879675117', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'timg.jpg');
INSERT INTO `repair_pic` VALUES ('32', 'group1/M00/00/02/rKgEVFtOoTyAFhbFAAB8AA1kMJA597.doc', '1531879694379', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', '苏伟_第24周.doc');
INSERT INTO `repair_pic` VALUES ('33', 'group1/M00/00/02/rKgEVFtOoySAD9-AAAJmaEyl4Vo218.jpg', '1531880182671', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'timg.jpg');
INSERT INTO `repair_pic` VALUES ('34', 'group1/M00/00/02/rKgEVFtOo2qATC4pAAAN1DtjEFQ321.png', '1531880251877', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'll_invalid_environment@2x.png');
INSERT INTO `repair_pic` VALUES ('35', 'group1/M00/00/02/rKgEVFtO83uAWOK4AAAKsTb4dNo810.lrc', '1531900748250', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', '不将就.lrc');
INSERT INTO `repair_pic` VALUES ('36', 'group1/M00/00/02/rKgEVFtQU3KAVEGTAALHiZc_vuc693.jpg', '1531990895943', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj1.jpg');
INSERT INTO `repair_pic` VALUES ('37', 'group1/M00/00/02/rKgEVFtQU3KAQhTaAASMVA50hxQ441.jpg', '1531990895943', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'syj2.jpg');
INSERT INTO `repair_pic` VALUES ('38', 'group1/M00/00/02/rKgEVFtVKVeAWc7fAALHiZc_vuc393.jpg', '1532307797177', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b92d6d9d-4326-4f1f-828c-fa4ae9bc696f.jpg');
INSERT INTO `repair_pic` VALUES ('39', 'group1/M00/00/02/rKgEVFtVKVeAKgL8AASMVA50hxQ079.jpg', '1532307797177', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'ed95d1b755784319cc2db7d2f3b59a09_53d71b942a18a.jpg');
INSERT INTO `repair_pic` VALUES ('40', 'group1/M00/00/02/rKgEVFtW24-APX40AASMVA50hxQ577.jpg', '1532418956152', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'ed95d1b755784319cc2db7d2f3b59a09_53d71b942a18a.jpg');
INSERT INTO `repair_pic` VALUES ('41', 'group1/M00/00/02/rKgEVFtW24-AYAy1AAyvPJ83YaY144.png', '1532418956152', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b4557ac95bfaf52da6336368ee6aa210_01c53f5567f0930000016756edc878.jpg@1280w_1l_2o_100sh.png');
INSERT INTO `repair_pic` VALUES ('42', 'group1/M00/00/02/rKgEVFtW24-AOBJ7AALHiZc_vuc664.jpg', '1532418956152', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b92d6d9d-4326-4f1f-828c-fa4ae9bc696f.jpg');
INSERT INTO `repair_pic` VALUES ('43', 'group1/M00/00/02/rKgEVFtW2_6APIGzAAyvPJ83YaY869.png', '1532419067198', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b4557ac95bfaf52da6336368ee6aa210_01c53f5567f0930000016756edc878.jpg@1280w_1l_2o_100sh.png');
INSERT INTO `repair_pic` VALUES ('44', 'group1/M00/00/02/rKgEVFtW2_6AGGMYAASMVA50hxQ041.jpg', '1532419067198', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'ed95d1b755784319cc2db7d2f3b59a09_53d71b942a18a.jpg');
INSERT INTO `repair_pic` VALUES ('45', 'group1/M00/00/02/rKgEVFtW2_6ARH5hAALHiZc_vuc004.jpg', '1532419067198', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b92d6d9d-4326-4f1f-828c-fa4ae9bc696f.jpg');
INSERT INTO `repair_pic` VALUES ('46', 'group1/M00/00/02/rKgEVFtW3L6AAdXvAASMVA50hxQ329.jpg', '1532419259016', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'ed95d1b755784319cc2db7d2f3b59a09_53d71b942a18a.jpg');
INSERT INTO `repair_pic` VALUES ('47', 'group1/M00/00/02/rKgEVFtW3L6AAg1PAAyvPJ83YaY532.png', '1532419259016', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b4557ac95bfaf52da6336368ee6aa210_01c53f5567f0930000016756edc878.jpg@1280w_1l_2o_100sh.png');
INSERT INTO `repair_pic` VALUES ('48', 'group1/M00/00/02/rKgEVFtW3L6AaQWEAALHiZc_vuc675.jpg', '1532419259016', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', null, '0', 'b92d6d9d-4326-4f1f-828c-fa4ae9bc696f.jpg');

-- ----------------------------
-- Table structure for survey
-- ----------------------------
DROP TABLE IF EXISTS `survey`;
CREATE TABLE `survey` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '问卷主题',
  `remark` varchar(600) DEFAULT NULL COMMENT '备注',
  `project_id` varchar(50) DEFAULT NULL COMMENT '小区选择（项目id）',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `release_time` datetime DEFAULT NULL COMMENT '发布日期',
  `end_time` datetime DEFAULT NULL COMMENT '问卷结束时间',
  `survey_type` varchar(20) DEFAULT NULL COMMENT '问卷类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '发布人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `status` varchar(2) DEFAULT NULL COMMENT '状态 Y 发布 N 未发布',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='问卷主表';

-- ----------------------------
-- Records of survey
-- ----------------------------
INSERT INTO `survey` VALUES ('1', '满意度调查（一）', '备注一', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', '2018-08-01 14:39:34', '2018-08-24 00:00:00', null, '2018-07-23 13:18:17', '33', '2018-08-01 14:39:33', '33', 'Y', '0');
INSERT INTO `survey` VALUES ('2', '满意度调查（二）', '备注二', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', '2018-08-01 14:25:26', null, null, '2018-07-23 15:24:15', '33', '2018-08-01 14:25:25', '33', 'Y', '0');
INSERT INTO `survey` VALUES ('3', '食堂伙食', '伙食是否标准', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', '2018-08-01 14:25:32', '2018-08-01 12:00:00', null, '2018-07-24 16:24:27', '33', '2018-08-01 14:25:32', '33', 'Y', '0');
INSERT INTO `survey` VALUES ('4', '问卷调查四', '备注四', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', null, null, null, '2018-07-31 16:30:59', '33', '2018-08-01 14:36:12', '33', 'N', '0');
INSERT INTO `survey` VALUES ('5', '问卷调研五', '备注五', 'D78E8B91-7FEF-46C0-9BB9-13E71784B157', '大文件上传测试', null, null, null, '2018-08-01 09:02:38', '33', '2018-08-01 09:02:53', '33', 'N', '0');

-- ----------------------------
-- Table structure for survey_answer
-- ----------------------------
DROP TABLE IF EXISTS `survey_answer`;
CREATE TABLE `survey_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `survey_id` bigint(20) DEFAULT NULL COMMENT '问卷主表id',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `options_id` bigint(20) DEFAULT NULL COMMENT '选项的 id',
  `text_val` varchar(1000) DEFAULT NULL COMMENT 'options_type为 text文本类型时输入的值',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户id',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='问卷回答表';

-- ----------------------------
-- Records of survey_answer
-- ----------------------------
INSERT INTO `survey_answer` VALUES ('32', '1', '30', '70', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('33', '1', '31', '72', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('34', '1', '31', '73', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('35', '1', '32', '75', '我能有什么意见呢？', '00001', '0');
INSERT INTO `survey_answer` VALUES ('36', '1', '33', '76', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('37', '1', '34', '79', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('50', '1', '30', '70', null, '00004', '0');
INSERT INTO `survey_answer` VALUES ('51', '1', '31', '74', null, '00004', '0');
INSERT INTO `survey_answer` VALUES ('52', '1', '32', '75', '错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错啛啛喳喳错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错啛啛喳喳错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错错啛啛喳喳错错错错错错错错错错错错错错错错错错错错错', '00004', '0');
INSERT INTO `survey_answer` VALUES ('53', '1', '33', '77', null, '00004', '0');
INSERT INTO `survey_answer` VALUES ('54', '1', '34', '79', null, '00004', '0');
INSERT INTO `survey_answer` VALUES ('55', '1', '30', '70', null, '00005', '0');
INSERT INTO `survey_answer` VALUES ('56', '1', '31', '74', null, '00005', '0');
INSERT INTO `survey_answer` VALUES ('57', '1', '32', '75', '建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议', '00005', '0');
INSERT INTO `survey_answer` VALUES ('58', '1', '33', '76', null, '00005', '0');
INSERT INTO `survey_answer` VALUES ('59', '1', '34', '80', null, '00005', '0');
INSERT INTO `survey_answer` VALUES ('74', '1', '35', '82', null, '0001', '0');
INSERT INTO `survey_answer` VALUES ('75', '1', '36', '83', null, '0001', '0');
INSERT INTO `survey_answer` VALUES ('76', '1', '36', '111', null, '0001', '0');
INSERT INTO `survey_answer` VALUES ('77', '1', '37', '86', '年后啊', '0001', '0');
INSERT INTO `survey_answer` VALUES ('78', '1', '38', '88', null, '0001', '0');
INSERT INTO `survey_answer` VALUES ('79', '1', '39', '90', null, '0001', '0');
INSERT INTO `survey_answer` VALUES ('85', '1', '35', '81', null, '00002', '0');
INSERT INTO `survey_answer` VALUES ('86', '1', '36', '83', null, '00002', '0');
INSERT INTO `survey_answer` VALUES ('87', '1', '36', '84', null, '00002', '0');
INSERT INTO `survey_answer` VALUES ('88', '1', '37', '86', '没有意见', '00002', '0');
INSERT INTO `survey_answer` VALUES ('89', '1', '38', '88', null, '00002', '0');
INSERT INTO `survey_answer` VALUES ('90', '1', '39', '90', null, '00002', '0');
INSERT INTO `survey_answer` VALUES ('91', '1', '35', '81', null, '00003', '0');
INSERT INTO `survey_answer` VALUES ('92', '1', '36', '83', null, '00003', '0');
INSERT INTO `survey_answer` VALUES ('93', '1', '36', '85', null, '00003', '0');
INSERT INTO `survey_answer` VALUES ('94', '1', '37', '86', '建议', '00003', '0');
INSERT INTO `survey_answer` VALUES ('95', '1', '38', '87', null, '00003', '0');
INSERT INTO `survey_answer` VALUES ('96', '1', '39', '89', null, '00003', '0');
INSERT INTO `survey_answer` VALUES ('97', '2', '40', '92', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('98', '2', '45', '104', null, '00001', '0');
INSERT INTO `survey_answer` VALUES ('99', '1', '35', '81', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');
INSERT INTO `survey_answer` VALUES ('100', '1', '36', '83', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');
INSERT INTO `survey_answer` VALUES ('101', '1', '36', '84', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');
INSERT INTO `survey_answer` VALUES ('102', '1', '49', '116', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');
INSERT INTO `survey_answer` VALUES ('103', '1', '38', '88', null, '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');
INSERT INTO `survey_answer` VALUES ('104', '1', '48', '115', '问题五是一个文本题', '892B0DAC-C3EF-4456-BBC9-0039493ED598', '0');

-- ----------------------------
-- Table structure for survey_building
-- ----------------------------
DROP TABLE IF EXISTS `survey_building`;
CREATE TABLE `survey_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `survey_id` bigint(20) DEFAULT NULL COMMENT '问卷id',
  `building_id` varchar(50) DEFAULT NULL COMMENT '楼栋id',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='问卷-楼栋 关联表';

-- ----------------------------
-- Records of survey_building
-- ----------------------------
INSERT INTO `survey_building` VALUES ('117', '2', 'BFDEB257-AC45-4158-89F4-B126945714FD', '0');
INSERT INTO `survey_building` VALUES ('118', '1', 'BFDEB257-AC45-4158-89F4-B126945714FD', '0');
INSERT INTO `survey_building` VALUES ('119', '1', 'A7657433-B686-4B8F-BD14-18627866AD79', '0');

-- ----------------------------
-- Table structure for survey_options
-- ----------------------------
DROP TABLE IF EXISTS `survey_options`;
CREATE TABLE `survey_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `survey_id` bigint(20) DEFAULT NULL COMMENT '主表survey 的 id',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `option_val` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '选项内容',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=latin1 COMMENT='问卷选项';

-- ----------------------------
-- Records of survey_options
-- ----------------------------
INSERT INTO `survey_options` VALUES ('81', '1', '35', '满意', '0');
INSERT INTO `survey_options` VALUES ('82', '1', '35', '不满意', '0');
INSERT INTO `survey_options` VALUES ('83', '1', '36', '画画1', '0');
INSERT INTO `survey_options` VALUES ('84', '1', '36', '唱歌', '0');
INSERT INTO `survey_options` VALUES ('85', '1', '36', '写作', '0');
INSERT INTO `survey_options` VALUES ('86', '1', '37', '', '1');
INSERT INTO `survey_options` VALUES ('87', '1', '38', '选项一', '0');
INSERT INTO `survey_options` VALUES ('88', '1', '38', '选项二', '0');
INSERT INTO `survey_options` VALUES ('89', '1', '39', '1', '1');
INSERT INTO `survey_options` VALUES ('90', '1', '39', '2', '1');
INSERT INTO `survey_options` VALUES ('91', '1', '39', '3', '1');
INSERT INTO `survey_options` VALUES ('92', '2', '40', '1', '0');
INSERT INTO `survey_options` VALUES ('93', '2', '40', '2', '0');
INSERT INTO `survey_options` VALUES ('94', '2', '41', '选项一', '1');
INSERT INTO `survey_options` VALUES ('95', '2', '41', '选项二', '1');
INSERT INTO `survey_options` VALUES ('96', '2', '41', '选项三', '1');
INSERT INTO `survey_options` VALUES ('97', '2', '42', '', '1');
INSERT INTO `survey_options` VALUES ('100', '2', '43', '1', '1');
INSERT INTO `survey_options` VALUES ('101', '2', '43', '2', '1');
INSERT INTO `survey_options` VALUES ('102', '2', '44', '', '1');
INSERT INTO `survey_options` VALUES ('103', '2', '45', '选项一', '0');
INSERT INTO `survey_options` VALUES ('104', '2', '45', '选项二', '0');
INSERT INTO `survey_options` VALUES ('105', '2', '45', '3', '1');
INSERT INTO `survey_options` VALUES ('106', '2', '45', '选项三', '1');
INSERT INTO `survey_options` VALUES ('107', '2', '45', '选项三3', '1');
INSERT INTO `survey_options` VALUES ('108', '2', '45', '选项四', '1');
INSERT INTO `survey_options` VALUES ('109', '2', '45', '选项五', '1');
INSERT INTO `survey_options` VALUES ('110', '2', '45', '选项4', '1');
INSERT INTO `survey_options` VALUES ('111', '1', '36', '健身', '1');
INSERT INTO `survey_options` VALUES ('112', '2', '45', '选项三', '0');
INSERT INTO `survey_options` VALUES ('113', '4', '46', '', '0');
INSERT INTO `survey_options` VALUES ('114', '4', '47', '', '0');
INSERT INTO `survey_options` VALUES ('115', '1', '48', '', '0');
INSERT INTO `survey_options` VALUES ('116', '1', '49', '选项一', '0');
INSERT INTO `survey_options` VALUES ('117', '1', '49', '选项二', '0');
INSERT INTO `survey_options` VALUES ('118', '4', '50', '1', '0');
INSERT INTO `survey_options` VALUES ('119', '4', '50', '2', '0');

-- ----------------------------
-- Table structure for survey_question
-- ----------------------------
DROP TABLE IF EXISTS `survey_question`;
CREATE TABLE `survey_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `survey_id` bigint(20) DEFAULT NULL COMMENT '主表survey id',
  `seq` bigint(20) DEFAULT NULL COMMENT '后台处理，界面不显示，用于排序，显示为第几题',
  `option_title` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '题目名称',
  `option_type` varchar(10) DEFAULT NULL COMMENT '题目类型: single单选 multi多选 text文本',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标记 0 代表未删除 1 代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1 COMMENT='问卷问题';

-- ----------------------------
-- Records of survey_question
-- ----------------------------
INSERT INTO `survey_question` VALUES ('35', '1', '1', '您对我们的产品满意吗?', 'single', '0');
INSERT INTO `survey_question` VALUES ('36', '1', '2', '您的兴趣爱好有哪些？', 'multi', '0');
INSERT INTO `survey_question` VALUES ('37', '1', '3', '您对我们还有什么建议? 请写出来', 'text', '1');
INSERT INTO `survey_question` VALUES ('38', '1', '4', '问题四', 'single', '0');
INSERT INTO `survey_question` VALUES ('39', '1', '5', '问题五', 'single', '1');
INSERT INTO `survey_question` VALUES ('40', '2', '1', '问题一', 'single', '0');
INSERT INTO `survey_question` VALUES ('41', '2', '2', '问题二', 'single', '1');
INSERT INTO `survey_question` VALUES ('42', '2', '2', '问题三', 'text', '1');
INSERT INTO `survey_question` VALUES ('43', '2', '3', '问题四', 'single', '1');
INSERT INTO `survey_question` VALUES ('44', '2', '2', '问题五', 'text', '1');
INSERT INTO `survey_question` VALUES ('45', '2', '2', '问题二', 'single', '0');
INSERT INTO `survey_question` VALUES ('46', '4', '1', '问题一', 'text', '0');
INSERT INTO `survey_question` VALUES ('47', '4', '3', '问题三', 'text', '0');
INSERT INTO `survey_question` VALUES ('48', '1', '5', '问题五', 'text', '0');
INSERT INTO `survey_question` VALUES ('49', '1', '3', '问题三', 'multi', '0');
INSERT INTO `survey_question` VALUES ('50', '4', '2', '问题二', 'single', '0');

-- ----------------------------
-- Table structure for user_expand
-- ----------------------------
DROP TABLE IF EXISTS `user_expand`;
CREATE TABLE `user_expand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别: male 男 female女 unknown 未知',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='第三方用户扩展表';

-- ----------------------------
-- Records of user_expand
-- ----------------------------
INSERT INTO `user_expand` VALUES ('1', '18559759381', '', '', '');
INSERT INTO `user_expand` VALUES ('2', '18046086607', 'http://172.168.4.84/group1/M00/00/01/rKgEVFtGobOAIgQOAAGc8NTmXSE888.jpg', 'female', 'zhangsan1');
INSERT INTO `user_expand` VALUES ('5', '18305965360', 'http://172.168.4.84/group1/M00/00/02/rKgEVFtW10KASU47AACScnd6OTQ314.jpg', 'male', '空军13号');
INSERT INTO `user_expand` VALUES ('6', '18705046183', 'http://172.168.4.84/group1/M00/00/02/rKgEVFtPANmAWouzAACScnd6OTQ783.jpg', 'female', '1112233');
