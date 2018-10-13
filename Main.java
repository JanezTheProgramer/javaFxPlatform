package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main extends Application {

    Stage loginStage, reqWindow = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginStage = primaryStage;
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setHeight(300);
        loginStage.setWidth(480);
        loginStage.setTitle("Login");
        Parent loginFormFile = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Scene logScene = new Scene(loginFormFile);
        logScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        loginStage.setScene(logScene);
        loginStage.show();
    }

    @FXML
    private void requireRegisterWindow() throws IOException {
        Connection conn = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT ff FROM users");
            while ( rs.next() ) {
                String lastName = rs.getString("ff");
                System.out.println(lastName);
            }
            conn.close();

        }
        catch(Exception e){
            System.out.print("Do not connect to DB - Error:"+e);
        }
        reqWindow.setHeight(300);
        reqWindow.setWidth(480);
        reqWindow.setTitle("Register");
        reqWindow.initModality(Modality.WINDOW_MODAL);
        reqWindow.initStyle(StageStyle.UNDECORATED);
        Parent loginFormFile = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Scene reqScene = new Scene(loginFormFile);
        reqScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        reqWindow.setScene(reqScene);
        reqWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


