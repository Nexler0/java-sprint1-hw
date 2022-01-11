import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport {
    ArrayList<String> monthListExpenses;
    HashMap<String, Double> monthProfit = new HashMap<>();
    HashMap<String, Double> monthExpense = new HashMap<>();
    double monthSumProfit;
    double monthSumExpense;

    MonthReport(ArrayList<String> monthListExpenses) {
        this.monthListExpenses = monthListExpenses;
    }

    void monthDataProcessing() {
        String keyList = "";
        boolean is_expense = true;
        double valueList = 0;
        double quantity = 0;
        for (int index = 1; index < monthListExpenses.size(); index++) {
            String[] value = monthListExpenses.get(index).split(",");
            for (int a = 0; a < value.length; a++) {
                if (a == 0) keyList = value[a];                      // Brackets {} are always needed here?
                if (a == 1) is_expense = (value[a].equals("TRUE"));
                if (a == 2) valueList = Double.parseDouble(value[a]);
                if (a == 3) quantity = Double.parseDouble(value[a]);
            }
            if (!is_expense) {
                monthProfit.put(keyList, valueList * quantity);
            } else {
                monthExpense.put(keyList, valueList * quantity);
            }
        }
        if (monthProfit.size() > 0 && monthExpense.size() > 0) {
            System.out.println("Данные получены");
        } else {
            System.out.println("Ошибка получения данных");
        }
        for (String key : monthProfit.keySet()) {
            monthSumProfit += monthProfit.get(key);
        }
        for (String key : monthExpense.keySet()) {
            monthSumExpense += monthExpense.get(key);
        }
    }

    void getMaxProfitOnMonth() {
        double maxVal = 0;
        double val;
        String index = "";
        for (String key : monthProfit.keySet()) {
            val = monthProfit.get(key);
            if (maxVal < val) {
                maxVal = val;
                index = key;
            }
        }
        System.out.println(index + " - " + maxVal);
    }

    void getMaxExpenseOnMonth() {
        double maxVal = 0;
        double val;
        String index = "";
        for (String key : monthExpense.keySet()) {
            val = monthExpense.get(key);
            if (maxVal < val) {
                maxVal = val;
                index = key;
            }
        }
        System.out.println(index + " - " + maxVal);
    }

    public double getMonthSumExpense() {
        return monthSumExpense;
    }

    public double getMonthSumProfit() {
        return monthSumProfit;
    }
}









