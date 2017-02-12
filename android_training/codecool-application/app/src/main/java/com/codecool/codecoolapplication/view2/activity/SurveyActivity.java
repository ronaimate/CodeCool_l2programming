package com.codecool.codecoolapplication.view2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.enums.TestType;
import com.codecool.codecoolapplication.model.Question;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.view2.fragment.survey.FragmentQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SurveyActivity extends AppCompatActivity {

    public static HashSet<String> mandatoryQuestions;
    private TestType testType;
    private List<Fragment> questionAndAnswerFragmentsList = new ArrayList<>();
    private String surveyBankUrl;
    public static int position = 0;
    private boolean isComplate = false;
    private Button nextButton, previousButton;
    private TextView toolbarNumber;
    private TextView isMandatoryTextView;
    public static int lastTestResult = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        this.setTitle("");
        initializeTestType();
        initializeVariables();
        initializeSurveyBankUrl();
        initializeQuestionFragmentsToList();
        surveyButtonsHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        questionStepper(position);
    }

    private void surveyControlElementsHandler() {
        Log.d("surveyPosition", String.valueOf(position));
        Log.d("surveyPositionList", String.valueOf(getQuestionListSize()));
        toolbarNumber.setText((position + 1) + "/" + getQuestionListSize());
        if (position == 0) {
            previousButton.setText("");
            previousButton.setEnabled(false);
        } else if (position == (getQuestionListSize() - 1)) {
            nextButton.setText("SUBMIT SURVEY");
            isComplate = true;
        } else if (position != 0) {
            isComplate = false;
            previousButton.setText("PREV QUESTION");
            nextButton.setText("Next QUESTION");
            previousButton.setEnabled(true);
        }
    }

    public void surveyButtonsHandler() {
        surveyControlElementsHandler();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isComplate) {
                    position++;
                    questionStepper(position);
                    surveyControlElementsHandler();
                } else {
                    mandatoryListChecker();
                    if(mandatoryQuestions.size() == 0)
                    {
                        submitSurvey();
                        clearSharedPref();
                        mandatoryQuestions.clear();
                    }
                    else {
                        Log.d("mandatorySize", String.valueOf(mandatoryQuestions.size()));
                        Toast.makeText(SurveyActivity.this, "Please fill the mandatory Questions.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                questionStepper(position);
                surveyControlElementsHandler();
            }
        });
    }

    private void mandatoryListChecker() {
        HashMap<String, String> answersAndQuestionsHashMap = getOptionsAndQuestions();
        for (Map.Entry<String, String> entry : answersAndQuestionsHashMap.entrySet()) {
            mandatoryQuestions.remove(entry.getValue());
            Log.d("MNDItemHMP", entry.getValue());
        }
        ArrayList<String> help = new ArrayList<>(mandatoryQuestions);
        for (int i = 0; i<help.size(); i++)
        {
            Log.d("MNDItemMND", help.get(i));
        }
    }

    private HashMap<String, String> getOptionsAndQuestions() {
        Map<String, String> resultHashMap = new HashMap<>();
        SharedPreferences mSharedpreferences = getSharedPreferences("com.codecool.codecoolapplication.questionsandanswers", Context.MODE_PRIVATE);

        Map<String, String> keys = (Map<String, String>) mSharedpreferences.getAll();
        Log.d("sharedfrefSize", keys.toString() + keys.size());
        for (Map.Entry<String, String> entry : keys.entrySet()) {
            resultHashMap.put(entry.getKey(), entry.getValue());
        }
        Log.d("resultHaspmapSize", String.valueOf(resultHashMap.size()));
        return (HashMap<String, String>) resultHashMap;
    }

    private void submitSurvey() {
        HashMap<String, String> sendHashMap = getOptionsAndQuestions();
        MessageHandler.sendSurveyToServer(sendHashMap, testType.toString(), "http://codecool-application.appspot.com/app2", this);
        Intent mainIntent = new Intent(SurveyActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainIntent.putExtra("userSessionId", MainActivity.user.getId());
        setLastTestResult();
        startActivity(mainIntent);
    }

    private void setLastTestResult() {
        if(true && testType == TestType.ACCEPTANCE)
        {
           lastTestResult = 1;
        }
        else if(true && testType == TestType.ENGLISH)
        {
            lastTestResult = 2;
        }
        else if(true && testType == TestType.LOGIC)
        {
            lastTestResult = 3;
        }
        else
        {
            lastTestResult = 0;
        }
        Log.d("testTypeValueInSurvey", String.valueOf(lastTestResult));

    }

    private void clearSharedPref() {
        SharedPreferences mSharedpreferences = getSharedPreferences("com.codecool.codecoolapplication.questionsandanswers", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedpreferences.edit();
        mEditor.clear();
        mEditor.apply();
    }

    private void initializeTestType() {
        testType = TestType.valueOf(getIntent().getStringExtra("testType"));
    }

    private void initializeVariables() {
        isMandatoryTextView = (TextView) findViewById(R.id.mandatorystar);
        nextButton = (Button) findViewById(R.id.survey_nextquestion);
        previousButton = (Button) findViewById(R.id.survey_previous);
        toolbarNumber = (TextView) findViewById(R.id.toolbar_prev_question);
        mandatoryQuestions = new HashSet<>();
    }

    private void initializeSurveyBankUrl() {
        if (testType == TestType.ACCEPTANCE) {
            surveyBankUrl = "http://codecool-application.appspot.com/acceptance";
        } else if (testType == TestType.ENGLISH) {
            surveyBankUrl = "https://codecool-application.appspot.com/english";
        } else if (testType == TestType.LOGIC) {
            surveyBankUrl = "http://codecool-application.appspot.com/logic";
        }
    }

    private void initializeQuestionFragmentsToList() {
        List<Question> questionsAndAnswers;
        questionsAndAnswers = MessageHandler.geSurveyBank(surveyBankUrl, this);

        while (true)
        {
            if(questionsAndAnswers != null)
            {
                break;
            }
        }

        for (Question question : questionsAndAnswers) {
            Fragment fragment = new FragmentQuestion();
            Bundle bundle = new Bundle();
            bundle.putParcelable("actualQuestion", question);
            fragment.setArguments(bundle);
            questionAndAnswerFragmentsList.add(fragment);
        }

    }


    public void setMandatoryTextView(boolean questionMandatory)
    {
        if(questionMandatory)
        {
            isMandatoryTextView.setText("mandatory question".toUpperCase());
        }
        else
        {
            isMandatoryTextView.setText("");
        }
    }


    private void questionStepper(int position) {
        Fragment fragment = questionAndAnswerFragmentsList.get(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.survey_placeholder, fragment);
        fragmentTransaction.commit();
    }

    private int getQuestionListSize() {
        return questionAndAnswerFragmentsList.size();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
