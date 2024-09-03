package com.example.a310finalproject;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseData {
    public static ArrayList<UserType> data = new ArrayList<UserType>();
    public static ArrayList<String> dataKeys = new ArrayList<String>();

    public static ArrayList<Meeting> meetings = new ArrayList<Meeting>();
    public static ArrayList<String> meetingKeys = new ArrayList<String>();

    public static ArrayList<String> codes = new ArrayList<String>();
    public static ArrayList<String> codeKeys = new ArrayList<String>();

    public FirebaseData(){
        updateData();
        updateMeetings();
        updateCodes();
    }

    private void updateData() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                dataKeys.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserType u = snapshot.getValue(UserType.class);
                    data.add(u);
                    dataKeys.add(snapshot.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateMeetings() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("meetings");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                meetings.clear();
                meetingKeys.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Meeting m = snapshot.getValue(Meeting.class);
                    meetings.add(m);
                    meetingKeys.add(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        private void updateCodes() {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("codes");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    codes.clear();
                    codeKeys.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String s = snapshot.getValue(String.class);
                        codes.add(s);
                        codeKeys.add(snapshot.getKey());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    public static ArrayList<UserType> getData() {return data;}
    public static ArrayList<String> getDataKeys() {return dataKeys;}

    public static ArrayList<Meeting> getMeetings() {return meetings;}
    public static ArrayList<String> getMeetingKeys() {return meetingKeys;}

    public static ArrayList<String> getCodes() {return codes;}
    public static ArrayList<String> getCodeKeys() {return codeKeys;}

}

