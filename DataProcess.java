import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DataProcess {
    public static ArrayList<String> createArray (){
        File dataFile = new File("dataset.txt");
        ArrayList<String> dataset;

        //read dataFile.txt into ArrayList dataset
        try (Scanner scanner = new Scanner(dataFile)) {
            dataset = new ArrayList<>();

            //skip table labels in txt file
            if (scanner.hasNextLine()){
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                dataset.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return dataset;
    }

    public static ArrayList<ArrayList<String>> tokenizeArray (ArrayList<String> dataset){
        ArrayList<ArrayList<String>> datasetSet = new ArrayList<>();

        for (String s : dataset) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(s.split("\t")));
            datasetSet.add(temp);
        }

        return datasetSet;
    }

    public static String multiArrayToString (ArrayList<ArrayList<String>> dataset, int element){
        StringBuilder arrayToString = new StringBuilder();

        for(String x : dataset.get(element)){
            arrayToString.append(x).append(" ");
        }

        return arrayToString.toString();
    }
}
