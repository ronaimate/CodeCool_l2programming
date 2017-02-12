package com.example.n.mvpexample;

import android.content.Context;

/**
 * Created by Én on 2016. 08. 16..
 */
public class Presenter {
    PresenterInterface presenterInterface;

    public Presenter(Context context) {
        presenterInterface = (PresenterInterface) context;
    }

    public void PresenterLogic()
    {
        presenterInterface.setTextView("HAHA SIKER.");
    }
}
