package br.com.rbarrelo.clima.modelos;

/**
 * Created by rafaelbarrelo on 10/16/15.
 */
public class Temperatura {

    public enum TEMPERATURA_TIPO { KELVIN, CELSIUS, FAHRENHEIT}

    private double Minima;
    private double Maxima;
    private double Atual;
    private String Icone;
    private String BgColor;
    private Cidade Cidade;

    public Temperatura() {}

    public Temperatura(double minima, double maxima, double atual, String icone, String bgColor, br.com.rbarrelo.clima.modelos.Cidade cidade) {
        Minima = minima;
        Maxima = maxima;
        Atual = atual;
        Icone = icone;
        BgColor = bgColor;
        Cidade = cidade;
    }

    private double converteTemperatura(TEMPERATURA_TIPO tipo, double valor){

        if(tipo == TEMPERATURA_TIPO.CELSIUS){
            valor = valor * 1;
        }
        else if (tipo == TEMPERATURA_TIPO.FAHRENHEIT){
            valor = valor * 2;
        }

        return valor;
    }

    public double getMinima() {
        return getMinima(TEMPERATURA_TIPO.CELSIUS);
    }
    public double getMinima(TEMPERATURA_TIPO tipo) {
        return converteTemperatura(tipo, Minima) ;
    }

    public void setMinima(double minima) {
        Minima = minima;
    }

    public double getMaxima() {
        return getMaxima(TEMPERATURA_TIPO.CELSIUS);
    }
    public double getMaxima(TEMPERATURA_TIPO tipo) {
        return converteTemperatura(tipo, Maxima);
    }

    public void setMaxima(double maxima) {
        Maxima = maxima;
    }

    public double getAtual() {
        return getAtual(TEMPERATURA_TIPO.CELSIUS);
    }
    public double getAtual(TEMPERATURA_TIPO tipo) {
        return converteTemperatura(tipo, Atual);
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

    public String getBgColor() {
        return BgColor;
    }

    public void setBgColor(String bgColor) {
        BgColor = bgColor;
    }

    public br.com.rbarrelo.clima.modelos.Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(br.com.rbarrelo.clima.modelos.Cidade cidade) {
        Cidade = cidade;
    }
}
