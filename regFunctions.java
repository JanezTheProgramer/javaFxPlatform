package sample;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;

public class regFunctions {
    public TextField userText;
    public PasswordField passText;
    public PasswordField confText;


    //deez 2 func do the same thing one handles enter one mouse click!
    public void requestNewAcc(javafx.event.ActionEvent actionEvent) {
        regFunc();
    }

    public void EnterPressedReg(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            regFunc();
        }
    }

    public void regFunc() {
        if(userText.getText().length() > 0 && passText.getText().length() > 0 && confText.getText().length() > 0){
            if(passText.getText().equals(confText.getText())){
                try{
                    switch (QueryClass.createAcc(userText.getText(), confText.getText())){
                        case -1:
                            System.out.println("runtime error!");
                            break;
                        case 0:
                            System.out.println("created acc");
                            break;
                        case 1:
                            System.out.println("already exsist!");
                            break;
                        case 2:
                            System.out.println("not valid email!");
                            break;
                        default:
                            break;
                    }

                }catch (Exception e){
                    System.out.println("error");
                    //custom failed to create acc!!!
                }
            }else{
                //pasw do not match
            }

        }else{
            //fil in data
        }
    }
}
