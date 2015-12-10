package com.bignerdranch.android.shootit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.UUID;

/**
 * Created by robbiepaine on 12/6/15.
 */

@ParseClassName("ShootIt")
public class Shoot extends ParseObject{

    private String mLocation;
    private String mPhoneNumber;
    private String mObjectId;
    private UUID mId;
    private Date mDate;

    public Shoot() {
        //this(UUID.randomUUID());
        mObjectId = getObjectId();
        mPhoneNumber = getPhone();
        mLocation = getLocation();
        mDate = getDate();
    }

    public Shoot(String location, String phone, Date date) {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mLocation = location;
        mPhoneNumber = phone;
        if (date == null){
            mDate = new Date();
        }else {
            mDate = date;
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

    public Date getDate(){ return mDate;}

    public String getDateString(){
        if (mDate != null){
            return mDate.toString();
        }else return "No Date Available";
    }

    public void setDate(Date date) {
        mDate = date;
        put("CreatedAt", date);
    }

    public void setObjectId(String id){
        mObjectId = id;
    }

    public String getObjectId(){
        return mObjectId;
    }

    public static ParseQuery<Shoot> getQuery() {
        return ParseQuery.getQuery(Shoot.class);
    }

}
