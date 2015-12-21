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
 * Created by Igwe Igwe-Kalu on 12/3/15.
 */
public class AddFriendListFragment extends Fragment {

        private RecyclerView mAddFriendRecyclerView;
        private AddFriendAdapter mAddAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

            mAddFriendRecyclerView = (RecyclerView) view.findViewById(R.id.friend_recycler_view);
            mAddFriendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            updateUI();
            return view;
        }

    private void updateUI() {
        AddFriendLab addfriendLab = AddFriendLab.get(this.getContext());
        List<Friend> friendList = addfriendLab.getFriends();
        mAddAdapter = new AddFriendAdapter(friendList);
        mAddFriendRecyclerView.setAdapter(mAddAdapter);
    }

    private class AddFriendHolder extends RecyclerView.ViewHolder {

        public TextView mAddTitleTextView;
        private TextView mFriendNumberTextView;
        public Friend mFriendList;

        public void bindFriend(Friend friend) {
            mFriendList = friend;
            mAddTitleTextView.setText(friend.getName());
            mFriendNumberTextView.setText(friend.getPhone());

        }

        public AddFriendHolder(View itemView) {
            super(itemView);
            mAddTitleTextView = (TextView) itemView.findViewById(R.id.list_add_item_friend_title);
            mFriendNumberTextView = (TextView) itemView.findViewById(R.id.list_add_item_friend_number);
        }
    }

    private class AddFriendAdapter extends RecyclerView.Adapter<AddFriendHolder> {

        private List<Friend> mFriendLists;

        public AddFriendAdapter(List<Friend> friends) {
            mFriendLists = friends;
        }

        @Override
        public AddFriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_add_friend_item, parent, false);
            return new AddFriendHolder(view);
        }

        @Override
        public void onBindViewHolder(AddFriendHolder holder, int position) {
            Friend friend= mFriendLists.get(position);
            holder.bindFriend(friend);
        }

        @Override
        public int getItemCount() {
            return mFriendLists.size();
        }

    }
}
