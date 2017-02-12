package com.codecool.codecoolapplication.network;

import android.util.Log;

import com.codecool.codecoolapplication.enums.QuestionType;
import com.codecool.codecoolapplication.enums.TestType;
import com.codecool.codecoolapplication.model.Option;
import com.codecool.codecoolapplication.model.Question;
import com.codecool.codecoolapplication.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ronai on 15/06/16.
 */
public class JsonHandler {

    public static User loginJsonToUser(String jsonString) {
        Log.d("JSONHANDLER_loginToUser", jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            String email = json.getJSONObject("user").getString("email");
            String name = json.getJSONObject("user").getString("name");
            String id = json.getJSONObject("user").getString("id");
            String address = json.getJSONObject("user").getString("address");
            String phone = json.getJSONObject("user").getString("phone");
            String acceptance = json.getJSONObject("user").getJSONObject("application").getString("acceptance");
            String englishTest = json.getJSONObject("user").getJSONObject("application").getString("englishtest");
            String logicTest = json.getJSONObject("user").getJSONObject("application").getString("logictest");
            String introduction = json.getJSONObject("user").getJSONObject("application").getString("introduction");
            Boolean acceptanceSuccess = null;
            Boolean englishTestSuccess = null;
            Boolean logicTestSuccess = null;
            Boolean introductionSuccess = null;

            if (!json.getJSONObject("user").getJSONObject("testsuccess").isNull("acceptance"))
            {
                acceptanceSuccess = json.getJSONObject("user").getJSONObject("testsuccess").getBoolean("acceptance");
            }
            if (!json.getJSONObject("user").getJSONObject("testsuccess").isNull("englishtest"))
            {
                englishTestSuccess = json.getJSONObject("user").getJSONObject("testsuccess").getBoolean("englishtest");
            }
            if (!json.getJSONObject("user").getJSONObject("testsuccess").isNull("logictest"))
            {
                logicTestSuccess = json.getJSONObject("user").getJSONObject("testsuccess").getBoolean("logictest");
            }
            if (!json.getJSONObject("user").getJSONObject("testsuccess").isNull("introduction"))
            {
                introductionSuccess = json.getJSONObject("user").getJSONObject("testsuccess").getBoolean("introduction");
            }


            HashMap<TestType, Integer> application = new HashMap<>();
            application.put(TestType.ACCEPTANCE, Integer.valueOf(acceptance));
            application.put(TestType.ENGLISH, Integer.valueOf(englishTest));
            application.put(TestType.LOGIC, Integer.valueOf(logicTest));
            application.put(TestType.INTRODUCTION, Integer.valueOf(introduction));

            HashMap<TestType, Boolean> testSuccess = new HashMap<>();
            testSuccess.put(TestType.ACCEPTANCE, acceptanceSuccess);
            testSuccess.put(TestType.ENGLISH, englishTestSuccess);
            testSuccess.put(TestType.LOGIC, logicTestSuccess);
            testSuccess.put(TestType.INTRODUCTION, introductionSuccess);

            return new User(name, id, address, email, phone, application, testSuccess);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Question> getTest(String jsonResponse) {
        JSONObject json;
        List<Question> questionList = new ArrayList<>();
        try {
            json = new JSONObject(jsonResponse);
            JSONArray questionArray = json.getJSONArray("questions");
            for (int i = 0; i < questionArray.length(); i++) {
                Question question = new Question();
                String id = questionArray.getJSONObject(i).getString("id");
                question.setId(id);
                String description = questionArray.getJSONObject(i).getString("description");
                question.setDescription(description);
                String mandatory = questionArray.getJSONObject(i).getString("mandatory");
                question.setMandatory(Boolean.valueOf(mandatory));
                JSONArray answerOption =  questionArray.getJSONObject(i).getJSONArray("option");
                List<Option> optionList = new ArrayList<>();
                for (int j = 0; j < answerOption.length(); j++) {
                    Option option = new Option();
                    String answerOptionId = answerOption.getJSONObject(j).getString("id");
                    option.setId(answerOptionId);
                    String answerOptionDescripton = answerOption.getJSONObject(j).getString("description");
                    option.setDescription(answerOptionDescripton);
                    String answerOptionType = answerOption.getJSONObject(j).getString("type");
                    option.setQuestionType(QuestionType.valueOf(answerOptionType.toUpperCase()));

                    if (!answerOption.getJSONObject(j).isNull("child")) {
                        Option optioninOption = new Option();
                        JSONObject answerOptionChild = answerOption.getJSONObject(j).getJSONObject("child");
                        String answerOptionChildDescription = answerOptionChild.getString("description");
                        optioninOption.setDescription(answerOptionChildDescription);
                        String answerOptionChildType = answerOptionChild.getString("type");
                        optioninOption.setQuestionType(QuestionType.valueOf(answerOptionType.toUpperCase()));

                        option.setChild(optioninOption);

                        //TODO majd kell rekurzív hívás ha opcióban lévő opciónak is van további opciója. :D
                    }
                    optionList.add(option);
                }
                question.setPossibleAnswers(optionList);
                questionList.add(question);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public static JSONObject loginJsonObject(String email, String password) {
        Log.d("JSONHANDLER_loginObject","EMAIL: "+email+"  PASSWORD: "+password);
        JSONObject login = new JSONObject();
        JSONObject loginContent = new JSONObject();
        try {
            loginContent.put("email", email);
            loginContent.put("password", password);
            login.put("login", loginContent);
            return login;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject forgottenPassword(String email) {
        Log.d("JSONHANDLER_forgotenpas",email);
        JSONObject forgottenPassword = new JSONObject();
        JSONObject forgottenPasswordContent = new JSONObject();
        try {
            forgottenPasswordContent.put("email", email);
            forgottenPassword.put("forgottenPassword", forgottenPasswordContent);
            return forgottenPassword;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject logoutJsonObject(String sessionId) {
        JSONObject logoutJsonObject = new JSONObject();
        try {
            logoutJsonObject.put("sessionId", sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logoutJsonObject;
    }

    public static JSONObject introductionTextJsonObject(String introductionText)
    {
        JSONObject introductionTextJsonObject = new JSONObject();
        try {
            introductionTextJsonObject.put("introductionText", introductionText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return introductionTextJsonObject;
    }

    public static JSONObject introductionVideoLinkJsonObject(String introductionText)
    {
        JSONObject introductionTextJsonObject = new JSONObject();
        try {
            introductionTextJsonObject.put("youtubeLink", introductionText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return introductionTextJsonObject;
    }
}
