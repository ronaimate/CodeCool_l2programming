package com.codecool.codecoolapplication.view2.fragment.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.view2.activity.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andra on 2016-07-06.
 */
public class FragmentForgottenPassword extends Fragment {

    Button forgotMyPasswordButton;
    EditText emailEditText;
    private TextInputLayout mEmailWrapper;
    View view;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgotten_password, container, false);
        initializeVariables();
        buttonHandling();
        return view;
    }

    private void buttonHandling()
    {
        forgotMyPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout til = (TextInputLayout) view.findViewById(R.id.emailWrapper);
                String text = til.getEditText().getText().toString();
                if(validate(text)) {
                    forgotMyPassword();
                    getActivity().onBackPressed();
                }else{
                    mEmailWrapper.setError("Please enter a valid email address");
                }
            }
        });
    }

    private void forgotMyPassword()
    {
        MessageHandler.forgottenPassword("http://codecool-application.appspot.com/app2", String.valueOf(emailEditText.getText()), getActivity());
    }

    private void initializeVariables()
    {
        forgotMyPasswordButton = (Button) view.findViewById(R.id.btnForgotMyPassword);
        emailEditText = (EditText) view.findViewById(R.id.editTextEmailForgottenPass);
        mEmailWrapper = (TextInputLayout) view.findViewById(R.id.emailWrapper);
    }
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


}
