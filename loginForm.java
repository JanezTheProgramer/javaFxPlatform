package sample;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class loginForm extends VBox {

    protected final AnchorPane anchorPane;
    protected final TextField textField;
    protected final PasswordField passwordField;
    protected final Button button;
    protected final Button button0;

    public loginForm() {

        anchorPane = new AnchorPane();
        textField = new TextField();
        passwordField = new PasswordField();
        button = new Button();
        button0 = new Button();

        setPrefHeight(390.0);
        setPrefWidth(420.0);

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setMaxHeight(-1.0);
        anchorPane.setMaxWidth(-1.0);
        anchorPane.setPrefHeight(320.0);
        anchorPane.setPrefWidth(420.0);

        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setLayoutX(138.0);
        textField.setLayoutY(38.0);
        textField.setPrefHeight(35.0);
        textField.setPrefWidth(200.0);
        textField.setFont(new Font(15.0));

        passwordField.setAlignment(javafx.geometry.Pos.CENTER);
        passwordField.setLayoutX(138.0);
        passwordField.setLayoutY(92.0);
        passwordField.setPrefHeight(35.0);
        passwordField.setPrefWidth(200.0);
        passwordField.setFont(new Font(15.0));

        button.setAlignment(javafx.geometry.Pos.CENTER);
        button.setLayoutX(138.0);
        button.setLayoutY(149.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(35.0);
        button.setPrefWidth(200.0);
        button.setText("Login");

        button0.setAlignment(javafx.geometry.Pos.CENTER);
        button0.setLayoutX(138.0);
        button0.setLayoutY(195.0);
        button0.setMnemonicParsing(false);
        button0.setOnAction(this::requireRegisterWindow);
        button0.setPrefHeight(35.0);
        button0.setPrefWidth(200.0);
        button0.setText("Create new acc");

        anchorPane.getChildren().add(textField);
        anchorPane.getChildren().add(passwordField);
        anchorPane.getChildren().add(button);
        anchorPane.getChildren().add(button0);
        getChildren().add(anchorPane);

    }

    protected abstract void requireRegisterWindow(javafx.event.ActionEvent actionEvent);

}
