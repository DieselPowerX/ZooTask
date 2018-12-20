package model;


import java.util.*;

public class AlgorithmService {

    private int numbersOfElephants;
    private List<Integer> massOfElephants;
    private List<Integer> currentPositionOfElephants;
    private List<Integer> requestedPositionOfElephants;
    private List<Boolean> checkedList;
    private FilesService fileService;
    private long minWeight;
    private long result;


    public AlgorithmService() {
        fileService = new FilesService();
        massOfElephants = new ArrayList<>();
        currentPositionOfElephants = new ArrayList<>();
    }

    public void setDataFromFile(){
        final int[] i = {0};

        fileService.readInputFile().asIterator().forEachRemaining(s->{

            if(i[0]==0){
                numbersOfElephants = (Integer.parseInt((String)s));
                requestedPositionOfElephants = Arrays.asList(new Integer[numbersOfElephants]);
            }
            if(i[0]>=1 && i[0] <=numbersOfElephants) {
                setMinWeight((Long.parseLong((String) s)));
                massOfElephants.add((Integer.parseInt((String) s)));

            }
            if(i[0]>numbersOfElephants && i[0]<=2*numbersOfElephants){
                currentPositionOfElephants.add((Integer.parseInt((String) s)));
            }
            if(i[0]>numbersOfElephants*2 && i[0]<=3*numbersOfElephants){

                requestedPositionOfElephants.set((Integer.parseInt((String) s))-1, currentPositionOfElephants.get((i[0]-1)-2*numbersOfElephants));
            }

            i[0]++;
        });

        setCheckedList(numbersOfElephants);
    }

    private void setMinWeight(long minWeight) {
        if(this.minWeight == 0 || this.minWeight > minWeight) {
            this.minWeight = minWeight;
        }
    }

    private long doCalculation() {

        int CycleLength;
        long sumOfWeigth;
        long minOfWeigthInCycle;
        int currentPositionInCycle;
        int currentPosition = 1;

        do{
            CycleLength = 0;
            sumOfWeigth = 0;
            minOfWeigthInCycle = 1000000000;
            currentPositionInCycle = currentPosition;

            if(!checkIfPositionAllreadyChecked(currentPositionInCycle)) {
                do {
                    lockPositionOfCheckingPoint(currentPositionInCycle);
                    sumOfWeigth += massOfElephants.get(currentPositionInCycle-1);
                    minOfWeigthInCycle = Math.min(minOfWeigthInCycle, massOfElephants.get(currentPositionInCycle-1));
                    currentPositionInCycle = requestedPositionOfElephants.get(currentPositionInCycle-1);
                    ++CycleLength;

                    if(checkIfEndOfCycle(currentPositionInCycle,currentPosition)) {
                        break;
                    }

                }while (checkIfCycleFinished(currentPosition));

                result+= Math.min(sumOfWeigth+(CycleLength-2)*minOfWeigthInCycle, sumOfWeigth + minOfWeigthInCycle +(CycleLength+1) * minWeight);
            }
            currentPosition++;

        }while(currentPosition<numbersOfElephants);

        return result;
    }

    private boolean checkIfCycleFinished(int currentPosition) {
        return currentPosition != requestedPositionOfElephants.get(currentPosition - 1);
    }

    private boolean checkIfEndOfCycle(int currentPositionInCycle, int currentPosition){
        return currentPositionInCycle==currentPosition;
    }

    private boolean checkIfPositionAllreadyChecked(int position){
        return checkedList.get(position);
    }


    private void lockPositionOfCheckingPoint(int currentPositionInCycle) {
        checkedList.set(currentPositionInCycle, true);
    }

    public void setCheckedList(int numbersOfElephants) {
        this.checkedList = new ArrayList<>(Arrays.asList(new Boolean[numbersOfElephants+1]));
        Collections.fill(this.checkedList, Boolean.FALSE);
    }

    public long getResult() {
        setDataFromFile();

        return fileService.saveOutputFile(doCalculation());

    }
}
