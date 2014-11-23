package eu.meiremans.winlife.app.enums.Trophies;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public enum TrophyType {
    BRONZE(15),
    SILVER(30),
    GOLD(90),
    PLATINUM(180);

    private final Integer trophyPoints;

    private TrophyType(final Integer trophyPoints) {
        this.trophyPoints = trophyPoints;
    }
    public Integer getTrophyPoints() {
        return trophyPoints;
    }
}
