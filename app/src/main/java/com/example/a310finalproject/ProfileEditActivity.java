package com.example.a310finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ProfileEditActivity extends AppCompatActivity {

    private View.OnClickListener backListener = view -> onBackPressed();

    private View.OnClickListener confirmListener = view -> {
        String firstName = ((EditText) findViewById(R.id.edit_first_name)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.edit_last_name)).getText().toString();
        String email = ((EditText) findViewById(R.id.edit_email)).getText().toString();
        String speakerStatus = "";
        if (((RadioButton) findViewById(R.id.edit_radio_native)).isChecked()) {
            speakerStatus = "Native";
        } else {
            speakerStatus = "International";
        }

        boolean valid = true;
        String errorMessage = "";

        if(email.isEmpty()) {
            errorMessage = "Please enter an email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(email).matches())) {
            errorMessage = "Please enter a valid email!";
        } else if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(email).matches())) {
            errorMessage = "Please enter a USC email!";
        } else if (email.contains("#") || email.contains("$") || email.contains("[") || email.contains("]") || email.substring(email.indexOf(".")+1).contains(".")) {
            errorMessage = "Please enter am email without '.', '#', '$', '[', or ']'";
        } else if(firstName.isEmpty() || !Character.isUpperCase(firstName.charAt(0))) {
            errorMessage = "Please enter a valid first name!";
        } else if (lastName.isEmpty() || !Character.isUpperCase(lastName.charAt(0))) {
            errorMessage = "Please enter a valid last name!";
        }

        if(!errorMessage.isEmpty()) {
            Toast errorToast = Toast.makeText(ProfileEditActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
            valid = false;
        }

        if (valid) {

            List<Integer> interests = Arrays.asList(
                ((Switch) findViewById(R.id.sports_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.food_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.gym_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.partying_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.programming_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.travelling_switch)).isChecked() ? 1 : -1,
                ((Switch) findViewById(R.id.movies_switch)).isChecked() ? 1 : -1
                    );

            // Backend needs to upload this interests list to user in DB

            // Perform backend work
            // Don't forget to update CurrentUser.currentUser object!
//            ArrayList<String> keys = FirebaseData.getDataKeys();
            ArrayList<UserType> data = FirebaseData.getData();
            data = FirebaseData.getData();

            for (int i = 0; i < data.size(); i++) {
                if(CurrentUser.currentUser.getmEmail() == data.get(i).getmEmail()) {
                    CurrentUser.currentUser.setmFirstname(firstName);
                    CurrentUser.currentUser.setmLastname(lastName);
                    CurrentUser.currentUser.setmEmail(email);
                    CurrentUser.currentUser.setmType(speakerStatus);

                    UserType u = new UserType(email, CurrentUser.currentUser.getmPassword(), firstName, lastName, speakerStatus);
                    u.setmFriends(CurrentUser.currentUser.getmFriends());
                    u.setmInterestsList(CurrentUser.currentUser.getmInterestsList());

                    data.set(i, u);
                    CurrentUser.currentUser = u;
                    break;
                }
            }

            FirebaseDatabase.getInstance().getReference("users").removeValue();

            for(UserType uu : data) {
                FirebaseDatabase.getInstance().getReference("users").push()
                        .setValue(uu);
            }

        }

        onBackPressed();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        findViewById(R.id.back_button).setOnClickListener(backListener);
        findViewById(R.id.confirm_button).setOnClickListener(confirmListener);

        ((EditText) findViewById(R.id.edit_first_name)).setText(CurrentUser.currentUser.getmFirstname());
        ((EditText) findViewById(R.id.edit_last_name)).setText(CurrentUser.currentUser.getmLastname());
        ((EditText) findViewById(R.id.edit_email)).setText(CurrentUser.currentUser.getmEmail());
        if (CurrentUser.currentUser.getmType().equals("Native")) {
            ((RadioButton) findViewById(R.id.edit_radio_native)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.edit_radio_international)).setChecked(true);
        }

        // HARDCODED INTERESTS BELOW FOR TESTING; ACTUAL CODE IN COMMENT
        List<Integer> interests = Arrays.asList(-1, 1, 1, 1, 1, -1, -1); //CurrentUser.currentUser.getmInterestsList();
        for (int i = 0; i < interests.size(); i++) {
                if (i == 0)
                    ((Switch) findViewById(R.id.sports_switch)).setChecked(interests.get(i) == 1);
                else if (i == 1)
                    ((Switch) findViewById(R.id.food_switch)).setChecked(interests.get(i) == 1);
                else if (i == 2)
                    ((Switch) findViewById(R.id.gym_switch)).setChecked(interests.get(i) == 1);
                else if (i == 3)
                    ((Switch) findViewById(R.id.partying_switch)).setChecked(interests.get(i) == 1);
                else if (i == 4)
                    ((Switch) findViewById(R.id.programming_switch)).setChecked(interests.get(i) == 1);
                else if (i == 5)
                    ((Switch) findViewById(R.id.travelling_switch)).setChecked(interests.get(i) == 1);
                else if (i == 6)
                    ((Switch) findViewById(R.id.movies_switch)).setChecked(interests.get(i) == 1);
        }
    }

}
