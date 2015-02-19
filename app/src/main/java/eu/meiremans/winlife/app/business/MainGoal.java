package eu.meiremans.winlife.app.business;

import eu.meiremans.winlife.app.business.Trophies.Trophy;
import eu.meiremans.winlife.app.enums.Trophies.TrophyState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 16/11/2014.LIKE A BOSS
 */
public class MainGoal extends Goal implements Serializable {

ArrayList<Trophy> trophies = new ArrayList<>();

    public MainGoal(String goalDescription,Integer goalPoints){
        super(goalDescription,goalPoints);
    }
    public MainGoal(Integer id,String goalDescription,Integer goalPoints){
        super(id,goalDescription,goalPoints);
    }

    public void addTrophy(Trophy trophy){
        trophies.add(trophy);
    }

    public void setTrophies(ArrayList<Trophy> trophies) {
        this.trophies = trophies;
    }

    public ArrayList<Trophy> getTrophies() {
        return trophies;
    }

    public ArrayList<Trophy> completedTrophies(){
        ArrayList<Trophy> completed = new ArrayList<>();
        for(Trophy trophy : trophies){
            if(trophy.getTrophyState() == TrophyState.COMPLETED){
                completed.add(trophy);
            }
        }
        return completed;
    }

    public ArrayList<Trophy> incompletedTrophies(){
        ArrayList<Trophy> incompleted = new ArrayList<>();
        for(Trophy trophy : trophies){
            if(trophy.getTrophyState() == TrophyState.NOT_COMPLETED){
                incompleted.add(trophy);
            }
        }
        return incompleted;
    }

    @Override
    public String toString() {
        return this.getGoalDescription();
    }


}
