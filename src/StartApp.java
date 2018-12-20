import controller.MainController;

public class StartApp {

    private static MainController mainController;

    public static void main(String[] args){

        mainController = new MainController();
        mainController.getResult();

    }
}