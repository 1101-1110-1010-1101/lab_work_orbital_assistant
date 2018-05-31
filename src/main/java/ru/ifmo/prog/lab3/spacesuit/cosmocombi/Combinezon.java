package ru.ifmo.prog.lab3.spacesuit.cosmocombi;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.Colors;
import ru.ifmo.prog.lab3.UserClass;
import ru.ifmo.prog.lab3.elements.Material;
import ru.ifmo.prog.lab3.elements.Connection;
import ru.ifmo.prog.lab3.elements.Plate;
import ru.ifmo.prog.lab3.elements.Ring;

public class Combinezon {
    @UserClass
    private Plate plate;
    @UserClass
    private Ring ring;
    @UserClass
    private Connection connection;
    private Colors color;

    @CVSConstructor
    public Combinezon(Plate p, Ring r, Connection c, Colors col){
        this.plate = p;
        this.ring = r;
        this.connection = c;
        this.color = col;
    }

    private void setColor(){
        float a = (float)Math.random();
        if (a < 0.25f)
            this.color = Colors.Красный;
        else if (a < 0.5f)
            this.color = Colors.Синий;
        else if (a < 0.75f)
            this.color = Colors.Желтый;
        else this.color = Colors.Зеленый;
    }

    public Combinezon(boolean console){
        this.setColor();
        if (console)
        System.out.println("- " + this.color + " космический комбинезон из:");
        this.connection = new Connection(Material.Космопластмасса, console);
        this.plate = new Plate(Material.Металл, console);
        this.ring = new Ring(Material.Металл, console);
    }
    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + ring.hashCode();
        result = result*37 + plate.hashCode();
        result = result*37 + connection.hashCode();
        result = result*37 + color.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Модный " + color + " комбинезон";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Combinezon other = (Combinezon) anoter;
            return other.color.equals(this.color) && other.connection.equals(this.connection) && other.plate.equals(this.plate) && other.ring.equals(this.ring);
        }
        return false;
    }
}
