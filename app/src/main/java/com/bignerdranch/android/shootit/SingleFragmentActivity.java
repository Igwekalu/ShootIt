package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.Random;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    private Switch mSwitch;
    private Button mRefreshButton;
    private Button mFriendButton;
    private Button mShootGym;
    private Button mShootLib;
    private Button mShootMather;

    private Context mAppContext;
    private TelephonyManager tMgr;

    private TextView phoneNumberView;
    public String mPhoneNumber;
    public static String mAddFriendNumber;


    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_shooting_fragment);

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Shoot.class);
        ParseObject.registerSubclass(Friend.class);
        Parse.initialize(this, "SiMNxGWtpqUqvPjFHNDC8X9vv95gYHHq3BuuOoyQ", "MjWwXB0bRNHaiaw8fB20gf1uN0IIFYwteTp0INDY");

        final FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_layout);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.friend_layout, fragment).commit();
        }

        mFriendButton = (Button) findViewById(R.id.add_friend);
        mFriendButton.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(SingleFragmentActivity.this, "Getting  more Shoots... ", Toast.LENGTH_SHORT).show();
                Fragment frg = null;

                Fragment fragment1 = fm.findFragmentById(R.id.friend_layout);
                if (fragment1 == null) {
                    fragment1 = createFragment();
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(fragment1);
                ft.attach(fragment1).commit();
            }
        });

        phoneNumberView = (TextView) findViewById(R.id.phone_number);


        if (((TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                == TelephonyManager.PHONE_TYPE_NONE) {
            //uncomment this to hardcode number
            //mPhoneNumber = "6172334779";
            //mPhoneNumber = phoneNumberView.toString();
            mAddFriendNumber = mPhoneNumber;
        } else {
            tMgr = (TelephonyManager) mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber = tMgr.getLine1Number();
            //comment this to hardcode number
            phoneNumberView.setEnabled(false);
            phoneNumberView.setText("My Phone Number: " + mPhoneNumber);
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mShootGym = (Button) findViewById(R.id.shoot_gym);
        mShootGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoot gymShoot = new Shoot();
                gymShoot.setLocation("Gym");
                gymShoot.setPhone(mPhoneNumber);
                gymShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Gym! ", Toast.LENGTH_SHORT).show();

                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mPhoneNumber, null, "Your friend " + mPhoneNumber + "is shooting the gym!", null, null);
                    Toast.makeText(SingleFragmentActivity.this, "SMS SENT", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(SingleFragmentActivity.this, "SMS Not Sent...Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }

        });

        mShootLib = (Button) findViewById(R.id.shoot_library);
        mShootLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoot libShoot = new Shoot();
                libShoot.setLocation("Library");
                libShoot.setPhone(mPhoneNumber);
                libShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Library!", Toast.LENGTH_SHORT).show();

                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mPhoneNumber, null, "Your friend " + mPhoneNumber + "is shooting the Library!", null, null);
                    Toast.makeText(SingleFragmentActivity.this, "SMS SENT", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(SingleFragmentActivity.this, "SMS Not Sent...", Toast.LENGTH_LONG).show();
                }
            }
        });

        mShootMather = (Button) findViewById(R.id.shoot_mather);
        mShootMather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoot matherShoot = new Shoot();
                matherShoot.setLocation("Mather");
                matherShoot.setPhone(mPhoneNumber);
                matherShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot Mather!", Toast.LENGTH_SHORT).show();

                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mPhoneNumber, null, "Your friend " + mPhoneNumber + "is shooting Mather!", null, null);
                    Toast.makeText(SingleFragmentActivity.this, "SMS SENT", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(SingleFragmentActivity.this, "SMS Not Sent...", Toast.LENGTH_LONG).show();
                }
            }
        });

        mSwitch = (Switch) findViewById(R.id.off_switch);
        mSwitch.setChecked(true);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFriendButton.setEnabled(true);
                    mShootGym.setEnabled(true);
                    mShootLib.setEnabled(true);
                    mShootMather.setEnabled(true);
                } else {
                    mFriendButton.setEnabled(false);
                    mShootGym.setEnabled(false);
                    mShootLib.setEnabled(false);
                    mShootMather.setEnabled(false);
                }
            }
        });
    }
}
