package karp4004.clinicschedule;

import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by okarpov on 3/28/2016.
 */
public class Model {

    interface ModelEvent
    {
        void onSortedRecord(Record r);
    }

    public static class Record
    {
        Record( int h,
                int m,
                String n,
                int d,
                int c)
        {
            mName = n;
            mDuration = d;
            mHour = h;
            mMinute = m;
            mColor = c;
        }

        int mHour;
        int mMinute;
        String mName;
        int mDuration;
        int mColor;
    }

    public int getStartHour()
    {
        return 8;
    }

    public int getEndHour()
    {
        return 22;
    }

    void sortRecords(ArrayList<Record> mRecords, ModelEvent event)
    {
        int startHour = 8, endHour = 22;

        //SORT
        SparseArray<SparseArray<Record>> sorted = new SparseArray<SparseArray<Record>>();
        for (Record r:
                mRecords) {

            if(r.mHour < startHour || r.mHour > endHour || r.mDuration > 60)
                continue;

            SparseArray<Record> min = sorted.get(r.mHour);
            if(min == null)
            {
                min = new SparseArray<>();
            }

            min.put(r.mMinute, r);
            sorted.put(r.mHour, min);
        }

        for(int k=0;k<sorted.size();k++)
        {
            Log.i("onCreate", "h:" + sorted.keyAt(k));
            SparseArray<Record> min = sorted.valueAt(k);
            if(min != null)
            {
                for(int j=0;j<min.size();j++) {

                    Record r = min.valueAt(j);
                    if(r != null) {
                        Log.i("onCreate", "c:" + min.keyAt(j) + " " + r.mName + " " + r.mDuration);
                        event.onSortedRecord(r);
                    }
                }
            }
        }
    }
}
