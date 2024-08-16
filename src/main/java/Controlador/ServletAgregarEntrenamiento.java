/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controlador;  // Paquete en el que se encuentra la clase ServletAgregarEntrenamiento

import Modelo.Conexiondb;  // Importa la clase Conexiondb para manejar la conexión a la base de datos
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.sql.Connection;  // Importa la interfaz Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para ejecutar consultas SQL precompiladas
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores relacionados con SQL
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP
import javax.servlet.http.HttpSession;  // Importa la clase HttpSession para manejar la sesión HTTP del usuario

@WebServlet("/AgregarEntrenamiento")  // Define el URL pattern para este servlet
public class ServletAgregarEntrenamiento extends HttpServlet {  // Define la clase ServletAgregarEntrenamiento que extiende HttpServlet
    private Conexiondb conexion;  // Declara una variable para manejar la conexión a la base de datos

    public ServletAgregarEntrenamiento() {  // Constructor del servlet
        this.conexion = new Conexiondb();  // Inicializa la conexión a la base de datos
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes POST
        HttpSession session = request.getSession();  // Obtiene la sesión HTTP del usuario
        Integer idUsuario = (Integer) session.getAttribute("userId");  // Obtiene id_usuario de la sesión
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud
        response.setContentType("text/html; charset=UTF-8");  // Establece el tipo de contenido de la respuesta
        response.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la respuesta

        // Verificar que id_usuario no es nulo
        if (idUsuario == null) {
            response.getWriter().write("Error: No se encontró el id_usuario en la sesión.");  // Envía un mensaje de error si no se encontró id_usuario
            return;  // Sale del método si no se encontró id_usuario
        }

        String pregunta = request.getParameter("pregunta");  // Obtiene la pregunta desde la solicitud
        String respuesta = request.getParameter("respuesta");  // Obtiene la respuesta desde la solicitud
        String idCategoriaStr = request.getParameter("categoria");  // Obtiene id_categoria desde la solicitud

        // Verificar que id_categoria no es nulo
        if (idCategoriaStr == null || idCategoriaStr.isEmpty()) {
            response.getWriter().write("Error: No se proporcionó una categoría válida.");  // Envía un mensaje de error si no se proporcionó id_categoria
            return;  // Sale del método si no se proporcionó id_categoria
        }

        int idCategoria;
        try {
            idCategoria = Integer.parseInt(idCategoriaStr);  // Convierte id_categoria a entero
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: id_categoria no es un número válido.");  // Envía un mensaje de error si id_categoria no es un número válido
            return;  // Sale del método si id_categoria no es un número válido
        }

        Connection cx = conexion.Conectar();  // Establece la conexión con la base de datos

        // Define las consultas SQL para insertar datos
        String sqlPregunta = "INSERT INTO tb_preguntas (pregunta, id_usuario, fecha_pregunta, id_categoria) VALUES (?, ?, CURDATE(), ?)";
        String sqlRespuesta = "INSERT INTO tb_respuestas (id_pregunta, respuesta, fecha_respuesta) VALUES (?, ?, CURDATE())";
        String sqlEntrenamiento = "INSERT INTO tb_entrenamiento (pregunta, respuesta, id_usuario, id_categoria) VALUES (?, ?, ?, ?)";

        try {
            // Insertar la pregunta en la base de datos
            int idPregunta;
            try (PreparedStatement pstmtPregunta = cx.prepareStatement(sqlPregunta, PreparedStatement.RETURN_GENERATED_KEYS)) {  // Prepara la consulta SQL para insertar la pregunta y obtener la clave generada
                pstmtPregunta.setString(1, pregunta);  // Establece el valor para el primer parámetro (pregunta)
                pstmtPregunta.setInt(2, idUsuario);  // Establece el valor para el segundo parámetro (id_usuario)
                pstmtPregunta.setInt(3, idCategoria);  // Establece el valor para el tercer parámetro (id_categoria)
                pstmtPregunta.executeUpdate();  // Ejecuta la actualización en la base de datos

                // Obtiene el ID generado para la pregunta
                try (ResultSet rs = pstmtPregunta.getGeneratedKeys()) {  // Obtiene las claves generadas para la pregunta
                    if (rs.next()) {
                        idPregunta = rs.getInt(1);  // Asigna el ID generado a idPregunta
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la pregunta insertada.");  // Lanza una excepción si no se pudo obtener el ID
                    }
                }
            }

            // Insertar la respuesta usando el idPregunta generado
            int idRespuesta;
            try (PreparedStatement pstmtRespuesta = cx.prepareStatement(sqlRespuesta, PreparedStatement.RETURN_GENERATED_KEYS)) {  // Prepara la consulta SQL para insertar la respuesta y obtener la clave generada
                pstmtRespuesta.setInt(1, idPregunta);  // Establece el valor para el primer parámetro (id_pregunta)
                pstmtRespuesta.setString(2, respuesta);  // Establece el valor para el segundo parámetro (respuesta)
                pstmtRespuesta.executeUpdate();  // Ejecuta la actualización en la base de datos

                // Obtiene el ID generado para la respuesta
                try (ResultSet rs = pstmtRespuesta.getGeneratedKeys()) {  // Obtiene las claves generadas para la respuesta
                    if (rs.next()) {
                        idRespuesta = rs.getInt(1);  // Asigna el ID generado a idRespuesta
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la respuesta insertada.");  // Lanza una excepción si no se pudo obtener el ID
                    }
                }
            }

            // Insertar los datos en la tabla tb_entrenamiento
            try (PreparedStatement pstmtEntrenamiento = cx.prepareStatement(sqlEntrenamiento)) {  // Prepara la consulta SQL para insertar los datos en tb_entrenamiento
                pstmtEntrenamiento.setString(1, pregunta);  // Establece el valor para el primer parámetro (pregunta)
                pstmtEntrenamiento.setString(2, respuesta);  // Establece el valor para el segundo parámetro (respuesta)
                pstmtEntrenamiento.setInt(3, idUsuario);  // Establece el valor para el tercer parámetro (id_usuario)
                pstmtEntrenamiento.setInt(4, idCategoria);  // Establece el valor para el cuarto parámetro (id_categoria)
                pstmtEntrenamiento.executeUpdate();  // Ejecuta la actualización en la base de datos
            }

            // Insertar las palabras clave en la tabla tb_respuestas_palabras_clave
            String palabrasClave = request.getParameter("palabras_clave");  // Obtiene las palabras clave desde la solicitud
            if (palabrasClave != null && !palabrasClave.trim().isEmpty()) {  // Verifica que palabrasClave no sea nulo ni vacío
                String[] palabrasArray = palabrasClave.split(",");  // Divide las palabras clave en un array usando la coma como delimitador
                String sqlBuscarPalabraClave = "SELECT id_palabra_c FROM tb_palabras_clave WHERE palabra_c = ?";  // Consulta SQL para buscar el ID de una palabra clave existente
                String sqlInsertarPalabraClave = "INSERT INTO tb_palabras_clave (palabra_c) VALUES (?)";  // Consulta SQL para insertar una nueva palabra clave
                String sqlRelacionarPalabraClave = "INSERT INTO tb_respuestas_palabras_clave (id_respuesta, id_palabra_c) VALUES (?, ?)";  // Consulta SQL para relacionar una palabra clave con una respuesta
                
                for (String palabra : palabrasArray) {  // Itera sobre cada palabra clave en el array
                    int idPalabraClave;
                    // Buscar si la palabra clave ya existe
                    try (PreparedStatement pstmtBuscarPalabraClave = cx.prepareStatement(sqlBuscarPalabraClave)) {  // Prepara la consulta SQL para buscar la palabra clave
                        pstmtBuscarPalabraClave.setString(1, palabra.trim());  // Establece el valor para el parámetro (palabra_c)
                        try (ResultSet rs = pstmtBuscarPalabraClave.executeQuery()) {  // Ejecuta la consulta y obtiene los resultados
                            if (rs.next()) {
                                idPalabraClave = rs.getInt("id_palabra_c");  // Asigna el ID de la palabra clave existente
                            } else {
                                // Insertar la nueva palabra clave si no existe
                                try (PreparedStatement pstmtInsertarPalabraClave = cx.prepareStatement(sqlInsertarPalabraClave, PreparedStatement.RETURN_GENERATED_KEYS)) {  // Prepara la consulta SQL para insertar la nueva palabra clave y obtener la clave generada
                                    pstmtInsertarPalabraClave.setString(1, palabra.trim());  // Establece el valor para el parámetro (palabra_c)
                                    pstmtInsertarPalabraClave.executeUpdate();  // Ejecuta la actualización en la base de datos
                                    try (ResultSet rsInsert = pstmtInsertarPalabraClave.getGeneratedKeys()) {  // Obtiene las claves generadas para la palabra clave
                                        if (rsInsert.next()) {
                                            idPalabraClave = rsInsert.getInt(1);  // Asigna el ID generado a idPalabraClave
                                        } else {
                                            throw new SQLException("No se pudo obtener el ID de la palabra clave insertada.");  // Lanza una excepción si no se pudo obtener el ID
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    // Insertar la relación en tb_respuestas_palabras_clave
                    try (PreparedStatement pstmtRelacionarPalabraClave = cx.prepareStatement(sqlRelacionarPalabraClave)) {  // Prepara la consulta SQL para insertar la relación entre la respuesta y la palabra clave
                        pstmtRelacionarPalabraClave.setInt(1, idRespuesta);  // Establece el valor para el primer parámetro (id_respuesta)
                        pstmtRelacionarPalabraClave.setInt(2, idPalabraClave);  // Establece el valor para el segundo parámetro (id_palabra_c)
                        pstmtRelacionarPalabraClave.executeUpdate();  // Ejecuta la actualización en la base de datos
                    }
                }
            }

            response.getWriter().write("Pregunta, respuesta y palabras clave agregadas exitosamente."); 
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar datos: " + e.getMessage());  // Envía un mensaje de error si ocurre una excepción SQL
        } finally {
            conexion.desconectar();  // Cierra la conexión a la base de datos en el bloque finally para asegurar que siempre se desconecte
        }
    }
}
