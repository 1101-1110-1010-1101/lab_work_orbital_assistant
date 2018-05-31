package ru.ifmo.prog.lab3;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginForm extends Application {
    @Override
    public void start(Stage primaryStage){
        Button connect = new Button();
        connect.setText("Connect");
        Button register = new Button();
        register.setText("Registration");
        TextField login = new TextField();
        PasswordField pswrd = new PasswordField();
        Label login_t = new Label("Login: ");
        Label pswrd_t = new Label("Password: ");

        Label name = new Label();
        name.setText("Orbital Assistant");
        Label name1 = new Label("Professional ground \ncontrol software");

        final ImageView selectedImage = new ImageView();

        /*  Variant 1

        try {
            Image image1 = new Image(new FileInputStream("F:\\ITMO\\Projects\\Java\\Laba6_Server_new\\src\\main\\java\\resources\\logo.jpg"));
        selectedImage.setImage(image1);
        selectedImage.setFitHeight(150);
        selectedImage.setFitWidth(150);



        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(login.getText());
                System.out.println(pswrd.getText());
            }
        });

        AnchorPane root = new AnchorPane();



        root.getChildren().add(connect);
        AnchorPane.setBottomAnchor(connect, 10.0);
        AnchorPane.setLeftAnchor(connect, 10.0);

        root.getChildren().add(register);
        AnchorPane.setBottomAnchor(register, 10.0);
        AnchorPane.setLeftAnchor(register, AnchorPane.getLeftAnchor(connect) + 70.0);

        root.getChildren().add(name);
        AnchorPane.setTopAnchor(name, 10.0);
        AnchorPane.setLeftAnchor(name, 10.0);

        root.getChildren().add(selectedImage);
        AnchorPane.setRightAnchor(selectedImage, 0.0);
        AnchorPane.setTopAnchor(selectedImage, 0.0);

        root.getChildren().add(login_t);
        AnchorPane.setTopAnchor(login_t, 100.0);
        AnchorPane.setLeftAnchor(login_t, 25.0);

        root.getChildren().add(pswrd_t);
        AnchorPane.setTopAnchor(pswrd_t, 150.0);
        AnchorPane.setLeftAnchor(pswrd_t, 7.0);

        root.getChildren().add(login);
        AnchorPane.setTopAnchor(login, 100.0);
        AnchorPane.setLeftAnchor(login, 65.0);

        root.getChildren().add(pswrd);
        AnchorPane.setTopAnchor(pswrd, 150.0);
        AnchorPane.setLeftAnchor(pswrd, 65.0);

        Scene scene = new Scene(root, 250*1.61803398875, 250);

        primaryStage.setTitle("OR_ASS 1.001 alpha");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (FileNotFoundException f) {f.printStackTrace();}*/

        // Variant 2

        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(login.getText());
                System.out.println(pswrd.getText());
            }
        });
        try {
            Image image1 = new Image(new FileInputStream("F:\\ITMO\\Projects\\Java\\Laba6_Server_new\\src\\main\\java\\resources\\image.png"));
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

            Scene scene = new Scene(root, 350, 350);
            primaryStage.setTitle("OR_ASS 1.001 alpha");
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (FileNotFoundException f) {f.printStackTrace();}

    }
    public static void main(String[] args) {
        launch(args);
    }
}
