package eu.meiremans.winlife.app.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.SubGoal;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.enums.TrophyState;
import eu.meiremans.winlife.app.enums.TrophyType;
import eu.meiremans.winlife.app.enums.database.GoalsColumns;
import eu.meiremans.winlife.app.enums.database.TrophiesColumns;
import eu.meiremans.winlife.app.enums.database.WinLifeTables;

import java.util.ArrayList;
import java.util.List;

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
        values.put(TrophiesColumns.TROPHIES_STATE.getColumnName(), trophy.getTrophyState().name());
        values.put(TrophiesColumns.TROPHIES_GOAL.getColumnName(), trophy.getMainGoalId());
        dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        dbw.close(); // Closing database connection
    }

    public ArrayList<Trophy> getAllTrophiesForMainGoal(MainGoal mainGoal){
        Integer trophyId;
        String trophyTitle;
        String trophyDescription;
        TrophyType trophyType;
        TrophyState trophyState;

        ArrayList<Trophy> trophies = new ArrayList<>();
        SQLiteDatabase dbw = db.getWritableDatabase();

        String selectQuery = "SELECT " + TrophiesColumns.ID.getColumnName() + "," + TrophiesColumns.TROPHIES_TITLE.getColumnName() + "," + TrophiesColumns.TROPHIES_DESCRIPTION.TROPHIES_TYPE.getColumnName()
                + "," + TrophiesColumns.TROPHIES_STATE.getColumnName() +"," + TrophiesColumns.TROPHIES_GOAL.getColumnName() +
                " FROM " + WinLifeTables.TROPHIES.getTableName(); //+ " WHERE " + TrophiesColumns.TROPHIES_GOAL.getColumnName() +"="+ mainGoal.getId();
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                trophyId = c.getInt(c.getColumnIndex(TrophiesColumns.ID.getColumnName()));
                trophyTitle = c.getString(c.getColumnIndex(TrophiesColumns.TROPHIES_TITLE.getColumnName()));
                trophyDescription = c.getString(c.getColumnIndex(TrophiesColumns.TROPHIES_DESCRIPTION.getColumnName()));
                trophyType = TrophyType.valueOf(c.getString((c.getColumnIndex(TrophiesColumns.TROPHIES_DESCRIPTION.TROPHIES_TYPE.getColumnName()))));
                trophyState = TrophyState.valueOf(c.getString(c.getColumnIndex(TrophiesColumns.TROPHIES_DESCRIPTION.getColumnName())));
                Trophy trophy = new Trophy(trophyType,trophyTitle,trophyDescription,trophyState);
                trophy.setId(trophyId);
                trophies.add(trophy);
            }while(c.moveToNext());
        }
        c.close();

        return trophies;

    }
}
