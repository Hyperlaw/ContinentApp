package com.example.continentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListFragment extends Fragment {


    private List<String> items;
    private RecyclerViewAdapter.OnItemClickListener listener;
    private String title;

    public ListFragment(List<String> items, RecyclerViewAdapter.OnItemClickListener listener, String title) {
        this.items = items;
        this.listener = listener;
        this.title = title;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items, listener);
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            ((MainActivity) getActivity()).setHeaderTitle(title);
        }

    }
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setHeaderTitle(title);
        }
    }
}