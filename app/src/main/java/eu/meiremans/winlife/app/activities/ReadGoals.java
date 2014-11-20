package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.connection.GoalDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 17/11/2014.LIKE A BOSS
 */
public class ReadGoals extends Activity {
    private GridView gridView;




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Reading all goals
        Log.d("Reading: ", "Reading all goals..");
        List<MainGoal> goals = getAllGoals(this);
        ArrayList<String> goalNames = new ArrayList<>();

        for (MainGoal goal : goals) {
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

    public List<MainGoal> getAllGoals(Context context) {
        GoalDAO goalDAO = new GoalDAO(context);
      return  goalDAO.getAllMainGoals();

    }



}
