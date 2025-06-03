package com.odontologia.app.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;        // ← AGREGAR
    private String descripcion;
    private Double precio;        // ← CAMBIAR de 'costo' a 'precio'
    private Integer duracion;     // ← AGREGAR
    private LocalDate fecha;
   
    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    // ← AGREGAR getter y setter para nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    // ← CAMBIAR de getCosto a getPrecio
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    // ← AGREGAR getter y setter para duracion
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}