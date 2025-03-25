package co.edu.poli.ces3.taller1_ces.dao;

import java.util.ArrayList;

public class Equipo {

    private Integer id;
    private String nombre;
    private String deporte;
    private String ciudad;
    private String fechaFun;
    private String logo;
    private ArrayList<Integer> jugadores;

    public Equipo() {
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

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaFun() {
        return fechaFun;
    }

    public void setFechaFun(String fechaFun) {
        this.fechaFun = fechaFun;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<Integer> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Integer> jugadores) {
        this.jugadores = jugadores;
    }


    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", deporte='" + deporte + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", fechaFun='" + fechaFun + '\'' +
                ", logo='" + logo + '\'' +
                ", jugadores=" + jugadores +
                '}';
    }
}
/* comentario para probar git */