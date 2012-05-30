DROP DATABASE IF EXISTS `ufp`;
CREATE DATABASE `ufp`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';
GRANT ALL PRIVILEGES ON ufp.* TO 'ufp'@'localhost' IDENTIFIED BY 'ufp';
GRANT ALL PRIVILEGES ON ufp.* TO 'ufp'@'10.%' IDENTIFIED BY 'ufp';
flush privileges;

DROP DATABASE IF EXISTS `ufp_log`;
CREATE DATABASE `ufp_log`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';
GRANT ALL PRIVILEGES ON ufp_log.* TO 'ufp'@'localhost' IDENTIFIED BY 'ufp';
GRANT ALL PRIVILEGES ON ufp_log.* TO 'ufp'@'10.%' IDENTIFIED BY 'ufp';
flush privileges;
