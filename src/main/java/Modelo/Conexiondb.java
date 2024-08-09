
 // Esta clase se encarga de la conexión a la base de datos para la aplicación del chatbot.
 //Establece una conexión con la base de datos MySQL y proporciona métodos para conectarse,
 // desconectarse y administrar la conexión.

package Modelo;  // Paquete en el que se encuentra la clase Conexiondb

import java.sql.Connection;  // Importa la interfaz Connection para manejar la conexión a la base de datos
import java.sql.DriverManager;  // Importa la clase DriverManager para gestionar la conexión a la base de datos
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores relacionados con SQL
import java.util.logging.Level;  // Importa la clase Level para definir los niveles de severidad en los logs
import java.util.logging.Logger;  // Importa la clase Logger para registrar mensajes de log

public class Conexiondb {  // Define la clase Conexiondb
    // Variables de configuración para la conexión a la base de datos
    String bd = "db_chatbotbm";  // Nombre de la base de datos
    String url = "jdbc:mysql://localhost:3306/";  // URL de conexión a MySQL, especificando el puerto 3306
    String user = "root";  // Usuario de la base de datos
    String password = "";  // Contraseña del usuario de la base de datos
    String driver = "com.mysql.cj.jdbc.Driver";  // Controlador JDBC para MySQL
    Connection cx;  // Objeto para gestionar la conexión a la base de datos

    public Connection Conectar() {  // Método para establecer la conexión con la base de datos
        try {
            Class.forName(driver);  // Carga el controlador JDBC para MySQL
            cx = DriverManager.getConnection(url + bd, user, password);  // Establece la conexión con la base de datos usando la URL, usuario y contraseña
            System.out.println("Conexion exitosa " + bd);  // Mensaje de éxito si la conexión es establecida
        } catch (ClassNotFoundException | SQLException ex) {  // Captura errores si el controlador no se encuentra o la conexión falla
            System.out.println("Conexion fallida " + bd);  // Mensaje de error si la conexión falla
            Logger.getLogger(Conexiondb.class.getName()).log(Level.SEVERE, null, ex);  // Registra el error en los logs
        }
        return cx;  // Devuelve la conexión a la base de datos
    }

    public void desconectar() {  // Método para cerrar la conexión con la base de datos
        if (cx != null) {  // Verifica si la conexión no es nula
            try {
                cx.close();  // Cierra la conexión a la base de datos
                System.out.println("Conexion cerrada " + bd);  // Mensaje de confirmación de que la conexión fue cerrada
            } catch (SQLException ex) {  // Captura errores si la conexión no puede ser cerrada
                Logger.getLogger(Conexiondb.class.getName()).log(Level.SEVERE, null, ex);  // Registra el error en los logs
            }
        }
    }

    public static void main(String[] args) {  // Método principal para ejecutar la clase
        Conexiondb conexion = new Conexiondb();  // Crea una instancia de Conexiondb
        conexion.Conectar();  // Llama al método Conectar para establecer la conexión con la base de datos
    }
}
