package com.example.trunghoang.materialviewpager.materialviewpager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trunghoang.materialviewpager.R;
import com.example.trunghoang.materialviewpager.materialviewpager.adapter.TestRecyclerViewAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trunghoang on 7/28/15.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        // Allows as vertical list display
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // 100 false content
        List<Object> contentItems = new ArrayList<Object>();
        for (int i = 0; i < 100; i++) {
            contentItems.add(new Object());
        }

        // Get our thinking Adapter (here: TestRecyclerViewAdapter) a RecyclerViewMaterialAdapter
        adapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(contentItems));
        recyclerView.setAdapter(adapter);

        // Notify MaterialViewPager we will use a RecyclerView
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
    }
}
