package com.example.vincent.lab1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vincent.lab1.R;

/**
 * Created by Vincent on 2016-09-19.
 */
public class PlaceHolderFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.place_holder_fragment, container, false);
    }
}
