package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edtAgenda;
    private String vacio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAgenda = findViewById(R.id.edtAgenda);
        String datos[]=fileList();

        if (datosExistentes(datos,"bitacora.txt")){
            try {
                InputStreamReader guardado = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(guardado);
                String line = br.readLine();
                String bitacora = "";

                while (line!=null){
                    bitacora = bitacora + line + "\n";
                    line = br.readLine();
                }
                br.close();
                guardado.close();
                edtAgenda.setText(bitacora);
            }catch (IOException ex){
            }
        }
    }

    private boolean datosExistentes(String archivos[], String NombreArchivo){
        for (int i=0;i<archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    public void btnGuardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(edtAgenda.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException ex){
        }
        Toast.makeText(this, "La Bitacora ha sido guardada", Toast.LENGTH_SHORT).show();

    }

    public void btnBorrar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(vacio);
            archivo.flush();
            archivo.close();
        }catch (IOException ex){
        }
        Toast.makeText(this, "Los datos de Bitacora han sido borrados", Toast.LENGTH_SHORT).show();

    }
}