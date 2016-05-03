
# Database : azpz
# Erstellt von Matthias Lüthke am 26.04.2016
# zuletzt Überarbeitet von Matthias Lüthke am 03.05.2016  13:27  


# Rechte für `azpz`@`%`

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, FILE, INDEX, ALTER, CREATE TEMPORARY TABLES, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON *.* TO 'AZPZ'@'%' IDENTIFIED BY PASSWORD '*67ADA74BF02D77B2DC4BF6694FB15A8339D558EB';

GRANT ALL PRIVILEGES ON `azpz`.* TO 'azpz'@'%';


CREATE DATABASE IF NOT EXISTS azpz
    CHARACTER SET 'latin1'
    COLLATE 'latin1_general_ci';

USE `azpz`;           

#
# Löschen von Datenbankobjekten
#

DROP FUNCTION IF EXISTS `table_exits`;
DROP PROCEDURE IF EXISTS `table_exits`;
DROP TABLE IF EXISTS `worktime`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `projects`;
DROP TABLE IF EXISTS `project_worker`;
DROP TABLE IF EXISTS `project_head`;
DROP TABLE IF EXISTS `persons`;
DROP TABLE IF EXISTS `login`;
DROP TABLE IF EXISTS `address`;
DROP VIEW IF EXISTS `v_projects` ;  
DROP PROCEDURE IF EXISTS `v_projects` ;  


#
#  Prozeduren:
#  Erste Prozedure auch als Beispiel
CREATE  PROCEDURE `table_exits`(
        IN `ptable_name` VARCHAR(30),
        OUT `count_table` INTEGER
    )
    DETERMINISTIC
    CONTAINS SQL     
    COMMENT ''
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
    `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
    `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
    `timestamp` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`persons_ID`) USING BTREE,
    UNIQUE KEY `persons_ID` (`persons_ID`) USING BTREE
  ) ENGINE=InnoDB    AUTO_INCREMENT=1; 


#
# Struktur für die Tabelle `projects`:  
#

CREATE TABLE IF NOT EXISTS `projects` (
  `projects_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` varchar(30)  DEFAULT NULL,
  `insert_MB` varchar(30)  DEFAULT NULL,
  `update_MB` varchar(30)  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT NULL,
  `update_Date` timestamp NULL DEFAULT NULL,
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
  `user_name` VARCHAR(30)    NOT NULL,
  `pw_clear` VARCHAR(30)  NOT NULL,
  `pw_hash` VARCHAR(30)   NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
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
  `start` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timediff` BIGINT(20) DEFAULT NULL,  
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`address_ID`) USING BTREE,
  UNIQUE KEY `address_ID` (`address_ID`) USING BTREE
)  ENGINE=InnoDB
AUTO_INCREMENT=1  ;

Drop Table   IF  EXISTS `login` ;

CREATE TABLE IF NOT EXISTS `login` (
  `login_ID` bigint(20) NOT NULL,
  `fk_user_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`login_ID`) USING BTREE,
  UNIQUE KEY `login_ID` (`login_ID`) USING BTREE
) ;


Drop Table   IF  EXISTS `project_worker` ;

CREATE TABLE  `project_worker` (
  `project_worker_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(30)    NOT NULL,
  `fk_persons_ID` bigint(20) NOT NULL,
  `fk_projects_ID` bigint(20) NOT NULL,      
  `insert_MB` VARCHAR(30)    DEFAULT NULL,
  `update_MB` VARCHAR(30)    DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  ,  
  PRIMARY KEY (`project_worker_ID`) USING BTREE,
  UNIQUE KEY `project_worker_ID` (`project_worker_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1  ;

CREATE   TRIGGER `trgin_project_worker` BEFORE INSERT ON `project_worker`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "project_worker" , NEW.project_worker_ID ));
      
Drop Table   IF  EXISTS `project_head` ;
 
CREATE TABLE IF NOT EXISTS `project_head` (
  `project_head_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30)    NOT NULL,
  `text` VARCHAR(30)    NOT NULL,
  `fk_projectworker_id`  BIGINT(20) NOT NULL,
  `insert_MB` VARCHAR(30)    DEFAULT NULL,
  `update_MB` VARCHAR(30)    DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,     
  PRIMARY KEY (`project_head_ID`) USING BTREE,
  UNIQUE KEY `project_head_ID` (`project_head_ID`) USING BTREE 
) ENGINE=InnoDB
AUTO_INCREMENT=1    
;

CREATE   TRIGGER `trgin_project_head` BEFORE INSERT ON `project_head`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "PROJECT_HEAD_", NEW.project_head_ID ));     
      
Commit;

#
# the persons 
#

INSERT INTO `persons` ( `name`, `firstName`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  ('Luethke','Matthias','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:55:49.931498'),
  ('Labsch','Martin','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:56:18.429800');
COMMIT;

#
# Data for the user_table  
#

DELETE FROM user ;
commit;

INSERT INTO `user` (  `pw_clear`,  `user_name`, `fk_persons_ID`)           
      SELECT  "start",
         CONCAT     ( p.`firstName`,  p.`Name`,  p.`persons_ID` )   ,         
        p.`persons_ID`     
      from persons p 
       where   persons_ID 
      in                
      (
       Select    `persons_ID`  from `persons`
      );
      
#
# Data for the `project_worker` table  (LIMIT 0,500)
#

Commit;

INSERT INTO `project_worker` ( `TEXT` )            
SELECT  
         CONCAT     (   p.`TEXT` )   
   
      from persons p 
       where    p.`persons_ID` 
       NOT
       IN               
      (
       Select  
        fk_persons_ID   from `project_worker`  
      );  

#
# kurz und schmutzig
#      
UPDATE 
  `project_worker`  
SET  
  `fk_persons_ID` = project_worker.`project_worker_ID` 
WHERE 
  `project_worker_ID` 
AND fk_persons_ID IS NULL;

Commit;    
     
#
#  für tabelle   project_head
#    
   
INSERT INTO `project_head` ( `name`, `text`, `fk_projectworker_id`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  ('Project_1','test a',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:10:24.184906'),
  ('Project_2','test b',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:44.529825'),
  ('Project_3','test c',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:46.898161'),
  ('Project_4','test d',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:50.597786'),
  ('Project_5','test f',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:51.814286'),
  ('Project_6','test',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-04 02:19:52.549366');
COMMIT;     


INSERT  INTO `worktime` ( `TEXT` )    
 Select text
 from
  `persons`   ; 
  
  COMMIT;   
  
  
  INSERT INTO `worktime` ( `start`, `end`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`, `fk_persons_ID`, `timediff`) VALUES
  ('0000-00-00 00:00:00.000000','2016-05-01 20:56:38.000000','Mein Projekt 1',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 04:56:38.133405',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Mein Projekt 2',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Mein Projekt  Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Mein Projekt 3',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Mein Projekt  Martin 4',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Mein Projekt 5',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Mein Projekt  Martin 6',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,44),
  (NULL,'2016-05-01 21:15:27.000000','Mein Projekt Oh Mann',NULL,NULL,NULL,NULL,'2016-05-03 05:15:27.859186',2,33);
COMMIT;

  
  CREATE ALGORITHM=UNDEFINED  SQL SECURITY DEFINER VIEW `v_projects`
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
  
 Commit;  
  
 INSERT INTO `projects` (   `text`   )       
  Select  CONCAT ( 'Mein Projekt _' ,   `text`   )   from `worktime`    ;    
  Commit;       
# Database : AZPZ
# Erstellt von Matthias Lüthke am 26.04.2016
# zuletzt Überarbeitet von Matthias Lüthke am 03.05.2016  13:12    

SET FOREIGN_KEY_CHECKS=0;   

CREATE DATABASE IF NOT EXISTS azpz
    CHARACTER SET 'latin1'
    COLLATE 'latin1_general_ci';

USE `azpz`;           

#
# Löschen von Datenbankobjekten
#

DROP FUNCTION IF EXISTS `table_exits`;
DROP PROCEDURE IF EXISTS `table_exits`;
DROP TABLE IF EXISTS `worktime`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `projects`;
DROP TABLE IF EXISTS `project_worker`;
DROP TABLE IF EXISTS `project_head`;
DROP TABLE IF EXISTS `persons`;
DROP TABLE IF EXISTS `login`;
DROP TABLE IF EXISTS `address`;
DROP VIEW IF EXISTS `v_projects` ;  
DROP PROCEDURE IF EXISTS `v_projects` ;  


#
#  Prozeduren:
#  Erste Prozedure auch als Beispiel
CREATE  PROCEDURE `table_exits`(
        IN `ptable_name` VARCHAR(30),
        OUT `count_table` INTEGER
    )
    DETERMINISTIC
    CONTAINS SQL     
    COMMENT ''
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
    `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
    `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
    `timestamp` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`persons_ID`) USING BTREE,
    UNIQUE KEY `persons_ID` (`persons_ID`) USING BTREE
  ) ENGINE=InnoDB    AUTO_INCREMENT=1; 


#
# Struktur für die Tabelle `projects`:  
#

CREATE TABLE IF NOT EXISTS `projects` (
  `projects_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `text` varchar(30)  DEFAULT NULL,
  `insert_MB` varchar(30)  DEFAULT NULL,
  `update_MB` varchar(30)  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT NULL,
  `update_Date` timestamp NULL DEFAULT NULL,
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
  `user_name` VARCHAR(30)    NOT NULL,
  `pw_clear` VARCHAR(30)  NOT NULL,
  `pw_hash` VARCHAR(30)   NULL,
  `fk_persons_ID` BIGINT(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
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
  `start` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timediff` BIGINT(20) DEFAULT NULL,  
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1  DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`address_ID`) USING BTREE,
  UNIQUE KEY `address_ID` (`address_ID`) USING BTREE
)  ENGINE=InnoDB
AUTO_INCREMENT=1  ;

Drop Table   IF  EXISTS `login` ;

CREATE TABLE IF NOT EXISTS `login` (
  `login_ID` bigint(20) NOT NULL,
  `fk_user_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1   NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`login_ID`) USING BTREE,
  UNIQUE KEY `login_ID` (`login_ID`) USING BTREE
) ;


Drop Table   IF  EXISTS `project_worker` ;

CREATE TABLE  `project_worker` (
  `project_worker_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(30)    NOT NULL,
  `fk_persons_ID` bigint(20) NOT NULL,
  `fk_projects_ID` bigint(20) NOT NULL,      
  `insert_MB` VARCHAR(30)    DEFAULT NULL,
  `update_MB` VARCHAR(30)    DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  ,  
  PRIMARY KEY (`project_worker_ID`) USING BTREE,
  UNIQUE KEY `project_worker_ID` (`project_worker_ID`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=1  ;

CREATE   TRIGGER `trgin_project_worker` BEFORE INSERT ON `project_worker`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "project_worker" , NEW.project_worker_ID ));
      
Drop Table   IF  EXISTS `project_head` ;
 
CREATE TABLE IF NOT EXISTS `project_head` (
  `project_head_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30)    NOT NULL,
  `text` VARCHAR(30)    NOT NULL,
  `fk_projectworker_id`  BIGINT(20) NOT NULL,
  `insert_MB` VARCHAR(30)    DEFAULT NULL,
  `update_MB` VARCHAR(30)    DEFAULT NULL,
  `insert_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,     
  PRIMARY KEY (`project_head_ID`) USING BTREE,
  UNIQUE KEY `project_head_ID` (`project_head_ID`) USING BTREE 
) ENGINE=InnoDB
AUTO_INCREMENT=1    
;

CREATE   TRIGGER `trgin_project_head` BEFORE INSERT ON `project_head`
  FOR EACH ROW
SET New.TEXT =
      COALESCE(New.TEXT, CONCAT( "PROJECT_HEAD_", NEW.project_head_ID ));     
      
Commit;

#
# the persons 
#

INSERT INTO `persons` ( `name`, `firstName`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  ('Luethke','Matthias','Test',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:55:49.931498'),
  ('Labsch','Martin','Test Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-01 15:56:18.429800');
COMMIT;

#
# Data for the user_table  
#

DELETE FROM user ;
commit;

INSERT INTO `user` (  `pw_clear`,  `user_name`, `fk_persons_ID`)           
      SELECT  "start",
         CONCAT     ( p.`firstName`,  p.`Name`,  p.`persons_ID` )   ,         
        p.`persons_ID`     
      from persons p 
       where   persons_ID 
      in                
      (
       Select    `persons_ID`  from `persons`
      );
      
#
# Data for the `project_worker` table  (LIMIT 0,500)
#

Commit;

INSERT INTO `project_worker` ( `TEXT` )            
SELECT  
         CONCAT     (   p.`TEXT` )   
   
      from persons p 
       where    p.`persons_ID` 
       NOT
       IN               
      (
       Select  
        fk_persons_ID   from `project_worker`  
      );  

#
# kurz und schmutzig
#      
UPDATE 
  `project_worker`  
SET  
  `fk_persons_ID` = project_worker.`project_worker_ID` 
WHERE 
  `project_worker_ID` 
AND fk_persons_ID IS NULL;

Commit;    
     
#
#  für tabelle   project_head
#    
   
INSERT INTO `project_head` ( `name`, `text`, `fk_projectworker_id`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`) VALUES
  ('Project_1','test a',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:10:24.184906'),
  ('Project_2','test b',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:44.529825'),
  ('Project_3','test c',1,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:46.898161'),
  ('Project_4','test d',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:50.597786'),
  ('Project_5','test f',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 02:19:51.814286'),
  ('Project_6','test',2,NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-04 02:19:52.549366');
COMMIT;     


INSERT  INTO `worktime` ( `TEXT` )    
 Select text
 from
  `persons`   ; 
  
  COMMIT;   
  
  
  INSERT INTO `worktime` ( `start`, `end`, `text`, `insert_MB`, `update_MB`, `insert_Date`, `update_Date`, `timestamp`, `fk_persons_ID`, `timediff`) VALUES
  ('0000-00-00 00:00:00.000000','2016-05-01 20:56:38.000000','Mein Projekt 1',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 04:56:38.133405',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Mein Projekt 2',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:09:50.000000','Mein Projekt  Martin',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:09:50.761627',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Mein Projekt 3',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:09.000000','Mein Projekt  Martin 4',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:09.376318',1,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Mein Projekt 5',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,NULL),
  ('0000-00-00 00:00:00.000000','2016-05-01 21:14:28.000000','Mein Projekt  Martin 6',NULL,NULL,'0000-00-00 00:00:00.000000','0000-00-00 00:00:00.000000','2016-05-03 05:14:28.323604',2,44),
  (NULL,'2016-05-01 21:15:27.000000','Mein Projekt Oh Mann',NULL,NULL,NULL,NULL,'2016-05-03 05:15:27.859186',2,33);
COMMIT;


 

  CREATE ALGORITHM=UNDEFINED  SQL SECURITY DEFINER VIEW `v_projects`
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
  
 Commit;  
  
 INSERT INTO `projects` (   `text`   )       
  Select  CONCAT ( 'Mein Projekt _' ,   `text`   )   from `worktime`    ;    
  Commit;      
  
#
# Wenn gezeigt wird  MySQL lieferte ein leeres Resultat zurück (d.h. null Datensätze).  ist es OK
#   
   