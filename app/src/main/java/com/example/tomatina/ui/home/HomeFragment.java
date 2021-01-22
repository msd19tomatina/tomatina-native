package com.example.tomatina.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomatina.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // List Array of Strings
        String[] focusGroups = {
                "Gruppe 1            " + "03/15            " + "27",
                "Gruppe 2            " + "11/17            " + "47",
                "Gruppe 3            " + "18/17            " + "08",
                "Gruppe 4            " + "40/75            " + "88",
        };

        ListView lw = (ListView) root.findViewById(R.id.list);

        // this adapter is needed so that listView knows what to display
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                focusGroups
        );

        lw.setAdapter(listViewAdapter);


        return root;
    }
}