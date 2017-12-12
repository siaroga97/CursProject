package com.example.notepadby.cursproject.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notepadby.cursproject.CreateActivity;
import com.example.notepadby.cursproject.R;
import com.example.notepadby.cursproject.adapters.RecyclerViewAdapter;
import com.example.notepadby.cursproject.constants.Constants;

import com.example.notepadby.cursproject.entity.ListElement;

import java.util.List;


public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddButton;
    private LinearLayoutManager mLayoutManager;

    private RecyclerViewAdapter mAdapter;

    public void setElements(List<ListElement> elements) {
        this.elements = elements;
    }

    private List<ListElement> elements;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAddButton = view.findViewById(R.id.fab_add);
        mAddButton.setOnClickListener(listener -> {
            Intent intent = new Intent(getContext(), CreateActivity.class);

            startActivityForResult(intent, Constants.REQUEST_CODE_LIST_UPDATED);
        });

        new LoadContent().execute();
        return view;
    }

    public void changeData() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    class LoadContent extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                mAdapter = new RecyclerViewAdapter(elements);
                mLayoutManager = new LinearLayoutManager(getContext());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
             //   assert mAdapter != null;
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(mLayoutManager);
            } catch (Exception ex) {
               // Log.e(Constants.APP_LOGS, "WE FUCKED UP!");
                ex.printStackTrace();
            }
        }
    }
}
