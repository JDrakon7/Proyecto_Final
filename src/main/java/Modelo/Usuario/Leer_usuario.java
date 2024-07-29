package Modelo.Usuario;

import Modelo.Conexiondb;  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_usuario {

    private Conexiondb conexion;

    // Constructor que inicializa la conexión
    public Leer_usuario() {
        this.conexion = new Conexiondb();
    }

    public void leer_Usuario(int id) {
        String sql = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
        try (Connection cx = conexion.Conectar(); 
             PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_usuario"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Fecha de Registro: " + rs.getString("fecha_registro"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Leer_usuario leerUsuario = new Leer_usuario();
        leerUsuario.leer_Usuario(1); // Identificar el usuario que se desea leer
    }
}
