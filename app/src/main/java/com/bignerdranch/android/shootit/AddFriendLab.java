package com.bignerdranch.android.shootit;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;

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
    private Adapter mAdapter;

    //private List<AddFriendList> mAddFriendLists;

    public List<Friend> getFriends() {
        mFriendList = new ArrayList<Friend>();
        final ParseQuery<Friend> query = Friend.getQuery();
        query.orderByDescending("createdAt").whereMatches("MyNumber", SingleFragmentActivity.mAddFriendNumber);
        //query.whereExists("PhoneNumber");
        try{
            List<Friend> queryResult = query.find();
            for (Friend friend : queryResult){
                mFriendList.add(new Friend(friend.getString("Name"), friend.getString("PhoneNumber"), friend.getDate("createdAt")));
            }
        }
        catch(ParseException e){
            Log.d("error", "didn't work" + e.getMessage());
        }
        return mFriendList;
    }

    public static AddFriendLab get(Context context) {
        if (sAddFriendLab == null) {
            sAddFriendLab = new AddFriendLab(context);
        }
        return sAddFriendLab;
    }

    private AddFriendLab(Context context) {
        //mFriendList = new ArrayList<>();

        mFriendList = getFriends();
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

}
