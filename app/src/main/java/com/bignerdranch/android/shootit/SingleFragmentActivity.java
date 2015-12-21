package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    private Button mfriendButton;
    private Button mShootGym;
    private Button mShootLib;
    private Button mShootMather;
    private Button mRefreshButton;
    private Context mAppContext;
    private TelephonyManager tMgr;

    //private LocationManager locationManager;
    //private LocationListener locationListener;

    private Switch mSwitch;

    private TextView phoneNumberView;
    private String mPhoneNumber;
    private Date mDate;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_shooting_fragment);

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Shoot.class);
        Parse.initialize(this, "SiMNxGWtpqUqvPjFHNDC8X9vv95gYHHq3BuuOoyQ", "MjWwXB0bRNHaiaw8fB20gf1uN0IIFYwteTp0INDY");

        final FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_layout);

        if (fragment == null) {

            fragment = createFragment();
            fm.beginTransaction().add(R.id.friend_layout, fragment).commit();
            Toast.makeText(SingleFragmentActivity.this, "Pass! ", Toast.LENGTH_SHORT).show();
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

                Fragment fragment1 = fm.findFragmentById(R.id.friend_layout);
                if(fragment1 == null){
                    fragment1 = createFragment();
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(fragment1);
                ft.attach(fragment1).commit();

                /*Fragment currentFragment = SingleFragmentActivity.this.getSupportFragmentManager().findFragmentById(R.id.friend_layout);

                    FragmentTransaction fragTransaction =   (SingleFragmentActivity.this.getSupportFragmentManager().beginTransaction());
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();*/

               /* Fragment frg = null;
                frg = getSupportFragmentManager().findFragmentById(R.id.friend_layout);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();
                ft.show(frg);*/
            }
        });



        if (((TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                == TelephonyManager.PHONE_TYPE_NONE){
            mPhoneNumber = "0000000000";
        }

        else{
            tMgr = (TelephonyManager) mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber = tMgr.getLine1Number();
        }




        phoneNumberView = (TextView) findViewById(R.id.phone_number);
        phoneNumberView.setText("My Phone Number: " + mPhoneNumber);
        phoneNumberView.setEnabled(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        mShootGym = (Button) findViewById(R.id.shoot_gym);
        mShootGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDate = Shoot.getCurrentDate();
                Shoot gymShoot = new Shoot();
                gymShoot.setLocation("Gym");
                gymShoot.setPhone(mPhoneNumber);
                //gymShoot.setDate(mDate);
                gymShoot.saveInBackground();
                Toast.makeText(SingleFragmentActivity.this, "You Shot the Gym! ", Toast.LENGTH_SHORT).show();

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
