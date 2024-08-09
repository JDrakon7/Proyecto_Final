/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Categorias;

// Importaciones necesarias para la conexión a la base de datos y manejo de datos JSON y Servlet
import Modelo.Conexiondb;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Anotación para definir la ruta del Servlet
@WebServlet("/ObtenerCategorias")
public class ObtenerCategorias extends HttpServlet { // La clase extiende HttpServlet para manejar solicitudes HTTP
    
    // Atributo para manejar la conexión a la base de datos
    private final Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public ObtenerCategorias() {
        this.conexion = new Conexiondb();
    }

    // Método que maneja las solicitudes HTTP GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración de codificación de caracteres para la solicitud y respuesta
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Lista para almacenar las categorías que se obtendrán de la base de datos
        List<Categoria> categorias = new ArrayList<>();
        
        // Conexión a la base de datos
        Connection cx = conexion.Conectar();
        
        // Consulta SQL para obtener las categorías
        String sql = "SELECT id_categoria, nombre_categoria FROM tb_categorias";

        // Ejecutar la consulta y procesar los resultados
        try (Statement stmt = cx.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            // Mientras haya resultados, se crea un objeto Categoria y se agrega a la lista
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria")); // Asigna el ID de la categoría
                categoria.setNombreCategoria(rs.getString("nombre_categoria")); // Asigna el nombre de la categoría
                categorias.add(categoria); // Agrega la categoría a la lista
            }
        } catch (Exception e) {
            // Captura y maneja cualquier excepción que ocurra durante la ejecución de la consulta
            e.printStackTrace();
        } finally {
            // Cierra la conexión a la base de datos
            conexion.desconectar();
        }

        // Configura el tipo de contenido de la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Preparar para escribir la respuesta al cliente
        PrintWriter out = response.getWriter();
        
        // Convierte la lista de categorías a formato JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(categorias);
        
        // Escribe el JSON en la respuesta
        out.print(json);
        out.flush(); // Asegura que todo el contenido se envíe al cliente
    }
}
