import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Company {

    private List<Employee> employees;
    private double income = 0D;

    public Company() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        count = checkCount(count);
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getMonthSalary))
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Employee> getTopSalaryStaff(int count) {
        count = checkCount(count);
        return employees.stream()
                .sorted((o1, o2) -> -Double.compare(o1.getMonthSalary(), o2.getMonthSalary()))
                .limit(count)
                .collect(Collectors.toList());
    }


    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employeeList) {
        employees.addAll(employeeList);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public double getIncome() {
        employees.stream()
                .filter(employee -> employee instanceof Manager)
                .forEach(employee -> income += ((Manager) employee).getPersonalIncome());
        return income;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    private int checkCount (int count){
        if (count > employees.size()) {
            return employees.size();
        } else {
            return Math.max(count, 0);
        }
    }
}
