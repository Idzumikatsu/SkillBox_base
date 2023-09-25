import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            List<Thread> threads = new ArrayList<>();
            try (ExecutorService service = Executors.newCachedThreadPool()) {
                for (int regionCode = 1; regionCode < 100; regionCode++) {
                    PrintWriter writer = new PrintWriter("res/" + regionCode + ".txt");
                    Thread t = getThread(regionCode, letters, writer);
                    service.execute(t);
                    threads.add(t);
                }
                for (Thread thread : threads) {
                    thread.join();
                }
            }
        } catch (FileNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static Thread getThread(int regionCode, char[] letters, PrintWriter writer) {
        return new Thread(() -> {
            for (int number = 1; number < 1000; number++) {
                StringBuilder sb = new StringBuilder();
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            sb.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(padNumber(regionCode, 2))
                                    .append("\n");
                        }
                    }
                }
                writer.write(sb.toString());
                writer.flush();
            }
        });
    }

    private static String padNumber(int number, int numberLength) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным.");
        }

        char[] paddedNumber = new char[numberLength];
        char[] numberChars = Integer.toString(number).toCharArray();

        int zerosToAdd = numberLength - numberChars.length;
        if (zerosToAdd <= 0) {
            return new String(numberChars);
        }

        Arrays.fill(paddedNumber, 0, zerosToAdd, '0');
        System.arraycopy(numberChars, 0, paddedNumber, zerosToAdd, numberChars.length);

        return new String(paddedNumber);
    }
}
