package com.bignerdranch.android.shootit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.UUID;

/**
 * Created by robbiepaine on 12/6/15.
 */

//Class Shoot is an extention of a ParseObject that uses "put" to push fields into the
//database. It retrieves using a getQuery method that returns a query with the specified
//results. The database used is the "ShootIt" Parse Class that holds all shoots.
@ParseClassName("ShootIt")
public class Shoot extends ParseObject{

    private String mLocation;
    private String mPhoneNumber;
    private String mObjectId;
    private UUID mId;
    private Date mDates;
    private String mName;

    public Shoot() {
        //this(UUID.randomUUID());
        mObjectId = getObjectId();
        mPhoneNumber = getPhone();
        mLocation = getLocation();
        mDates = getDate();
    }

    public Shoot(String location, String phone, Date date) {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mLocation = location;
        mPhoneNumber = phone;
        if (date == null){
            mDates = new Date();
        }else {
            mDates = date;
        }
    }

    public String getPhone() {
        return mPhoneNumber;
    }

    public void setPhone(String number){
        mPhoneNumber = number;
        put("PhoneNumber", number);
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String value) {
        mLocation = value;
        put("Location", value);
    }

    public Date getDate(){ return mDates;}

    public String getName(String number) {
        for (int i = 0; i < AddFriendLab.mStaticFriendsList.size(); i++) {
            if (AddFriendLab.mStaticFriendsList.get(i).getPhone() == number)
                return AddFriendLab.mStaticFriendsList.get(i).getName();
        }
        return number;
    }

    public String getDateString(){
        if (mDates != null){
            return mDates.toString();
        }else return "No Date Available";
    }

    public void setDate(Date date) {
        mDates = date;
        //put("createdAt", mDate);
    }

    public String getObjectId(){
        return mObjectId;
    }

    public void setObjectId(String id){
        mObjectId = id;
    }

    public static ParseQuery<Shoot> getQuery() {
        return ParseQuery.getQuery(Shoot.class);
    }

}
