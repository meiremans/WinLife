package eu.meiremans.winlife.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Nick on 19/11/2014.LIKE A BOSS
 */
public class MainScreen extends Activity {

    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize database on first run(Otherwise this happens on first transaction & this takes a while
        db = new MyDatabase(this);
        SQLiteDatabase dbw = db.getWritableDatabase();
        dbw.close();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_overview:
                Intent action_overview_intent = new Intent(this, ReadGoals.class);
                this.startActivity(action_overview_intent);
                break;
            case R.id.action_add_goal:
                Intent add_goal_intent = new Intent(this, AddGoal.class);
                this.startActivity(add_goal_intent);
                break;
            case R.id.action_settings:break;


            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


}
