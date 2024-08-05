/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActualizarPerfil {
    private Conexiondb conexion;

    public ActualizarPerfil() {
        this.conexion = new Conexiondb();
    }

    public boolean ActualizarPerfil(int userId, String username, String email, String password) {
        boolean isUpdated = false;
        Connection cx = conexion.Conectar();
        String checkEmailSql = "SELECT id_usuario FROM tb_usuario WHERE email = ? AND id_usuario != ?";
        String updateSql = "UPDATE tb_usuario SET nombre = ?, email = ?, password = ? WHERE id_usuario = ?";

        try (PreparedStatement checkStmt = cx.prepareStatement(checkEmailSql);
             PreparedStatement updateStmt = cx.prepareStatement(updateSql)) {
            
            // Verificar si el correo ya está en uso por otro usuario
            checkStmt.setString(1, email);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false; // El correo ya está en uso por otro usuario
            }

            // Actualizar el perfil del usuario
            updateStmt.setString(1, username);
            updateStmt.setString(2, email);
            updateStmt.setString(3, password);
            updateStmt.setInt(4, userId);
            isUpdated = updateStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
        return isUpdated;
    }

}