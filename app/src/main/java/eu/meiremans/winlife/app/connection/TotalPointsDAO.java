package eu.meiremans.winlife.app.connection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.TotalPoints;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.enums.Trophies.TrophyLevel;
import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyType;
import eu.meiremans.winlife.app.enums.database.GoalsColumns;
import eu.meiremans.winlife.app.enums.database.TrophiesColumns;
import eu.meiremans.winlife.app.enums.database.WinLifeTables;

/**
 * Created by Nick on 23/11/2014.LIKE A BOSS
 */
public class TotalPointsDAO {
    private MyDatabase db;

    public TotalPointsDAO(Context context){
        db = new MyDatabase(context);
    }

    public TotalPoints getCountAllTrophies(){
        TotalPoints totalPoints = new TotalPoints();
        totalPoints.setNumberBronze(getCountCompletedTrophyType(TrophyType.BRONZE));
        totalPoints.setNumberSilver(getCountCompletedTrophyType(TrophyType.SILVER));
        totalPoints.setNumberGold(getCountCompletedTrophyType(TrophyType.GOLD));

        return totalPoints;
    }

    public Integer getCountCompletedTrophyType(TrophyType trophyType){
        SQLiteDatabase dbw = db.getWritableDatabase();
        Integer count = null;
        String selectQuery = "SELECT COUNT(*) " +
                " FROM " + WinLifeTables.TROPHIES.getTableName() +
                " WHERE " + TrophiesColumns.TROPHIES_STATE.getColumnName() + " = \"" + TrophyState.COMPLETED.name() +
                "\" AND " + TrophiesColumns.TROPHIES_TYPE.getColumnName() + " = \"" + trophyType.name()+"\"";

        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                count = c.getInt(0);
            }while(c.moveToNext());
        }
        c.close();

        return count;
    }
}
