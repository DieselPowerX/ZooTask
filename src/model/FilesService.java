package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FilesService {

    private String fileName;
    private File inputFile;
    private File outputFile;
    private Scanner scanner;

    public FilesService() {
        scanner = new Scanner(System.in);
        fileName = getFileName();
        inputFile = new File("C:\\Users\\Konrad\\IdeaProjects\\Zoo\\tasks\\" +  fileName + ".in");
        outputFile = new File("C:\\Users\\Konrad\\IdeaProjects\\Zoo\\tasks\\" +  fileName + ".out");
    }

    public StringTokenizer readInputFile() {

        try {
            return new StringTokenizer(Files.readString(inputFile.toPath()));

        } catch (NoSuchFileException s){
            System.out.println("file doesnt exist");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getFileName(){
        System.out.println("podaj nazwe pliku do rozwiazania pomijajac rozszerzenie");
        return scanner.nextLine();
    }

    public long saveOutputFile(long result){

        try {
            createFileIfdoesntExist();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void createFileIfdoesntExist() {
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
