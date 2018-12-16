package guithings.controllers;

import Algorithm.RSA;
import guithings.utils.ExceptionDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import guithings.utils.FxmlUtils;
import guithings.utils.StaticStuff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class MainSceneController extends BaseController
{


        @FXML
        private Button PlikDoPodpisu;

        @FXML
        private Button PlikDoSprawdzenia;

        @FXML
        private Button Podpisz;

        @FXML
        private TextField NazwaTextField;

        @FXML
        private Button Sprawdz;

        @FXML
        private ListView listView;

        private File file;

        private String path;

        private int size;

        @FXML
        private void initialize()
        {
                StaticStuff.setMainController(this);
        }

        @FXML
        void PlikDoSprawdzenia() {

                Stage secondaryStage = new Stage();
                FxmlUtils.loadingStage(secondaryStage,"/fxml/ChoosingDialog.fxml" );
        }

        @FXML
        void Podpisz() {
//this.path;

RSA rsa = new RSA();
this.saveAsBytesArray();

        }

        @FXML
        void Sprawdz() {

        }


        public byte[] saveAsBytesArray() {
                if (file != null) {
                        byte[] fileContent = null;
                        try {
                                fileContent = Files.readAllBytes(file.toPath());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        this.size = (fileContent.length % 8 == 0) ? (fileContent.length / 8) : ((fileContent.length / 8) + 1);

                        return fileContent;
                } else {
                        System.out.println("ERROR! U HAVE TO CHOOSE FILE");
                        ExceptionDialog exceptionDialog = new ExceptionDialog();

                       exceptionDialog.choiceDialog("WOLISZ ZJEŚĆ GÓWNO CZY SZKLANKE?");
                        return null;
                }
        }

        private String getItem() {
                String absolutePath = (String) this.getListView().getSelectionModel().getSelectedItem();
                if (absolutePath == null) {
                        if(this.getListView().getItems().get(0) == null)
                        {
                                throw new NullPointerException(" U have to choose item");
                        }
                        absolutePath = (String) this.getListView().getItems().get(0);
                }
                System.out.println(absolutePath);
                return absolutePath;
        }


        public ListView getListView() {
                return this.listView;
        }
}
