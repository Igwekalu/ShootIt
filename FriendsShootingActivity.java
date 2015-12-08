package com.bignerdranch.android.shootit;

import android.support.v4.app.Fragment;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendsShootingActivity extends SingleFragmentActivity{
    @Override
     protected Fragment createFragment() {
        return new FriendListFragment();
    }
}
