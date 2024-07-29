
 // Esta clase se encarga de la conexión a la base de datos para la aplicación del chatbot.
 //Establece una conexión con la base de datos MySQL y proporciona métodos para conectarse,
 // desconectarse y administrar la conexión.

package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexiondb {
    String bd = "db_chatbotbm";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Connection Conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("Conexion exitosa " + bd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Conexion fallida " + bd);
            Logger.getLogger(Conexiondb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    public void desconectar() {
        if (cx != null) {
            try {
                cx.close();
                System.out.println("Conexion cerrada " + bd);
            } catch (SQLException ex) {
                Logger.getLogger(Conexiondb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Conexiondb conexion = new Conexiondb();
        conexion.Conectar();
    }
}
