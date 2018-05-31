package ru.ifmo.prog.lab3.elements;

import ru.ifmo.prog.lab3.CVSConstructor;

public class Plate {

    private Material material;
    private int resist;
    private int elongation;

    public Plate(Material m, boolean console){
        resist = (int)(Math.random()*50 + 20);
        elongation = (int)(Math.random()*10 + 2);
        this.material = m;
        if (console)
        System.out.println("-- Пластина. " + this.material);
    }
    @CVSConstructor
    public Plate(Material m, int r, int e){
        this.material = m;
        this.resist = r;
        this.elongation = e;
    }
    @Override
    public int hashCode(){
        int result = 19;
        result = result*37 + resist;
        result = result*37 + elongation;
        result = result*37 + material.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Прочная, как пластина!";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Plate other = (Plate) anoter;
            return other.resist == this.resist && other.elongation == this.elongation && other.material.equals(this.material);
        }
        return false;
    }
}
