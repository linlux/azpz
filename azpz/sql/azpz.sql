
# Database : AZPZ
# Überarbeitet von Matthias Lüthke am 26.04.2016
# Überarbeitet von Matthias Lüthke am 26.04.2016
# Test git

SET FOREIGN_KEY_CHECKS=0;   

CREATE DATABASE IF NOT EXISTS azpz
    CHARACTER SET 'latin1'
    COLLATE 'latin1_general_ci';

USE `azpz`;

#
#  Prozeduren:
#  Erste Prozedure auch als Beispiel
DROP PROCEDURE IF EXISTS `table_exits`;

CREATE   PROCEDURE `table_exits`(IN `ptable_name` VARCHAR(30), OUT `count_table` INT)
        DETERMINISTIC
    SELECT COUNT(*) INTO count_table   
    FROM `information_schema`.`TABLES` WHERE 
    `information_schema`.`TABLES`.`TABLE_NAME` = ptable_name; 

#
# Struktur für die Tabelle `persons`:
# 
     CREATE TABLE IF NOT EXISTS `persons` (
    `persons_ID` bigint (20) NOT  NULL AUTO_INCREMENT,
    `name` VARCHAR(30)  NOT NULL,
    `firstName` VARCHAR(30)  DEFAULT NULL,
    `text` varchar(30)  NOT NULL,
    `insert_MB` varchar(30)  DEFAULT NULL,
    `update_MB` varchar(30)  DEFAULT NULL,
    `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
    `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
    `timestamp` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`persons_ID`) USING BTREE,
    UNIQUE KEY `persons_ID` (`persons_ID`) USING BTREE
  ) ENGINE=InnoDB    AUTO_INCREMENT=1; 


#
# Struktur für die Tabelle `projects`:  
#

CREATE TABLE IF NOT EXISTS `projects` (
  `projects_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` varchar(30)  DEFAULT NULL,
  `insert_MB` varchar(30)  DEFAULT NULL,
  `update_MB` varchar(30)  DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT NULL,
  `update_Date` timestamp(6) NULL DEFAULT NULL,
  `timestamp` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`projects_ID`) USING BTREE,
  UNIQUE KEY `projects_ID` (`projects_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1  
;

#
# Struktur für die Tabelle `user`:
#
Drop Table   IF  EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `pw_clear` VARCHAR(30)  NOT NULL,
  `pw_hash` VARCHAR(30)  NOT NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`user_ID`) USING BTREE,
  UNIQUE KEY `user_ID` (`user_ID`) USING BTREE,
  UNIQUE KEY `user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB

AUTO_INCREMENT=1  
;



# Struktur für die Tabelle `worktime`:
#
# Struktur für die Tabelle `worktime`:
#

CREATE TABLE IF NOT EXISTS `worktime` (
  `worktime_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fk_persons_ID` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`worktime_ID`) USING BTREE,
  UNIQUE KEY `worktime_ID` (`worktime_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1  
;

CREATE TABLE IF NOT EXISTS `address` (
  `address_ID` bigint(20)  NOT NULL AUTO_INCREMENT,
  `address1` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address2` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address3` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `city` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  `plz` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `Country_short` varchar(2) COLLATE latin1_general_ci DEFAULT NULL,
  `Country` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `fk_persons_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`address_ID`) USING BTREE,
  UNIQUE KEY `address_ID` (`address_ID`) USING BTREE
)  ENGINE=InnoDB
AUTO_INCREMENT=1  ;


CREATE TABLE IF NOT EXISTS `login` (
  `login_ID` bigint(20) NOT NULL,
  `fk_user_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`login_ID`) USING BTREE,
  UNIQUE KEY `login_ID` (`login_ID`) USING BTREE
) ;


#
# Data for the `persons` table  (LIMIT 0,500)
#

INSERT INTO `persons` (`persons_ID`, `name`, `firstName`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  (1,'Luethke','Matthias','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:55:49.931498'),
  (2,'Labsch','Martin','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:56:18.429800');
COMMIT;

#
# Data for the `user` table  (LIMIT 0,500)
#

INSERT INTO `user` (  `pw_clear`,  `user_name`, `fk_persons_ID`)           
      SELECT  "start",
       CONCAT     ( p.`firstName`,  p.`Name`,  p.`persons_ID` )   ,         
        p.`persons_ID`     
      from persons p 
       where   persons_ID 
      not in                
      (
       Select    fk_persons_ID  from user
      )

