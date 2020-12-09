package com.example.incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.incidencias.models.ApiCliente;
import com.example.incidencias.models.RespuestaLogin;
import com.example.incidencias.models.SolicitudLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText EtCorreo, EtContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtCorreo=(EditText)findViewById(R.id.EtCorreo);
        EtContrasenia=(EditText)findViewById(R.id.EtContrasenia);
    }

    public void validar(View view){
        if (TextUtils.isEmpty(EtCorreo.getText().toString()) || TextUtils.isEmpty(EtContrasenia.getText().toString())){

            Toast.makeText(this, "Correo y contraseña requerido", Toast.LENGTH_LONG).show();
        }else {
            //Procede a logearse
            Login();
        }
    }
    public void irRegistroUsuario(View view){
        Intent registroUsuario=new Intent(this,RegistroUsuario.class);
        startActivity(registroUsuario);
    }

    public void Login(){
        SolicitudLogin solicitudLogin= new SolicitudLogin();
        solicitudLogin.setUS_Email(EtCorreo.getText().toString());
        solicitudLogin.setUS_Contrasena(EtContrasenia.getText().toString());

        Call<RespuestaLogin> respuestaLoginCall= ApiCliente.getServicioApiUsuario().userlogin(solicitudLogin);
        respuestaLoginCall. enqueue(new Callback<RespuestaLogin>() {
            @Override
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Exitoso", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String Email=EtCorreo.getText().toString();
                            Intent irEscaner=new Intent(MainActivity.this,Escaner.class);
                            irEscaner.putExtra("mail",Email);
                            startActivity(irEscaner);
                        }
                    },700);
                }else {
                    Toast.makeText(MainActivity.this, "Fallo en el Login", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}