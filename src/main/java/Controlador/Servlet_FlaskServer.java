/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "Servlet_FlaskServer", urlPatterns = {"/startFlaskServer"})
public class Servlet_FlaskServer extends HttpServlet {
private static final long serialVersionUID = 1L;
    private Process flaskProcess;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void init() throws ServletException {
        super.init();
        startFlaskServer();
    }

    @Override
    public void destroy() {
        super.destroy();
        stopFlaskServer();
    }

    private void startFlaskServer() {
        executor.submit(() -> {
            try {
                 String scriptPath = "D:\\Usuario\\Desktop\\Proyecto Final\\src\\main\\java\\Modelo\\chatbot.py"; // Cambia esto por la ruta correcta
                ProcessBuilder pb = new ProcessBuilder("D:\\Python\\python.exe", scriptPath);
                pb.redirectErrorStream(true);
                flaskProcess = pb.start();

                // Leer el output del servidor Flask
                InputStream is = flaskProcess.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                }

                flaskProcess.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void stopFlaskServer() {
        if (flaskProcess != null && flaskProcess.isAlive()) {
            flaskProcess.destroy();
        }
        executor.shutdownNow();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("El servidor Flask se ha iniciado.");
    }
}