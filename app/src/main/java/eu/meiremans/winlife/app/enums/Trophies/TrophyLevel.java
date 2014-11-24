package eu.meiremans.winlife.app.enums.Trophies;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public enum TrophyLevel {
    /* THE ORDER IS IMPORTANT!
    * TODO: FROM LEVEL 13 EVERY NEXT LEVEL = +8000 THIS SHOULD BE IMPLEMENTED SOMEWHERE*/
    LEVEL_1(0,"1"),
    LEVEL_2(200,"2"),
    LEVEL_3(600,"3"),
    LEVEL_4(1200,"4"),
    LEVEL_5(2400,"5"),
    LEVEL_6(4000,"6"),
    LEVEL_7(6000,"7"),
    LEVEL_8(8000,"8"),
    LEVEL_9(10000,"9"),
    LEVEL_10(12000,"10"),
    LEVEL_11(14000,"11"),
    LEVEL_12(16000,"12"),
    LEVEL_13(24000,"13");

    private final Integer levelStart;
    private final String levelString;
    private TrophyLevel(final Integer levelStart,final String levelString) {
        this.levelStart = levelStart;
        this.levelString = levelString;
    }

public String getLevelString(){
    return levelString;
}
    public Integer getLevelStart(){
        return levelStart;
    }

    public static TrophyLevel getTrophyLevel(Integer totalPoints){
        final TrophyLevel[] t = TrophyLevel.values();
        int min = 0;
        int max = t.length  - 1;
        int i;
        do {
            i = (min + max) / 2;
            final int av = t[i].levelStart;
            if (totalPoints < av) {
                max = i;
            } else if (totalPoints > av) {
                if (i + 1 < t.length && totalPoints < t[i + 1].levelStart) {
                    break;
                }
                min = i + 1;
            }
        } while (totalPoints != t[i].levelStart && min < max);
        if (min == max) {
            return t[max];
        }
        return t[i];
    }

    public TrophyLevel getNextLevel() {
        return values()[(ordinal() + 1) % values().length];
    }


}
