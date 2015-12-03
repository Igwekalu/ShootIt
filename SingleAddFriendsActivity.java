package com.bignerdranch.android.shootit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public abstract class SingleAddFriendsActivity extends FragmentActivity{

    private Button mAddButton;
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_fragment);
        //Intent i = AddFriendFragment.newIntent(SingleAddFriendsActivity.this);
          //      startActivity(i);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_list);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.friend_list, fragment)
                    .commit();
        }

        mAddButton = (Button) findViewById(R.id.add_plus);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleAddFriendsActivity.this, "You Shot the Gym!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
