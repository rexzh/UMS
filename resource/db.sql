-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ums
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dict_type`
--

DROP TABLE IF EXISTS `dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL,
  `name` varchar(45) NOT NULL,
  `global` bit(1) NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_type`
--

LOCK TABLES `dict_type` WRITE;
/*!40000 ALTER TABLE `dict_type` DISABLE KEYS */;
INSERT INTO `dict_type` VALUES (1,'revenue','收入','',NULL),(2,'charge','支出','',NULL),(3,'level','等级','\0',NULL);
/*!40000 ALTER TABLE `dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_meta`
--

DROP TABLE IF EXISTS `file_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `uri` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_meta`
--

LOCK TABLES `file_meta` WRITE;
/*!40000 ALTER TABLE `file_meta` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_dict`
--

DROP TABLE IF EXISTS `global_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `global_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gdict_type_idx` (`type_id`),
  CONSTRAINT `fk_gdict_type` FOREIGN KEY (`type_id`) REFERENCES `dict_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_dict`
--

LOCK TABLES `global_dict` WRITE;
/*!40000 ALTER TABLE `global_dict` DISABLE KEYS */;
INSERT INTO `global_dict` VALUES (1,2,1,'采购',NULL),(2,2,3,'电费',NULL),(3,1,1,'用户缴费',NULL),(4,1,3,'补贴',NULL);
/*!40000 ALTER TABLE `global_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l10n`
--

DROP TABLE IF EXISTS `l10n`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `l10n` (
  `code` varchar(8) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l10n`
--

LOCK TABLES `l10n` WRITE;
/*!40000 ALTER TABLE `l10n` DISABLE KEYS */;
INSERT INTO `l10n` VALUES ('en_us','English'),('zh_cn','简体中文');
/*!40000 ALTER TABLE `l10n` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local_dict`
--

DROP TABLE IF EXISTS `local_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `local_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `org_id` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ldict_type_idx` (`type_id`),
  KEY `fk_ldict_org_idx` (`org_id`),
  CONSTRAINT `fk_ldict_org` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ldict_type` FOREIGN KEY (`type_id`) REFERENCES `dict_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_dict`
--

LOCK TABLES `local_dict` WRITE;
/*!40000 ALTER TABLE `local_dict` DISABLE KEYS */;
INSERT INTO `local_dict` VALUES (1,3,6,1,'L1',NULL),(2,3,6,2,'L2',NULL);
/*!40000 ALTER TABLE `local_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `display_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Data Configuration',90),(2,'System Management',80),(3,'Help',99),(4,'User Settings',95);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_l10n`
--

DROP TABLE IF EXISTS `msg_l10n`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_l10n` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(160) NOT NULL,
  `code` varchar(8) NOT NULL,
  `trans_msg` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_msg_code` (`msg`,`code`),
  KEY `fk_l10n_msg` (`code`),
  CONSTRAINT `fk_l10n_msg` FOREIGN KEY (`code`) REFERENCES `l10n` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_l10n`
--

LOCK TABLES `msg_l10n` WRITE;
/*!40000 ALTER TABLE `msg_l10n` DISABLE KEYS */;
INSERT INTO `msg_l10n` VALUES (1,'About','en_us','About'),(2,'About','zh_cn','关于'),(3,'Add','en_us','Add'),(4,'Add','zh_cn','添加'),(5,'Address','en_us','Address'),(6,'Address','zh_cn','地址'),(7,'Apply','en_us','Apply'),(8,'Apply','zh_cn','应用'),(9,'Attention','en_us','Attention'),(10,'Attention','zh_cn','注意'),(11,'Authentication Fail','en_us','Authentication Fail'),(12,'Authentication Fail','zh_cn','认证失败'),(13,'Authorization','en_us','Authorization'),(14,'Authorization','zh_cn','授权'),(15,'Built-in role can\'t be changed','en_us','Built-in role can\'t be changed'),(16,'Built-in role can\'t be changed','zh_cn','内置角色不能修改'),(17,'Built-in role can\'t be removed','en_us','Built-in role can\'t be removed'),(18,'Built-in role can\'t be removed','zh_cn','内置角色不能删除'),(19,'Business','en_us','Business'),(20,'Business','zh_cn','业务'),(21,'Can\'t delete current user','en_us','Can\'t delete current user'),(22,'Can\'t delete current user','zh_cn','不能删除当前用户'),(23,'Cancel','en_us','Cancel'),(24,'Cancel','zh_cn','取消'),(25,'Code','en_us','Code'),(26,'Code','zh_cn','代码'),(27,'Comment','en_us','Comment'),(28,'Comment','zh_cn','备注'),(29,'Confirm','en_us','Confirm'),(30,'Confirm','zh_cn','确认'),(31,'Criteria','en_us','Criteria'),(32,'Criteria','zh_cn','条件'),(33,'Data Configuration','en_us','Data Configuration'),(34,'Data Configuration','zh_cn','数据配置'),(35,'Delete','en_us','Delete'),(36,'Delete','zh_cn','删除'),(37,'Description','en_us','Description'),(38,'Description','zh_cn','描述'),(39,'Dictionary Type','en_us','Dictionary Type'),(40,'Dictionary Type','zh_cn','字典类型'),(41,'DisplayOrder','en_us','Display Order'),(42,'DisplayOrder','zh_cn','显示顺序'),(43,'Enabled','en_us','Enabled'),(44,'Enabled','zh_cn','可用'),(45,'Error','en_us','Error'),(46,'Error','zh_cn','错误'),(47,'Frequency','en_us','Frequency'),(48,'Frequency','zh_cn','频率'),(49,'Global','en_us','Global'),(50,'Global','zh_cn','全局'),(51,'Global Dictionary','en_us','Global Dictionary'),(52,'Global Dictionary','zh_cn','全局字典'),(53,'Go','en_us','Go'),(54,'Go','zh_cn','跳转'),(55,'Help','en_us','Help'),(56,'Help','zh_cn','帮助'),(57,'Home','en_us','Home'),(58,'Home','zh_cn','首页'),(59,'Icon','en_us','Icon'),(60,'Icon','zh_cn','图标'),(61,'Input Password','en_us','Input Password'),(62,'Input Password','zh_cn','输入密码'),(63,'Item(s)','zh_cn','项'),(64,'Item(s)','en_us','Item(s)'),(65,'Key','en_us','Key'),(66,'Key','zh_cn','键'),(67,'Language','en_us','Language'),(68,'Language','zh_cn','语言'),(69,'Link','en_us','Link'),(70,'Link','zh_cn','链接'),(71,'Local Dictionary','en_us','Local Dictionary'),(72,'Local Dictionary','zh_cn','局部字典'),(73,'Localization','en_us','Localization'),(74,'Localization','zh_cn','本地化'),(75,'Login','en_us','Login'),(76,'Login','zh_cn','登录'),(77,'Logout','en_us','Logout'),(78,'Logout','zh_cn','注销'),(79,'Menu','en_us','Menu'),(80,'Menu','zh_cn','菜单'),(81,'MessageCount','en_us','Message Count'),(82,'MessageCount','zh_cn','消息数量'),(83,'Modify','en_us','Modify'),(84,'Modify','zh_cn','修改'),(85,'ms','en_us','ms'),(86,'ms','zh_cn','毫秒'),(87,'Name','en_us','Name'),(88,'Name','zh_cn','名称'),(89,'New Password','en_us','New Password'),(90,'New Password','zh_cn','新密码'),(91,'New Password Again','en_us','New Password Again'),(92,'New Password Again','zh_cn','确认新密码'),(93,'New password mismatch','en_us','New password mismatch'),(94,'New password mismatch','zh_cn','新密码不一致'),(95,'No Permission','en_us','No Permission'),(96,'No Permission','zh_cn','没有权限'),(97,'None','en_us','N/A'),(98,'None','zh_cn','无效'),(99,'OK','en_us','OK'),(100,'OK','zh_cn','确认'),(101,'Old Password','en_us','Old Password'),(102,'Old Password','zh_cn','旧密码'),(103,'Operation','en_us','Operation'),(104,'Operation','zh_cn','操作'),(105,'Organization','en_us','Organization'),(106,'Organization','zh_cn','机构'),(107,'Page(s)','zh_cn','页'),(108,'Page(s)','en_us','Page(s)'),(109,'ParentMenu','en_us','Parent Menu'),(110,'ParentMenu','zh_cn','上级菜单'),(111,'Password','en_us','Password'),(112,'Password','zh_cn','密码'),(113,'Password can\'t be empty','en_us','Password can\'t be empty'),(114,'Password can\'t be empty','zh_cn','密码不能为空'),(115,'Password min length','en_us','Password length must great then 6'),(116,'Password min length','zh_cn','密码至少6位'),(117,'Password Setting','en_us','Password Setting'),(118,'Password Setting','zh_cn','密码设置'),(119,'Please select','en_us','Please select'),(120,'Please select','zh_cn','请选择'),(121,'Preference','en_us','User Preference'),(122,'Preference','zh_cn','用户偏好'),(123,'Product','en_us','Product'),(124,'Product','zh_cn','产品'),(125,'Register','en_us','Register'),(126,'Register','zh_cn','注册'),(127,'Reset','en_us','Reset'),(128,'Reset','zh_cn','重置'),(129,'ResetPassword','en_us','Reset Password'),(130,'ResetPassword','zh_cn','重置密码'),(131,'Role','en_us','Role'),(132,'Role','zh_cn','角色'),(133,'Save','en_us','Save'),(134,'Save','zh_cn','保存'),(135,'Save success','en_us','Save success'),(136,'Save success','zh_cn','保存成功'),(137,'Search','en_us','Search'),(138,'Search','zh_cn','查询'),(139,'Select Organization','en_us','Select Organization'),(140,'Select Organization','zh_cn','选择机构'),(141,'Server Side Error','en_us','Server Side Error'),(142,'Server Side Error','zh_cn','服务器错误'),(143,'Status','en_us','Status'),(144,'Status','zh_cn','状态'),(145,'Submenu','en_us','Submenu'),(146,'Submenu','zh_cn','子菜单'),(147,'Success','en_us','Success'),(148,'Success','zh_cn','成功'),(149,'System Config','en_us','System Config'),(150,'System Config','zh_cn','系统配置'),(151,'System Management','en_us','System Management'),(152,'System Management','zh_cn','系统管理'),(153,'Tel','en_us','Tel'),(154,'Tel','zh_cn','电话'),(155,'Time','en_us','Time'),(156,'Time','zh_cn','时间'),(157,'TimeZone','en_us','Time Zone'),(158,'TimeZone','zh_cn','时区'),(159,'Total','en_us','Total'),(160,'Total','zh_cn','总共'),(161,'Type','en_us','Type'),(162,'Type','zh_cn','类型'),(163,'Unknown','en_us','Unknown'),(164,'Unknown','zh_cn','未知'),(165,'User','en_us','User'),(166,'User','zh_cn','用户'),(167,'User Settings','en_us','User Settings'),(168,'User Settings','zh_cn','用户设置'),(169,'UserCode','en_us','User Code'),(170,'UserCode','zh_cn','用户名'),(171,'UserName','en_us','User Name'),(172,'UserName','zh_cn','姓名'),(173,'UserRole','en_us','User\'s Role'),(174,'UserRole','zh_cn','用户角色'),(175,'Value','en_us','Value'),(176,'Value','zh_cn','值'),(177,'Version','en_us','Version'),(178,'Version','zh_cn','版本'),(179,'Warning','en_us','Warning'),(180,'Warning','zh_cn','异常'),(181,'Language Code','en_us','Language Code'),(182,'Language Code','zh_cn','语言代码'),(188,'ProductType','en_us','Product Type'),(189,'ProductType','zh_cn','产品类型'),(192,'StartTime','en_us','Start Time'),(193,'StartTime','zh_cn','开始时间'),(194,'EndTime','en_us','End Time'),(195,'EndTime','zh_cn','结束时间'),(196,'Price','en_us','Price'),(197,'Price','zh_cn','价格'),(198,'Location','en_us','Location'),(199,'Location','zh_cn','位置'),(200,'Picture','en_us','Picture'),(201,'Picture','zh_cn','图片'),(202,'Delete success','en_us','Delete success'),(203,'Delete success','zh_cn','删除成功'),(210,'FileID','en_us','FileID'),(211,'FileID','zh_cn','文件编号'),(212,'Can\'t be empty','en_us','can\'t be empty'),(213,'Can\'t be empty','zh_cn','不能为空'),(215,'Must be number','en_us','must be number'),(216,'Must be number','zh_cn','必须为数字'),(217,'Currency','en_us','Currency'),(218,'Currency','zh_cn','货币'),(219,'ParentID','en_us','Parent ID'),(220,'ParentID','zh_cn','父节点ID'),(221,'Individual','en_us','Individual'),(222,'Individual','zh_cn','个人'),(223,'Group','en_us','Group'),(224,'Group','zh_cn','团队'),(225,'CountryRegion','en_us','Country&Region'),(226,'CountryRegion','zh_cn','国家和地区'),(227,'City','en_us','City'),(228,'City','zh_cn','城市'),(229,'UserType','en_us','User Type'),(230,'UserType','zh_cn','用户类型'),(231,'Consumer','en_us','Consumer'),(232,'Consumer','zh_cn','消费者'),(233,'Supplier','en_us','Supplier'),(234,'Supplier','zh_cn','供应商'),(235,'SupplierApproved','en_us','Can Publish'),(236,'SupplierApproved','zh_cn','可发布'),(237,'PayType','en_us','Pay Type'),(238,'PayType','zh_cn','支付方式'),(239,'AliPay','en_us','AliPay'),(240,'AliPay','zh_cn','支付宝'),(241,'TenPay','en_us','TenPay'),(242,'TenPay','zh_cn','财付通'),(243,'PayPal','en_us','PayPal'),(244,'PayPal','zh_cn','贝宝'),(245,'Mail','en_us','Mail'),(246,'Mail','zh_cn','邮件'),(247,'Mobile','en_us','Mobile'),(248,'Mobile','zh_cn','手机'),(249,'Username or Password wrong','en_us','Username or Password wrong'),(250,'Username or Password wrong','zh_cn','用户名或密码错误'),(251,'MinPrice','en_us','Lowest Price'),(252,'MinPrice','zh_cn','最低价'),(253,'MaxPrice','en_us','Highest Price'),(254,'MaxPrice','zh_cn','最高价'),(255,'TimeRange','en_us','Time Range'),(256,'TimeRange','zh_cn','时间区间'),(257,'MoneyRange','en_us','Money Range'),(258,'MoneyRange','zh_cn','价格区间'),(259,'File','en_us','File'),(260,'File','zh_cn','文件'),(261,'Preview','en_us','Preview'),(262,'Preview','zh_cn','预览'),(263,'ForgetPassword','en_us','Forget Password?'),(264,'ForgetPassword','zh_cn','忘记密码？'),(265,'VerifyCode','en_us','Verify Code'),(266,'VerifyCode','zh_cn','验证码'),(267,'GetVerifyCode','en_us','Get verify code'),(268,'GetVerifyCode','zh_cn','获取验证码'),(269,'VerifyKey','en_us','Mail/Mobile'),(270,'VerifyKey','zh_cn','邮箱/手机'),(271,'Submit','en_us','Submit'),(272,'Submit','zh_cn','提交'),(273,'Statistic','en_us','Statistic'),(274,'Statistic','zh_cn','统计'),(275,'File not found','en_us','File not found'),(276,'File not found','zh_cn','文件不存在'),(277,'Publish','en_us','Publish'),(278,'Publish','zh_cn','发布');
/*!40000 ALTER TABLE `msg_l10n` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (6,'A-1',NULL,NULL,1),(7,'A-2',NULL,NULL,1),(8,'A-3',NULL,NULL,0),(9,'B',NULL,NULL,1);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(64) NOT NULL,
  `Description` varchar(128) DEFAULT NULL,
  `Enabled` tinyint(1) NOT NULL DEFAULT '1',
  `Register` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (20,'Admin','Admin',1,'\0'),(31,'PowerUser','PowerUser',1,'\0'),(58,'GU','GeneralUser',1,'');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `submenu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_menu_role` (`role_id`),
  KEY `fk_role_menu_submenu` (`submenu_id`),
  CONSTRAINT `fk_role_menu_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk_role_menu_submenu` FOREIGN KEY (`submenu_id`) REFERENCES `submenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (266,58,3),(267,58,23),(286,31,26),(287,31,1),(288,31,3),(289,31,22),(290,31,23),(324,20,28),(325,20,1),(326,20,4),(327,20,2),(328,20,27),(329,20,21),(330,20,24),(331,20,25),(332,20,26),(333,20,23),(334,20,22),(335,20,3);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submenu`
--

DROP TABLE IF EXISTS `submenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `link` varchar(64) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `display_order` int(11) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_submenu_menu_idx` (`parent_id`),
  CONSTRAINT `fk_submenu_menu` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submenu`
--

LOCK TABLES `submenu` WRITE;
/*!40000 ALTER TABLE `submenu` DISABLE KEYS */;
INSERT INTO `submenu` VALUES (1,'User','/user',2,1,'person'),(2,'Role','/role',2,2,'group'),(3,'About','/about',3,1,'info'),(4,'Organization','/organization',2,1,'device_hub'),(21,'Submenu','/submenu',2,20,'apps'),(22,'Preference','/settings',4,50,'settings'),(23,'Password Setting','/chgpwd',4,30,'vpn_key'),(24,'Dictionary Type','/dictType',1,10,'view_headline'),(25,'Global Dictionary','/globalDict',1,20,'view_list'),(26,'Local Dictionary','/localDict',1,30,'view_module'),(27,'Menu','/menu',2,15,'menu'),(28,'System Config','/sysConfig',2,0,'build');
/*!40000 ALTER TABLE `submenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_key` varchar(45) NOT NULL,
  `cfg_value` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`cfg_key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'brand','UMS'),(2,'register','B|999123-555'),(3,'company','RexStudio'),(4,'copyright','2017');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `salt` char(4) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin','admin','1111','e11170b8cbd2d74102651cb967fa28e5',1),(4,'PU-A','PU-A','2222','eca119682b8abe5d6e1e0bb37ddb3716',1),(19,'PU-B','PU-B','1664',NULL,1),(20,'GU-A1','GU-A1','1111','e11170b8cbd2d74102651cb967fa28e5',1),(21,'GU-A2','GU-A2','0320',NULL,1),(22,'GU-A3','GU-A3','7330',NULL,1),(23,'GU-B','GU-B','2299','f501d42b40f72890c5e10d2fbece2233',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_ext`
--

DROP TABLE IF EXISTS `user_ext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_ext` (
  `user_id` bigint(11) NOT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `birth` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_userext_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_ext`
--

LOCK TABLES `user_ext` WRITE;
/*!40000 ALTER TABLE `user_ext` DISABLE KEYS */;
INSERT INTO `user_ext` VALUES (24,'13661832031',NULL,NULL);
/*!40000 ALTER TABLE `user_ext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_org`
--

DROP TABLE IF EXISTS `user_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `org_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_org`
--

LOCK TABLES `user_org` WRITE;
/*!40000 ALTER TABLE `user_org` DISABLE KEYS */;
INSERT INTO `user_org` VALUES (26,4,6),(27,4,7),(28,4,8),(29,19,9),(30,20,6),(31,21,7),(32,22,8),(33,23,9),(34,3,6),(35,3,7),(36,3,8),(37,3,9);
/*!40000 ALTER TABLE `user_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_user_role_role` (`role_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,20),(4,31),(19,31),(20,58),(21,58),(22,58),(23,58);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verify_code`
--

DROP TABLE IF EXISTS `verify_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verify_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(45) NOT NULL,
  `code` varchar(6) NOT NULL,
  `timestamp` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verify_code`
--

LOCK TABLES `verify_code` WRITE;
/*!40000 ALTER TABLE `verify_code` DISABLE KEYS */;
INSERT INTO `verify_code` VALUES (1,'13600001111','777777',13011112222),(2,'13600001111','888888',13111112222),(3,'13388889999','194791',1492674031532),(4,'13388889999','477550',1492674213090),(5,'13388889999','670440',1492674295707),(6,'13377778888','089086',1492674327559),(7,'13366660000','995237',1492674486697),(8,'123','464086',1492675154811),(9,'13661832031','803431',1492675460878);
/*!40000 ALTER TABLE `verify_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-14 22:27:41
