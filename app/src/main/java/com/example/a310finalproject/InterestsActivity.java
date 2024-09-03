package com.example.a310finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class InterestsActivity extends AppCompatActivity {

    private View.OnClickListener backListener = view -> onBackPressed();

    private View.OnClickListener toggleInterest = view -> {
        //First check if the user has NOT already added this interest
        //then we can add it to their interests (this also needs to be reflected in the database)
        //update the button to green to indicate its been added

        //If it has been added
        //then we remove the interest (this also needs to be reflected in the database)
        //update the button to red to indicate its been removed (and can be added if the user desires)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interests_page);

        findViewById(R.id.back_button).setOnClickListener(backListener);
        //findViewById(R.id.upload_button).setOnClickListener(uploadListener);
    }
}