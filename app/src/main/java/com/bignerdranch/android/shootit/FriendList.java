package com.bignerdranch.android.shootit;

import java.util.Date;
import java.util.UUID;

/**
 * Created by igweigwe-kalu on 11/18/15.
 */
public class FriendList {

    private UUID mId;
    private String mTitle;
    private Date mDate;

    public FriendList() {
        this(UUID.randomUUID());
    }

    public FriendList(UUID id) {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}