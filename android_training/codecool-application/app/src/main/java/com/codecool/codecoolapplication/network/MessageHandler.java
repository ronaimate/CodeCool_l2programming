package com.codecool.codecoolapplication.network;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.util.Log;

import com.codecool.codecoolapplication.model.Question;
import com.codecool.codecoolapplication.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageHandler {
    private String host;

    public static void forgottenPassword(String url, String email, Activity activity) {
        Log.d("MSGHANDLER_forgotPass","URL: "+url+"  EMAIL: "+email );
        JSONObject forgottenPasswordJsonObject = JsonHandler.forgottenPassword(email);
        RequestBody forgottenPasswordRequestBody = new FormBody.Builder()
                .add("forgottenPasswordJsonObject", forgottenPasswordJsonObject.toString())
                .build();
            new RequestHandler(url, forgottenPasswordRequestBody, activity).sendRequest();
    }

    public static String loginUser(String url, String email, String password, Activity activity) {

        JSONObject loginJsonObject = JsonHandler.loginJsonObject(email, password);
        RequestBody loginUserRequestBody = new FormBody.Builder()
                .add("loginJsonObject", loginJsonObject.toString())
                .build();

            Log.d("MSGHANDLER_loginUsr_req", loginJsonObject.toString());
            String response = new RequestHandler(url, loginUserRequestBody, activity).sendRequest();
            Log.d("MSGHANDLER_loginUsr_res", response);
            return response;
    }

    public static void logoutUser(String url, String sessionId, Activity activity)
    {
        JSONObject logoutJsonObject = JsonHandler.logoutJsonObject(sessionId);
        RequestBody logoutUserRequestBody = new FormBody.Builder()
                .add("logoutJsonObject", logoutJsonObject.toString())
                .build();

        try {
            new RequestHandler(url, logoutUserRequestBody, activity).sendRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getQuestionsAndAnswers(String url, Activity activity) {
        Log.d("MSGHANDLER_getQA_url",url);
        RequestBody getQuestionsAndAnswersRequestBody = new FormBody.Builder()
                .build();


            return new RequestHandler(url, getQuestionsAndAnswersRequestBody, activity).sendRequest();
    }

    public static List<Question> geSurveyBank(String url, Activity activity) {
        return JsonHandler.getTest(MessageHandler.getQuestionsAndAnswers(url, activity));
    }

    public static User getUser(String url, String sessionId, Activity activity) {
        Log.d("MSGHANDLER_getUser_SID", sessionId);
        RequestBody getUserRequestBody = new FormBody.Builder()
                .add("sessionId", sessionId)
                .build();

            String response = new RequestHandler(url, getUserRequestBody, activity).sendRequest();
            return JsonHandler.loginJsonToUser(response);
    }

    public static void sendSurveyToServer(HashMap<String, String> answersAndQuestions, String testType, String url, Activity activity) {
        JSONObject survey = new JSONObject();
        JSONObject surveyContent = new JSONObject();
        for (Map.Entry<String, String> entry : answersAndQuestions.entrySet()) {
            String key = entry.getKey();
            String  value = entry.getValue();
            try {
                surveyContent.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            survey.put(testType, surveyContent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String requestBodyParameterName = "survey" + testType + "JsonObject";
        RequestBody sendSurveyToServerRequestBody = new FormBody.Builder()
                .add(requestBodyParameterName, survey.toString())
                .build();

        Log.d("MSGHANDLER_sendSurvey", survey.toString());

            new RequestHandler(url, sendSurveyToServerRequestBody, activity).sendRequest();
    }

    public static void sendIntroductionFileToServer(String url,Activity activity, String filePath, File videoFile) {
        String requestBodyParameterName = "survey" + "INTRODUCTION" + "JsonObject";
        MultipartBody videoFileRequestBody = new MultipartBody.Builder()
                .addFormDataPart(requestBodyParameterName, filePath, RequestBody.create(MediaType.parse("video/mp4"), videoFile))
                .build();


        new FileRequestHandler(url, videoFileRequestBody, activity).run();
//        try {
//            new RequestHandler(mUrl, videoFileRequestBody, activity).execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public static void sendIntroductionTextToServer(String url, String introductionText, Activity activity) {
        String requestBodyParameterName = "survey" + "INTRODUCTION" + "JsonObject";
        JSONObject introductionTextJsonObject = JsonHandler.introductionTextJsonObject(introductionText);
        RequestBody introductionTextRequestBody = new FormBody.Builder()
                .add(requestBodyParameterName, String.valueOf(introductionTextJsonObject))
                .build();

        new RequestHandler(url, introductionTextRequestBody, activity).sendRequest();
    }


    public static void sendIntroductionYoutubeLinkToServer(String url, String youtubeLink, Activity activity) {
        String requestBodyParameterName = "survey" + "INTRODUCTION" + "JsonObject";
        JSONObject introductionTextJsonObject = JsonHandler.introductionVideoLinkJsonObject(youtubeLink);
        RequestBody introductionTextRequestBody = new FormBody.Builder()
                .add(requestBodyParameterName, String.valueOf(introductionTextJsonObject))
                .build();

        new RequestHandler(url, introductionTextRequestBody, activity).sendRequest();
    }
}
