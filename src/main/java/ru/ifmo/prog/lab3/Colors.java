package ru.ifmo.prog.lab3;

public enum Colors { Красный, Синий, Зеленый, Желтый;
    public static Colors stringToColor(String s){
    if (s.equals("Красный"))
        return Colors.Красный;
    else if (s.equals("Синий"))
        return Colors.Синий;
    else if (s.equals("Зеленый"))
        return Colors.Зеленый;
    else if (s.equals("Желтый"))
        return Colors.Желтый;
    else return null;
    }
}
