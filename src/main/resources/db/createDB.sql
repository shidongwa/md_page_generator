CREATE TABLE `iteye_blog` (
  `blog_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(250) NOT NULL DEFAULT '',
  `content` mediumtext DEFAULT '',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;