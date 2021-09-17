/*
-ci es un valor único.
-Cada estudiante tiene una id diferente
-Recordar que para cada lotteries solo puede haber 1 estudiante. No se pueden repetir.
En el proyecto existen formas de borrar loterias y hasta de vaciar las loterias. 
La idea es poder realizar varios sorteos en base a los estudiantes que hay en la base.
Se podra seguir sorteando hasta que no hayan estudiantes disponibles

Con h2 ir a http://localhost:8080/h2-console
Si no se usa h2 descomentar la linea de abajo.
*/

/* use lotteries_db; */

INSERT INTO students(`id`,`ci`,`name`,`surname`) 
VALUES 
(1,'1294567', 'Kaiba', 'Seto'),
(2,'1234566', 'Bob', 'Construye'),
(3,'1234557', 'Inuyasha', '?'),
(4,'1234565', 'Illidan', 'Tempestira'),
(5,'1234465', 'Arthur', 'Traidor'),
(6,'1224567', 'Karthus', 'Fantasmon'),
(7,'1333566', 'Anastasia', 'García'),
(8,'1233557', 'Sebastian', 'Mayordomo'),
(9,'1533565', 'Red', '?'),
(10,'1833465', 'Francisco', 'Torres'),
(11,'8234567', 'Fernanda', 'Castillos'),
(12,'8234566', 'Julio', 'García'),
(13,'8234557', 'Pedro', 'Martínez'),
(14,'8234565', 'Katherine','Encargos'),
(15,'8234465', 'Hu tao', 'Pyro'),
(16,'8224567', 'Brand', 'Sosa'),
(17,'8334566', 'Jesus', 'Mesias'),
(18,'8254557', 'Maria', 'Martínez'),
(19,'8534565', 'Juan', 'Sosa'),
(20,'8834465', 'Karen', 'Torres'),
(21,'1278567', 'Sofia', 'Rodriguez'),
(22,'1273566', 'Juan', 'García'),
(23,'1273557', 'Gonzalo', 'Gomez'),
(24,'1273565', 'Alicia', 'Maravilla'),
(25,'1273465', 'Genaro', 'Torres'),
(26,'1273567', 'Lucero', 'Sosa'),
(27,'1373566', 'Garen', 'Demacia'),
(28,'1274557', 'Taric', 'Gemas'),
(29,'1574565', 'Lulu', 'Enormizar'),
(30,'1874465', 'Diluc', 'Pyro'),
(31,'1274967', 'Razor', 'Electro'),
(32,'1274966', 'Baal', 'Electro'),
(33,'1234957', 'Xin xao', 'Anemo'),
(34,'1234965', 'Kaeya', 'Cyro'),
(35,'1234995', 'Morgana', 'Torres'),
(36,'1224997', 'Lux', 'Demacia'),
(37,'1334996', 'Amber', 'Pyro'),
(38,'1254907', 'Esmeralda', 'Verde'),
(39,'1534925', 'Gaston', 'Sosa'),
(40,'1834925', 'Sebastian', 'Torres'),
(41,'1434127', 'Manuel', 'Sosa'),
(42,'1734126', 'Willy', 'García'),
(43,'1734127', 'Principe', 'Azul'),
(44,'1734105', 'Johan', 'Sosa'),
(45,'1734125', 'Santiago', 'Torres'),
(46,'1704817', 'Leopoldo', 'Baron'),
(47,'1734866', 'Nacho', 'García'),
(48,'1754857', 'Lucia', 'Martínez'),
(49,'1734565', 'Alberto', 'Sosa'),
(50,'1834465', 'Yugi', 'Moto'),
(51,'1644567', 'Eduardo', 'Escudero'),
(52,'1644566', 'Jose', 'García'),
(53,'1644557', 'Jaimito', 'Cuentos'),
(54,'1644565', 'Bruno', 'Sosa'),
(55,'1644065', 'Carla', 'Torres'),
(56,'1604567', 'Carmen', 'Sosa'),
(57,'1604566', 'Sol', 'Naranja'),
(58,'1644057', 'Alejandra', 'Ramos'),
(59,'1644560', 'Roberta', 'DelPiano'),
(60,'1044465', 'Lila', 'Ramos');