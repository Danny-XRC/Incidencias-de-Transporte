package com.example.incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incidencias.interfaces.ApiGenerales;
import com.example.incidencias.models.DatosVehiculo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Generales extends AppCompatActivity {
    public static String Email;

    private TextView TvPlaca,TvMarca,TvConductor;
    String idVehiculo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generales);

        TvPlaca= (TextView)findViewById(R.id.tv_placa);
        TvMarca= (TextView) findViewById(R.id.tv_marca);
        TvConductor= (TextView) findViewById(R.id.tv_conductor);
        Email=getIntent().getStringExtra("mail");

        Consulta();
    }

    public void irListaIncidentes(View view){
        Intent irLista= new Intent(this, ListaIncidentes.class);
        irLista.putExtra("idVehiculo",idVehiculo);
        startActivity(irLista);
    }
    public void irRegistroIncidentes(View view){
        Intent irRegistro= new Intent(this, RegistroIncidentes.class);
        irRegistro.putExtra("idVehiculo",idVehiculo);
        irRegistro.putExtra("mail",Email);
        startActivity(irRegistro);
    }
    public void Consulta(){
        String placa=getIntent().getStringExtra("placa");

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcityhyo.tk/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiGenerales apiGenerales=retrofit.create(ApiGenerales.class);
        Call<DatosVehiculo> call = apiGenerales.getVehiculo(placa);
        call.enqueue(new Callback<DatosVehiculo>() {
            @Override
            public void onResponse(Call<DatosVehiculo> call, Response<DatosVehiculo> response) {
                try {
                    if(response.isSuccessful()){
                        Toast.makeText(Generales.this, "Exitoso", Toast.LENGTH_LONG).show();
                        DatosVehiculo datosVehiculo=response.body();
                        TvPlaca.setText(datosVehiculo.getVEH_Placa());
                        TvMarca.setText(datosVehiculo.getVEH_Marca());
                        TvConductor.setText(datosVehiculo.getNombre_Conductor());

                        idVehiculo=datosVehiculo.getID_Vehiculo();

                    }else {
                        Toast.makeText(Generales.this, response.code(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(Generales.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DatosVehiculo> call, Throwable t) {
                Toast.makeText(Generales.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}