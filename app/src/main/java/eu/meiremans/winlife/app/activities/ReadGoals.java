package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.MyExpandableListAdapter;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.connection.GoalDAO;
import eu.meiremans.winlife.app.connection.TrophyDAO;

import java.util.HashMap;

import java.util.List;

/**
 * Created by Nick on 17/11/2014.LIKE A BOSS
 */
public class ReadGoals extends Activity {
    private ExpandableListView expandableListView;
    private List<MainGoal> mainGoals;
    private  HashMap<MainGoal, List<Trophy>> trophies = new HashMap<>();




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Reading all goals
        Log.d("Reading: ", "Reading all goals..");
        mainGoals = getAllGoals(this);

        for(MainGoal mainGoal: mainGoals){
            TrophyDAO trophyDAO = new TrophyDAO(this);
            mainGoal.setTrophies(trophyDAO.getAllTrophiesForMainGoal(mainGoal));
            trophies.put(mainGoal,mainGoal.getTrophies());


        }





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
                return false;
            }

        });
    }



    public List<MainGoal> getAllGoals(Context context) {
        GoalDAO goalDAO = new GoalDAO(context);
      return  goalDAO.getAllMainGoals();

    }



}
