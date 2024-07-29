
package Modelo.Login;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    
public class Login_update {
    
      private Conexiondb conexion;
      
      public Login_update(){
          
           this.conexion = new Conexiondb();
        
    }
      
     public void login_update(int idlogin , String email , String password){
         Connection cx = conexion.Conectar();
         String sql = "UPDATE tb_login SET email = ?, password = ? WHERE id_login = ?";
         try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setInt(3, idlogin);
            pstmt.executeUpdate();
            System.out.println("Login actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar login: " + e.getMessage());
        } finally {
            conexion.desconectar();
     }

     }
}