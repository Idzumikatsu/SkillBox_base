import java.util.ArrayList;
import java.util.List;

public class Basket {
    private static int allBasketsPrice = 0;
    private static int allBasketsItemsCount = 0;
    private static int basketsCount = 0;

    private List<String> items;
    private int priceLimit;
    private int oneBasketItemsCount = 0;
    private int oneBasketPrice = 0;
    private double basketWeight = 0;


    public Basket() {
        increaseBasketsCount(1);
        this.items = new ArrayList<>();
        this.priceLimit = 1000000;
    }

    public Basket(int priceLimit) {
        this();
        this.priceLimit = priceLimit;
    }

    public Basket(String items, int oneBasketPrice) {
        this();
        this.items.add(items);
        this.oneBasketPrice = oneBasketPrice;
        increaseOneBasketItemsCount(1);
    }

    public static int getBasketsCount() {
        return basketsCount;
    }

    public int getOneBasketItemsCount() {
        return oneBasketItemsCount;
    }

    private static void increaseBasketsCount(int count) {
        basketsCount += count;
    }

    private void increaseOneBasketItemsCount(int count) {
        oneBasketItemsCount += count;
    }

    private void increaseAllBasketsItemsCount(int count) {
        allBasketsItemsCount += count;
    }

    public static int getAllBasketsPrice() {
        return allBasketsPrice;
    }

    public static int getAllBasketsItemsCount() {
        return allBasketsItemsCount;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = items.stream().anyMatch(item -> item.contains(name));

        if (oneBasketPrice + count * price >= priceLimit) {
            error = true;
        }

        if (error) {
            System.out.println("Вы пытались добавить товар " + name + ", который уже есть в корзине");
        } else {
            items.add(name + " - " + count + " шт. - " + price + " Руб.");
            oneBasketPrice += count * price;
            incAllBasketPrice(count * price);
            increaseOneBasketItemsCount(count);
            increaseAllBasketsItemsCount(count);
        }
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        basketWeight += weight * count;
    }

    public double getBasketWeight() {
        return basketWeight;
    }

    public void clear() {
        items.clear();
        basketWeight = 0;

        if ((allBasketsItemsCount - oneBasketItemsCount) >= 0) {
            allBasketsItemsCount -= oneBasketItemsCount;
        }
        oneBasketItemsCount = 0;

        if ((allBasketsPrice - oneBasketPrice) >= 0) {
            allBasketsPrice -= oneBasketPrice;
        }
        oneBasketPrice = 0;
    }

    public int getOneBasketPrice() {
        return oneBasketPrice;
    }


    private static void incAllBasketPrice(int count) {
        allBasketsPrice += count;
    }

    public static int calcAvgItemPrice() {
        return getAllBasketsPrice() / getAllBasketsItemsCount();
    }

    public static int calcAvgBasketPrice() {
        return getAllBasketsPrice() / getBasketsCount();
    }

    public void print() {
        if (!items.isEmpty()) {
            System.out.println("Товары в корзине: ");
            items.forEach(System.out::println);
        } else {
            System.out.println("Корзина пуста");
        }
    }
}
