/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entrenamiento;

import java.sql.Timestamp; // Importación para manejar el tipo de datos Timestamp, que representa una marca de tiempo

/**
 *
 * @author JDBJ
 */
public class Entrenamiento {

    // Declaración de atributos privados de la clase
    private int idEntrenamiento;          // ID único para cada entrenamiento
    private String pregunta;              // Texto de la pregunta del entrenamiento
    private String respuesta;             // Texto de la respuesta correspondiente a la pregunta
    private int idCategoria;              // ID de la categoría a la que pertenece el entrenamiento
    private int idUsuario;                // ID del usuario que creó o modificó el entrenamiento
    private Timestamp fechaActualizacion; // Marca de tiempo de la última actualización del entrenamiento
    private String nombreCategoria;       // Nombre de la categoría a la que pertenece el entrenamiento (puede ser redundante con idCategoria, pero útil para mostrar en interfaces)

    // Métodos getter y setter para cada atributo
    // Los getters obtienen el valor de un atributo específico
    // Los setters asignan un valor a un atributo específico

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
