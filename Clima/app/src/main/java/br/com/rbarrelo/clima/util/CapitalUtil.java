package br.com.rbarrelo.clima.util;

import java.util.ArrayList;

import br.com.rbarrelo.clima.R;
import br.com.rbarrelo.clima.modelos.simplificado.Cidade;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class CapitalUtil {

    private final ArrayList<Cidade> capitais = new ArrayList<Cidade>();

    public CapitalUtil() {
        capitais.clear();
    }

    public ArrayList<Cidade> getCapitais(){
        if (capitais.size() == 0){

            capitais.add(new Cidade("Rio Branco", "Acre", R.drawable.acre));
            capitais.add(new Cidade("Maceió", "Alagoas", R.drawable.alagoas));
            capitais.add(new Cidade("Macapá", "Amapá", R.drawable.amapa));
            capitais.add(new Cidade("Manaus", "Amazonas", R.drawable.amazonas));
            capitais.add(new Cidade("Salvador", "Bahia", R.drawable.bahia));
            capitais.add(new Cidade("Fortaleza", "Ceará", R.drawable.ceara));
            capitais.add(new Cidade("Vitória", "Espírito Santo", R.drawable.espirito_santo));
            capitais.add(new Cidade("Goiânia", "Goiás", R.drawable.goias));
            capitais.add(new Cidade("São Luís", "Maranhão", R.drawable.maranhao));
            capitais.add(new Cidade("Cuiabá", "Mato Grosso", R.drawable.mato_grosso));
            capitais.add(new Cidade("Campo Grande", "Mato Grosso do Sul",R.drawable.mato_grosso_do_sul));
            capitais.add(new Cidade("Belo Horizonte", "Minas Gerais", R.drawable.minas_gerais));
            capitais.add(new Cidade("Belém", "Pará", R.drawable.para));
            capitais.add(new Cidade("João Pessoa", "Paraíba", R.drawable.paraiba));
            capitais.add(new Cidade("Curitiba", "Paraná", R.drawable.parana));
            capitais.add(new Cidade("Recife", "Pernambuco", R.drawable.pernambuco));
            capitais.add(new Cidade("Teresina", "Piauí", R.drawable.piaui));
            capitais.add(new Cidade("Rio de Janeiro", "Rio de Janeiro", R.drawable.rio_de_janeiro));
            capitais.add(new Cidade("Natal", "Rio Grande do Norte", R.drawable.rio_grande_do_norte));
            capitais.add(new Cidade("Porto Alegre", "Rio Grande do Sul", R.drawable.rio_grande_do_sul));
            capitais.add(new Cidade("Porto Velho", "Rondônia", R.drawable.rondonia));
            capitais.add(new Cidade("Boa Vista", "Roraima", R.drawable.roraima));
            capitais.add(new Cidade("Florianópolis", "Santa Catarina", R.drawable.santa_catarina));
            capitais.add(new Cidade("São Paulo", "São Paulo", R.drawable.sao_paulo));
            capitais.add(new Cidade("Aracaju", "Sergipe", R.drawable.sergipe));
            capitais.add(new Cidade("Palmas", "Tocantins", R.drawable.tocantins));
            capitais.add(new Cidade("Brasília", "Distrito Federal", R.drawable.distrito_federal));
        }

        return capitais;
    }
}
