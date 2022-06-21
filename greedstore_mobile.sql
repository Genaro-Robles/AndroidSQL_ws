-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-06-2022 a las 03:06:03
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `greedstore_mobile`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `idcategoria` int(11) NOT NULL,
  `nombre_categoria` varchar(100) NOT NULL,
  `foto_categoria` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idcategoria`, `nombre_categoria`, `foto_categoria`) VALUES
(1, 'Tarjetas de video', 'https://img.pccomponentes.com/pcblog/1621548000000/tarjetanvidia3090.JPG'),
(2, 'Procesadores', 'https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2017/01/216828-que-significan-numeros-letras-procesadores-intel.jpg?itok=YDmejr-F'),
(3, 'Discos duros', 'https://tienda.dnpcorp.pe/761-home_default/disco-duro-ssd-kingston-1920gb-a400-sata3-25.jpg'),
(4, 'Fuentes de poder', 'https://falabella.scene7.com/is/image/FalabellaPE/17991193_1?wid=800&hei=800&qlt=70');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idproducto` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `idcategoria` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `imagen` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idproducto`, `nombre`, `descripcion`, `idcategoria`, `stock`, `precio`, `imagen`) VALUES
(1, 'RTX 3090 NVIDIA', 'Tarjeta de video de ultima generación', 1, 48, '10632', 'https://www.notebookcheck.org/fileadmin/Notebooks/News/_nc3/rtx_3090_super_ga102.png'),
(2, 'RTX 2060 NVIDIA', 'Tarjeta de video de gama media perfecta para 1080p', 1, 53, '4346', 'https://images.versus.io/objects/nvidia-geforce-rtx-2060.front.variety.1576684641045.jpg'),
(3, 'COREI 9 12900K', 'Procesador de gama alta adaptado para overclock', 2, 60, '5346', 'https://m.media-amazon.com/images/I/61RnzuOIOoL._AC_SS450_.jpg'),
(4, 'SSD 580 gb kingston', 'Disco duro de 580GB de Kingston', 3, 123, '282', 'https://media.kingston.com/kingston/product/ktc-product-ssd-a400-sa400s37-480gb-3-zm-lg.jpg'),
(5, 'Corsair CV550', 'Fuente de poder Cosair de 550W', 4, 58, '356', 'https://www.corsair.com/medias/sys_master/images/images/h2d/he6/9463839621150/-CP-9020210-NA-Gallery-CV550-PSU-01.png');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idcategoria`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idproducto`),
  ADD KEY `idcategoria` (`idcategoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `idcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categorias` (`idcategoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
