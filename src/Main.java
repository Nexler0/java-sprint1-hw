import java.util.*;

public class Main {
    static HashMap<Integer, MonthReport> monthData;
    static HashMap<Integer, YearReport> yearData;

    public static void main(String[] args) {
        monthData = new HashMap<>();
        yearData = new HashMap<>();
        int command;
        while (true) {
            menu();
            Scanner inputMenu = new Scanner(System.in);
            try {
                command = inputMenu.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите числовой символ");
                continue;
            }
            if (command == 1) {

                if (ConsideredYear.getYear() == 0) {
                    monthData = InputData.readMonthExpenses(ConsideredYear.enterYear());
                } else {
                    monthData = InputData.readMonthExpenses(String.valueOf(ConsideredYear.getYear()));
                }
                for (int index : monthData.keySet()) {
                    monthData.get(index).monthDataProcessing();
                }

            } else if (command == 2) {

                if (ConsideredYear.getYear() == 0) {
                    yearData = InputData.readYearExpenses(ConsideredYear.enterYear());
                } else {
                    yearData = InputData.readYearExpenses(String.valueOf(ConsideredYear.getYear()));
                }
                for (int index : yearData.keySet()) {
                    yearData.get(index).yearDataProcessing();
                }

            } else if (command == 3) {  //add the function checking of having data in the lists

                if (yearData.size() > 0 && monthData.size() > 0) {
                    System.out.println("Рассматриваемый год: " + ConsideredYear.getYear()
                            + "\nСравнение месячной и годовой прибыли:");
                    for (int index : monthData.keySet()) {
                        System.out.println(InputData.getNameMonth(index - 1));
                        System.out.print(" Прибыль: ");
                        double year = yearData.get(index).getYearProfit();
                        double month = monthData.get(index).getMonthSumProfit();
                        if (month == year) {
                            System.out.println(monthData.get(index).getMonthSumProfit()
                                    + " - " + yearData.get(index).getYearProfit());
                        } else {
                            System.out.println(monthData.get(index).getMonthSumProfit()
                                    + " - " + yearData.get(index).getYearProfit() + " -Ошибка ");
                        }
                        System.out.print(" Расход: ");
                        year = yearData.get(index).getYearExpense();
                        month = monthData.get(index).getMonthSumExpense();
                        if (month == year) {
                            System.out.println(monthData.get(index).getMonthSumExpense()
                                    + " - " + yearData.get(index).getYearExpense());
                        } else {
                            System.out.println(monthData.get(index).getMonthSumExpense()
                                    + " - " + yearData.get(index).getYearExpense() + " -Ошибка ");
                        }
                    }
                } else if (monthData.size() == 0) {
                    System.out.println("Не введены данные по месяцам!");
                } else {
                    System.out.println("Не введены годовые данные!");
                }

            } else if (command == 4) {

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

            } else if (command == 5) {

                if (yearData.size() > 0) {
                    System.out.println("Рассматриваемый год: " + ConsideredYear.getYear()
                            + "\nЧистая прибыль по месяцам: ");
                    for (int index : yearData.keySet()) {
                        System.out.print(InputData.getNameMonth(index - 1) + " - ");
                        System.out.println(yearData.get(index).getClearProfit());
                    }
                    double printProfit = 0;
                    double printExpense = 0;
                    double count = 0;
                    for (int index : yearData.keySet()) {
                        printProfit += yearData.get(index).getYearProfit();
                        count = yearData.size();
                        printExpense += yearData.get(index).getYearExpense();
                    }
                    System.out.println("Средняя прибыль за год: " + printProfit / count);
                    System.out.println("Средние расходы за год: " + printExpense / count);
                } else {
                    System.out.println("Не введены годовые данные!");
                }

            } else if (command == 6) {

                ConsideredYear.yearIn = 0;
                System.out.println("Год сброшен!");
                yearData.clear();
                monthData.clear();

            } else if (command == 0) { // delete password

                System.out.println("Выход из программы"
                        + "\n------------------");
                break;
            }
        }
    }

    static void menu() {
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