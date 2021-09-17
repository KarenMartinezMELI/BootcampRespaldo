DROP DATABASE IF EXISTS `emple_dep`;
CREATE DATABASE `emple_dep`;

USE emple_dep;

DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department` (
  `dep_id` int NOT NULL,
  `dep_name` varchar(50) NOT NULL,
  `dep_adr` varchar(100) NOT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `emp_file_id` int NOT NULL,
  `emp_dni` varchar(10) NOT NULL,
  `emp_name` varchar(25) NOT NULL,
  `emp_surname` varchar(25) NOT NULL,
  `emp_birthdate` date DEFAULT NULL,
  `emp_entry_date` date NOT NULL,
  `emp_rol` varchar(25) NOT NULL,
  `emp_salary` double NOT NULL,
  `dep_id` int NOT NULL,
  PRIMARY KEY (`emp_file_id`,`emp_dni`),
  KEY `id_dep_idx` (`dep_id`),
  CONSTRAINT `dep_id` FOREIGN KEY (`dep_id`) REFERENCES `Department` (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `emple_dep`.`Department` (`dep_id`, `dep_name`, `dep_adr`) VALUES ('1', 'Customer', 'UnaDir');
INSERT INTO `emple_dep`.`Department` (`dep_id`, `dep_name`, `dep_adr`) VALUES ('2', 'Backend', 'OtraDir');
INSERT INTO `emple_dep`.`Department` (`dep_id`, `dep_name`, `dep_adr`) VALUES ('3', 'FrontEnd', 'UnaDir');
INSERT INTO `emple_dep`.`Department` (`dep_id`, `dep_name`, `dep_adr`) VALUES ('4', 'Seguridad', 'OtraDir');
INSERT INTO `emple_dep`.`Department` (`dep_id`, `dep_name`, `dep_adr`) VALUES ('5', 'Envios', 'UnaDir');

INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('1', '123456789', 'Marta', 'Rosas', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '2');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('2', '123456782', 'Juan', 'Casas', '1994/01/24', '2020/02/20', 'Paquetes', '1000', '5');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('3', '123456789', 'Marta', 'Torres', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '4');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('4', '123456784', 'Lucy', 'Castillos', '1994/01/24', '2020/02/20', 'Paquetes', '1000', '5');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('5', '123456789', 'Tonio', 'Rosas', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '2');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('6', '123456786', 'Miguel', 'DelPiano', '1994/01/24', '2020/02/20', 'UX', '1000', '3');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('7', '123456789', 'Nora', 'Martinez', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '2');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('8', '123456788', 'Martin', 'Gomez', '1994/01/24', '2020/02/20', 'Paquetes', '1000', '5');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('9', '123456789', 'Sofia', 'Rodriguez', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '2');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('10', '123456723', 'Carla', 'Lopez', '1994/01/24', '2020/02/20', 'Paquetes', '1000', '5');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('11', '123456783', 'Tania', 'Baron', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '3');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('12', '123456782', 'Valentina', 'Casas', '1994/01/24', '2020/02/20', 'Paquetes', '1000', '5');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('13', '124456782', 'Gabriel', 'Rosas', '1994/01/24', '2020/02/20', 'Desarrollo', '2000', '2');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('14', '123456782', 'Sebastian', 'Torres', '1994/01/24', '2020/02/20', 'Transacciones', '1000', '4');
INSERT INTO `emple_dep`.`Employee` (`emp_file_id`, `emp_dni`, `emp_name`, `emp_surname`, `emp_birthdate`, `emp_entry_date`, `emp_rol`, `emp_salary`, `dep_id`) VALUES ('15', '1234467892', 'Manolo', 'Rosas', '1994/01/24', '2020/02/20', 'Atencion al cliente', '2000', '1');





