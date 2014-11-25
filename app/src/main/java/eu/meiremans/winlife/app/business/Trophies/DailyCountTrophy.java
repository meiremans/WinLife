package eu.meiremans.winlife.app.business.Trophies;

import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyType;

/**
 * Created by Nick on 24/11/2014.LIKE A BOSS
 */
public class DailyCountTrophy extends Trophy {
    Boolean alreadyAskedToday;
    Integer dayCount;
    Integer endDayStreak;

    public DailyCountTrophy(TrophyType trophyType,String trophyName,String trophyDescription,TrophyState trophyState,Boolean alreadyAskedToday,Integer dayCount){
        super(trophyType,trophyName,trophyDescription,trophyState);
        this.alreadyAskedToday = alreadyAskedToday;
        this.dayCount = dayCount;
    }

    public Boolean getAlreadyAskedToday() {
        return alreadyAskedToday;
    }

    public void setAlreadyAskedToday(Boolean alreadyAskedToday) {
        this.alreadyAskedToday = alreadyAskedToday;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Integer getEndDayStreak() {
        return endDayStreak;
    }

    public void setEndDayStreak(Integer endDayStreak) {
        this.endDayStreak = endDayStreak;
    }
}
