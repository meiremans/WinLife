package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.connection.GoalDAO;
import eu.meiremans.winlife.app.enums.Intent_Extras;

import java.util.List;

/**
 * Created by nmeiremans on 2/23/15.
 */


public class EditGoal extends Activity{
    MainGoal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);
        EditText goalName = (EditText) findViewById(R.id.txtGoalName);
        goal = (MainGoal) getIntent().getSerializableExtra(Intent_Extras.MAIN_GOAL.getId());
        goalName.setText(goal.getGoalDescription());

        addListenerOnButton();
    }


    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.btnAddTrophy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), AddTrophy.class);
                intent.putExtra(Intent_Extras.MAIN_GOAL.getId(),goal);
                startActivity(intent);
                finish();

            }
        });
    }
    }
