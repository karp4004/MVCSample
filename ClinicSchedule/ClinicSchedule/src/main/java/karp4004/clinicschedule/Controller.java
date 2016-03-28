package karp4004.clinicschedule;

import com.example.s3test.R;

import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.content.Context;

/**
 * Created by okarpov on 3/28/2016.
 */
public class Controller implements Model.ModelEvent{

    Model mModel;
    Activity mActivity;
    ViewGroup scheduleTable;
    float hourOffset;
    int d;
    float hourOffsetPx;
    float minuteOffsetPx;
    int idx = 0;

    public Controller(Activity a)
    {
        mActivity = a;
        mModel = new Model();

        hourOffset = 100;
        d = (int)mActivity.getResources().getDisplayMetrics().density;
        hourOffsetPx = hourOffset*d;
        minuteOffsetPx = hourOffsetPx/60;
        scheduleTable = (ViewGroup)mActivity.findViewById(R.id.scheduleTable);
    }

    public void fillScheduleTable(Model.Record[] mRecords)
    {
        for(int i=mModel.getStartHour();i<=mModel.getEndHour();i++) {
            TextView v = new TextView(mActivity);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)hourOffsetPx*2, (int)hourOffsetPx);
            lp.setMargins(0, (i - mModel.getStartHour())*(int)hourOffsetPx, 0, 0);
            v.setLayoutParams(lp);
            v.setText("" + i + "-00");
            scheduleTable.addView(v);
        }

        mModel.sortRecords(mRecords, this);
    }

    @SuppressLint("NewApi") public void onSortedRecord(Model.Record r)
    {
        float o = (r.mHour - mModel.getStartHour())*hourOffsetPx + r.mMinute*minuteOffsetPx;
        float height = r.mDuration*minuteOffsetPx;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.client, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)hourOffsetPx*2, (int)height);
        lp.setMargins((idx + 1) * (int)hourOffsetPx * 2, (int)o, 0, 0);
        v.setLayoutParams(lp);

        TextView tv = (TextView)v.findViewById(R.id.nameText);
        tv.setText(r.mName);

        ViewGroup layColor = (ViewGroup)v.findViewById(R.id.layColor);
        layColor.setBackground(new ColorDrawable(r.mColor));

        scheduleTable.addView(v);

        idx++;
    }
}
