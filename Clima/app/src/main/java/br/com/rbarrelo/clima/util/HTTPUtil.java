package br.com.rbarrelo.clima.util;

import java.util.ArrayList;
import java.util.Random;

import br.com.rbarrelo.clima.R;
import br.com.rbarrelo.clima.modelos.Cidade;
import br.com.rbarrelo.clima.modelos.Temperatura;

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
        temperatura.setBgColor("azul");
        temperatura.setCidade(cidades.get(i));
        temperatura.setIcone("android");
        temperatura.setMaxima(33.1);
        temperatura.setMinima(21.5);

        return temperatura;
    }

    public ArrayList<Cidade> getCidades(){
        if (cidades.size() == 0){

            //https://github.com/alanwillms/geoinfo/blob/master/Latitude%20e%20longitude%20das%20cidades%20do%20Brasil/latitude-longitude-cidades-brasil.csv
            cidades.add(new Cidade("Rio Branco", "Acre", new Float(-9.974990), new Float(-67.824348), R.drawable.acre));
            cidades.add(new Cidade("Maceió", "Alagoas", new Float(-9.665985), new Float(-35.734960), R.drawable.alagoas));
            cidades.add(new Cidade("Macapá", "Amapá", new Float(-0.034934), new Float(-51.069395), R.drawable.amapa));
            cidades.add(new Cidade("Manaus", "Amazonas", new Float(-3.118662), new Float(-60.021230), R.drawable.amazonas));
            cidades.add(new Cidade("Salvador", "Bahia", new Float(-12.971780), new Float(-38.501068), R.drawable.bahia));
            cidades.add(new Cidade("Fortaleza", "Ceará", new Float(-3.716638), new Float(-38.542298), R.drawable.ceara));
            cidades.add(new Cidade("Vitória", "Espírito Santo", new Float(-20.315472), new Float(-40.312806), R.drawable.espirito_santo));
            cidades.add(new Cidade("Goiânia", "Goiás", new Float(-16.686439), new Float(-49.264346), R.drawable.goias));
            cidades.add(new Cidade("São Luís", "Maranhão", new Float(-2.538742), new Float(-44.282513), R.drawable.maranhao));
            cidades.add(new Cidade("Cuiabá", "Mato Grosso", new Float(-15.600979), new Float(-56.097397), R.drawable.mato_grosso));
            cidades.add(new Cidade("Campo Grande", "Mato Grosso do Sul", new Float(-20.448589), new Float(-54.629463), R.drawable.mato_grosso_do_sul));
            cidades.add(new Cidade("Belo Horizonte", "Minas Gerais", new Float(-19.910183), new Float(-43.926572), R.drawable.minas_gerais));
            cidades.add(new Cidade("Belém", "Pará", new Float(-1.455396), new Float(-48.489756), R.drawable.para));
            cidades.add(new Cidade("João Pessoa", "Paraíba", new Float(-7.115090), new Float(-34.864121), R.drawable.paraiba));
            cidades.add(new Cidade("Curitiba", "Paraná", new Float(-25.419547), new Float(-49.264622), R.drawable.parana));
            cidades.add(new Cidade("Recife", "Pernambuco", new Float(-8.046658), new Float(-34.877065), R.drawable.pernambuco));
            cidades.add(new Cidade("Teresina", "Piauí", new Float(-5.091944), new Float(-42.803364), R.drawable.piaui));
            cidades.add(new Cidade("Rio de Janeiro", "Rio de Janeiro", new Float(-22.912897), new Float(-43.200295), R.drawable.rio_de_janeiro));
            cidades.add(new Cidade("Natal", "Rio Grande do Norte", new Float(-5.793567), new Float(-35.198604), R.drawable.rio_grande_do_norte));
            cidades.add(new Cidade("Porto Alegre", "Rio Grande do Sul", new Float(-30.031771), new Float(-51.206533), R.drawable.rio_grande_do_sul));
            cidades.add(new Cidade("Porto Velho", "Rondônia", new Float(-8.760772), new Float(-63.899902), R.drawable.rondonia));
            cidades.add(new Cidade("Boa Vista", "Roraima", new Float(-2.823842), new Float(-60.675328), R.drawable.roraima));
            cidades.add(new Cidade("Florianópolis", "Santa Catarina", new Float(-27.594486), new Float(-48.547696), R.drawable.santa_catarina));
            cidades.add(new Cidade("São Paulo", "São Paulo", new Float(-23.532905), new Float(-46.639520), R.drawable.sao_paulo));
            cidades.add(new Cidade("Aracaju", "Sergipe", new Float(-10.909133), new Float(-37.067660), R.drawable.sergipe));
            cidades.add(new Cidade("Palmas", "Tocantins", new Float(-26.483868), new Float(-51.988812), R.drawable.tocantins));
            cidades.add(new Cidade("Brasília", "Distrito Federal", new Float(-15.779522), new Float(-47.929657), R.drawable.distrito_federal));
        }

        return cidades;
    }
}
