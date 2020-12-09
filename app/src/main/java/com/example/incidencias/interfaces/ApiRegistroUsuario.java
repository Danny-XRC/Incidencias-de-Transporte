package com.example.incidencias.interfaces;

import com.example.incidencias.models.DatosRegistroUsuario;
import com.example.incidencias.models.RespuestaRegistroUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRegistroUsuario {
    @POST("api/Usuario/Insert_Usuario_Incidente.php")
    Call<RespuestaRegistroUsuario> registrarUsuario(@Body DatosRegistroUsuario datosUsuario);
}
