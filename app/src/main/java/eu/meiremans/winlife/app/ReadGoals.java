package eu.meiremans.winlife.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import eu.meiremans.winlife.app.business.Goal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 17/11/2014.LIKE A BOSS
 */
public class ReadGoals extends Activity {
    private GridView gridView;
    private MyDatabase db = null;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);
        db = new MyDatabase(this);


        /**
         * CRUD Operations
         * */

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Goal> goals = getAllGoals();
        ArrayList<String> goalNames = new ArrayList<>();

        for (Goal goal : goals) {
            String log = "Id: "+goal.getId()+" ,Name: " + goal.getGoalDescription() + " ,Points: " + goal.getGoalPoints();
            // Writing Goals to log
            Log.d("goal: ", log);

            goalNames.add(goal.getGoalDescription());

        }



        gridView = (GridView) findViewById(R.id.overviewGrid);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, goalNames);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Goal> getAllGoals() {
        List<Goal> goalList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM GOALS" ;

        SQLiteDatabase dbw = db.getWritableDatabase();
        Cursor cursor = dbw.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Goal goal = new Goal();
                goal.setId(Integer.parseInt(cursor.getString(0)));
                goal.setGoalDescription(cursor.getString(1));
                goal.setGoalPoints(cursor.getInt(2));

                goalList.add(goal);
            } while (cursor.moveToNext());
        }

        // return contact list
        return goalList;
    }



}
