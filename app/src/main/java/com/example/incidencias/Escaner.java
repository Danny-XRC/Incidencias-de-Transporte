package com.example.incidencias;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Escaner extends AppCompatActivity {
    public static String Email;

    private TextView tvCodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);

        tvCodigo=(TextView) findViewById(R.id.tvCodigo);
        Email=getIntent().getStringExtra("mail");
    }
//validar y guardar el codigo leido
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents() != null){
                tvCodigo.setText(result.getContents());

                //Pasar el codigo leido a la actividad siguiente

               Intent irGenerales= new Intent(this,Generales.class);
               irGenerales.putExtra("placa",tvCodigo.getText().toString());
               irGenerales.putExtra("mail",Email);
               startActivity(irGenerales);

            }else {
                tvCodigo.setText("Error al leer el codigo");
            }
        }

    }

    public void ActivarEscaner(View view) {

//Activar el escaner
        IntentIntegrator intent= new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Enfoque la camara");
        intent.setCameraId(0);
        intent.setOrientationLocked(false);
        intent.setCaptureActivity(ActividadCapturadaRetrato.class);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();

    }
}