package com.example.incidencias.interfaces;

import com.example.incidencias.models.DatosDetalleIncidente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDetalles {
    @GET("api/Incidente/Consultar_Incidentes.php")
    Call<DatosDetalleIncidente> getDetalle(@Query("ID_Incidente") String idIncidente);
}
