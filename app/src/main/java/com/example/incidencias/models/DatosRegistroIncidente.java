package com.example.incidencias.models;

public class DatosRegistroIncidente {
    private String ID_Vehiculo;
    private String ID_Usuario;
    private String ind_Descripcion;
    private String ind_Fecha_Incidente;
    private String ind_Tipo_incidente;

    public String getID_Vehiculo() {
        return ID_Vehiculo;
    }

    public void setID_Vehiculo(String ID_Vehiculo) {
        this.ID_Vehiculo = ID_Vehiculo;
    }

    public String getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
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
}
