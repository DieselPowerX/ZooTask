package model;

public class PrintService {
    AlgorithmService algorithmService;

    public PrintService() {
        algorithmService = new AlgorithmService();
    }

    public void printResult() {
        System.out.println(algorithmService.getResult());


    }
}
