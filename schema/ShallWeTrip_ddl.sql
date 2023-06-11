use enjoytrip;

CREATE TABLE `user` (
  `id` varchar(16) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hotplace_board` (
  `hotId` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `userId` varchar(45) NOT NULL,
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `hit` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotId`),
  KEY `hotplace_board_to_user_fk_idx` (`userId`),
  CONSTRAINT `hotplace_board_to_user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hotplace_file_info` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `hotId` int DEFAULT NULL,
  `save_folder` varchar(45) DEFAULT NULL,
  `original_file` varchar(100) DEFAULT NULL,
  `save_file` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `hotplace_file_info_to_board_article_no_fk` (`hotId`),
  CONSTRAINT `hotplace_file_info_to_board_article_no_fk` FOREIGN KEY (`hotId`) REFERENCES `hotplace_board` (`hotId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `info_board` (
  `info_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `user_id` varchar(45) NOT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `hit` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`info_id`),
  KEY `writer_id_idx` (`user_id`),
  CONSTRAINT `info_board_to_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;