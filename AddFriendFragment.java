package com.bignerdranch.android.shootit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendFragment extends Fragment {

    private AddFriend mAddFriend;
    private EditText mAddNameField;
    private EditText mAddNumberField;
    private AddFriendList mAddFriendList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAddFriend = new AddFriend();
        mAddFriendList = new AddFriendList();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addfriendlist, container, false);
        mAddNameField = (EditText)v.findViewById(R.id.addfriend_name);
        mAddNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mAddFriend.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mAddNumberField = (EditText)v.findViewById(R.id.friend_number);
        mAddNumberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mAddFriend.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        return v;
    }


}
