public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        currentFloor--;
    }

    public void moveUp() {
        currentFloor++;
    }

    public void printCurrentFloor() {
        // пограничный случай (нулевой этаж в выводе некорректен)
        if (getCurrentFloor() != 0){
            System.out.println(getCurrentFloor());
        }

    }

    private void printErrorMsg() {
        System.out.println("Такого этажа не существует");
    }

    public void move(int floor) {
        if (floor >= minFloor && floor <= maxFloor) {
            while (currentFloor < floor) {
                moveUp();
                printCurrentFloor();
            }
            while (currentFloor > floor) {
                moveDown();
                printCurrentFloor();
            }
        } else {
            printErrorMsg();
        }
    }
}
