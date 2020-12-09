package com.example.incidencias.interfaces;

import com.example.incidencias.models.DatosVehiculo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGenerales {
    @GET("api/Vehiculo/Consultar_Vehiculo_Placa.php")
    Call<DatosVehiculo> getVehiculo(@Query("VEH_Placa") String postId);
}
