/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100136
 Source Host           : localhost:3306
 Source Schema         : aiondbls_gs

 Target Server Type    : MySQL
 Target Server Version : 100136
 File Encoding         : 65001

 Date: 25/01/2019 16:03:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for player_minions
-- ----------------------------
DROP TABLE IF EXISTS `player_minions`;
CREATE TABLE `player_minions`  (
  `player_id` int(11) NOT NULL,
  `object_id` int(11) NOT NULL DEFAULT 0,
  `minion_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `growthpoints` int(6) NOT NULL,
  `is_locked` int(1) NOT NULL DEFAULT 0,
  `expire_time` int(11) NOT NULL DEFAULT 0,
  `despawn_time` timestamp(0) NULL DEFAULT NULL,
  `buff_bag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`player_id`, `object_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
