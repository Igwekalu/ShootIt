package com.bignerdranch.android.shootit;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by igweigwe-kalu on 11/24/15.
 */
public class FriendLab {
    private static FriendLab sFriendLab;
    private FriendList mFriendList;

    private List<FriendList> mFriendLists;

    public static FriendLab get(Context context) {
        if (sFriendLab == null) {
            sFriendLab = new FriendLab(context);
        }
        return sFriendLab;
    }

    private FriendLab(Context context) {
        mFriendLists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FriendList friendList= new FriendList();
            friendList.setTitle("Friend #" + i);
            mFriendLists.add(friendList);
        }
    }

    public List<FriendList> getFriendList() {
        return mFriendLists;
    }

    public FriendList getFriendList(UUID id) {
        for (FriendList friendList : mFriendLists) {
            if (friendList.getId().equals(id)) {
                return friendList;
            }
        }
        return null;
    }
}
