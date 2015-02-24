package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.Trophies.Trophy;
import eu.meiremans.winlife.app.connection.GoalDAO;
import eu.meiremans.winlife.app.enums.Intent_Extras;

import java.util.ArrayList;
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
        listviewAdapter(goal.getTrophies());
    }

public void listviewAdapter(ArrayList<Trophy> trophies){
    // Get a handle to the list view
    ListView lv = (ListView) findViewById(R.id.showTrophies);

    // Convert ArrayList to array
    ArrayAdapter<Trophy> adapter = new ArrayAdapter<Trophy>(this,
            android.R.layout.simple_list_item_1, trophies);
    lv.setAdapter(adapter);
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
