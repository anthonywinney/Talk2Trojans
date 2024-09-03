package com.example.a310finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ProfileCreationActivity extends AppCompatActivity {

        private UserType user;
    private View.OnClickListener completeProfileListener = view -> {
        boolean profileSuccess = true;
        String errorMessage = "";

        String firstName = ((EditText) findViewById(R.id.first_name_textbox)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.last_name_textbox)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.birthday_textbox)).getText().toString();
        String school = ((Spinner) findViewById(R.id.school_spinner)).getSelectedItem().toString();
        String speakerStatus = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId())).getText().toString();

        // Frontend security checks
        if(firstName.isEmpty() || !Character.isUpperCase(firstName.charAt(0))) {
            errorMessage = "Please enter a valid first name!";
        } else if (lastName.isEmpty() || !Character.isUpperCase(lastName.charAt(0))) {
            errorMessage = "Please enter a valid last name!";
        } else if (!(Pattern.compile("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$").matcher(birthday).matches())) {
            errorMessage = "Please enter a valid birthday!";
        } // else if (school is not valid for some reason) {}
        // else if (speakerStatus is not valid for some reason) {}

        if(!errorMessage.isEmpty()) {
            Toast errorToast = Toast.makeText(ProfileCreationActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
            profileSuccess = false;
        }

        // Backend security checks
        // Make sure profileSuccess isn't already false before trying to send to backend

        if (profileSuccess) {
            Intent switchActivityIntent = new Intent(this, HomeActivity.class);
            switchActivityIntent.putExtra("USER_KEY", user);
            startActivity(switchActivityIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_creation);

        Intent intent = getIntent();
        if (intent != null) {user = (UserType) intent.getSerializableExtra("USER_KEY");}

        findViewById(R.id.complete_profile_button).setOnClickListener(completeProfileListener);
    }
}
