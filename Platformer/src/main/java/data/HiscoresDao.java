package data;

import java.sql.*;


public class HiscoresDao {


    private String url;

    public HiscoresDao(String dbUrl) {
        this.url = "jdbc:sqlite:" +  dbUrl + ".db";
        this.createDB();
    }


    private String createTable() {
        return "CREATE TABLE IF NOT EXISTS hiscores(" +
                "id INTEGER PRIMARY KEY, " +
                "playerName TEXT NOT NULL, " +
                "score INTEGER NOT NULL);";
    }


    private void createDB() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement statement = conn.createStatement();
                statement.execute(createTable());
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    

}
