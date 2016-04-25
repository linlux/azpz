# SQL Manager Lite for MySQL 5.5.3.46984
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : AZPZ
# Bearabeitet von Matthias Lüthke


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `AZPZ`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_german1_ci';

USE `azpz`;

#
# Struktur für die Tabelle `persons`:
#

CREATE TABLE `persons` (
  `persons_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `firstName` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `text` varchar(30) COLLATE latin1_german1_ci NOT NULL,
  `insert_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
  PRIMARY KEY (`persons_ID`) USING BTREE,
  UNIQUE KEY `persons_ID` (`persons_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci'
;

#
# Struktur für die Tabelle `projects_alt`:
#

CREATE TABLE `projects (
  `projects_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT NULL,
  `update_Date` timestamp(6) NULL DEFAULT NULL,
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`projects_ID`) USING BTREE,
  UNIQUE KEY `projects_ID` (`projects_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci'
;

#
# Struktur für die Tabelle `user`:
#

CREATE TABLE `user` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pw_clear` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `pw_hash` VARCHAR(30) COLLATE latin1_german1_ci NOT NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci'
;

#
# Struktur für die Tabelle `worktime`:
#

CREATE TABLE `worktime_alt` (
  `worktime_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `Insert_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` VARCHAR(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` TIMESTAMP(6) NULL DEFAULT '0000-00-00 00:00:00.000000' ON UPDATE CURRENT_TIMESTAMP(6),
  `timestamp` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `dk_persons_ID` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`worktime_ID`) USING BTREE,
  UNIQUE KEY `worktime_ID` (`worktime_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci'
;

CREATE TABLE `address` (
  `address_ID` bigint(20)  NOT NULL AUTO_INCREMENT,
  `address1` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address2` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `address3` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `city` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  `plz` varchar(10) COLLATE latin1_general_ci DEFAULT NULL,
  `Country_short` varchar(2) COLLATE latin1_general_ci DEFAULT NULL,
  `Country` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `fk_persons_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)  ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;