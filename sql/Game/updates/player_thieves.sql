-- ----------------------------
-- Table structure for player_thieves
-- ----------------------------
DROP TABLE IF EXISTS `player_thieves`;
CREATE TABLE `player_thieves` (
  `player_id` int(11) NOT NULL,
  `rank` int(255) NOT NULL DEFAULT '0',
  `thieves_count` int(255) NOT NULL DEFAULT '0',
  `prison_count` int(255) NOT NULL DEFAULT '0',
  `last_kinah` bigint(20) NOT NULL DEFAULT '0',
  `revenge_name` varchar(25) NOT NULL DEFAULT 'Нет',
  `revenge_count` int(255) NOT NULL DEFAULT '0',
  `revenge_date` timestamp NOT NULL DEFAULT '2016-07-30 00:00:00',
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `unique_name` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;