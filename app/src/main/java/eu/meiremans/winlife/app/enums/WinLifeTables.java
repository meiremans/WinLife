package eu.meiremans.winlife.app.enums;

/**
 * Created by Nick on 20/11/2014.LIKE A BOSS
 */
public enum WinLifeTables {
    GOALS("goals");

    private final String tableName;

    private WinLifeTables(final String tableName) {
        this.tableName = tableName;
    }
    public String getTableName() {
        return tableName;
    }

}
