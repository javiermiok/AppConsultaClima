package com.example.a21752434.appconsultaclima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etLatitud;
    private EditText etLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
    }

    public void consultarTiempo(View v) {
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();

        if (latitud.trim().equals("") || longitud.trim().equals("")) {
            showMessage(R.string.toast_campos_vacios);

        } else {
            Intent i = new Intent(this, WeatherActivity.class);
            i.putExtra("LAT", latitud);
            i.putExtra("LON", longitud);
            startActivity(i);

        }

    }

    public void showMessage(int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show();
    }
}
