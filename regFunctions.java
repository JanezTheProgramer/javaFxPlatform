package sample;

import javafx.scene.input.MouseEvent;

public class regFunctions {
    public void requestNewAcc(MouseEvent mouseEvent) {
        try{
            QueryClass.createAcc("janez.bananez@gmail.com", "987f");
            System.out.println("acc was created");
        }catch (Exception e){
            System.out.println("error");
            //custom failed to create acc!!!
        }
    }
}
