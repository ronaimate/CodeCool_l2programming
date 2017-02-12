package com.codecool.codecoolapplication.view2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.network.MessageHandler;

public class TextualIntroduction extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mSenderTextViewButton;
    private EditText mIntroductionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textual_introduction);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_textual);
        mToolbar.setTitle("Textual Introduction");


        initializeVariable();
        eventHandler();

    }

    private void initializeVariable() {
        mSenderTextViewButton = (TextView) findViewById(R.id.textual_intro_activity_action_button);
        mIntroductionText = (EditText) findViewById(R.id.editText2);
    }

    private void eventHandler()
    {
        mSenderTextViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String introductionText = mIntroductionText.getText().toString();
                MessageHandler.sendIntroductionTextToServer("https://codecool-application.appspot.com/app2", introductionText, TextualIntroduction.this);
                Intent mainIntent = new Intent(TextualIntroduction.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mainIntent.putExtra("userSessionId", MainActivity.user.getId());
                startActivity(mainIntent);
            }
        });
    }
}
