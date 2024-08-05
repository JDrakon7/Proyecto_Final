/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class Encriptar {
    
    public static void main(String[] args) {
        Conexiondb conexion = new Conexiondb();
        Connection cx = conexion.Conectar();

        String selectSql = "SELECT id_usuario, password FROM tb_login";
        String updateSql = "UPDATE tb_login SET password = ? WHERE id_usuario = ?";

        try (PreparedStatement selectStmt = cx.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String plainPassword = rs.getString("password");

                // Encriptar la contraseña
                String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

                try (PreparedStatement updateStmt = cx.prepareStatement(updateSql)) {
                    updateStmt.setString(1, hashedPassword);
                    updateStmt.setInt(2, idUsuario);
                    updateStmt.executeUpdate();
                }
            }

            System.out.println("Contraseñas encriptadas exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
  }
        
 }
    
}  
    

