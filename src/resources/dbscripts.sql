CREATE DATABASE `myHotel`;
USE `myHotel`;

CREATE TABLE `person` (
                    `id` int(11) NOT NULL AUTO_INCREMENT,
                    `name` varchar(100) NOT NULL,
                    `age` int(4) DEFAULT NUll,
                    PRIMARY KEY (`id`);
                );

CREATE TABLE `client` (
  `id` INT NOT NULL REFERENCES person(id),
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `availability` TINYINT NOT NULL DEFAULT 1,
  `price` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`));

CREATE TABLE `singleroom` (
  `id` INT NOT NULL REFERENCES room(id),
  `id_firstperson` INT NULL REFERENCES person(id),
  PRIMARY KEY (`id`));

CREATE TABLE `doubleroom` (
  `id` INT NOT NULL REFERENCES singleroom(id),
  `id_secondperson` INT NULL REFERENCES person(id),
  PRIMARY KEY (`id`));

CREATE TABLE `hotel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_hotel` INT NOT NULL,
  `id_client` INT NOT NULL,
  `id_room` INT NOT NULL,
  `date_in` DATE NULL,
  `date_out` DATE NULL,
  `cost` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_booking_hotel`
    FOREIGN KEY (`id_hotel`)
    REFERENCES `myHotel`.`hotel` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `FK_booking_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `myHotel`.`client` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `FK_booking_room`
    FOREIGN KEY (`id_room`)
    REFERENCES `myHotel`.`room` (`id`)
    ON DELETE CASCADE
  );

ALTER TABLE `myHotel`.`client`
ADD CONSTRAINT `FK_client_person`
  FOREIGN KEY (`id`)
  REFERENCES `myHotel`.`person` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `myHotel`.`employee`
ADD CONSTRAINT `FK_employee_person`
  FOREIGN KEY (`id`)
  REFERENCES `myHotel`.`person` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
