package eu.meiremans.winlife.app.business.Trophies;

import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyType;

import java.io.Serializable;

/**
 * Created by Nick on 21/11/2014.LIKE A BOSS
 */
public class Trophy implements Serializable{
    Integer Id;
    TrophyType trophyType;
    TrophyState trophyState;
    String trophyName;
    String trophyDescription;
    Integer mainGoalId;


    public Trophy (TrophyType trophyType,String trophyName,String trophyDescription,TrophyState trophyState){
        this.trophyType = trophyType;
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

    public TrophyType getTrophyType() {
        return trophyType;
    }

    public void setTrophyType(TrophyType trophyType) {
        this.trophyType = trophyType;
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
