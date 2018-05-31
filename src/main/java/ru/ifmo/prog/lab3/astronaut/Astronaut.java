package ru.ifmo.prog.lab3.astronaut;

import ru.ifmo.prog.lab3.*;
import ru.ifmo.prog.lab3.elements.*;
import ru.ifmo.prog.lab3.spacesuit.SpaceSuit;
import ru.ifmo.prog.lab3.spacesuit.backpack.*;
import ru.ifmo.prog.lab3.spacesuit.cosmocombi.Combinezon;
import ru.ifmo.prog.lab3.spacesuit.helmet.GermoHelmet;
import ru.ifmo.prog.lab3.spacesuit.helmet.radio.Radio;
import ru.ifmo.prog.lab3.spacesuit.kosmoboots.Boots;
import ru.ifmo.prog.lab3.spacesuit.kosmoboots.KosmoBoots;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Astronaut implements Interaction, Serializable, Comparable<Astronaut>{

    @UserClass
    private SpaceSuit spaceSuit;
    private boolean batteryChecked;
    @UserClass
    private Electric electric;
    private State state;
    private String name;
    @UserClass
    private Coordinates coordinates;
    private Date initTime;

    @CVSConstructor
    public Astronaut(SpaceSuit spaceSuit, Electric electric, boolean batteryChecked, State state, String name, Coordinates coordinates, Date init){
        this.spaceSuit = spaceSuit;
        this.electric = electric;
        this.batteryChecked = batteryChecked;
        this.state = state;
        this.name = name;
        this.coordinates = coordinates;
        this.initTime = init;
    }

    @Override
    public int compareTo(Astronaut another){
        if (this.equals(another)) return 0;
        if (!this.comparing(another))
            return 1;
        else return -1;
    }

    public Astronaut(String name, boolean console){
        this.name = name;
        this.spaceSuit = new SpaceSuit(console);
        this.batteryChecked = false;
        this.electric = null;
        this.checkAllSystems(console);
        this.coordinates = new Coordinates();
        this.initTime = new Date();
    }



    public boolean comparing(Astronaut another){
            return this.distance() > another.distance();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double distance(){
        return sqrt(pow(this.coordinates.getX(), 2)+pow(this.coordinates.getY(), 2)+pow(this.coordinates.getZ(), 2));
    }

    public int index(){
        return this.electric.getPower() + this.spaceSuit.backpack().battery().getCharge() + this.spaceSuit.backpack().airPurifyingDevice().getKpd() + this.spaceSuit.backpack().airPurifyingDevice().getClas() + this.spaceSuit.backpack().lantern().getPower() + this.spaceSuit.backpack().parashute().getClas() + this.spaceSuit.backpack().parashute().getKpd() + this.spaceSuit.backpack().ventillatingDevice().getClas() + this.spaceSuit.backpack().ventillatingDevice().getKpd();
    }

    public String getName() {
        return name;
    }

    public void checkAllSystems(boolean console){
       this.electric = this.switchON(console);
       if (this.spaceSuit.germoHelmet().radio().isWorking(this.electric) && this.spaceSuit.backpack().check(this.electric)) {
           this.state = State.ALLRIGHT;
           if (console)
           System.out.println("#" + this.name + ": Все системы проверены");
       }
       else {
           this.state = State.NOT_ALLRIGHT;
           if (console)
           System.out.println("#" + this.name + ": Проблема с аппаратурой");
       }
    }

    public Electric switchON(boolean console){
        Electric e;
        if (console)
        System.out.println("#" + this.name + ": Батарея включена");
        e = this.spaceSuit.backpack().battery().isWorking();
        return e;
    }

    public static void parseCSV(String FILENAME, List astronauts) throws IOException {
        String[] args;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                args = str.split(",");

                Material material = Material.stringToMaterial(args[0]);
                if (material == null)
                    throw new NumberFormatException();
                int density = Integer.parseInt(args[1]);
                int melting = Integer.parseInt(args[2]);
                int crystal = Integer.parseInt(args[3]);
                Illuminator illuminator = new Illuminator(material, density, melting, crystal);

                float freq = Float.valueOf(args[4]);
                Radio radio = new Radio(freq);

                material = Material.stringToMaterial(args[5]);
                if (material == null)
                    throw new NumberFormatException();
                Material material1 = Material.stringToMaterial(args[6]);
                if (material1 == null)
                    throw new NumberFormatException();
                GermoHelmet germoHelmet = new GermoHelmet(illuminator, radio, material, material1);

                material = Material.stringToMaterial(args[7]);
                if (material == null)
                    throw new NumberFormatException();
                int resist = Integer.parseInt(args[8]);
                int elongation = Integer.parseInt(args[9]);
                Plate plate = new Plate(material, resist, elongation);

                material = Material.stringToMaterial(args[10]);
                if (material == null)
                    throw new NumberFormatException();
                resist = Integer.parseInt(args[11]);
                elongation = Integer.parseInt(args[12]);
                Ring ring = new Ring(material, resist, elongation);

                material = Material.stringToMaterial(args[13]);
                if (material == null)
                    throw new NumberFormatException();
                density = Integer.parseInt(args[14]);
                melting = Integer.parseInt(args[15]);
                crystal = Integer.parseInt(args[16]);
                Connection connection = new Connection(material, density, melting, crystal);

                Colors col = Colors.stringToColor(args[17]);
                if (col == null)
                    throw new NumberFormatException();
                Combinezon combinezon = new Combinezon(plate, ring, connection, col);

                col = Colors.stringToColor(args[18]);
                if (col == null)
                    throw new NumberFormatException();
                int size = Integer.parseInt(args[19]);
                Boots boots = new Boots(col, size);

                material = Material.stringToMaterial(args[20]);
                if (material == null)
                    throw new NumberFormatException();
                KosmoBoots kosmoBoots = new KosmoBoots(material, boots);

                int kpd = Integer.parseInt(args[21]);
                int clas = Integer.parseInt(args[22]);
                AirPurifyingDevice airPurifyingDevice = new AirPurifyingDevice(kpd, clas);

                int power = Integer.parseInt(args[23]);
                Lantern lantern = new Lantern(power);

                kpd = Integer.parseInt(args[24]);
                clas = Integer.parseInt(args[25]);
                VentillatingDevice ventillatingDevice = new VentillatingDevice(kpd, clas);

                int charge = Integer.parseInt(args[27]);
                boolean isCharged = Boolean.parseBoolean(args[26]);
                Battery battery = new Battery(isCharged, charge);

                kpd = Integer.parseInt(args[28]);
                clas = Integer.parseInt(args[29]);
                Parashute parashute = new Parashute(kpd, clas);

                Backpack backpack = new Backpack(airPurifyingDevice, lantern, ventillatingDevice, battery, parashute);

                resist = Integer.parseInt(args[30]);
                power = Integer.parseInt(args[31]);
                Electric electric = new Electric(resist, power);

                SpaceSuit spaceSuit = new SpaceSuit(germoHelmet, combinezon, kosmoBoots, backpack);
                double x = Double.parseDouble(args[32]);
                double y = Double.parseDouble(args[33]);
                double z = Double.parseDouble(args[34]);
                Coordinates coordinates = new Coordinates(x, y, z);
                boolean batteryChecked = Boolean.parseBoolean(args[35]);
                State state = State.stringToState(args[36]);
                if (state == null)
                    throw new NumberFormatException();

                DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
                try{Date init = format.parse(args[38]);
                Astronaut astronaut = new Astronaut(spaceSuit, electric, batteryChecked, state, args[37], coordinates, init);
                astronauts.add(astronaut);}catch (ParseException p){System.out.println("Wrong date format");}
            }
        } catch (FileNotFoundException f) {System.out.println("Файл не найден");}
        catch (NumberFormatException n){System.out.println("Неверный формат объекта. Для пропуска наберите 'p', для выхода - 'e'");

            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            String answer = read.readLine();
            if (answer.equals("e"))
                System.exit(1);
        }
    }


    @Override
    public void beginInteraction(RadioProtocol r) throws IOException {
        while(true)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("#GroundControl to #" + this.getName() + ": ");
                String mess = reader.readLine();
                if (mess.equals("Конец связи"))
                    break;
                else if (mess.equals("help")) {
                    System.out.println("Протокол " + r.getName() + ". Список доступных команд:");
                    System.out.println("*-----------------------------*");
                    for (int i = 0; i < r.getSize(); i++)
                        if (r.getHum(i))
                            System.out.println(r.getMes(i));
                        else System.out.print("");
                    System.out.println("*-----------------------------*");
                } else if (mess.equals("Доложите обстановку")) {
                    if (this.state == State.ALLRIGHT)
                        System.out.println("#" + this.name + " to #GroundControl: Все идет по плану");
                    else System.out.println("#" + this.name + " to #GroundControl: Проблемы с аппаратурой");
                } else this.hear(r, r.new Decoder().decode(mess));
            }
        }

    public void hear(RadioProtocol pr, int id) {
        try {
            if (id == 1)
                throw new NoProtocolException();
            else System.out.println("#" + this.name + " to #GroundControl: " + pr.getMes(id));
        } catch (NoProtocolException npe) {
            System.out.println("Недопустимая команда. Для справки по радиопротоколу наберите 'help'");
        }
    }



    @Override
    public int hashCode(){
        int result = 17;
        result = 37*result + spaceSuit.hashCode();
        result = 37*result + (batteryChecked ? 1 : 0);
        result = 37*result + electric.hashCode();
        result = 37*result + state.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Героический герой " + name;
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Astronaut other = (Astronaut) anoter;
            return other.state.equals(this.state) && other.name == this.name && other.electric.equals(this.electric) && other.batteryChecked == this.batteryChecked && other.spaceSuit.equals(this.spaceSuit);
        }
        return false;
    }
}
