package com.codecool.codecoolapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.codecool.codecoolapplication.enums.QuestionType;

import java.util.List;

public class Question implements Parcelable{
    private String id;
    private String description;
    private Boolean mandatory;
    private List<Option> options;

    public Question(String id, String description, Boolean mandatory, List<Option> options) {
        this.id = id;
        this.description = description;
        this.mandatory = mandatory;
        this.options = options;
        Log.d("QUESTION", "Constructor");

    }

    public Question()
    {
//        Log.d("QUESTION", "Empty Constructor");

    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Log.d("QUESTION_setDescription", description);
        this.description = description;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<Option> getPossibleOptions() {
        return options;
    }

    public void setPossibleAnswers(List<Option> options) {
        this.options = options;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeString(mandatory.toString());
        dest.writeInt(options.size());
        for (Option option: options)
        {
            dest.writeString(option.getId());
            dest.writeString(option.getDescription());
            dest.writeString(option.getQuestionType().toString());
            //Todo ha van gyereke akkor itt mókolni kell.
        }
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Question(Parcel in) {
        id = in.readString();
        description = in.readString();
        mandatory = Boolean.valueOf(in.readString());
        for(int i = 0; i < in.readInt(); i++)
        {
            Option option = new Option();
            option.setId(in.readString());
            option.setDescription(in.readString());
            option.setQuestionType(QuestionType.valueOf(in.readString().toUpperCase()));
            options.add(option);
        }
    }
}
