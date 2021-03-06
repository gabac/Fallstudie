DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS friendship;
DROP VIEW IF EXISTS friends;
DROP VIEW IF EXISTS roles;
DROP TABLE IF EXISTS activities;
CREATE TABLE activities  ( 
	activity_id	int(11) AUTO_INCREMENT NOT NULL,
	user_id    	int(11) NOT NULL DEFAULT '0',
	typ        	enum('status','profile','friend','like','dislike') NOT NULL,
	parent     	int(11) NULL,
	time       	datetime NOT NULL,
	content    	text NULL,
    privacy     enum('everyone','friends') NOT NULL DEFAULT 'friends',
	PRIMARY KEY(activity_id)
)
ENGINE = InnoDB
CHARACTER SET utf8
COLLATE utf8_general_ci
AUTO_INCREMENT = 8;

CREATE TABLE friendship  ( 
	primary_user  	int(11) NOT NULL,
	secondary_user	int(11) NOT NULL,
	accepted      	tinyint(1) NOT NULL DEFAULT '0',
	PRIMARY KEY(primary_user,secondary_user)
)
ENGINE = InnoDB
CHARACTER SET utf8
COLLATE utf8_general_ci
AUTO_INCREMENT = 0;

CREATE TABLE status  ( 
	status_id  	int(11) AUTO_INCREMENT NOT NULL,
	activity_id	int(11) NOT NULL DEFAULT '0',
	content    	varchar(1000) NOT NULL,
	PRIMARY KEY(status_id)
)
ENGINE = InnoDB
CHARACTER SET utf8
COLLATE utf8_general_ci
AUTO_INCREMENT = 1;

CREATE TABLE users  ( 
	id       	int(11) UNSIGNED AUTO_INCREMENT NOT NULL,
	email    	varchar(255) NOT NULL,
	password 	varchar(255) NOT NULL,
	prename  	varchar(255) NOT NULL,
	surname  	varchar(255) NOT NULL,
	birthdate	datetime NOT NULL,
	city     	varchar(255) NOT NULL,
	photo    	longblob NULL,
    privacy_profile  enum('everyone','friends') NOT NULL DEFAULT 'friends',
    privacy_email    enum('everyone','friends') NOT NULL DEFAULT 'friends',
    device_token     varchar(255) NOT NULL,
	PRIMARY KEY(id)
)
ENGINE = InnoDB
CHARACTER SET utf8
COLLATE utf8_general_ci
AUTO_INCREMENT = 5;

CREATE VIEW friends
AS
select `u1`.`surname` AS `user_surname`,`u1`.`prename` AS `user_prename`,`u2`.`surname` AS `friends_surname`,`u2`.`prename` AS `friends_prename` from `socialnetwork`.`friendship` `fs` join `socialnetwork`.`users` `u1` join `socialnetwork`.`users` `u2` where ((`u1`.`id` = `fs`.`primary_user`) and (`u2`.`id` = `fs`.`secondary_user`) and (`fs`.`accepted` = 1));

CREATE VIEW roles
AS
select `socialnetwork`.`users`.`email` AS `email`,'user' AS `role` from `socialnetwork`.`users`;


INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(1, 1, 'status', NULL, '2011-12-01 12:22:33.0', 'Ich bin m�de!*:…?');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(2, 3, 'status', 1, '2011-12-01 12:22:44.0', 'Du bist aber ein Waschlappen oder etwa nicht? wie viel Zeichen kann ich hier wohl füllen????? wer weiss!!!!!!');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(3, 1, 'friend', NULL, '2011-12-01 12:22:33.0', 'Raphael Marques hat Roger Bollmann als Freund hinzugef�gt.');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(4, 2, 'status', NULL, '2011-11-20 12:22:33.0', 'Ich heisse Cyril und bin super(denke ich zumindest)!');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(5, 4, 'profile', NULL, '2011-10-20 12:22:33.0', 'Ich habe mein Name zu F�be ge�ndert');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(6, 2, 'status', NULL, '2011-11-20 12:21:33.0', 'Ich bin so doof, ich k�nnte eine Kuh essen');
INSERT INTO activities(activity_id, user_id, typ, parent, time, content)
  VALUES(7, 2, 'status', 2, '2011-11-23 13:22:22.0', 'Ich kommentiere gerne Kommentare');
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(1, 2, 0);
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(1, 3, 1);
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(1, 4, 1);
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(2, 3, 0);
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(2, 4, 1);
INSERT INTO friendship(primary_user, secondary_user, accepted)
  VALUES(3, 4, 1);
  
INSERT INTO users(id, email, password, prename, surname, birthdate, city, photo)
  VALUES(1, 'raphi.rm@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Raphael', 'Marques', '1988-09-16', 'Z�rich', '');
INSERT INTO users(id, email, password, prename, surname, birthdate, city, photo)
  VALUES(2, 'roger.bollmann@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Roger', 'Bollmann', '1922-12-31', 'Uster', '');
INSERT INTO users(id, email, password, prename, surname, birthdate, city, photo)
  VALUES(3, 'gabathuler@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Cyril', 'Gaba', '1711-09-30', 'Baden', '');
INSERT INTO users(id, email, password, prename, surname, birthdate, city, photo)
  VALUES(4, 'fabian.vogler@bluewin.ch', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Fabian', 'Vogler', '1989-01-01', 'Baden', '');

ALTER TABLE activities
	ADD CONSTRAINT activities_ibfk_1
	FOREIGN KEY(parent)
	REFERENCES activities(activity_id)
	ON DELETE CASCADE 
	ON UPDATE RESTRICT ;
