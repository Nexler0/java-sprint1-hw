import java.util.ArrayList;
import java.util.HashMap;

class DataProcessing {
    HashMap<String, HashMap<String, Double>> monthProfit = new HashMap<>();
    HashMap<String, HashMap<String, Double>> monthExpense = new HashMap<>();
    HashMap<String, Double> yearExpenses = new HashMap<>();
    HashMap<String, Double> yearProfit = new HashMap<>();

    void monthDataProcessing(HashMap<Integer, ArrayList<String>> monthListExpenses) {
        String keyList = "";
        boolean is_expense = true;
        double valueList = 0;
        double quantity = 0;
        HashMap<String, Double> monthProfitNameAndValue;
        HashMap<String, Double> monthExpenseNameAndValue;
        for (int key : monthListExpenses.keySet()) {
            monthProfitNameAndValue = new HashMap<>();
            monthExpenseNameAndValue = new HashMap<>();
            for (String str : monthListExpenses.get(key)) {
                String[] value = str.split(",");
                for (int a = 0; a < value.length; a++) {
                    if (a == 0) keyList = value[a];
                    if (a == 1) is_expense = (value[a].equals("TRUE"));
                    if (a == 2) valueList = Double.parseDouble(value[a]);
                    if (a == 3) quantity = Double.parseDouble(value[a]);
                }
                if (!is_expense) {
                    monthProfitNameAndValue.put(keyList, valueList * quantity);
                } else {
                    monthExpenseNameAndValue.put(keyList, valueList * quantity);
                }
            }
            monthProfit.put(nameMonth(key), monthProfitNameAndValue);
            monthExpense.put(nameMonth(key), monthExpenseNameAndValue);
        }
        if (monthProfit.size() > 0 && monthExpense.size() > 0)
            System.out.println("Данные получены");
        else System.out.println("Ошибка получения данных");
    }

    void yearDataProcessing(ArrayList<String> yearListExpenses) {
        int keyList = 0;
        boolean is_expense = true;
        double valueList = 0;
        for (String str : yearListExpenses) {
            String[] value = str.split(",");
            for (int a = 0; a < value.length; a++) {
                if (a == 0) keyList = Integer.parseInt(value[a]);
                if (a == 1) valueList = Double.parseDouble(value[a]);
                if (a == 2) is_expense = (value[a].equals("true"));
            }
            if (!is_expense) {
                yearProfit.put(nameMonth(keyList), valueList);
            } else {
                yearExpenses.put(nameMonth(keyList), valueList);
            }
        }
        if (yearProfit.size() > 0 && yearExpenses.size() > 0)
            System.out.println("Данные получены");
        else System.out.println("Ошибка получения данных");
    }

    void getMaxOnMonth(HashMap<String, HashMap<String, Double>> monthProfit) {
        HashMap<String, Double> nameValue;
        double maxValue;
        String index = "";
        for (String month : monthProfit.keySet()) {
            System.out.print(month);
            nameValue = monthProfit.get(month);
            maxValue = 0;
            for (String name : nameValue.keySet()) {
                if (maxValue < nameValue.get(name)) {
                    maxValue = nameValue.get(name);
                    index = name;
                }
            }
            System.out.println(" - " + index + " - " + maxValue);
        }
    }

    void yearClearProfit() {
        double profit;
        for (String key : yearProfit.keySet()) {
            profit = yearProfit.get(key) - yearExpenses.get(key);
            System.out.println(key + " - " + profit);
        }
    }

    double yearAverage(HashMap<String, Double> year) {
        double sum = 0;
        int count = 0;
        for (double key : year.values()) {
            count++;
            sum += key;
        }
        return sum / count;
    }

    HashMap<String, Double> sumInMonth(HashMap<String, HashMap<String, Double>> month) {
        HashMap<String, Double> nameValue;
        HashMap<String, Double> sumInMonth = new HashMap<>();
        for (String key : month.keySet()) {
            nameValue = month.get(key);
            double sumValue = 0;
            for (String nameGoods : nameValue.keySet()) {
                sumValue += nameValue.get(nameGoods);
            }
            sumInMonth.put(key, sumValue);
        }
        return sumInMonth;
    }

    void compareData(HashMap<String, Double> month, HashMap<String, Double> year) {
        double monthVal;
        double yearVal;
        for (String key : month.keySet()) {
            monthVal = month.get(key);
            yearVal = year.get(key);
            if (monthVal != yearVal) {
                System.out.println(key + " " + month.get(key)
                        + " " + year.get(key) + " - !!Ошибка!!");
            } else {
                System.out.println(key + " " + month.get(key)
                        + " " + year.get(key) + " - Хорошо");
            }
        }
    }

    String nameMonth(int key) {
        String nameMonth;
        if (key == 1) {
            nameMonth = "Январь";
        } else if (key == 2) {
            nameMonth = "Февраль";
        } else if (key == 3) {
            nameMonth = "Март";
        } else if (key == 4) {
            nameMonth = "Апрель";
        } else if (key == 5) {
            nameMonth = "май";
        } else if (key == 6) {
            nameMonth = "Июнь";
        } else if (key == 7) {
            nameMonth = "Июль";
        } else if (key == 8) {
            nameMonth = "Август";
        } else if (key == 9) {
            nameMonth = "Сентябрь";
        } else if (key == 10) {
            nameMonth = "Октябрь";
        } else if (key == 11) {
            nameMonth = "Ноябрь";
        } else {
            nameMonth = "Декабрь";
        }
        return nameMonth;
    }

    // может пригодятся \/  \/
    HashMap<String, HashMap<String, Double>> getMonthExpense() {
        return monthExpense;
    }

    HashMap<String, HashMap<String, Double>> getMonthProfit() {
        return monthProfit;
    }

    HashMap<String, Double> getYearExpenses() {
        return yearExpenses;
    }

    HashMap<String, Double> getYearProfit() {
        return yearProfit;
    }
}


