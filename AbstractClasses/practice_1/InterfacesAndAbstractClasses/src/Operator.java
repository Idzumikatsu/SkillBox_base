public class Operator implements Employee{

    private double salary;
    private Company company;

    public Operator(Company company) {
        this.salary = initSalaryFixPart();
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}
