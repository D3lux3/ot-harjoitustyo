package rottasimulaattori.data;

import rottasimulaattori.game.utils.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    private String removeAllEntries() {
        return "DELETE FROM hiscores";
    }

    private String getTopTenHiscores() {
        return "SELECT playerName, score FROM hiscores ORDER BY score DESC LIMIT 10";
    }

    private String addPlayerQuery() {
        return "INSERT INTO hiscores(playerName, score) VALUES(?,?)";
    }

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
