package ru.ifmo.prog.lab3.spacesuit;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.UserClass;
import ru.ifmo.prog.lab3.elements.Material;
import ru.ifmo.prog.lab3.spacesuit.backpack.Backpack;
import ru.ifmo.prog.lab3.spacesuit.cosmocombi.Combinezon;
import ru.ifmo.prog.lab3.spacesuit.helmet.GermoHelmet;
import ru.ifmo.prog.lab3.spacesuit.kosmoboots.KosmoBoots;

public class SpaceSuit {
    @UserClass
    private GermoHelmet germoHelmet;
    @UserClass
    private Combinezon combinezon;
    @UserClass
    private KosmoBoots boots;
    @UserClass
    private Backpack backpack;


    public GermoHelmet germoHelmet() {
        return germoHelmet;
    }


    public Backpack backpack() {
        return backpack;
    }


    public SpaceSuit(boolean console) {
        if (console)
        System.out.println("Скафандр состоит из: ");
        this.boots = new KosmoBoots(Material.Сталь, console);
        this.combinezon = new Combinezon(console);
        this.germoHelmet = new GermoHelmet(Material.Космопластмасса, Material.Сталь, console);
        this.backpack = new Backpack(console);
    }

    @CVSConstructor
    public SpaceSuit(GermoHelmet g, Combinezon c, KosmoBoots k, Backpack b){
        this.backpack = b;
        this.boots = k;
        this.combinezon = c;
        this.germoHelmet = g;
    }

    @Override
    public int hashCode(){
        int result = 19;
        result = 37*result + germoHelmet.hashCode();
        result = 37*result + combinezon.hashCode();
        result = 37*result + boots.hashCode();
        result = 37*result + backpack.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Стильный космический костюм";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            SpaceSuit other = (SpaceSuit) anoter;
            return other.boots.equals(this.boots) && other.backpack.equals(this.backpack) && other.germoHelmet.equals(this.germoHelmet) && other.combinezon.equals(this.combinezon);
        }
        return false;
    }
}
