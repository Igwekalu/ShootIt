package com.bignerdranch.android.shootit;

import android.support.v4.app.Fragment;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendsActivity extends SingleAddFriendsActivity {
    @Override
    protected Fragment createFragment() {
        return new AddFriendListFragment();
    }
}
