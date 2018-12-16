package program;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {


    public static final String FXML_MAIN_STAGE ="/fxml/MainScene.fxml" ;
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {

        Locale.setDefault(new Locale("en"));

        guithings.utils.FxmlUtils.loadingStage(primaryStage,FXML_MAIN_STAGE);

        primaryStage.setTitle("Slepy podpis cyfrowy przy uzycia algorytmu RSA");


    }


}
