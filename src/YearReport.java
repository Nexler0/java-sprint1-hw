import java.util.ArrayList;

public class YearReport {
    ArrayList<String> yearData;
    ArrayList<MonthInYear> yearMonths = new ArrayList<>();

    YearReport(ArrayList<String> yearData) {
        this.yearData = yearData;
    }

    void yearDataProcessing() {
        for (String str : yearData) {
            String[] value = str.split(",");
            yearMonths.add(new MonthInYear(
                    Integer.parseInt(value[0]),
                    Double.parseDouble(value[1]),
                    Boolean.parseBoolean(value[2])));
        }
        if (yearMonths.size() > 0) {
            System.out.println("Данные получены");
        } else {
            System.out.println("Ошибка получения данных");
        }

    }

    public double getClearProfit() {
        double yearProfit=0;
        double yearExpense=0;
        for (MonthInYear month : yearMonths){
            if (!month.getIsExpense()){
                yearProfit += month.getValue();
            } else {
                yearExpense += month.getValue();
            }
        }
        return yearProfit - yearExpense;
    }

    public double getYearProfit() {
        double yearProfit=0;
        for (MonthInYear month : yearMonths){
            if (!month.getIsExpense()){
                yearProfit += month.getValue();
            }
        }
        return yearProfit;
    }

    public double getYearExpense() {
        double yearExpense=0;
        for (MonthInYear month : yearMonths){
            if (month.getIsExpense()){
                yearExpense += month.getValue();
            }
        }
        return yearExpense;
    }

}

class MonthInYear {
    private int month;
    private double value;
    private boolean isExpense;

    MonthInYear(int month, double value, boolean isExpense) {
        this.month = month;
        this.value = value;
        this.isExpense = isExpense;
    }

    public double getValue() {
        return value;
    }

    public boolean getIsExpense(){
        return isExpense;
    }
}
