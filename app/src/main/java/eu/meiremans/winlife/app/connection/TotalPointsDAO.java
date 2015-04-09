package eu.meiremans.winlife.app.connection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.TotalPoints;
import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyValue;
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
        totalPoints.setNumberBronze(getCountCompletedTrophyType(TrophyValue.BRONZE));
        totalPoints.setNumberSilver(getCountCompletedTrophyType(TrophyValue.SILVER));
        totalPoints.setNumberGold(getCountCompletedTrophyType(TrophyValue.GOLD));

        return totalPoints;
    }

    public Integer getCountCompletedTrophyType(TrophyValue trophyValue){
        SQLiteDatabase dbw = db.getWritableDatabase();
        Integer count = null;
        String selectQuery = "SELECT COUNT(*) " +
                " FROM " + WinLifeTables.TROPHIES.getTableName() +
                " WHERE " + TrophiesColumns.TROPHIES_STATE.getColumnName() + " = \"" + TrophyState.COMPLETED.name() +
                "\" AND " + TrophiesColumns.TROPHIES_TYPE.getColumnName() + " = \"" + trophyValue.name()+"\"";

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
