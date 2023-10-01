import java.sql.*;
import java.util.List;

public class DBConnection {

    private static Connection connection;

    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "rootuser";
    private static final int BUFFER_SIZE = 51200;

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
                                .append("PRIMARY KEY(id))")
                                .toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void writeDataIntoDB(XMLHandler handler) throws Exception {
        List<Voter> list = handler.getVotersList();
        list.forEach(voter -> {
            if (insertQuery.length() < BUFFER_SIZE){
                DBConnection.setInsertQuery(voter.getName(), voter.getBirthDay());
                return;
            }
            try {
                DBConnection.executeMultiInsert();
                insertQuery.setLength(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        DBConnection.executeMultiInsert();
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = new StringBuilder()
                .append("INSERT INTO voter_count(name, birthDate) ")
                .append("VALUES")
                .append(insertQuery.toString())
                .toString();
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void setInsertQuery(String name, String birthDay) {
        insertQuery.append((insertQuery.isEmpty() ? "" : ",") +
                "('" + name + "', '" + birthDay + "')");
    }
}
