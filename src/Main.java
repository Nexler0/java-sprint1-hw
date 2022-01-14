import java.util.*;

public class Main {
    static HashMap<Integer, MonthReport> monthData;
    static ArrayList<ArrayList<String>> yearData;
    static YearReport yearReport;

    public static void main(String[] args) {
        monthData = new HashMap<>();
        yearData = new ArrayList<>();
        int command;
        while (true) {
            printMenu();
            Scanner inputMenu = new Scanner(System.in);
            try {
                command = inputMenu.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите числовой символ");
                continue;
            }
            if (command == 1) {
                readMonthData();
            } else if (command == 2) {
                readYearData();
            } else if (command == 3) {
                comparisonYearAndMonthReports();
            } else if (command == 4) {
                printMonthsReport();
            } else if (command == 5) {
                printYearReport();
            } else if (command == 6) {
                removeConsideredYear();
            } else if (command == 0) {
                System.out.println("Выход из программы"
                        + "\n------------------");
                break;
            }
        }
    }

    static void readMonthData() {
        if (ConsideredYear.getYear() == 0) {
            monthData = InputData.readMonthExpenses(ConsideredYear.enterYear());
        } else {
            monthData = InputData.readMonthExpenses(String.valueOf(ConsideredYear.getYear()));
        }
        for (int index : monthData.keySet()) {
            monthData.get(index).processingMonthData();
        }
    }

    static void readYearData() {
        if (ConsideredYear.getYear() == 0) {
            yearData = InputData.readYearExpenses(ConsideredYear.enterYear());
        } else {
            yearData = InputData.readYearExpenses(String.valueOf(ConsideredYear.getYear()));
        }
        for (ArrayList<String> data : yearData) {
            yearReport = new YearReport(data);
            yearReport.processingYearData();
        }
    }

    static void printMonthsReport() {
        if (monthData.size() > 0) {
            System.out.println("Рассматриваемый год: " + ConsideredYear.getYear());
            for (int index : monthData.keySet()) {
                System.out.println(InputData.getNameMonth(index - 1));
                System.out.print("Максимальная пибыль: ");
                monthData.get(index).getMaxProfitOnMonth();
                System.out.print("Максимальная трата: ");
                monthData.get(index).getMaxExpenseOnMonth();
            }
        } else {
            System.out.println("Не введены данные по месяцам!");
        }
    }

    static void printYearReport() {
        if (yearData.size() > 0) {
            System.out.println("Рассматриваемый год: " + ConsideredYear.getYear()
                    + "\nЧистая прибыль по месяцам: ");
            yearReport.getClearProfit();
            System.out.println("Средняя прибыль за год: "
                    + yearReport.getYearProfit() / yearReport.getMonthCount());
            System.out.println("Средние расходы за год: "
                    + yearReport.getYearExpense() / yearReport.getMonthCount());
        } else {
            System.out.println("Не введены годовые данные!");
        }
    }

    static void comparisonYearAndMonthReports() {
        int indexMonthsInYearReport = 0;
        if (yearData.size() > 0 && monthData.size() > 0) {
            System.out.println("Рассматриваемый год: " + ConsideredYear.getYear()
                    + "\nСравнение месячной и годовой прибыли:");
            for (int index : monthData.keySet()) {
                System.out.println(InputData.getNameMonth(index - 1));
                System.out.print(" Прибыль: ");
                double monthProfitInYearReport = yearReport.getMonthsProfit(indexMonthsInYearReport);
                double monthProfitInMonthReport = monthData.get(index).getMonthSumProfit();
                if (monthProfitInMonthReport == monthProfitInYearReport) {
                    System.out.println(monthProfitInMonthReport + " - " + monthProfitInYearReport);
                } else {
                    System.out.println(monthProfitInMonthReport + " - " + monthProfitInYearReport + " -Ошибка ");
                }
                System.out.print(" Расход: ");
                double monthExpenseInYearReport = yearReport.getMonthsExpense(indexMonthsInYearReport);
                double monthExpenseInMonthReport = monthData.get(index).getMonthSumExpense();
                if (monthExpenseInMonthReport == monthExpenseInYearReport) {
                    System.out.println(monthExpenseInMonthReport + " - " + monthExpenseInYearReport);
                } else {
                    System.out.println(monthExpenseInMonthReport + " - " + monthExpenseInYearReport + " -Ошибка ");
                }
                indexMonthsInYearReport += 2;
            }
        } else if (monthData.size() == 0) {
            System.out.println("Не введены данные по месяцам!");
        } else {
            System.out.println("Не введены годовые данные!");
        }
    }

    static void removeConsideredYear() {
        ConsideredYear.yearIn = 0;
        System.out.println("Год сброшен!");
        yearData.clear();
        monthData.clear();
    }

    static void printMenu() {
        System.out.println("""
                                        
                Выберете действие:
                1. Считать месячные отчеты.
                2. Считать годовые отчеты.
                3. Сверить отчеты.
                4. Вывести информацию о всех месячных отчётах.
                5. Вывести информацию о годовом отчёте.
                6. Рассмотреть другой год.
                0. Выход.
                """);
    }
}

class ConsideredYear {
    static int yearIn = 0;

    static String enterYear() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите год:");
        try {
            yearIn = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введите числовой символ");
        }
        return String.valueOf(yearIn);
    }

    public static int getYear() {
        return yearIn;
    }
}