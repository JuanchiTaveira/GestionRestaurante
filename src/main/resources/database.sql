CREATE DATABASE  IF NOT EXISTS `gestion_restaurante` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestion_restaurante`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_restaurante
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ar8ntug2bm5pr1irt7f9oysox` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Taveira','juan.taveira@gmail.com','Juan','+34112233'),(2,'Messi','leo.messi@gmail.com','Leo','+34112233'),(3,'Azpiazu','asier@gmail.com','Asier','+34666000666'),(4,'Carrillo','sancho.bustamante@hotmail.com','Gabriela','918-239-738'),(5,'Canales','magdalena.hernandes@hotmail.com','María Elena','930537706'),(6,'Pedraza','cristina.arreola@yahoo.com','Clara','997178318'),(7,'Gallardo','gregorio.fuentes@yahoo.com','Conchita','912-007-416'),(8,'Valdez','jose.acosta@hotmail.com','Débora','972573746'),(9,'Vela','natalia.covarrubias@gmail.com','Cristian','912.363.312'),(10,'Pedraza','ruben.zamudio@yahoo.com','Guillermo','903 484 210'),(11,'Arroyo','lola.fernandez@hotmail.com','Elvira','965.785.242'),(12,'Arteaga','mariaelena.bermudez@yahoo.com','Sergio','921-527-537'),(13,'Palomino','joseemilio.esquivel@yahoo.com','Esteban','905453107'),(14,'Huerta','lola.baez@gmail.com','Carmen','932-284-137'),(15,'Alvarado','manuela.maestas@hotmail.com','Martín','916 392 148'),(16,'Pagan','miguel.colon@hotmail.com','Laura','907617771'),(17,'Lucio','ramon.solorzano@hotmail.com','Catalina','980.336.128'),(18,'Vega','raquel.gracia@hotmail.com','Cristian','942.589.929'),(19,'Lemus','miguelangel.marquez@yahoo.com','Eduardo','923433640'),(20,'Madrigal','cristina.granado@hotmail.com','Concepción','983 988 829'),(21,'Manzanares','guillermina.cuellar@hotmail.com','Ramón','986395265'),(22,'Delapaz','mariajose.marrero@hotmail.com','Marcos','935 312 693'),(23,'Pacheco','ramona.melendez@yahoo.com','Ramón','900121926'),(24,'Mojica','hugo.maestas@hotmail.com','Benito','977-593-773'),(25,'Gaitán','sergio.saldana@hotmail.com','Luis','953070166'),(26,'Morales','patricio.espinal@hotmail.com','Óscar','916 866 336'),(27,'Colón','esperanza.echevarria@hotmail.com','Anita','984.944.972'),(28,'Leyva','ester.montemayor@gmail.com','Jacobo','902-689-918'),(29,'Merino','vicente.altamirano@yahoo.com','Ana Luisa','901 698 716'),(30,'Colón','mayte.montenegro@gmail.com','Alfonso','923-183-106'),(31,'Aponte','luis.mondragon@hotmail.com','Ramona','936 931 744'),(32,'Curiel','marcos.orosco@yahoo.com','Miguel Ángel','912-449-466'),(33,'Rentería','gustavo.villarreal@yahoo.com','Catalina','956.243.567'),(34,'Espinal','leticia.armijo@yahoo.com','Ariadna','913084312'),(35,'Briones','gustavo.casanova@gmail.com','Antonia','970150314'),(36,'Puente','eva.valladares@gmail.com','Amalia','909.692.945'),(37,'Alarcón','tomas.lozada@gmail.com','Mario','978879024'),(38,'Gutiérrez','ester.matias@yahoo.com','Clemente','981-914-146'),(39,'Matías','angela.carvajal@gmail.com','José Emilio','989-060-411'),(40,'Aragón','lucas.benavides@hotmail.com','María José','976-937-085'),(41,'Delrío','lourdes.melendez@gmail.com','Emilia','912894733'),(42,'Suárez','mayte.salgado@gmail.com','Victoria','906.504.708'),(43,'Sáenz','conchita.abreu@yahoo.com','Ana Luisa','953-724-127'),(44,'Salazar','anita.barreto@yahoo.com','Carolina','965.096.771'),(45,'Aguayo','joseluis.abrego@hotmail.com','Andrés','913-277-792'),(46,'Alba','roberto.almaraz@hotmail.com','Lorenzo','956 177 334'),(47,'Flores','federico.casanova@yahoo.com','Laura','984.035.305'),(48,'Cardenas','ines.velasquez@hotmail.com','Mario','980713702'),(49,'Treviño','armando.munoz@hotmail.com','Adela','964 212 257'),(50,'Fonseca','amalia.lopez@hotmail.com','Lucas','977.153.885'),(51,'Pichardo','armando.madera@hotmail.com','Salvador','995535801'),(52,'Ríos','alfonso.varela@yahoo.com','María Soledad','987 222 070'),(53,'Polanco','jorgeluis.cazares@hotmail.com','María del Carmen','974369279');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `admin` bit(1) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (_binary '',1,'Admin','12345678','Admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin'),(_binary '\0',2,'Taveira','Y123456Z','Juan','ed08c290d7e22f7bb324b15cbadce35b0b348564fd2d5f95752388d86d71bcca','juan'),(_binary '\0',3,'Azpiazu','12345678Z','Asier','e507f1a62080a184a06e719846ce6bb64542ad1789e70982a8ecc6cedf5ea20e','asier'),(_binary '',4,'Olivárez','242-31-3033','Dolores','b7bab1ebf0096c13508364016a0b7f7620ce65a8e60d5f16614e1754ebfc0039','santiago.urías'),(_binary '\0',5,'Almaraz','780-42-8632','Pedro','2f980ef6e487a77dabb711fe46a85e56d3b5a9317cc05a4686832b7a44a50021','ernesto.molina'),(_binary '\0',6,'Ramón','114-30-8560','Cecilia','f02837d6ea6585205d5e4459ac1f4fc191744835d5182cb5d376d816b3353de5','francisca.zamora'),(_binary '\0',7,'Ceballos','869-94-0676','Isabela','c3e2026b3f4f35250669531cd66eedec8f0e1bb36733e89cb93e9042408f2ab2','reina.sarabia'),(_binary '\0',8,'Valentín','130-85-0506','Ana María','11053cd73d2bdf6bdd94be50162905e37ef5b2be2f2332692c9959a06cb90669','jorge.sauceda'),(_binary '\0',9,'Serrano','115-74-7720','Sara','2d0e1d49a8eeeaf2e56688deff762730b7f91347615ec24cea1c5cd3be2b964b','gerardo.ontiveros'),(_binary '\0',10,'Posada','463-59-7667','Agustín','f2bfd3ad77a53db2af3cac69cba68f3b70d5458b115d86129adeec58c627bd53','mateo.calderón'),(_binary '\0',11,'Rosario','285-88-1707','María','4a1e2457786b787d5e346513bb656dc2db8bd9924b9c39ae2fc7ae0b72866d3a','sofia.gallegos'),(_binary '\0',12,'Aguirre','595-98-6735','Hernán','19bc562cb67138214af3ebe85edbdf025f9e58ac2e910825a261006fc9bac481','césar.vega'),(_binary '\0',13,'Tapia','126-84-8269','Natalia','a017382617a56090257ba56c80bf9ccfbd3ee7a7d4b419d67bdc9c7fc334fd4d','rosario.camacho');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas` (
  `max_personas` int DEFAULT NULL,
  `numero_mesa` int NOT NULL,
  PRIMARY KEY (`numero_mesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas`
--

LOCK TABLES `mesas` WRITE;
/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
INSERT INTO `mesas` VALUES (10,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(2,10),(2,11),(2,12),(2,13);
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `cliente_id` int NOT NULL,
  `dia` date NOT NULL,
  `empleado_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_mesa` int NOT NULL,
  `numero_personas` int NOT NULL,
  `horario` enum('ALMUERZO','CENA') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK474ngys4y846umkd9781qonyq` (`cliente_id`),
  KEY `FKoqs16prpavj992n9t2lxr9egh` (`empleado_id`),
  CONSTRAINT `FK474ngys4y846umkd9781qonyq` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKoqs16prpavj992n9t2lxr9egh` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'2024-05-12',1,1,1,5,'ALMUERZO'),(2,'2024-05-12',3,2,5,4,'ALMUERZO'),(3,'2024-05-12',2,3,2,3,'ALMUERZO'),(4,'2024-05-15',9,4,2,2,'CENA'),(5,'2024-05-16',8,5,9,3,'CENA'),(6,'2024-05-14',9,6,9,1,'ALMUERZO'),(7,'2024-05-14',4,7,7,2,'CENA'),(8,'2024-05-14',8,8,2,1,'ALMUERZO'),(9,'2024-05-15',6,9,11,1,'ALMUERZO'),(10,'2024-05-14',8,10,9,2,'CENA'),(11,'2024-05-13',4,11,5,2,'CENA'),(12,'2024-05-15',3,12,6,2,'ALMUERZO'),(13,'2024-05-13',5,13,10,1,'ALMUERZO'),(16,'2024-05-16',6,14,4,1,'ALMUERZO'),(17,'2024-05-16',9,15,1,9,'ALMUERZO'),(18,'2024-05-16',8,16,3,2,'ALMUERZO'),(19,'2024-05-14',8,17,2,2,'CENA'),(20,'2024-05-16',4,18,10,1,'CENA'),(21,'2024-05-13',6,19,4,1,'CENA'),(23,'2024-05-16',7,20,11,1,'CENA'),(24,'2024-05-14',9,21,4,2,'ALMUERZO'),(25,'2024-05-15',2,22,4,1,'ALMUERZO'),(26,'2024-05-15',2,23,11,1,'CENA'),(27,'2024-05-14',2,24,10,1,'CENA'),(29,'2024-05-13',2,25,5,1,'ALMUERZO'),(30,'2024-05-12',3,26,10,1,'ALMUERZO'),(31,'2024-05-15',3,27,9,2,'ALMUERZO'),(32,'2024-05-14',7,28,6,3,'CENA'),(33,'2024-05-15',6,29,5,2,'ALMUERZO'),(36,'2024-05-12',2,30,9,2,'ALMUERZO'),(39,'2024-05-13',8,31,2,2,'ALMUERZO'),(40,'2024-05-13',5,32,2,2,'CENA'),(41,'2024-05-16',8,33,5,1,'ALMUERZO'),(42,'2024-05-15',4,34,8,1,'ALMUERZO'),(43,'2024-05-15',9,35,8,2,'CENA'),(44,'2024-05-12',2,36,6,2,'ALMUERZO'),(47,'2024-05-14',8,37,5,1,'ALMUERZO'),(50,'2024-05-13',8,38,4,2,'ALMUERZO'),(53,'2024-05-14',8,39,3,2,'ALMUERZO');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-12 16:55:32
