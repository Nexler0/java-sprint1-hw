import java.util.ArrayList;

public class YearReport {
    ArrayList<String> yearData;
    ArrayList<MonthInYear> monthsInYearReport;

    YearReport(ArrayList<String> yearData) {
        this.yearData = yearData;
        monthsInYearReport = new ArrayList<>();
    }

    void processingYearData() {
        for (String str : yearData) {
            String[] value = str.split(",");
            monthsInYearReport.add(new MonthInYear(
                    Integer.parseInt(value[0]),
                    Double.parseDouble(value[1]),
                    Boolean.parseBoolean(value[2])));
        }
        if (monthsInYearReport.size() > 0) {
            System.out.println("Данные получены");
        } else {
            System.out.println("Ошибка получения данных");
        }
    }

    public void getClearProfit() {
        double yearProfit = 0;
        double yearExpense = 0;
        int monthNum;
        int monthOldNum = 1;
        int i;
        for (i = 0; i < monthsInYearReport.size(); i++) {
            monthNum = monthsInYearReport.get(i).getMonth();
            if (monthOldNum == monthNum) {
                if (!monthsInYearReport.get(i).getIsExpense()) {
                    yearProfit = monthsInYearReport.get(i).getValue();
                } else {
                    yearExpense = monthsInYearReport.get(i).getValue();
                }
            } else {
                monthOldNum = monthsInYearReport.get(i).getMonth();
                i--;
                System.out.println(InputData.getNameMonth(monthsInYearReport.get(i).getMonth() - 1)
                        + " " + (yearProfit - yearExpense));
            }
        }
        System.out.println(InputData.getNameMonth(monthsInYearReport.get(i - 1).getMonth() - 1)
                + " " + (yearProfit - yearExpense));
    }

    public double getYearProfit() {
        double yearProfit = 0;
        for (MonthInYear month : monthsInYearReport) {
            if (!month.getIsExpense()) {
                yearProfit += month.getValue();
            }
        }
        return yearProfit;
    }

    public double getMonthsProfit(int key) {
        double yearMonthProfit = 0;
        if (!monthsInYearReport.get(key).getIsExpense()) {
            yearMonthProfit = monthsInYearReport.get(key).getValue();
        } else if (!monthsInYearReport.get(key + 1).getIsExpense()) {
            yearMonthProfit = monthsInYearReport.get(key + 1).getValue();
        }
        return yearMonthProfit;
    }

    public double getMonthsExpense(int key) {
        double yearMonthExpense = 0;
        if (monthsInYearReport.get(key).getIsExpense()) {
            yearMonthExpense = monthsInYearReport.get(key).getValue();
        } else if (monthsInYearReport.get(key + 1).getIsExpense()) {
            yearMonthExpense = monthsInYearReport.get(key + 1).getValue();
        }
        return yearMonthExpense;
    }

    public double getYearExpense() {
        double yearExpense = 0;
        for (MonthInYear month : monthsInYearReport) {
            if (month.getIsExpense()) {
                yearExpense += month.getValue();
            }
        }
        return yearExpense;
    }

    public int getMonthCount() {
        int monthCount = 0;
        for (MonthInYear month : monthsInYearReport) {
            if (month.getMonth() != 0) {
                monthCount++;
            }
        }
        return monthCount / 2;
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

    public int getMonth() {
        return month;
    }

    public double getValue() {
        return value;
    }

    public boolean getIsExpense() {
        return isExpense;
    }
}
