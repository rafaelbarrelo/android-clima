package br.com.rbarrelo.clima.modelos.openweathermap.api;

import br.com.rbarrelo.clima.modelos.openweathermap.pojo.OpenWeatherMap;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public interface OpenWeatherMapApi {

    @GET("weather")
    Call<OpenWeatherMap> getTemperatura(@Query("q") String query_string,
                                        @Query("appid") String appKey);
}
