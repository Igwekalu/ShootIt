package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendFragment extends Fragment {

    private Friend mAddFriend;
    private EditText mAddNameField;
    private EditText mAddNumberField;
    private Friend mAddFriendList;
    //private AddFriendList mAddFriendList;
    private Button mAddButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mAddFriend = new AddFriend();
        mAddFriend = new Friend();
        //mAddFriendList = new AddFriendList();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addfriendlist, container, false);

        //Activity got = SingleAddFriendsActivity.getActivity();

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
                mAddFriend.setName(s.toString());
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
                mAddFriend.setPhone(s.toString());
                //mAddFriendList.setPhone(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }

        });

        mAddNumberField.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Toast.makeText(getActivity(), "Adding " + mAddFriend.getName() + " to friends list...", Toast.LENGTH_SHORT).show();
                    if (SingleFragmentActivity.mAddFriendNumber.isEmpty()){
                        mAddFriend.setMyNumber("Default Number");
                    }else {
                        mAddFriend.setMyNumber(SingleFragmentActivity.mAddFriendNumber);
                    }
                    mAddFriend.saveInBackground();
                    return true;
                }
                return false;
            }
        });


        return v;
    }



    public static Intent newIntent(Context packageContext) {
        Intent j = new Intent(packageContext, AddFriendFragment.class);
        return j;
    }


}
