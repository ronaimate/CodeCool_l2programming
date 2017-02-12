package com.codecool.codecoolapplication.model;

import android.util.Log;

import com.codecool.codecoolapplication.enums.QuestionType;

public class Option {
    private String id;
    private String description;
    private QuestionType questionType;
    private Option child;

    public Option(QuestionType questionType, String description) {
        this.questionType = questionType;
        this.description = description;
        Log.d("OPTION", "Constructor 1");

    }

    public Option(Option child, QuestionType questionType, String description) {
        Log.d("OPTION", "Constructor 2");

        this.child = child;
        this.questionType = questionType;
        this.description = description;
    }

    public Option()
    {
//        Log.d("OPTION", "Empty Constructor");

    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {

        Log.d("OPTION_setDescription", description);
        this.description = description;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setChild(Option child) {
        this.child = child;
    }
}
