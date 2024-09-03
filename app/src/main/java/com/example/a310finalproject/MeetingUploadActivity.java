package com.example.a310finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MeetingUploadActivity extends AppCompatActivity
        implements TimePickerFragment.TimeListener, DatePickerFragment.DateListener {

    private Calendar mCalendar = Calendar.getInstance();
    private String mTime = String.format("%02d:%02d",
            mCalendar.get(Calendar.HOUR),
            mCalendar.get(Calendar.MINUTE));
    private String mDate = String.format("%02d/%02d/%04d",
            mCalendar.get(Calendar.MONTH) + 1,
            mCalendar.get(Calendar.DAY_OF_MONTH),
            mCalendar.get(Calendar.YEAR));

    private View.OnClickListener backListener = view -> onBackPressed();

    private UserType user = null;
    private View.OnClickListener uploadListener = view -> {
        boolean uploadSuccess = true;
        String errorMessage = "";

        String meetingName = ((EditText) findViewById(R.id.meeting_name_textbox)).getText().toString();
        String meetingDate = mDate;
        String meetingTime = mTime;
        String meetingLanguage = ((EditText) findViewById(R.id.language_textbox)).getText().toString();
        String meetingZoomLink = ((EditText) findViewById(R.id.zoom_link_textbox)).getText().toString();
        
        // Frontend security checks
        if (meetingName.isEmpty()) {
            errorMessage = "Please enter a valid meeting name!";
        } else if (meetingDate.isEmpty()) {
            errorMessage = "Please enter a valid meeting date!";
        } else if (meetingTime.isEmpty()) {
            errorMessage = "Please enter a valid meeting time!";
        } else if (meetingLanguage.isEmpty()) {
            errorMessage = "Please enter a valid meeting language!";
        } else if (!(Pattern.compile("[A-Za-z]+://usc\\.zoom\\.us/[A-Za-z0-9]/[0-9]+")
                .matcher(meetingZoomLink).matches()) &&
                !(Pattern.compile("[A-Za-z]+://usc\\.zoom\\.us/[A-Za-z0-9]/[0-9]+\\?[A-Za-z]+=[A-Za-z0-9]+")
                        .matcher(meetingZoomLink).matches())) {
            errorMessage = "Please enter a valid meeting Zoom link!";
        }

        if(!errorMessage.isEmpty()) {
            Toast errorToast = Toast.makeText(MeetingUploadActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
            uploadSuccess = false;
        }

        if(uploadSuccess) {
            DatabaseReference mDatabase = null;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("meetings").push().setValue(new Meeting(
                    CurrentUser.currentUser.getmFirstname() + " " + CurrentUser.currentUser.getmLastname(),
                    meetingName, meetingZoomLink, meetingDate, meetingTime, meetingLanguage));

            Intent switchActivityIntent = new Intent(this, HomeActivity.class);
            switchActivityIntent.putExtra("USER_KEY", user);
            startActivity(switchActivityIntent);
        }
    };

    private View.OnClickListener timeListener = view -> {
        TimePickerFragment dialog = new TimePickerFragment();
        dialog.show(getSupportFragmentManager(), "timePicker");
    };

    private View.OnClickListener dateListener = view -> {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "datePicker");
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_upload);

        findViewById(R.id.back_button).setOnClickListener(backListener);
        findViewById(R.id.create_meeting_button).setOnClickListener(uploadListener);
        findViewById(R.id.choose_time).setOnClickListener(timeListener);
        findViewById(R.id.choose_date).setOnClickListener(dateListener);

        ((TextView) findViewById(R.id.date_textview)).setText(mDate);
        ((TextView) findViewById(R.id.time_textview)).setText(mTime);
    }

    @Override
    public void applyTime(String time) {
        mTime = time;
        ((TextView) findViewById(R.id.time_textview)).setText(mTime);
    }

    @Override
    public void applyDate(String date) {
        mDate = date;
        Log.d("applyDate", date);
        ((TextView) findViewById(R.id.date_textview)).setText(mDate);
    }
}