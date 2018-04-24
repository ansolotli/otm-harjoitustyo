
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
    
    public Score saveOrUpdate(Score object) throws SQLException {
        Score byName = findByName(object.getName());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO HighScore (name) VALUES (?)");
            stmt.setString(1, object.getName());
            stmt.executeUpdate();
        }

        return findByName(object.getName());
    }
    
    private Score findByName(String name) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name, points FROM HighScore WHERE name = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new Score(result.getInt("id"), result.getString("name"), result.getInt("points"));
        }
    }
    
    public List<Score.ScoreStats> displayHighScore() throws SQLException{
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT name, points FROM HighScore ORDER BY points");

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
