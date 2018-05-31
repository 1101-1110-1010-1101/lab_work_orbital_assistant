package ru.ifmo.prog.lab3;

public class RadioProtocol {

    private int size = 7;

    private String name;

    private Message[] pool = new Message[size];
    {
        for (int i = 0; i < size; i++) {this.pool[i] = new Message(); }
    }

    public class Decoder{
    public int decode(String mes){
        int buf = 0;
        for (int i = 1; i < pool.length; i++) {
            if (pool[i].mesge.equals(mes)) {
                buf = i;
                break;
            }
        }
        return buf + 1;
    }};

    class Message {

        Message() {
            this.mesge = "";
            this.forHuman = false;
        }
        private String mesge;
        private boolean forHuman;
    }

    public RadioProtocol(String name) {
        this.name = name;
        this.set();
        Decoder d = new Decoder();
    }

    private void set() {
            pool[0].mesge = "Конец связи";
            pool[0].forHuman = true;
            pool[1].mesge = "-";
            pool[1].forHuman = false;
            pool[2].mesge = "Доложите обстановку";
            pool[2].forHuman = true;
            pool[3].mesge = "Чисто, эээээ, лаба";
            pool[3].forHuman = true;
            pool[4].mesge = "Согласен, имхо";
            pool[4].forHuman = false;
            pool[5].mesge = "Приём";
            pool[5].forHuman = true;
            pool[6].mesge = "Приём!";
            pool[6].forHuman = false;
    }

    public String getMes(int id) {
        return this.pool[id].mesge;
    }

    public boolean getHum(int id)  {
        return this.pool[id].forHuman;
    }

    public int getSize() {return size; }

    public String getName() {return name; }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + size;
        for(int i = 0; i <= name.length(); i++)
            result = 37 * result + name.indexOf(i);
        return result;
    }

    @Override
    public String toString(){
        return "Радиопротокол " + name + ".";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            RadioProtocol other = (RadioProtocol) anoter;
            return other.name == this.name && other.size == this.size && other.pool == this.pool;
        }
        return false;
    }
}