package com.example.incidencias;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.incidencias.interfaces.ApiIdUsuario;
import com.example.incidencias.models.ApiCliente;
import com.example.incidencias.models.DatoIdUsuario;
import com.example.incidencias.models.ObjetoDatosUsuario;
import com.example.incidencias.models.RespuestaRegistroIncidente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroIncidentes extends AppCompatActivity {
    public static String Email;
    private Spinner spinerTipo;
    private EditText etDescripcion;
    private ImageView fotoIncidente;

    String seleccion;
    String IDusuario;
    String rutaImagen;
    Bitmap imgBitmap;

    Uri path;
    OutputStream outputStream;
    File imgIncidencias;

    private static final int WRITE_EXTERNAL_STORAGE_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                    PackageManager.PERMISSION_DENIED){

                String[] permission={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, WRITE_EXTERNAL_STORAGE_CODE);

            }
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                    PackageManager.PERMISSION_DENIED){

                Toast.makeText(RegistroIncidentes.this, "El permiso de lectura esta denegado", Toast.LENGTH_LONG).show();

            }
        }
        setContentView(R.layout.activity_registro_incidentes);
        etDescripcion=(EditText)findViewById(R.id.EtDescripcion);
        spinerTipo=(Spinner)findViewById(R.id.spinTipo);
        fotoIncidente=(ImageView)findViewById(R.id.ivFoto);

        String[]TiposIncidentes={"Accidente de tránsito","Maltrato","Exceso de Tiempo","Sobrecarga","Exceso de Velocidad"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_item_registro,TiposIncidentes);
        spinerTipo.setAdapter(adapter);

        Email=getIntent().getStringExtra("mail");
        
        ConsultarIdUsuario();
    }

    public void ConsultarIdUsuario() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcityhyo.tk/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiIdUsuario apiIdUsuario=retrofit.create(ApiIdUsuario.class);
        Call<ObjetoDatosUsuario> call = apiIdUsuario.consultaIdUsuario(Email);
        call.enqueue(new Callback<ObjetoDatosUsuario>() {
            @Override
            public void onResponse(Call<ObjetoDatosUsuario> call, Response<ObjetoDatosUsuario> response) {
                try {
                    if(response.isSuccessful()){
                        ObjetoDatosUsuario usuario=response.body();
                        ArrayList<DatoIdUsuario> datoIdUsuarios= (ArrayList<DatoIdUsuario>) usuario.getUsuario();
                        DatoIdUsuario U=datoIdUsuarios.get(0);
                        IDusuario=U.getID_Usuario();
                        Toast.makeText(RegistroIncidentes.this, "Exitoso:"+IDusuario, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistroIncidentes.this, response.code(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(RegistroIncidentes.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjetoDatosUsuario> call, Throwable t) {
                Toast.makeText(RegistroIncidentes.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void TomarFoto(View view){
/*        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            File imagenArchivo=null;

            try {
                imagenArchivo=crearImagen();
            }catch (IOException ex){

            }
            if (imagenArchivo!=null){
                Uri fotoUri= FileProvider.getUriForFile(this,"com.example.incidencias",imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent,1);
            }
        }*/
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

           String rutaPrueba="/storage/emulated/0/Android/data/com.example.incidencias/files/Pictures/solofoto.jpg";

           path=data.getData();

           rutaImagen=data.getData().getPath();
           //fotoIncidente.setImageURI(path);
            imgBitmap= BitmapFactory.decodeFile(rutaPrueba);
            fotoIncidente.setImageURI(path);
            BitmapDrawable drawable=(BitmapDrawable) fotoIncidente.getDrawable();
            Bitmap bitmap=drawable.getBitmap();

            File filepath=Environment.getExternalStorageDirectory();
            File dir= new File(filepath.getAbsolutePath()+"/Incidencias/");
            dir.mkdir();
            imgIncidencias =new File(dir,System.currentTimeMillis()+".jpg");
            try {
                outputStream=new FileOutputStream(imgIncidencias);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(RegistroIncidentes.this, "Guardado en incidencias", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }


            etDescripcion.setText(imgIncidencias.getAbsolutePath());

            rutaImagen=rutaPrueba;
        }
    }
/*    private File crearImagen() throws IOException {
        String nombreImagen="foto_";
        File directorio=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File Imagen=File.createTempFile(nombreImagen,".jpg",directorio);
        rutaImagen=Imagen.getAbsolutePath();
        return Imagen;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void registrarIncidente(View view){

        String IdVehiculo=getIntent().getStringExtra("idVehiculo");
        File imagen=new File(rutaImagen);

        LocalDate fechaActual=LocalDate.now();
        LocalTime horaActual=LocalTime.now();

        String FechaTotal=fechaActual.toString()+" "+horaActual.toString();
        seleccion=spinerTipo.getSelectedItem().toString();
        String descripcion=etDescripcion.getText().toString();

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),imgIncidencias);
        MultipartBody.Part img=MultipartBody.Part.createFormData("incidente_image",imagen.getName(),requestBody);

        RequestBody idVehiculo= RequestBody.create(MediaType.parse("multipart/form-data"),IdVehiculo);
        RequestBody idUsuario= RequestBody.create(MediaType.parse("multipart/form-data"),IDusuario);
        RequestBody Descripcion= RequestBody.create(MediaType.parse("multipart/form-data"),descripcion);
        RequestBody fechaTotal= RequestBody.create(MediaType.parse("multipart/form-data"),FechaTotal);
        RequestBody Seleccion= RequestBody.create(MediaType.parse("multipart/form-data"),seleccion);

        Call<RespuestaRegistroIncidente> registroIncidente= ApiCliente.getApiRegistroIncidente().registroIncidentes(
                idVehiculo,
                idUsuario,
                Descripcion,
                fechaTotal,
                Seleccion,
                img
        );
        registroIncidente.enqueue(new Callback<RespuestaRegistroIncidente>() {
            @Override
            public void onResponse(Call<RespuestaRegistroIncidente> call, Response<RespuestaRegistroIncidente> response) {
                try {
                    if(response.isSuccessful()){
                        Toast.makeText(RegistroIncidentes.this, "Exitoso", Toast.LENGTH_SHORT).show();


                    }else {
                        Toast.makeText(RegistroIncidentes.this, response.code(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(RegistroIncidentes.this, "Error ex:"+ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRegistroIncidente> call, Throwable t) {
                Toast.makeText(RegistroIncidentes.this, "Error onFailure:"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}