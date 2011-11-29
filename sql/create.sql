/**Create Table
 * Creating all tables, when you copy the stamente into a sql browser and execute it
 * at the end commit;
 */

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `prename` varchar(255) NOT NULL DEFAULT '',
  `surname` varchar(255) NOT NULL DEFAULT '',
  `birthdate` date NOT NULL,
  `city` varchar(255) NOT NULL DEFAULT '',
  `photo` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**Create Table
 * Creating all tables, when you copy the stamente into a sql browser and execute it
 * at the end commit;
 */

CREATE TABLE `friendship` (
	`primary_user` INT(11) NOT NULL,
	`secondary_user` INT(11) NOT NULL,
	`accepted` TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (`primary_user`, `secondary_user`)
) COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `activities` (
  `activity_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `friendship_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `activity_definition` (
  `activity_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `activity` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `status` (
	`status_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`friendship_id` INT(11) NOT NULL DEFAULT '0',
	`status` varchar(1000) NOT NULL DEFAULT '',
	PRIMARY KEY (`status_id`))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**Create Views
 * Creating all views, when you copy the stamente into a sql browser and execute it
 * at the end commit;
 */

CREATE VIEW `roles` AS SELECT `users`.`email` AS `email`,'user' AS `role` FROM `users`;

CREATE VIEW `friends` AS select u1.surname as 'user_surname', u1.prename as 'user_prename', u2.surname as 'friends_surname', u2.prename as 'friends_prename'
from friendship fs, users u1, users u2 where u1.id=fs.primary_user and u2.id=fs.secondary_user and fs.accepted = 1;

create view activities_friends as
select u1.email as 'user', u2.email as 'friend', ad.activity
from friendship fs, users u1, users u2, activities a, activity_definition ad
where u1.id = fs.primary_user and u2.id=fs.secondary_user
and ad.activity_id=a.activity_id;
