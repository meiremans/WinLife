package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.MyExpandableListAdapter;
import eu.meiremans.winlife.app.business.SubGoal;
import eu.meiremans.winlife.app.connection.GoalDAO;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 17/11/2014.LIKE A BOSS
 */
public class ReadGoals extends Activity {
    private ExpandableListView expandableListView;
    private List<MainGoal> mainGoals;
    private  HashMap<MainGoal, List<SubGoal>> subGoals = new HashMap<MainGoal, List<SubGoal>>();




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Reading all goals
        Log.d("Reading: ", "Reading all goals..");
        mainGoals = getAllGoals(this);

        for(MainGoal s: mainGoals){
            SubGoal subGoal = new SubGoal("trololol",(Integer)5005,1);
            ArrayList <SubGoal>subGoalsA = new ArrayList<SubGoal>();
            subGoalsA.add(subGoal);
            subGoals.put(s,subGoalsA);


        }





        expandableListView = (ExpandableListView) findViewById(R.id.overview);


        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,mainGoals,subGoals);

        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        mainGoals.get(groupPosition)
                                + " : "
                                + subGoals.get(
                                mainGoals.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
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
