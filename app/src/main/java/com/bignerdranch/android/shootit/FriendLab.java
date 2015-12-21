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

    private List<Friend> mFriendList;
    private List<Shoot> mShootList;
    private List<String> mFriendsNumbers;


    public static FriendLab get(Context context) {
        if (sFriendLab == null) {
            sFriendLab = new FriendLab(context);
        }
        return sFriendLab;
    }

    public FriendLab(Context context) {

        mShootList = new ArrayList<>(getResults());
        for (int i = 0; i < mShootList.size(); i++) {
            Shoot shoot = new Shoot();
            shoot.setPhone(mShootList.get(i).getPhone());
            shoot.setDate(mShootList.get(i).getDate());
        }
    }

    public List<Friend> getFriends() {
        mFriendList = new ArrayList<Friend>();
        final ParseQuery<Friend> query = Friend.getQuery();
        query.orderByDescending("createdAt").whereMatches("MyNumber", SingleFragmentActivity.mAddFriendNumber);
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

    public List<Shoot> getResults() {
        mShootList = new ArrayList<Shoot>();
        mFriendList = new ArrayList<Friend>(getFriends());
        mFriendsNumbers = new ArrayList<String>();
        for (int i=0;i<mFriendList.size();i++) {
            mFriendsNumbers.add(mFriendList.get(i).getPhone());
        }
        ParseQuery<Shoot> query = Shoot.getQuery();
        query.orderByDescending("createdAt").whereContainedIn("PhoneNumber", mFriendsNumbers);
        try{
            List<Shoot> queryResult = query.find();
            for (Shoot post : queryResult){
                mShootList.add(new Shoot(post.getString("Location"), post.getString("PhoneNumber"), post.getCreatedAt()));
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
