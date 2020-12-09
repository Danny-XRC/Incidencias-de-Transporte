package com.example.incidencias.models;

public class DatosListado {
    private String ID_Incidente;
    private String ID_Vehiculo;
    private String Placa_Vehiculo;
    private String ID_Usuario;
    private String Usuario_Nombres;
    private String ind_Descripcion;
    private String ind_Fecha_Incidente;
    private String ID_Tipo_Ind;
    private String Tipo_Incidente;

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

    public String getID_Tipo_Ind() {
        return ID_Tipo_Ind;
    }

    public void setID_Tipo_Ind(String ID_Tipo_Ind) {
        this.ID_Tipo_Ind = ID_Tipo_Ind;
    }

    public String getTipo_Incidente() {
        return Tipo_Incidente;
    }

    public void setTipo_Incidente(String tipo_Incidente) {
        Tipo_Incidente = tipo_Incidente;
    }
}
