--
-- Datenbank: `azpz`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `address`
--
-- Erstellt am: 25. Apr 2016 um 10:06
--

CREATE TABLE `address` (
  `address_ID` bigint(20) NOT NULL,
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
) ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `login`
--
-- Erstellt am: 25. Apr 2016 um 09:34
--

CREATE TABLE `login` (
  `login_ID` bigint(20) NOT NULL,
  `fk_user_ID` bigint(20) NOT NULL,
  `text` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `persons`
--
-- Erstellt am: 25. Apr 2016 um 09:06
--

CREATE TABLE `persons` (
  `persons_ID` bigint(20) NOT NULL,
  `name` varchar(30) COLLATE latin1_german1_ci NOT NULL,
  `firstName` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `text` varchar(30) COLLATE latin1_german1_ci NOT NULL,
  `insert_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projects`
--
-- Erstellt am: 25. Apr 2016 um 09:09
--

CREATE TABLE `projects` (
  `projects_ID` bigint(20) NOT NULL,
  `name` varchar(30) COLLATE latin1_german1_ci NOT NULL,
  `text` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT NULL,
  `update_Date` timestamp(6) NULL DEFAULT NULL,
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--
-- Erstellt am: 25. Apr 2016 um 09:36
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `pw_clear` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `pw_hash` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `fk_persons_ID` bigint(20) NOT NULL,
  `insert_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `worktime`
--
-- Erstellt am: 25. Apr 2016 um 09:10
--

CREATE TABLE `worktime` (
  `worktime_ID` bigint(20) NOT NULL,
  `start` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `Insert_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `update_MB` varchar(30) CHARACTER SET latin1 COLLATE latin1_german1_ci DEFAULT NULL,
  `insert_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `update_Date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
) ;

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `address`
--
ALTER TABLE `address`
  MODIFY `address_ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `login`
--
ALTER TABLE `login`
  MODIFY `login_ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `projects`
--
ALTER TABLE `projects`
  MODIFY `projects_ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `worktime`
--
ALTER TABLE `worktime`
  MODIFY `worktime_ID` bigint(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
