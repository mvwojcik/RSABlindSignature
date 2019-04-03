package guithings.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import guithings.utils.StaticStuff;

import java.io.File;

public class ChoosingDialogController {

ControllersInterface ci;
    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField PathTextField;

    @FXML
    void ChoiceFromFile() {
        openFileChooser();
    }

    @FXML
    void ConfirmOnAction(ActionEvent event) {
        StaticStuff.getMainController().getListView().getItems().add(this.PathTextField.getText()); //dodaje wybrany plik do listyitemów
        StaticStuff.getMainStage().close();
    }
    @FXML
    public void initialize()
    {
        ci = new ControllersInterface();
    }

    public void openFileChooser() {
        FileChooser fc = new FileChooser();  //Inicjalizacja fc
        fc.setInitialDirectory(new File((System.getProperty("user.dir"))+"\\test"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt", "*.txt"),
                new FileChooser.ExtensionFilter("pdf","*.pdf"),
                new FileChooser.ExtensionFilter("png","*.png"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"));

        File selectedFile = fc.showOpenDialog(null); //przypisz wybrany w fc plik do selectedFile
        fileIsNull(selectedFile,fc);
    }

    private void fileIsNull(File selectedFile,FileChooser fc) {
        if (selectedFile != null) {
            this.PathTextField.setText(selectedFile.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }
    }


}