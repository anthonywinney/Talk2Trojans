package com.example.a310finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendViewAdapter extends BaseAdapter {

    private Context context;
    private List<Friend> data;

    public FriendViewAdapter(Context context, List<Friend> data) {
        this.context = context;
        this.data = data;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_grid_item, parent, false);
        }

        Friend friend = data.get(position);
        Log.d("FriendViewAdapter", CurrentUser.currentUser.getmFriends());
        ((TextView) convertView.findViewById(R.id.friend_name)).setText(friend.getName());

        String buttonText = "";
        List<String> friends = new ArrayList<>(Arrays.asList(CurrentUser.currentUser.getmFriends().split(",")));
        if(friends.contains(friend.getName())) {
            buttonText = "Remove";
            View finalConvertView = convertView;
            ((Button) convertView.findViewById(R.id.add_remove_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    friends.remove(friend.getName());

                    ArrayList<String> keys = FirebaseData.getDataKeys();
                    ArrayList<UserType> dbData = FirebaseData.getData();

                    String key = "";
                    for (int i = 0; i < dbData.size(); i++) {
                        if(CurrentUser.currentUser.getmEmail() == dbData.get(i).getmEmail()) {
                            CurrentUser.currentUser.setmFriends(String.join(",", friends));
                            dbData.set(i, CurrentUser.currentUser);
                            break;
                        }
                    }

                    FirebaseDatabase.getInstance().getReference("users").removeValue();

                    for(UserType uu : dbData) {
                        FirebaseDatabase.getInstance().getReference("users").push()
                                .setValue(uu);
                    }

                    view.setVisibility(View.GONE);
                }
            });
        } else {
            buttonText = "Add";
            ((Button) convertView.findViewById(R.id.add_remove_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    friends.add(friend.getName());

                    ArrayList<String> keys = FirebaseData.getDataKeys();
                    ArrayList<UserType> dbData = FirebaseData.getData();

                    String key = "";
                    for (int i = 0; i < dbData.size(); i++) {
                        if(CurrentUser.currentUser.getmEmail() == dbData.get(i).getmEmail()) {
                            CurrentUser.currentUser.setmFriends(String.join(",", friends));
                            dbData.set(i, CurrentUser.currentUser);
                            break;
                        }
                    }

                    FirebaseDatabase.getInstance().getReference("users").removeValue();

                    for(UserType uu : dbData) {
                        FirebaseDatabase.getInstance().getReference("users").push()
                                .setValue(uu);
                    }

                    view.setVisibility(View.GONE);
                }

            });
        }
        ((Button) convertView.findViewById(R.id.add_remove_button)).setText(buttonText);


        return convertView;
    }

    public void addMeeting(Friend friend) {
        data.add(friend);
        notifyDataSetChanged();
    }
}
