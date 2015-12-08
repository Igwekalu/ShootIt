package com.bignerdranch.android.shootit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendListFragment extends Fragment {

    public RecyclerView mFriendRecyclerView;
    public FriendAdapter mAdapter;
    public TextView mDateTextView;

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
        FriendListFragment updatedFragment = new FriendListFragment();
        android.support.v4.app.FragmentManager fm = getFragmentManager();

        FriendLab friendLab = new FriendLab(this.getContext());
        List<Shoot> allPosts = friendLab.getResults();
        mAdapter = new FriendAdapter(allPosts);
        mFriendRecyclerView.setAdapter(mAdapter);

        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.friend_recycler_view, updatedFragment);
        //ft.addToBackStack(null).commit();
        //FriendLab friendLab = FriendLab.get(getActivity());
    }

    private class FriendHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public FriendList mFriendList;
        public Shoot mShootList;

        /*public void bindFriend(FriendList friendList) {
            mFriendList = friendList;
            mTitleTextView.setText(mFriendList.getTitle());
            mDateTextView.setText(mFriendList.getDate().toString());

        }*/
        public void bindShoot(Shoot shoot){
            mShootList = shoot;
            mTitleTextView.setText(mShootList.getPhone() + " shot the " + mShootList.getLocation() + "!");
            mDateTextView.setText(mShootList.getDate().toString());
        }

        public FriendHolder(View itemView) {
            super(itemView);


            mTitleTextView=(TextView)itemView.findViewById(R.id.list_item_friend_title);
            mDateTextView= (TextView)itemView.findViewById(R.id.list_item_date);
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

        private List<FriendList> mFriendLists;
        private List<Shoot> mShootLists;

        public FriendAdapter(List<Shoot> posts) {

            //mFriendLists = friendLists;
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
            //FriendList friendList = mFriendLists.get(position);
            Shoot shoot = mShootLists.get(position);
            //holder.bindFriend(friendList);
            holder.bindShoot(shoot);
        }

        @Override
        public int getItemCount() {
            return mShootLists.size();
            //return mFriendLists.size();
        }

    }
}