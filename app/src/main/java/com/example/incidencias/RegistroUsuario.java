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
import com.example.incidencias.models.DatosRegistroUsuario;
import com.example.incidencias.models.RespuestaRegistroUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuario extends AppCompatActivity {

    private EditText etNombre,etApellido,etEmail,etContasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        etNombre=(EditText)findViewById(R.id.Et_Nombre);
        etApellido=(EditText)findViewById(R.id.Et_Apellido);
        etEmail=(EditText)findViewById(R.id.Et_Email);
        etContasena=(EditText)findViewById(R.id.Et_Contrasena);
    }

    public void RegistrarUsuario(View view){
        if (TextUtils.isEmpty(etNombre.getText().toString()) || TextUtils.isEmpty(etApellido.getText().toString())
                || TextUtils.isEmpty(etEmail.getText().toString())|| TextUtils.isEmpty(etContasena.getText().toString())){

            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_LONG).show();
        }else {

            enviarUsuario();
        }
    }

    public void enviarUsuario(){
        DatosRegistroUsuario datosUsuario=new DatosRegistroUsuario();
        datosUsuario.setUS_Nombres(etNombre.getText().toString());
        datosUsuario.setUS_Apellidos(etApellido.getText().toString());
        datosUsuario.setUS_Email(etEmail.getText().toString());
        datosUsuario.setUS_Contrasena(etContasena.getText().toString());

        Call<RespuestaRegistroUsuario> respuestaUsuario= ApiCliente.getApiRegistroUsuario().registrarUsuario(datosUsuario);
        respuestaUsuario.enqueue(new Callback<RespuestaRegistroUsuario>() {
            @Override
            public void onResponse(Call<RespuestaRegistroUsuario> call, Response<RespuestaRegistroUsuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegistroUsuario.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(RegistroUsuario.this,MainActivity.class));
                        }
                    },700);
                }else {
                    Toast.makeText(RegistroUsuario.this, "No se pudo registar", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRegistroUsuario> call, Throwable t) {
                Toast.makeText(RegistroUsuario.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}