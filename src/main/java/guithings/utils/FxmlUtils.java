package guithings.utils;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class FxmlUtils {

    public static Pane fxmlLoader(String PATH)
    {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(PATH));
        try {
            return loader.load();
        } catch (IOException e) {
            guithings.utils.ExceptionDialog.errorDialog("Wolisz zjesc gowno czy szklankę?");
        }
        return null;
    }

    public static void loadingStage(Stage primaryStage, String PATH) {
        Pane pane = FxmlUtils.fxmlLoader(PATH);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                StaticStuff.shutDown();
            }
        });

    }


}
