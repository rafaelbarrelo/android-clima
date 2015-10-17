package br.com.rbarrelo.clima.modelos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.rbarrelo.clima.R;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class CidadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context context;
    public TextView Nome;
    public TextView Estado;
    public ImageView Imagem;
    public int Position;

    public CidadeViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        Nome = (TextView) itemView.findViewById(R.id.cidade_nome);
        Estado = (TextView) itemView.findViewById(R.id.cidade_estado);
        Imagem = (ImageView) itemView.findViewById(R.id.img_cidade);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, Nome.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
