package eu.meiremans.winlife.app.business;

/**
 * Created by Nick on 16/11/2014.
 */
public class Goal {
    Integer id;
    String goalDescription;
    Integer goalPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public Integer getGoalPoints() {
        return goalPoints;
    }

    public void setGoalPoints(Integer goalPoints) {
        this.goalPoints = goalPoints;
    }
}
