package br.com.rbarrelo.clima.modelos.simplificado;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.rbarrelo.clima.R;
import br.com.rbarrelo.clima.modelos.openweathermap.pojo.OpenWeatherMap;
import br.com.rbarrelo.clima.util.Commom;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class Temperatura {

    public enum TEMPERATURA_TIPO { CELSIUS, FAHRENHEIT}

    private  TEMPERATURA_TIPO tipo;
    private double Minima;
    private double Maxima;
    private double Atual;
    private String Icone;
    private int BgColor;
    private String Condicao;
    private Cidade Cidade;

    private Date LastUse;
    private static Map<String, Integer> mapBgColor = new HashMap<String, Integer>();

    public Temperatura() {}

    public Temperatura(OpenWeatherMap openWeatherMap, Cidade cidade){
        this.Minima = openWeatherMap.getMain().getTemp_min();
        this.Maxima = openWeatherMap.getMain().getTemp_max();
        this.Atual = openWeatherMap.getMain().getTemp();
        this.Icone = openWeatherMap.getWeather().get(0).getIcon();
        this.Condicao = openWeatherMap.getWeather().get(0).getMain();
        this.BgColor = identificaBgColor(Condicao);
        this.Cidade = cidade;
        this.LastUse = new Date();
        this.tipo = TEMPERATURA_TIPO.CELSIUS;
    }

    public static int identificaBgColor(String condicao){

        if (mapBgColor.size() == 0){
            mapBgColor.put("clear", R.color.clear);
            mapBgColor.put("rain", R.color.rain);
            mapBgColor.put("clouds", R.color.clouds);
            mapBgColor.put("fog", R.color.fog);
            mapBgColor.put("mist", R.color.mist);
            mapBgColor.put("haze", R.color.haze);
            mapBgColor.put("snow", R.color.snow);
            mapBgColor.put("exteme", R.color.exteme);
        }

        if (mapBgColor.containsKey(condicao.toLowerCase())){
            return mapBgColor.get(condicao.toLowerCase());
        }
        else{
            Log.i(Commom.TAG, "identificaBgColor: " + condicao.toLowerCase());
            return R.color.colorPrimary;
        }
    }

    public  String getCondicao() { return this.Condicao; }

    public TEMPERATURA_TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TEMPERATURA_TIPO tipo) {
        this.tipo = tipo;
    }

    public String getLetra(){
        return getTipo().name().substring(0, 1).toUpperCase();
    }

    //http://www.rapidtables.com/convert/temperature/how-kelvin-to-celsius.htm
    private int converteTemperatura(double valor){

        if(tipo == TEMPERATURA_TIPO.CELSIUS){
            //T(°C) = T(K) - 273.15
            valor = valor - 273.15;
        }
        else if (tipo == TEMPERATURA_TIPO.FAHRENHEIT){
            //T(°F) = T(K) × 9/5 - 459.67
            valor = valor * 1.8 - 459.67;
        }

        return ((int) Math.round(valor));
    }

    public int getMinima() {
        return converteTemperatura(Minima) ;
    }

    public void setMinima(double minima) {
        Minima = minima;
    }

    public int getMaxima() {
        return converteTemperatura(Maxima);
    }

    public void setMaxima(double maxima) {
        Maxima = maxima;
    }

    public int getAtual() {
        return converteTemperatura(Atual);
    }

    public void setAtual(double atual) {
        Atual = atual;
    }

    public String getIcone() {
        return Icone;
    }

    public void setIcone(String icone) {
        Icone = icone;
    }

    public int getBgColor() {
        return BgColor;
    }

    public void setBgColor(int bgColor) {
        BgColor = bgColor;
    }

    public br.com.rbarrelo.clima.modelos.simplificado.Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(br.com.rbarrelo.clima.modelos.simplificado.Cidade cidade) {
        Cidade = cidade;
    }

    public Date getLastUse() {
        return LastUse;
    }

    public void setLastUse(Date lastUse) {
        LastUse = lastUse;
    }

}
