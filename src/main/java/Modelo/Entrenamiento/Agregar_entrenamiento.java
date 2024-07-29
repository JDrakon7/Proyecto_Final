
    package Modelo.Entrenamiento;

    import Modelo.Conexiondb;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;

    public class Agregar_entrenamiento {

        private Conexiondb conexion;

         public Agregar_entrenamiento() {
            this.conexion = new Conexiondb();
        }
         public void insertarDatosIniciales() {
            Connection cx = conexion.Conectar();
            String sql = "INSERT INTO tb_entrenamiento (pregunta, respuesta) VALUES (?, ?)";

            String[][] datos = {
                {"Hola", "Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python"},
                {"Como estas", "Hola soy Botmaster, mi funcion principal es brindar orientacion sobre la logica de programacion en Python"}



            };
              try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
                for (String[] dato : datos) {
                    pstmt.setString(1, dato[0]);
                    pstmt.setString(2, dato[1]);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                System.out.println("Datos iniciales insertados exitosamente");
            } catch (SQLException e) {
                System.out.println("Error al insertar datos iniciales: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        }

        public static void main(String[] args) {
            Agregar_entrenamiento insertar = new Agregar_entrenamiento();
            insertar.insertarDatosIniciales();
        }
    }


     

