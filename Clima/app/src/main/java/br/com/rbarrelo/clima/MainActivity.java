package br.com.rbarrelo.clima;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.rbarrelo.clima.design.ColorAnimation;
import br.com.rbarrelo.clima.design.LineDividerItemDecoration;
import br.com.rbarrelo.clima.eventbus.MessageEvent;
import br.com.rbarrelo.clima.helpers.GoogleApiHelper;
import br.com.rbarrelo.clima.helpers.RetrofitHelper;
import br.com.rbarrelo.clima.modelos.CidadeAdapter;
import br.com.rbarrelo.clima.openweathermap.pojo.OpenWeatherMap;
import br.com.rbarrelo.clima.util.Commom;
import br.com.rbarrelo.clima.util.HTTPUtil;
import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HTTPUtil httpUtil = new HTTPUtil();
    private GoogleApiHelper googleApiHelper = new GoogleApiHelper();
    private GoogleApiClient googleApiClient;
    private RetrofitHelper retrofit = new RetrofitHelper();

    private TextView nome_cidade;
    private TextView temperatura;
    private TextView min_max;
    private AppBarLayout appBarLayout;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(MessageEvent event) {
        String lat = Float.toString(event.getCidade().getLatitude());
        String lon = Float.toString(event.getCidade().getLongitude());

        Call<OpenWeatherMap> call = retrofit.getOpenWeatherApi().getTemperatura(lat, lon, Commom.API_KEY);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Response<OpenWeatherMap> response, Retrofit retrofit) {
                OpenWeatherMap openWeatherMap = response.body();

                setBackgroudTemperatura(appBarLayout, ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                nome_cidade.setText(openWeatherMap.getName());
                temperatura.setText(Double.toString(openWeatherMap.getMain().getTemp()));
                String minMax = Double.toString(openWeatherMap.getMain().getTemp_min()) + " | " +
                                Double.toString(openWeatherMap.getMain().getTemp_max());
                min_max.setText(minMax);

                Snackbar.make(recyclerView, openWeatherMap.getName(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    @TargetApi(16)
    private void setBackgroudTemperatura(View view, int id){
        //view.setBackground(ContextCompat.getDrawable(MainActivity.this, id));
        ColorAnimation.animateBetweenColors(view, id, 800);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.nome_cidade = (TextView)findViewById(R.id.temp_cidade);
        this.temperatura = (TextView)findViewById(R.id.temp_atual);
        this.min_max = (TextView)findViewById(R.id.temp_min_max);
        this.appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        this.googleApiClient = googleApiHelper.getInstanceOfApiClient(this);
        this.ImplementaFAB();
        this.PopulaListaDeCidades();
    }

    private void ImplementaFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean showSnack = true;

                if (googleApiHelper.isConectado()) {
                    Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    if (location != null) {
                        showSnack = false;

                        Log.i(Commom.TAG, "Latitude: " + location.getLatitude());
                        Log.i(Commom.TAG, "Longitude: " + location.getLongitude());
                    }
                }

                if (showSnack) {
                    Snackbar.make(view, "Não foi possível identificar a localização atual", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void testeGSON() {
        Gson gson = new GsonBuilder().create();
        String json = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": -46.77,\n" +
                "    \"lat\": -23.63\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 803,\n" +
                "      \"main\": \"Clouds\",\n" +
                "      \"description\": \"broken clouds\",\n" +
                "      \"icon\": \"04d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 300.84,\n" +
                "    \"pressure\": 1021,\n" +
                "    \"humidity\": 73,\n" +
                "    \"temp_min\": 295.15,\n" +
                "    \"temp_max\": 302.59\n" +
                "  },\n" +
                "  \"visibility\": 10000,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 7.7,\n" +
                "    \"deg\": 180\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 75\n" +
                "  },\n" +
                "  \"dt\": 1445104800,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 1,\n" +
                "    \"id\": 4575,\n" +
                "    \"message\": 0.0146,\n" +
                "    \"country\": \"BR\",\n" +
                "    \"sunrise\": 1445070715,\n" +
                "    \"sunset\": 1445116381\n" +
                "  },\n" +
                "  \"id\": 3467722,\n" +
                "  \"name\": \"Campo Limpo\",\n" +
                "  \"cod\": 200\n" +
                "}";
        OpenWeatherMap omap = gson.fromJson(json, OpenWeatherMap.class);
    }

    private void PopulaListaDeCidades(){
        CidadeAdapter cidadeAdapter = new CidadeAdapter(this, httpUtil.getCidades());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); // otimizacao pois a lista nao muda de tamanho
        recyclerView.setAdapter(cidadeAdapter);
        recyclerView.addItemDecoration(new LineDividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
