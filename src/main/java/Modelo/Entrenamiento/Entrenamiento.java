/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entrenamiento;

import java.sql.Timestamp;

/**
 *
 * @author JDBJ
 */
public class Entrenamiento {

        private int idEntrenamiento;
        private String pregunta;
        private String respuesta;
        private int idCategoria;
        private int idUsuario;
        private Timestamp fechaActualizacion;

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
  }

