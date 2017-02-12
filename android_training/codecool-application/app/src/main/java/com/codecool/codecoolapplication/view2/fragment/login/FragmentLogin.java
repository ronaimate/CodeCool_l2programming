package com.codecool.codecoolapplication.view2.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.network.RequestHandler;
import com.codecool.codecoolapplication.service.PingerService;
import com.codecool.codecoolapplication.view2.activity.MainActivity;

/**
 * Created by andra on 2016-07-06.
 */
public class FragmentLogin extends Fragment{

    public interface LoginAnimation{
        void animateLoginScreen();
    }


    // TAG
    private static final String TAG = FragmentLogin.class.getName();

    // Constants
    private static final String LOGINURL = "http://codecool-application.appspot.com/testlogin";
    public static final String WRONG_EMAIL_ERR_MSG = "Wrong email address!";
    public static final String WRONG_PASS_ERR_MSG = "Wrong password!";
    public static final String NETWORK_IS_NOT_AVAILABLE_ERR_MSG = "Network is not available";

    // Layouts
//    private RelativeLayout mLogoLayout;
    private LinearLayout mInputFieldsLayout;
    private LinearLayout mButtonsLayout;

    // Views
    private AutoCompleteTextView mEditTextEmail;
    private EditText mEditTextPass;
    private Button mBtnLogin;
    private TextView mBtnOpenForgotPassUI;
    private TextInputLayout mEmailWrapper;
    private TextInputLayout mPassWrapper;

    // Others
    private String mEmail;
    private String mPass;

    public LoginAnimation loginAnimation;
    private boolean isAnimated =false;
    private static final String[] EMAILS = new String[] {
            "testuser@nomail.com","testuser1@nomail.com","testuser2@nomail.com","testuser3@nomail.com","testuser4@nomail.com"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Getting reference to all views
        findAllViewsById(view);

        loginAnimation = (LoginAnimation) getActivity();

        if(!isAnimated) {
            initializePositions();
            animateElements();
            isAnimated = true;
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, EMAILS);
        mEditTextEmail.setAdapter(adapter);
        mEditTextEmail.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditTextPass.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Setting up an onClickListener for the Login button
        View.OnClickListener loginBtnOnClickListener = getLoginButtonOnclickListener();
        mBtnLogin.setOnClickListener(loginBtnOnClickListener);

        // Setting up an onClickListener for the Forgot Password button (which is a TextView though)
        View.OnClickListener forgotPassBtnOnClickListener = getForgotPassButtonOnClickListener();
        mBtnOpenForgotPassUI.setOnClickListener(forgotPassBtnOnClickListener);

        return view;
    }

    private void animateElements() {
        loginAnimation.animateLoginScreen();

        mButtonsLayout.animate().translationYBy(200f).setDuration(800);
        mInputFieldsLayout.animate().alpha(1f).setDuration(800);
    }

    private void initializePositions() {

        mButtonsLayout.setY(-200f);
        mInputFieldsLayout.setAlpha(0f);
    }


    private View.OnClickListener getForgotPassButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .addToBackStack(null)
                        .replace(R.id.login_placeholder, new FragmentForgottenPassword())
                        .commit();
            }
        };
    }

    @NonNull
    private View.OnClickListener getLoginButtonOnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RequestHandler.isNetworkAvailable(getActivity())) {
                    mEmail = mEditTextEmail.getText() + "";
                    mPass = mEditTextPass.getText() + "";

//                    (getActivity()).runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                                Toast.makeText(getActivity(), "Toast", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                    String sessionId = MessageHandler.loginUser("http://codecool-application.appspot.com/app2", mEmail, mPass, getActivity());
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("userSessionId", sessionId);

                    Log.d("sessionIdresp", String.valueOf(sessionId.length()));

                    if (sessionId.equals("-1")){
//                        mEmailWrapper.setError(WRONG_EMAIL_ERR_MSG);
//                        mPassWrapper.setError(WRONG_PASS_ERR_MSG);
                        Toast.makeText(getActivity(), "Wrong email or password", Toast.LENGTH_LONG).show();
                    } else {
                        mEmailWrapper.setErrorEnabled(false);
                        mPassWrapper.setErrorEnabled(false);
                        getActivity().startService(new Intent(getActivity(), PingerService.class));
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getActivity(), NETWORK_IS_NOT_AVAILABLE_ERR_MSG, Toast.LENGTH_LONG).show();
                }

            }
        };
    }

    private void findAllViewsById(View view) {
        // Layouts
//        mLogoLayout = (RelativeLayout) view.findViewById(R.id.logoLayout);
        mInputFieldsLayout = (LinearLayout) view.findViewById(R.id.inputFieldsLayout);
        mButtonsLayout = (LinearLayout) view.findViewById(R.id.buttonsLayout);

        // Other Elements
        mEditTextEmail = (AutoCompleteTextView) view.findViewById(R.id.editTextEmail);
        mEditTextPass = (EditText) view.findViewById(R.id.editTextPass);
        mBtnLogin = (Button) view.findViewById(R.id.btnLogin);
        mBtnOpenForgotPassUI = (TextView) view.findViewById(R.id.btnOpenForgotPassUI);
        mEmailWrapper = (TextInputLayout) view.findViewById(R.id.emailWrapper);
        mPassWrapper = (TextInputLayout) view.findViewById(R.id.passWrapper);
    }
}
