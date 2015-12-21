package com.bignerdranch.android.shootit;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendLab {

    private static AddFriendLab sAddFriendLab;
    private AddFriendList mAddFriendList;
    private List<Friend> mFriendList;

    //private List<AddFriendList> mAddFriendLists;

    public static AddFriendLab get(Context context) {
        if (sAddFriendLab == null) {
            sAddFriendLab = new AddFriendLab(context);
        }
        return sAddFriendLab;
    }

    private AddFriendLab(Context context) {
        mFriendList = new ArrayList<>();

        mFriendList = new ArrayList<>(getFriends());
        for (int i = 0; i < mFriendList.size(); i++) {
            Friend friend = new Friend();
            friend.setName(mFriendList.get(i).getName());
            friend.setPhone(mFriendList.get(i).getPhone());
        }
    }

    public List<Friend> getAddFriendList() {
        return mFriendList;
    }

    /*public AddFriendList getFriendList(UUID id) {
        for (Friend friend : mFriendList) {
            if (friend.getId().equals(id)) {
                return friend;
            }
        }
        return null;
    }*/

    public List<Friend> getFriends() {
        mFriendList = new ArrayList<Friend>();

        final ParseQuery<Friend> query = Friend.getQuery();
        //query.orderByDescending("createdAt").whereMatches("MyNumber", SingleFragmentActivity.mPhoneNumber);
        query.whereExists("PhoneNumber");
        query.findInBackground(new FindCallback<Friend>() {
            @Override
            public void done(final List<Friend> List, ParseException e) {
                if (e == null) {
                    for (final Friend friend : List) {
                        mFriendList.add(new Friend(friend.getString("Name"), friend.getString("PhoneNumber"), friend.getDate("createdAt")));
                    }
                } else {
                    Log.d("error", "didn't work" + e.getMessage());
                }
            }
        });
        return mFriendList;
    }

}
