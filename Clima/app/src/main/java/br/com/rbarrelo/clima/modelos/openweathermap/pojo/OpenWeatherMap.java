package br.com.rbarrelo.clima.modelos.openweathermap.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class OpenWeatherMap {

    private Coord coord;
    private List<Weather> weather = new ArrayList<Weather>();
    private String base;
    private OpenWeatherMain main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private OpenWeatherSys sys;
    private int id;
    private String name;
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public OpenWeatherMain getMain() {
        return main;
    }

    public void setMain(OpenWeatherMain main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public OpenWeatherSys getSys() {
        return sys;
    }

    public void setSys(OpenWeatherSys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
