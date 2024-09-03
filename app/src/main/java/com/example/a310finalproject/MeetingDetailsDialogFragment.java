package com.example.a310finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MeetingDetailsDialogFragment extends AppCompatDialogFragment {

    private Meeting mMeeting;

    private String positiveButtonText = "RSVP";

    public MeetingDetailsDialogFragment(Meeting meeting) {
        super();
        mMeeting = meeting;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.meeting_details, null);

        TextView meetingHost = view.findViewById(R.id.meeting_details_host);
        TextView meetingDate = view.findViewById(R.id.meeting_details_time);
        TextView meetingLanguage = view.findViewById(R.id.meeting_details_language);
        TextView meetingLink = view.findViewById(R.id.meeting_details_link);
        TextView meetingParticipantList = view.findViewById(R.id.meeting_details_participant_list);

        meetingHost.setText(mMeeting.getHost());
        
        meetingDate.setText(mMeeting.getDate() + " @ " + mMeeting.getTime());
        meetingLanguage.setText(mMeeting.getmLanguage());
        meetingLink.setText(mMeeting.getLink());
        meetingParticipantList.setText(mMeeting.getParticipants());

        // This string should either be 'RSVP' or 'Un-RSVP' based on if user is in
        // participant list or not

        positiveButtonText = (!mMeeting.getParticipantsList().contains(CurrentUser.currentUser.getFullName())) ? "RSVP" : "un-RSVP";
        if(mMeeting.getHost().equals(CurrentUser.currentUser.getFullName())) {
            System.out.println("here");
            positiveButtonText = "";
        }

        builder.setView(view)
                .setTitle(mMeeting.getName())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // This is where we should RSVP or un-RSVP based on if user is in
                        // participant list or not
//                        System.out.println(positiveButtonText);
                        if (positiveButtonText.equals("RSVP")) {
                            ArrayList<String> curr = mMeeting.getParticipantsList();
                            curr.add(CurrentUser.currentUser.getFullName());

                            ArrayList<Meeting> meetings = FirebaseData.getMeetings();
                            meetings = FirebaseData.getMeetings();

                            for (int j = 0; j < meetings.size(); j++) {
                                if(     mMeeting.getHost().equals(meetings.get(j).getHost()) &&
                                        mMeeting.getLink().equals(meetings.get(j).getLink())) {
                                    mMeeting.setParticipantsList(curr);

                                    Meeting m = new Meeting(mMeeting.getHost(), mMeeting.getName(), mMeeting.getLink(), mMeeting.getDate(), mMeeting.getTime(), mMeeting.getmLanguage());
                                    m.setParticipantsList(curr);

                                    meetings.set(j, m);
                                    mMeeting = m;
                                    break;
                                }
                            }

                            FirebaseDatabase.getInstance().getReference("meetings").removeValue();

                            for(Meeting mm : meetings) {
                                FirebaseDatabase.getInstance().getReference("meetings").push()
                                        .setValue(mm);
                            }


//                            ArrayList<String> keys = FirebaseData.getMeetingKeys();
//                            ArrayList<Meeting> meetings = FirebaseData.getMeetings();
//
//                            String key = "";
//                            for (int j = 0; j < meetings.size(); j++) {
//                                if(mMeeting == meetings.get(j)) {
//                                    key = keys.get(j);
//                                    break;
//                                }
//                            }
//
//                            mMeeting.setParticipantsList(curr);
//                            FirebaseDatabase.getInstance().getReference().child("meetings").child(key)
//                                    .setValue(mMeeting);

                            meetingParticipantList.setText(mMeeting.getParticipants());

                            positiveButtonText = "un-RSVP";
                        }
                        else if (positiveButtonText.equals("un-RSVP")) {
                            ArrayList<String> curr = mMeeting.getParticipantsList();
                            curr.remove(CurrentUser.currentUser.getFullName());

                            ArrayList<Meeting> meetings = FirebaseData.getMeetings();
                            meetings = FirebaseData.getMeetings();

                            for (int j = 0; j < meetings.size(); j++) {
                                if(     mMeeting.getHost().equals(meetings.get(j).getHost()) &&
                                        mMeeting.getLink().equals(meetings.get(j).getLink())) {
                                    mMeeting.setParticipantsList(curr);

                                    Meeting m = new Meeting(mMeeting.getHost(), mMeeting.getName(), mMeeting.getLink(), mMeeting.getDate(), mMeeting.getTime(), mMeeting.getmLanguage());
                                    m.setParticipantsList(curr);

                                    meetings.set(j, m);
                                    mMeeting = m;
                                    break;
                                }
                            }

                            FirebaseDatabase.getInstance().getReference("meetings").removeValue();

                            for(Meeting mm : meetings) {
                                FirebaseDatabase.getInstance().getReference("meetings").push()
                                        .setValue(mm);
                            }

                            meetingParticipantList.setText(mMeeting.getParticipants());

                            positiveButtonText = "RSVP";
                        }
                    }
                });
        return builder.create();
    }
}
