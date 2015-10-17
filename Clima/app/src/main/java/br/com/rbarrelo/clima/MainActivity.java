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
import android.view.View;
import android.widget.TextView;

import br.com.rbarrelo.clima.design.ColorAnimation;
import br.com.rbarrelo.clima.modelos.CidadeAdapter;
import br.com.rbarrelo.clima.util.HTTPUtil;
import br.com.rbarrelo.clima.design.LineDividerItemDecoration;
import br.com.rbarrelo.clima.eventbus.MessageEvent;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HTTPUtil httpUtil = new HTTPUtil();

    private TextView nome_cidade;
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
        String nome = event.getCidade().getNome();

        this.setBackgroudTemperatura(this.appBarLayout, ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
        this.nome_cidade.setText(nome);

        Snackbar.make(recyclerView, nome, Snackbar.LENGTH_LONG).show();
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
        this.appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        this.ImplementaFAB();
        this.PopulaListaDeCidades();
    }

    private void ImplementaFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
