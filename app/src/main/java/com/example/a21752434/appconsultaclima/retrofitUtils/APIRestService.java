package com.example.a21752434.appconsultaclima.retrofitUtils;

import com.example.a21752434.appconsultaclima.retrofitData.Localizacion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {

    public static final String BASE_URL = "https://api.darksky.net/";
    public static final String KEY = "11ce4328111023379e0fdc9d28c24a02";

    @GET("forecast/11ce4328111023379e0fdc9d28c24a02/{coord}")
    Call<Localizacion> obtenerDatosJSON(@Path("coord") String coord, @Query("exclude") String var1, @Query("lang") String var2);

}
