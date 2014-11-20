package eu.meiremans.winlife.app.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.SubGoal;
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
    public ArrayList<MainGoal> getAllMainGoals(){
        Integer goalId;
        String goalName;
        Integer goalPoints;
        ArrayList<MainGoal> mainGoals = new ArrayList<>();
        SQLiteDatabase dbw = db.getWritableDatabase();
        //if goalIsPartOf is null, then it means its a main goal
        String selectQuery = "SELECT " + GoalsTable.ID.getColumnName() + "," + GoalsTable.GOAL_NAME.getColumnName() + "," + GoalsTable.GOAL_POINT.getColumnName() +
                " FROM " + WinLifeTables.GOALS.getTableName() + " WHERE " + GoalsTable.GOAL_IS_PART_OF.getColumnName() + " is null";
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                goalId = c.getInt(c.getColumnIndex(GoalsTable.ID.getColumnName()));
                goalName = c.getString(c.getColumnIndex(GoalsTable.GOAL_NAME.getColumnName()));
                goalPoints = c.getInt(c.getColumnIndex(GoalsTable.GOAL_POINT.getColumnName()));
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
        String selectQuery = "SELECT " + GoalsTable.ID.getColumnName() + "," + GoalsTable.GOAL_NAME.getColumnName() + "," + GoalsTable.GOAL_POINT.getColumnName() + "," + GoalsTable.GOAL_IS_PART_OF.getColumnName() +
                " FROM " + WinLifeTables.GOALS.getTableName() + " WHERE " + GoalsTable.GOAL_IS_PART_OF.getColumnName() + maingoal.getId();
        Cursor c = dbw.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                goalId = c.getInt(c.getColumnIndex(GoalsTable.ID.getColumnName()));
                goalName = c.getString(c.getColumnIndex(GoalsTable.GOAL_NAME.getColumnName()));
                goalPoints = c.getInt(c.getColumnIndex(GoalsTable.GOAL_POINT.getColumnName()));
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
        values.put(GoalsTable.GOAL_NAME.getColumnName(), goal.getGoalDescription());
        values.put(GoalsTable.GOAL_POINT.getColumnName(), goal.getGoalPoints());
        dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        dbw.close(); // Closing database connection
    }
    public void addSubGoal(SubGoal goal){

        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalsTable.GOAL_NAME.getColumnName(), goal.getGoalDescription());
        values.put(GoalsTable.GOAL_POINT.getColumnName(), goal.getGoalPoints());
        values.put(GoalsTable.GOAL_IS_PART_OF.getColumnName(), goal.getIsPartOf());
        dbw.insert(WinLifeTables.GOALS.getTableName(), null, values);
        dbw.close(); // Closing database connection
    }




}
