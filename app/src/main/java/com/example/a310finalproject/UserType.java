package com.example.a310finalproject;
import android.util.Pair;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class UserType implements Serializable {

    public UserType(String email, String password, String firstname, String lastname, String type){
       this.mEmail = email;
       this.mPassword = password;
       this.mFirstname = firstname;
       this.mLastname = lastname;
       this.mType = type;
       this.mFriendsList = new ArrayList<String>();
       this.mFriends = "";
       this.mInterestsList = new ArrayList<String>();
    }

    public UserType(){}
//    void AddMeeting(MeetingTime meetingTime){
//        mMeetingTimes.add(meetingTime);
//    }
//
//     void AddAvailableTime(MeetingTime meetingTime){
//        mAvailableTimes.add(meetingTime);
//    }

    public String getmEmail() {
        return mEmail;
    }
    public void setmEmail(String email) {
        this.mEmail = email;
    }

    public String getmPassword() {
        return mPassword;
    }
    public void setmPassword(String pass) {
        this.mPassword = pass;
    }

    public String getmFirstname() {
        return mFirstname;
    }
    public void setmFirstname(String firstname) {this.mFirstname = firstname;}
    public String getmLastname() {
        return mLastname;
    }
    public void setmLastname(String lastname) {
        this.mLastname = lastname;
    }

    public String getmType() {
        return mType;
    }
    public void setmType(String type) {
        this.mType = type;
    }

    public String getmFriends() {
        return mFriends;
    }
    public void setmFriends(String f) { //DO NOT USE for DB purposes only
        this.mFriends = f;
    }

    public ArrayList<String> getmFriendsList() {return mFriendsList;}
    public void setmFriendsList(ArrayList<String> f) {
        this.mFriendsList = f;
        this.mFriends = String.join(", ", this.mFriendsList);
    }

    public ArrayList<String> getmInterestsList() {return mInterestsList;}
    public void setmInterestsList(ArrayList<String> i) {
        this.mFriends = String.join(", ", this.mInterestsList);
    }

    public String getFullName() {return mFirstname + " " + mLastname;}
    public void setfullName(String s) {
        return;
    }

    private transient String mEmail;
    private transient String mPassword;
    private transient String mFirstname;
    private transient String mLastname;
    private transient String mType;
    private transient String mFriends;
    private ArrayList<String> mFriendsList;
    private ArrayList<String> mInterestsList;


}
