package ru.ifmo.prog.lab3;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;

public class LoginForm extends Application {

    static final String filepath = "F:\\ITMO\\Projects\\Java\\Laba6_Server_new\\src\\main\\java\\resources\\users.json";
    @Override
    public void start(Stage primaryStage){
        Button connect = new Button();
        connect.setText("Connect");
        Button register = new Button();
        register.setText("Registration");
        TextField login = new TextField();
        PasswordField pswrd = new PasswordField();

        Label name = new Label();
        name.setText("Orbital Assistant");
        Label name1 = new Label("Professional ground \ncontrol software");

        final ImageView selectedImage = new ImageView();



        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                register(login.getText(), pswrd.getText());
            }
        });

        try {
            Image image1 = new Image(new FileInputStream("F:\\ITMO\\Projects\\Java\\Laba6_Server_new\\src\\main\\java\\resources\\image.png"));
            Image image2 = new Image(new FileInputStream("F:\\ITMO\\Projects\\Java\\Laba6_Server_new\\src\\main\\java\\resources\\logo.jpg"));
            selectedImage.setImage(image1);
            selectedImage.setFitHeight(360);
            selectedImage.setFitWidth(360);

            AnchorPane root = new AnchorPane();

            root.getChildren().add(selectedImage);

            name.setTextFill(Color.WHITE);
            name.setFont(new Font("Courier New", 28));
            root.getChildren().add(name);
            AnchorPane.setTopAnchor(name, 10.0);
            AnchorPane.setLeftAnchor(name, 10.0);
            name1.setTextFill(Color.WHITE);
            name1.setFont(new Font("Courier New", 16));
            root.getChildren().add(name1);
            AnchorPane.setTopAnchor(name1, 40.0);
            AnchorPane.setLeftAnchor(name1, 10.0);

            root.getChildren().add(connect);
            AnchorPane.setBottomAnchor(connect, 10.0);
            AnchorPane.setRightAnchor(connect, 10.0);

            root.getChildren().add(register);
            AnchorPane.setBottomAnchor(register, 10.0);
            AnchorPane.setRightAnchor(register, AnchorPane.getRightAnchor(connect) + 70.0);

            login.setPromptText("Login");
            root.getChildren().add(login);
            AnchorPane.setTopAnchor(login, 265.0);
            AnchorPane.setRightAnchor(login, 10.0);

            pswrd.setPromptText("Password");
            root.getChildren().add(pswrd);
            AnchorPane.setTopAnchor(pswrd, 295.0);
            AnchorPane.setRightAnchor(pswrd, 10.0);

            Button roundButton = new Button();

            roundButton.setStyle(
                    "-fx-background-radius: 50em; " +
                            "-fx-min-width: 20px; " +
                            "-fx-min-height: 20px; " +
                            "-fx-max-width: 20px; " +
                            "-fx-max-height: 20px;" +
                            "-fx-background-color: White"
            );
            root.getChildren().add(roundButton);
            AnchorPane.setBottomAnchor(roundButton, 5.0);
            AnchorPane.setLeftAnchor(roundButton, 5.0);



            Scene login_form = new Scene(root, 350, 350);
            primaryStage.setTitle("OR_ASS 1.001 alpha(server) LOGIN");
            primaryStage.setScene(login_form);
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
            primaryStage.show();

            connect.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    if (login(login.getText(), pswrd.getText())) {
                        System.out.println("Success");
                        root.getChildren().clear();
                        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                        primaryStage.setFullScreen(true);
                    }
                    else System.out.println("Fail");
                }
            });

            roundButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    if (selectedImage.getImage() == image1) { // Станет белым
                        selectedImage.setImage(image2);
                        roundButton.setStyle(
                                "-fx-background-radius: 50em; " +
                                        "-fx-min-width: 20px; " +
                                        "-fx-min-height: 20px; " +
                                        "-fx-max-width: 20px; " +
                                        "-fx-max-height: 20px;" +
                                        "-fx-background-color: Black"
                        );
                        name.setTextFill(Color.BLACK);
                        name1.setTextFill(Color.BLACK);
                    }
                    else {
                        selectedImage.setImage(image1); // FIXME image size
                        roundButton.setStyle(
                                "-fx-background-radius: 50em; " +
                                        "-fx-min-width: 20px; " +
                                        "-fx-min-height: 20px; " +
                                        "-fx-max-width: 20px; " +
                                        "-fx-max-height: 20px;" +
                                        "-fx-background-color: WHITE"
                        );
                        name.setTextFill(Color.WHITE);
                        name1.setTextFill(Color.WHITE);
                    }
                }
            });
        } catch (FileNotFoundException f) {f.printStackTrace();}

    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void register(String login, String psw){
        User u = new User(login, psw);
        String json_string = new Gson().toJson(u);
        for (User user: getUsers(filepath)) {
            if (user.getLogin().equals(login) && user.getPswrd().equals(psw))
                json_string = "";
        }
        try {
            FileWriter fstream = new FileWriter(filepath, true);
            fstream.append(json_string + "\n");
            fstream.close();
        } catch (IOException io) {
            System.err.println("Ошибка ввода/вывода");
        }
    }
    public static boolean login(String login, String psw){

        for (User user: getUsers(filepath)){
            if (user.getLogin().equals(login) && user.getPswrd().equals(psw))
                return true;
        }
        return false;
    }
    public static LinkedList<User> getUsers(String filepath){
        Gson json = new Gson();
        LinkedList<User> users = new LinkedList<User>();
        try{
            FileInputStream fstream = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                users.add(json.fromJson(strLine, User.class));
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
       return users;
    }
}
