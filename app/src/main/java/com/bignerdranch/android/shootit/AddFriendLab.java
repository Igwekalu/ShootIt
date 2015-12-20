package com.bignerdranch.android.shootit;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendLab {

    private static AddFriendLab sAddFriendLab;
    private AddFriendList mAddFriendList;

    private List<AddFriendList> mAddFriendLists;

    public static AddFriendLab get(Context context) {
        if (sAddFriendLab == null) {
            sAddFriendLab = new AddFriendLab(context);
        }
        return sAddFriendLab;
    }

    private AddFriendLab(Context context) {
        mAddFriendLists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AddFriendList addfriendList= new AddFriendList();
            addfriendList.setTitle("Friend #" + i);
            mAddFriendLists.add(addfriendList);
        }
    }

    public List<AddFriendList> getAddFriendList() {
        return mAddFriendLists;
    }

    public AddFriendList getAddFriendList(UUID id) {
        for (AddFriendList addfriendList : mAddFriendLists) {
            if (addfriendList.getId().equals(id)) {
                return addfriendList;
            }
        }
        return null;
    }
}
