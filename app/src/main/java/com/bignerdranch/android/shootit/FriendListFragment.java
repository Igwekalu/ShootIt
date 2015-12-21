package com.bignerdranch.android.shootit;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendListFragment extends Fragment {

    public RecyclerView mFriendRecyclerView;
    public FriendAdapter mAdapter;

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

        //int[] ColorList = new int[]{Color.RED,Color.GREEN,Color.YELLOW};
        //Random random = new Random();
        //int color = ColorList.get(random.nextInt(ColorList.length));


        ArrayList<Integer> ColorList = new ArrayList<Integer>();
        //ColorList.add(){Color.RED,Color.GREEN,Color.YELLOW};

        public void bindShoot(Shoot shoot){
            mShootList = shoot;
            mTitleTextView.setText(mShootList.getPhone() + " shot the " + mShootList.getLocation() + "!");
            mDateTextView.setText(mShootList.getDateString());
        }

        public FriendHolder(View itemView) {
            super(itemView);
            mTitleTextView=(TextView)itemView.findViewById(R.id.list_item_friend_title);
            //mTitleTextView.setBackgroundColor(color);
            mDateTextView= (TextView)itemView.findViewById(R.id.list_item_date);
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
