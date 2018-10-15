package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class logFunctions {

    public PasswordField passField;
    public TextField userField;

    //deez 2 func do the same thing one handles enter one mouse click!
    public void tryLoginMaster(ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException {
        loginFunc();
        //call function that does need parameter bcs its called from main.java
    }
    public void EnterPressedLog(KeyEvent keyEvent) throws NoSuchAlgorithmException, IOException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            loginFunc();
        }
    }

    public void loginFunc() throws NoSuchAlgorithmException, IOException {
        if(passField.getText().length() > 0 && userField.getText().length() > 0){
            if(QueryClass.loginQuery(passField.getText(), userField.getText())){
                JOptionPane.showMessageDialog(null, "Login was successfull!");
            }else
                JOptionPane.showMessageDialog(null, "Failed to login!");
        }else{
            JOptionPane.showMessageDialog(null, "Fill the form!");
        }
    }


    public void requireRegisterWindow(ActionEvent mouseEvent) throws IOException, NullPointerException {
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
