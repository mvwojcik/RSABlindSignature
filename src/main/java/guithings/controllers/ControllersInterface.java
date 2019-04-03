package guithings.controllers;

import guithings.utils.FxmlUtils;
import javafx.stage.Stage;

public class ControllersInterface implements Runnable {
    Stage secondaryStage;

    @Override
    public void run() {
        secondaryStage = new Stage();
        FxmlUtils.loadingStage(secondaryStage, "/fxml/ChoosingDialog.fxml");
    }

}
