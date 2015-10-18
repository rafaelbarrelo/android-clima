package br.com.rbarrelo.clima.modelos.simplificado;

/**
 * Created by rafaelbarrelo on 10/15/15.
 */
public class Cidade {

    private String Nome;
    private String Estado;
    private int Bandeira;
    private String Pais;
    private int myHash;

    public Cidade() {}

    public Cidade(String nome, String estado, String pais, int bandeira){
        Nome = nome;
        Estado = estado;
        Pais = pais;
        Bandeira = bandeira;
    }

    public Cidade(String nome, String estado) { this(nome, estado, 0); }

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
        if(myHash == 0){
            String hash = getNome() + getEstado() + getPais();
            byte[] bytes = hash.getBytes();

            myHash = getBandeira();
            for (int i = 0; i < bytes.length; i++) {
                myHash += bytes[i] * i;
            }
        }

        return myHash;
    }
}
