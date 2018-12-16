package guithings.utils;

import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ExceptionDialog {


    public static void errorDialog(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Ooooooops");
        errorAlert.setHeaderText("Something goes so wrong");
        TextArea errormsg = new TextArea(error);
        errorAlert.getDialogPane().setContent(errormsg);
        errorAlert.showAndWait();
    }

    public void choiceDialog(String error)
    {
        Alert choiceAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonType1 = new ButtonType("Gówno");
        ButtonType buttonType2 = new ButtonType("Szklanke");

        choiceAlert.getButtonTypes().set(1,buttonType1);
        choiceAlert.getButtonTypes().set(0,buttonType2);
        choiceAlert.setTitle("HAU HAU");
        choiceAlert.setHeaderText("Chowaj te blanty pssssssssss jadą");
        TextField text = new TextField(error);
        choiceAlert.getDialogPane().setContent(text);
        Image image = new Image(this.getClass().getResource("/image/FRYCZXD.png").toExternalForm());

        ImageView imageView = new ImageView(image);

        choiceAlert.setGraphic(imageView);


        choiceAlert.showAndWait().ifPresent(response -> {
            if (response == buttonType1) {
                ExceptionDialog exceptionDialog = new ExceptionDialog();
                exceptionDialog.otherDialog("JAZDA");
            }
            else if(response == buttonType2)
            {
                ExceptionDialog exceptionDialog = new ExceptionDialog();
                exceptionDialog.otherDialog2("JAZDA");
            }
        });

    }

    public void otherDialog(String error)
    {
Alert infoAlert = new Alert(Alert.AlertType.WARNING);
infoAlert.setTitle("No i chuj");
infoAlert.setHeaderText("Zaraz przyjedzie 130 chamów");
Label label = new Label("No i nie podobasz mi się");
infoAlert.getDialogPane().setContent(label);

        Image image = new Image(this.getClass().getResource("/image/ciec.jpg").toExternalForm());

        ImageView imageView = new ImageView(image);

        infoAlert.setGraphic(imageView);
    infoAlert.showAndWait();
    }

    public void otherDialog2(String error)
    {
        Alert infoAlert = new Alert(Alert.AlertType.WARNING);
        infoAlert.setTitle("No i chuj");
        infoAlert.setHeaderText("Zaraz przyjedzie 130 chamów");
        Label label = new Label("Ale to nie do mnie tak, do mnie nie");
        label.setPrefHeight(40);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
            label.setEffect(r);

        infoAlert.getDialogPane().setContent(label);

        Image image = new Image(this.getClass().getResource("/image/ciec.jpg").toExternalForm());

        ImageView imageView = new ImageView(image);

        infoAlert.setGraphic(imageView);
        infoAlert.showAndWait();
    }

}
