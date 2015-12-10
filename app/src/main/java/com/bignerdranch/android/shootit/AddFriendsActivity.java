package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendsActivity extends SingleAddFriendsActivity {
    @Override
    protected Fragment createFragment() {
        return new AddFriendListFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, AddFriendsActivity.class);
        return i;
    }

    public static class AddAFriend extends SingleAddFriendsActivity{
        @Override
        protected Fragment createFragment() {

            return new AddFriendFragment();
        }

        public Intent newIntent(Context packageContext) {
            Intent j = new Intent(packageContext, AddAFriend.class);
            return j;
        }
    }
}
