package eu.meiremans.winlife.app.business;

import eu.meiremans.winlife.app.enums.TrophyType;

/**
 * Created by Nick on 21/11/2014.LIKE A BOSS
 */
public class Trophy {
    TrophyType trophyType;
    Boolean isCompleted;
    String trophyName;
    String trophyDescription;


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
