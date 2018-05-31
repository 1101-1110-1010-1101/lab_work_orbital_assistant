package ru.ifmo.prog.lab3.spacesuit.helmet;

abstract public class Helmet {
    private int size;
    private int mas;
    public Helmet(){
        size = (int)(Math.random()*50);
        mas = (int)(Math.random()*10);
    }

    @Override
    public int hashCode(){
        int result = 19;
        result = result*37 + size;
        result = result*37 + mas;
        return result;
    }
    @Override
    public String toString(){
        return "Шлем. Просто шлем.";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Helmet other = (Helmet) anoter;
            return other.size == this.size && other.mas == this.mas;
        }
        return false;
    }
}
