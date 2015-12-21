package com.bignerdranch.android.shootit;

import java.util.UUID;

/**
 * Created by igweigwe-kalu on 12/3/15.
 */
public class AddFriendList {

    private UUID mId;
    private String mTitle;


    public AddFriendList(UUID id) {
        // Generate unique identifier
        mId = UUID.randomUUID();
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
}
