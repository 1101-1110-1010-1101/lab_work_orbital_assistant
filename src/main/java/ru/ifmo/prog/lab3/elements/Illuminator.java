package ru.ifmo.prog.lab3.elements;

import ru.ifmo.prog.lab3.CVSConstructor;

public class Illuminator {

    private Material material;
    private int density;
    private int meltingT;
    private int crystalT;

    public Illuminator(Material m, boolean console){
        if (console)
        System.out.println("-- Иллюминатор:");
        this.material = m;
        if (console)
        System.out.println("--- " + this.material);
        density = (int)(Math.random()*1000 + 1000);
        meltingT = (int)(Math.random()*200 + 70);
        crystalT = (int)(Math.random()*-200 - 70);
    }
    @CVSConstructor
    public Illuminator(Material m, int density, int meltingT, int crystalT){
        this.material = m;
        this.density = density;
        this.meltingT = meltingT;
        this.crystalT = crystalT;
    }
    @Override
    public int hashCode(){
        int result = 31;
        result = result*37 + density;
        result = result*37 + meltingT;
        result = result*37 + crystalT;
        result = result*37 + material.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Иллюминатор";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Illuminator other = (Illuminator) anoter;
            return other.material.equals(this.material) && other.crystalT == this.crystalT && other.meltingT == this.meltingT && other.density == this.density;
        }
        return false;
    }
}
