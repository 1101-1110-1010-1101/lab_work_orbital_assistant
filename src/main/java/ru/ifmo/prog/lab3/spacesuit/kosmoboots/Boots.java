package ru.ifmo.prog.lab3.spacesuit.kosmoboots;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.Colors;

public class Boots {
    private Colors color;
    private int size;

    @CVSConstructor
    public Boots(Colors col, int size){
        this.color = col;
        this.size = size;
    }

    public Boots(){
        float a = (float)Math.random();
        if (a < 0.25f)
            this.color = Colors.Красный;
        else if (a < 0.5f)
            this.color = Colors.Синий;
        else if (a < 0.75f)
            this.color = Colors.Желтый;
        else this.color = Colors.Зеленый;

        float b = (float)Math.random();
        this.size = (int)b*14 + 32;
    }

    @Override
    public int hashCode(){
        int result = 13;
        result = result*37 + color.hashCode();
        result = result*37 + size;
        return result;
    }
    @Override
    public String toString(){
        return "Обычные ботинки";
    }
    @Override
    public boolean equals(Object anoter) {
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Boots other = (Boots) anoter;
            return other.size == this.size && other.color.equals(this.color);
        }
        return false;
    }
}
