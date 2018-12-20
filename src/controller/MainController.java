package controller;

import model.PrintService;

public class MainController {

    private PrintService printService;

    public MainController() {
        printService = new PrintService();
    }


    public void getResult() {
        printService.printResult();
    }

}
