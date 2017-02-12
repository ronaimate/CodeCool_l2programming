package com.codecool.codecoolapplication.view2.fragment.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.codecoolapplication.R;
import com.codecool.codecoolapplication.network.MessageHandler;
import com.codecool.codecoolapplication.view2.activity.MainActivity;
import com.codecool.codecoolapplication.view2.activity.TextualIntroduction;
import com.codecool.codecoolapplication.camera.CameraClass;

import java.io.File;

public class FragmentIntroductionCover extends Fragment {

    public static final String CTA_BTN_TXT_NOTHING_IS_SELECTED = "Choose an option first!";
    public static final String CTA_BTN_TXT_YOUTUBE_IS_SELECTED = "ADD YOUTUBE URL";
    public static final String CTA_BTN_TXT_CAMCORDER_IS_SELECTED = "LAUNCH CAMCORDER NOW";
    public static final String CTA_BTN_TXT_LOCALVIDEOFILE_IS_SELECTED = "SELECT A LOCAL VIDEO FILE";
    public static final String CTA_BTN_TXT_INTROTEXT_IS_SELECTED = "LOAD INTRODUCTION TEXTBOX";
    public static final String NOTHING_IS_SELECTED_TOAST_MSG = "You must select an option from the list above!";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduction_cover, container, false);
        getActivity().setTitle("Introduction");

        final TextView ctaButton = (TextView) view.findViewById(R.id.fragment_introduction_action_button);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.introduction_radiogroup);
        final RadioButton radiobutton_youtubeurl = (RadioButton) view.findViewById(R.id.radiobutton_youtubeurl);
        final RadioButton radiobutton_rec_video = (RadioButton) view.findViewById(R.id.radiobutton_rec_video);
        final RadioButton radiobutton_select_local_file = (RadioButton) view.findViewById(R.id.radiobutton_select_local_file);
        final RadioButton radiobutton_textual_introduction = (RadioButton) view.findViewById(R.id.radiobutton_textual_introduction);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton_youtubeurl:
                        ctaButton.setText(CTA_BTN_TXT_YOUTUBE_IS_SELECTED);
                        break;
                    case R.id.radiobutton_rec_video:
                        ctaButton.setText(CTA_BTN_TXT_CAMCORDER_IS_SELECTED);
                        break;
                    case R.id.radiobutton_select_local_file:
                        ctaButton.setText("SELECT A FILE FROM PHONE");
                        break;
                    case R.id.radiobutton_textual_introduction:
                        ctaButton.setText(CTA_BTN_TXT_INTROTEXT_IS_SELECTED);
                        break;
                }

            }
        });

        ctaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_youtubeurl.isChecked()) {
                    sendYoutubeUrl();
                } else if (radiobutton_rec_video.isChecked()) {
                    startActivity(new Intent(getActivity(), CameraClass.class));
                    popupToastMsg(getActivity(), "Video Recording Selected");

                } else if (radiobutton_select_local_file.isChecked()) {
//                    popupToastMsg(getActivity(), "Local File Selected");
                    selectLocalFile();
                } else if (radiobutton_textual_introduction.isChecked()) {
                    launchTextualIntroduction();

                } else {
                    popupToastMsg(getActivity(), NOTHING_IS_SELECTED_TOAST_MSG);
                }
            }
        });
        
        return view;
    }



    private static void popupToastMsg(Context context, String msgToShow) {
        Toast.makeText(context, msgToShow, Toast.LENGTH_SHORT).show();
    }

    private void sendYoutubeUrl(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Upload your video");

        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setMessage("Paste your video's URL here: ");
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = input.getText().toString();
                MessageHandler.sendIntroductionYoutubeLinkToServer("http://codecool-application.appspot.com/app2", url, getActivity());
                int applicationPosition = ((MainActivity)getActivity()).getApplicationPosition(true);
                ((MainActivity)getActivity()).setFragmentToViewGroup(applicationPosition);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    private void selectLocalFile(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 0);
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }



    // FOR VIDEO SELECTION FROM LOCAL DEVICE
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final String url = "https://codecool-application.appspot.com/app2";
        if (requestCode == 0  && resultCode == Activity.RESULT_OK) {
            final String filePath = getRealPathFromURI(getContext(), data.getData());
            Log.d("selectedFile", filePath);
            final File videoFile = new File(filePath);


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("File upload title.");
            builder.setMessage("Are you sure want upload this video?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MessageHandler.sendIntroductionFileToServer(url, getActivity(), filePath, videoFile);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  //Cancel button
                }
            });
            builder.show();
        }
    }

    private void launchTextualIntroduction() {
        Intent intent = new Intent(getActivity(), TextualIntroduction.class);
        startActivity(intent);
    }


}
