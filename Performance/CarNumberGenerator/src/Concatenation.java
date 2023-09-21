
public class Concatenation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StringBuilder builder = new StringBuilder();
        builder.append("some text some text some text".repeat(20_000));

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
