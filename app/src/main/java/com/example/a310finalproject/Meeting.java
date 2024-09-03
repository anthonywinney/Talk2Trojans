package com.example.a310finalproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Meeting {


    public Meeting(String host, String name, String link, String date, String time, String language) {
        this.mHost = host;
        this.mName = name;
        this.mLink = link;
        this.mDate = date;
        this.mTime = time;
        this.mLanguage = language;
        this.participants = host;
        this.participantsList = new ArrayList<String>(Arrays.asList(host));
    }

    public Meeting(){}

    public String getHost() {
        return mHost;
    }
    public void setHost(String host) {
        this.mHost = host;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        this.mName = name;
    }

    public String getLink() {
        return mLink;
    }
    public void setLink(String link) {
        this.mLink = link;
    }

    public String getDate() { return mDate; }
    public void setDate(String date) {
        this.mDate = date;
    }

    public String getTime() { return mTime; }
    public void setTime(String time) {
        this.mTime = time;
    }

    public String getmLanguage() { return mLanguage; }
    public void setmLanguage(String lang) {
        this.mLanguage = lang;
    }

    public String getParticipants() {return participants;}
    public void setParticipants(String p) {this.participants = p;} //DO NOT USE for DB purposes only

    public ArrayList<String> getParticipantsList() {return participantsList;}
    public void setParticipantsList(ArrayList<String> p) {
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("meetings");
//        ArrayList<Meeting> meetings = FirebaseData.getMeetings();
//        ArrayList<String> keys = FirebaseData.getMeetingKeys();
//        String key = "";
//
//        for(int i = 0; i < meetings.size(); ++i) {
//            if(this == meetings.get(i)) {
//                key = keys.get(i);
//                break;
//            }
//        }

        this.participantsList = p;
        this.participants = String.join(", ", this.participantsList);

//        mDatabase.child(key).setValue(this);
    }

    private transient String mHost;
    private transient String mName;
    private transient String mLink;
    private transient String mDate;
    private transient String mTime;
    private transient String mLanguage;
    private transient String participants;
    private ArrayList<String> participantsList;
}
