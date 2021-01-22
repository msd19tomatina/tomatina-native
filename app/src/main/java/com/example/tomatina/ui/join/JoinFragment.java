package com.example.tomatina.ui.join;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tomatina.R;

public class JoinFragment extends Fragment {

    private JoinViewModel joinViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        joinViewModel =
                new ViewModelProvider(this).get(JoinViewModel.class);
        View root = inflater.inflate(R.layout.fragment_join, container, false);

        return root;
    }
}