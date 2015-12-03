package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
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
public class AddFriendListFragment extends Fragment{

        private RecyclerView mAddFriendRecyclerView;
        private AddFriendAdapter mAddAdapter;
        private TextView mAddTitleTextView;
        private TextView mFriendNumberTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_addfriend_list, container, false);

            mAddFriendRecyclerView = (RecyclerView) view.findViewById(R.id.addfriend_recycler_view);
            mAddFriendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            updateUI();
            return view;
        }

        private void updateUI() {
            AddFriendLab addfriendLab = AddFriendLab.get(getActivity());
            List<AddFriendList> addfriendLists = addfriendLab.getAddFriendList();

            mAddAdapter = new AddFriendAdapter(addfriendLists);
            mAddFriendRecyclerView.setAdapter(mAddAdapter);
        }

        private class AddFriendHolder extends RecyclerView.ViewHolder {

            public TextView mAddTitleTextView;
            private AddFriendList mAddFriendList;

            public void bindFriend(AddFriendList addfriendList) {
                mAddFriendList = addfriendList;
                mAddTitleTextView.setText(mAddFriendList.getTitle());
                mFriendNumberTextView.setText(mAddFriendList.getTitle());

            }

            public AddFriendHolder(View itemView) {
                super(itemView);


                mAddTitleTextView = (TextView) itemView.findViewById(R.id.list_add_item_friend_title);
                mFriendNumberTextView = (TextView) itemView.findViewById(R.id.list_add_item_friend_number);
            }
        }

        private class AddFriendAdapter extends RecyclerView.Adapter<AddFriendHolder> {

            private List<AddFriendList> mAddFriendLists;

            public AddFriendAdapter(List<AddFriendList> addfriendLists) {
                mAddFriendLists = addfriendLists;
            }

            @Override
            public AddFriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(R.layout.list_add_friend_item, parent, false);
                return new AddFriendHolder(view);
            }

            @Override
            public void onBindViewHolder(AddFriendHolder holder, int position) {
                AddFriendList addfriendList = mAddFriendLists.get(position);
                holder.bindFriend(addfriendList);
            }

            @Override
            public int getItemCount() {
                return mAddFriendLists.size();
            }

        }
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, AddFriendListFragment.class);
        return i;
    }
}
