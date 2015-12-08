package com.bignerdranch.android.shootit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by igweigwe-kalu on 11/25/15.
 */
public class FriendListFragment extends Fragment {

    private RecyclerView mFriendRecyclerView;
    private FriendAdapter mAdapter;
    private TextView mTitleTextView;
    private TextView mDateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mFriendRecyclerView = (RecyclerView) view.findViewById(R.id.friend_recycler_view);
        mFriendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        FriendLab friendLab = FriendLab.get(getActivity());
        List<FriendList> friendLists = friendLab.getFriendList();

        mAdapter = new FriendAdapter(friendLists);
        mFriendRecyclerView.setAdapter(mAdapter);
    }

    private class FriendHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        private FriendList mFriendList;

        public void bindFriend(FriendList friendList) {
            mFriendList = friendList;
            mTitleTextView.setText(mFriendList.getTitle());
            mDateTextView.setText(mFriendList.getDate().toString());

        }

        public FriendHolder(View itemView) {
            super(itemView);


            mTitleTextView=(TextView)itemView.findViewById(R.id.list_item_friend_title);
            mDateTextView= (TextView)itemView.findViewById(R.id.list_item_date);
        }
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

        private List<FriendList> mFriendLists;

        public FriendAdapter(List<FriendList> friendLists) {
            mFriendLists = friendLists;
        }

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_friend, parent, false);
            return new FriendHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            FriendList friendList = mFriendLists.get(position);
            holder.bindFriend(friendList);
        }

        @Override
        public int getItemCount() {
            return mFriendLists.size();
        }

    }
}
