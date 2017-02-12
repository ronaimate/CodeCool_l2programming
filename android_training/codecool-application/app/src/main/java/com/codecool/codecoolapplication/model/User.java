package com.codecool.codecoolapplication.model;

import android.util.Log;

import com.codecool.codecoolapplication.enums.TestType;

import java.util.HashMap;
import java.util.List;

public class User {

    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private HashMap<TestType, Integer> application;
    private HashMap<TestType, Boolean> testSuccess;

    public HashMap<TestType, Boolean> getTestSuccess() {
        return testSuccess;
    }

    public void setTestSuccess(HashMap<TestType, Boolean> testSuccess) {
        this.testSuccess = testSuccess;
    }

    public User(String name, String id, String address, String email, String phone, HashMap<TestType, Integer> application, HashMap<TestType, Boolean> testSuccess) {
        Log.d("USER", "Constructor");
        this.name = name;
        Log.d("USER_ID_constructor", id);
        this.id = id;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.application = application;
        this.testSuccess = testSuccess;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        Log.d("USER_setID", id);
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public HashMap<TestType, Integer> getApplication() {
        return application;
    }

    public void setApplication(HashMap<TestType, Integer> application) {
        this.application = application;
    }
}
