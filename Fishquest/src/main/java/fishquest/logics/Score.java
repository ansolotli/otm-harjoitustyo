
package fishquest.logics;

public class Score {
    private final Integer id;
    private final String name;
    private final Integer points;

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
