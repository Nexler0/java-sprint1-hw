import java.util.ArrayList;

public class YearReport {
    ArrayList<String> yearListExpenses;
    double yearProfit;
    double yearExpense;

    YearReport(ArrayList<String> yearListExpenses) {
        this.yearListExpenses = yearListExpenses;
    }

    void yearDataProcessing() {
        boolean is_expense = true;
        double valueList = 0;
        for (String str : yearListExpenses) {
            String[] value = str.split(",");
            for (int a = 0; a < value.length; a++) {
                if (a == 1) valueList = Double.parseDouble(value[a]);
                if (a == 2) is_expense = (value[a].equals("true"));
            }
            if (!is_expense) {
                yearProfit = valueList;
            } else {
                yearExpense = valueList;
            }
        }
        if (yearProfit != 0 && yearExpense != 0) {
            System.out.println("Данные получены");
        } else {
            System.out.println("Ошибка получения данных");
        }
    }

    void getClearProfit() {
        double clearProfit;
        clearProfit = yearProfit - yearExpense;
        System.out.println(clearProfit);
    }

    public double getYearExpense() {
        return yearExpense;
    }

    public double getYearProfit() {
        return yearProfit;
    }
}
