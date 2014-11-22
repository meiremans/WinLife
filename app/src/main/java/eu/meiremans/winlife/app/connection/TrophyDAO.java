package eu.meiremans.winlife.app.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.enums.database.TrophiesColumns;
import eu.meiremans.winlife.app.enums.database.WinLifeTables;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public class TrophyDAO {
    private MyDatabase db;

    public TrophyDAO(Context context){
        db = new MyDatabase(context);
    }

    public void addTrophy(Trophy trophy){

        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TrophiesColumns.TROPHIES_TITLE.getColumnName(), trophy.getTrophyName());
        values.put(TrophiesColumns.TROPHIES_DESCRIPTION.getColumnName(), trophy.getTrophyDescription());
        values.put(TrophiesColumns.TROPHIES_TYPE.getColumnName(), trophy.getTrophyType().name());//used the name for storing in the db, most convienent solution;
        values.put(TrophiesColumns.TROPHIES_IS_COMPLETED.getColumnName(), trophy.getIsCompleted());
        values.put(TrophiesColumns.TROPHIES_GOAL.getColumnName(), trophy.getMainGoalId());
        dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        dbw.close(); // Closing database connection
    }
}
