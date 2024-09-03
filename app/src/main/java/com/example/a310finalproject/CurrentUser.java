package com.example.a310finalproject;

public class CurrentUser {
    public static UserType currentUser = null;
    public CurrentUser(UserType u) {
        this.currentUser = u;
    }

    public static void setCurrentUser(UserType currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
