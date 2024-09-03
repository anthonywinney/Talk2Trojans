package com.example.a310finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    private void generateProfile() {
        ((TextView) getView().findViewById(R.id.profile_first_name)).setText(CurrentUser.currentUser.getmFirstname());
        ((TextView) getView().findViewById(R.id.profile_last_name)).setText(CurrentUser.currentUser.getmLastname());
        ((TextView) getView().findViewById(R.id.profile_email)).setText(CurrentUser.currentUser.getmEmail());
        ((TextView) getView().findViewById(R.id.profile_speaker_status)).setText(CurrentUser.currentUser.getmType());

        ((LinearLayout) getView().findViewById(R.id.profile_fragment_interests_list)).removeAllViews();
        // HARDCODED INTERESTS BELOW FOR TESTING; ACTUAL CODE IN COMMENT
        List<Integer> interests = Arrays.asList(-1, 1, 1, 1, 1, -1, -1); // CurrentUser.currentUser.getmInterestsList();
        for (int i = 0; i < interests.size(); i++) {
            if (interests.get(i) == 1) {
                TextView tv = new TextView(getActivity());
                if (i == 0) {
                    tv.setText("Sports");
                } else if (i == 1) {
                    tv.setText("Food");
                } else if (i == 2) {
                    tv.setText("Gym");
                } else if (i == 3) {
                    tv.setText("Partying");
                } else if (i == 4) {
                    tv.setText("Programming");
                } else if (i == 5) {
                    tv.setText("Travelling");
                } else if (i == 6) {
                    tv.setText("Movies");
                }
                ((LinearLayout) getView().findViewById(R.id.profile_fragment_interests_list)).addView(tv);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        generateProfile();
    }

}