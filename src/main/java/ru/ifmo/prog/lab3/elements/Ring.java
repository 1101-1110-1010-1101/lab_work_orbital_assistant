package ru.ifmo.prog.lab3.elements;

import ru.ifmo.prog.lab3.CVSConstructor;

public class Ring {

    private Material material;
    private int resist;
    private int elongation;

    public Ring(Material m, boolean console){
        this.material = m;
        resist = (int)(Math.random()*70 + 20);
        elongation = (int)(Math.random()*5 + 2);
        if (console)
        System.out.println("-- Кольца. " + this.material);
    }
    @CVSConstructor
    public Ring(Material m, int r, int e){
        this.material = m;
        this.resist = r;
        this.elongation = e;
    }
    @Override
    public int hashCode(){
        int result = 23;
        result = result*37 + resist;
        result = result*37 + elongation;
        return result;
    }
    @Override
    public String toString(){
        return "Колечко";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Ring other = (Ring) anoter;
            return other.elongation == this.elongation && other.resist == this.resist && other.material.equals(this.material);
        }
        return false;
    }
}
