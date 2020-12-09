package com.example.incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.incidencias.interfaces.ApiDetalles;
import com.example.incidencias.models.DatosDetalleIncidente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleIncidente extends AppCompatActivity {
    String numIncidente;
    public static String idIncidente;
    private TextView tvFecha,tvTipo,tvDescripcion,tvTitulo;
    private ImageView ivImagen;
    private TextView tvPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_incidente);

        tvFecha=(TextView)findViewById(R.id.tvFecha);
        tvTipo=(TextView)findViewById(R.id.tvTipo);
        tvDescripcion=(TextView)findViewById(R.id.tvDescripcion);
        tvTitulo=(TextView)findViewById(R.id.tvTitulo);
        ivImagen=(ImageView)findViewById(R.id.ivImagen);

        tvPrueba=(TextView)findViewById(R.id.tvPrueba);

        idIncidente=getIntent().getStringExtra("idIncidente");
        numIncidente=getIntent().getStringExtra("numInci");
        tvTitulo.setText("Incidente "+numIncidente);
        Consulta();
    }

    public void Consulta(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcityhyo.tk/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiDetalles apiDetalle=retrofit.create(ApiDetalles.class);
        Call<DatosDetalleIncidente> call = apiDetalle.getDetalle(idIncidente);
        call.enqueue(new Callback<DatosDetalleIncidente>() {
            @Override
            public void onResponse(Call<DatosDetalleIncidente> call, Response<DatosDetalleIncidente> response) {
                try {
                    if(response.isSuccessful()){
                        Toast.makeText(DetalleIncidente.this, "Exitoso", Toast.LENGTH_SHORT).show();
                        DatosDetalleIncidente datos=response.body();
                        String URL=datos.getInd_Fotografia();
                        String Fecha=datos.getInd_Fecha_Incidente().substring(0,19);
                        tvFecha.setText(Fecha);
                        tvTipo.setText(datos.getInd_Tipo_incidente());
                        tvDescripcion.setText(datos.getInd_Descripcion());
                        if (URL.length()>0){
                            int cantLetras=URL.length();
                            URL="https"+datos.getInd_Fotografia().substring(4,cantLetras);
                            Glide.with(getApplication()).load(URL).into(ivImagen);
                            tvPrueba.setText(URL);

                        }else {
                            Toast.makeText(DetalleIncidente.this, "imagen no encontrada", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(DetalleIncidente.this, response.code(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(DetalleIncidente.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DatosDetalleIncidente> call, Throwable t) {
                Toast.makeText(DetalleIncidente.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}