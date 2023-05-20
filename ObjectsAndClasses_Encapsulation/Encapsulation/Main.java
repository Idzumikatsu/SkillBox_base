import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Task 1 testcase
        Elevator elevator = new Elevator(-3, 26);
        while (true) {
            System.out.print("Введите номер этажа: ");
            int floor = new Scanner(System.in).nextInt();
            elevator.move(floor);
        }

//        Task 2 testcases
//        Dimensions dimensions = new Dimensions(2, 3, 4);
//        System.out.println("Объем груза - " + dimensions.calcVolume() + "\n");
//
//        Cargo cargo = new Cargo(dimensions, 120, "Москва", true, "1-02-34", true);
//
//        Cargo copy = cargo.setMass(130);
//        System.out.println("Копия\n" + copy + "\nОригинал\n" + cargo + "\n");
//
//        copy = cargo.setDeliveryAddress("Бишкек");
//        System.out.println("Копия\n" + copy + "\nОригинал\n" + cargo + "\n");
//
//        copy = cargo.setDimensions(10,15,20);
//        System.out.println("Копия\n" + copy + "\nОригинал\n" + cargo);
    }
}
