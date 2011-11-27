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

CREATE VIEW `roles` AS SELECT `users`.`email` AS `email`,'user' AS `role` FROM `users`;
