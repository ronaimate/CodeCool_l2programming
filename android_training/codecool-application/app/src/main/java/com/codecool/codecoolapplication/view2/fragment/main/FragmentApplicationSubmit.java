package com.codecool.codecoolapplication.view2.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecool.codecoolapplication.R;

/**
 * Created by andra on 2016-07-06.
 */
public class FragmentApplicationSubmit extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_submit, container, false);
        getActivity().setTitle("Submit Application");
        return view;
    }
}
