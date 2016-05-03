# SQL Manager Lite for MySQL 5.5.3.46984
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : azpz


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `azpz`;

CREATE DATABASE `azpz`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_german1_ci';

USE `azpz`;

#
# Löschen von Datenbankobjekten
#

DROP VIEW IF EXISTS `v_projects`;
DROP PROCEDURE IF EXISTS `table_exits`;
DROP TABLE IF EXISTS `worktime_new`;
DROP TABLE IF EXISTS `worktime`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `projects`;
DROP TABLE IF EXISTS `project_worker`;
DROP TABLE IF EXISTS `project_head`;
DROP TABLE IF EXISTS `persons`;
DROP TABLE IF EXISTS `login`;
DROP TABLE IF EXISTS `address`;

#
# Struktur für die Tabelle `address`: 
#

CREATE TABLE `address` (
  `address_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `address1` VARCHAR(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address2` VARCHAR(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address3` VARCHAR(100) COLLATE latin1_general_ci DEFAULT NULL,
  `city` VARCHAR(30) COLLATE latin1_general_ci DEFAULT NULL,
  `plz` VARCHAR(10) COLLATE latin1_general_ci DEFAULT NULL,
  `Country_short` VARCHAR(2) COLLATE latin1_general_ci DEFAULT NULL,
  `Country` VARCHAR(30) COLLATE latin1_general_ci NOT NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `text` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`address_ID`) USING BTREE,
  UNIQUE KEY `address_ID` (`address_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `login`: 
#

CREATE TABLE `login` (
  `login_ID` BIGINT(20) NOT NULL,
  `fk_user_ID` BIGINT(20) NOT NULL,
  `text` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`login_ID`) USING BTREE,
  UNIQUE KEY `login_ID` (`login_ID`) USING BTREE
) ENGINE=InnoDB
CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `persons`: 
#

CREATE TABLE `persons` (
  `persons_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `firstName` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `text` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`persons_ID`) USING BTREE,
  UNIQUE KEY `persons_ID` (`persons_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=3 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `project_head`: 
#

CREATE TABLE `project_head` (
  `project_head_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `text` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `fk_projectworker_id` BIGINT(20) NOT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`project_head_ID`) USING BTREE,
  UNIQUE KEY `project_head_ID` (`project_head_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=7 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `project_worker`: 
#

CREATE TABLE `project_worker` (
  `project_worker_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `fk_projects_ID` BIGINT(20) DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`project_worker_ID`) USING BTREE,
  UNIQUE KEY `project_worker_ID` (`project_worker_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=4 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `projects`: 
#

CREATE TABLE `projects` (
  `projects_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT NULL,
  `update_Date` TIMESTAMP(6) NULL DEFAULT NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`projects_ID`) USING BTREE,
  UNIQUE KEY `projects_ID` (`projects_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `user`: 
#

CREATE TABLE `user` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `pw_clear` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `pw_hash` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `text` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `user_ID` (`user_id`) USING BTREE,
  UNIQUE KEY `user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=4 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `worktime`: 
#

CREATE TABLE `worktime` (
  `worktime_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `fk_persons_ID` BIGINT(20) DEFAULT NULL,
  `timediff` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`worktime_ID`) USING BTREE,
  UNIQUE KEY `worktime_ID` (`worktime_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=12 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Struktur für die Tabelle `worktime_new`: 
#

CREATE TABLE `worktime_new` (
  `worktime_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `text` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_swedish_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fk_persons_ID` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`worktime_ID`) USING BTREE,
  UNIQUE KEY `worktime_ID_new` (`worktime_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

#
# Definition für die Prozedur`: 
#

DELIMITER $$

CREATE PROCEDURE `table_exits`(
        IN `ptable_name` VARCHAR(30),
        OUT `count_table` INTEGER
    )
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
SELECT COUNT(*) INTO count_table   
    FROM `information_schema`.`TABLES` WHERE 
    `information_schema`.`TABLES`.`TABLE_NAME` = ptable_name$$

DELIMITER ;

#
# Definition für die Sicht`v_projects`:
 
#

CREATE ALGORITHM=UNDEFINED VIEW `v_projects`
AS
select
  `ph`.`project_head_ID` AS `project_head_ID`,
  `ph`.`name` AS `name`,
  `ph`.`text` AS `text`,
  `ph`.`fk_projectworker_id` AS `fk_projectworker_id`,
  `ph`.`insert_MB` AS `insert_MB`,
  `ph`.`update_MB` AS `update_MB`,
  `ph`.`insert_Date` AS `insert_Date`,
  `ph`.`update_Date` AS `update_Date`,
  `ph`.`timestamp` AS `timestamp`,
  `w`.`text` AS `Project_worker_name`,
  `p`.`text` AS `Project_name`,
  `wt`.`timediff` AS `timediff`
from
  (((`projects` `p`
  join `project_head` `ph`)
  join `project_worker` `w`)
  join `worktime` `wt`)
where
  ((`p`.`projects_ID` = `ph`.`fk_projectworker_id`) and
  (`w`.`fk_projects_ID` = `p`.`projects_ID`) and
  (`p`.`projects_ID` = `wt`.`fk_persons_ID`));

#
# Data for the `persons` table  (LIMIT 0,500)
#

INSERT INTO `persons` (`persons_ID`, `name`, `firstName`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  (1,'Luethke','Matthias','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:55:49'),
  (2,'Labsch','Martin','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:56:18');
COMMIT;

#
# Data for the `project_head` table  (LIMIT 0,500)
#

INSERT INTO `project_head` (`project_head_ID`, `name`, `text`, `fk_projectworker_id`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  (1,'Project_1','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:10:24.184906'),
  (2,'Project_2','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:44.529825'),
  (3,'Project_3','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:46.898161'),
  (4,'Project_4','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:50.597786'),
  (5,'Project_5','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:51.814286'),
  (6,'Project_6','test',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:52.549366');
COMMIT;

#
# Data for the `project_worker` table  (LIMIT 0,500)
#

INSERT INTO `project_worker` (`project_worker_ID`, `text`, `fk_persons_ID`, `fk_projects_ID`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  (1,'Test',0,NULL,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:48:51.060473'),
  (2,'Test Martin',0,NULL,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:48:51.060473');
COMMIT;

#
# Data for the `user` table  (LIMIT 0,500)
#

INSERT INTO `user` (`user_id`, `user_name`, `pw_clear`, `pw_hash`, `fk_persons_ID`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  (1,'MatthiasLuethke1','start',NULL,1,NULL,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:48:51'),
  (2,'MartinLabsch2','start',NULL,2,NULL,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:48:51');
COMMIT;

#
# Data for the `worktime` table  (LIMIT 0,500)
#

INSERT INTO `worktime` (`worktime_ID`, `start`, `end`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`, `fk_persons_ID`, `timediff`) VALUES
  (1,'0000-00-00 00:00:00.000000','2016-05-01 20:56:38.000000','test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 04:56:38.133405',1,NULL),
  (2,'0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  (3,'0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  (5,'0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  (6,'0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  (8,'0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,55),
  (9,'0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,44),
  (11,NULL,'2016-05-01 21:15:27.000000','Jetzt',NULL,NULL,NULL,NULL,'2016-05-03 05:15:27.859186',1,66);
COMMIT;



DELIMITER $$

CREATE DEFINER = 'root'@'localhost' TRIGGER `trgin_project_head` BEFORE INSERT ON `project_head`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "PROJECT_HEAD_", NEW.project_head_ID ))$$

DELIMITER ;

DELIMITER $$

CREATE DEFINER = 'root'@'localhost' TRIGGER `trgin_project_worker` BEFORE INSERT ON `project_worker`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "project_worker" , NEW.project_worker_ID ))$$

DELIMITER ;

DELIMITER $$

CREATE DEFINER = 'root'@'localhost' TRIGGER `trgin_worktime` BEFORE INSERT ON `worktime`
  FOR EACH ROW
SET New.END     =      DATE_ADD( CURRENT_TIMESTAMP,  INTERVAL '-1 8' DAY_HOUR)$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;