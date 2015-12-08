package com.bignerdranch.android.shootit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import org.json.JSONArray;

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

    private Switch mSwitch;

    private TextView phoneNumberView;
    private String mPhoneNumber;

    private String mTitle;

    private SwipeRefreshLayout swipeContainer;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_shooting_fragment);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Shoot.class);
        Parse.initialize(this, "SiMNxGWtpqUqvPjFHNDC8X9vv95gYHHq3BuuOoyQ", "MjWwXB0bRNHaiaw8fB20gf1uN0IIFYwteTp0INDY");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_layout);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.friend_layout, fragment)
                    .commit();
        }


        mfriendButton = (Button) findViewById(R.id.add_friend);
        mfriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_add_friend_fragment);

                Intent i = AddFriendsActivity.newIntent(SingleFragmentActivity.this);

                startActivity(i);
            }
        });

        //TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        //mPhoneNumber = tm.getLine1Number();
        phoneNumberView = (TextView) findViewById(R.id.phone_number);

        mShootGym = (Button) findViewById(R.id.shoot_gym);
        mShootGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNumber = phoneNumberView.getText().toString();
                Shoot gymShoot = new Shoot();
                gymShoot.setLocation("Gym");
                gymShoot.setPhone(mPhoneNumber);
                gymShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Gym! ", Toast.LENGTH_SHORT).show();

            }
        });

        mShootLib = (Button) findViewById(R.id.shoot_library);
        mShootLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNumber = phoneNumberView.getText().toString();
                Shoot libShoot = new Shoot();
                libShoot.setLocation("Library");
                libShoot.setPhone(mPhoneNumber);
                libShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Library!", Toast.LENGTH_SHORT).show();
            }
        });

        mShootMather = (Button) findViewById(R.id.shoot_mather);
        mShootMather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNumber = phoneNumberView.getText().toString();
                Shoot matherShoot = new Shoot();
                matherShoot.setLocation("Mather");
                matherShoot.setPhone(mPhoneNumber);
                matherShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot Mather!", Toast.LENGTH_SHORT).show();
            }
        });

        mSwitch = (Switch) findViewById(R.id.off_switch);
        mSwitch.setChecked(true);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mfriendButton.setEnabled(true);
                    mShootGym.setEnabled(true);
                    mShootLib.setEnabled(true);
                    mShootMather.setEnabled(true);
                } else {
                    mfriendButton.setEnabled(false);
                    mShootGym.setEnabled(false);
                    mShootLib.setEnabled(false);
                    mShootMather.setEnabled(false);
                }
            }
        });
    }

//gggg


    public void fetchTimelineAsync(int page) {
        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones
                adapter.clear();
                // ...the data has come back, add new items to your adapter...
                adapter.addAll(...);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }
    /**
     * Created by rpaine on 12/7/15.
     */
    public static class adapter {
        public void clear() {
            items.clear();
            notifyDataSetChanged();
        }

        // Add a list of items
        public void addAll(List<list> list) {
            items.addAll(list);
            notifyDataSetChanged();
        }
    }
}
