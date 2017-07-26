package com.hiromisakurai.bookapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EditBookFragment extends Fragment {

    public EditBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title = (TextView)getActivity().findViewById(R.id.toolbar_main_title);
        Button button = (Button)getActivity().findViewById(R.id.button_add);
        title.setText(R.string.toolbar_title_edit);
        button.setText(R.string.toolbar_button_save);
    }
//    public static EditBookFragment newInstance(int position) {
//        EditBookFragment fragment = new EditBookFragment();
//
//    }
}
