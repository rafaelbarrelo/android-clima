package br.com.rbarrelo.clima.util;

import java.util.ArrayList;
import java.util.Random;

import br.com.rbarrelo.clima.R;
import br.com.rbarrelo.clima.modelos.simplificado.Cidade;
import br.com.rbarrelo.clima.modelos.simplificado.Temperatura;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class HTTPUtil {

    private final ArrayList<Cidade> cidades = new ArrayList<Cidade>();

    public HTTPUtil() {
        cidades.clear();
    }

    public Temperatura getTemperatura(float lat, float lon){
        Random rand = new Random();

        ArrayList<Cidade> cidades = getCidades();

        int i = rand.nextInt(cidades.size());
        if (i >= cidades.size()) i = 0;

        Temperatura temperatura = new Temperatura();
        temperatura.setAtual(23.1);
        temperatura.setBgColor(1);
        temperatura.setCidade(cidades.get(i));
        temperatura.setIcone("android");
        temperatura.setMaxima(33.1);
        temperatura.setMinima(21.5);

        return temperatura;
    }

    public ArrayList<Cidade> getCidades(){
        if (cidades.size() == 0){

            cidades.add(new Cidade("Rio Branco", "Acre", R.drawable.acre));
            cidades.add(new Cidade("Maceió", "Alagoas", R.drawable.alagoas));
            cidades.add(new Cidade("Macapá", "Amapá", R.drawable.amapa));
            cidades.add(new Cidade("Manaus", "Amazonas", R.drawable.amazonas));
            cidades.add(new Cidade("Salvador", "Bahia", R.drawable.bahia));
            cidades.add(new Cidade("Fortaleza", "Ceará", R.drawable.ceara));
            cidades.add(new Cidade("Vitória", "Espírito Santo", R.drawable.espirito_santo));
            cidades.add(new Cidade("Goiânia", "Goiás", R.drawable.goias));
            cidades.add(new Cidade("São Luís", "Maranhão", R.drawable.maranhao));
            cidades.add(new Cidade("Cuiabá", "Mato Grosso", R.drawable.mato_grosso));
            cidades.add(new Cidade("Campo Grande", "Mato Grosso do Sul",R.drawable.mato_grosso_do_sul));
            cidades.add(new Cidade("Belo Horizonte", "Minas Gerais", R.drawable.minas_gerais));
            cidades.add(new Cidade("Belém", "Pará", R.drawable.para));
            cidades.add(new Cidade("João Pessoa", "Paraíba", R.drawable.paraiba));
            cidades.add(new Cidade("Curitiba", "Paraná", R.drawable.parana));
            cidades.add(new Cidade("Recife", "Pernambuco", R.drawable.pernambuco));
            cidades.add(new Cidade("Teresina", "Piauí", R.drawable.piaui));
            cidades.add(new Cidade("Rio de Janeiro", "Rio de Janeiro", R.drawable.rio_de_janeiro));
            cidades.add(new Cidade("Natal", "Rio Grande do Norte", R.drawable.rio_grande_do_norte));
            cidades.add(new Cidade("Porto Alegre", "Rio Grande do Sul", R.drawable.rio_grande_do_sul));
            cidades.add(new Cidade("Porto Velho", "Rondônia", R.drawable.rondonia));
            cidades.add(new Cidade("Boa Vista", "Roraima", R.drawable.roraima));
            cidades.add(new Cidade("Florianópolis", "Santa Catarina", R.drawable.santa_catarina));
            cidades.add(new Cidade("São Paulo", "São Paulo", R.drawable.sao_paulo));
            cidades.add(new Cidade("Aracaju", "Sergipe", R.drawable.sergipe));
            cidades.add(new Cidade("Palmas", "Tocantins", R.drawable.tocantins));
            cidades.add(new Cidade("Brasília", "Distrito Federal", R.drawable.distrito_federal));
        }

        return cidades;
    }
}
