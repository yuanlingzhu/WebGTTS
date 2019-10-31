/*
Navicat MySQL Data Transfer

Source Server         : 120.24.165.86
Source Server Version : 50528
Source Host           : 120.24.165.86:3306
Source Database       : ggts

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-10-31 15:36:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for led_background_image
-- ----------------------------
DROP TABLE IF EXISTS `led_background_image`;
CREATE TABLE `led_background_image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LED_NO` varchar(255) DEFAULT NULL COMMENT 'LED屏的编号（要唯一）',
  `BACKGROUND_IMAGE` longtext COMMENT '背景图',
  `PIXEL_WIDTH` int(20) DEFAULT NULL COMMENT '图片的像素宽',
  `PIXEL_HEIGHT` int(20) DEFAULT NULL COMMENT '图片的像素高',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of led_background_image
-- ----------------------------

-- ----------------------------
-- Table structure for led_road_inf
-- ----------------------------
DROP TABLE IF EXISTS `led_road_inf`;
CREATE TABLE `led_road_inf` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `LED_NO` varchar(255) DEFAULT NULL COMMENT 'LED屏标识（唯一）',
  `ROAD_ID` varchar(255) DEFAULT NULL COMMENT '据根GPS坐标定位路段ID',
  `START_LON_GPS` varchar(255) DEFAULT NULL COMMENT '开始经度坐标',
  `START_LAT_GPS` varchar(255) DEFAULT NULL COMMENT '始开纬度坐标',
  `END_LON_GPS` varchar(255) DEFAULT NULL COMMENT '结束经度坐标',
  `END_LAT_GPS` varchar(255) DEFAULT NULL COMMENT '结束纬度坐标',
  `TWO_POINT_TIME` int(11) DEFAULT NULL COMMENT '起点到终点所需要的 时间',
  `TIME_SHOW_X` int(11) DEFAULT NULL COMMENT '时间在屏上显示的X坐标',
  `TIME_SHOW_Y` int(11) DEFAULT NULL COMMENT '时间在屏上显示的X坐标',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of led_road_inf
-- ----------------------------
