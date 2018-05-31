package ru.ifmo.prog.lab3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.ifmo.prog.lab3.astronaut.Astronaut;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class User_Collection {
    private static List<Astronaut> astronauts = Collections.synchronizedList(new LinkedList<Astronaut>());
    private static final Date initTime = new Date();

    static String remove_last(Comparator<Astronaut> com){
        try{astronauts.sort(Main.com);
        String res = astronauts.stream().max(com).get().getName() + " removed from team";
        astronauts = astronauts.stream().limit(astronauts.size() - 1).collect(Collectors.toList());
        return res;} catch (NoSuchElementException e){return "Collection is empty";}
    }

    static String remove_first(){
        try{astronauts.sort(Main.com);
        String name = astronauts.stream().findFirst().get().getName() + " removed from team";
        astronauts = astronauts.stream().skip(1).collect(Collectors.toList());
        return name;} catch (NoSuchElementException e){return "Collection is empty";}
    }

    public static List<Astronaut> getAstronauts() {
        return astronauts;
    }

    static String load(String filename) {
        try {
            Astronaut.parseCSV(filename, astronauts);
            return "Data is loaded from: " + filename;
        } catch (IOException e) {return "Sorry, critical problem while loading collection";}
    }

    static String getNew(String command){
        try {
            String result = "";
        String[] parts = command.split(" ", 3);
        Astronaut a = new Astronaut(parts[2], false);
        result += new Gson().toJson(a) + "\n";
        if (parts[1].equals("y")){
            astronauts.add(a);
            result += "Adjunction! " + a.getName() + " added to team!";
        } else result += "";
        return result;} catch (ArrayIndexOutOfBoundsException e) {return "Command get_new requires 2 args";}
    }

    static String add(String com, boolean add){
        try{Astronaut astronaut = Main.astroJSONHandler(com, add);
            astronauts.add(astronaut);
            return "Adjunction! " + astronaut.getName() + " added to team!";} catch (IOException i){return "Sorry, IO Problem detected(";}catch (NoProtocolException n){
            return ("Invalid JSON");
        } catch (JsonSyntaxException j){return ("Invalid JSON");}
    }

    static String info(){
        String result = "";
        result +="Type: " + astronauts.getClass().getSimpleName()+"\nInitialization Date: " + initTime + "\nTeam size: " + astronauts.size();
        result += "\n*---Team---*";
        int i = 1;
        astronauts.sort(Main.com);
        for(Astronaut a: astronauts) {
            result +=("\n" + i + ")*-" + a.getName() + "-*\nExperience index: " + a.index() + "\nLocation: " + a.getCoordinates().toString() + "\nDistance from control center: " + a.distance());
            i++;
        }
        return result;
    }

    static String add_if_max(String com, Comparator<Astronaut> comp){
        try{Astronaut astroboy = Main.astronautFromJSON(com, true);
            astronauts.sort(comp);
            if (astroboy.comparing(astronauts.stream().findFirst().get())) {
                astronauts.add(astroboy);
                return ("Adjunction! " + astroboy.getName() + " added to team!");
            }else return null;
        } catch (NoProtocolException n){return ("Invalid JSON");}catch (JsonSyntaxException j){return ("Invalid JSON");}
    }

    static String remove_greater(String com) {
        try {
            String result = "";
            Astronaut astroboy = Main.astronautFromJSON(com, true);
            for (Astronaut a : astronauts)
                if (a.comparing(astroboy)) {
                    astronauts.remove(a);
                    result += (a.getName() + " leaves team\n");
                } else result += null;
        return result;
        } catch (NoProtocolException n) {
            return ("Invalid JSON");
        } catch (JsonSyntaxException j) {
            return ("Invalid JSON");
        }
    }


    static String help(){
         return "add {JSON element} - add element\nload - regenerate collection from file\nremove_first/remove_last - remove from collection first/last element\nadd_if_max {JSON element} - add element, if it is max for collection\nremove_greater {JSON element} - remove all elements that are bigger than 'element'\nget_new [y/n] 'Name' - generate new astronaut, add it to collection or no with name 'Name'\nexit - exit from program";
    }

    static String decode_message(String msg) {
        if (msg.equals("load")) {
            return load(Main.FILENAME); }
        else if (msg.equals("remove_last")){
            return remove_last(Main.com); }
        else if (msg.equals("remove_first")){
             return remove_first();
            }
        else if (msg.matches("add .*")) {
            return add(msg, true);
        } else if (msg.equals("info")) {
            return info();
        } else if (msg.matches("add_if_max .*")) {
            return add_if_max(msg, Main.com);
        } else if (msg.matches("remove_greater .*")) {
            return remove_greater(msg);
        } else if (msg.matches("get_new .*")) {
            return getNew(msg);
        }
        else if (msg.equals("help"))
            return help();
        else {
            return "Uknown command\nType 'help' to get a list of available commands";
        }
    }
}
