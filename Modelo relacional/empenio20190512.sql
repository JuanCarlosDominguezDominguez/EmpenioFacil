CREATE DATABASE  IF NOT EXISTS `empenio` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `empenio`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: empenio
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adelantoapartado`
--

DROP TABLE IF EXISTS `adelantoapartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adelantoapartado` (
  `idadelantoapartado` int(11) NOT NULL,
  `monto` int(11) NOT NULL,
  `idVentaApartado` int(11) NOT NULL,
  PRIMARY KEY (`idadelantoapartado`),
  KEY `idVentaApartado_idx` (`idVentaApartado`),
  CONSTRAINT `idVentaApartado` FOREIGN KEY (`idVentaApartado`) REFERENCES `ventaapartado` (`idVentaApartado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adelantoapartado`
--

LOCK TABLES `adelantoapartado` WRITE;
/*!40000 ALTER TABLE `adelantoapartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `adelantoapartado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articulo` (
  `idArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `Prenda_idPrenda` int(11) NOT NULL,
  `Nota_idNota` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `descripcion` varchar(120) NOT NULL,
  `precio` float NOT NULL,
  `Comercializacion_idComercializacion1` int(11) NOT NULL,
  `tipoProducto` int(11) NOT NULL,
  `deBaja` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idArticulo`),
  KEY `fk_Articulo_Prenda1_idx` (`Prenda_idPrenda`),
  KEY `fk_Articulo_Categorias1_idx` (`categoria`),
  KEY `fk_Articulo_Comercializacion2_idx` (`Comercializacion_idComercializacion1`),
  KEY `fk_Articulo_Categorias2_idx` (`tipoProducto`),
  CONSTRAINT `fk_Articulo_Categorias1` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_Articulo_Categorias2` FOREIGN KEY (`tipoProducto`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_Articulo_Comercializacion2` FOREIGN KEY (`Comercializacion_idComercializacion1`) REFERENCES `comercializacion` (`idComercializacion`),
  CONSTRAINT `fk_Articulo_Prenda1` FOREIGN KEY (`Prenda_idPrenda`) REFERENCES `prenda` (`idPrenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorias` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `Categorias_idCategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `fk_Categorias_Categorias1_idx` (`Categorias_idCategoria`),
  CONSTRAINT `fk_Categorias_Categorias1` FOREIGN KEY (`Categorias_idCategoria`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Rol',NULL),(2,'Oro',NULL),(3,'Producto',NULL),(4,'Venta/apartado',NULL),(5,'Periodo',NULL),(6,'Ocupacion',NULL),(7,'Administrador',1),(8,'Cajero',1),(9,'Gerente',1),(10,'Bodeguero',1),(11,'Venta',4),(12,'Apartado',4),(13,'Semanal',5),(14,'Quincenal',5),(15,'Mensual',5),(16,'Maestro',6),(17,'Ingeniero',6),(18,'Ama de casa',6),(19,'Carpitero',6),(20,'Mecanico',6),(22,'Equipo de computo',3);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `nombre` varchar(30) NOT NULL,
  `apellidoPaterno` varchar(45) NOT NULL,
  `apellidoMaterno` varchar(45) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `curp` varchar(18) NOT NULL,
  `numeroIdentificacion` varchar(20) NOT NULL,
  `ocupacion` int(11) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `enListaNegra` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rfc`),
  KEY `fk_Cliente_Categorias1_idx` (`ocupacion`),
  CONSTRAINT `fk_Cliente_Categorias1` FOREIGN KEY (`ocupacion`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('F','G','G','12356ABC','AAAAAAA123123','123123hsjdrit234',18,'2019-05-09',0),('Yeshua','Vélez','Álvarez','VEAY970402ABC','VEAY970402HVZABC12','1111',20,'2019-05-07',0),('Karla','Villa','Hernández','VEHK12312322','VEHK12312312111','ABCABCABC12',17,'2019-05-09',0),('Jonathan','Vélez','Álvarez','XXXX000000AAA','XXXX000000XXXXXX00','9999K',16,'2019-05-07',0),('Yeriel','Zamora','Ortiz','ZAOY970525AAA','ZAOY970525HVZMRR08','123lmao',17,'2019-05-07',0);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercializacion`
--

DROP TABLE IF EXISTS `comercializacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comercializacion` (
  `idComercializacion` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario_numPersonal` int(11) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idComercializacion`),
  KEY `fk_Comercializacion_Usuario1_idx` (`Usuario_numPersonal`),
  CONSTRAINT `fk_Comercializacion_Usuario1` FOREIGN KEY (`Usuario_numPersonal`) REFERENCES `usuario` (`numPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercializacion`
--

LOCK TABLES `comercializacion` WRITE;
/*!40000 ALTER TABLE `comercializacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `comercializacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `Cliente_rfc` varchar(13) NOT NULL,
  `Usuario_numPersonal` int(11) NOT NULL,
  `interesOrdinario` int(11) NOT NULL,
  `interesAlmacen` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  PRIMARY KEY (`idContrato`),
  KEY `fk_Contrato_Cliente1_idx` (`Cliente_rfc`),
  KEY `fk_Contrato_Usuario1_idx` (`Usuario_numPersonal`),
  CONSTRAINT `fk_Contrato_Cliente1` FOREIGN KEY (`Cliente_rfc`) REFERENCES `cliente` (`rfc`),
  CONSTRAINT `fk_Contrato_Usuario1` FOREIGN KEY (`Usuario_numPersonal`) REFERENCES `usuario` (`numPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotocliente`
--

DROP TABLE IF EXISTS `fotocliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fotocliente` (
  `idfotoCliente` int(11) NOT NULL,
  `Cliente_rfc` varchar(13) NOT NULL,
  `foto` longblob ,
  PRIMARY KEY (`idfotoCliente`),
  KEY `fk_fotoCliente_Cliente1_idx` (`Cliente_rfc`),
  CONSTRAINT `fk_fotoCliente_Cliente1` FOREIGN KEY (`Cliente_rfc`) REFERENCES `cliente` (`rfc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotocliente`
--

LOCK TABLES `fotocliente` WRITE;
/*!40000 ALTER TABLE `fotocliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotocliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotocontrato`
--

DROP TABLE IF EXISTS `fotocontrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fotocontrato` (
  `idfotoContrato` int(11) NOT NULL AUTO_INCREMENT,
  `Contrato_idContrato` int(11) NOT NULL,
  `foto` longblob ,
  PRIMARY KEY (`idfotoContrato`),
  KEY `fk_fotoContrato_Contrato1_idx` (`Contrato_idContrato`),
  CONSTRAINT `fk_fotoContrato_Contrato1` FOREIGN KEY (`Contrato_idContrato`) REFERENCES `contrato` (`idContrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotocontrato`
--

LOCK TABLES `fotocontrato` WRITE;
/*!40000 ALTER TABLE `fotocontrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotocontrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametrossucursal`
--

DROP TABLE IF EXISTS `parametrossucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `parametrossucursal` (
  `idSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `fondo` int(11) NOT NULL,
  `interesOrdinario` int(11) NOT NULL,
  `interesAlmacen` int(11) NOT NULL,
  `tipoPeriodo` int(11) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `fk_ParametrosSucursal_Categorias1_idx` (`tipoPeriodo`),
  CONSTRAINT `fk_ParametrosSucursal_Categorias1` FOREIGN KEY (`tipoPeriodo`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametrossucursal`
--

LOCK TABLES `parametrossucursal` WRITE;
/*!40000 ALTER TABLE `parametrossucursal` DISABLE KEYS */;
INSERT INTO `parametrossucursal` VALUES (1,8000000,8,5,14);
/*!40000 ALTER TABLE `parametrossucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `periodo` (
  `idPeriodo` int(11) NOT NULL AUTO_INCREMENT,
  `Contrato_idContrato` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `montoRefrendo` int(11) NOT NULL,
  `tipoPeriodo` int(11) NOT NULL,
  `montoFiniquito` int(11) NOT NULL,
  PRIMARY KEY (`idPeriodo`),
  KEY `fk_Periodo_Contrato1_idx` (`Contrato_idContrato`),
  KEY `fk_Periodo_Categorias1_idx` (`tipoPeriodo`),
  CONSTRAINT `fk_Periodo_Categorias1` FOREIGN KEY (`tipoPeriodo`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_Periodo_Contrato1` FOREIGN KEY (`Contrato_idContrato`) REFERENCES `contrato` (`idContrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prenda` (
  `descripcion` varchar(120) NOT NULL,
  `avaluo` int(11) NOT NULL,
  `prestamo` int(11) NOT NULL,
  `idPrenda` int(11) NOT NULL AUTO_INCREMENT,
  `Contrato_idContrato` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `tipoProducto` int(11) NOT NULL,
  PRIMARY KEY (`idPrenda`),
  KEY `fk_Prenda_Contrato1_idx` (`Contrato_idContrato`),
  KEY `fk_Prenda_Categorias1_idx` (`categoria`),
  KEY `fk_Prenda_Categorias2_idx` (`tipoProducto`),
  CONSTRAINT `fk_Prenda_Categorias1` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_Prenda_Categorias2` FOREIGN KEY (`tipoProducto`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_Prenda_Contrato1` FOREIGN KEY (`Contrato_idContrato`) REFERENCES `contrato` (`idContrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `Venta/apartado` int(11) NOT NULL,
  `Articulo_idArticulo` int(11) NOT NULL,
  KEY `fk_Venta/apartado_has_Articulo_Articulo1_idx` (`Articulo_idArticulo`),
  KEY `fk_Venta/apartado_has_Articulo_Venta/apartado1_idx` (`Venta/apartado`),
  CONSTRAINT `fk_Venta/apartado_has_Articulo_Articulo1` FOREIGN KEY (`Articulo_idArticulo`) REFERENCES `articulo` (`idArticulo`),
  CONSTRAINT `fk_Venta/apartado_has_Articulo_Venta/apartado1` FOREIGN KEY (`Venta/apartado`) REFERENCES `ventaapartado` (`idVentaApartado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `numPersonal` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(60) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `rol` int(11) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `deBaja` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`numPersonal`),
  KEY `fk_Usuario_Categorias1_idx` (`rol`),
  CONSTRAINT `fk_Usuario_Categorias1` FOREIGN KEY (`rol`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Yeriel Zamora Ortiz','pass',7,'2019-05-09', 0),(2,'Juan','123',8,'2019-05-09', 0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventaapartado`
--

DROP TABLE IF EXISTS `ventaapartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ventaapartado` (
  `idVentaApartado` int(11) NOT NULL AUTO_INCREMENT,
  `monto` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `Cliente_rfc` varchar(13) NOT NULL,
  `Usuario_numPersonal` int(11) NOT NULL,
  `tipoVenta` int(11) NOT NULL,
  PRIMARY KEY (`idVentaApartado`),
  KEY `fk_VentaApartado_Cliente1_idx` (`Cliente_rfc`),
  KEY `fk_VentaApartado_Usuario1_idx` (`Usuario_numPersonal`),
  KEY `fk_VentaApartado_Categorias1_idx` (`tipoVenta`),
  CONSTRAINT `fk_VentaApartado_Categorias1` FOREIGN KEY (`tipoVenta`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_VentaApartado_Cliente1` FOREIGN KEY (`Cliente_rfc`) REFERENCES `cliente` (`rfc`),
  CONSTRAINT `fk_VentaApartado_Usuario1` FOREIGN KEY (`Usuario_numPersonal`) REFERENCES `usuario` (`numPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventaapartado`
--

LOCK TABLES `ventaapartado` WRITE;
/*!40000 ALTER TABLE `ventaapartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventaapartado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-11 17:53:10
