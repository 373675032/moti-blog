/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : moti-blog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-10-20 21:06:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `open_id` varchar(255) DEFAULT NULL COMMENT '绑定QQ登录的ID',
  `img` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `name` varchar(20) DEFAULT NULL COMMENT '作者昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别：男、女、保密',
  `birthday` date DEFAULT NULL COMMENT '出生年月日',
  `address` varchar(50) DEFAULT NULL COMMENT '地区',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信号图片地址',
  `public_wechat` varchar(255) DEFAULT NULL COMMENT '微信公众号图片地址',
  `career` varchar(20) DEFAULT NULL COMMENT '职业',
  `info` varchar(500) DEFAULT NULL,
  `recent_login` datetime DEFAULT NULL COMMENT '最近登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8eba7aefcef54f7ba27644269107f6ad.jpg', '莫提', 'd3a3f5e999c9362e', '301320288', '男', '1999-07-29', '河北省唐山市迁安市', 'cnmoti@foxmail.com', ' ', '373675032', 'https://gitee.com/cn_moti/moti-img/raw/master/other/44ddb80f33af4ba3ba3b4934fdaa2d8f.jpg', 'https://gitee.com/cn_moti/moti-img/raw/master/other/5693878fafb0452a89f7d685d19ca8a4.jpg', '在校大学生', '不积跬步无以至千里，不积小流无以成江海！', '2020-10-20 20:11:12');

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章的主键ID',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '正文',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `recent_edit` datetime DEFAULT NULL COMMENT '最近编辑时间',
  `status` int(11) DEFAULT NULL COMMENT '文章状态。0：草稿，1：发布，2：回收站，3：加密',
  `read_count` int(11) DEFAULT NULL COMMENT '阅读量',
  `introduce` varchar(500) DEFAULT NULL COMMENT '文章介绍，引言',
  `img` varchar(255) DEFAULT 'https://gitee.com/cn_moti/moti-img/raw/master/other/6e1aceb77d1e4b5e91e390fd9e136bd7.jpg' COMMENT '头图地址',
  PRIMARY KEY (`id`),
  KEY `article_status` (`status`),
  KEY `article_read_count` (`read_count`),
  KEY `article_publish_time` (`publish_time`)
) ENGINE=InnoDB AUTO_INCREMENT=446863 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('122', '高效使用Github寻找开源项目', '## Git和GitHub什么关系？\n\nGit（读音为/gɪt/）是一个开源的分布式版本控制系统，可以有效、高速地处理从很小到非常大的项目版本管理。\n\nGitHub是一个面向开源及私有软件项目的托管平台，因为只支持git 作为唯一的版本库格式进行托管，故名GitHub。\n\n**git用来管理你的代码，可以对你的项目进行版本控制。github将你用git管理的项目放在网上， 本地的一个个项目对应github上面的一个个仓库。仓库可以公开（开源），私密。程序员之间可以互相学习别人的项目，互相交流，github是全球最大的同性交友网站。**\n\n## 怎么在GitHub上靠谱的找一些开源项目\n\n![](https://gitee.com/cn_moti/moti-img/raw/master/2020-10-12/280b3e896b004524b2652a1a1384cd55.png)\n\n```\n# 按照项目名/仓库名搜索（大小写不敏感）\nin:name xxx \n# 按照README搜索（大小写不敏感）\nin:readme xxx\n# 按照description搜索（大小写不敏感）\nin:description xxx\n# stars数大于xxx\nstars:>xxx\n# forks数大于xxx\nforks:>xxx\n# 编程语言为xxx\nlanguage:xxx\n# 最新更新时间晚于YYYY-MM-DD\npushed:>YYYY-MM-DD\n```\n\n## 示例\n\n**寻找仓库名称包含ssm、stars数大于500、forks数大于100的开源项目**\n\n![](https://gitee.com/cn_moti/moti-img/raw/master/2020-10-12/f4797ee1a1d64ebea39e0bff15120050.png)', '2020-04-01 09:33:55', '2020-10-12 08:33:27', '1', '205', 'GitHub是一个面向开源及私有软件项目的托管平台，因为只支持git 作为唯一的版本库格式进行托管，故名GitHub', 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `article` VALUES ('123', '阿里云启动 SpringBoot 后访问特别慢', '## 原因\nTomcat 使用 SHA1PRNG 算法是基于 SHA-1 算法实现且保密性较强的伪随机数生成器。在 SHA1PRNG 中，有一个种子产生器，它根据配置执行各种操作。\n\nLinux 中的随机数可以从两个特殊的文件中产生，一个是 /dev/urandom，另外一个是 /dev/random。他们产生随机数的原理是利用当前系统的熵池来计算出固定一定数量的随机比特，然后将这些比特作为字节流返回。熵池就是当前系统的环境噪音，熵指的是一个系统的混乱程度，系统噪音可以通过很多参数来评估，如内存的使用，文件的使用量，不同类型的进程数量等等。如果当前环境噪音变化的不是很剧烈或者当前环境噪音很小，比如刚开机的时候，而当前需要大量的随机比特，这时产生的随机数的随机效果就不是很好了。\n\n这就是为什么会有 /dev/urandom 和 /dev/random 这两种不同的文件，后者在不能产生新的随机数时会阻塞程序，而前者不会（ublock），当然产生的随机数效果就不太好了，这对加密解密这样的应用来说就不是一种很好的选择。/dev/random 会阻塞当前的程序，直到根据熵池产生新的随机字节之后才返回，所以使用 /dev/random 比使用 /dev/urandom 产生大量随机数的速度要慢。\n\n## 解决方案\n#### 1. 进入你服务器的jdk安装目录的 .../jdk1.8.0_212/jre/lib/security\n\n![](https://gitee.com/cn_moti/moti-img/raw/master/2020-10-12/8bf057c7266c4517a0df5d308b24130b.png)\n\n#### 2. 修改java.security\n\n找到这句话: `securerandom.source=file:/dev/random`\n\n![](https://gitee.com/cn_moti/moti-img/raw/master/2020-10-12/22a32b35d8484969a0f7c6499df47f77.png)\n\n将这句话修改为: `securerandom.source=file:/dev/urandom`\n\n#### 3. 重新启动你的项目就OK', '2020-04-12 09:37:18', '2020-10-12 09:37:18', '1', '302', '原因Tomcat使用SHA1PRNG算法是基于SHA-1算法实现且保密性较强的伪随机数生成器。在SHA1PRNG中，有一个种子产生器，它根据配置执行各种操作。Linux中的随机数可以从两个特殊的文..', 'https://gitee.com/cn_moti/moti-img/raw/master/other/e65e22ed581f498c8d984a7adc7022e8.png');
INSERT INTO `article` VALUES ('124', '启动阿里云的各项服务', '## Nginx服务\n```\ncd /usr/local/nginx/sbin\n./nginx\n```\n## Docker服务\n```\n# 查看Docker状态\nsystemctl status docker\n\n# 启动Docker\nsystemctl start docker\n```\n## Redis服务\n```\n# 启动Redis\nservice redisd start\n\n# 关闭Redis\nservice redisd stop\nredis-cli shutdown\n```\n## RabbitMq服务\n```\n# 启动\nrabbitmq-server -detached\n\n# 停止\nrabbitmqctl stop\n\n# 查看状态\nrabbitmqctl status\n```\n## ElasticSearch服务\n切换到非root用户，进入到es的bin目录\n```\n# 后台启动\n./elasticsearch -d\n```\n## Kibana服务\n```\n# 启动\nsystemctl start kibana\n\n# 状态\nsystemctl status kibana\n\n# 关闭\nsystemctl stop kibana\n```\n## SpringBoot项目（jar）\n```\n# 后台运行jar,并记录日志到当前文件夹下的logs.txt\nnohup java -jar moti-cloud-0.0.1-SNAPSHOT.jar >log.txt &\n\n# 停止后台运行的jar\nps -ef | grep java\n\n# kill命令杀掉jar包运行的进程\nkill 进程ID\n```', '2020-04-12 10:33:39', '2020-10-12 10:33:39', '1', '152', '阿里云服务器只要一重启，各项服务就都关闭了，所以我索性把这些服务的启动命令做了一个汇总', 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `article` VALUES ('125', '索引查询优化', '## 单表查询优化\n### 注意\n- 使用复合索引的效果会大于使用单个字段索引（但是要注意顺序）\n- 查询条件时要按照索引中的定义顺序进行匹配。如果索引了多列，要遵守最左前缀法则。指的是查询从索引的最左前列开始并且不跳过索引中的列。\n- 不在索引列上做任何操作（计算、函数、(自动or手动)类型转换），会导致索引失效而转向全表扫描\n- 存储引擎不能使用索引中范围条件右边的列，范围查询的列在定义索引的时候，应该放在最后面。\n- mysql 在使用不等于(!= 或者<>)的时候无法使用索引会导致全表扫描\n- is not null 也无法使用索引,但是is null是可以使用索引的\n- like以通配符开头(\'%abc...\')mysql索引失效会变成全表扫描的操作\n- 字符串不加单引号索引失效（类型转换导致索引失效）\n\n![](https://gitee.com/cn_moti/moti-img/raw/master/2020-10-12/e8514289d15e499381e702c36020dfce.png)\n\n### 建议\n- 对于单键索引，尽量选择针对当前query过滤性更好的索引。\n- 在选择组合索引的时候，当前Query中过滤性最好的字段在索引字段顺序中，位置越靠前越好。\n- 在选择组合索引的时候，尽量选择可以能够包含当前query中的where字句中更多字段的索引。\n- 在选择组合索引的时候，如果某个字段可能出现范围查询时，尽量把这个字段放在索引次序的最后面。\n- 书写sql语句时，尽量避免造成索引失效的情况。\n\n### 示例\n```\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE age = 30;\n# 给emp表的age字段创建索引\nCREATE INDEX idx_age ON emp(age);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE age = 30 AND deptId = 1;\n# 给emp表的age字段和deptid创建索引\nCREATE INDEX idx_age_deptId ON emp(age,deptId);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE age = 30 AND deptId = 1 AND NAME = \'abcd\';\n# 给emp表的age字段和deptid和name创建索引\nCREATE INDEX idx_age_deptId_name ON emp(age,deptId,NAME);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME  LIKE \'abc%\';\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE LEFT(NAME,3) = \'abc\';\n# 给emp表的name字段创建索引,如果where条件中使用到了函数，可能会造成索引失效！\nCREATE INDEX idx_name ON emp(NAME);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE age = 30 AND deptId > 20 AND NAME = \'abc\';\n# 当索引中有字段是范围查询时，这个字段后面的字段索引失效，所以在创建索引时，范围查询的字段放在最后\nCREATE INDEX idx_age_deptId_name ON emp(age,deptId,NAME);\nCREATE INDEX idx_age_name_deptId ON emp(age,NAME,deptId);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME <> \'abc\';\n# <>会让索引失效\nCREATE INDEX idx_name ON emp(NAME);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME IS NULL;\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME IS NOT NULL;\n# IS NOT 也会导致索引失效\nCREATE INDEX idx_name ON emp(NAME);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME LIKE \'%abc%\';\n# LIKE 匹配值的开头是%，索引失效\nCREATE INDEX idx_name ON emp(NAME);\n\nEXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE NAME = 123;\n# NAME类型为字符类型，123会被mysql做类型转换，索引失效\nCREATE INDEX idx_name ON emp(NAME);\n```\n## 关联查询优化\n### 建议\n\n- 保证被驱动表（从表）的join字段已经被索引\n- left join 时，选择小表作为驱动表（主表），大表作为被驱动表（从表）\n- inner join 时，mysql会自己帮你把小结果集的表选为驱动表（主表）\n- 子查询尽量不要放在被驱动表（从表），有可能使用不到索引\n- 能够直接多表关联的尽量直接关联，不用子查询\n\n## 子查询优化\n\n尽量不要使用 not in 或者 not exists，因为两者会导致索引失效。用left outer join on xxx is null 替代 not in 或者 not exists。两者都可以取到一个表独有的数据。连接查询可以使用到索引。', '2020-04-12 10:39:08', '2020-10-12 10:39:08', '1', '65', '- 使用复合索引的效果会大于使用单个字段索引（但是要注意顺序）\n- 查询条件时要按照索引中的定义顺序进行匹配。如果索引了多列，要遵守最左前缀法则。指的是查询从索引的最', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `article` VALUES ('126', '排序分组的索引优化', '## 排序优化\nORDER BY子句，尽量使用Index方式（索引）排序,避免使用FileSort方式（手工）排序。\n\n**技巧：无过滤，不索引；顺序错，必手工排序；方向反，必手工排序；**\n\n- 要想Order BY使用到索引，必须要添加过滤条件（where子句对索引中的字段进行过滤，而且必须按照顺序），Limit分页也行。\n- 在SQL语句中的顺序一定要和定义索引中的字段顺序完全一致！\n- 要么全升序、要么全降序。有升有降无法使用索引\n\n### 案例\n\n```\nSELECT SQL_NO_CACHE * FROM emp WHERE age =30 AND empno <101000 ORDER BY NAME ;\n```\n可以看到，上面where条件中有范围查询，那么后面的索引会失效。\n\n那么我们可以创建两个索引，一个是idx_age_empno（避免不了Using filesort），另一个是idx_age_name（不能让where条件充分用到索引），当这两个索引同时存在的时候，MySQL会选择谁作为最优索引呢？会选择让where子句舒服的索引，即idx_age_empno。\n\n### Using filesort有两种排序算法\n一种是单路排序，一种的双路排序。\n\n单路排序更快，因为使用到了内存。\n\n## 分组优化\nGroup By 使用索引的原则几乎跟Order By一致 ，唯一区别是Group By即使没有过滤条件用到索引，也可以直接使用索引。', '2020-04-12 10:45:22', '2020-10-12 10:45:22', '1', '74', 'ORDER BY子句，尽量使用Index方式（索引）排序,避免使用FileSort方式（手工）排序', 'https://gitee.com/cn_moti/moti-img/raw/master/other/d4eef3844c3643d08e4d4bc1cb41e508.jpg');
INSERT INTO `article` VALUES ('127', 'SpringMVC是如何实现作用域传值和页面跳转的？', '## HelloWorld', '2020-05-12 10:52:03', '2020-10-12 10:52:03', '1', '83', '学习SpringMVC的源码总结', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `article` VALUES ('128', 'SpringBoot整合Redis', '测试', '2020-05-12 10:52:51', '2020-10-12 10:52:51', '1', '19', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `article` VALUES ('129', 'SpringBoot整合RabbitMq', '测试', '2020-05-12 10:53:18', '2020-10-12 10:53:18', '1', '14', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `article` VALUES ('130', 'SpringBoot整合ElasticSearch', '测试', '2020-06-12 10:53:55', '2020-10-12 10:53:55', '1', '52', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `article` VALUES ('131', 'SpringBoot整合邮件任务', '测试', '2020-06-06 10:55:25', '2020-10-12 10:55:25', '1', '46', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `article` VALUES ('132', 'SpringBoot整合定时任务', '测试', '2020-06-12 10:55:51', '2020-10-12 10:55:51', '1', '300', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `article` VALUES ('133', 'RabbitMq之HelloWorld模式', '测试', '2020-07-12 10:57:59', '2020-10-12 10:57:59', '1', '265', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/d4eef3844c3643d08e4d4bc1cb41e508.jpg');
INSERT INTO `article` VALUES ('134', 'RabbitMq之Topic模式', '测试', '2020-07-12 10:58:31', '2020-10-12 10:58:31', '1', '234', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `article` VALUES ('135', 'Redis常用命令', '测试', '2020-08-12 10:59:31', '2020-10-12 10:59:31', '1', '79', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `article` VALUES ('136', '单例模式', '测试', '2020-08-12 11:01:24', '2020-10-12 11:01:24', '1', '94', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `article` VALUES ('137', '建造者模式', '测试', '2020-08-12 11:01:44', '2020-10-12 11:01:44', '1', '165', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `article` VALUES ('138', '代理模式', '测试', '2020-08-12 11:02:05', '2020-10-12 11:02:05', '1', '154', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `article` VALUES ('139', 'Jquery在线链接', '测试', '2020-09-12 11:03:08', '2020-10-12 11:03:08', '1', '187', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `article` VALUES ('140', 'BootStrap的基本使用', '测试', '2020-10-12 11:03:35', '2020-10-12 11:03:35', '1', '32', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `article` VALUES ('141', '冒泡排序', '测试', '2020-10-12 11:34:59', '2020-10-12 11:34:59', '1', '65', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `article` VALUES ('142', '选择排序', '测试', '2020-10-12 11:35:22', '2020-10-12 11:35:22', '1', '9', '测试', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `article` VALUES ('143', 'Linux系统防火墙常用命令', '> linux系统防火墙：centos5、centos6、redhat6系统自带的是iptables防火墙，centos7、redhat7自带firewall防火墙，ubuntu系统使用的是ufw防火墙。\n\n## 防火墙导致服务不正常的问题：\n在服务器安装某些服务之后，服务无法连接、无法正常启动等情况。查看下系统防火墙有没开放相关的服务端口。（linux系统防火墙开放相关端口后还要重启防火墙，重启防火墙后防火墙规则才会生效）\n\n## 关于80端口：\n应国家相关部门的相关规定，解析在国内的域名必须要备案，所以要使用80端口的客户需提供相关域名备案信息，在我们上层防火墙开放80端口后，云服务器的80端口才能正常使用。\n\n## 常用命令\n```\nfirewall-cmd --zone=public --list-ports ##列出所有开放的端口\n\nfirewall-cmd --add-port=80/tcp --permanent ##永久添加80端口 \n\nfirewall-cmd --zone=public --remove-port=80/tcp --permanent ##移除80端口 \n\nfirewall-cmd --reload ##重新载入配置，比如添加规则之后，需要执行此命令 \n\nfirewall-cmd --state ##查看防火墙状态，是否是running\n\nsystemctl status firewalld.service ##查看防火墙状态\n\nsystemctl start firewalld.service ##启动防火墙\n\nsystemctl stop firewalld.service ##临时关闭防火墙\n\nsystemctl enable firewalld.service ##设置开机启动防火墙\n\nsystemctl disable firewalld.service ##设置禁止开机启动防火墙\n\nfirewall-cmd --permanent --query-port=80/tcp ##查看80端口有没开放\n\nfirewall-cmd --get-zones ##列出支持的zone\n\nfirewall-cmd --get-services ##列出预定义的服务\n\nfirewall-cmd --query-service ftp ##查看ftp服务是否放行，返回yes或者no\n\nfirewall-cmd --add-service=ftp ##临时开放ftp服务\n\nfirewall-cmd --add-service=ftp --permanent ##永久开放ftp服务\n\nfirewall-cmd --remove-service=ftp --permanent ##永久移除ftp服务\n\niptables -L -n ##查看规则，这个命令是和iptables的相同的\n\nman firewall-cmd ##查看帮助\n\n参数含义：\n--zone #作用域\n--permanent #永久生效，没有此参数重启后失效 \n```', '2020-10-12 11:46:32', '2020-10-12 11:46:32', '1', '0', 'Linux系统防火墙常用命令', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `article` VALUES ('446862', '图解设计模式', '', '2020-10-20 20:02:07', '2020-10-20 20:02:07', '1', '0', '这是一本不错的书啊！', 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');

-- ----------------------------
-- Table structure for `article_kind`
-- ----------------------------
DROP TABLE IF EXISTS `article_kind`;
CREATE TABLE `article_kind` (
  `article_id` int(11) NOT NULL DEFAULT '0' COMMENT '文章ID',
  `kind_id` int(11) NOT NULL DEFAULT '0' COMMENT '类别ID',
  PRIMARY KEY (`article_id`,`kind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_kind
-- ----------------------------
INSERT INTO `article_kind` VALUES ('122', '16');
INSERT INTO `article_kind` VALUES ('123', '20');
INSERT INTO `article_kind` VALUES ('124', '20');
INSERT INTO `article_kind` VALUES ('125', '19');
INSERT INTO `article_kind` VALUES ('126', '19');
INSERT INTO `article_kind` VALUES ('127', '15');
INSERT INTO `article_kind` VALUES ('128', '15');
INSERT INTO `article_kind` VALUES ('129', '15');
INSERT INTO `article_kind` VALUES ('130', '15');
INSERT INTO `article_kind` VALUES ('131', '15');
INSERT INTO `article_kind` VALUES ('132', '15');
INSERT INTO `article_kind` VALUES ('133', '20');
INSERT INTO `article_kind` VALUES ('134', '20');
INSERT INTO `article_kind` VALUES ('135', '19');
INSERT INTO `article_kind` VALUES ('136', '21');
INSERT INTO `article_kind` VALUES ('137', '21');
INSERT INTO `article_kind` VALUES ('138', '21');
INSERT INTO `article_kind` VALUES ('139', '17');
INSERT INTO `article_kind` VALUES ('140', '17');
INSERT INTO `article_kind` VALUES ('141', '18');
INSERT INTO `article_kind` VALUES ('142', '18');
INSERT INTO `article_kind` VALUES ('143', '20');
INSERT INTO `article_kind` VALUES ('446862', '21');

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_id` int(11) NOT NULL DEFAULT '0' COMMENT '文章ID',
  `tag_id` int(11) NOT NULL DEFAULT '0' COMMENT '标签ID',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES ('122', '81');
INSERT INTO `article_tag` VALUES ('122', '82');
INSERT INTO `article_tag` VALUES ('123', '77');
INSERT INTO `article_tag` VALUES ('123', '78');
INSERT INTO `article_tag` VALUES ('123', '79');
INSERT INTO `article_tag` VALUES ('123', '80');
INSERT INTO `article_tag` VALUES ('124', '78');
INSERT INTO `article_tag` VALUES ('124', '83');
INSERT INTO `article_tag` VALUES ('124', '84');
INSERT INTO `article_tag` VALUES ('124', '85');
INSERT INTO `article_tag` VALUES ('124', '86');
INSERT INTO `article_tag` VALUES ('124', '87');
INSERT INTO `article_tag` VALUES ('124', '88');
INSERT INTO `article_tag` VALUES ('125', '89');
INSERT INTO `article_tag` VALUES ('125', '90');
INSERT INTO `article_tag` VALUES ('126', '89');
INSERT INTO `article_tag` VALUES ('126', '90');
INSERT INTO `article_tag` VALUES ('127', '91');
INSERT INTO `article_tag` VALUES ('128', '78');
INSERT INTO `article_tag` VALUES ('128', '86');
INSERT INTO `article_tag` VALUES ('129', '78');
INSERT INTO `article_tag` VALUES ('129', '88');
INSERT INTO `article_tag` VALUES ('130', '78');
INSERT INTO `article_tag` VALUES ('130', '85');
INSERT INTO `article_tag` VALUES ('131', '78');
INSERT INTO `article_tag` VALUES ('131', '92');
INSERT INTO `article_tag` VALUES ('132', '78');
INSERT INTO `article_tag` VALUES ('132', '93');
INSERT INTO `article_tag` VALUES ('133', '88');
INSERT INTO `article_tag` VALUES ('134', '88');
INSERT INTO `article_tag` VALUES ('135', '86');
INSERT INTO `article_tag` VALUES ('136', '94');
INSERT INTO `article_tag` VALUES ('137', '95');
INSERT INTO `article_tag` VALUES ('138', '96');
INSERT INTO `article_tag` VALUES ('139', '97');
INSERT INTO `article_tag` VALUES ('140', '98');
INSERT INTO `article_tag` VALUES ('141', '99');
INSERT INTO `article_tag` VALUES ('142', '100');
INSERT INTO `article_tag` VALUES ('143', '77');
INSERT INTO `article_tag` VALUES ('143', '101');
INSERT INTO `article_tag` VALUES ('446862', '102');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `name` varchar(255) DEFAULT NULL COMMENT '评论者昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `content` longtext COMMENT '留言正文',
  `reply_id` int(11) DEFAULT NULL COMMENT '作者回复的留言ID',
  `img` varchar(255) DEFAULT NULL COMMENT '随机的头像地址',
  `time` datetime DEFAULT NULL COMMENT '留言时间',
  `type` int(11) DEFAULT NULL COMMENT '评论类型【0：读者评论】【1：作者回复】',
  `ip` varchar(50) DEFAULT NULL COMMENT '留言者的IP地址',
  `status` int(11) DEFAULT NULL COMMENT '评论的状态【0：未读】【1：已读】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('43', '143', '张三', '373675032@qq.com', '你好', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/771ba241fe164ea0a1a1a17068c69ab4.jpg', '2020-10-12 12:00:18', '0', '127.0.0.1', '1');
INSERT INTO `comment` VALUES ('44', '143', null, null, '你好呀', '43', null, '2020-10-12 12:08:44', '1', null, null);
INSERT INTO `comment` VALUES ('45', '142', '123', '123@qq.com', '123', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/fa962e4d45e04dda9c90afcb8e4ff46e.jpg', '2020-10-12 15:32:41', '0', '127.0.0.1', '1');
INSERT INTO `comment` VALUES ('46', '143', null, null, '哦哦哦哦哦哦，还行吧！', '43', null, '2020-10-20 19:55:39', '1', null, null);

-- ----------------------------
-- Table structure for `front`
-- ----------------------------
DROP TABLE IF EXISTS `front`;
CREATE TABLE `front` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `background` varchar(255) DEFAULT NULL COMMENT '背景图片',
  `notice` varchar(500) DEFAULT NULL COMMENT '公告',
  `img_target` varchar(255) DEFAULT NULL COMMENT '公告图片的URL',
  `notice_img` varchar(255) DEFAULT NULL COMMENT '公告图片',
  `title` varchar(255) DEFAULT NULL COMMENT '站点标题',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of front
-- ----------------------------
INSERT INTO `front` VALUES ('1', 'https://gitee.com/cn_moti/moti-img/raw/master/other/0d0624a91d02463792202f4a292725c4.jpg', '一个喜欢自学的学生程序员UP主！日常分享关于程序员初学者的经验和知识，关注我的公众号可以获取更多内容哦~', 'http://xuewei.world', 'https://gitee.com/cn_moti/moti-img/raw/master/other/4cd76ab5d1dd411cae57010fc173bf87.gif', '莫提', '不积跬步无以至千里');

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别的主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '类别的介绍',
  `img` varchar(255) DEFAULT NULL COMMENT '类别头图地址',
  PRIMARY KEY (`id`),
  KEY `kind_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('15', '后端', '关于后端的技术文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `kind` VALUES ('16', '开源项目', '开源项目推荐', 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `kind` VALUES ('17', '前端', '关于前端的技术文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/d4eef3844c3643d08e4d4bc1cb41e508.jpg');
INSERT INTO `kind` VALUES ('18', '算法', '关于常用算法的文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `kind` VALUES ('19', '数据库', '关于关系型数据库以及非关系型数据库的技术文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `kind` VALUES ('20', 'Linux服务器', '关于Linux系统、服务器相关的技术文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `kind` VALUES ('21', '设计模式', '关于常用设计模式的文章', 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('2', '我的Github', 'https://github.com/373675032');
INSERT INTO `link` VALUES ('3', 'B站关注【我是莫提】', 'https://space.bilibili.com/301320288');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `location` int(1) DEFAULT NULL COMMENT '位置【0：左】【1：右】',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('4', '掘金', 'https://juejin.im/user/3421335917961710', '0');
INSERT INTO `menu` VALUES ('5', 'B站', 'https://space.bilibili.com/301320288', '0');
INSERT INTO `menu` VALUES ('6', 'Github', 'https://github.com/373675032', '0');
INSERT INTO `menu` VALUES ('8', '莫提网盘', 'http://xuewei.world/moti-cloud', '1');
INSERT INTO `menu` VALUES ('9', '个人博客', 'http://xuewei.world', '0');

-- ----------------------------
-- Table structure for `statistics`
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `request_count` int(11) DEFAULT NULL COMMENT '访问次数',
  `request_date` date DEFAULT NULL COMMENT '日期',
  `article_id` int(11) DEFAULT NULL COMMENT '访问的文章id。如果为-1表示访问主页',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of statistics
-- ----------------------------
INSERT INTO `statistics` VALUES ('1', '127.0.0.2', '52', '2020-10-11', '-1');
INSERT INTO `statistics` VALUES ('2', '127.0.0.3', '25', '2020-10-13', '-1');
INSERT INTO `statistics` VALUES ('3', '127.0.0.4', '36', '2020-10-14', '-1');
INSERT INTO `statistics` VALUES ('4', '127.0.0.5', '58', '2020-10-15', '-1');
INSERT INTO `statistics` VALUES ('5', '127.0.0.6', '66', '2020-10-16', '-1');
INSERT INTO `statistics` VALUES ('6', '127.0.0.7', '74', '2020-10-17', '-1');
INSERT INTO `statistics` VALUES ('7', '127.0.0.8', '88', '2020-10-18', '-1');
INSERT INTO `statistics` VALUES ('8', '127.0.0.9', '92', '2020-10-19', '-1');
INSERT INTO `statistics` VALUES ('9', '127.0.0.10', '65', '2020-10-20', '-1');
INSERT INTO `statistics` VALUES ('11', '127.0.0.11', '20', '2020-10-12', '-1');
INSERT INTO `statistics` VALUES ('13', '127.0.0.12', '55', '2020-10-10', '-1');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签的主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '标签的介绍',
  `img` varchar(255) DEFAULT NULL COMMENT '标签头图地址',
  PRIMARY KEY (`id`),
  KEY `tag_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('77', 'Linux', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `tag` VALUES ('78', 'SpringBoot', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `tag` VALUES ('79', '阿里云', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/43ff1d5db66e49a48e4b75ad18b3f888.jpg');
INSERT INTO `tag` VALUES ('80', 'Tomcat', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('81', 'Github', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `tag` VALUES ('82', '开源项目', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `tag` VALUES ('83', 'Nginx', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `tag` VALUES ('84', 'Docker', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `tag` VALUES ('85', 'ElasticSearch', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('86', 'Redis', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `tag` VALUES ('87', 'Kibana', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `tag` VALUES ('88', 'RabbitMq', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('89', 'MySQL', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `tag` VALUES ('90', '索引', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `tag` VALUES ('91', 'SpringMVC', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png');
INSERT INTO `tag` VALUES ('92', '邮件任务', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('93', '定时任务', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg');
INSERT INTO `tag` VALUES ('94', '单例模式', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `tag` VALUES ('95', '建造者模式', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `tag` VALUES ('96', '代理模式', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('97', 'Jquery', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
INSERT INTO `tag` VALUES ('98', 'BootStrap', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png');
INSERT INTO `tag` VALUES ('99', '冒泡排序', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg');
INSERT INTO `tag` VALUES ('100', '选择排序', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/d4eef3844c3643d08e4d4bc1cb41e508.jpg');
INSERT INTO `tag` VALUES ('101', '防火墙', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg');
INSERT INTO `tag` VALUES ('102', '设计模式', null, 'https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png');
