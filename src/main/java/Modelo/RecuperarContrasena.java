/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JDBJ
 */
public class RecuperarContrasena {

    private Conexiondb conexion;

    public RecuperarContrasena() {
        this.conexion = new Conexiondb();
    }

// Método para validar si el correo existe en la base de datos tb_usuario
    public boolean validarCorreo(String email) {
        String sqlSelect = "SELECT id_usuario FROM tb_usuario WHERE email = ?";

        try (Connection cx = conexion.Conectar(); PreparedStatement psSelect = cx.prepareStatement(sqlSelect)) {

            psSelect.setString(1, email);
            try (ResultSet rs = psSelect.executeQuery()) {
                return rs.next(); // Retorna true si encuentra un resultado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar la contraseña del usuario en tb_login
    public boolean actualizarContrasena(String email, String nuevaContrasena) {
        String sqlUpdate = "UPDATE tb_login SET password = ? WHERE id_usuario = (SELECT id_usuario FROM tb_usuario WHERE email = ?)";

        try (Connection cx = conexion.Conectar(); PreparedStatement psUpdate = cx.prepareStatement(sqlUpdate)) {

            psUpdate.setString(1, nuevaContrasena);
            psUpdate.setString(2, email);
            return psUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
