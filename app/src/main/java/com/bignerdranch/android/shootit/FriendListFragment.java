package com.bignerdranch.android.shootit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Random;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendListFragment extends Fragment {

    public RecyclerView mFriendRecyclerView;
    public FriendAdapter mAdapter;
    public int color;

    Random rand = new Random();

    int r = rand.nextInt(255);
    int g = rand.nextInt(255);
    int b = rand.nextInt(255);

    int randomColor = Color.rgb(r,g,b);


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
        List<Shoot> allPosts = friendLab.getResults();
        mAdapter = new FriendAdapter(allPosts);
        mFriendRecyclerView.setAdapter(mAdapter);
    }

    private class FriendHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public TextView mDateTextView;
        public Shoot mShootList;



        public void bindShoot(Shoot shoot){
            mShootList = shoot;
            mTitleTextView.setText(mShootList.getPhone() + " shot the " + mShootList.getLocation() + "!");
            mDateTextView.setText(mShootList.getDate().toString());
        }

        public FriendHolder(View itemView) {
            super(itemView);
            mTitleTextView=(TextView)itemView.findViewById(R.id.list_item_friend_title);
            mTitleTextView.setBackgroundColor(randomColor);
            mDateTextView= (TextView)itemView.findViewById(R.id.list_item_date);
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

        private List<Shoot> mShootLists;


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


    }
}
