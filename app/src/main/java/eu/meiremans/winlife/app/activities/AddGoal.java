package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.SubGoal;
import eu.meiremans.winlife.app.connection.GoalDAO;

import java.util.List;


public class AddGoal extends Activity {
    MainGoal emptyGoal = new MainGoal(0,"This is a Main Goal",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        GoalDAO goalDAO = new GoalDAO(this);
        List<MainGoal> mainGoals = goalDAO.getAllMainGoals();

        mainGoals.add(0,emptyGoal);
        ArrayAdapter<MainGoal> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, mainGoals);
        Spinner mainGoalSpinner =  (Spinner) findViewById(R.id.mainGoalSelection);

        mainGoalSpinner.setAdapter(adapter);


        addListenerOnButton();
    }

    // Adding new goal
    public void add_Goal(Context context,Goal goal) {
        if (goal instanceof MainGoal) {
            GoalDAO goalDAO = new GoalDAO(context);
            goalDAO.addMainGoal((MainGoal)goal);
        }else{
            GoalDAO goalDAO = new GoalDAO(context);
            goalDAO.addSubGoal((SubGoal)goal);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_goal, menu);
        return true;
    }

    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.btnAddGoal);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEditGoal   = (EditText)findViewById(R.id.goal_to_add);
                EditText mEditPoints   = (EditText)findViewById(R.id.points_for_goal);
                Spinner mainGoalSpinner = (Spinner)findViewById(R.id.mainGoalSelection);
                Goal goal;
                if(mainGoalSpinner.getSelectedItem().equals(emptyGoal)) {
                    goal = new MainGoal(mEditGoal.getText().toString(), (Integer.parseInt(mEditPoints.getText().toString())));
                } else {
                    goal = new SubGoal(mEditGoal.getText().toString(), (Integer.parseInt(mEditPoints.getText().toString())),((Goal)(mainGoalSpinner.getSelectedItem())).getId());
                }
                add_Goal(AddGoal.this,goal);
                Intent intent = new Intent(getApplicationContext() , ReadGoals.class);
                AddGoal.this.startActivity(intent);

            }
        });
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
