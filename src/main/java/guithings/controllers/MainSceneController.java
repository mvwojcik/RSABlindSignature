package guithings.controllers;

import Algorithm.RSA;
import guithings.utils.ExceptionDialog;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import guithings.utils.FxmlUtils;
import guithings.utils.StaticStuff;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class MainSceneController extends BaseController {

    @FXML
    private TextField nazwaTextField;

    @FXML
    private ListView listView;

    @FXML
    private Label czySieUdalo;

    @FXML
    private ChoiceBox choiceBoxP1;

    @FXML
    private ChoiceBox choiceBoxP2;


    private File file;

    private String path;

    private int size;

    @FXML
    private void initialize() {
        StaticStuff.setMainController(this);

    }

    @FXML
    void wybierzPlik() {

        ControllersInterface controllersInterface = new ControllersInterface();
        Thread thread1 = new Thread(controllersInterface, "Jazda");

        controllersInterface.run();

    }

    @FXML
    void przeslon() {
        this.path = this.getItem();
        this.file = new File(path);

        RSA rsa = new RSA();
        rsa.setTab(RSA.bytesTabtoBigInt(saveAs2DBytesArray(this.saveAsBytesArray(this.file), this.size)));

        rsa.startBlinding();

        this.saveStringToFile(rsa.getValues());
        this.saveStringToFile(rsa.getOryginalSignature(), "sign");

    }

    @FXML
    void podpisz() {
        this.path = this.getItem();
        this.file = new File(path);
        RSA rsa = new RSA();
        rsa.saveStringValuesToBigInteger(this.saveFiletoString(file));
        rsa.startSigning();
        this.saveStringToFile(rsa.getValues());
    }

    @FXML
    void odbierz() {

        this.path = this.getItem();
        this.file = new File(path);
        RSA rsa = new RSA();
        rsa.saveStringValuesToBigInteger(this.saveFiletoString(file));
        rsa.startChecking();
        this.saveStringToFile(rsa.getValues());

    }

    @FXML
    void sprawdz() {
        byte[] temp1 = this.saveAsBytesArray(new File((String) this.choiceBoxP1.getSelectionModel().getSelectedItem()));
        byte[] temp2 = this.saveAsBytesArray(new File((String) this.choiceBoxP2.getSelectionModel().getSelectedItem()));
        boolean flag = false;

        if (temp1.length == temp2.length) {
            System.out.println("JEAAA");
            for (int i = 0; i < temp1.length; i++) {
                if (temp1[i] != temp2[i]) {
                    flag = true;
                    System.out.println("NOOOOOOOOOO");
                }
            }
            if (flag == false) {
                this.czySieUdalo.setText("Zgodność podpisu");
                this.czySieUdalo.setVisible(true);
            } else {
                this.czySieUdalo.setText("Niezgodność podpisu");
                this.czySieUdalo.setVisible(true);

            }
        } else {
            this.czySieUdalo.setText("Niezgodność podpisu");
            this.czySieUdalo.setVisible(true);

        }
    }

    @FXML
    void p1OnAction() {
        openFileChooser(choiceBoxP1);

    }

    @FXML
    void p2OnAction() {
        openFileChooser(choiceBoxP2);
    }


    public byte[] saveAsBytesArray(File filex) {
        if (filex != null) {
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(filex.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.size = (fileContent.length % 8 == 0) ? (fileContent.length / 8) : ((fileContent.length / 8) + 1);

            return fileContent;
        } else {
            System.out.println("ERROR! U HAVE TO CHOOSE FILE");
            return null;
        }
    }

    public byte[] saveAsBytesArray(File filex, int sizex) {
        if (filex != null) {
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(filex.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sizex = (fileContent.length % 8 == 0) ? (fileContent.length / 8) : ((fileContent.length / 8) + 1);

            return fileContent;
        } else {
            System.out.println("ERROR! U HAVE TO CHOOSE FILE");
            return null;
        }
    }

    private String getItem() {
        String absolutePath = (String) this.getListView().getSelectionModel().getSelectedItem();
        if (absolutePath == null) {
            if (this.getListView().getItems().get(0) == null) {
                throw new NullPointerException(" U have to choose item");
            }
            absolutePath = (String) this.getListView().getItems().get(0);
        }
        System.out.println(absolutePath);
        return absolutePath;
    }

    public static byte[][] saveAs2DBytesArray(byte[] fileContent, int size) {
        byte[][] temp = new byte[size][8];
        int temp1 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 8; j++) {
                if (temp1 == fileContent.length) {
                    temp[i][j] = 0;
                } else {
                    temp[i][j] = fileContent[temp1];
                    temp1++;
                }
            }
        }
        return temp;
    }

    public void saveStringToFile(String[] array) {

        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\test\\" + this.nazwaTextField.getText()));
            for (int i = 0; i < array.length; i++) {
                fw.write(array[i]);
                fw.newLine();
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void saveStringToFile(String[] array, String sign) {

        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\test\\signOf" + this.nazwaTextField.getText()));
            for (int i = 0; i < array.length; i++) {
                fw.write(array[i]);
                fw.newLine();
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String[] saveFiletoString(File file) {
        List<String> lista = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();

            while (temp != null) {
                lista.add(temp);
                temp = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.getMessage();
        }
        String[] tab = lista.toArray(new String[lista.size()]);
        return tab;
    }

    public void openFileChooser(ChoiceBox choiceBox) {
        FileChooser fc = new FileChooser();  //Inicjalizacja fc
        fc.setInitialDirectory(new File((System.getProperty("user.dir")) + "\\test"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt", "*.txt"),
                new FileChooser.ExtensionFilter("pdf", "*.pdf"),
                new FileChooser.ExtensionFilter("png", "*.png"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"));

        File selectedFile = fc.showOpenDialog(null); //przypisz wybrany w fc plik do selectedFile
        fileIsNull(selectedFile, fc, choiceBox);
    }

    private void fileIsNull(File selectedFile, FileChooser fc, ChoiceBox choiceBox) {
        if (selectedFile != null) {
            choiceBox.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }

    }


    public ListView getListView() {
        return this.listView;
    }


}
