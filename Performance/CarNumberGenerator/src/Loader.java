import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        PrintWriter writer = new PrintWriter("res/numbers.txt");

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for (int regionCode = 0; regionCode < 100; regionCode++) {
            StringBuilder sb = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
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
            }
            writer.write(sb.toString());
        }
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
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
