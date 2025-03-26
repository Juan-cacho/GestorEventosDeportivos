package co.edu.poli.ces3.taller1_ces.dao;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private Integer id;
    private String nombre;
    private String deporte;
    private String ciudad;
    private String fechaFun;
    private String logo;
    private ArrayList<Jugador> jugadores;

    public Equipo() {
    }

    public Equipo(Integer id, String nombre, String deporte, String ciudad, String fechaFun, String logo, ArrayList<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.fechaFun = fechaFun;
        this.logo = logo;
        this.jugadores = jugadores;
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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = new ArrayList<>(jugadores);
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