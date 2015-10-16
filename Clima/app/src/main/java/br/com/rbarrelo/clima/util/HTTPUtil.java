package br.com.rbarrelo.clima.util;

import java.util.ArrayList;
import java.util.Random;

import br.com.rbarrelo.clima.modelos.Cidade;
import br.com.rbarrelo.clima.modelos.Temperatura;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class HTTPUtil {

    public HTTPUtil() {
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
        ArrayList<Cidade> cidades = new ArrayList<Cidade>();

        for (int i = 0; i < 26; i++) {
            Cidade cidade = new Cidade();
            cidade.setNome("Cidade " + i);
            cidade.setEstado("Estado " + i);
            cidade.setLatitude(new Float(i + 30.3));
            cidade.setLongitude(new Float(i + 33.4));
        }
        
        return cidades;
    }
}
