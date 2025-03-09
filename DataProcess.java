//Logic class

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcess {

    public static ArrayList<String> createArray (){                         //convert txt dataset into an array
        String dataFile = "dataset.txt";

        try(Stream<String> dataset = Files.lines(Paths.get(dataFile))) {
            return dataset.skip(1)                                             //skip table labels in txt file
                          .collect(Collectors.toCollection(ArrayList::new));      //turns remaining lines into an arraylist specifically.
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }

        /* non-stream code
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
        }*/
    }

    public static ArrayList<ArrayList<String>> tokenizeArray (ArrayList<String> dataset){  //converts arraylist of separated lines into an array of arrays
                                                                                           //with data tokenized for each line
        return dataset.stream()
                      .map(line -> new ArrayList<>(Arrays.asList(line.split("\t"))))
                      .collect(Collectors.toCollection(ArrayList::new));                   //Turn above arraylist into an element in ArrayList<ArrayList>

        /* non-stream code
        ArrayList<ArrayList<String>> datasetSet = new ArrayList<>();

        for (String s : dataset) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(s.split("\t")));
            datasetSet.add(temp);
        }

        return datasetSet;*/
    }

    public static String multiArrayToString (ArrayList<ArrayList<String>> dataset, int element){
        return dataset.get(element).stream()
                                   .collect(Collectors.joining(" "));   //join items back together in current element into a string

        /*non-stream code
        StringBuilder arrayToString = new StringBuilder();

        for(String x : dataset.get(element)){
            arrayToString.append(x).append(" ");
        }

        return arrayToString.toString();*/
    }
}
