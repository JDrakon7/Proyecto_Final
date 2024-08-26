/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.InputStream; // Importa la clase InputStream para leer datos de una fuente de entrada, como archivos o flujos de red.
import java.util.concurrent.Executors; // Importa la clase Executors para crear y gestionar grupos de hilos (thread pools) de manera sencilla.
import java.util.concurrent.ExecutorService; // Importa la interfaz ExecutorService para gestionar un grupo de hilos y ejecutar tareas concurrentemente.
import java.io.IOException; // Importa la clase IOException para manejar excepciones relacionadas con operaciones de entrada/salida, como errores al leer o escribir datos.
import javax.servlet.ServletException; // Importa la clase ServletException para manejar excepciones que pueden ocurrir durante la ejecución de servlets.
import javax.servlet.annotation.WebServlet; // Importa la anotación WebServlet para definir y configurar un servlet en una aplicación web de manera declarativa.
import javax.servlet.http.HttpServlet; // Importa la clase HttpServlet, que es la clase base para crear servlets que responden a solicitudes HTTP.
import javax.servlet.http.HttpServletRequest; // Importa la clase HttpServletRequest para manejar la solicitud HTTP enviada por el cliente.
import javax.servlet.http.HttpServletResponse; // Importa la clase HttpServletResponse para manejar la respuesta HTTP que se envía al cliente.


/**
 * Servlet para iniciar y detener un servidor Flask y script del chatbot.
 */
@WebServlet(name = "Servlet_FlaskServer", urlPatterns = {"/startFlaskServer"})  // Define la URL pattern para el servlet
public class Servlet_FlaskServer extends HttpServlet {
    private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización
    private Process flaskProcess;  // Variable para manejar el proceso del servidor Flask
    private Process saveInteractionProcess;  // Variable para manejar el proceso del script de guardado de interacciones
    private ExecutorService executor = Executors.newSingleThreadExecutor();  // Crea un ExecutorService para ejecutar procesos en un hilo separado

    @Override
    public void init() throws ServletException {  // Método llamado cuando se inicializa el servlet
        super.init();
        startFlaskServer();  // Inicia el servidor Flask cuando se inicializa el servlet
    }

    @Override
    public void destroy() {  // Método llamado cuando se destruye el servlet
        super.destroy();
        stopFlaskServer();  // Detiene el servidor Flask
        stopSaveInteractionScript();  // Detiene el script de guardado de interacciones
    }

    private void startFlaskServer() {  // Método para iniciar el servidor Flask
        executor.submit(() -> {  // Ejecuta el código en un hilo separado
            try {
                String scriptPath = "D:\\Usuario\\Desktop\\Proyecto Final\\src\\main\\java\\Modelo\\chatbot.py";  // Ruta al script de Flask
                ProcessBuilder pb = new ProcessBuilder("D:\\Python\\python.exe", scriptPath);  // Crea un ProcessBuilder para ejecutar el script de Flask con Python
                pb.redirectErrorStream(true);  // Redirige el flujo de errores al flujo de entrada
                flaskProcess = pb.start();  // Inicia el proceso del servidor Flask

                // Leer el output del servidor Flask
                InputStream is = flaskProcess.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);  // Imprime el output del servidor Flask en la consola
                }

                flaskProcess.waitFor();  // Espera a que el proceso termine
            } catch (Exception e) {
                e.printStackTrace();  // Imprime cualquier excepción que ocurra
            }
        });
    }

    private void stopFlaskServer() {  // Método para detener el servidor Flask
        if (flaskProcess != null && flaskProcess.isAlive()) {  // Verifica si el proceso del servidor Flask está activo
            flaskProcess.destroy();  // Detiene el proceso del servidor Flask
        }
        executor.shutdownNow();  // Detiene el ExecutorService
    }

    private void stopSaveInteractionScript() {  // Método para detener el script de guardado de interacciones
        if (saveInteractionProcess != null && saveInteractionProcess.isAlive()) {  // Verifica si el proceso de guardado de interacciones está activo
            saveInteractionProcess.destroy();  // Detiene el proceso de guardado de interacciones
        }
        executor.shutdownNow();  // Detiene el ExecutorService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes GET
        response.getWriter().println("El servidor Flask y el script de guardado de interacciones se han iniciado.");  // Envía una respuesta indicando que los procesos se han iniciado
    }
}
