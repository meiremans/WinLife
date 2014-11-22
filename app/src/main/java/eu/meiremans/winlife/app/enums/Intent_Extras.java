package eu.meiremans.winlife.app.enums;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public enum Intent_Extras {
    MAIN_GOAL("mainGOal");

    private final String id;

    private Intent_Extras(final String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
