package com.example.n.mvpexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PresenterInterface {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);

        new Presenter(this).PresenterLogic();

    }

    @Override
    public void setTextView(String textViewText) {
        mTextView.setText(textViewText);
    }
}
