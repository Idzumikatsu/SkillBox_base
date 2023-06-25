import java.util.Random;

public class Manager implements Employee{
    private double salary;
    private double personalIncome;

    private Company company;
    private final double BONUS_PERCENT = 0.05D;

    public Manager(Company company) {
        this.salary = initSalaryFixPart();
        this.personalIncome = initPersonalIncome();
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return salary + (personalIncome * BONUS_PERCENT);
    }

    private double initPersonalIncome(){
        return (new Random())
                .doubles(115_000D, 140_000D)
                .iterator()
                .nextDouble();
    }

    public double getPersonalIncome() {
        return personalIncome;
    }
}
