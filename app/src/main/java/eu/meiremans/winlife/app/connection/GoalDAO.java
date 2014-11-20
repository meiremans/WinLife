package eu.meiremans.winlife.app.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.enums.GoalsTable;
import eu.meiremans.winlife.app.enums.WinLifeTables;

import java.util.ArrayList;

/**
 * Created by Nick on 19/11/2014.LIKE A BOSS
 */
public class GoalDAO {

    private MyDatabase db;

    public GoalDAO(Context context){
        db = new MyDatabase(context);
    }
    public ArrayList<Goal> getAllMainGoals(){
        String goalName;
        Integer goalPoints;
        ArrayList<Goal> mainGoals = new ArrayList<>();
        SQLiteDatabase dbw = db.getWritableDatabase();
        String selectQuery = "SELECT " +  GoalsTable.GOAL_NAME.getColumnName() + "," + GoalsTable.GOAL_POINT.getColumnName() +
        " FROM " + WinLifeTables.GOALS.getTableName() + " WHERE " + GoalsTable.GOAL_IS_PART_OF.getColumnName() + "=NULL";
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                goalName = c.getString(c.getColumnIndex(GoalsTable.GOAL_NAME.getColumnName()));
                goalPoints = c.getInt(c.getColumnIndex(GoalsTable.GOAL_POINT.getColumnName()));
                Goal goal = new MainGoal(goalName, goalPoints);
                mainGoals.add(goal);
            }while(c.moveToNext());
        }
        c.close();

        return mainGoals;

    }

    public void addGoal(Goal goal){

        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalsTable.GOAL_NAME.getColumnName(), goal.getGoalDescription());
        values.put(GoalsTable.GOAL_POINT.getColumnName(), goal.getGoalPoints());
        dbw.insert("goals", null, values);
        dbw.close(); // Closing database connection
    }




}
