package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class Main extends Application {

    Stage loginStage, reqWindow = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginStage = primaryStage;
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setHeight(300);
        loginStage.setWidth(480);
        loginStage.setTitle("Login");
        Parent logFile = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Scene logScene = new Scene(logFile);
        logScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        loginStage.setScene(logScene);
        loginStage.show();
        Main.createDB();
    }

    private static void createDB() throws ClassNotFoundException {
        //get db file
        try{
            File database = new File("database.db");
            boolean makefile = database.createNewFile();
        }catch (Exception err){
            System.out.println("file creation error!");
            //custom error!!!
        }
        Class.forName("org.sqlite.JDBC");
        String[] tables = {""};
        tables[0] = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS `users`")
                .append("( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,")
                .append("`gmail` TEXT NOT NULL,")
                .append("`pass` TEXT NOT NULL,")
                .append("`date` BLOB NOT NULL )")
                .toString();
        //generate tables if they dont exsist
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = conn.createStatement()) {
            try{
                for(String sql: tables) stmt.execute(sql);
            }
            catch(Exception e){/*not valid create table*/}
        } catch (SQLException e) {
            //error on base connection
        }
    }

    @FXML
    private void requireRegisterWindow() throws IOException, ClassNotFoundException {
        reqWindow.setHeight(300);
        reqWindow.setWidth(480);
        reqWindow.setTitle("Register");
        reqWindow.initModality(Modality.WINDOW_MODAL);
        reqWindow.initStyle(StageStyle.UNDECORATED);
        Parent reqFile = FXMLLoader.load(getClass().getResource("regForm.fxml"));
        Scene reqScene = new Scene(reqFile);
        reqScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        reqWindow.setScene(reqScene);
        reqWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


