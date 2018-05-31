package ru.ifmo.prog.lab3;

public class Coordinates {
    private double x;
    private double y;
    private double z;

    public Coordinates (double x1, double y1, double z1){
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    public Coordinates(){
        this.x = Math.random()*1000;
        this.y = Math.random()*1000;
        this.z = Math.random()*1000;
    }

    public void setCoors(double x1, double y1, double z1){
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    @Override
    public String toString(){
        return this.x + "; " + this.y + "; " + this.z;
    }
}
