
package fishquest.logics;

/**
 * Class offers methods to get and set values of a Score object
 */
public class Score {
    private final Integer id;
    private final String name;
    private final Integer points;

    /**
     * @param id - identifier of a specific score object
     * @param name - name given by the player
     * @param points - number of points a player scored in a game
     */
    public Score(Integer id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public Integer getPoints() {
        return this.points;
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.points;
    }
}
