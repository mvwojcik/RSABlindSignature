package guithings.utils;

import guithings.controllers.MainSceneController;

import java.io.IOException;

public class StaticStuff {
    public static MainSceneController MSC;
    public static void setMainController(MainSceneController msc)
    {
        MSC = msc;
    }
    public static MainSceneController getMainController()
    {
        return MSC;
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
