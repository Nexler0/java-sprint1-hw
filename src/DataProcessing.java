import java.util.ArrayList;
import java.util.HashMap;

public class DataProcessing {
    HashMap<String, HashMap<String, Double>> monthProfit = new HashMap<>();
    HashMap<String, HashMap<String, Double>> monthExpense = new HashMap<>();
    HashMap<Integer, Double> yearExpenses = new HashMap<>();
    HashMap<Integer, Double> yearProfit = new HashMap<>();

    public void monthDataProcessing(HashMap<Integer, ArrayList<String>> monthListExpenses) {
        String keyList = "";
        String nameMonth = "";
        boolean is_expense = true;
        double valueList = 0;
        double quantity = 0;
        HashMap<String, Double> monthProfitNameAndValue;
        HashMap<String, Double> monthExpenseNameAndValue;
        for (int key : monthListExpenses.keySet()) {
//            System.out.println(" ");
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
//                System.out.print(keyList
//                        + "   " + is_expense
//                        + "   " + valueList
//                        + "   " + quantity);
                if (!is_expense) {
                    monthProfitNameAndValue.put(keyList, valueList * quantity);
//                    System.out.println(" <--");
                } else {
                    monthExpenseNameAndValue.put(keyList, valueList * quantity);
//                    System.out.println("");
                }
            }
            if (key == 1) {
                nameMonth = "Январь";
            } else if (key == 2){
                nameMonth = "Февраль";
            } else if (key == 3){
                nameMonth = "Март";
            } else if (key == 4){
                nameMonth = "Апрель";
            } else if (key == 5){
                nameMonth = "май";
            } else if (key == 6){
                nameMonth = "Июнь";
            } else if (key == 7){
                nameMonth = "Июль";
            } else if (key == 8){
                nameMonth = "Август";
            } else if (key == 9){
                nameMonth = "Сентябрь";
            } else if (key == 10){
                nameMonth = "Октябрь";
            } else if (key == 11){
                nameMonth = "Ноябрь";
            } else {
                nameMonth = "Декабрь";
            }
            monthProfit.put(nameMonth, monthProfitNameAndValue);
            monthExpense.put(nameMonth, monthExpenseNameAndValue);
        }
        if (monthProfit.size() > 0 && monthExpense.size() > 0)
            System.out.println("Данные получены");
        else System.out.println("Ошибка получения данных");
    }

    public void yearDataProcessing(ArrayList<String> yearListExpenses) {
        int keyList = 0;
        boolean is_expense = true;
        double valueList = 0;
//            System.out.println(" ");
            for (String str : yearListExpenses) {
                String[] value = str.split(",");
                for (int a = 0; a < value.length; a++) {
                    if (a == 0) keyList = Integer.parseInt(value[a]);
                    if (a == 1) valueList = Double.parseDouble(value[a]);
                    if (a == 2) is_expense = (value[a].equals("true"));
                }
//                System.out.print(keyList
//                        + "   " + is_expense
//                        + "   " + valueList);
                if (!is_expense) {
                    yearProfit.put(keyList, valueList);
//                    System.out.println(" <--");
                } else {
                    yearExpenses.put(keyList, valueList);
//                    System.out.println("");
                }
            }
        if (yearProfit.size() > 0 && yearExpenses.size() > 0)
            System.out.println("Данные получены");
        else System.out.println("Ошибка получения данных");
    }

    public void getMaxOnMonth(HashMap<String, HashMap<String, Double>> monthProfit) {
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

    public HashMap<String, HashMap<String, Double>> getMonthExpense() {
        return monthExpense;
    }

    public HashMap<String, HashMap<String, Double>> getMonthProfit() {
        return monthProfit;
    }

    public HashMap<Integer, Double> getYearExpenses() {
        return yearExpenses;
    }

    public HashMap<Integer, Double> getYearProfit() {
        return yearProfit;
    }
}


