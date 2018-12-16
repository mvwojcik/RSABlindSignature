package guithings.utils;

import guithings.controllers.BaseController;

public class ActualPage  <T extends BaseController> {

    T controller;
    public void setActualPage(T controller)
    {
        this.controller = controller;
    }

}
