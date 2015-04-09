package eu.meiremans.winlife.app.business.Trophies;

import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyValue;

import java.io.Serializable;

/**
 * Created by Nick on 21/11/2014.LIKE A BOSS
 */
public class Trophy implements Serializable{
    Integer Id;
    TrophyValue trophyValue;
    TrophyState trophyState;
    String trophyName;
    String trophyDescription;
    Integer mainGoalId;


    public Trophy (TrophyValue trophyValue,String trophyName,String trophyDescription,TrophyState trophyState){
        this.trophyValue = trophyValue;
        this.trophyName = trophyName;
        this.trophyDescription = trophyDescription;
        this.trophyState = trophyState;
    }

    public Integer getMainGoalId() {
        return mainGoalId;
    }

    public void setMainGoalId(Integer mainGoalId) {
        this.mainGoalId = mainGoalId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public TrophyValue getTrophyValue() {
        return trophyValue;
    }

    public void setTrophyValue(TrophyValue trophyValue) {
        this.trophyValue = trophyValue;
    }

    public TrophyState getTrophyState() {
        return trophyState;
    }

    public void setTrophyState(TrophyState trophyState) {
        this.trophyState = trophyState;
    }

    public String getTrophyName() {
        return trophyName;
    }

    public void setTrophyName(String trophyName) {
        this.trophyName = trophyName;
    }

    public String getTrophyDescription() {
        return trophyDescription;
    }

    public void setTrophyDescription(String trophyDescription) {
        this.trophyDescription = trophyDescription;
    }

    @Override
    public String toString(){
        return trophyName;
    }
}
