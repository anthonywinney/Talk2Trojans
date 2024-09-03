package com.example.a310finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MeetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_list);
        start();
    }

    private void start() {
        GridLayout grid = findViewById(R.id.gridLayout01);
//        TextView tv = new TextView(this);
//        tv.setText("test"); //this should be a meeting link
//        grid.addView(tv);

        //https://usc.zoom.us/j/3081383371

        for (int i = 0; i < 10; i++) {
            TextView tv = new TextView(this);
            tv.setText("test"); //this should be a meeting link

//            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
//            lp.setMargins(2, 2, 2, 2);
//            lp.rowSpec = GridLayout.spec(i);
//            lp.columnSpec = GridLayout.spec(0);

            grid.addView(tv);
        }


//            tv.setHeight(dpToPixel(26));
//            tv.setWidth(dpToPixel(26));
//            tv.setTextSize(13);//dpToPixel(32) );
//            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
//            tv.setTextColor(Color.parseColor("lime"));
//            tv.setBackgroundColor(Color.parseColor("lime"));
//            tv.setOnClickListener(this::onClickTV);
//
//            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
//            lp.setMargins(dpToPixel(2), dpToPixel(2), dpToPixel(2), dpToPixel(2));
//            lp.rowSpec = GridLayout.spec(i);
//            lp.columnSpec = GridLayout.spec(j);
//
//            grid.addView(tv, lp);
//
//            cell_tvs.add(tv);
//            if(cell_bombs.contains(findIndexOfCellTextView(tv))) {
//                row.add(-1);
//            }
//            else {row.add(0);}
//        }

    }

    public void switchActivities(View v) {

//        int buttonID = v.getId();
//        if(buttonID == R.id.login_button) {
//            Intent switchActivityIntent = new Intent(this, LoginActivity.class);
//            startActivity(switchActivityIntent);
//        } else if (buttonID == R.id.register_button) {
//            Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
//            startActivity(switchActivityIntent);
//        }
    }

}