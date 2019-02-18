package com.example.a21752434.appconsultaclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21752434.appconsultaclima.retrofitData.Localizacion;
import com.example.a21752434.appconsultaclima.retrofitUtils.APIRestService;
import com.example.a21752434.appconsultaclima.retrofitUtils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    private String coord;
    private String var1;
    private String var2;
    private Localizacion loc;

    private TextView tvCiudad;
    private ImageView ivImagen;
    private TextView tvHora;
    private TextView tvGrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        /*Iniciar elementos View*/
        tvCiudad = findViewById(R.id.tvCiudad);
        ivImagen = findViewById(R.id.ivImagen);
        tvHora = findViewById(R.id.tvHora);
        tvGrados = findViewById(R.id.tvGrados);

        /*Obtener datos coordenadas*/
        double latitud = getIntent().getDoubleExtra("LAT", 0.0);
        double longitud = getIntent().getDoubleExtra("LON", 0.0);
        coord = String.valueOf(latitud) +","+ String.valueOf(longitud);

        var1 = "minutely,hourly,daily,alerts,flags";
        var2 = "es";

        /*Conectar al API*/
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<Localizacion> call = ars.obtenerDatosJSON(coord, var1, var2);

        call.enqueue(new Callback<Localizacion>() {
            @Override
            public void onResponse(Call<Localizacion> call, Response<Localizacion> response) {
                if(response.isSuccessful()) {
                    loc = response.body();

                    tvCiudad.setText(loc.getTimezone());
                    //ivImagen.setImageDrawable(loc.getCurrently().getIcon());
                    tvHora.setText(loc.getCurrently().getTime());

                } else {
                    showMessage(R.string.toast_response_unsuccessful);
                }
            }

            @Override
            public void onFailure(Call<Localizacion> call, Throwable t) {
                showMessage(R.string.toast_failure);
            }
        });
    }

    public void showMessage(int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show();
    }
}
