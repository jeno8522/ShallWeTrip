-- -----------------------------------------------------
-- Schema ssafyweb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafyweb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyproject` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafyproject` ;

-- -----------------------------------------------------
-- Table `ssafyweb`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyproject`.`members` ;

CREATE TABLE IF NOT EXISTS `ssafyproject`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `nickname` VARCHAR(20) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `ssafyproject`.`members` (user_id, user_name, user_password, email, nickname, join_date)
values 	('admin', '관리자', '1234', 'admin@google.com', 'admin', now());
	
commit;