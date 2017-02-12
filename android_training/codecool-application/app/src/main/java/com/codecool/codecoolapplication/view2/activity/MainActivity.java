package com.codecool.codecoolapplication.view2.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.enums.TestType;
import com.codecool.codecoolapplication.model.User;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentAcceptanceCover;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentApplicationSubmit;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentCompletedSurvey;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentEnglishCover;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentFailedApplication;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentHome;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentIntroductionCover;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentLogicCover;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentNotYetAvailable;
import com.codecool.codecoolapplication.view2.fragment.main.FragmentThankYou;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static Map<Integer, Fragment> fragments = new HashMap<>();
    private FragmentTransaction fragmentTransaction;
    public static User user;
    private String sessionId;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private int applicationPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        initializeFragmentsToHashMap();
        setupToolbar();
        setupDrawer();
        SurveyActivity.position = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeVariables();
        setFragmentToViewGroup(getApplicationPosition(false));
        Log.d("userNameString", user.getName());
        Log.d("ActivityLifeCicyle", "resume");
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            logOut();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("applicationPosition", applicationPosition);
        Log.d("ActivityLifeCicyle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        applicationPosition = savedInstanceState.getInt("applicationPosition");
        Log.d("ActivityLifeCicyle", "restore");
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("CodecooL");
    }

    private void setupDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationViewTop = (NavigationView) findViewById(R.id.navigation_view_top);
        navigationViewTop.setNavigationItemSelectedListener(this);

        NavigationView topNavigationView = (NavigationView) findViewById(R.id.navigation_view_top);
        View headerLayout = topNavigationView.getHeaderView(0);
        TextView drawerName = (TextView) headerLayout.findViewById(R.id.drawerName);
        TextView drawerEmail = (TextView) headerLayout.findViewById(R.id.drawerEmail);
        drawerName.setText(user.getName());
        drawerEmail.setText(user.getEmail());
    }

    public void initializeVariables() {
        sessionId = getIntent().getStringExtra("userSessionId");
        user = MessageHandler.getUser("http://codecool-application.appspot.com/app2", sessionId, this);
        user.setId(sessionId);
    }

    public void initializeFragmentsToHashMap() {
        fragments.put(0, new FragmentHome());
        fragments.put(1, new FragmentAcceptanceCover());
        fragments.put(2, new FragmentEnglishCover());
        fragments.put(3, new FragmentLogicCover());
        fragments.put(4, new FragmentIntroductionCover());
        fragments.put(5, new FragmentApplicationSubmit());
        fragments.put(6, new FragmentThankYou());

        fragments.put(7, new FragmentCompletedSurvey());
        fragments.put(8, new FragmentCompletedSurvey());
        fragments.put(9, new FragmentCompletedSurvey());
    }

    public void setFragmentToViewGroup(int applicationPosition) {
        Bundle userBundle = new Bundle();
        Fragment fragment = fragments.get(applicationPosition);
        if (applicationPosition == 7) {
            userBundle.putInt("percentage", user.getApplication().get(TestType.ACCEPTANCE));
            fragment.setArguments(userBundle);
        } else if (applicationPosition == 8) {
            userBundle.putInt("percentage", user.getApplication().get(TestType.ENGLISH));
            fragment.setArguments(userBundle);
        } else if (applicationPosition == 9) {
            userBundle.putInt("percentage", user.getApplication().get(TestType.LOGIC));
            fragment.setArguments(userBundle);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.main_placeholder, fragment);
        fragmentTransaction.commit();
    }


    public int getApplicationPosition(boolean isMethodCall) {

        if (isMethodCall) {
            SurveyActivity.lastTestResult = -1;
        }

        int userAcceptanceEvaluated = user.getApplication().get(TestType.ACCEPTANCE);
        int userEnglishEvaluated = user.getApplication().get(TestType.ENGLISH);
        int userLogicEvaluated = user.getApplication().get(TestType.LOGIC);
        int userIntroductionEvaluated = user.getApplication().get(TestType.INTRODUCTION);

        if (userAcceptanceEvaluated != -1 && userEnglishEvaluated != -1
                && userLogicEvaluated != -1 && userIntroductionEvaluated != -1) {
            applicationPosition = 6;
        } else if (SurveyActivity.lastTestResult == 1) {
            applicationPosition = 7;
        } else if (SurveyActivity.lastTestResult == 2) {
            applicationPosition = 8;
        } else if (SurveyActivity.lastTestResult == 3) {
            applicationPosition = 9;
        } else if (userAcceptanceEvaluated == -1) {
            applicationPosition = 0;
        } else if (userEnglishEvaluated == -1) {
            applicationPosition = 2;
        } else if (userLogicEvaluated == -1) {
            applicationPosition = 3;
        } else if (userIntroductionEvaluated == -1) {
            applicationPosition = 4;
        } else {
            applicationPosition = 0;
        }
        Log.d("applicatipnpos", String.valueOf(applicationPosition));
        return applicationPosition;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            changePlaceholderContent(0);
        } else if (id == R.id.nav_acceptance) {
            changePlaceholderContent(1);
        } else if (id == R.id.nav_english) {
            changePlaceholderContent(2);
        } else if (id == R.id.nav_logic) {
            changePlaceholderContent(3);
        } else if (id == R.id.nav_introduction) {
            changePlaceholderContent(4);
        } else if (id == R.id.nav_devs) {
            Toast.makeText(MainActivity.this, "Who Made This App Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            logOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changePlaceholderContent(int index) {
        Fragment necessaryFragment;
        if (getComplatedIfNeeded(index)) {

            Bundle userBundle = new Bundle();
            necessaryFragment = new FragmentCompletedSurvey();
            if (index == 1) {
                userBundle.putInt("percentage", user.getApplication().get(TestType.ACCEPTANCE));
                necessaryFragment.setArguments(userBundle);
            } else if (index == 2) {
                userBundle.putInt("percentage", user.getApplication().get(TestType.ENGLISH));
                necessaryFragment.setArguments(userBundle);
            } else if (index == 3) {
                userBundle.putInt("percentage", user.getApplication().get(TestType.LOGIC));
                necessaryFragment.setArguments(userBundle);
            }
        else if (index == 4) {
            userBundle.putInt("percentage", user.getApplication().get(TestType.INTRODUCTION));
            necessaryFragment.setArguments(userBundle);
        }


        } else if (getNotYetAvailableIfNeeded(index)) {
            necessaryFragment = fragments.get(index);
        } else {
            necessaryFragment = new FragmentNotYetAvailable();
        }
        //Todo ide kell majd egy olyan is ha már kész van akkor a comlated oldalra dobjon be.
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.main_placeholder, necessaryFragment);
        fragmentTransaction.commit();
    }

    private boolean getNotYetAvailableIfNeeded(int index) {
        int userAcceptanceEvaluated = user.getApplication().get(TestType.ACCEPTANCE);
        int userEnglishEvaluated = user.getApplication().get(TestType.ENGLISH);
        int userLogicEvaluated = user.getApplication().get(TestType.LOGIC);

        if (index == 2 && userAcceptanceEvaluated == -1) {
            return false;
        } else if (index == 3 && userEnglishEvaluated == -1) {
            return false;
        } else if (index == 4 && userLogicEvaluated == -1) {
            return false;
        }
        return true;
    }

    private boolean getComplatedIfNeeded(int index) {
        int userAcceptanceEvaluated = user.getApplication().get(TestType.ACCEPTANCE);
        int userEnglishEvaluated = user.getApplication().get(TestType.ENGLISH);
        int userLogicEvaluated = user.getApplication().get(TestType.LOGIC);
        int userIntroductionEvaluated = user.getApplication().get(TestType.INTRODUCTION);

        if (index == 1 && userAcceptanceEvaluated > -1) {
            return true;
        } else if (index == 2 && userEnglishEvaluated > -1) {
            return true;
        } else if (index == 3 && userLogicEvaluated > -1) {
            return true;
        } else if (index == 4 && userIntroductionEvaluated > -1) {
            return true;
        }
        return false;
    }

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logging Out");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MessageHandler.logoutUser("http://codecool-application.appspot.com/app2", user.getId(), MainActivity.this);
                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

}
