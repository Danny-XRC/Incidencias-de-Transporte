package com.example.incidencias.interfaces;

import com.example.incidencias.models.RespuestaLogin;
import com.example.incidencias.models.SolicitudLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicioApiUsuario {

    @POST("api/Usuario/login.php")
    Call<RespuestaLogin> userlogin(@Body SolicitudLogin solicitudLogin);
}
