/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Conexiondb;  // Importa la clase Conexiondb para manejar la conexión a la base de datos
import Modelo.Entrenamiento.Entrenamiento;  // Importa la clase Entrenamiento que representa los datos de un entrenamiento
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.sql.Connection;  // Importa la interfaz Connection para manejar la conexión a la base de datos
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar el conjunto de resultados de una consulta SQL
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores relacionados con SQL
import java.sql.Statement;  // Importa la clase Statement para ejecutar consultas SQL
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP
import java.util.ArrayList;  // Importa la clase ArrayList para manejar listas de objetos
import java.util.List;  // Importa la interfaz List para manejar listas de objetos
import com.google.gson.Gson;  // Importa la clase Gson para convertir objetos Java a JSON

@WebServlet("/LeerEntrenamiento")  // Define el URL pattern para este servlet
public class ServletLeerEntrenamiento extends HttpServlet {  // Define la clase ServletLeerEntrenamiento que extiende HttpServlet

    private Conexiondb conexion;  // Declara una variable para manejar la conexión a la base de datos

    public ServletLeerEntrenamiento() {  // Constructor del servlet
        this.conexion = new Conexiondb();  // Inicializa la conexión a la base de datos
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes GET
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud
        response.setContentType("application/json");  // Establece el tipo de contenido de la respuesta como JSON
        response.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la respuesta

        List<Entrenamiento> entrenamientos = new ArrayList<>();  // Crea una lista para almacenar los objetos de entrenamiento
        Connection cx = conexion.Conectar();  // Establece la conexión con la base de datos

        String sqlEntrenamiento = "SELECT * FROM tb_entrenamiento";  // Define la consulta SQL para obtener todos los entrenamientos
        String sqlCategoria = "SELECT nombre_categoria FROM tb_categorias WHERE id_categoria = ";  // Define la consulta SQL para obtener el nombre de la categoría

        try (Statement stmtEntrenamiento = cx.createStatement();  // Crea un Statement para ejecutar la consulta de entrenamientos
             ResultSet rsEntrenamiento = stmtEntrenamiento.executeQuery(sqlEntrenamiento)) {  // Ejecuta la consulta y obtiene el conjunto de resultados
            while (rsEntrenamiento.next()) {  // Itera sobre cada registro en el conjunto de resultados
                Entrenamiento entrenamiento = new Entrenamiento();  // Crea un nuevo objeto Entrenamiento
                entrenamiento.setIdEntrenamiento(rsEntrenamiento.getInt("id_entrenamiento"));  // Establece el ID del entrenamiento
                entrenamiento.setPregunta(rsEntrenamiento.getString("pregunta"));  // Establece la pregunta
                entrenamiento.setRespuesta(rsEntrenamiento.getString("respuesta"));  // Establece la respuesta
                entrenamiento.setIdCategoria(rsEntrenamiento.getInt("id_categoria"));  // Establece el ID de la categoría
                entrenamiento.setIdUsuario(rsEntrenamiento.getInt("id_usuario"));  // Establece el ID del usuario
                entrenamiento.setFechaActualizacion(rsEntrenamiento.getTimestamp("fecha_actualizacion"));  // Establece la fecha de actualización

                // Obtener el nombre de la categoría
                try (Statement stmtCategoria = cx.createStatement();  // Crea un Statement para ejecutar la consulta de categorías
                     ResultSet rsCategoria = stmtCategoria.executeQuery(sqlCategoria + entrenamiento.getIdCategoria())) {  // Ejecuta la consulta y obtiene el conjunto de resultados
                    if (rsCategoria.next()) {  // Verifica si se obtuvo un resultado
                        entrenamiento.setNombreCategoria(rsCategoria.getString("nombre_categoria"));  // Establece el nombre de la categoría
                    } else {
                        entrenamiento.setNombreCategoria("Categoría desconocida");  // Establece un nombre de categoría predeterminado si no se encontró el ID
                    }
                }
                entrenamientos.add(entrenamiento);  // Agrega el objeto Entrenamiento a la lista
            }
        } catch (SQLException e) {
            response.getWriter().write("Error al leer entrenamiento: " + e.getMessage());  // Envía un mensaje de error si ocurre una excepción SQL
        } finally {
            conexion.desconectar();  // Cierra la conexión a la base de datos en el bloque finally para asegurar que siempre se desconecte
        }

        // Convertir la lista de entrenamientos a JSON y enviarla en la respuesta
        response.getWriter().write(new Gson().toJson(entrenamientos));  // Convierte la lista de entrenamientos a JSON y la envía como respuesta
    }
}
