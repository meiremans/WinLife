package eu.meiremans.winlife.app.business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nick on 16/11/2014.LIKE A BOSS
 */
public class MainGoal extends Goal implements Serializable {

ArrayList<SubGoal> subGoals = new ArrayList<>();

    public MainGoal(String goalDescription,Integer goalPoints){
        super(goalDescription,goalPoints);
    }
    public MainGoal(Integer id,String goalDescription,Integer goalPoints){
        super(id,goalDescription,goalPoints);
    }

    public void addSubGoals(SubGoal subgoal){
        subGoals.add(subgoal);
    }

    @Override
    public String toString() {
        return this.getGoalDescription();
    }


}
