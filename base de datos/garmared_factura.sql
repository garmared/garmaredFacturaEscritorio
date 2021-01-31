-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-01-2021 a las 18:38:05
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
  `CP` int(5) NOT NULL,
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
  `id_empresa` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `CIF`, `Nombre`, `Direccion`, `Poblacion`, `Provincia`, `CP`, `Telefono1`, `Telefono2`, `Telefono3`, `Persona_contacto`, `mail`, `web`, `fp`, `dia_pago`, `modo_pago`, `observaciones`, `activo`, `id_empresa`) VALUES
(2, '46350800', 'Edu', 'Aqui', '1', '1', 5258, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'N', 0),
(3, '46350000D', 'Juan Palomo', 'Palomares 12', 'Barcelona', 'Barcelona', 8036, 936585452, 656525252, 658696585, 'Jacinta', 'notiene@correo.es', 'notien.es', '1', 1, '1', 'prueba', 'S', 0),
(8, '1', 'Luis', 'Urgell', 'Barcelona', 'Barcelona', 1231, 324342, 43423, 324234, 'Pedro', 'ddddd', 'j.com', '1', 1, '1', 'sa', 'S', 0),
(10, '1', 'Ejemplo1', '1', '1', '1', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 7),
(12, '1', 'cliente1', 'alli', 'alli', '1', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8),
(13, '1', 'cliente2', 'alli', 'alli', '1', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8),
(14, '1', 'cliente3', 'alli', 'alli', '323', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8),
(15, '1', 'cliente5', 'alli', 'alli', '1', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8),
(16, '1', 'cliente4', 'alli', 'alli', '1', 1, 1, 1, 1, '1', '1', '1', '1', 1, '1', '1', 'S', 8),
(17, '43242', 'eeee', 'ee', 'ee', 'ee', 22, 22, 22, 22, '32dsf', 'dfsfsd', 'fsdfs', '1', 1, '1', 'dsfsdf', 'S', 8);

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
(7, ' Gasolina', 8);

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
(8, 'coste3', 8);

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
(6, 8, 7, 5, 5, '12.00', 'dfsdfs'),
(7, 8, 7, 5, 5, '12.00', 'coste3');

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
  `CP` int(5) NOT NULL,
  `telefono1` int(9) NOT NULL,
  `telefono2` int(9) NOT NULL,
  `telefono3` int(9) NOT NULL,
  `Persona_contacto` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `mail` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `web` char(100) COLLATE utf8_spanish2_ci NOT NULL,
  `IBAN` char(24) COLLATE utf8_spanish2_ci NOT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `activo` char(1) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`id_empresa`, `empresa`, `CIF`, `tipo`, `Nombre`, `Direccion`, `Poblacion`, `Provincia`, `CP`, `telefono1`, `telefono2`, `telefono3`, `Persona_contacto`, `mail`, `web`, `IBAN`, `observaciones`, `activo`) VALUES
(2, 0, '46350000D', 'P', 'Proveedor1', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S'),
(3, 0, '46350800r', 'E', 'dani', '162 Villarroel', 'Barcelona', 'Barcelona', 1, 65733394, 32145678, 1111, 'daniel san', 'danielsan@cobra kai', 'MIYAGI DO KARATE', '1', 'prueba dani', 'S'),
(4, 0, '34324', 'P', 'hjdfhsdkj', 'jhkh', 'hkhk', 'hkhk', 1, 3333, 333, 333, 'khk', 'hkhk', 'khk', '1', '3', 'S'),
(5, 0, '324234', 'E', 'Juan', '43244234', '4324', '42342', 42342, 42342, 423423, 42323, '43242', '423423', '42432', '42432', '4234', 'N'),
(6, 0, '4234234', 'E', 'Empresa1', 'a', 'a', 's', 2, 2, 2, 2, 's', 's', 'd', '1', '1212', 'S'),
(7, 0, '11', 'E', 'Dani Garcia', '255 bolin', 'dubai', 'Emiratos Arabes', 90201, 603418629, 775409432, 546754, 'Edu suldiandr', 'no me lo se', 'no te importa', '5', '5', 'N'),
(8, 0, '2', 'E', '2', '2', '2222', '2', 2, 2, 23412312, 2, '2', '2', '2', '2', '2', 'N'),
(9, 8, '46350000D', 'P', 'Proveedor2', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S'),
(10, 8, '46350000D', 'P', 'Proveedor3', 'Poligono 1', 'Zaragoza', 'Zaragoza', 50011, 97658542, 689545456, 965854545, 'Juan', 'notiene', 'notiene', '1', 'prueba', 'S');

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
(2, 2, 10102020, 10102020, 2, 2, 3, 2, 2, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', ''),
(3, 8, 12122020, 20122020, 5, 12, 7, 6, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', ''),
(4, 8, 12122020, 20122020, 6, 13, 5, 8, 10, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(5, 8, 12122020, 20122020, 6, 14, 5, 8, 10, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', ''),
(6, 8, 12122020, 20122020, 6, 0, 5, 8, 10, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(7, 8, 25122020, 2022021, 6, 16, 5, 7, 9, '2.00', '2.00', '2', '2.0000', '2.00', '2.00', ''),
(8, 8, 20210113, 20220101, 5, 12, 7, 6, 0, '9.00', '9.00', '9', '9.0000', '9.00', '9.00', 'Pagado'),
(9, 8, 20210111, 20220105, 5, 12, 5, 7, 0, '25.00', '1.00', '1', '1.0000', '1.00', '1.00', 'N'),
(10, 8, 20210105, 20210129, 5, 0, 5, 6, 9, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'S'),
(11, 8, 20210112, 20210130, 5, 15, 6, 6, 9, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(12, 8, 20210125, 20210125, 5, 14, 7, 7, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(13, 8, 20210119, 20210120, 7, 0, 5, 7, 9, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono'),
(14, 8, 20210121, 20210129, 5, 16, 5, 7, 0, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Pendiente'),
(15, 8, 20210101, 20210121, 7, 0, 5, 6, 10, '1.00', '1.00', '1', '1.0000', '1.00', '1.00', 'Abono');

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
  `CP` int(5) NOT NULL,
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
(1, '1', '1', '1', '1', '1', 1, 1, 1, 1, '1', '1', '1', 0, '0', '1', '1', 0),
(2, '1', '1', '1', '1', '1', 1, 1, 1, 1, '1', '1', '1', 0, '0', '1', '1', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `id_proyecto` int(4) NOT NULL,
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

INSERT INTO `proyectos` (`id_proyecto`, `id_empresa`, `fecha_ini`, `fecha_fin`, `fecha_cierre`, `id_cliente`, `descripcion`, `web`, `Tipo_coste`, `IBAN`, `observaciones`, `importe`, `margen`) VALUES
(1, 3, 1, 1, 1, 0, '1', '1', 4, '1', '1', '0.0000', '0.00'),
(2, 3, 1012020, 1012021, 1012021, 0, 'fdsfsd', 'sdasd', 3, '21312', 'fdsfsdf', '0.0000', '0.00'),
(3, 7, 1012020, 1012021, 1012021, 0, '342423', '434', 2, '34234', '43234', '0.0000', '0.00'),
(4, 3, 1012020, 1, 2, 2, '1', '1', 3, '1', '1', '0.0000', '0.00'),
(5, 8, 11122020, 11122021, 11122021, 12, 'proyecto1', 'dsfsd', 6, '122115', 'cvxcv', '12.0000', '1.00'),
(6, 8, 11122020, 11122021, 11122021, 13, 'proyecto2', 'dsfsd', 6, '122115', 'cvxcv', '12.0000', '1.00'),
(7, 8, 11130820, 11130821, 11130821, 14, 'proyecto3', 'dsfsd', 6, '122115', 'pro', '12.0000', '1.00'),
(10, 8, 20210105, 20220112, 20220412, 12, '1', '2', 6, '1', '1', '1.0000', '1.00');

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
  ADD UNIQUE KEY `id_empresa` (`id_empresa`) USING BTREE;

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD UNIQUE KEY `id_factura` (`id_factura`) USING BTREE;

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
  MODIFY `id_cliente` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `conceptos`
--
ALTER TABLE `conceptos`
  MODIFY `id_concepto` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `costes`
--
ALTER TABLE `costes`
  MODIFY `id_coste` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `costesindirectos`
--
ALTER TABLE `costesindirectos`
  MODIFY `id_costeInd` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `id_empresa` int(4) NOT NULL AUTO_INCREMENT COMMENT 'este valor sirve para empresa o proveedor, es el identificador', AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id_proyecto` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
