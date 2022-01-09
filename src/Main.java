import java.util.*;

public class Main {

    public static void main(String[] args) {
        int command;
        HashMap<Integer, ArrayList<String>> monthListExpenses = new HashMap<>();
        ArrayList<String> yearListExpenses = new ArrayList<>();
        DataProcessing dataProcessing = new DataProcessing();
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
                    monthListExpenses = InputData.readMonthExpenses(InsertYear.insertYear());
                } else {
                    monthListExpenses = InputData.readMonthExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                dataProcessing.monthDataProcessing(monthListExpenses);
            } else if (command == 2) {
                if (InsertYear.getYear() == 0) {
                    yearListExpenses = InputData.readYearExpenses(
                            InsertYear.insertYear());
                } else {
                    yearListExpenses = InputData.readYearExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                dataProcessing.yearDataProcessing(yearListExpenses);
            } else if (command == 3) {
                System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                        + "\nСравнение месячной и годовой прибыли:");
                dataProcessing.compareData(dataProcessing.sumInMonth(
                        dataProcessing.monthProfit), dataProcessing.yearProfit);
                System.out.println("\nСравнение месячных и годовых расходов: ");
                dataProcessing.compareData(dataProcessing.sumInMonth(
                        dataProcessing.monthExpense), dataProcessing.yearExpenses);
            } else if (command == 4) {
                System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                        + "\nМаксимальная прибыль: ");
                dataProcessing.getMaxOnMonth(dataProcessing.monthProfit);
                System.out.println("\n" + "Максимальные расходы: ");
                dataProcessing.getMaxOnMonth(dataProcessing.monthExpense);
            } else if (command == 5) {
                System.out.println("Рассматриваемый год: " + InsertYear.getYear()
                        + "\nЧистая прибыль по месяцам: ");
                dataProcessing.yearClearProfit();
                System.out.println("Средняя прибыль за год: "
                        + dataProcessing.yearAverage(dataProcessing.yearProfit));
                System.out.println("Средние расходы за год: "
                        + dataProcessing.yearAverage(dataProcessing.yearExpenses));
            } else if (command == 6) {
                InsertYear.yearIn = 0;
                System.out.println("Год сброшен!");
                monthListExpenses.clear();
                yearListExpenses.clear();
                dataProcessing.monthProfit.clear();
                dataProcessing.monthExpense.clear();
                dataProcessing.yearProfit.clear();
                dataProcessing.yearExpenses.clear();
            } else if (command == 0) {
                System.out.println("Для выхода введите пароль: ");
                int pass;
                try {
                    pass = inputMenu.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Введите числовой символ");
                    continue;
                }
                if (pass == 1579) {
                    System.out.println("Выход из программы"
                            + "\n------------------");
                    break;
                } else {
                    System.out.println(" Неверный пароль! ");

                }
            } else {
                System.out.println("Неверный ввод номера команды.");
            }
        }
    }

    static void menu() {
        System.out.println(" " +
                "\n Выберете действие:" +
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