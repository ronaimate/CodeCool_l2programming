package com.codecool.codecoolapplication.network;

/**
 * Created by ronai on 15/06/16.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestHandler{
    private static final String LOG_TAG = "OkHttp";
    private String mUrl;
    private RequestBody mRequestBody;
    private ProgressDialog mDialog;
    private Activity mActivity;
    private String mMessage = null;
    private OkHttpClient client = new OkHttpClient();
    private Request request;

    public RequestHandler(String url, RequestBody requestBody, Activity activity) {
        this.mUrl = url;
        this.mRequestBody = requestBody;
        this.mActivity = activity;
    }

    private void initializeDialog()
    {
        mDialog = new ProgressDialog(mActivity);
        mDialog.setMessage("Please wait!");
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);
    }

    private void initializeRequest()
    {
        request = new Request.Builder()
                .url(mUrl)
                .post(mRequestBody)
                .build();
    }

    public String sendRequest()
    {
        initializeDialog();
        initializeRequest();

        mDialog.show();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMessage = e.toString();
                Log.e(LOG_TAG, mMessage);
                (mActivity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMessage = response.body().string();
                response.body().close();
                Log.i(LOG_TAG, mMessage);
                (mActivity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    }
                });
            }

        });
        while (mMessage == null)
        {
            if(mMessage != null)
            {
                break;
            }
        }
        return mMessage;
    }


//        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                .build();
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectionSpecs(Collections.singletonList(spec))
//                .build();


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }
}
