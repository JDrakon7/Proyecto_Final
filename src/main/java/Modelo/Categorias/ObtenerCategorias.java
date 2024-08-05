/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Categorias;

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
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/ObtenerCategorias")
public class ObtenerCategorias extends HttpServlet {
    private final Conexiondb conexion;

    public ObtenerCategorias() {
        this.conexion = new Conexiondb();
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> categorias = new ArrayList<>();
        Connection cx = conexion.Conectar();
        String sql = "SELECT id_categoria, nombre_categoria FROM tb_categorias";

        try (Statement stmt = cx.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(categorias);
        out.print(json);
        out.flush();
    }
}
