/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperarContrasena {

    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public RecuperarContrasena() {
        this.conexion = new Conexiondb();
    }

    // Método para validar si el correo existe en la base de datos
    public boolean validarCorreo(String email) {
        String sql = "SELECT id_usuario FROM tb_usuario WHERE email = ?";
        try (Connection cx = conexion.Conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar la contraseña del usuario
    public boolean actualizarContrasena(String email, String nuevaContrasena) {
        String sql = "UPDATE tb_usuario SET password = ? WHERE email = ?";
        try (Connection cx = conexion.Conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, nuevaContrasena);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
