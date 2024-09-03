package com.example.a310finalproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeetingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<UserType> data;

    public MeetingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingsFragment newInstance(String param1, String param2) {
        MeetingsFragment fragment = new MeetingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.meetings_fragment, container, false);
    }

    private void generateMeetings() {
        GridView meetingGrid = getView().findViewById(R.id.meeting_grid);
        // Retrieve meetings from DB and store in meetings
        ArrayList<Meeting> meetings = FirebaseData.getMeetings();
        // Begin dummy data
//        meetings.add(new Meeting("Anthony W", "Database Meeting",
//                "https://usc.zoom.us/j/3422258659?pwd=WWp0VW9WNWFKZlNHUXhKNGxSOThhUT09\n",
//                "11/6/23", "11:25", "English"));
//        meetings.add(new Meeting("Gurjot C", "Backend Meeting",
//                "https://usc.zoom.us/j/3422258659?pwd=WWp0VW9WNWFKZlNHUXhKNGxSOThhUT09\n",
//                "11/6/23", "06:10", "English"));
//        meetings.add(new Meeting("Tom W", "Frontend Meeting",
//                "https://usc.zoom.us/j/3422258659?pwd=WWp0VW9WNWFKZlNHUXhKNGxSOThhUT09\n",
//                "11/6/23", "10:50", "English"));
        // End dummy data

        MeetingViewAdapter adapter = new MeetingViewAdapter(getContext(), meetings, getParentFragmentManager());
        meetingGrid.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        generateMeetings();
    }
}