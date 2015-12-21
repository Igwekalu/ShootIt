package com.bignerdranch.android.shootit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.UUID;

/**
 * Created by robbiepaine on 12/6/15.
 */

@ParseClassName("ShootItFriends")
public class Friend extends ParseObject{

    private String mFriendName;
    private String mPhoneNumber;
    private String mObjectId;
    private String mMyNumber;
    private UUID mId;
    private Date mDate;


    public Friend() {
        //this(UUID.randomUUID());
        mObjectId = getObjectId();
        mPhoneNumber = getPhone();
        mFriendName = getName();
        mDate = getDate();
    }

    public Friend(String name, String phone, Date date) {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mFriendName = name;
        mPhoneNumber = phone;
        if (date == null){
            mDate = new Date();
        }else {
            mDate = date;
        }
    }

    //public  getId(){ return mId; }

    public String getPhone() {
        return mPhoneNumber;
    }

    public void setMyNumber(String number){
        mMyNumber = number;
        put("MyNumber", number);
    }

    public String getMyNumber(){
        return mMyNumber;
    }

    public void setPhone(String number){
        mPhoneNumber = number;
        put("PhoneNumber", number);
    }

    public String getName() {
        return mFriendName;
    }

    public void setName(String value) {
        mFriendName = value;
        put("Name", value);
    }

    public Date getDate(){ return mDate;}

    public String getDateString(){
        if (mDate != null){
            return mDate.toString();
        }else return "No Date Available";
    }

    public void setDate(Date date) {
        mDate = date;
        put("CreatedAt", mDate);
    }

    public void setObjectId(String id){
        mObjectId = id;
    }

    public String getObjectId(){
        return mObjectId;
    }

    public static ParseQuery<Friend> getQuery() {
        return ParseQuery.getQuery(Friend.class);
    }

}
