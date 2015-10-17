package br.com.rbarrelo.clima.helpers;

import br.com.rbarrelo.clima.openweathermap.api.OpenWeatherMapApi;
import br.com.rbarrelo.clima.util.Commom;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class RetrofitHelper {

    private Retrofit retrofit;

    private Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit =  new Retrofit.Builder()
                    .baseUrl(Commom.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }

    public OpenWeatherMapApi getOpenWeatherApi(){
        return getRetrofitInstance().create(OpenWeatherMapApi.class);
    }
}
