/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

/**
 *
 * @author JDBJ
 */
public class Usuario {
    
        private int idUsuario;
        private String nombre;
        private String email;
        private String role;

        // Constructor
        public Usuario(int idUsuario, String nombre, String email, String role) {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.email = email;
            this.role = role;
        }

        // Getters y setters
        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }



