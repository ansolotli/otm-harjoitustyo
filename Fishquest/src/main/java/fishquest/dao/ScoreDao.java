package fishquest.dao;

import fishquest.logics.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao {

    private final Database database;

    public ScoreDao(Database database) {
        this.database = database;
    }

    public Score save(Score score) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO HighScore (name, points) VALUES (?, ?)");
        stmt.setString(1, score.getName());
        stmt.setInt(2, score.getPoints());
        
        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM HighScore WHERE name = ? AND points = ?");
        stmt.setString(1, score.getName());
        stmt.setInt(2, score.getPoints());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Score s = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));

        stmt.close();
        rs.close();
        conn.close();

        return s;
    }

    public List<Score.ScoreStats> displayHighScoreByPoints() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT name, points FROM HighScore ORDER BY points DESC");

        ResultSet rs = stmt.executeQuery();
        List<Score.ScoreStats> stats = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            Integer points = rs.getInt("points");

            stats.add(new Score.ScoreStats(name, points));
        }

        rs.close();
        stmt.close();
        conn.close();

        return stats;
    }
}
