package eu.meiremans.winlife.app.enums.database;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public enum TrophiesColumns {
    ID("id"),
    TROPHIES_TITLE("trophyTitle"),
    TROPHIES_DESCRIPTION("trophiesDescription"),
    TROPHIES_TYPE("trophyType"),
    TROPHIES_STATE("trophyState"),
    TROPHIES_GOAL("trophyGoal");

    private final String columnName;

    private TrophiesColumns(final String columnName) {
        this.columnName = columnName;
    }
    public String getColumnName() {
        return columnName;
    }

}
