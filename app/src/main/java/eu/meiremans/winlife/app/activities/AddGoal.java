package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.connection.GoalDAO;


public class AddGoal extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_goal);
        addListenerOnButton();
    }

    // Adding new goal
    public void add_Goal(Context context,MainGoal mainGoal) {
        GoalDAO goalDAO = new GoalDAO(context);
        goalDAO.addGoal(mainGoal);
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

                MainGoal goal = new MainGoal(mEditGoal.getText().toString(),(Integer.parseInt(mEditPoints.getText().toString())));
                add_Goal(AddGoal.this,goal);
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
