-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-08-2024 a las 19:01:17
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
(65, 'POO'),
(66, 'Librerias'),
(67, 'Chatbots'),
(68, 'Interaccion');

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
(1, 'Hola', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python', 68, 1, '2024-08-08 22:09:39'),
(3, '¿Cuál es la diferencia entre un bucle for y un bucle while en Python?', 'Un bucle for en Python se utiliza para iterar sobre una secuencia de elementos, como una lista o una cadena de texto, mientras que un bucle while se ejecuta mientras una condicion específica sea verdadera', 1, 1, '2024-08-07 21:09:43'),
(6, '¿Qué es un bucle en Python y cómo se utiliza?', 'Un bucle en Python es una estructura de control que permite repetir un bloque de código varias veces mientras se cumple una condición específica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condición se vuelva falsa.', 1, 1, '2024-07-29 15:57:07'),
(7, '¿Qué es un condicional \'if-else\' en Python y cómo se utiliza?', 'Un condicional \'if-else\' en Python es una estructura que permite ejecutar un bloque de código si se cumple una condición y otro bloque de código si la condición no se cumple. Se utiliza para tomar decisiones en base a condiciones.', 1, 1, '2024-07-31 15:41:25'),
(15, '¿Cómo se realiza la entrada y salida de datos en Python', 'En Python, la entrada de datos se realiza utilizando la función \'input\' para obtener datos del usuario, y la salida de datos se realiza utilizando la función \'print\' para mostrar resultados en la consola.\"', 1, 1, '2024-08-07 09:21:21'),
(32, '¿Qué es una función en Python como se utiliza?', 'Una función en Python es un bloque de código que realiza una tarea específica. Su propósito es organizar y reutilizar el código, mejorando la modularidad y la legibilidad del programa. Se define utilizando la palabra clave def.', 1, 1, '2024-08-08 17:02:05'),
(33, '¿Qué es la recursividad en Python y cómo se implementa?', 'La recursividad en Python es cuando una función se llama a sí misma para resolver un problema de manera iterativa. Se implementa definiendo un caso base y un caso recursivo en la función.', 1, 1, '2024-08-08 18:24:55'),
(34, '¿Cuál es la diferencia entre una clase y una función en Python?', 'Una clase en Python es una plantilla que define las propiedades y comportamientos de un objeto, mientras que una función es un bloque de código que realiza una tarea específica. Las clases se definen con la palabra clave (class\' y las funciones con \'def)', 1, 1, '2024-08-08 18:40:09'),
(36, '¿Qué son las excepciones en Python y cómo se manejan?', 'Las excepciones en Python son eventos que ocurren durante la ejecución de un programa y pueden interrumpir su flujo normal. Se manejan utilizando bloques (try) y (except) para atrapar y manejar excepciones.', 1, 1, '2024-08-08 18:59:17'),
(37, '¿Qué función cumple `get_close_matches` en la biblioteca `difflib`?', 'get_close_matches en difflib encuentra las coincidencias más cercanas entre una cadena de texto dada y una lista de cadenas. Permite ajustar el nivel de similitud con el parámetro cutoff para determinar qué tan cercana debe ser una coincidencia', 1, 1, '2024-08-08 19:18:13'),
(38, 'Qué es `SequenceMatcher` en `difflib` y cómo se usa', 'SequenceMatcher en difflib es una clase que se utiliza para comparar pares de secuencias. Permite medir la similitud entre dos cadenas y encontrar la mejor coincidencia. Se usa creando una instancia de SequenceMatcher con dos secuencias y llamando al método ratio() para obtener un valor de similitud.', 1, 1, '2024-08-08 19:29:07'),
(39, 'Qué es `SequenceMatcher` en `difflib` y cómo se usa', 'SequenceMatcher en difflib es una clase que se utiliza para comparar pares de secuencias. Permite medir la similitud entre dos cadenas y encontrar la mejor coincidencia. Se usa creando una instancia de SequenceMatcher con dos secuencias y llamando al método ratio() para obtener un valor de similitud.', 1, 1, '2024-08-08 19:33:27'),
(40, 'Que es flask', 'Flask es un marco de trabajo para crear aplicaciones web en Python. Es útil porque es sencillo y flexible, permitiendo a los desarrolladores construir aplicaciones web de forma rápida y fácil, con solo las características esenciales que necesitan', 2, 1, '2024-08-21 04:04:56'),
(42, '¿Qué es un bucle en Python y cómo se utiliza?', 'prueba', 1, 1, '2024-08-21 05:18:10');

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
(2, '2024-07-06', '10:32:02', 1, 'Que es un bucle en python?', 'Un bucle en Python es una estructura de control que permite repetir un bloque de código varias veces mientras se cumple una condición especifica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condición se vuelva falsa.'),
(8, '2024-08-08', '00:59:44', 1, 'Hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(9, '2024-08-08', '01:02:27', 1, 'Cual es la diferencia entre un bucle for y while?', 'Un bucle for en Python se utiliza para iterar sobre una secuencia de elementos, como una lista o una cadena de texto, mientras que un bucle while se ejecuta mientras una condicion específica sea verdadera'),
(11, '2024-08-08', '03:22:31', 4, 'entrada y salida de datos en python', 'En Python, la entrada de datos se realiza utilizando la función \'input\' para obtener datos del usuario, y la salida de datos se realiza utilizando la función \'print\' para mostrar resultados en la consola.\"'),
(15, '2024-08-08', '03:38:24', 4, 'hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(16, '2024-08-08', '11:46:59', 1, 'que es una funcion en python', 'Una función en Python es un bloque de código que realiza una tarea específica. Su propósito es organizar y reutilizar el código, mejorando la modularidad y la legibilidad del programa. Se define utilizando la palabra clave (def).'),
(17, '2024-08-08', '13:25:06', 1, 'que es la recursividad en python', 'La recursividad en Python es cuando una función se llama a sí misma para resolver un problema de manera iterativa. Se implementa definiendo un caso base y un caso recursivo en la función.'),
(18, '2024-08-08', '16:35:13', 24, 'Hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(19, '2024-08-08', '16:35:20', 24, 'Que es un bucle en python', 'Un bucle en Python es una estructura de control que permite repetir un bloque de código varias veces mientras se cumple una condición específica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condición se vuelva falsa.'),
(20, '2024-08-08', '16:35:25', 24, 'Diferencia entre for y while', 'Lo lamento en este momento no puedo responder a tu pregunta'),
(21, '2024-08-08', '16:35:36', 24, 'Cual es la diferencia de entre  for y while', 'Un bucle for en Python se utiliza para iterar sobre una secuencia de elementos, como una lista o una cadena de texto, mientras que un bucle while se ejecuta mientras una condicion específica sea verdadera'),
(22, '2024-08-08', '16:36:10', 24, 'Que es flask', 'Flask es un marco de trabajo para crear aplicaciones web en Python. Es útil porque es sencillo y flexible, permitiendo a los desarrolladores construir aplicaciones web de forma rápida y fácil, con solo las características esenciales que necesitan'),
(23, '2024-08-08', '16:37:35', 24, 'Cual es la diferencia entre for y while', 'Un bucle for en Python se utiliza para iterar sobre una secuencia de elementos, como una lista o una cadena de texto, mientras que un bucle while se ejecuta mientras una condicion específica sea verdadera'),
(24, '2024-08-08', '16:49:54', 24, 'Hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(25, '2024-08-08', '16:50:31', 24, 'Hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(26, '2024-08-08', '19:22:50', 25, 'Hola chat', 'Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python'),
(27, '2024-08-08', '19:22:55', 25, 'Que es un bucle', 'Lo lamento en este momento no puedo responder a tu pregunta'),
(28, '2024-08-08', '19:23:04', 25, 'Que es un bucle en python', 'Un bucle en Python es una estructura de control que permite repetir un bloque de código varias veces mientras se cumple una condición específica. Se puede utilizar el bucle \'for\' para iterar sobre una secuencia o el bucle \'while\' para repetir hasta que una condición se vuelva falsa.');

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
(2, 4, 'juanb@gmail.com', 'juan03'),
(3, 6, 'draco@gmail.com', 'draco03'),
(5, 8, 'david@gmail.com', 'david03'),
(21, 24, 'enzy@gmail.com', 'martin03'),
(22, 25, 'lalis@gmail.com', '123');

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
(2, 'Salida de datos'),
(23, 'listas'),
(24, 'declarar listas'),
(25, 'funciones'),
(26, 'recursividad'),
(27, 'diferencia'),
(28, 'clase'),
(29, 'funcion'),
(30, 'excepciones'),
(31, 'get_close_matches'),
(32, 'SequenceMatcher'),
(33, 'Flask'),
(34, 'cutoff'),
(35, 'funcion cutoff'),
(36, 'prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_preguntas`
--

CREATE TABLE `tb_preguntas` (
  `id_pregunta` int(11) NOT NULL,
  `pregunta` text NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_pregunta` date NOT NULL,
  `id_categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_preguntas`
--

INSERT INTO `tb_preguntas` (`id_pregunta`, `pregunta`, `id_usuario`, `fecha_pregunta`, `id_categoria`) VALUES
(1, '¿Cómo se realiza la entrada y salida de datos en Python?', 1, '2024-07-26', 1),
(3, '¿Qué es una lista en Python y cómo se declara?', 1, '2024-08-07', 1),
(12, '¿Qué es una función en Python como se utiliza?', 1, '2024-08-08', 1),
(13, '¿Qué es la recursividad en Python y cómo se implementa?', 1, '2024-08-08', 1),
(14, '¿Cuál es la diferencia entre una clase y una función en Python?', 1, '2024-08-08', 1),
(16, '¿Qué son las excepciones en Python y cómo se manejan?', 1, '2024-08-08', 1),
(17, '¿Qué función cumple `get_close_matches` en la biblioteca `difflib`?', 1, '2024-08-08', 1),
(18, 'Qué es `SequenceMatcher` en `difflib` y cómo se usa', 1, '2024-08-08', 1),
(20, 'Que es flask', 1, '2024-08-08', 66),
(21, '¿Por qué es importante ajustar el parámetro `cutoff` en la función `get_close_matches`?', 24, '2024-08-08', 67),
(22, '¿Qué es un bucle en Python y cómo se utiliza?', 1, '2024-08-21', 1),
(23, '', 1, '2024-08-21', 1);

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
(4, 3, 'Una lista en Python es una estructura de datos que permite almacenar múltiples valores en una sola variable. Se declara utilizando corchetes y separando los elementos por comas.', '2024-08-07', NULL),
(13, 12, 'Una función en Python es un bloque de código que realiza una tarea específica. Su propósito es organizar y reutilizar el código, mejorando la modularidad y la legibilidad del programa. Se define utilizando la palabra clave (def).', '2024-08-08', NULL),
(14, 13, 'La recursividad en Python es cuando una función se llama a sí misma para resolver un problema de manera iterativa. Se implementa definiendo un caso base y un caso recursivo en la función.', '2024-08-08', NULL),
(15, 14, 'Una clase en Python es una plantilla que define las propiedades y comportamientos de un objeto, mientras que una función es un bloque de código que realiza una tarea específica. Las clases se definen con la palabra clave (class\' y las funciones con \'def)', '2024-08-08', NULL),
(17, 16, 'Las excepciones en Python son eventos que ocurren durante la ejecución de un programa y pueden interrumpir su flujo normal. Se manejan utilizando bloques (try) y (except) para atrapar y manejar excepciones.', '2024-08-08', NULL),
(18, 17, 'get_close_matches en difflib encuentra las coincidencias más cercanas entre una cadena de texto dada y una lista de cadenas. Permite ajustar el nivel de similitud con el parámetro cutoff para determinar qué tan cercana debe ser una coincidencia', '2024-08-08', NULL),
(19, 18, 'SequenceMatcher en difflib es una clase que se utiliza para comparar pares de secuencias. Permite medir la similitud entre dos cadenas y encontrar la mejor coincidencia. Se usa creando una instancia de SequenceMatcher con dos secuencias y llamando al método ratio() para obtener un valor de similitud.', '2024-08-08', NULL),
(21, 20, 'Flask es un marco de trabajo para crear aplicaciones web en Python. Es útil porque es sencillo y flexible, permitiendo a los desarrolladores construir aplicaciones web de forma rápida y fácil, con solo las características esenciales que necesitan', '2024-08-08', NULL),
(22, 21, 'El parámetro cutoff ajusta qué tan similares deben ser las cadenas para ser consideradas coincidencias. Es importante para controlar la precisión de las coincidencias y evitar resultados que no sean relevantes. Un cutoff más alto requiere coincidencias más precisas.', '2024-08-08', NULL),
(23, 22, 'prueba', '2024-08-21', NULL),
(24, 23, '', '2024-08-21', NULL);

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
(13, 25),
(14, 26),
(15, 27),
(15, 28),
(15, 29),
(17, 30),
(18, 31),
(19, 32),
(21, 33),
(22, 34),
(22, 35),
(23, 36);

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
(3, 'superadmin'),
(4, 'Inhabilitado');

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
(1, 'Juan Super Admin', 'juandavid219@outlook.com', '2024-07-02', 3),
(4, 'Juan Barrera', 'juanb@gmail.com', '2024-07-09', 4),
(6, 'Draco', 'draco@gmail.com', '2024-07-28', 2),
(8, 'David10', 'david@gmail.com', '2024-07-28', 4),
(10, 'Stivenx', 'stiven@gmail.com', '2024-08-01', 1),
(24, 'Enzy10', 'enzy@gmail.com', '2024-08-08', 1),
(25, 'Lalischarlis', 'lalis@gmail.com', '2024-08-08', 1);

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
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT de la tabla `tb_entrenamiento`
--
ALTER TABLE `tb_entrenamiento`
  MODIFY `id_entrenamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `tb_historial`
--
ALTER TABLE `tb_historial`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `tb_login`
--
ALTER TABLE `tb_login`
  MODIFY `id_login` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `tb_palabras_clave`
--
ALTER TABLE `tb_palabras_clave`
  MODIFY `id_palabra_c` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `tb_preguntas`
--
ALTER TABLE `tb_preguntas`
  MODIFY `id_pregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `tb_respuestas`
--
ALTER TABLE `tb_respuestas`
  MODIFY `id_respuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `tb_roles`
--
ALTER TABLE `tb_roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

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
