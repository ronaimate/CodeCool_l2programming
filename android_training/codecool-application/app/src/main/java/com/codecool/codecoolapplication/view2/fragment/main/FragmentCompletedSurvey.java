package com.codecool.codecoolapplication.view2.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.view2.activity.MainActivity;

/**
 * Created by andra on 2016-07-06.
 */
public class FragmentCompletedSurvey extends Fragment {

    TextView gotoNextSurveyCover;
    View view;
    int percentage;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_completedsurvey, container, false);
        getActivity().setTitle("Completed");
        initializeVariable();
        gotoNextSurveyCoverOnClick();
        return view;
    }

    private void initializeVariable()
    {
        gotoNextSurveyCover = (TextView) view.findViewById(R.id.fragment_completed_button);
        Bundle bundle = getArguments();
        percentage = bundle.getInt("percentage");
        textView = (TextView) view.findViewById(R.id.percentage);
        textView.setText(percentage + " %");
    }

    private void gotoNextSurveyCoverOnClick()
    {
        gotoNextSurveyCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int applicationPosition = ((MainActivity)getActivity()).getApplicationPosition(true);
                ((MainActivity)getActivity()).setFragmentToViewGroup(applicationPosition);
            }
        });
    }

}
