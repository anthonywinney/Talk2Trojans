package com.example.a310finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.util.List;

public class MeetingViewAdapter extends BaseAdapter {
    private Context context;
    private List<Meeting> data;
    private FragmentManager mFragmentManager;

    public MeetingViewAdapter(Context context, List<Meeting> data, FragmentManager fragmentManager) {
        this.context = context;
        this.data = data;
        mFragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.meeting_grid_item, parent, false);
        }

        Meeting meeting = data.get(position);
        TextView meetingNameTextView = convertView.findViewById(R.id.meeting_grid_item_name);
        Button meetingDetailsButton = convertView.findViewById(R.id.meeting_grid_item_button);
        TextView meetingHostTextView = convertView.findViewById(R.id.meeting_grid_item_host);
        TextView meetingDateTextView = convertView.findViewById(R.id.meeting_grid_item_date);

        meetingNameTextView.setText(meeting.getName());
        meetingHostTextView.setText("Hosted by " + meeting.getHost());
        meetingDateTextView.setText(meeting.getDate() + " @ " + meeting.getTime());
        meetingDetailsButton.setText("Details");

        meetingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingDetailsDialogFragment dialog = new MeetingDetailsDialogFragment(meeting);
                dialog.show(mFragmentManager, "Details");
            }
        });

        return convertView;
    }

    public void addMeeting(Meeting meeting) {
        data.add(meeting);
        notifyDataSetChanged();
    }
}
