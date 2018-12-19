package guithings.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlUtils {

    public static Pane fxmlLoader(String PATH)
    {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(PATH));
        try {
            return loader.load();
        } catch (IOException e) {
            guithings.utils.ExceptionDialog.errorDialog("FXML LOADER ERROR");
        }
        return null;
    }

    public static void loadingStage(Stage primaryStage, String PATH) {
        Pane pane = FxmlUtils.fxmlLoader(PATH);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        StaticStuff.setActualStage(primaryStage);
        primaryStage.show();
        /*
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                StaticStuff.shutDown();
            }
        });
        */

    }


}
