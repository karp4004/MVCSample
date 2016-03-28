package karp4004.clinicschedule;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = new Controller(this);

        Model.Record[] mRecords = new Model.Record[]{
                new Model.Record(4, 35, "Иванов И.", 30, Color.BLACK),
                new Model.Record(14, 15, "Петров П.", 45, Color.BLUE),
                new Model.Record(14, 5, "Сидоров С.", 15, Color.RED),
                new Model.Record(20, 30, "er b.", 20, Color.YELLOW),
                new Model.Record(3, 10, "rrr b.", 25, Color.RED),
                new Model.Record(20, 15, "eee b.", 45, Color.BLACK),
                new Model.Record(20, 30, "www b. new", 60, Color.RED),
                new Model.Record(12, 10, "nnn b.", 55, Color.BLACK),
                new Model.Record(11, 15, "qqq b.", 15, Color.BLUE),
                new Model.Record(9, 30, "ooo b. new 1", 43, Color.BLACK),
                new Model.Record(21, 30, "ppp b. new 2", 25, Color.BLUE),
                new Model.Record(23, 30, "zzz b. new 3", 25, Color.RED),
                new Model.Record(19, 30, "zzz b. new 3", 61, Color.BLUE)
        };

        mController.fillScheduleTable(mRecords);
    }
}
