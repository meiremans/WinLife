package eu.meiremans.winlife.app.enums;

/**
 * Created by Nick on 20/11/2014.LIKE A BOSS
 */
public enum GoalsTable {
    ID("id"),
    GOAL_NAME("goalName"),
    GOAL_POINT("goalPoint"),
    GOAL_IS_PART_OF("goalIsPartOf");

    private final String columnName;

    private GoalsTable(final String columnName) {
        this.columnName = columnName;
    }
    public String getColumnName() {
        return columnName;
    }


}
