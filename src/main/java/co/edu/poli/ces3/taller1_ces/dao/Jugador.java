package co.edu.poli.ces3.taller1_ces.dao;

public class Jugador {

    private Integer id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String posicion;
    private Integer peto;
    private Integer equipoId;
    private boolean estado;


    public Jugador() {
    }

    public Jugador(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String posicion, Integer peto, Integer equipoId, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.peto = peto;
        this.equipoId = equipoId;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getPeto() {
        return peto;
    }

    public void setPeto(Integer peto) {
        this.peto = peto;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.id + " " + this.nombre + " " + this.apellido + " " + this.fechaNacimiento
                + " " + this.nacionalidad + " " + this.posicion + " " + this.peto + " " + this.equipoId +
                " " + this.estado;
    }
}

/* comentario para probar git */