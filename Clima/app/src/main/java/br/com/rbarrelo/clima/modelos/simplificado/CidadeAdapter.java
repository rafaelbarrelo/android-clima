package br.com.rbarrelo.clima.modelos.simplificado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import br.com.rbarrelo.clima.R;
import br.com.rbarrelo.clima.util.CapitalUtil;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class CidadeAdapter extends RecyclerView.Adapter<CidadeViewHolder> {

    private Context context;
    private ArrayList<Cidade> items;
    private CapitalUtil capitalUtil = new CapitalUtil();

    public CidadeAdapter(Context context, ArrayList<Cidade> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public CidadeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cidade, parent, false);
        CidadeViewHolder viewHolder = new CidadeViewHolder(context, view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CidadeViewHolder viewHolder, int position) {
        Cidade cidade = items.get(position);
        viewHolder.Nome.setText(cidade.getNome());
        viewHolder.Estado.setText(cidade.getEstado());
        viewHolder.Imagem.setImageResource(cidade.getBandeira());
        viewHolder.cidade = capitalUtil.getCapitais().get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}