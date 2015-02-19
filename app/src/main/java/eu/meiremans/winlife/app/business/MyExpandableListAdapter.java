package eu.meiremans.winlife.app.business;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import eu.meiremans.winlife.app.R;
import eu.meiremans.winlife.app.business.Trophies.Trophy;
import eu.meiremans.winlife.app.enums.Trophies.TrophyState;
import eu.meiremans.winlife.app.enums.Trophies.TrophyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nick on 20/11/2014.LIKE A BOSS
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<MainGoal> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<MainGoal, List<Trophy>> _listDataChild;

    public MyExpandableListAdapter(Context context, List<MainGoal> listDataHeader,
                                 HashMap<MainGoal, List<Trophy>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = ((Trophy) getChild(groupPosition, childPosition)).getTrophyName();
        Trophy trophy = (Trophy) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        ImageView iv = (ImageView) convertView.findViewById(R.id.trophy);
        iv.setImageResource(R.drawable.trophy_black);
        if(trophy.getTrophyState() == TrophyState.COMPLETED){
            if(trophy.getTrophyType() == TrophyType.BRONZE)
                iv.setImageResource(R.drawable.bronze);

            if(trophy.getTrophyType() == TrophyType.SILVER)
                iv.setImageResource(R.drawable.trophy_silver);

            if(trophy.getTrophyType() == TrophyType.GOLD)
                iv.setImageResource(R.drawable.trophy_gold);

        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (getGroup(groupPosition)).toString();
        ArrayList<Trophy> completed = ((MainGoal)getGroup(groupPosition)).completedTrophies();
        ArrayList<Trophy> total = ((MainGoal)getGroup(groupPosition)).getTrophies();
        double percentage = ((double)completed.size()/(double)total.size())*100;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        ProgressBar progressTrophy = (ProgressBar) convertView.findViewById(R.id.progressTrophy);

        progressTrophy.setProgress((int)percentage);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
