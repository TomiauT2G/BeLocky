package com.example.belocky;

public class Usuario {
    String usuario;
    int id;
    String correo;
    String contrasenna;

    public Usuario(String usuario, int id, String correo, String contrasenna) {
        this.usuario = usuario;
        this.id = id;
        this.correo = correo;
        this.contrasenna = contrasenna;
    }

    public int getID() {
        return id;
    }

    public void setDataUsuario() {

    }
}
