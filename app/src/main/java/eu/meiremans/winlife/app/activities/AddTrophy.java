package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Goal;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.enums.Intent_Extras;
import eu.meiremans.winlife.app.enums.TrophyType;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public class AddTrophy extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trophy);
        Bundle b = getIntent().getExtras();
        //if b = zero, parameter forgotten to send
        if (b == null) {
            throw new NullPointerException();
        }
        MainGoal mainGoal = (MainGoal) b.getSerializable(Intent_Extras.MAIN_GOAL.getId());
        this.setTitle("add trophy for " + mainGoal.getGoalDescription());
addListenerOnButton();
    }

    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.btnAddTrophy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText trophyTitle   = (EditText)findViewById(R.id.txtTrophyTitle);
                EditText trophyDescription   = (EditText)findViewById(R.id.txtTrophyDescription);
                RadioGroup radioTrophyGroup = (RadioGroup)findViewById(R.id.radioTrophy);
                RadioButton radioTrophyButton = (RadioButton) findViewById(radioTrophyGroup.getCheckedRadioButtonId());


                //convert the ID of the radiobutton to TrophyType enum(OMG THIS IS DIRTY?!?)
                TrophyType trophyType;
                switch (radioTrophyButton.getId()){
                    case R.id.rdbBronze: trophyType = TrophyType.BRONZE;break;
                    case R.id.rdbSilver: trophyType = TrophyType.SILVER;break;
                    case R.id.rdbGold: trophyType = TrophyType.GOLD;break;
                    default : trophyType = null;
                }

                Trophy trophy = new Trophy(trophyType,trophyTitle.getText().toString(),trophyDescription.getText().toString(),Boolean.FALSE);



                add_Goal(getApplicationContext(),goal);
                Intent intent = new Intent(getApplicationContext(), AddTrophy.class);
                intent.putExtra(Intent_Extras.MAIN_GOAL.getId(),mainGoal);
                startActivity(intent);

            }
        });
    }

    }
