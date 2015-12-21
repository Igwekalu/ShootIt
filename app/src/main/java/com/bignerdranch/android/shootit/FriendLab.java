package com.bignerdranch.android.shootit;

import android.content.Context;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igweigwe-kalu on 11/24/15.
 */
public class FriendLab{

    private static FriendLab sFriendLab;
    //private FriendList mFriendLists;
    //public List<ParseObject> mShootList = new ArrayList<ParseObject>();
    private List<Friend> mFriendLists;
    private List<Shoot> mShootList;
    private Friend mFriend;


    public static FriendLab get(Context context) {
        if (sFriendLab == null) {
            sFriendLab = new FriendLab(context);
        }
        return sFriendLab;
    }

    public FriendLab(Context context) {
        //mFriendLists = new ArrayList<>();
        mShootList = new ArrayList<>(getResults());
        for (int i = 0; i < mShootList.size(); i++) {
            Shoot shoot = new Shoot();
            shoot.setPhone(mShootList.get(i).getPhone());
            shoot.setDate(mShootList.get(i).getDate());
        }
    }

    public List<Friend> getFriendList() {

        final ParseQuery<Friend> query = Friend.getQuery();
        query.orderByDescending("createdAt").whereMatches("MyNumber", SingleFragmentActivity.mPhoneNumber);
        try{
            List<Friend> queryResult = query.find();
            for (Friend friend : queryResult){
                mFriendLists.add(new Friend(friend.getString("Name"), friend.getString("PhoneNumber"), friend.getDate("createdAt")));
            }
        }
        catch(ParseException e){
            Log.d("error", "didn't work" + e.getMessage());
        }
        return mFriendLists;
    }

    public List<Shoot> getResults() {
        mShootList = new ArrayList<Shoot>();
        mFriendLists = new ArrayList<Friend>(getFriendList());
        final ParseQuery<Shoot> query = Shoot.getQuery();
        for (int i=0; i<mFriendLists.size(); i++)
        query.orderByDescending("createdAt").whereMatches("PhoneNumber", mFriendLists.get(i).getPhone());
        try{
            List<Shoot> queryResult = query.find();
            for (Shoot post : queryResult){
                mShootList.add(new Shoot(post.getString("Location"), post.getString("PhoneNumber"), post.getDate("createdAt")));
            }
        }
        catch(ParseException e){
            Log.d("error", "didn't work" + e.getMessage());
        }
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
