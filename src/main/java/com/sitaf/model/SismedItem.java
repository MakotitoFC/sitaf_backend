package com.sitaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos_sismed")
public class SismedItem {
    @Id
    private String codigo;
    private String nombre;
    private int stockActual;
    private String ubicacion;
    private String fechaVencimiento;

    public SismedItem() {
    }

    public SismedItem(String codigo, String nombre, int stockActual, String ubicacion, String fechaVencimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.ubicacion = ubicacion;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}
