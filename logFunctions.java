package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class logFunctions {

    public void tryLoginMaster(MouseEvent mouseEvent) throws  NullPointerException {
        if(QueryClass.loginQuery("neki", "drugo")){
            System.out.print("login sucees");
            //open main platform
        }else{
            System.out.print("login fail");
            //custom alert failed to login wrong pass / username!
        }
    }

    public void requireRegisterWindow(MouseEvent mouseEvent) throws IOException {
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
