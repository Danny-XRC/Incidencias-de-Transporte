package com.example.incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.incidencias.interfaces.ApiListado;
import com.example.incidencias.models.DatosListado;
import com.example.incidencias.models.ObjetoListado;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaIncidentes extends AppCompatActivity {
    public static String idVehicilo;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_incidentes);

        lv1=(ListView)findViewById(R.id.lv1);

        idVehicilo=getIntent().getStringExtra("idVehiculo");

        Consulta();
    }
    public void Consulta(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcityhyo.tk/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiListado apiListado=retrofit.create(ApiListado.class);

        Call<ObjetoListado> call= apiListado.getLista(idVehicilo);
        call.enqueue(new Callback<ObjetoListado>() {
            @Override
            public void onResponse(Call<ObjetoListado> call, Response<ObjetoListado> response) {
                try {
                    if(response.isSuccessful()){
                        Toast.makeText(ListaIncidentes.this, "Exitoso", Toast.LENGTH_LONG).show();

                        ObjetoListado objetoListado=response.body();
                        ArrayList<DatosListado> datosListados= (ArrayList<DatosListado>) objetoListado.getRecords();
                        String  incidentes[]=new String[datosListados.size()];
                        String  idIncidente[]=new String[datosListados.size()];
                        for (int i=0;i<datosListados.size();i++){
                            DatosListado l=datosListados.get(i);
                            String resumen=l.getInd_Fecha_Incidente().substring(0,19);

                            incidentes[i]="Incidente nº"+(i+1)+"\n"+"Fecha: "+resumen+"\n"+
                                    "Descripcion: "+l.getInd_Descripcion();
                            idIncidente[i]=l.getID_Incidente();
                        }

                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ListaIncidentes.this,R.layout.list_item_incidentes,incidentes);
                        lv1.setAdapter(adapter);
                        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent irDetalles= new Intent(ListaIncidentes.this, DetalleIncidente.class);
                                irDetalles.putExtra("idIncidente",idIncidente[position]);
                                int Titulo=position+1;
                                String num=Integer.toString(Titulo);
                                irDetalles.putExtra("numInci",num);
                                startActivity(irDetalles);
                            }
                        });

                    }else {
                        Toast.makeText(ListaIncidentes.this, response.code(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(ListaIncidentes.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjetoListado> call, Throwable t) {
                Toast.makeText(ListaIncidentes.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void irDetalleIncidente(View view){
        Intent irDetalle= new Intent(this, DetalleIncidente.class);
        startActivity(irDetalle);
    }
}