import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "rootuser";

    private static StringBuilder insertQuery = new StringBuilder();
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager
                        .getConnection(new StringBuilder()
                                .append("jdbc:mysql://localhost:3306/")
                                .append(dbName)
                                .append("?user=")
                                .append(dbUser)
                                .append("&password=")
                                .append(dbPass).toString());
                connection.createStatement()
                        .execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement()
                        .execute(new StringBuilder()
                                .append("CREATE TABLE voter_count(")
                                .append("id INT NOT NULL AUTO_INCREMENT, ")
                                .append("name TINYTEXT NOT NULL, ")
                                .append("birthDate DATE NOT NULL, ")
                                .append("`count` INT NOT NULL, ")
                                .append("PRIMARY KEY(id), ")
                                .append("UNIQUE KEY name_date(name(50), birthDate))")
                                .toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = new StringBuilder()
                .append("INSERT INTO voter_count(name, birthDate, `count`) ")
                .append("VALUES")
                .append(insertQuery.toString())
                .append("ON DUPLICATE KEY UPDATE `count`=`count` + 1")
                .toString();
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) {
        birthDay = birthDay.replace('.', '-');

        insertQuery.append((insertQuery.isEmpty() ? "" : ",") +
                "('" + name + "', '" + birthDay + "', 1)");
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
