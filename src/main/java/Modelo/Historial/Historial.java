/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Historial;

import java.util.Date;

/**
 *
 * @author JDBJ
 */
public class Historial {
    // Atributos de la clase Historial
    private int id;                     // ID único para cada registro de historial
    private java.util.Date fecha;       // Fecha de la interacción
    private java.util.Date hora;        // Hora de la interacción
    private int idUsuario;              // ID del usuario que realizó la interacción
    private String pregunta;            // Pregunta realizada por el usuario
    private String respuesta;           // Respuesta dada por el sistema

    // Getters y setters para los atributos

    // Obtiene el ID del historial
    public int getId() {
        return id;
    }

    // Establece el ID del historial
    public void setId(int id) {
        this.id = id;
    }

    // Obtiene la fecha de la interacción
    public java.util.Date getFecha() {
        return fecha;
    }

    // Establece la fecha de la interacción
    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }
    
    // Obtiene la hora de la interacción
    public Date getHora() {
        return hora;
    }

    // Establece la hora de la interacción
    public void setHora(Date hora) {
        this.hora = hora;
    }

    // Obtiene el ID del usuario que realizó la interacción
    public int getIdUsuario() {
        return idUsuario;
    }

    // Establece el ID del usuario que realizó la interacción
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Obtiene la pregunta realizada por el usuario
    public String getPregunta() {
        return pregunta;
    }

    // Establece la pregunta realizada por el usuario
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    // Obtiene la respuesta dada por el sistema
    public String getRespuesta() {
        return respuesta;
    }

    // Establece la respuesta dada por el sistema
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}

