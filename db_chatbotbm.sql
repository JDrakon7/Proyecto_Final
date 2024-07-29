-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2024 a las 18:19:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_chatbotbm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_categorias`
--

CREATE TABLE `tb_categorias` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_categorias`
--

INSERT INTO `tb_categorias` (`id_categoria`, `nombre_categoria`) VALUES
(1, 'Logica'),
(2, 'Ejemplos'),
(3, 'Codigo ejemplos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_entrenamiento`
--

CREATE TABLE `tb_entrenamiento` (
  `id_entrenamiento` int(11) NOT NULL,
  `pregunta` varchar(255) NOT NULL,
  `respuesta` text NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_entrenamiento`
--

INSERT INTO `tb_entrenamiento` (`id_entrenamiento`, `pregunta`, `respuesta`, `id_categoria`, `id_usuario`, `fecha_actualizacion`) VALUES
(1, 'Hola', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python', NULL, 1, '2024-07-25 13:23:01'),
(2, 'Como estas', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python', NULL, 1, '2024-07-25 13:23:01'),
(3, '¿Cuál es la diferencia entre un bucle \'for\' y un bucle \'while\' en Python?', 'Un bucle \'for\' en Python se utiliza para iterar sobre una secuencia de elementos, como una lista o una cadena de texto, mientras que un bucle \'while\' se ejecuta mientras una condiciÃ³n especÃ­fica sea verdadera', NULL, 1, '2024-07-25 13:23:01'),
(6, '¿Qué es un bucle en Python y cómo se utiliza?', 'Un bucle en Python es una estructura de control que permite repetir un bloque de código varias veces mientras se cumple una condición específica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condición se vuelva falsa.', 1, 1, '2024-07-29 15:57:07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_historial`
--

CREATE TABLE `tb_historial` (
  `id_historial` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `pregunta` text NOT NULL,
  `respuesta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_historial`
--

INSERT INTO `tb_historial` (`id_historial`, `fecha`, `hora`, `id_usuario`, `pregunta`, `respuesta`) VALUES
(1, '2024-07-06', '10:29:16', 1, 'Hola chat', 'Hola soy Botmaster , mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(2, '2024-07-06', '10:32:02', 1, 'Que es un bucle en python?', 'Un bucle en Python es una estructura de control que permite repetir un bloque de cÃ³digo varias veces mientras se cumple una condiciÃ³n especÃ­fica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condiciÃ³n se vuelva falsa.'),
(3, '2024-07-19', '10:24:53', 1, 'Hola', 'Hola soy Botmaster , mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(4, '2024-07-19', '10:25:09', 1, 'que es un bucle en python', 'Un bucle en Python es una estructura de control que permite repetir un bloque de cÃ³digo varias veces mientras se cumple una condiciÃ³n especÃ­fica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condiciÃ³n se vuelva falsa.'),
(5, '2024-07-19', '10:37:59', 1, 'Holis', 'Hola soy Botmaster , mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(6, '2024-07-19', '10:38:08', 1, 'Que es un bucle en python', 'Un bucle en Python es una estructura de control que permite repetir un bloque de cÃ³digo varias veces mientras se cumple una condiciÃ³n especÃ­fica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condiciÃ³n se vuelva falsa.'),
(7, '2024-07-21', '23:14:51', 1, 'Hola', 'Hola soy Botmaster , mi funcion principal es brindar orientacion sobre la logica de programacion en Python');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_login`
--

CREATE TABLE `tb_login` (
  `id_login` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_login`
--

INSERT INTO `tb_login` (`id_login`, `id_usuario`, `email`, `password`) VALUES
(1, 1, 'juandavid219@outlook.com', 'martin03'),
(2, 4, 'juanb@gmail.com', 'martin03'),
(3, 6, 'draco@gmail.com', 'martin03'),
(4, 7, 'david30@gmail.com', 'martin03'),
(5, 8, 'david@gmail.com', 'martin03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_palabras_clave`
--

CREATE TABLE `tb_palabras_clave` (
  `id_palabra_c` int(11) NOT NULL,
  `palabra_c` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_palabras_clave`
--

INSERT INTO `tb_palabras_clave` (`id_palabra_c`, `palabra_c`) VALUES
(1, 'Entrada de datos'),
(2, 'Salida de datos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_preguntas`
--

CREATE TABLE `tb_preguntas` (
  `id_pregunta` int(11) NOT NULL,
  `pregunta` text NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_pregunta` date NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `estado` varchar(50) DEFAULT 'pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_preguntas`
--

INSERT INTO `tb_preguntas` (`id_pregunta`, `pregunta`, `id_usuario`, `fecha_pregunta`, `id_categoria`, `estado`) VALUES
(1, '¿Cómo se realiza la entrada y salida de datos en Python?', 1, '2024-07-26', 1, 'pendiente'),
(2, '¿Cómo se realiza la entrada y salida de datos en Python?', 1, '2024-07-26', 1, 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_recuperacion_contrasena`
--

CREATE TABLE `tb_recuperacion_contrasena` (
  `id_recuperacion` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `token` varchar(100) NOT NULL,
  `expiracion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_respuestas`
--

CREATE TABLE `tb_respuestas` (
  `id_respuesta` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `respuesta` text NOT NULL,
  `fecha_respuesta` date NOT NULL,
  `fecha_actualización` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_respuestas`
--

INSERT INTO `tb_respuestas` (`id_respuesta`, `id_pregunta`, `respuesta`, `fecha_respuesta`, `fecha_actualización`) VALUES
(2, 1, 'En Python, la entrada de datos se realiza utilizando la función \'input\' para obtener datos del usuario, y la salida de datos se realiza utilizando la función \'print\' para mostrar resultados en la consola.', '2024-07-26', NULL),
(3, 2, 'En Python, la entrada de datos se realiza utilizando la función \'input\' para obtener datos del usuario, y la salida de datos se realiza utilizando la función \'print\' para mostrar resultados en la consola.', '2024-07-26', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_respuestas_palabras_clave`
--

CREATE TABLE `tb_respuestas_palabras_clave` (
  `id_respuesta` int(11) NOT NULL,
  `id_palabra_c` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_respuestas_palabras_clave`
--

INSERT INTO `tb_respuestas_palabras_clave` (`id_respuesta`, `id_palabra_c`) VALUES
(2, 1),
(2, 2),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_roles`
--

CREATE TABLE `tb_roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_roles`
--

INSERT INTO `tb_roles` (`id_rol`, `nombre_rol`) VALUES
(1, 'usuario'),
(2, 'admin'),
(3, 'superadmin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fecha_registro` date NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`id_usuario`, `nombre`, `email`, `fecha_registro`, `id_rol`) VALUES
(1, 'Juan Admin', 'jdbarrera90@soy.sena.edu.co', '2024-07-02', 3),
(4, 'Juan Barrera', 'juanb@gmail.com', '2024-07-09', 1),
(6, 'Draco', 'draco@gmail.com', '2024-07-28', 1),
(7, 'David30', 'david30@gmail.com', '2024-07-28', 1),
(8, 'David10', 'david@gmail.com', '2024-07-28', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_categorias`
--
ALTER TABLE `tb_categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `tb_entrenamiento`
--
ALTER TABLE `tb_entrenamiento`
  ADD PRIMARY KEY (`id_entrenamiento`),
  ADD KEY `fk_entrenamiento_usuario` (`id_usuario`),
  ADD KEY `fk_categoria` (`id_categoria`);

--
-- Indices de la tabla `tb_historial`
--
ALTER TABLE `tb_historial`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `tb_login`
--
ALTER TABLE `tb_login`
  ADD PRIMARY KEY (`id_login`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `tb_palabras_clave`
--
ALTER TABLE `tb_palabras_clave`
  ADD PRIMARY KEY (`id_palabra_c`);

--
-- Indices de la tabla `tb_preguntas`
--
ALTER TABLE `tb_preguntas`
  ADD PRIMARY KEY (`id_pregunta`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `tb_recuperacion_contrasena`
--
ALTER TABLE `tb_recuperacion_contrasena`
  ADD PRIMARY KEY (`id_recuperacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `tb_respuestas`
--
ALTER TABLE `tb_respuestas`
  ADD PRIMARY KEY (`id_respuesta`),
  ADD KEY `id_pregunta` (`id_pregunta`);

--
-- Indices de la tabla `tb_respuestas_palabras_clave`
--
ALTER TABLE `tb_respuestas_palabras_clave`
  ADD PRIMARY KEY (`id_respuesta`,`id_palabra_c`),
  ADD KEY `fk_palabra` (`id_palabra_c`);

--
-- Indices de la tabla `tb_roles`
--
ALTER TABLE `tb_roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `fk_usuario_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_categorias`
--
ALTER TABLE `tb_categorias`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `tb_entrenamiento`
--
ALTER TABLE `tb_entrenamiento`
  MODIFY `id_entrenamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tb_historial`
--
ALTER TABLE `tb_historial`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tb_login`
--
ALTER TABLE `tb_login`
  MODIFY `id_login` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tb_palabras_clave`
--
ALTER TABLE `tb_palabras_clave`
  MODIFY `id_palabra_c` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_preguntas`
--
ALTER TABLE `tb_preguntas`
  MODIFY `id_pregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_recuperacion_contrasena`
--
ALTER TABLE `tb_recuperacion_contrasena`
  MODIFY `id_recuperacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_respuestas`
--
ALTER TABLE `tb_respuestas`
  MODIFY `id_respuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_roles`
--
ALTER TABLE `tb_roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_entrenamiento`
--
ALTER TABLE `tb_entrenamiento`
  ADD CONSTRAINT `fk_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categorias` (`id_categoria`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_entrenamiento_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`);

--
-- Filtros para la tabla `tb_historial`
--
ALTER TABLE `tb_historial`
  ADD CONSTRAINT `tb_historial_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`);

--
-- Filtros para la tabla `tb_login`
--
ALTER TABLE `tb_login`
  ADD CONSTRAINT `tb_login_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`);

--
-- Filtros para la tabla `tb_preguntas`
--
ALTER TABLE `tb_preguntas`
  ADD CONSTRAINT `tb_preguntas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  ADD CONSTRAINT `tb_preguntas_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categorias` (`id_categoria`);

--
-- Filtros para la tabla `tb_recuperacion_contrasena`
--
ALTER TABLE `tb_recuperacion_contrasena`
  ADD CONSTRAINT `tb_recuperacion_contrasena_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`);

--
-- Filtros para la tabla `tb_respuestas`
--
ALTER TABLE `tb_respuestas`
  ADD CONSTRAINT `tb_respuestas_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `tb_preguntas` (`id_pregunta`);

--
-- Filtros para la tabla `tb_respuestas_palabras_clave`
--
ALTER TABLE `tb_respuestas_palabras_clave`
  ADD CONSTRAINT `fk_palabra` FOREIGN KEY (`id_palabra_c`) REFERENCES `tb_palabras_clave` (`id_palabra_c`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_respuesta` FOREIGN KEY (`id_respuesta`) REFERENCES `tb_respuestas` (`id_respuesta`) ON DELETE CASCADE,
  ADD CONSTRAINT `tb_respuestas_palabras_clave_ibfk_1` FOREIGN KEY (`id_respuesta`) REFERENCES `tb_respuestas` (`id_respuesta`),
  ADD CONSTRAINT `tb_respuestas_palabras_clave_ibfk_2` FOREIGN KEY (`id_palabra_c`) REFERENCES `tb_palabras_clave` (`id_palabra_c`);

--
-- Filtros para la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `tb_roles` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
