public class Main {
    public static void main(String[] args) {
//      Task 1 testcase
        Container container = new Container();
        container.addCount(5672);
        container.addCount(654868);
        System.out.println(container.getCount());

//      Task 2 testcase
        charToDecimalUnicode();
    }

    public static void charToDecimalUnicode(){
        for (int i = 'А'; i <= (int) 'я'; i++) {
            System.out.println((char) i + " - " + i + " ");
            switch ((char) i) {
                case 'Е' -> {
                    i = 'Ё';
                    System.out.println((char) i + " - " + i + " ");
                    i = 'Е';
                }
                case 'е' -> {
                    i = 'ё';
                    System.out.println((char) i + " - " + i + " ");
                    i = 'е';
                }
            }
        }
    }
}
