package fishquest.dao;

import fishquest.logics.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class operates as an interface between the application's Score objects and an SQL database.
 */
public class ScoreDao {

    private final Database database;

    /**
     * @param database - a specific database used in the application
     */
    public ScoreDao(Database database) {
        this.database = database;
    }

    /**
     * Method saves a Score object into a database table HighScore.
     * @param score - an object to be saved
     * @return a saved Score object
     * @throws SQLException 
     */
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

    /**
     * Method displays all Scores saved to the database in descending order by points.
     * @return a list of Score objects
     * @throws SQLException 
     */
    public List<Score> displayHighScoreByPoints() throws SQLException {
        List<Score> highScoreList = new ArrayList<>();
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id, name, points FROM HighScore ORDER BY points DESC");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            highScoreList.add(new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("points")));
        }

        rs.close();
        stmt.close();
        conn.close();

        return highScoreList;
    }
}
