package ru.ifmo.prog.lab3;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CSV_Handler {

    static void writeCSV(Object obj, String FILENAME){
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FILENAME, true), "UTF-8"));
            recurs(obj, printWriter);
            printWriter.print("\n");
            printWriter.close();
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        } catch (UnsupportedEncodingException u){System.out.println("Ошибка кодировки");
        }catch (IllegalAccessException i){System.out.println("Отказано в доступе");}

    }

    static void recurs(Object obj, PrintWriter printWriter) throws IllegalAccessException{
        Field[] f = obj.getClass().getDeclaredFields();
        String s = "";
        for (int i = 0; i < f.length; i++) {
            f[i].setAccessible(true);
            Annotation anno = f[i].getAnnotation(UserClass.class);
            if (anno != null) {
                recurs(f[i].get(obj), printWriter);
            }
            else s = s + f[i].get(obj) + ",";
        }
        printWriter.print(s);
    }
}
