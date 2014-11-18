package eu.meiremans.winlife.app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import eu.meiremans.winlife.app.business.Goal;


public class AddGoal extends Activity {
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MyDatabase(this);

        setContentView(R.layout.activity_add_goal);
        Goal goal = new Goal();
        goal.setGoalDescription("test");
        goal.setGoalPoints(5);
        add_Goal(goal);
    }

    // Adding new goal
    public void add_Goal(Goal goal) {
        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("goalName", goal.getGoalDescription());
        values.put("goalPoint", goal.getGoalPoints());
// Inserting Row
        dbw.insert("goals", null, values);
        dbw.close(); // Closing database connection
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_goal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_overview:
                Intent intent = new Intent(this, ReadGoals.class);
                this.startActivity(intent);
                break;
            case R.id.action_settings:break;


            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
