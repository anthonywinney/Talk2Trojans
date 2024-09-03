package com.example.a310finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private View.OnClickListener backListener = view -> onBackPressed();

    private ArrayList<UserType> data;
    private View.OnClickListener registerListener = view -> {
        boolean registerSuccess = true;
        String errorMessage = "";

        String email = ((EditText) findViewById(R.id.email_textbox)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textbox)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.confirm_password_textbox)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.first_name_textbox)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.last_name_textbox)).getText().toString();
        String regCode = ((EditText) findViewById(R.id.reg_code_textbox)).getText().toString();
        String speakerStatus = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId())).getText().toString();


        ArrayList<String> codes = FirebaseData.getCodes();

        // Frontend security checks
        if(email.isEmpty()) {
            errorMessage = "Please enter an email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(email).matches())) {
            errorMessage = "Please enter a valid email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(email).matches())) {
            errorMessage = "Please enter a USC email!";
        } else if (email.contains("#") || email.contains("$") || email.contains("[") || email.contains("]") || email.substring(email.indexOf(".")+1).contains(".")) {
            errorMessage = "Please enter am email without '.', '#', '$', '[', or ']'";
        } else if (password.isEmpty()) {
            errorMessage = "Please enter a password!";
        } // else if (password does not live up to security standard) {}
        else if (!confirmPassword.equals(password)) {
            errorMessage = "Passwords must match!";
        } else if(firstName.isEmpty() || !Character.isUpperCase(firstName.charAt(0))) {
            errorMessage = "Please enter a valid first name!";
        } else if (lastName.isEmpty() || !Character.isUpperCase(lastName.charAt(0))) {
            errorMessage = "Please enter a valid last name!";
        } else {
            boolean codesMatch = false;
            for(String code : codes) {
                if(code.contains(email)) {
                    if(code.substring(0, 5).equals(regCode)) {
                        codesMatch = true;
                        break;
                    }
                }
            }

            if(!codesMatch && !regCode.equals("00000")) { //if regCode is '00000' then no error. Even though the app is invite only we need a way for the first user to create an account (think of this like an "admin" account or something)
                errorMessage = "Please enter a valid registration code";
            }
        }

        if(!errorMessage.isEmpty()) {
            Toast errorToast = Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
            registerSuccess = false;
        }

        data = FirebaseData.getData();
        for(UserType u : data) {
            if(u.getmEmail().equals(email)) {
                registerSuccess = false;
                break;
            }
        }

        if(registerSuccess) {
            UserType user = new UserType(email, password, firstName, lastName, speakerStatus);
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
            mDatabase.push().setValue(user);
            new CurrentUser(user);

            Intent switchActivityIntent = new Intent(this, HomeActivity.class);
            switchActivityIntent.putExtra("USER_KEY", user);
            startActivity(switchActivityIntent);
        }
        else {
            Toast errorToast = Toast.makeText(RegisterActivity.this, "Email Already In Use", Toast.LENGTH_SHORT);
            errorToast.show();
            Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
            startActivity(switchActivityIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        findViewById(R.id.back_button).setOnClickListener(backListener);
        findViewById(R.id.register_button).setOnClickListener(registerListener);
    }
}
