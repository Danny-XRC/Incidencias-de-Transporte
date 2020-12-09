package com.example.incidencias.models;

public class DatosVehiculo {

    private String ID_Vehiculo;
    private String VEH_Placa;
    private String VEH_Color;
    private String VEH_Modelo;
    private String VEH_Marca;
    private String ID_Tipo_Vehiculo;
    private String Tipo_Vehiculo;
    private String ID_Conductor;
    private String Nombre_Conductor;
    private String message;

    public String getID_Vehiculo() {
        return ID_Vehiculo;
    }

    public void setID_Vehiculo(String ID_Vehiculo) {
        this.ID_Vehiculo = ID_Vehiculo;
    }

    public String getVEH_Placa() {
        return VEH_Placa;
    }

    public void setVEH_Placa(String VEH_Placa) {
        this.VEH_Placa = VEH_Placa;
    }

    public String getVEH_Color() {
        return VEH_Color;
    }

    public void setVEH_Color(String VEH_Color) {
        this.VEH_Color = VEH_Color;
    }

    public String getVEH_Modelo() {
        return VEH_Modelo;
    }

    public void setVEH_Modelo(String VEH_Modelo) {
        this.VEH_Modelo = VEH_Modelo;
    }

    public String getVEH_Marca() {
        return VEH_Marca;
    }

    public void setVEH_Marca(String VEH_Marca) {
        this.VEH_Marca = VEH_Marca;
    }

    public String getID_Tipo_Vehiculo() {
        return ID_Tipo_Vehiculo;
    }

    public void setID_Tipo_Vehiculo(String ID_Tipo_Vehiculo) {
        this.ID_Tipo_Vehiculo = ID_Tipo_Vehiculo;
    }

    public String getTipo_Vehiculo() {
        return Tipo_Vehiculo;
    }

    public void setTipo_Vehiculo(String tipo_Vehiculo) {
        Tipo_Vehiculo = tipo_Vehiculo;
    }

    public String getID_Conductor() {
        return ID_Conductor;
    }

    public void setID_Conductor(String ID_Conductor) {
        this.ID_Conductor = ID_Conductor;
    }

    public String getNombre_Conductor() {
        return Nombre_Conductor;
    }

    public void setNombre_Conductor(String nombre_Conductor) {
        Nombre_Conductor = nombre_Conductor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
