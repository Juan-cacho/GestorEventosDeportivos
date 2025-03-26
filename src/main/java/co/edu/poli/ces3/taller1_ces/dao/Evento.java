package co.edu.poli.ces3.taller1_ces.dao;

import java.util.ArrayList;
import java.util.List;

public class Evento {

    private Integer id;
    private String nombre;
    private String fecha;
    private String lugar;
    private String deporte;
    private Integer capacidad;
    private Integer entradasVendidas;
    private List<Integer> equipos;;
    private String estado;

    public Evento() {
    }

    public Evento(Integer id, String nombre, String fecha, String lugar, String deporte, Integer capacidad, Integer entradasVendidas, List<Integer> equipos, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.deporte = deporte;
        this.capacidad = capacidad;
        this.entradasVendidas = entradasVendidas;
        this.equipos = equipos;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getEntradasVendidas() {
        return entradasVendidas;
    }

    public void setEntradasVendidas(Integer entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }

    public List<Integer> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Integer> equipos) {
        this.equipos = equipos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", lugar='" + lugar + '\'' +
                ", deporte='" + deporte + '\'' +
                ", capacidad=" + capacidad +
                ", entradasVendidas=" + entradasVendidas +
                ", equipos=" + equipos +
                ", estado='" + estado + '\'' +
                '}';
    }
}

/* comentario para probar git */