package eu.meiremans.winlife.app.business;

import eu.meiremans.winlife.app.enums.Trophies.TrophyLevel;
import eu.meiremans.winlife.app.enums.Trophies.TrophyType;

/**
 * Created by Nick on 23/11/2014.LIKE A BOSS
 */
public class TotalPoints {


    Integer numberBronze;
    Integer numberSilver;
    Integer numberGold;

    public Integer getTotalPoints() {
        return (numberBronze * TrophyType.BRONZE.getTrophyPoints())
                + (numberSilver * TrophyType.SILVER.getTrophyPoints())
                + (numberGold * TrophyType.GOLD.getTrophyPoints());
    }

    public TrophyLevel getCurrentLevel() {
        return TrophyLevel.getTrophyLevel(getTotalPoints());
    }

    public Integer getPointsToNextLevel(){
       TrophyLevel trophyLevel =  getCurrentLevel();
        Integer nextLevelStart = trophyLevel.getNextLevel().getLevelStart();
        return nextLevelStart - getTotalPoints();
    }



    public Integer getNumberBronze() {
        return numberBronze;
    }

    public void setNumberBronze(Integer numberBronze) {
        this.numberBronze = numberBronze;
    }

    public Integer getNumberSilver() {
        return numberSilver;
    }

    public void setNumberSilver(Integer numberSilver) {
        this.numberSilver = numberSilver;
    }

    public Integer getNumberGold() {
        return numberGold;
    }

    public void setNumberGold(Integer numberGold) {
        this.numberGold = numberGold;
    }
}
