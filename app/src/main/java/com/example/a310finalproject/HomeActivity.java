package com.example.a310finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    public UserType user;

    private String mState = "meetings";

    public ArrayList<UserType> data;

    public UserType currentUser = null;


    private void swapFragments(Fragment f) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame_layout, f);
        fragmentTransaction.commit();
    }

    private View.OnClickListener meetingsListener = view ->  {
        swapFragments(new MeetingsFragment());
        mState = "meetings";
    };

    private View.OnClickListener friendsListener = view ->  {
        swapFragments(new FriendsFragment());
        mState = "friends";
    };

    private View.OnClickListener profileListener = view -> {
        swapFragments(new ProfileFragment());
        mState = "profile";
    };

    private View.OnClickListener addListener = view -> {
        if (mState.equals("meetings")) {
            if(currentUser.getmType().equals("Native")) {
                Intent switchActivityIntent = new Intent(this, MeetingUploadActivity.class);
                startActivity(switchActivityIntent);
            } else {
                Toast error = Toast.makeText(this, "Only Native speakers can create meetings!", Toast.LENGTH_LONG);
                error.show();
            }
        } else if (mState.equals("friends")) {
            Intent switchActivityIntent = new Intent(this, FriendAddActivity.class);
            startActivity(switchActivityIntent);
        } else if (mState.equals("profile")) {
            Intent switchActivityIntent = new Intent(this, ProfileEditActivity.class);
            startActivity(switchActivityIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent intent = getIntent();
        if (intent != null) {user = (UserType) intent.getSerializableExtra("USER_KEY");}

        swapFragments(new MeetingsFragment());

        if(CurrentUser.currentUser == null) {
            //For testing purposes
            CurrentUser.setCurrentUser(new UserType("temp@usc.edu", "temp", "Temp", "Temp", "Native"));
        }

        currentUser = CurrentUser.currentUser;

        findViewById(R.id.meetings_button).setOnClickListener(meetingsListener);
        findViewById(R.id.friends_button).setOnClickListener(friendsListener);
        findViewById(R.id.profile_button).setOnClickListener(profileListener);
        findViewById(R.id.add_button).setOnClickListener(addListener);
    }
}
