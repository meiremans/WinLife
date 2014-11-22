package eu.meiremans.winlife.app.business;

import eu.meiremans.winlife.app.enums.TrophyType;

/**
 * Created by Nick on 21/11/2014.LIKE A BOSS
 */
public class Trophy {
    Integer Id;
    TrophyType trophyType;
    Boolean isCompleted;
    String trophyName;
    String trophyDescription;


    public Trophy (TrophyType trophyType,String trophyName,String trophyDescription,Boolean isCompleted){
        this.trophyType = trophyType;
        this.trophyName = trophyName;
        this.trophyDescription = trophyDescription;
        this.isCompleted = isCompleted;
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

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
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
}
