package com.example.a310finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private View.OnClickListener backListener = view -> onBackPressed();

    private ArrayList<UserType>  data = new ArrayList<>();
    private UserType currentUser;

    private View.OnClickListener loginListener = view -> {

        boolean loginSuccess = true;
        String errorMessage = "";

        String email = ((EditText) findViewById(R.id.email_textbox)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textbox)).getText().toString();

        // Frontend security checks
        if(email.isEmpty()) {
            errorMessage = "Please enter an email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(email).matches())) {
            errorMessage = "Please enter a valid email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(email).matches())) {
            errorMessage = "Please enter a USC email!";
        } else if (password.isEmpty()) {
            errorMessage = "Please enter a password!";
        }


        if(!errorMessage.isEmpty()) {
            Toast errorToast = Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
            loginSuccess = false;
        }
        else {
            loginSuccess = false;
            data = FirebaseData.getData();
            for(UserType user : data) {
                if(user.getmEmail().equals(email) && user.getmPassword().equals(password)) {
                    loginSuccess = true;
                    currentUser = user;
                    break;
                }
            }
        }

        if(loginSuccess) {
            Intent switchActivityIntent = new Intent(this, HomeActivity.class);
            new CurrentUser(currentUser);
            startActivity(switchActivityIntent);
        }
        else {
            Toast errorToast = Toast.makeText(LoginActivity.this, "Email or Password Incorrect", Toast.LENGTH_SHORT);
            errorToast.show();
            Intent switchActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(switchActivityIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findViewById(R.id.back_button).setOnClickListener(backListener);
        findViewById(R.id.login_button).setOnClickListener(loginListener);
    }
}