package pe.edu.upc.faraway.activity;

public class Usuarios {
    private String id_usuario;
    private String clave;
    private String nombres;
    private String paterno;

    public Usuarios(String id_usuario, String clave, String nombres, String paterno) {
        this.id_usuario = id_usuario;
        this.clave = clave;
        this.nombres = nombres;
        this.paterno = paterno;
    }
    public Usuarios()
    {

    }
    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }



}
