/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Categorias;  // Especifica que la clase pertenece al paquete Modelo.Categorias

/**
 * Clase que representa una categoría en el sistema.
 * 
 * @autor JDBJ
 */
public class Categoria {

    // Atributos privados de la clase
    private int idCategoria;  // Identificador único de la categoría
    private String nombreCategoria;  // Nombre de la categoría

    // Método getter para obtener el valor de idCategoria
    public int getIdCategoria() {
        return idCategoria;
    }

    // Método setter para asignar un valor a idCategoria
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Método getter para obtener el valor de nombreCategoria
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    // Método setter para asignar un valor a nombreCategoria
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}

    
