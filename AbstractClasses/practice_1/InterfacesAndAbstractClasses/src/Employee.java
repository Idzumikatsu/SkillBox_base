import java.util.Random;

public interface Employee {
    double getMonthSalary();

    default double initSalaryFixPart() {
        return (new Random())
                .doubles(50_000D, 100_000D)
                .iterator()
                .nextDouble();
    }
}
