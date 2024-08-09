/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import Modelo.Conexiondb;  // Importa la clase Conexiondb que maneja la conexión a la base de datos
import java.sql.Connection;  // Importa la clase Connection para manejar conexiones a la base de datos
import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para ejecutar consultas SQL precompiladas
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar los resultados de una consulta SQL
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores SQL
import org.mindrot.jbcrypt.BCrypt;  // Importa la biblioteca BCrypt para encriptar contraseñas (aunque no se usa en el código actual)

public class RecuperarContrasena {

    private Conexiondb conexion;  // Instancia de la clase Conexiondb para manejar la conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos
    public RecuperarContrasena() {
        this.conexion = new Conexiondb();
    }

    // Método para validar si el correo existe en la base de datos
    public boolean validarCorreo(String email) {
        String sql = "SELECT id_usuario FROM tb_usuario WHERE email = ?";  // Consulta SQL para seleccionar el id_usuario basado en el correo electrónico
        try (Connection cx = conexion.Conectar();  // Establece una conexión con la base de datos usando Conexiondb
             PreparedStatement ps = cx.prepareStatement(sql)) {  // Prepara la consulta SQL

            ps.setString(1, email);  // Establece el valor del parámetro en la consulta SQL
            try (ResultSet rs = ps.executeQuery()) {  // Ejecuta la consulta y obtiene los resultados
                return rs.next();  // Devuelve true si hay resultados, indicando que el correo existe
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace si ocurre una excepción SQL
            return false;  // Devuelve false si ocurre un error
        }
    }

    // Método para actualizar la contraseña del usuario
    public boolean actualizarContrasena(String email, String password) {
        String sql = "UPDATE tb_login SET password = ? WHERE email = ?";  // Consulta SQL para actualizar la contraseña basado en el correo electrónico
        try (Connection cx = conexion.Conectar();  // Establece una conexión con la base de datos usando Conexiondb
             PreparedStatement ps = cx.prepareStatement(sql)) {  // Prepara la consulta SQL

            ps.setString(1, password);  // Establece el valor del parámetro para la nueva contraseña
            ps.setString(2, email);  // Establece el valor del parámetro para el correo electrónico
            int rowsUpdated = ps.executeUpdate();  // Ejecuta la actualización y obtiene el número de filas actualizadas
            return rowsUpdated > 0;  // Devuelve true si se actualizaron filas, indicando éxito
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace si ocurre una excepción SQL
            return false;  // Devuelve false si ocurre un error
        }
    }
}
