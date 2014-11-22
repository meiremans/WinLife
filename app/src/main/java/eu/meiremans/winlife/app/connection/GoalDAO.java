package eu.meiremans.winlife.app.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.SubGoal;
import eu.meiremans.winlife.app.enums.database.GoalsColumns;
import eu.meiremans.winlife.app.enums.database.WinLifeTables;

import java.util.ArrayList;

/**
 * Created by Nick on 19/11/2014.LIKE A BOSS
 */
public class GoalDAO {

    private MyDatabase db;

    public GoalDAO(Context context){
        db = new MyDatabase(context);
    }
    public ArrayList<MainGoal> getAllMainGoals(){
        Integer goalId;
        String goalName;
        Integer goalPoints;
        ArrayList<MainGoal> mainGoals = new ArrayList<>();
        SQLiteDatabase dbw = db.getWritableDatabase();
        //if goalIsPartOf is null, then it means its a main goal
        String selectQuery = "SELECT " + GoalsColumns.ID.getColumnName() + "," + GoalsColumns.GOAL_NAME.getColumnName() + "," + GoalsColumns.GOAL_POINT.getColumnName() +
                " FROM " + WinLifeTables.GOALS.getTableName() + " WHERE " + GoalsColumns.GOAL_IS_PART_OF.getColumnName() + " is null";
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                goalId = c.getInt(c.getColumnIndex(GoalsColumns.ID.getColumnName()));
                goalName = c.getString(c.getColumnIndex(GoalsColumns.GOAL_NAME.getColumnName()));
                goalPoints = c.getInt(c.getColumnIndex(GoalsColumns.GOAL_POINT.getColumnName()));
                MainGoal mainGoal = new MainGoal(goalId,goalName, goalPoints);
                mainGoals.add(mainGoal);
            }while(c.moveToNext());
        }
        c.close();

        return mainGoals;

    }

    public ArrayList<SubGoal> getAllSubGoals(MainGoal maingoal){
        Integer goalId;
        String goalName;
        Integer goalPoints;
        ArrayList<SubGoal> subGoals = new ArrayList<>();
        SQLiteDatabase dbw = db.getWritableDatabase();
        //if goalIsPartOf is null, then it means its a main goal
        String selectQuery = "SELECT " + GoalsColumns.ID.getColumnName() + "," + GoalsColumns.GOAL_NAME.getColumnName() + "," + GoalsColumns.GOAL_POINT.getColumnName() + "," + GoalsColumns.GOAL_IS_PART_OF.getColumnName() +
                " FROM " + WinLifeTables.GOALS.getTableName() + " WHERE " + GoalsColumns.GOAL_IS_PART_OF.getColumnName() +"="+ maingoal.getId();
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                goalId = c.getInt(c.getColumnIndex(GoalsColumns.ID.getColumnName()));
                goalName = c.getString(c.getColumnIndex(GoalsColumns.GOAL_NAME.getColumnName()));
                goalPoints = c.getInt(c.getColumnIndex(GoalsColumns.GOAL_POINT.getColumnName()));
                SubGoal subGoal = new SubGoal(goalId,goalName, goalPoints,maingoal.getId());
                subGoals.add(subGoal);
            }while(c.moveToNext());
        }
        c.close();

        return subGoals;

    }



    public void addMainGoal(MainGoal goal){

        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalsColumns.GOAL_NAME.getColumnName(), goal.getGoalDescription());
        values.put(GoalsColumns.GOAL_POINT.getColumnName(), goal.getGoalPoints());
        long id = dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        goal.setId(safeLongToInt(id));
        dbw.close(); // Closing database connection
    }
    public void addSubGoal(SubGoal goal){

        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalsColumns.GOAL_NAME.getColumnName(), goal.getGoalDescription());
        values.put(GoalsColumns.GOAL_POINT.getColumnName(), goal.getGoalPoints());
        values.put(GoalsColumns.GOAL_IS_PART_OF.getColumnName(), goal.getIsPartOf());
        dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        dbw.close(); // Closing database connection
    }
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }



}
