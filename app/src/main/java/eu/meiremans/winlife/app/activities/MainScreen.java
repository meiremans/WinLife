package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.TotalPoints;
import eu.meiremans.winlife.app.connection.MyDatabase;
import eu.meiremans.winlife.app.connection.TotalPointsDAO;
import eu.meiremans.winlife.app.enums.Trophies.TrophyLevel;
import org.w3c.dom.Text;

/**
 * Created by Nick on 19/11/2014.LIKE A BOSS
 */
public class MainScreen extends Activity {

    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

refreshView();


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
            case R.id.view_database:
                Intent database_intent = new Intent(this, AndroidDatabaseManager.class);
                this.startActivity(database_intent);
                break;
            case R.id.action_settings:break;


            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
    @Override
    protected void onResume(){
super.onResume();
refreshView();
    }

    private void refreshView(){
        setContentView(R.layout.activity_main_screen);
        TextView totalPointsView = (TextView)findViewById(R.id.txtTotalPoints);
        TextView level = (TextView)findViewById(R.id.txtLevel);
        TextView pointsToNextLevel = (TextView)findViewById(R.id.txtPointsToNextLevel);
        TextView numberBronze = (TextView)findViewById(R.id.txtNumberBronze);
        TextView numberSilver = (TextView)findViewById(R.id.txtNumberSilver);
        TextView numberGold = (TextView)findViewById(R.id.txtNumberGold);
        ProgressBar progressLvl = (ProgressBar)findViewById(R.id.progressLvl);



        TotalPointsDAO totalPointsDAO = new TotalPointsDAO(this);
        TotalPoints totalPoints = totalPointsDAO.getCountAllTrophies();
        TrophyLevel nextLevel = totalPoints.getCurrentLevel().getNextLevel();
        int pointEarnedThisLevel = totalPoints.getTotalPoints() - totalPoints.getCurrentLevel().getLevelStart();
        double progress = ((double)pointEarnedThisLevel/(double)nextLevel.getLevelStart())*100;
        Log.v("test,","test");
        progressLvl.setProgress(((int)progress));

        totalPointsView.setText(totalPoints.getTotalPoints()+"");
        level.setText(totalPoints.getCurrentLevel().getLevelString());
        pointsToNextLevel.setText(totalPoints.getPointsToNextLevel()+"");
        numberBronze.setText(totalPoints.getNumberBronze()+"");
        numberSilver.setText(totalPoints.getNumberSilver()+"");
        numberGold.setText(totalPoints.getNumberGold()+"");
    }

}
