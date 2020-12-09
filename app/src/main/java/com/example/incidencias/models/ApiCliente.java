package com.example.incidencias.models;

import com.example.incidencias.interfaces.ApiRegistroIncidente;
import com.example.incidencias.interfaces.ApiRegistroUsuario;
import com.example.incidencias.interfaces.ServicioApiUsuario;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://smartcityhyo.tk/")
                .build();

        return retrofit;
    }

    public static ServicioApiUsuario getServicioApiUsuario(){
        ServicioApiUsuario servicioApiUsuario= getRetrofit().create(ServicioApiUsuario.class);
        return servicioApiUsuario;
    }

    public static ApiRegistroIncidente getApiRegistroIncidente(){
        ApiRegistroIncidente apiRegistroIncidente=getRetrofit().create(ApiRegistroIncidente.class);
        return apiRegistroIncidente;
    }
    public static ApiRegistroUsuario getApiRegistroUsuario(){
        ApiRegistroUsuario apiRegistroUsuario=getRetrofit().create(ApiRegistroUsuario.class);
        return apiRegistroUsuario;
    }
}
