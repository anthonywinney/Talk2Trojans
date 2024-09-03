package com.example.a310finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

//        UserType user = new UserType("email", "password", "firstName", "lastName", "Native");
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
//        mDatabase.push().setValue(user);

        new FirebaseData();
    }

    public void switchActivities(View v) {
        int buttonID = v.getId();
        if(buttonID == R.id.login_button) {
            Intent switchActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(switchActivityIntent);
        } else if (buttonID == R.id.register_button) {
            Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
            startActivity(switchActivityIntent);
        }
    }

}