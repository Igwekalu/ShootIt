package com.bignerdranch.android.shootit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    private UUID mId;

    private Button mfriendButton;
    private Button mShootGym;
    private Button mShootLib;
    private Button mShootMather;
    private Intent i;

    private Switch mSwitch;

    private String mTitle;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_shooting_fragment);
        AddFriend.newIntent(SingleFragmentActivity.this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_layout);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.friend_layout, fragment)
                    .commit();
        }

        mfriendButton = (Button)findViewById(R.id.add_friend);
        mfriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_add_friend_fragment);
                startActivity(i);
            }
        });

        mShootGym = (Button)findViewById(R.id.shoot_gym);
        mShootGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Gym!", Toast.LENGTH_SHORT).show();
            }
        });

        mShootLib = (Button)findViewById(R.id.shoot_library);
        mShootLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Library!", Toast.LENGTH_SHORT).show();
            }
        });

        mShootMather = (Button)findViewById(R.id.shoot_mather);
        mShootMather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleFragmentActivity.this, "You Shot Mather!", Toast.LENGTH_SHORT).show();
            }
        });

        mSwitch = (Switch)findViewById(R.id.off_switch);
        mSwitch.setChecked(true);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mfriendButton.setEnabled(true);
                    mShootGym.setEnabled(true);
                    mShootLib.setEnabled(true);
                    mShootMather.setEnabled(true);
                }

                else{
                    mfriendButton.setEnabled(false);
                    mShootGym.setEnabled(false);
                    mShootLib.setEnabled(false);
                    mShootMather.setEnabled(false);
                }
            }
        });
    }
}
