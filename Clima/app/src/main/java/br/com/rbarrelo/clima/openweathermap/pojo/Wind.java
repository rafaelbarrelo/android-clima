package br.com.rbarrelo.clima.openweathermap.pojo;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class Wind {

    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
