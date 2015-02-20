package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.MyExpandableListAdapter;
import eu.meiremans.winlife.app.business.Trophies.Trophy;
import eu.meiremans.winlife.app.connection.GoalDAO;
import eu.meiremans.winlife.app.connection.MyDatabase;
import eu.meiremans.winlife.app.connection.TrophyDAO;
import eu.meiremans.winlife.app.enums.Intent_Extras;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nick on 17/11/2014.LIKE A BOSS
 */
public class ReadGoals extends Activity {
    private ExpandableListView expandableListView;
    private List<MainGoal> mainGoals;
    private  HashMap<MainGoal, List<Trophy>> trophies = new HashMap<>();
    private MyDatabase db;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        firstRun();


        // Reading all goals
        Log.d("Reading: ", "Reading all goals..");

        readGoals();

        expandableListView = (ExpandableListView) findViewById(R.id.overview);


        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,mainGoals,trophies);

        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        trophies.get(
                                mainGoals.get(groupPosition)).get(
                                childPosition).getTrophyName()
                                + " : "
                                + trophies.get(
                                mainGoals.get(groupPosition)).get(
                                childPosition).getTrophyDescription(), Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(getApplicationContext(), Trophy_Details.class);
                intent.putExtra(Intent_Extras.TROPHY.getId(),trophies.get(
                        mainGoals.get(groupPosition)).get(
                        childPosition));
                startActivity(intent);
                return false;
            }

        });

        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

                Log.v("long clicked","pos: " + pos);
                Toast.makeText(getApplicationContext(),"HELLO",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }



    public List<MainGoal> getAllGoals(Context context) {
        GoalDAO goalDAO = new GoalDAO(context);
      return  goalDAO.getAllMainGoals();

    }

    @Override
    public void onResume()
    {
        super.onResume();
       readGoals();
        expandableListView = (ExpandableListView) findViewById(R.id.overview);


        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,mainGoals,trophies);

        expandableListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_profile:
                Intent action_overview_intent = new Intent(this, Profile.class);
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

    private void readGoals(){
        mainGoals = getAllGoals(this);

        for(MainGoal mainGoal: mainGoals){
            TrophyDAO trophyDAO = new TrophyDAO(this);
            mainGoal.setTrophies(trophyDAO.getAllTrophiesForMainGoal(mainGoal));
            trophies.put(mainGoal,mainGoal.getTrophies());
        }
    }

    private void firstRun(){

        //check for first run
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time

            Intent intent = new Intent(getApplicationContext(), AddGoal.class);
            startActivity(intent);
            finish();

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        //initialize database on first run(Otherwise this happens on first transaction & this takes a while
        db = new MyDatabase(this);
        SQLiteDatabase dbw = db.getWritableDatabase();
        dbw.close();

    }



}
