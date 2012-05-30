DROP DATABASE IF EXISTS `ufp_log`;
CREATE DATABASE `ufp_log`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

use ufp_log;

create table action_log
(
  id int(11) NOT NULL auto_increment comment '自增的ID',
  created_time datetime,
  
  app_key varchar(128),
  ad_slot_id int, 
  ad_ids varchar(1024),
  ad_types varchar(1024),
  action_type int,
  action_index int,
  
  sid varchar(128),
  device_id varchar(256),
  layout_type int,
  sdk_version varchar(128),
  protocol_verion varchar(128),
  orientation varchar(128),
  
  device_model varchar(128),
  network varchar(128),
  lat double,
  lng double,
  http_user_agent varchar(128),
  mac varchar(128) DEFAULT NULL,
  PRIMARY KEY  (`id`)
  ) ENGINE= MyISAM DEFAULT CHARSET=utf8;

