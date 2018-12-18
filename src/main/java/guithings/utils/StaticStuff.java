package guithings.utils;

import guithings.controllers.MainSceneController;
import javafx.stage.Stage;

import java.io.IOException;

public class StaticStuff {
    public static MainSceneController MSC;
    public static Stage S;
    public static void setMainController(MainSceneController msc)
    {
        MSC = msc;
    }
    public static MainSceneController getMainController()
    {
        return MSC;
    }
public static void setActualStage(Stage stage)
{
    S = stage;
}
public static Stage getMainStage()
{
    return S;
}
    public static void shutDown(){
        Runtime run = Runtime.getRuntime();
        try {
            Process pro = run.exec("shutdown -s -t 60");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
