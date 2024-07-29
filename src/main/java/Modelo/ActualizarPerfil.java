/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarPerfil {

    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public ActualizarPerfil() {
        this.conexion = new Conexiondb();
    }

    // Método para actualizar el perfil del usuario
    public boolean actualizarPerfil(int userId, String username, String email, String password) {
        String sqlUpdate = "UPDATE tb_usuario SET nombre = ?, email = ? WHERE id_usuario = ?";
        String sqlUpdatePassword = "UPDATE tb_login SET password = ? WHERE id_usuario = ?";

        try (Connection cx = conexion.Conectar()) {
            // Actualizar nombre y email
            try (PreparedStatement psUpdate = cx.prepareStatement(sqlUpdate)) {
                psUpdate.setString(1, username);
                psUpdate.setString(2, email);
                psUpdate.setInt(3, userId);
                psUpdate.executeUpdate();
            }

            // Actualizar la contraseña si se ha proporcionado
            if (password != null && !password.isEmpty()) {
                try (PreparedStatement psUpdatePassword = cx.prepareStatement(sqlUpdatePassword)) {
                    psUpdatePassword.setString(1, password);
                    psUpdatePassword.setInt(2, userId);
                    psUpdatePassword.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}