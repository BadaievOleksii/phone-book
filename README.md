'app.properties' file can be moved anywhere, while its path is specified as JVM parameter -Dlardi.conf (separate declaration
for tests is in pom.xml plugins)

Can be run from this folder with two commands, e.g.:
mvn package
java -jar -Dlardi.conf="C:\\LardiData\\app.properties" target/phone-book-0.1-SNAPSHOT.jar


MySQL table creation commands:

CREATE TABLE `phonebook_records` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `mobile_phone` varchar(45) NOT NULL,
  `home_phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fio` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

