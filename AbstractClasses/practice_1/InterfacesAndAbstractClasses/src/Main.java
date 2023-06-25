import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company google = new Company();
        initEmployees(google,180,80,10);

        print(google.getTopSalaryStaff(10));
        System.out.println();
        print(google.getLowestSalaryStaff(30));
        System.out.println(google.getEmployees().size()+ "\n");

        Collections.shuffle(google.getEmployees());
        int count = google.getEmployees().size() / 2;
        for (int i = 0; i < count; i++) {
            google.fire(google.getEmployees().get(i));
        }

        print(google.getTopSalaryStaff(10));
        System.out.println();
        print(google.getLowestSalaryStaff(30));
        System.out.println(google.getEmployees().size()+ "\n");
    }

    public static void initEmployees (Company company,int operators, int managers, int topManagers) {
        for (int i = 0; i < operators; i++) {
            company.hire(new Operator(company));
        }

        for (int i = 0; i < managers; i++) {
            company.hire(new Manager(company));
        }

        for (int i = 0; i < topManagers; i++) {
            company.hire(new TopManager(company));
        }
    }

    public static void print(List<Employee> list) {
        for (Employee employee : list) {
            System.out.println(new DecimalFormat( "###,###" )
                    .format(employee.getMonthSalary()) + " руб.");
        }
    }
}