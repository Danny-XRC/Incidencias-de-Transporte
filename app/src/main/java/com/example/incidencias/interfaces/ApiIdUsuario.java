package com.example.incidencias.interfaces;

import com.example.incidencias.models.ObjetoDatosUsuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiIdUsuario {
    @GET("api/buscar_Email.php")
    Call<ObjetoDatosUsuario> consultaIdUsuario(@Query("US_Email") String Email);
}
