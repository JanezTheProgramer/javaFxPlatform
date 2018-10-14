package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class logFunctions {

    public static PasswordField passField;
    public static TextField userField;

    public void tryLoginMaster(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        loginFunc();
        //call function that does need parameter bcs its called from main.java
    }

    public static void loginFunc() throws NoSuchAlgorithmException {
        if(passField.getText().length() > 0 && userField.getText().length() > 0){
            if(QueryClass.loginQuery(passField.getText(), userField.getText())){
                System.out.print("login sucees");
                //open main platform
            }else{
                System.out.print("login fail");
                //custom alert failed to login wrong pass / username!
            }
        }else{
            //fil in log data
        }
    }


    public void requireRegisterWindow(ActionEvent mouseEvent) throws IOException {
        Stage reqWindow = new Stage();
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
}
