import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SQLExecutor executor = new SQLExecutor();
        String query =
                "SELECT DISTINCT pl.course_name AS c_name, " +
                " (COUNT(pl.course_name)/(max(MONTH(pl.subscription_date)) - min(MONTH(pl.subscription_date)) + 1)) avg_per_month" +
                " FROM purchaselist AS pl" +
                " WHERE pl.subscription_date >= '2018-01-01' AND pl.subscription_date < '2019-01-01'" +
                " GROUP BY c_name";
        ResultSet rs = executor.getResultSetFromExecuteQuery(query);
        System.out.println("Название курса" + " - " + "Среднее количество продаж в месяц за 2018 год");
        while (rs.next()) {
            System.out.println(rs.getString("c_name") + " - " + rs.getString("avg_per_month"));
        }
    }
}