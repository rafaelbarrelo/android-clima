package br.com.rbarrelo.clima.modelos;

/**
 * Created by rafaelbarrelo on 10/15/15.
 */
public class Cidade {

    private String Nome;
    private String Estado;
    private float Latitude;
    private float Longitude;
    private int Bandeira;

    public Cidade() {}

    public Cidade(String nome, String estado, float latitude, float longitude, int bandeira) {
        Nome = nome;
        Estado = estado;
        Latitude = latitude;
        Longitude = longitude;
        Bandeira = bandeira;
    }

    public String getNome() {
        return Nome;
    }

    public String getEstado() {
        return Estado;
    }

    public float getLatitude() {
        return Latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setLatitude(float latitude) { Latitude = latitude; }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public int getBandeira() { return Bandeira; }

    public void setBandeira(int bandeira) { Bandeira = bandeira; }

}
