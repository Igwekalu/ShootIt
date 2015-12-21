package com.bignerdranch.android.shootit;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendListFragment extends Fragment {

    public RecyclerView mFriendRecyclerView;
    public FriendAdapter mAdapter;
    public List<Friend> mFriendList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mFriendRecyclerView = (RecyclerView) view.findViewById(R.id.friend_recycler_view);
        mFriendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    public void updateUI() {
        FriendLab friendLab = new FriendLab(this.getContext());
        AddFriendLab addFriendLab= new AddFriendLab(this.getContext());
        List<Shoot> allPosts = friendLab.getResults();
        mAdapter = new FriendAdapter(allPosts);
        mFriendRecyclerView.setAdapter(mAdapter);
    }

    private class FriendHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public TextView mDateTextView;
        public Shoot mShootList;


        public int color;
        Random rand = new Random();

        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        int randomColor = Color.rgb(r, g, b);


        public void bindShoot(Shoot shoot){
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
            String mFriend = null;
            mShootList = shoot;
            /*for (int i=0;i<mFriendList.size();i++) {
                if (mShootList.getPhone() == mFriendList.get(i).getPhone()){
                    mFriend = mFriendList.get(i).getName();
                }
            }*/

            if (mFriend != null) {
                mTitleTextView.setText(mFriend + " shot the " + mShootList.getLocation() + "!");
            }else {
                mTitleTextView.setText(mShootList.getPhone() + " shot the " + mShootList.getLocation() + "!");
            }
            mDateTextView.setText(mShootList.getDateString());
        }

        public FriendHolder(View itemView) {
            super(itemView);
            mTitleTextView=(TextView)itemView.findViewById(R.id.list_item_friend_title);
            mTitleTextView.setBackgroundColor(randomColor);
            mDateTextView= (TextView)itemView.findViewById(R.id.list_item_date);
            mDateTextView.setBackgroundColor(randomColor);
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

        private List<Shoot> mShootLists;

        final ListAdapter mListAdapter = new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return mShootLists.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };

        public FriendAdapter(List<Shoot> posts) {
            mShootLists = posts;
        }

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_friend, parent, false);
            return new FriendHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            Shoot shoot = mShootLists.get(position);
            holder.bindShoot(shoot);
        }

        @Override
        public int getItemCount() {
            return mShootLists.size();
        }

        public int getItem(int position){
            return 0;
        }

    }
}
