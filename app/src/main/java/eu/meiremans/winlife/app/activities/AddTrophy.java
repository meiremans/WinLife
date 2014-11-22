package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.os.Bundle;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.MainGoal;
import eu.meiremans.winlife.app.enums.Intent_Extras;

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

    }
}
