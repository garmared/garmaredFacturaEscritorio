-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2021 a las 17:35:27
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `garmared_factura`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(9) NOT NULL,
  `CIF` char(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Direccion` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Poblacion` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Provincia` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `CP` int(5) UNSIGNED ZEROFILL NOT NULL,
  `Telefono1` int(9) NOT NULL,
  `Telefono2` int(9) NOT NULL,
  `Telefono3` int(9) NOT NULL,
  `Persona_contacto` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `mail` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `web` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `fp` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `dia_pago` int(2) NOT NULL,
  `modo_pago` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `id_empresa` int(4) NOT NULL,
  `iban` char(24) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `CIF`, `Nombre`, `Direccion`, `Poblacion`, `Provincia`, `CP`, `Telefono1`, `Telefono2`, `Telefono3`, `Persona_contacto`, `mail`, `web`, `fp`, `dia_pago`, `modo_pago`, `observaciones`, `activo`, `id_empresa`, `iban`) VALUES
(2, '46350800', 'Edu', 'Aqui', '1', '1', 05258, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'N', 0, ''),
(3, '46350000D', 'Juan Palomo', 'Palomares 12', 'Barcelona', 'Barcelona', 08036, 936585452, 656525252, 658696585, 'Jacinta', 'notiene@correo.es', 'notien.es', '1', 1, '1', 'prueba', 'S', 0, ''),
(8, '1', 'Luis', 'Urgell', 'Barcelona', 'Barcelona', 01231, 324342, 43423, 324234, 'Pedro', 'ddddd', 'j.com', '1', 1, '1', 'sa', 'S', 0, ''),
(10, '1', 'Ejemplo1', '1', '1', '1', 00001, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 7, ''),
(12, '1', 'cliente1', 'alli', 'alli', '1', 00001, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8, ''),
(14, '1', 'cliente3', 'alli', 'alli', '323', 12312, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8, ''),
(15, '1', 'cliente5', 'alli', 'alli', '1', 00001, 6666, 1, 1, '1', 'notiene@correo.es', '1', '1', 1, '1', '1', 'S', 8, ''),
(16, '1', 'cliente4', 'alli', 'alli', '1', 00001, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8, ''),
(17, '43242', 'eeee', 'ee', 'ee', 'ee', 00022, 698545452, 22, 22, '32dsf', 'dfsfsd', 'fsdfs', '1', 1, '1', 'dsfsdf', 'S', 8, ''),
(18, '1', 'cliente2', 'alli', 'alli', '1', 00001, 6666, 1, 1, '1', 'notiene@correo.es', '1', '1', 1, '1', '1', 'S', 8, ''),
(19, '34583834', 'Juan', 'esa calle', 'Barcelona', 'Barcelona', 03423, 453453, 34423, 34234, 'Alex', 'no tiene', '1', '1', 1, '1', '1', 'S', 12, 'ES9123141412432432'),
(20, '7897', 'E', '879', '879', '9879', 00979, 652545421, 797, 979, '97', '79', '97', '1', 8, '2', '8', 'S', 8, ''),
(21, '324234', 'Pedro', '3', '3', '3', 00003, 3, 3, 3, '3', '3', '3', '3', 3, '3', '3', 'S', 8, ''),
(22, '123123', 'Prueba FP', 'sdfsdf', 'dfsdf', 'fsdfsd', 04324, 34234, 432423, 43242, 'efsdf', 'fsdfs', 'fsdfs', '4', 1, '2', '2123', 'S', 8, ''),
(23, '32434234', '43242', '432423', '43242', '432423', 04234, 42342, 42342, 43242, '423', '423', '432', '1', 42342, '1', '42432', 'S', 12, ''),
(24, '13213546H', 'Pedro', 'Villarroel 123', 'Barcelona', 'Barcelona', 08025, 658745254, 362542254, 2525424, 'Juan', 'notiene', 'notiene', '1', 1, '1', ' ', 'S', 12, 'ES344375874358335934');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conceptoproyecto`
--

CREATE TABLE `conceptoproyecto` (
  `idProyecto` int(4) NOT NULL,
  `idConcepto` text COLLATE utf8_spanish2_ci NOT NULL,
  `importe` decimal(20,4) NOT NULL,
  `empresa` int(4) NOT NULL,
  `iva` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `conceptoproyecto`
--

INSERT INTO `conceptoproyecto` (`idProyecto`, `idConcepto`, `importe`, `empresa`, `iva`) VALUES
(11, 'Edu', '123.0000', 8, '231.00'),
(14, ' edu', '12.0000', 8, '1.00'),
(14, ' juan', '21.0000', 8, '2.00'),
(14, ' pedro', '3.0000', 8, '5.00'),
(5, ' juan', '3.0000', 8, '3.00'),
(6, ' Taxi', '12.0000', 8, '12.00'),
(6, 'Material', '1.0000', 8, '1.00'),
(13, 'Taxi', '2.3000', 8, '12.00'),
(13, 'Material', '0.0000', 8, '0.00'),
(13, 'Piedras', '12.0000', 8, '12.00'),
(15, 'Ladrillos', '12300.0000', 12, '10.00'),
(15, 'Ventanas', '1222.0000', 12, '21.00'),
(15, 'parquet', '500.0000', 12, '10.00'),
(16, 'ladrillos', '1231.0000', 12, '10.00'),
(16, 'ventanas', '2563.0000', 12, '21.00'),
(16, 'pladur', '258.0000', 12, '10.00'),
(12, 'gggjgj', '1212.0000', 12, '12.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conceptos`
--

CREATE TABLE `conceptos` (
  `id_concepto` int(3) NOT NULL,
  `descripcion` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `id_empresa` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `conceptos`
--

INSERT INTO `conceptos` (`id_concepto`, `descripcion`, `id_empresa`) VALUES
(1, 'concepto', 0),
(2, 'Concepto1', 0),
(3, 'concepto2', 0),
(4, 'a1', 7),
(5, 'Conceepto1', 8),
(6, 'Taxi', 8),
(7, ' Gasolina', 8),
(8, 'Material Oficina', 8),
(9, 'Taxi', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `constantes`
--

CREATE TABLE `constantes` (
  `tiporeg` text COLLATE utf8_spanish2_ci NOT NULL,
  `codigo` int(4) NOT NULL,
  `nombre` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `constantes`
--

INSERT INTO `constantes` (`tiporeg`, `codigo`, `nombre`) VALUES
('FPAG', 1, 'Semanal'),
('FPAG', 2, 'Mensual'),
('FPAG', 3, 'Trimestral'),
('FPAG', 4, 'Anual'),
('FPAG', 5, 'Único'),
('MODP', 1, 'Tarjeta'),
('MODP', 2, 'Transferéncia'),
('MODP', 3, 'Efectivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `costes`
--

CREATE TABLE `costes` (
  `id_coste` int(3) NOT NULL,
  `descripcion` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `id_empresa` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `costes`
--

INSERT INTO `costes` (`id_coste`, `descripcion`, `id_empresa`) VALUES
(1, 'taxi', 0),
(2, 'gasolina', 0),
(3, 'Restaurantes', 0),
(5, 'a1', 7),
(6, 'coste1', 8),
(7, 'coste2', 8),
(8, 'coste3', 8),
(9, 'Ladrillos', 12),
(10, 'Coste 120', 8),
(11, 'Papelería', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `costesindirectos`
--

CREATE TABLE `costesindirectos` (
  `id_costeInd` int(3) NOT NULL,
  `empresa` int(4) NOT NULL,
  `id_coste` int(3) NOT NULL,
  `id_proyecto` int(4) NOT NULL,
  `id_concepto` int(3) NOT NULL,
  `importe` decimal(15,2) NOT NULL,
  `descripcion` char(100) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `costesindirectos`
--

INSERT INTO `costesindirectos` (`id_costeInd`, `empresa`, `id_coste`, `id_proyecto`, `id_concepto`, `importe`, `descripcion`) VALUES
(1, 7, 8, 6, 5, '1.00', 'dsfsdfs'),
(2, 6, 8, 5, 7, '1.00', '1'),
(3, 6, 8, 5, 7, '125.00', 'coste 1'),
(4, 6, 8, 5, 5, '12.00', 'hola'),
(5, 8, 7, 5, 5, '12.00', 'coste2'),
(6, 8, 7, 5, 5, '12.00', 'juan'),
(8, 8, 6, 5, 5, '12.00', 'pedro'),
(9, 8, 6, 10, 8, '1231.25', 'fdsfsd'),
(10, 8, 11, 6, 5, '23123.00', '2312');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `id_empresa` int(4) NOT NULL COMMENT 'este valor sirve para empresa o proveedor, es el identificador',
  `empresa` int(4) NOT NULL COMMENT 'el valor es 0 para empresas y para proveedor es la relacion con la empresa',
  `CIF` char(9) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Direccion` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Poblacion` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Provincia` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `CP` int(5) UNSIGNED ZEROFILL NOT NULL,
  `telefono1` int(9) NOT NULL,
  `telefono2` int(9) NOT NULL,
  `telefono3` int(9) NOT NULL,
  `Persona_contacto` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `mail` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `web` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `IBAN` char(24) COLLATE utf8_spanish2_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `reg_mercantil` text COLLATE utf8_spanish2_ci NOT NULL,
  `tomo` int(10) NOT NULL,
  `folio` int(10) NOT NULL,
  `hoja` int(10) NOT NULL,
  `inscripcion` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`id_empresa`, `empresa`, `CIF`, `tipo`, `Nombre`, `Direccion`, `Poblacion`, `Provincia`, `CP`, `telefono1`, `telefono2`, `telefono3`, `Persona_contacto`, `mail`, `web`, `IBAN`, `observaciones`, `activo`, `reg_mercantil`, `tomo`, `folio`, `hoja`, `inscripcion`) VALUES
(2, 0, '46350000D', 'P', 'Proveedor1', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S', '', 0, 0, 0, 0),
(4, 0, '34324', 'P', 'hjdfhsdkj', 'jhkh', 'hkhk', 'hkhk', 00001, 3333, 333, 333, 'khk', 'hkhk', 'khk', '1', '3', 'S', '', 0, 0, 0, 0),
(5, 0, '324234', 'E', 'Juan', '43244234', '4324', '42342', 42342, 42342, 423423, 42323, '43242', '423423', '42432', '42432', '4234', 'N', '', 0, 0, 0, 0),
(6, 0, '4234234', 'E', 'Empresa1', 'a', 'a', 's', 00002, 2, 2, 2, 's', 's', 'd', '1', '1212', 'S', '', 0, 0, 0, 0),
(7, 0, '11', 'E', 'Dani Garcia', '255 bolin', 'dubai', 'Emiratos Arabes', 90201, 603418629, 775409432, 546754, 'Edu suldiandr', 'no me lo se', 'no te importa', '5', '5', 'N', '', 0, 0, 0, 0),
(8, 1, '324234', 'P', '4324', '4324', '432', '432', 04324, 432, 432, 42, '423', '42', '42', '423', '423', 'N', '', 0, 0, 0, 0),
(8, 2, '34234', 'P', '432423', '43242', '4322', '432', 00423, 432, 432, 423, '432', '432', '423', '423', '42', 'N', '', 0, 0, 0, 0),
(9, 8, '46350000D', 'P', 'Proveedor2', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S', '', 0, 0, 0, 0),
(10, 8, '46350000D', 'P', 'Proveedor3', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S', '', 0, 0, 0, 0),
(11, 0, '46350800', 'E', 'Edu', 'Villarroel 162', 'Barcelona', 'Barcelona', 08036, 932198558, 666404249, 666666666, 'Montse', 'notien@correo.es', 'notiene', 'ES95210001101111111111', 'ninguna', 'S', '', 0, 0, 0, 0),
(12, 0, '3512', 'E', 'Arqpoma', 'alla', 'Barcelona', 'Barcelona', 08025, 453453, 4356645, 46645, 'Alex', 'info@arqpoma.es', 'no', 'no', 'no', 'S', '', 0, 0, 0, 0),
(12, 1, '3213213', 'P', '31231', '32131', '31231', '3123', 03123, 3123, 32131, 312, '3123', '312', '3123', '312', '3123', 'N', '', 0, 0, 0, 0),
(14, 0, 'B67102343', 'E', 'Irona', 'Corcega 453', 'Barcelona', 'Barcelona', 08034, 935432343, 656434234, 676546545, 'Alex', 'info@irona,es', 'irona.es', 'ES9521001001001111111111', 'no hay', 'S', 'Barcelona', 324234, 321, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(9) NOT NULL,
  `id_empresa` int(4) NOT NULL,
  `fecha` int(8) NOT NULL,
  `vencimiento` int(8) NOT NULL,
  `id_proyecto` int(4) NOT NULL,
  `id_cliente` int(9) NOT NULL,
  `id_concepto` int(3) NOT NULL,
  `id_coste` int(3) NOT NULL,
  `id_proveedor` int(4) NOT NULL,
  `IRPF` decimal(5,2) NOT NULL,
  `descuento` decimal(5,2) NOT NULL,
  `IBAN` char(24) COLLATE utf8_spanish2_ci NOT NULL,
  `base_impo` decimal(15,4) NOT NULL,
  `IVA` decimal(5,2) NOT NULL,
  `tasa` decimal(5,2) NOT NULL,
  `pagado` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `id_empresa`, `fecha`, `vencimiento`, `id_proyecto`, `id_cliente`, `id_concepto`, `id_coste`, `id_proveedor`, `IRPF`, `descuento`, `IBAN`, `base_impo`, `IVA`, `tasa`, `pagado`) VALUES
(3, 8, 20200818, 20210820, 5, 12, 7, 6, 0, '1.00', '1.00', '1', '1.0000', '4.00', '1.00', 'Abono'),
(8, 8, 20210113, 20220101, 5, 12, 6, 6, 0, '9.00', '9.00', '9', '9.0000', '9.00', '9.00', 'Pagado'),
(9, 8, 20210111, 20220105, 5, 12, 7, 7, 0, '25.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(10, 8, 20210105, 20210129, 5, 0, 5, 6, 9, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(11, 8, 20210112, 20210130, 5, 15, 6, 6, 9, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(14, 8, 20210121, 20210129, 5, 16, 5, 7, 0, '0.00', '1.00', '1', '1.0000', '1.00', '0.00', 'Pendiente'),
(15, 8, 20210101, 20210121, 10, 0, 5, 6, 10, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(16, 8, 20210127, 20210129, 6, 16, 5, 7, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(17, 8, 20210210, 20210228, 10, 18, 5, 6, 0, '12.00', '12.00', '12', '212.0000', '12.00', '12.00', 'Abono'),
(18, 8, 20210202, 20210226, 10, 0, 5, 7, 9, '12.00', '121.00', ' 12', '1222.0000', '12.00', '12.00', 'Abono'),
(19, 12, 20210310, 20210328, 12, 19, 6, 9, 0, '12.00', '12.00', '212', '12.0000', '12.00', '12.00', 'Abono'),
(20, 8, 20210319, 20210328, 10, 12, 7, 7, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(21, 8, 20210302, 20210331, 10, 18, 5, 8, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(22, 8, 20210309, 20210330, 10, 0, 6, 10, 10, '12.00', '12.00', '12', '12.0000', '12.00', '12.00', 'Pendiente'),
(23, 12, 20210426, 20210429, 18, 24, 6, 9, 0, '0.00', '12.00', 'ES98342342', '12.0000', '12.00', '12.00', 'Pendiente'),
(24, 12, 20210427, 20210430, 12, 0, 6, 9, 12, '125.00', '12.00', '12', '12.0000', '12.00', '12.00', 'Abono');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_Empresa` int(4) NOT NULL,
  `idPago` int(3) NOT NULL,
  `idProyecto` int(4) NOT NULL,
  `importe` decimal(20,4) NOT NULL,
  `fecha` int(8) NOT NULL,
  `vencimiento` int(8) NOT NULL,
  `estado` text COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` text CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `observaciones` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_Empresa`, `idPago`, `idProyecto`, `importe`, `fecha`, `vencimiento`, `estado`, `tipo`, `observaciones`) VALUES
(12, 31, 24, '3123.0000', 3123, 23123, '', '2', 'hola'),
(12, 32, 24, '213.0000', 1213, 2131, '', '3', '1313'),
(12, 33, 24, '123.0000', 20220101, 20210101, '', '1', 'pago');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedor` int(4) NOT NULL,
  `CIF` char(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Direccion` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Poblacion` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Provincia` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `CP` int(5) UNSIGNED ZEROFILL NOT NULL,
  `telefono1` int(9) NOT NULL,
  `telefono2` int(9) NOT NULL,
  `telefono3` int(9) NOT NULL,
  `Persona_contacto` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `mail` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `web` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Tipo_coste` int(3) NOT NULL,
  `IBAN` char(24) COLLATE utf8_spanish2_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `id_empresa` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_proveedor`, `CIF`, `Nombre`, `Direccion`, `Poblacion`, `Provincia`, `CP`, `telefono1`, `telefono2`, `telefono3`, `Persona_contacto`, `mail`, `web`, `Tipo_coste`, `IBAN`, `observaciones`, `activo`, `id_empresa`) VALUES
(1, '1', '1', '1', '1', '1', 00001, 1, 1, 1, '1', '1', '1', 0, '0', '1', '1', 0),
(2, '1', '1', '1', '1', '1', 00001, 1, 1, 1, '1', '1', '1', 0, '0', '1', '1', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `id_proyecto` int(4) NOT NULL,
  `nombre` text COLLATE utf8_spanish2_ci NOT NULL,
  `id_empresa` int(4) NOT NULL,
  `fecha_ini` int(8) NOT NULL,
  `fecha_fin` int(8) NOT NULL,
  `fecha_cierre` int(8) NOT NULL,
  `id_cliente` int(9) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `web` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Tipo_coste` int(3) NOT NULL,
  `IBAN` char(24) COLLATE utf8_spanish2_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `importe` decimal(20,4) NOT NULL,
  `margen` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`id_proyecto`, `nombre`, `id_empresa`, `fecha_ini`, `fecha_fin`, `fecha_cierre`, `id_cliente`, `descripcion`, `web`, `Tipo_coste`, `IBAN`, `observaciones`, `importe`, `margen`) VALUES
(1, '', 3, 1, 1, 1, 0, '1', '1', 4, '1', '1', '0.0000', '0.00'),
(2, '', 3, 1012020, 1012021, 1012021, 0, 'fdsfsd', 'sdasd', 3, '21312', 'fdsfsdf', '0.0000', '0.00'),
(3, '', 7, 1012020, 1012021, 1012021, 0, '342423', '434', 2, '34234', '43234', '0.0000', '0.00'),
(4, '', 3, 1012020, 1, 2, 2, '1', '1', 3, '1', '1', '0.0000', '0.00'),
(5, 'Torre Elvira', 8, 20210818, 20210818, 20210818, 12, 'proyecto1', 'dsfsd', 6, '122115', 'cvxcv', '12.1200', '1.00'),
(6, 'Villarroel 162', 8, 11130820, 11130821, 11130821, 12, 'reforma baño de la calle villarroel 162', 'dsfsd', 6, '122115', 'otro', '12.0000', '1.00'),
(10, 'Prueba', 8, 20210105, 20220112, 20220412, 16, 'proyecto 1', '2', 10, '1', '1', '23.0000', '1.00'),
(11, 'Proyecto Juan', 8, 20210105, 20220112, 20220412, 12, 'proyecto 1', '2', 7, '1', '1', '23.0000', '1.00'),
(12, 'proyecto 1', 12, 20210305, 20210307, 20210312, 19, 'erwerw', '1', 9, '2131', '2132', '1212.0000', '12.00'),
(13, '', 8, 20210319, 20210327, 20210319, 16, '14342', '1', 7, '1', '1', '1.0000', '1.00'),
(14, 'Torre Elvira', 8, 20210316, 20210316, 20210327, 12, 'fdssdf', 'f', 6, '1', 'f', '1.0000', '1.00'),
(15, 'Oficina calle Corcega', 12, 20210414, 20220414, 20220414, 19, 'Remodelación de la oficina de Juan', 'no tiene', 9, 'ES9321000100101111111111', 'obra de la oficina', '123000.0000', '12.00'),
(16, 'Proyecto 1', 12, 20210416, 20220122, 20220423, 24, 'Es una obra', 'notiene', 9, 'ES34234234', ' ', '150000.0000', '10.00'),
(17, 'proyecto 2', 12, 20210421, 20210421, 20210421, 24, 'jhkjhkh ', 'f', 9, 'es545345', 'f', '1231231.0000', '12.00'),
(18, 'Jaca', 12, 20210401, 20220423, 20220429, 24, 'Pones puertas', 'no', 9, 'ES98342342', 'no', '1900.0000', '12.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prueba`
--

CREATE TABLE `prueba` (
  `entrada1` int(1) NOT NULL,
  `entrada2` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `prueba`
--

INSERT INTO `prueba` (`entrada1`, `entrada2`) VALUES
(1, 2),
(1, 2),
(2, 2),
(1, 96587),
(1, 965874),
(1222, 965874);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(4) NOT NULL,
  `usuario` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `password` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` char(50) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `email` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `nivelSeguridad` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `usuario`, `password`, `nombre`, `apellidos`, `email`, `nivelSeguridad`) VALUES
(1, 'garmared', 'garmared', 'Edu', 'Garcia Martinez', 'garmared@yahoo.es', 1),
(4, 'juan', 'juan', 'Juan', '3', '3', 3),
(5, 'a', 'b', 'c', 'd', 'e', 2),
(6, 'montse', 'montse', 'Montse', 'Rodriguez Gutierrez', 'notiene@mail.es', 2),
(7, 'admin', 'admin', 'admin', 'admin', 'admin', 1),
(8, 'e', 'e', 'ed', 'ed', 'e', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD UNIQUE KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `conceptos`
--
ALTER TABLE `conceptos`
  ADD PRIMARY KEY (`id_concepto`) USING BTREE;

--
-- Indices de la tabla `constantes`
--
ALTER TABLE `constantes`
  ADD KEY `tiporeg` (`tiporeg`(1024)) USING HASH;

--
-- Indices de la tabla `costes`
--
ALTER TABLE `costes`
  ADD PRIMARY KEY (`id_coste`);

--
-- Indices de la tabla `costesindirectos`
--
ALTER TABLE `costesindirectos`
  ADD PRIMARY KEY (`id_costeInd`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD UNIQUE KEY `id_empresa` (`id_empresa`,`empresa`,`tipo`) USING BTREE;

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD UNIQUE KEY `id_factura` (`id_factura`) USING BTREE;

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`idPago`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD UNIQUE KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD UNIQUE KEY `id_proyecto` (`id_proyecto`) USING BTREE;

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `conceptos`
--
ALTER TABLE `conceptos`
  MODIFY `id_concepto` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `costes`
--
ALTER TABLE `costes`
  MODIFY `id_coste` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `costesindirectos`
--
ALTER TABLE `costesindirectos`
  MODIFY `id_costeInd` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `id_empresa` int(4) NOT NULL AUTO_INCREMENT COMMENT 'este valor sirve para empresa o proveedor, es el identificador', AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `idPago` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id_proyecto` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
