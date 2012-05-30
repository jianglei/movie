DROP DATABASE IF EXISTS `ufp`;
CREATE DATABASE `ufp`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

Use ufp;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `email` varchar(64) NOT NULL COMMENT '账户地址',
  `password` varchar(64) NOT NULL COMMENT '密码，MD5加密后',
  `type` enum('publisher','channel','admin','admin_view','admin_op') NOT NULL DEFAULT 'publisher' COMMENT '用户组',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话或手机',
  `info` varchar(128) DEFAULT NULL COMMENT '更多信息',
  `logo_url` varchar(128) DEFAULT NULL COMMENT '账户定制的Logo地址',
  `logo_name` varchar(128) DEFAULT NULL COMMENT '账户定制的名字',
  `last_login_time` datetime DEFAULT NULL,
  `login_times` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `status` enum('normal','pause','delete') NOT NULL DEFAULT 'normal' COMMENT '状态',
  `blacklist` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33716 DEFAULT CHARSET=utf8;
  
insert into user(email, password, `type`, nickname) values('admin@umeng.com', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'admin');  
insert into user(email, password, `type`, nickname) values('demo@umeng.com', 'fe01ce2a7fbac8fafaed7c982a04e229', 'publisher', 'demo');  
insert into user(email, password, `type`, nickname) values('lichunxia@umeng.com', 'be1cbdf402615d259b8e76dc617ac552', 'publisher', 'lichunxia');

CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `user_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `appkey` varchar(64) DEFAULT NULL COMMENT '应用的key与umeng主站对应或新的',
  `platform` enum('android','iOS') NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '应用名称',
  `description` varchar(256) DEFAULT NULL COMMENT '应用描述',
  `status` enum('normal','pause','delete') DEFAULT 'normal',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32286 DEFAULT CHARSET=utf8;

CREATE TABLE `ad_slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `user_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `name` varchar(32) NOT NULL COMMENT '广告位名称',
  `landing_size` varchar(128) DEFAULT NULL COMMENT '入口尺寸',
  `landing_type` enum('banner','custom','bigimage','embed','wap','text') DEFAULT NULL COMMENT '入口类型',
  `landing_images` varchar(1024) DEFAULT NULL COMMENT '入口图片，用,分割',
  `platform` enum('android','iOS') NOT NULL,
  `style` varchar(32) DEFAULT NULL COMMENT '样式',
  `device` varchar(128) DEFAULT NULL,
  `channels` varchar(32) DEFAULT NULL COMMENT '适用渠道',
  `areas` varchar(2048) DEFAULT NULL,
  `timeslots` varchar(168) DEFAULT NULL COMMENT '适用时段',
  `enable_preload` enum('yes','no') NOT NULL COMMENT '是否缓存广告',
  `enable_page` enum('yes','no') NOT NULL COMMENT '广告可否翻页',
  `status` enum('normal','pause','delete') NOT NULL DEFAULT 'normal',
  `state` int NOT NULL DEFAULT 0 NOT NULL COMMENT '系统状态',
  `xp_enable` tinyint(4) DEFAULT '0' COMMENT 'enable xp ads',
  `xp_percent` int(11) DEFAULT '0' COMMENT 'xp ads percent',
  `appkey` varchar(32) DEFAULT NULL COMMENT 'appkey',
  `ad_network_strategy` enum('none','fill','percent') DEFAULT 'none',
  `uads_enable` tinyint(4) DEFAULT '0' COMMENT 'enable umeng ads',
  `uads_percent` int(11) DEFAULT '0' COMMENT 'umeng ads percent',
  `uads_key` varchar(64) DEFAULT NULL COMMENT 'mediaid',
  `uads_key2` varchar(64) DEFAULT NULL COMMENT 'slotxid',
  `uads_price_type` enum('','impression','click','download') DEFAULT '' COMMENT 'price type',
  `uads_price` double DEFAULT '0' COMMENT 'ads price',
  `text_size` varchar(128) DEFAULT NULL COMMENT '文字大小',
  `display_strategy` varchar(32) DEFAULT NULL,
  `anim_in` varchar(32) DEFAULT '0',
  `template` varchar(64) DEFAULT 'applist',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40336 DEFAULT CHARSET=utf8

CREATE TABLE `ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `user_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `ad_order_id` int(11) DEFAULT NULL COMMENT '所属订单的id',
  `name` varchar(32) NOT NULL COMMENT '广告名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `platform` enum('android','iOS') NOT NULL,
  `areas` varchar(2048) DEFAULT NULL COMMENT '地域',
  `networks` varchar(64) DEFAULT 'all' COMMENT '适用网络',
  `channels` varchar(32) DEFAULT NULL COMMENT '适用渠道',
  `budget` enum('none','limit') NOT NULL DEFAULT 'none' COMMENT '是否有预算',
  `budget_limit` double DEFAULT NULL COMMENT '预算',
  `price_type` enum('none','impression','click','download') DEFAULT 'none' COMMENT '计费方式',
  `cost_price` double DEFAULT NULL,
  `prior` int(11) DEFAULT '2' COMMENT '优先级',
  `status` enum('normal', 'pause', 'delete') NOT NULL DEFAULT 'normal' COMMENT '状态',
  `state` int NOT NULL DEFAULT 0 NOT NULL COMMENT '系统状态',
  `system_status` enum('normal','limit') NOT NULL DEFAULT 'normal',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47644 DEFAULT CHARSET=utf8

CREATE TABLE `ad_content` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `entry_type` enum('banner','custom','bigimage','embed','wap','text') DEFAULT NULL,
  `content_type` enum('app','web') NOT NULL DEFAULT 'app' COMMENT '广告类型',
  `landing_type` enum('detail','download','webview','browser','gotoAppStore') NOT NULL COMMENT '打开方式',
  `display_type` enum('standard','image','text') NOT NULL DEFAULT 'standard' COMMENT '广告形式',
  `url` varchar(128) DEFAULT NULL COMMENT '下载地址',
  `icon` varchar(128) DEFAULT NULL COMMENT '图标地址',
  `img` varchar(128) DEFAULT NULL COMMENT 'banner地址',
  `title` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `text_font` varchar(64) DEFAULT NULL COMMENT '文字字体',
  `text_size` varchar(64) DEFAULT NULL COMMENT '文字大小',
  `text_color` varchar(64) DEFAULT NULL COMMENT '文字颜色',
  `ad_words` varchar(64) DEFAULT NULL COMMENT '广告语',
  `provider` varchar(64) DEFAULT NULL COMMENT '开发商',
  `description` varchar(512) DEFAULT NULL,
  `price` varchar(64) DEFAULT NULL COMMENT '在商店的标价，默认为0,自动抓取',
  `platform` enum('android','iOS') NOT NULL,
  `template` varchar(64) DEFAULT 'applist',
  `size` int(11) DEFAULT NULL COMMENT '大小,自动抓取',
  `app_package_name` varchar(128) DEFAULT NULL,
  `app_version_code` varchar(64) DEFAULT NULL,
  `app_version_name` varchar(64) DEFAULT NULL,
  `app_store_id` varchar(64) DEFAULT NULL,
  `landing_size` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ad_holder` (
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `ad_id` int(11) NOT NULL COMMENT '广告的ID',
  `ad_slot_id` int(11) NOT NULL COMMENT '广告位的ID',
  `prior` int(11) DEFAULT '2' COMMENT '优先级',
  `ad_status` enum('normal','pause','delete') NOT NULL DEFAULT 'normal' COMMENT '状态',
  `adslot_status` enum('normal','pause','delete') NOT NULL DEFAULT 'normal' COMMENT '状态',
  `ad_state` int NOT NULL DEFAULT 0 COMMENT '状态',
  `adslot_state` int NOT NULL DEFAULT 0 COMMENT '状态',
  `dtr` double(15,5) DEFAULT '1.00000',
  `weight` double(15,6) DEFAULT '1.000000',
  `system_status` enum('normal','limit') NOT NULL DEFAULT 'normal',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ad_id`,`ad_slot_id`),
  KEY `ad_holder_index` (`ad_slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  


CREATE TABLE `ad_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `user_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `name` varchar(32) NOT NULL COMMENT '订单名',
  `customer` varchar(64) DEFAULT NULL COMMENT '客户',
  `contact` varchar(64) DEFAULT NULL COMMENT '广告公司联系人',
  `comments` varchar(256) DEFAULT NULL COMMENT '说明',
  `status` enum('normal','pause','delete') NOT NULL DEFAULT 'normal' COMMENT '状态',
  `state` int NOT NULL DEFAULT 0 NOT NULL COMMENT '系统状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41701 DEFAULT CHARSET=utf8;

CREATE TABLE `user_op_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `op_date` datetime DEFAULT NULL,
  `op_type` varchar(32) DEFAULT NULL,
  `object_type` varchar(32) DEFAULT NULL COMMENT '新增: add, 编辑: edit, 修改状态: change, 触发: trigger, 删除: delete, relate: 关联, unrelate: 取消关联',
  `object_key` varchar(32) DEFAULT NULL COMMENT '操作对象key',
  `object_value` varchar(1024) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '账户的编号',
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `op_date` datetime DEFAULT NULL,
  `op_type` varchar(32) DEFAULT NULL,
  `object_type` varchar(32) DEFAULT NULL COMMENT '新增: add, 编辑: edit, 修改状态: change, 删除: delete',
  `object_id` int DEFAULT NULL COMMENT '操作对象id',
  `object_oldvalue` varchar(1024) DEFAULT NULL,
  `object_newvalue` varchar(1024) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '账户的编号',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人员的编号',
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
create table role 
(
  id int(11) NOT NULL auto_increment comment '自增的ID',
  created_time datetime,
  updated_time datetime,

  utype enum('admin','admin_view','admin_op','publisher') not null comment '用户组',
  permission varchar(128) comment '权限设置',

  PRIMARY KEY  (`id`)
) ENGINE= InnoDB DEFAULT CHARSET=utf8;

create table ad_holder_counter
(
  `date` date NOT NULL COMMENT '日期',
  `ad_slot_id` int(11) NOT NULL COMMENT '广告位的ID',
  `ad_id` int(11) NOT NULL COMMENT '广告的ID',
  
  `category` varchar(64) NULL COMMENT 'UFP/XP/XP_ADS',
  `updated_time` datetime default NULL COMMENT '修改时间',
  
  `impression` int(11) NOT NULL COMMENT '点击展示次数', 
  `download` int(11) NOT NULL COMMENT '下载次数', 
  `click` int(11) NOT NULL COMMENT '点击次数,包括appstore点击次数', 
  `appstore_click` int(11) NOT NULL COMMENT 'appstore点击次数', 
  `revenue_impression` int(11) NOT NULL COMMENT '收益展示次数',
  `revenue_download` int(11) NOT NULL COMMENT '收益下载次数',
  `revenue_click` int(11) NOT NULL COMMENT '收益点击次数',
  `revenue_appstore_click` int(11) NOT NULL COMMENT '收益 appstore点击次数',
  `avg_price_click` int(11) NOT NULL COMMENT '平均点击价格次数',
  `revenue` double NOT NULL COMMENT '收益',

  PRIMARY KEY  (`date`, `ad_slot_id`, `ad_id`)
  ) ENGINE= InnoDB DEFAULT CHARSET=utf8;
 
  CREATE TABLE `user_action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `op_date` datetime DEFAULT NULL COMMENT '操作时间',
  `op_type` varchar(32) DEFAULT NULL COMMENT '操作类型: 新增: add, 编辑: edit, 修改状态: change, 删除: delete, 登录: login',
  `object_type` varchar(32) DEFAULT NULL COMMENT '操作对象: app, adslot, ad, adcontent, adorder, user', 
  `object_id` int(11) DEFAULT NULL COMMENT '操作对象id',
  `field_name` varchar(32) DEFAULT NULL COMMENT '修改的字段名', 
  `field_oldvalue` varchar(1024) DEFAULT NULL COMMENT '修改字段的旧值',
  `field_newvalue` varchar(1024) DEFAULT NULL COMMENT '修改字段的新值',
  `user_id` int(11) DEFAULT NULL COMMENT '账户的id',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人员的id',
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=638 DEFAULT CHARSET=utf8;

CREATE TABLE `user_op_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增的ID',
  `op_date` datetime DEFAULT NULL COMMENT '操作时间',
  `op_type` varchar(32) DEFAULT NULL COMMENT '操作类型: 新增: add, 编辑: edit, 修改状态: change, 触发: trigger, 删除: delete, 关联: relate, 取消关联: unrelate, 填充物料: padding',
  `object_type` varchar(32) DEFAULT NULL COMMENT '操作对象: app, adslot, ad, adcontent, adorder, user，adholder',
  `object_key` varchar(128) DEFAULT NULL COMMENT '操作对象key',
  `object_value` varchar(1024) DEFAULT NULL COMMENT '操作对象的新值',
  `user_id` int(11) DEFAULT NULL COMMENT '账户的id',
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1856 DEFAULT CHARSET=utf8;


  drop view op_dashboard;
  create view op_dashboard as select date, sum(impression) as impression, sum(click) as click, sum(appstore_click) as appstore_click, sum(download) as download from ad_holder_counter group by ad_holder_counter.date;
  
 drop view op_ad_slot;
  CREATE VIEW op_ad_slot AS
  select
  `ahc`.`date` AS `date`,
  `ahc`.`ad_slot_id` AS `ad_slot_id`,
  SUBSTRING(`ast`.`name` from 1 for 12) AS `ad_slot_name`,
  (select `u`.`nickname` from `user` `u` where (`u`.`id` = `ast`.`user_id`)) AS `user_nickname`,
  sum(`ahc`.`impression`) AS `impression`,
  sum(`ahc`.`click`) AS `click`,
  sum(`ahc`.`appstore_click`) AS `appstore_click`,
  sum(`ahc`.`download`) AS `download`,
  concat("ex.mobmore.com/api/wap?slot_id=", `ahc`.`ad_slot_id`) as wap_url
  from (`ad_holder_counter` `ahc` left join `ad_slot` `ast` on((`ast`.`id` = `ahc`.`ad_slot_id`)))
  group by `ahc`.`ad_slot_id`,`ahc`.`date`;