package br.com.rbarrelo.clima;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.rbarrelo.clima.modelos.CidadeAdapter;
import br.com.rbarrelo.clima.util.HTTPUtil;
import br.com.rbarrelo.clima.util.LineDividerItemDecoration;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HTTPUtil httpUtil = new HTTPUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        recyclerView.setHasFixedSize(true); // otimizacao
        recyclerView.setAdapter(cidadeAdapter);
        recyclerView.addItemDecoration(new LineDividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
