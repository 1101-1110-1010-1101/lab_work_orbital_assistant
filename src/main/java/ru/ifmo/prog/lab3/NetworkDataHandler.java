package ru.ifmo.prog.lab3;

import com.google.gson.Gson;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NetworkDataHandler {
    private int port;
    private DatagramSocket ds;
    private byte[] buf = new byte[64000];
    private DatagramPacket pack = new DatagramPacket(buf, 64000);
    private Gson json = new Gson();


    public NetworkDataHandler(int prt) throws SocketException {
        this.port = prt;
        ds = new DatagramSocket(port);
    }

    public String get_data() {
        try {
            buf = null;
            buf = new byte[64000];
            pack.setData(buf);
            ds.receive(pack);
            String undeserialized = new String(pack.getData()).trim();
            String md = undeserialized.chars().skip(undeserialized.length()-32).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String data = undeserialized.chars().limit(undeserialized.length()-32).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String checkSum = getCheckSum(data);
            //System.out.println(md);
            //System.out.println(checkSum);
            buf = null;
            buf = new byte[64000];
            if (md.equals(checkSum))
                return json.fromJson(data, String.class);
            else return "ERROR";
        } catch (IOException i) {
            return "IO_Exception";
        }
    }

    public void send_data(String data){
        try {
            buf = null;
            buf = new byte[64000];
            pack.setData(buf);
            String serialized = json.toJson(data);
            serialized += getCheckSum(serialized);
             //System.out.println(serialized);
            pack.setData(serialized.getBytes());
            ds.send(pack);
            buf = null;
            buf = new byte[64000];
        } catch (IOException i) {
            System.out.println("IO_Exception");
        }
    }

    private String getCheckSum(String object){
            try {MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(object.getBytes());
            return DatatypeConverter.printHexBinary(thedigest);} catch (NoSuchAlgorithmException n){return "Error";}
    }
}
