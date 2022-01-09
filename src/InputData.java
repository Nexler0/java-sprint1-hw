import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InputData {

    public static HashMap<Integer, ArrayList<String>> readMonthExpenses(String year) {
        String pathFile;
        String fileData;
        ArrayList<String> dataList;
        HashMap<Integer, ArrayList<String>> listString = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                pathFile = "../resources/m." + year + "0" + i + ".csv";
            } else {
                pathFile = "../resources/m." + year + "" + i + ".csv";
            }
            fileData = readFileContentsOrNull(pathFile);
            if (fileData != null) {
                dataList = new ArrayList<>();
                String[] dataLine = fileData.split("\n");
                for (int j = 1; j < dataLine.length; j++) {
                    dataList.add(dataLine[j]);
                }
                listString.put(i, dataList);
            }
        }
        return listString;
    }

    public static ArrayList<String> readYearExpenses(String year) {
        String pathFile = "../resources/y." + year + ".csv";
        String fileData;
        ArrayList<String> listString = new ArrayList<>();
        fileData = readFileContentsOrNull(pathFile);
        if (fileData != null) {
            String[] dataLine = fileData.split("\n");
            for (int j = 1; j < dataLine.length; j++) { //можно сокращенным сделать
                String data = dataLine[j];
                listString.add(data);
            }
        }
        return listString;
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }
}
