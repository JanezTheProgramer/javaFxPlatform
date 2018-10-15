package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import java.io.IOException;

public class Alert {
    public static void CreateAlert(String msg) throws IOException {
        Stage reqWindow = new Stage();
        reqWindow.setHeight(80);
        reqWindow.setWidth(240);
        reqWindow.setTitle("Alert");
        reqWindow.initModality(Modality.WINDOW_MODAL);
        reqWindow.initStyle(StageStyle.UNDECORATED);
        Parent reqFile = FXMLLoader.load(Alert.class.getResource("regForm.fxml"));
        Scene reqScene = new Scene(reqFile);
        reqScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        reqWindow.setScene(reqScene);
        reqWindow.show();
        TimerTask task = new TimerTask() {
            public void run() {
                reqWindow.close();
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 2000);
    }
}
