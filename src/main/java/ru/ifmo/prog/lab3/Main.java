package ru.ifmo.prog.lab3;

import javafx.stage.Stage;
import ru.ifmo.prog.lab3.astronaut.*;


import java.io.*;
import java.util.*;
import com.google.gson.*;



public class Main {
    static int port = Integer.parseInt(System.getenv("PORT"));
    public static final String FILENAME = System.getenv("LINKEDFILE");
    static ru.ifmo.prog.lab3.RadioProtocol RaProt1 = new RadioProtocol("RaProt1");
    static Comparator<Astronaut> com = new Comparator<Astronaut>() {
        @Override
        public int compare(Astronaut o1, Astronaut o2) {
            if (o1.equals(o2)) return 0;
            if (!o1.comparing(o2))
                return 1;
            else return -1;
        }
    };

    public static void main(String[] args) throws IOException {

        LoginForm login = new LoginForm();
        login.start(new Stage());
        System.out.println("Server is running");
        LinkedList<String> offline = new LinkedList<>();
        //BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        if (FILENAME == null) {
            System.out.println("Для запуска программы укажите имя файла");
            System.exit(1);
        }
        final User_Collection astronauts = new User_Collection();
        Astronaut.parseCSV(FILENAME, astronauts.getAstronauts());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            clear(FILENAME);
            for (Astronaut as : astronauts.getAstronauts()) CSV_Handler.writeCSV(as, FILENAME);
            System.out.println("Изменения сохранены");
        }));

        NetworkDataHandler connection = new NetworkDataHandler(port);
        while (true) {
            String data = connection.get_data();
            new Thread(() -> connection.send_data(astronauts.decode_message(data))).run();
        }
    }


    public static void clear(String filepath) {
        try {
            FileWriter fstream = new FileWriter(filepath);
            BufferedWriter out1 = new BufferedWriter(fstream);
            out1.write("");
            out1.close();
        } catch (IOException io) {
            System.err.println("Ошибка ввода/вывода");
        }
    }

    public static Astronaut astronautFromJSON(String msg, boolean add) throws NoProtocolException {
        String[] msgs = msg.split(" ", 1);
        int i = 0;
        if (add)
            i = 1;
        if (msgs[i].equals("{}"))
            throw new NoProtocolException();

        Gson gson = new Gson();
        return gson.fromJson(msgs[i], Astronaut.class);
    }

    public static Astronaut astroJSONHandler(String com, boolean add) throws IOException, NoProtocolException {
        String str;
        int leftCount = com.length() - com.replace("{", "").length();
        int rihghtCount = com.length() - com.replace("}", "").length();
        if (leftCount == rihghtCount)
            return astronautFromJSON(com, add);
        else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder(com);
            while (leftCount != rihghtCount) {
                str = bufferedReader.readLine();
                leftCount += str.length() - str.replace("{", "").length();
                rihghtCount += str.length() - str.replace("}", "").length();
                sb.append(str);
            }
            String res = sb.toString().replace( "\n", "");
            return astronautFromJSON(res, add);
        }
    }
}
