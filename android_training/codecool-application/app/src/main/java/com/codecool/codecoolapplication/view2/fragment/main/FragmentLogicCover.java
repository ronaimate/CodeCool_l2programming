package com.codecool.codecoolapplication.view2.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.enums.TestType;
import com.codecool.codecoolapplication.view2.activity.MainActivity;
import com.codecool.codecoolapplication.view2.activity.SurveyActivity;

public class FragmentLogicCover extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logic_cover, container, false);
        getActivity().setTitle("Logic Test");

        TextView button = (TextView) view.findViewById(R.id.fragment_logic_action_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent surveyIntent = new Intent(getActivity(), SurveyActivity.class);
                surveyIntent.putExtra("testType", TestType.LOGIC.toString());
                startActivity(surveyIntent);
            }
        });

        return view;
    }
}
