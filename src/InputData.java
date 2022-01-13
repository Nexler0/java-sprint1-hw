import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class InputData {

    public static HashMap<Integer, MonthReport> readMonthExpenses(String year) {
        HashMap<Integer, MonthReport> monthData = new HashMap<>();
        ArrayList<String> dataList;
        String pathFile;
        String fileData;
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                pathFile = "resources/m." + year + "0" + i + ".csv";
            } else {
                pathFile = "resources/m." + year + "" + i + ".csv";
            }
            fileData = readFileContentsOrNull(pathFile);
            if (fileData != null) {
                dataList = new ArrayList<>();
                String[] dataLine = fileData.split("\n");
                for (int j = 0; j < dataLine.length; j++) {
                    dataList.add(dataLine[j]);
                }
                monthData.put(i, new MonthReport(dataList));
            } else {
                System.out.println(getNameMonth(i - 1) + " - Ошибка чтения файла");
            }
        }
        return monthData;
    }

    public static HashMap<Integer, YearReport> readYearExpenses(String year) {
        String pathFile = "resources/y." + year + ".csv";
        String fileData;
        ArrayList<String> listString = new ArrayList<>();
        HashMap<Integer, YearReport> yearData = new HashMap<>();
        fileData = readFileContentsOrNull(pathFile);
        if (fileData != null) {
            String[] dataLine = fileData.split("\n");
            int count = 1;
            String[] num;
            for (int j = 1; j < dataLine.length; j++) {

                String data = dataLine[j];
                listString.add(data);

                for (String str : listString){
                    num = str.split(",");
                    count = Integer.parseInt(num[0]);
                }
                if (j % 2.0 == 0) {
                    yearData.put(count, new YearReport(listString));
                    count++;
                    listString = new ArrayList<>();
                }
            }
        } else {
            System.out.println("Ошибка чтения файла");
        }
        return yearData;
    }

    static String getNameMonth(int key) {
        String[] nameMonth;
        nameMonth = new String[]{
                "Январь",
                "Февраль",
                "Март",
                "Апрель",
                "Май",
                "Июнь",
                "Июль",
                "Август",
                "Сентябрь",
                "Октябрь",
                "Ноябрь",
                "Декабрь"};
        return nameMonth[key];
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }
}