package com.example.incidencias.models;

public class RespuestaLogin {

    private boolean status;
    private String message;
    private String ID_Usuario;
    private String US_Email;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getUS_Email() {
        return US_Email;
    }

    public void setUS_Email(String US_Email) {
        this.US_Email = US_Email;
    }
}
