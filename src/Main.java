import java.util.*;

public class Main {

    public static void main(String[] args) {
        int command;
        boolean flag = true;
        HashMap<Integer, ArrayList<String>> monthListExpenses;
        ArrayList<String> yearListExpenses;
        DataProcessing dataProcessing;
        dataProcessing = new DataProcessing();
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
                if(InsertYear.getYear() == 0) {
                    monthListExpenses = InputData.readMonthExpenses(InsertYear.insertYear());
                } else {
                    monthListExpenses = InputData.readMonthExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                dataProcessing.monthDataProcessing(monthListExpenses);
            } else if (command == 2) {
                if(InsertYear.getYear() == 0){yearListExpenses = InputData.readYearExpenses(
                        InsertYear.insertYear());
                } else {
                    yearListExpenses = InputData.readYearExpenses(
                            String.valueOf(InsertYear.getYear()));
                }
                dataProcessing.yearDataProcessing(yearListExpenses);
            } else if (command == 3) {

            } else if (command == 4) {
                System.out.println(InsertYear.getYear());
                System.out.println("Максимальная прибыль:");
                dataProcessing.getMaxOnMonth(dataProcessing.monthProfit);
                System.out.println("\n" + "Максимальные расходы:");
                dataProcessing.getMaxOnMonth(dataProcessing.monthExpense);
            } else if (command == 5) {

            } else if (command == 0) {
                break;
            } else {
                System.out.println("Неверный ввод номера команды.");
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
                "\n0. Выход.");
    }

}
class InsertYear{
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