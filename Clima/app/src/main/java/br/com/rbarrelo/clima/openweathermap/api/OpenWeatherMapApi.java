package br.com.rbarrelo.clima.openweathermap.api;

import br.com.rbarrelo.clima.openweathermap.pojo.OpenWeatherMap;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public interface OpenWeatherMapApi {

    @GET("weather")
    Call<OpenWeatherMap> getTemperatura(@Query("lat") String latitude,
                                        @Query("lon") String longitude,
                                        @Query("appid") String appKey);

}
