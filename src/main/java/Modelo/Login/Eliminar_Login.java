package Modelo.Login;



import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar_Login {
    
    private Conexiondb conexion;
    
    public Eliminar_Login() {
        this.conexion = new Conexiondb();
    }
    public void eliminarLogin(int idLogin){
        Connection cx = conexion.Conectar();
        String sql = "DELETE FROM tb_login WHERE id_login = ?";
         try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, idLogin);
            pstmt.executeUpdate();
            System.out.println("Login eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar login: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
    
}
