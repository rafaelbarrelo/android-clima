package br.com.rbarrelo.clima;

import android.annotation.TargetApi;
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

import java.util.HashMap;
import java.util.Map;

import br.com.rbarrelo.clima.design.ColorAnimation;
import br.com.rbarrelo.clima.design.LineDividerItemDecoration;
import br.com.rbarrelo.clima.eventbus.MessageEvent;
import br.com.rbarrelo.clima.helpers.GoogleApiHelper;
import br.com.rbarrelo.clima.helpers.RetrofitHelper;
import br.com.rbarrelo.clima.modelos.Cidade;
import br.com.rbarrelo.clima.modelos.CidadeAdapter;
import br.com.rbarrelo.clima.modelos.Temperatura;
import br.com.rbarrelo.clima.openweathermap.pojo.OpenWeatherMap;
import br.com.rbarrelo.clima.util.Commom;
import br.com.rbarrelo.clima.util.HTTPUtil;
import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private HTTPUtil httpUtil = new HTTPUtil();
    private GoogleApiHelper googleApiHelper;
    private RetrofitHelper retrofit = new RetrofitHelper();

    private TextView tvNomeCidade;
    private TextView tvTemperatura;
    private TextView tvMinMax;
    private TextView tvFC;
    private FloatingActionButton fab;
    private AppBarLayout appBarLayout;

    private Map<Integer, Temperatura> temperaturas = new HashMap<Integer, Temperatura>();
    private Temperatura current;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.googleApiHelper = new GoogleApiHelper(this);
        this.ConstroiElementosDaTela();
    }

    @Override
    public void onClick(View view) {
        if (current != null) {
            if (current.getTipo() == Temperatura.TEMPERATURA_TIPO.CELSIUS) {
                current.setTipo(Temperatura.TEMPERATURA_TIPO.FAHRENHEIT);
            } else {
                current.setTipo(Temperatura.TEMPERATURA_TIPO.CELSIUS);
            }
            ExibeTemperatura(current);
        }
    }

    private void ConstroiElementosDaTela() {
        this.tvNomeCidade = (TextView) findViewById(R.id.temp_cidade);
        this.tvMinMax = (TextView) findViewById(R.id.temp_min_max);

        this.tvTemperatura = (TextView) findViewById(R.id.temp_atual);
        this.tvTemperatura.setOnClickListener(this);

        this.tvFC = (TextView) findViewById(R.id.temp_tipo);
        this.tvFC.setOnClickListener(this);

        this.appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(googleApiHelper);

        this.PopulaListaDeCidades();
    }

    private void PopulaListaDeCidades() {
        CidadeAdapter cidadeAdapter = new CidadeAdapter(this, httpUtil.getCidades());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); // otimizacao pois a lista nao muda de tamanho
        recyclerView.setAdapter(cidadeAdapter);
        recyclerView.addItemDecoration(new LineDividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Tratamento do EventBus
    public void onEventMainThread(final MessageEvent event) {
        if (temperaturas.containsKey(event.getCidade().hashCode())) {
            Log.i(Commom.TAG, "onEventMainThread - from cache: " + event.getCidade().hashCode());
            UpdateViewTemp(temperaturas.get(event.getCidade().hashCode()));
        } else {
            String query_string = event.getCidade().getQueryString();
            Log.i(Commom.TAG, "onEventMainThread: " + query_string);
            CallAsyncRequest(query_string, event.getCidade());
        }
    }

    //Chamada do Retrofit
    private void CallAsyncRequest(String query_string, final Cidade cidade) {
        Call<OpenWeatherMap> call = retrofit
                .getOpenWeatherApi()
                .getTemperatura(query_string, Commom.API_KEY);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Response<OpenWeatherMap> response, Retrofit retrofit) {
                OpenWeatherMap openWeatherMap = response.body();
                Temperatura temperatura = new Temperatura(openWeatherMap, cidade);
                temperaturas.put(cidade.hashCode(), temperatura);

                UpdateViewTemp(temperatura);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(Commom.TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void UpdateViewTemp(Temperatura temperatura) {
        current = temperatura;
        setBackgroudTemperatura(appBarLayout,
                ContextCompat.getColor(MainActivity.this,
                        temperatura.getBgColor()));

        tvNomeCidade.setText(temperatura.getCidade().getNome());
        ExibeTemperatura(temperatura);

        String mensagem = temperatura.getCidade().getNome() + " (" + temperatura.getCondicao() + ")";
        Snackbar.make(recyclerView, mensagem, Snackbar.LENGTH_LONG).show();
    }

    private void ExibeTemperatura(Temperatura temperatura) {
        tvTemperatura.setText(Integer.toString(temperatura.getAtual()) + "º");

        String letra = temperatura.getLetra();
        tvFC.setText(letra);
        String minMax = "min. " + Integer.toString(temperatura.getMinima()) + "º" + letra +
                " | máx. " + Integer.toString(temperatura.getMaxima()) + "º" + letra;
        tvMinMax.setText(minMax);
    }

    @TargetApi(16)
    private void setBackgroudTemperatura(View view, int id) {
        //view.setBackground(ContextCompat.getDrawable(MainActivity.this, id));
        ColorAnimation.animateBetweenColors(view, id, 900);
    }

}

