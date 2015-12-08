package com.bignerdranch.android.shootit;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by igweigwe-kalu on 11/24/15.
 */
public class FriendLab{

    private static FriendLab sFriendLab;
    //private FriendList mFriendLists;
    //public List<ParseObject> mShootList = new ArrayList<ParseObject>();
    private List<FriendList> mFriendLists;
    private List<Shoot> mShootList;


    public static FriendLab get(Context context) {
        if (sFriendLab == null) {
            sFriendLab = new FriendLab(context);
        }
        return sFriendLab;
    }

    public FriendLab(Context context) {
        mFriendLists = new ArrayList<>();
        mShootList = new ArrayList<>(getResults());
        for (int i = 0; i < mShootList.size(); i++) {
            Shoot shoot = new Shoot();
            //FriendList friendList = new FriendList();
            shoot.setPhone(mShootList.get(i).getPhone());
            //friendList.setTitle(getPost(mShootList, i));
            //mFriendLists.add(friendList);
            //mShootList.add(shoot);
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

    public List<Shoot> getResults() {
        mShootList = new ArrayList<Shoot>();

        final ParseQuery<Shoot> query = Shoot.getQuery();
        query.whereEqualTo("PhoneNumber", "7818542809");
        query.findInBackground(new FindCallback<Shoot>() {
            @Override
            public void done(final List<Shoot> List, ParseException e) {
                if (e == null) {
                    for (final Shoot post : List) {
                        mShootList.add(new Shoot(post.getString("Location"), post.getString("PhoneNumber")));
                    }
                } else {
                    Log.d("error", "didn't work" + e.getMessage());
                }
            }
        });
        return mShootList;
    }

    public String getPost(List<Shoot> listOfPosts, int post){
        if (listOfPosts.isEmpty() || post < mShootList.size()){
            return("~~Removed~~");
        }else{
            String location = listOfPosts.get(post).getLocation();
            String phone = listOfPosts.get(post).getPhone();
            return (phone + " shot the " + location + "!");
        }
    }
}
