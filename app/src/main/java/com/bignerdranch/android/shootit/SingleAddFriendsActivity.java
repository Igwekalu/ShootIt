package com.bignerdranch.android.shootit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public abstract class SingleAddFriendsActivity extends FragmentActivity{

    private Button mAddButton;
    protected abstract Fragment createFragment();
    private SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_fragment);

        final FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.friend_list);


        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.friend_list, fragment)
                    .commit();
        }


        mAddButton = (Button) findViewById(R.id.add_plus);
        mAddButton.setEnabled(true);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFriendsActivity.AddAFriend AddAFriend= new AddFriendsActivity.AddAFriend();
                Intent i = AddAFriend.newIntent(SingleAddFriendsActivity.this);
                startActivity(i);

            }
        });
    }
}
