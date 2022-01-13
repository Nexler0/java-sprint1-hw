import java.util.ArrayList;

public class MonthReport {
    ArrayList<String> monthData;
    ArrayList<Item> monthItems = new ArrayList<>();

    MonthReport(ArrayList<String> monthData) {
        this.monthData = monthData;
    }

    void monthDataProcessing() {
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
        String index = "";
        for (Item staff : monthItems){
            if (!staff.getFlag()){
                if (maxProfit < staff.getPrice()*staff.getQuantity()){
                    maxProfit = staff.getPrice()*staff.getQuantity();
                    index = staff.getName();
                }
            }
        }
        System.out.println(index + " - " + maxProfit);
    }

    void getMaxExpenseOnMonth() {
        double maxExpense = 0;
        String index = "";
        for (Item staff : monthItems){
            if (staff.getFlag()){
                if (maxExpense < staff.getPrice()*staff.getQuantity()){
                    maxExpense = staff.getPrice()*staff.getQuantity();
                    index = staff.getName();
                }
            }
        }
        System.out.println(index + " - " + maxExpense);
    }

    public double getMonthSumProfit() {
        double maxSum = 0;
        for (Item staff : monthItems){
            if (!staff.getFlag()){
                maxSum += staff.getPrice() * staff.getQuantity();
            }
        }
        return maxSum;
    }

    public double getMonthSumExpense() {
        double maxSum = 0;
        for (Item staff : monthItems){
            if (staff.getFlag()){
                maxSum += staff.getPrice() * staff.getQuantity();
            }
        }
        return maxSum;
    }

}

class Item {
    private  String name;
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

    public boolean getFlag(){
        return isExpense;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}







