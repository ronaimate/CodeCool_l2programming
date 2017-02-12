package com.codecool.codecoolapplication.view2.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.model.Question;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.view2.fragment.login.FragmentLogin;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements FragmentLogin.LoginAnimation {
    private static final String TAG = LoginActivity.class.getName();

    // Constants
    private static final String LOGINURL = "http://codecool-application.appspot.com/testlogin";
    public static final String WRONG_EMAIL_ERR_MSG = "Wrong email address!";
    public static final String WRONG_PASS_ERR_MSG = "Wrong password!";
    public static final String NETWORK_IS_NOT_AVAILABLE_ERR_MSG = "Network is not available";

    public static final String TEST_TYPE = "TEST_TYPE";
    public static final String ACCEPTANCE_URL = "http://codecool-application.appspot.com/acceptance";
    public static final String LOGIC_URL = "http://codecool-application.appspot.com/logic";
    public static final String ENGLISH_URL = "http://codecool-application.appspot.com/english";

    RelativeLayout mLogoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogoLayout = (RelativeLayout) findViewById(R.id.logoLayout);
        mLogoLayout.setY(200f);

        FragmentLogin fragmentLogin = new FragmentLogin();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .add(R.id.login_placeholder, fragmentLogin);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void animateLoginScreen() {
        mLogoLayout.animate().translationYBy(-200f).setDuration(800);
    }
}
