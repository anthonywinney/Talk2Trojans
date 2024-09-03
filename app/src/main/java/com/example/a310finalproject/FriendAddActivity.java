package com.example.a310finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FriendAddActivity extends AppCompatActivity implements FriendInviteDialogFragment.EmailListener {

    private String mSearch;
    private String mEmail;
    FriendViewAdapter mSuggested;
    FriendViewAdapter mResults;
    GridView mResultsGrid;
    TextView mResultsText;

    private View.OnClickListener backListener = view -> onBackPressed();

    private ArrayList<Friend> getSuggestedFullNames(String s) {
        s = s.toLowerCase();
        ArrayList<UserType> data = FirebaseData.getData();
        ArrayList<Friend> names = new ArrayList<Friend>();

        for(UserType d : data) {
            String temp = d.getFullName();
            temp = temp.toLowerCase();
            if(temp.contains(s)) {
                names.add(new Friend(d.getFullName()));
            }
        }

        return names;
    }

    private ArrayList<Friend> getSuggested() {
        // Return a list of type Friend of suggested friends for the current user using some
        // backend search with a matching mechanic
        ArrayList<Friend> suggested = new ArrayList<>();
        // Begin dummy data
        suggested.add(new Friend("Luffy"));
        suggested.add(new Friend("Zoro"));
        suggested.add(new Friend("Sanji"));
        // End dummy data
        return suggested;
    }

    private void updateResultsGrid() {
        if(mSearch.isEmpty()) {
            // When the search is empty, display suggested friends
            mResultsText.setText("Suggested");
            mResultsGrid.setAdapter(mSuggested);
        } else {
            // Some type of backend search of the DB should produce a list of type Friend that will
            // be used to update the results grid
            // May use a different type besides Friend if use case here conflicts with
            // FriendFragment use case
            mResultsText.setText("Results");
            ArrayList<Friend> searchResults = new ArrayList<>();
            searchResults = getSuggestedFullNames(mSearch);
            mResults = new FriendViewAdapter(this, searchResults);
            mResultsGrid.setAdapter(mResults);
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // this function is called before text is edited
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // this function is called when text is edited
            mSearch = s.toString();
            updateResultsGrid();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // this function is called after text is edited
        }
    };


    private View.OnClickListener inviteListener = view -> {
        FriendInviteDialogFragment dialog = new FriendInviteDialogFragment();
        dialog.show(getSupportFragmentManager(), "Invite");
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_add);

        mResultsText = (TextView) findViewById(R.id.results_textview);
        mResultsText.setText("Suggested");
        mSuggested = new FriendViewAdapter(this, getSuggested());
        mSearch = ((EditText) findViewById(R.id.search_textbox)).getText().toString();
        mResultsGrid = ((GridView) findViewById(R.id.results_grid));
        updateResultsGrid();
        findViewById(R.id.back_button).setOnClickListener(backListener);
        ((EditText) findViewById(R.id.search_textbox)).addTextChangedListener(textWatcher);
        findViewById(R.id.invite_button).setOnClickListener(inviteListener);
    }

    @Override
    public void applyEmail(String email) {
        // Email submitted in popup will appear in this function as parameter 'email'
        // Backend should use this email to send invite
        // Log.d("FragmentEmail", email);
        String errorMessage = "";
        boolean success = true;

        if (!(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(email).matches())) {
            errorMessage = "INVITE ERROR: Have to input a USC email!";
        }

        if (!errorMessage.isEmpty()) {
            success = false;
            Toast errorToast = Toast.makeText(FriendAddActivity.this, errorMessage, Toast.LENGTH_SHORT);
            errorToast.show();
        }

        // At this point, email is valid and ready to be used in backend

        try {
            final String app_email = "talk2trojans@gmail.com";
            final String app_pass = "gzaq ikfh bvuf fpzi";
            final String emailSubject = "You've Been Invited To Talk2Trojans";
            final int random_code = (int)(Math.random() * (99999 - 10000 + 1) + 10000);
            final String code = String.valueOf(random_code);
            final String emailMsg = "Hi, your friend " + CurrentUser.currentUser.getFullName() + " invited you to Talk2Trojans, an app where you can make friends while learning languages! Join now with the code " + code + "!";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");

            javax.mail.Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(app_email, app_pass);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));

            mimeMessage.setSubject(emailSubject);
            mimeMessage.setText(emailMsg);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            System.out.println("Starting thread");

            thread.start();

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("codes");
            mDatabase.push().setValue(code+email);
        }
        catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

}
