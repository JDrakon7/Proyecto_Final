/*

 */
package Modelo.Login;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Leer_login {
        
    private Conexiondb conexion;
    
    public Leer_login(){
        this.conexion = new Conexiondb();
        
    }
}
