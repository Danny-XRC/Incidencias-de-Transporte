package com.example.incidencias.interfaces;

import android.graphics.Bitmap;

import com.example.incidencias.models.DatosRegistroIncidente;
import com.example.incidencias.models.RespuestaRegistroIncidente;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRegistroIncidente {
@Multipart
    @POST("api/Incidente/Insertar_Incidente.php")
    Call<RespuestaRegistroIncidente>registroIncidentes(
            @Part("ID_Vehiculo") RequestBody IdVehiculo,
            @Part("ID_Usuario") RequestBody IDusuario,
            @Part("ind_Descripcion") RequestBody descripcion,
            @Part("ind_Fecha_Incidente") RequestBody FechaTotal,
            @Part("ind_Tipo_incidente") RequestBody seleccion,
            @Part MultipartBody.Part img
    );
    //@Part MultipartBody.Part img);

/*    @FormUrlEncoded
    @POST("api/Incidente/Insertar_Incidente.php")
            Call<RespuestaRegistroIncidente>registroIncidentes(
                    @Field("ID_Vehiculo") String IdVehiculo,
                    @Field("ID_Usuario") String IDusuario,
                    @Field("ind_Descripcion") String descripcion,
                    @Field("ind_Fecha_Incidente") String FechaTotal,
                    @Field("ind_Tipo_incidente") String seleccion
                    );
                    //@Part MultipartBody.Part img);*/
}
