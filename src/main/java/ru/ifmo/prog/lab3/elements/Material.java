package ru.ifmo.prog.lab3.elements;

public enum Material { Металл, Дерево, Космопластмасса, Сталь, Стекло, Утеплитель;
    public static Material stringToMaterial(String s){
        if (s.equals("Металл"))
            return Material.Металл;
        else if (s.equals("Дерево"))
            return Material.Дерево;
        else if (s.equals("Космопластмасса"))
            return Material.Космопластмасса;
        else if (s.equals("Сталь"))
            return Material.Сталь;
        else if (s.equals("Стекло"))
            return Material.Стекло;
        else if (s.equals("Утеплитель"))
            return Material.Утеплитель;
        else return null;
    }
}
