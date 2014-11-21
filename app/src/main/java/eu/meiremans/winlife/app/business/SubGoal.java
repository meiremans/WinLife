package eu.meiremans.winlife.app.business;

/**
 * Created by Nick on 19/11/2014.LIKE A BOSS
 */
public class SubGoal extends Goal {

    Integer isPartOf;

    public SubGoal(String goalDescription, Integer goalPoints, Integer isPartOf) {
        super(goalDescription, goalPoints);
        this.isPartOf = isPartOf;
    }

    public SubGoal(Integer id, String goalDescription, Integer goalPoints, Integer isPartOf) {
        super(id, goalDescription, goalPoints);
        this.isPartOf = isPartOf;
    }

    public Integer getIsPartOf() {
        return isPartOf;
    }

    public void setIsPartOf(Integer isPartOf) {
        this.isPartOf = isPartOf;
    }

    @Override
    public String toString() {
       return  this.getGoalDescription();
    }
}
