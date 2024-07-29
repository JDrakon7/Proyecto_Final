/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author JDBJ
 */
public class RecuperarContrasena {

    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public RecuperarContrasena() {
        this.conexion = new Conexiondb();
    }

    // Método para enviar el token de recuperación al correo del usuario
    public boolean enviarTokenRecuperacion(String email) {
        String sqlSelect = "SELECT id_usuario FROM tb_usuario WHERE email = ?";
        String sqlInsert = "INSERT INTO tb_recuperacion_contrasena (id_usuario, fecha_creacion, token, expiracion) VALUES (?, ?, ?, ?)";

        try (Connection cx = conexion.Conectar();
             PreparedStatement psSelect = cx.prepareStatement(sqlSelect)) {

            // Verificar que el correo existe
            psSelect.setString(1, email);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");

                    // Generar un token y calcular la fecha de expiración
                    String token = UUID.randomUUID().toString();
                    Timestamp fechaCreacion = new Timestamp(new Date().getTime());
                    Timestamp expiracion = new Timestamp(new Date().getTime() + 3600 * 1000); // 1 hora de validez

                    try (PreparedStatement psInsert = cx.prepareStatement(sqlInsert)) {
                        // Insertar el token en la base de datos
                        psInsert.setInt(1, idUsuario);
                        psInsert.setTimestamp(2, fechaCreacion);
                        psInsert.setString(3, token);
                        psInsert.setTimestamp(4, expiracion);
                        psInsert.executeUpdate();

                        // Enviar el token al correo del usuario (se omite el código de envío de correo por simplicidad)
                        // sendEmail(email, token);  // Implementar el método sendEmail para enviar el correo con el token
                        return true;
                    }
                } else {
                    return false;  // El correo no existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método ficticio para enviar el correo (debe ser implementado)
    private void sendEmail(String email, String token) {
        
    }
}
    

