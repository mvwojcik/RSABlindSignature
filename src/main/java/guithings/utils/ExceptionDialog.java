package guithings.utils;

import javafx.scene.control.*;

public class ExceptionDialog {


    public static void errorDialog(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Ooooooops");
        errorAlert.setHeaderText("Something goes so wrong");
        Label errormsg = new Label(error);
        errorAlert.getDialogPane().setContent(errormsg);
        errorAlert.showAndWait();
    }



}
