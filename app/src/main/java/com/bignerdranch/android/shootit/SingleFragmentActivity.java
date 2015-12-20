package com.bignerdranch.android.shootit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    private Button mfriendButton;
    private Button mShootGym;
    private Button mShootLib;
    private Button mShootMather;
    private Button mRefreshButton;
    //private LocationManager locationManager;
    //private LocationListener locationListener;

    private Switch mSwitch;

    private TextView phoneNumberView;
    private String mPhoneNumber;

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

                Intent i = AddFriendsActivity.newIntent(SingleFragmentActivity.this);
                startActivity(i);
            }
        });


        mRefreshButton = (Button) findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*setContentView(R.layout.activity_friends_shooting_fragment);
                Intent i = SingleAddFriendsActivity.newIntent(this);
                startActivity(i);*/
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
    }

    //locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
}
