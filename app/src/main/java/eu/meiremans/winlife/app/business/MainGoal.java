package eu.meiremans.winlife.app.business;

import java.util.ArrayList;

/**
 * Created by Nick on 16/11/2014.
 */
public class MainGoal extends Goal {

ArrayList<SubGoal> subGoals = new ArrayList<>();

    public MainGoal(String goalDescription,Integer goalPoints){
        super(goalDescription,goalPoints);
    }

    public void addSubGoals(SubGoal subgoal){
        subGoals.add(subgoal);
    }


}
