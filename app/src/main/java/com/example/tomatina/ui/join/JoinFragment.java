package com.example.tomatina.ui.join;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tomatina.R;

public class JoinFragment extends Fragment {

    private JoinViewModel joinViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        joinViewModel =
                new ViewModelProvider(this).get(JoinViewModel.class);
        View root = inflater.inflate(R.layout.fragment_join, container, false);

        ImageButton ib = (ImageButton) root.findViewById(R.id.button);
        ib.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Navigation.findNavController(root).navigate(R.id.navigation_timer);
                                  }
                              }
        );
        return root;
    }
}