public class Main {

    public static void main(String[] args) {

        //Task 1 testcases
        Arithmetic test = new Arithmetic(120,-54);
        System.out.println(
                test.sum() +
                "\n" + test.multiplication() +
                "\n" + test.min() +
                "\n" + test.max() +
                "\n======================="
        );

        //Task 2 testcases
        Basket basket = new Basket();
        basket.add("Milk", 40, 3, 900);
        System.out.println(basket.getTotalWeight());
        basket.add("Watermelon",150,2,4500);
        System.out.println(basket.getTotalWeight());
        basket.clear();
        basket.add("Ice Cream", 300, 4, 95);
        System.out.println(basket.getTotalWeight());
    }
}
