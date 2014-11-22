package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.business.Trophy;
import eu.meiremans.winlife.app.connection.TrophyDAO;
import eu.meiremans.winlife.app.enums.Intent_Extras;
import eu.meiremans.winlife.app.enums.TrophyState;
import eu.meiremans.winlife.app.enums.TrophyType;

/**
 * Created by Nick on 22/11/2014.LIKE A BOSS
 */
public class AddTrophy extends Activity {
    MainGoal mainGoal;
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
        mainGoal = (MainGoal) b.getSerializable(Intent_Extras.MAIN_GOAL.getId());
        this.setTitle("add trophy for " + mainGoal.getGoalDescription());
addListenerOnButton();
    }

    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.btnAddTrophy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText trophyTitle   = (EditText)findViewById(R.id.txtTrophyTitle1);
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

                Trophy trophy = new Trophy(trophyType,trophyTitle.getText().toString(),trophyDescription.getText().toString(), TrophyState.NOT_COMPLETED);
                trophy.setMainGoalId(mainGoal.getId());
                TrophyDAO trophyDAO = new TrophyDAO(getApplicationContext());
                trophyDAO.addTrophy(trophy);
                Toast.makeText(getApplicationContext(),trophy.getTrophyName() +" is added",Toast.LENGTH_LONG).show();

            }
        });
    }

    }
