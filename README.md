Database gallary

1.CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `uName` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `mobileNumber` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `uName` (`uName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

2.CREATE TABLE `image` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT,
  `image` mediumblob,
  `uName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`imageId`),
  KEY `uName` (`uName`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`uName`) REFERENCES `user` (`uName`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 
