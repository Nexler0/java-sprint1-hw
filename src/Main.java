import java.util.*;

public class Main {
    static ArrayList<MonthReport> monthData;
    static ArrayList<YearReport> yearData;

    public static void main(String[] args) {
        monthData = new ArrayList<>();
        yearData = new ArrayList<>();
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
                if (InsertYear.getYear() == 0) {
                    InputData.readMonthExpenses(InsertYear.insertYear());
                } else {
                    InputData.readMonthExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                for (int index = 0; index < monthData.size(); index++) {
                    monthData.get(index).monthDataProcessing();
                }
            } else if (command == 2) {
                if (InsertYear.getYear() == 0) {
                    InputData.readYearExpenses(
                            InsertYear.insertYear());
                } else {
                    InputData.readYearExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                for (int index = 0; index < yearData.size(); index++) {
                    yearData.get(index).yearDataProcessing();
                }
            } else if (command == 3) {  //add the function checking of having data in the lists

                if (yearData.size() > 0 && monthData.size() > 0) {
                    System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                            + "\nСравнение месячной и годовой прибыли:");
                    for (int index = 0; index < monthData.size(); index++) {
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
                } else if (yearData.size() == 0) {
                    System.out.println("Не введены годовые данные!");
                } else {
                    System.out.println("Системная ошибка...");
                }
            } else if (command == 4) {
                if (monthData.size() > 0) {
                    System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                            + "\nМаксимальная прибыль:");
                    for (int index = 0; index < monthData.size(); index++) {
                        System.out.println(InputData.getNameMonth(index - 1));
                        monthData.get(index).getMaxProfitOnMonth();
                    }
                    System.out.println("Максимальная трата:");
                    for (int index = 0; index < monthData.size(); index++) {
                        System.out.println(InputData.getNameMonth(index - 1));
                        monthData.get(index).getMaxExpenseOnMonth();
                    }
                } else {
                    System.out.println("Не введены данные по месяцам!");
                }
            } else if (command == 5) {
                if (yearData.size() > 0) {
                    System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                            + "\nЧистая прибыль по месяцам: ");
                    for (int index = 0; index < yearData.size(); index++) {
                        System.out.print(InputData.getNameMonth(index - 1) + " - ");
                        yearData.get(index).getClearProfit();
                    }
                    double printProfit = 0;
                    double printExpense = 0;
                    double count = 0;
                    for (int index = 0; index < yearData.size(); index++) {
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
                InsertYear.yearIn = 0;
                System.out.println("Год сброшен!");
                yearData.clear();
                monthData.clear();
            } else if (command == 0) {
                System.out.println("Выход из программы"
                        + "\n------------------");
                break;
            }
        }
    }

    static void menu() {
        System.out.println("" +
                "\nВыберете действие:" +
                "\n1. Считать месячные отчеты." +
                "\n2. Считать годовые отчеты." +
                "\n3. Сверить отчеты." +
                "\n4. Вывести информацию о всех месячных отчётах." +
                "\n5. Вывести информацию о годовом отчёте." +
                "\n6. Рассмотреть другой год." +
                "\n0. Выход.");
    }
}

class InsertYear {
    static int yearIn = 0;

    static String insertYear() {
        String year;
        Scanner input = new Scanner(System.in);
        System.out.println("Введите год:");
        try {
            yearIn = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введите числовой символ");
        }
        return year = String.valueOf(yearIn);
    }

    public static int getYear() {
        return yearIn;
    }
}