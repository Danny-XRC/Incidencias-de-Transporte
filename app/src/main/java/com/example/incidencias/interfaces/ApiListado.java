package com.example.incidencias.interfaces;

import com.example.incidencias.models.ObjetoListado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiListado {
    @GET("api/Incidente/Consultar_Incidente_Fecha.php")
    Call<ObjetoListado> getLista(@Query("ID_Vehiculo") String idVehiculo);
}
