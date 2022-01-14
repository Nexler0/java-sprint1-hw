import java.util.ArrayList;

public class MonthReport {
    ArrayList<String> monthData;
    ArrayList<Item> monthItems;

    MonthReport(ArrayList<String> monthData) {
        this.monthData = monthData;
        monthItems = new ArrayList<>();
    }

    void processingMonthData() {
        for (int index = 1; index < monthData.size(); index++) {
            String[] value = monthData.get(index).split(",");
            monthItems.add(new Item(value[0],
                    Boolean.parseBoolean(value[1]),
                    Double.parseDouble(value[2]),
                    Integer.parseInt(value[3])));
        }
        if (monthData.size() > 0) {
            System.out.println("Данные получены");
        } else {
            System.out.println("Ошибка получения данных");
        }
    }

    void getMaxProfitOnMonth() {
        double maxProfit = 0;
        String productName = "";
        for (Item product : monthItems) {
            if (!product.getFlag()) {
                if (maxProfit < product.getPrice() * product.getQuantity()) {
                    maxProfit = product.getPrice() * product.getQuantity();
                    productName = product.getName();
                }
            }
        }
        System.out.println(productName + " - " + maxProfit);
    }

    void getMaxExpenseOnMonth() {
        double maxExpense = 0;
        String productName = "";
        for (Item product : monthItems) {
            if (product.getFlag()) {
                if (maxExpense < product.getPrice() * product.getQuantity()) {
                    maxExpense = product.getPrice() * product.getQuantity();
                    productName = product.getName();
                }
            }
        }
        System.out.println(productName + " - " + maxExpense);
    }

    public double getMonthSumProfit() {
        double maxSum = 0;
        for (Item product : monthItems) {
            if (!product.getFlag()) {
                maxSum += product.getPrice() * product.getQuantity();
            }
        }
        return maxSum;
    }

    public double getMonthSumExpense() {
        double maxSum = 0;
        for (Item product : monthItems) {
            if (product.getFlag()) {
                maxSum += product.getPrice() * product.getQuantity();
            }
        }
        return maxSum;
    }

}

class Item {
    private String name;
    private boolean isExpense;
    private double price;
    private int quantity;

    Item(String name, boolean isExpense, double price, int quantity) {
        this.name = name;
        this.isExpense = isExpense;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public boolean getFlag() {
        return isExpense;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}







