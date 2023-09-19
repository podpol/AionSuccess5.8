SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for threes_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `threes_upgrade`;
CREATE TABLE `threes_upgrade` (
  `id` int(11) NOT NULL,
  `exp` int(255) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '1',
  `threeId` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `threes_upgrade` VALUES ('1', '0', '1', '833774');
INSERT INTO `threes_upgrade` VALUES ('2', '0', '1', '833775');
INSERT INTO `threes_upgrade` VALUES ('3', '0', '1', '833776');
INSERT INTO `threes_upgrade` VALUES ('4', '0', '1', '833774');
INSERT INTO `threes_upgrade` VALUES ('5', '0', '1', '833775');
INSERT INTO `threes_upgrade` VALUES ('6', '0', '1', '833776');