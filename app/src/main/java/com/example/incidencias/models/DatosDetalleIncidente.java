package com.example.incidencias.models;

public class DatosDetalleIncidente {
    private String ID_Incidente;
    private String ID_Vehiculo;
    private String Placa_Vehiculo;
    private String ID_Usuario;
    private String Usuario_Nombres;
    private String ind_Descripcion;
    private String ind_Fecha_Incidente;
    private String ind_Tipo_incidente;
    private String ind_Fotografia;

    public String getID_Incidente() {
        return ID_Incidente;
    }

    public void setID_Incidente(String ID_Incidente) {
        this.ID_Incidente = ID_Incidente;
    }

    public String getID_Vehiculo() {
        return ID_Vehiculo;
    }

    public void setID_Vehiculo(String ID_Vehiculo) {
        this.ID_Vehiculo = ID_Vehiculo;
    }

    public String getPlaca_Vehiculo() {
        return Placa_Vehiculo;
    }

    public void setPlaca_Vehiculo(String placa_Vehiculo) {
        Placa_Vehiculo = placa_Vehiculo;
    }

    public String getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getUsuario_Nombres() {
        return Usuario_Nombres;
    }

    public void setUsuario_Nombres(String usuario_Nombres) {
        Usuario_Nombres = usuario_Nombres;
    }

    public String getInd_Descripcion() {
        return ind_Descripcion;
    }

    public void setInd_Descripcion(String ind_Descripcion) {
        this.ind_Descripcion = ind_Descripcion;
    }

    public String getInd_Fecha_Incidente() {
        return ind_Fecha_Incidente;
    }

    public void setInd_Fecha_Incidente(String ind_Fecha_Incidente) {
        this.ind_Fecha_Incidente = ind_Fecha_Incidente;
    }

    public String getInd_Tipo_incidente() {
        return ind_Tipo_incidente;
    }

    public void setInd_Tipo_incidente(String ind_Tipo_incidente) {
        this.ind_Tipo_incidente = ind_Tipo_incidente;
    }

    public String getInd_Fotografia() {
        return ind_Fotografia;
    }

    public void setInd_Fotografia(String ind_Fotografia) {
        this.ind_Fotografia = ind_Fotografia;
    }
}
