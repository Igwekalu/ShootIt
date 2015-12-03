package com.bignerdranch.android.shootit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by igweigwe-kalu on 11/24/15.
 */
public class FriendFragment extends Fragment{

    private FriendsShooting mFriendsShooting;
    private EditText mNameField;
    private Button mDateButton;
    private FriendList mFriendList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFriendsShooting = new FriendsShooting();
        mFriendList = new FriendList();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.fragment_friendlist, container, false);
        mNameField = (EditText)v.findViewById(R.id.friend_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mFriendsShooting.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mDateButton = (Button)v.findViewById(R.id.friend_date);
        mDateButton.setText(mFriendList.getDate().toString());
        mDateButton.setEnabled(false);
        return v;
    }
}
