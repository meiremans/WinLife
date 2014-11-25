package eu.meiremans.winlife.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Trophies.DailyCountTrophy;
import eu.meiremans.winlife.app.business.Trophies.Trophy;
import eu.meiremans.winlife.app.connection.TrophyDAO;
import eu.meiremans.winlife.app.enums.Intent_Extras;
import eu.meiremans.winlife.app.enums.Trophies.TrophyState;

/**
 * Created by Nick on 23/11/2014.LIKE A BOSS
 */
public class Trophy_Details extends Activity {
    Trophy trophy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_details);
        final CheckBox completed = (CheckBox)findViewById(R.id.chkTrophyCompleted);
        final TextView title = (TextView)findViewById(R.id.txtTrophyTitle);
        final TextView description = (TextView)findViewById(R.id.txtTrophyDescription);
        final TextView type = (TextView)findViewById(R.id.txtTrophyType);
        final TextView days = (TextView)findViewById(R.id.trophyDays);
        final TextView trophyDaysLabel = (TextView)findViewById(R.id.txtTrophyDaysLabel);

        Bundle b = getIntent().getExtras();
        //if b = zero, parameter forgotten to send
        if (b == null) {
            throw new NullPointerException();
        }
        trophy = (Trophy) b.getSerializable(Intent_Extras.TROPHY.getId());
        if (trophy instanceof  DailyCountTrophy) {
        days.setText(((DailyCountTrophy) trophy).getDayCount() + "/" + ((DailyCountTrophy) trophy).getEndDayStreak());
        }else{
            days.setText("");
            trophyDaysLabel.setText("");
        }



        this.setTitle("Trophy details from " + trophy.getTrophyName());
        title.append(trophy.getTrophyName());
        description.append(trophy.getTrophyDescription());
        type.append(trophy.getTrophyType().name());

        completed.setChecked(trophy.getTrophyState().equals(TrophyState.COMPLETED) ? Boolean.TRUE : Boolean.FALSE);

        addListenerOnButtons();
    }

   void addListenerOnButtons() {

       final CheckBox completed = (CheckBox)findViewById(R.id.chkTrophyCompleted);
    completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
            TrophyDAO trophyDAO = new TrophyDAO(getApplicationContext());

           trophy.setTrophyState(completed.isChecked() ? TrophyState.COMPLETED : TrophyState.NOT_COMPLETED);
            trophyDAO.changeTrophyState(trophy);


    }
    }

    );
}


}
