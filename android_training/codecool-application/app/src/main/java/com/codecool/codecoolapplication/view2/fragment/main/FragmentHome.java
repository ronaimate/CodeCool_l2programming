package com.codecool.codecoolapplication.view2.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.enums.TestType;
import com.codecool.codecoolapplication.view2.activity.MainActivity;

public class FragmentHome extends Fragment{

    TextView homeTitle;
    View view;
    TextView button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");

        initializeVariables();
        setHomeElementText();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickEvent();
            }
        });
        return view;
    }

    private void buttonClickEvent()
    {
        if(MainActivity.user.getApplication().get(TestType.ACCEPTANCE) == -1)
        {
            ((MainActivity)getActivity()).setFragmentToViewGroup(1);
        }
        else
        {
            int applicationPosition = ((MainActivity)getActivity()).getApplicationPosition(true);
            ((MainActivity)getActivity()).setFragmentToViewGroup(applicationPosition);
        }
    }

    private void initializeVariables()
    {
        button = (TextView) view.findViewById(R.id.fragment_home_action_button);
        homeTitle = (TextView) view.findViewById(R.id.fragment_home_title);
    }

    private void setHomeElementText()
    {
        homeTitle.setText("Welcome, " + MainActivity.user.getName());
    }
}
