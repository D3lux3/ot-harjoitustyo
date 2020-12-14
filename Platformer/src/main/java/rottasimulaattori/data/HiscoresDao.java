package rottasimulaattori.data;

import rottasimulaattori.game.utils.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Hiscores Data Access Object class.
 * Handles saving and reading of the players scores.
 */
public class HiscoresDao {

    private String url;

    /**
     * Creates Hiscores object.
     * Takes a string as a parameter which will be the database's filename.
     * @param dbUrl
     */
    public HiscoresDao(String dbUrl) {
        this.url = "jdbc:sqlite:" +  dbUrl + ".db";
        this.createDB();
    }


    /**
     * SQL Query that creates the table if it does not exists.
     * @return
     */
    private String createTable() {
        return "CREATE TABLE IF NOT EXISTS hiscores(" +
                "id INTEGER PRIMARY KEY, " +
                "playerName TEXT NOT NULL, " +
                "score INTEGER NOT NULL);";
    }

    /**
     * SQL Query that removes all entries from the hiscores table.
     * Mainly for testing purposes.
     * @return
     */
    private String removeAllEntries() {
        return "DELETE FROM hiscores";
    }

    /**
     * SQL Query that returns top ten best hiscores from the table ordered by score.
     * @return
     */
    private String getTopTenHiscores() {
        return "SELECT playerName, score FROM hiscores ORDER BY score DESC LIMIT 10";
    }

    /**
     * SQL Query that adds given values to the table.
     * @return
     */
    private String addPlayerQuery() {
        return "INSERT INTO hiscores(playerName, score) VALUES(?,?)";
    }

    /**
     * This method deletes all entries in the db.
     * Executes removeAllEntries SQL Query in the database.
     */
    public void clearDB() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement statement = conn.createStatement();
                statement.execute(removeAllEntries());
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method creates the database if not present.
     * Executes createTable SQL Query in the database.
     */
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

    /**
     * Saves given Player object to the database.
     * @param player
     */
    public void saveScores(Player player) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(this.addPlayerQuery())) {
            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns top 10 best player scores and returns them.
     * @return
     */
    public List<Player> getScores() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(this.getTopTenHiscores());
                while (rs.next()) {
                    players.add(new Player(rs.getString("playerName"), rs.getInt("score")));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

}
