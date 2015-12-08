package com.bignerdranch.android.shootit;

import android.content.Intent;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.Parse;
import com.parse.ParseObject;

import org.json.JSONArray;

import java.util.List;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    private UUID mId;

    private Button mfriendButton;
    private Button mShootGym;
    private Button mShootLib;
    private Button mShootMather;
    private Button mRefreshButton;

    private Switch mSwitch;

    private TextView phoneNumberView;
    private String mPhoneNumber;

    private String mTitle;

    private SwipeRefreshLayout swipeContainer;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_shooting_fragment);

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


        mRefreshButton = (Button) findViewById(R.id.button);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendListFragment ff = new FriendListFragment();
                ff.updateUI();
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SingleFragment Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.bignerdranch.android.shootit/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SingleFragment Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.bignerdranch.android.shootit/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
