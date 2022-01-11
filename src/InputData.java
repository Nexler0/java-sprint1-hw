import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class InputData {

    public static void readMonthExpenses(String year) {
        String pathFile;
        String fileData;
        ArrayList<String> dataList;
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                pathFile = "m." + year + "0" + i + ".csv"; // delete ../resources/, replace folder /resources in /src
            } else {
                pathFile = "m." + year + "" + i + ".csv";
            }
            fileData = readFileContentsOrNull(pathFile);
            if (fileData != null) {
                dataList = new ArrayList<>();
                String[] dataLine = fileData.split("\n");
                for (int j = 0; j < dataLine.length; j++) {
                    dataList.add(dataLine[j]);
                }
                Main.monthData.add(new MonthReport(dataList));
            }
        }
    }

    public static void readYearExpenses(String year) {
        String pathFile = "y." + year + ".csv";
        String fileData;
        ArrayList<String> listString = new ArrayList<>();
        fileData = readFileContentsOrNull(pathFile);
        if (fileData != null) {
            String[] dataLine = fileData.split("\n");
            for (int j = 1; j < dataLine.length; j++) {
                String data = dataLine[j];
                listString.add(data);
                if (j % 2.0 == 0) {
                    Main.yearData.add(new YearReport(listString));
                    listString = new ArrayList<>();
                }
            }
        }
    }

    static String getNameMonth(int key) {
        String[] nameMonth;
        nameMonth = new String[]
                {"Январь",
                        "Февраль",
                        "Март",
                        "Апрель",
                        "май",
                        "Июнь",
                        "Июль",
                        "Август",
                        "Сентябрь",
                        "Октябрь",
                        "Ноябрь",
                        "Декабрь"};
        return nameMonth[key + 1];
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }
}
