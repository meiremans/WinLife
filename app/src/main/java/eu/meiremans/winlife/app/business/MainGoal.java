package eu.meiremans.winlife.app.business;

import eu.meiremans.winlife.app.business.Trophies.Trophy;

import java.io.Serializable;
import java.util.ArrayList;

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

    @Override
    public String toString() {
        return this.getGoalDescription();
    }


}
