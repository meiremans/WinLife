package eu.meiremans.winlife.app;

import android.app.Activity;
import android.content.ContentValues;
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
    }

    // Adding new goal
    public void Add_Goal(Goal goal) {
        SQLiteDatabase dbw = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("goalName", goal.getGoalDescription()); // Contact Phone
        values.put("goalPoint", goal.getGoalPoints()); // Contact Email
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
