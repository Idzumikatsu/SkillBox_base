import java.sql.*;

public class SQLExecutor {
    private final String URL = "jdbc:mysql://localhost:3306/db_skillbox";
    private final String USER = "root";
    private final String PASS = "rootadmin";

    public ResultSet getResultSetFromExecuteQuery(String query) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new SQLException("Execution error");
    }


}
