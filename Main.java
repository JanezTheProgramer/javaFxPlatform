package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
        ActionEvent event = null;
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setHeight(300);
        loginStage.setWidth(480);
        loginStage.setTitle("Login");
        Parent logFile = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Scene logScene = new Scene(logFile);
        logScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        loginStage.setScene(logScene);
        logScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    logFunctions.loginFunc();
                } catch (Exception ignored) { }
            }
        });
        loginStage.show();
        QueryClass.createDB();
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b1 : b){
            sb.append(Integer.toHexString(b1 & 0xff));
        }
        return new StringBuilder(sb.toString()).reverse().toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


