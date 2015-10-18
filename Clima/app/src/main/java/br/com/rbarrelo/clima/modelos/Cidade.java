package br.com.rbarrelo.clima.modelos;

/**
 * Created by rafaelbarrelo on 10/15/15.
 */
public class Cidade {

    private String Nome;
    private String Estado;
    private int Bandeira;

    private String Pais;

    public Cidade() {}

    public Cidade(String nome, String estado, String pais, int bandeira){
        Nome = nome;
        Estado = estado;
        Pais = pais;
        Bandeira = bandeira;
    }

    public Cidade(String nome, String estado, int bandeira){
        this(nome, estado, "br", bandeira);
    }

    public String getNome() {
        return Nome;
    }

    public String getEstado() {
        return Estado;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getBandeira() { return Bandeira; }

    public void setBandeira(int bandeira) { Bandeira = bandeira; }

    public String getQueryString(){
        return getNome() + "," + getPais().toLowerCase();
    }

    @Override
    public int hashCode() {
        return getBandeira();
    }
}
