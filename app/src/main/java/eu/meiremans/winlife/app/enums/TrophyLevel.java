package eu.meiremans.winlife.app.enums;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public enum TrophyLevel {
    LEVEL_1(0),
    LEVEL_2(200),
    LEVEL_3(600),
    LEVEL_4(1200),
    LEVEL_5(2400),
    LEVEL_6(4000),
    LEVEL_7(6000),
    LEVEL_8(8000),
    LEVEL_9(10000),
    LEVEL_10(12000),
    LEVEL_11(14000),
    LEVEL_12(16000),
    LEVEL_13(24000);

    private final Integer levelStart;

    private TrophyLevel(final Integer levelStart) {
        this.levelStart = levelStart;
    }

    public TrophyLevel getTrophyLevel(Integer totalPoints){
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


}
