package com.arpaul.samplenavigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arpaul.samplenavigation.R;

/**
 * Created by Aritra on 24-02-2017.
 */

public class ContentFragment extends Fragment {

    public static final String ARG_POSITION = "position";

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        int i = getArguments().getInt(ARG_POSITION);
        return rootView;
    }
}
