public class TopManager implements Employee {
    private double salary;
    private Company company;
    private final double TARGET_INCOME = 10_000_000D;
    private final double BONUS = 1.5D;

    public TopManager(Company company) {
        this.salary = initSalaryFixPart();
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        if (company.getIncome() > TARGET_INCOME) {
            return salary * BONUS;
        } else {
            return salary;
        }
    }
}
