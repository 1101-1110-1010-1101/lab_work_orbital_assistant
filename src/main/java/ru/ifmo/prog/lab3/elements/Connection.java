package ru.ifmo.prog.lab3.elements;

import ru.ifmo.prog.lab3.CVSConstructor;

public class Connection {

    private Material material;
    private int density;
    private int meltingT;
    private int crystalT;

    public Connection(Material m, boolean console){
        this.material = m;
        density = (int)(Math.random()*2000 + 1000);
        meltingT = (int)(Math.random()*300 + 70);
        crystalT = (int)(Math.random()*-300 - 70);
        if (console)
        System.out.println("-- Соединение. " + this.material);
    }
    @CVSConstructor
    public Connection(Material m, int d, int mT, int cT){
        this.material = m;
        this.density = d;
        this.meltingT = mT;
        this.crystalT = cT;
    }
    @Override
    public int hashCode(){
        int result = 11;
        result = result*37 + density;
        result = result*37 + meltingT;
        result = result*37 + crystalT;
        result = result*37 + material.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Это соединение";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Connection other = (Connection) anoter;
            return other.material.equals(this.material) && other.crystalT == this.crystalT && other.density == this.density && other.meltingT == this.meltingT;
        }
        return false;
    }
}
