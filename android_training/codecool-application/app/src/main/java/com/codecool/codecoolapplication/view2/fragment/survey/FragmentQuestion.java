package com.codecool.codecoolapplication.view2.fragment.survey;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.enums.QuestionType;
import com.codecool.codecoolapplication.model.Option;
import com.codecool.codecoolapplication.model.Question;
import com.codecool.codecoolapplication.view2.activity.MainActivity;
import com.codecool.codecoolapplication.view2.activity.SurveyActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andra on 2016-07-06.
 */
public class FragmentQuestion extends Fragment {

    private Question question;
    private TextView questionTextView;
    private LinearLayout questionLinearLayout;
    private View view;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedpreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);
        initializeQuestion();
        initializeLayoutVariables();
        setQuestionTextViewText();
        setQuestionLinearLayout();
        mandatoryQuestionsToMandatoryList();
        return view;
    }


    private void mandatoryQuestionsToMandatoryList() {
        if (question.getMandatory() && !(SurveyActivity.mandatoryQuestions.contains(question))) {
            SurveyActivity.mandatoryQuestions.add(question.getId());

            ((SurveyActivity)getActivity()).setMandatoryTextView(true);

        }
        else {
            ((SurveyActivity)getActivity()).setMandatoryTextView(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initializeLayoutVariables() {
        questionTextView = (TextView) view.findViewById(R.id.question_questiontextview);
        questionLinearLayout = (LinearLayout) view.findViewById(R.id.question_linearlayout);

    }

    private void initializeQuestion() {
        Bundle bundle = this.getArguments();
        question = bundle.getParcelable("actualQuestion");
    }

    private void setQuestionTextViewText() {
        String questionText = question.getDescription();
        questionTextView.setText(questionText);
    }

    private void setQuestionLinearLayout() {
        List<Option> options = question.getPossibleOptions();
        final RadioGroup rg = new RadioGroup(getContext());
        for (int i = 0; i < options.size(); i++) {

            if (options.get(i).getQuestionType() == QuestionType.RADIO) {
                rg.setOrientation(RadioGroup.VERTICAL);
                final RadioButton rb = new RadioButton(new ContextThemeWrapper(getContext(), R.style.RadioAndCheckBoxStyle));
                rg.addView(rb);
                rb.setText(options.get(i).getDescription());
                final int radioButtonId = Integer.valueOf(options.get(i).getId());
                boolean isToggled = false;
                initializeSharedPref();
                try {
                    Map<String, ?> keys = mSharedpreferences.getAll();
                    if (keys.get(String.valueOf(radioButtonId)) != null) {
                        isToggled = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isToggled) {
                    rb.toggle();
                }
                rb.setId(radioButtonId);
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = rg.getChildCount();
                        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<>();
                        for (int i = 0; i < count; i++) {
                            View o = rg.getChildAt(i);
                            if (o instanceof RadioButton) {
                                listOfRadioButtons.add((RadioButton) o);
                            }
                        }

                        for (RadioButton rb : listOfRadioButtons) {

                            if ((radioButtonId != rb.getId())) {
                                mEditor.remove(String.valueOf(rb.getId()));
                                mEditor.apply();
                                Log.d("sharedprefchangeDel", String.valueOf(rb.getId()));
                                rb.setChecked(false);
                            }
                        }
                        ((RadioButton) v).setChecked(true);
                        mEditor.putString(String.valueOf(radioButtonId), question.getId());
                        mEditor.apply();
                        Log.d("sharedprefchangeAdd", String.valueOf(radioButtonId));
                    }
                });
            } else if (options.get(i).getQuestionType() == QuestionType.CHECKBOX) {
                final CheckBox cb = new CheckBox(new ContextThemeWrapper(getContext(), R.style.RadioAndCheckBoxStyle));
                cb.setText(options.get(i).getDescription());
                questionLinearLayout.addView(cb);
                final int checkBoxId = Integer.valueOf(options.get(i).getId());
                boolean isToggled = false;
                initializeSharedPref();
                try {
                    Map<String, ?> keys = mSharedpreferences.getAll();
                    if (keys.get(String.valueOf(checkBoxId)) != null) {
                        isToggled = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isToggled) {
                    cb.toggle();
                }
                cb.setId(Integer.valueOf(options.get(i).getId()));
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!((CheckBox) v).isChecked()) {
                            mEditor.remove(String.valueOf(v.getId()));
                            mEditor.apply();
                            Log.d("checkboxClick", "if");
                            Log.d("sharedprefchangeDel", String.valueOf(checkBoxId));
                        } else {
                            Log.d("checkboxClick", "else");
                            mEditor.putString(String.valueOf(checkBoxId), question.getId());
                            mEditor.apply();
                            Log.d("sharedprefchangeAdd", String.valueOf(checkBoxId));
                        }
                        //Todo majd a listát elkell menteni, ha fordul a telefon akkor ebbe vannak eltárolva a bejelölt válaszok.
                    }
                });
            }
        }
        questionLinearLayout.addView(rg);
    }

    private void initializeSharedPref() {
        mSharedpreferences = getContext().getSharedPreferences("com.codecool.codecoolapplication.questionsandanswers", Context.MODE_PRIVATE);
        mEditor = mSharedpreferences.edit();
    }
}
