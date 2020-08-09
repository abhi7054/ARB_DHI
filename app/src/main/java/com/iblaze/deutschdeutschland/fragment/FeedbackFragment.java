package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.HomeActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.model.FeedbackModel;
import com.iblaze.deutschdeutschland.model.ParamFeedbackFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ADNROID1 on 21-09-2017.
 */

public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.imgMenuTopbar)
    ImageView imgMenuTopbar;
    @BindView(R.id.txtTitleTopBar)
    TextView txtTitleTopBar;
    @BindView(R.id.imgDictionaryTopbar)
    ImageView imgDictionaryTopbar;
    @BindView(R.id.viewBottomLineTopbar)
    View viewBottomLineTopbar;
    @BindView(R.id.txtPoints)
    TextView txtPoints;
    @BindView(R.id.txtSpeed)
    TextView txtSpeed;
    @BindView(R.id.txtWrongClick)
    TextView txtWrongClick;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    Unbinder unbinder;
    private HomeActivity homeActivity;
    private ParamFeedbackFragment paramFeedback;

    private List<FeedbackModel> listFeedbackPoints;
    private List<FeedbackModel> listFeedbackSpeed;
    private List<FeedbackModel> listFeedbackWrongClick;

    private int feedbackPoints, feedbackSpeed, feedbackWrongClick;

//    private String feedBackPointText, feedbackSpeedText, feedbackWrongClickText;

   /* private HashMap<String, Integer> hmFeedbackPoints;
    private HashMap<String, Integer> hmFeedbackSpeed;
    private HashMap<String, Integer> hmFeedbackWrongClick;*/

    public static FeedbackFragment newInstance(ParamFeedbackFragment paramFeedback) {

        FeedbackFragment fragment = new FeedbackFragment();
        fragment.setParamFeedback(paramFeedback);
        return fragment;
    }

    public void setParamFeedback(ParamFeedbackFragment paramFeedback) {
        this.paramFeedback = paramFeedback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        topBarSetup();
        initializeVariableDefaultValue();

        setFeedbackValue();
    }

    private void setFeedbackValue() {

        feedbackPoints = paramFeedback.getFeedbackPoints();
        Log.e("feedbackPoints", feedbackPoints + "...");
        feedbackSpeed = paramFeedback.getFeedbackSpeed();
        Log.e("feedbackSpeed", feedbackSpeed + "...");
        feedbackWrongClick = paramFeedback.getFeedbackWrongClick();
        Log.e("feedbackWrongClick", feedbackWrongClick + "...");

       /* Log.e("PointsText",getPointsTextFromFile(feedbackPoints)+"...");
        Log.e("SpeedText",getSpeedTextFromFile(feedbackSpeed)+"...");
        Log.e("WrongClickText",getWrongClickTextFromFile(feedbackWrongClick)+"...");
*/
        txtPoints.setText(getPointsTextFromFile(feedbackPoints)+"");
        txtSpeed.setText(getSpeedTextFromFile(feedbackSpeed)+"");
        String wrongClickText=getWrongClickTextFromFile(feedbackWrongClick)+"";
        txtWrongClick.setText(wrongClickText.replace("[x]",feedbackWrongClick+""));

    }

    private String getWrongClickTextFromFile(int feedbackSpeed) {
        String feedbackStr = null;

        int point = 0;
        boolean isFirstMatch = false;

        List<String> tempListFeedbackWrongClick = new ArrayList<>();

        for (int i = 0; i < listFeedbackWrongClick.size(); i++) {
            FeedbackModel feedbackModel = listFeedbackWrongClick.get(i);

            String operator = feedbackModel.getLevelSymbol();
            if (operator.equals("=")) {
                if (feedbackSpeed == feedbackModel.getNumber()) {
                    feedbackStr = feedbackModel.getFeedbackText();
                    break;
                }
            }
            if (operator.equals(">")) {

                if (feedbackSpeed > feedbackModel.getNumber()) {

                    if (isFirstMatch) {
                        if (point == feedbackModel.getNumber()) {
                            tempListFeedbackWrongClick.add(feedbackModel.getFeedbackText());
                        }
                    } else {
                        tempListFeedbackWrongClick.add(feedbackModel.getFeedbackText());
                        point = feedbackModel.getNumber();
                        isFirstMatch = true;

                    }


                }
            }
        }
//        Log.e("SizeWrongClickTempList",tempListFeedbackWrongClick.size()+"...");
        if (tempListFeedbackWrongClick.size() > 0) {
            feedbackStr = tempListFeedbackWrongClick.get(getRandomNumber(0, tempListFeedbackWrongClick.size()));
        }

        return feedbackStr;
    }

    private String getSpeedTextFromFile(int feedbackSpeed) {
        String feedbackStr = null;

        int point = 0;
        boolean isFirstMatch = false;

        List<String> tempListFeedbackSpeed = new ArrayList<>();

        for (int i = 0; i < listFeedbackSpeed.size(); i++) {
            FeedbackModel feedbackModel = listFeedbackSpeed.get(i);

            String operator = feedbackModel.getLevelSymbol();
           /* if (operator.equals("<=")) {
                if (feedbackSpeed <= feedbackModel.getNumber()) {
                    feedbackStr = feedbackModel.getFeedbackText();
                    break;
                }
            }*/

            if (operator.equals("<")) {

                if (feedbackSpeed < feedbackModel.getNumber()) {

                    if (isFirstMatch) {
                        if (point == feedbackModel.getNumber()) {
                            tempListFeedbackSpeed.add(feedbackModel.getFeedbackText());
                        }
                    } else {
                        tempListFeedbackSpeed.add(feedbackModel.getFeedbackText());
                        point = feedbackModel.getNumber();
                        isFirstMatch = true;
                    }


                }
            }
          /*  if (operator.equals(">")) {
                if (feedbackSpeed > feedbackModel.getNumber()) {
                    feedbackStr = feedbackModel.getFeedbackText();
                    break;
                }
            }*/
        }

//        Log.e("SizeSpeedTempList",tempListFeedbackSpeed.size()+"...");
        if (tempListFeedbackSpeed.size() > 0) {
            feedbackStr = tempListFeedbackSpeed.get(getRandomNumber(0, tempListFeedbackSpeed.size()));
        }

        return feedbackStr;
    }


    private String getPointsTextFromFile(int feedbackPoints) {
        String feedbackStr = null;

        for (int i = 0; i < listFeedbackPoints.size(); i++) {
            FeedbackModel feedbackModel = listFeedbackPoints.get(i);

            if (feedbackPoints < feedbackModel.getNumber()) {
                feedbackStr = feedbackModel.getFeedbackText();

                break;
            }
        }
        return feedbackStr;
    }

    private void initializeVariableDefaultValue() {

        listFeedbackPoints = new ArrayList<>();
        listFeedbackSpeed = new ArrayList<>();
        listFeedbackWrongClick = new ArrayList<>();

        /*listFeedbackSpeed = readFeedBackFile("feedback_speed.txt");
        Log.e("readFile","listFeedbackSpeed");
*/
        listFeedbackPoints = readFeedBackFile("feedback_points.txt");
        Log.e("readFile","listFeedbackPoints");
        listFeedbackSpeed = readFeedBackFile("feedback_speed.txt");
        Log.e("readFile","listFeedbackSpeed");
        listFeedbackWrongClick = readFeedBackFile("feedback_wrongClicks.txt");
        Log.e("readFile","listFeedbackWrongClick");

       /* Log.e("size", listFeedbackPoints.size() + "...");
        Log.e("size", listFeedbackSpeed.size() + "...");
        Log.e("size", listFeedbackWrongClick.size() + "...");*/

       /* hmFeedbackPoints = new HashMap<>(); z
        hmFeedbackSpeed = new HashMap<>();
        hmFeedbackWrongClick = new HashMap<>();

        hmFeedbackPoints = readFeedBackFile2("feedback_points.txt");
        hmFeedbackSpeed = readFeedBackFile2("feedback_speed.txt");
        hmFeedbackWrongClick = readFeedBackFile2("feedback_wrongClicks.txt");

        Log.e("size",hmFeedbackPoints.size()+"...");
        Log.e("size",hmFeedbackSpeed.size()+"...");
        Log.e("size",hmFeedbackWrongClick.size()+"...");*/

    }

    private void topBarSetup() {
        imgDictionaryTopbar.setImageResource(R.drawable.ic_grammer);
        txtTitleTopBar.setText(paramFeedback.getTitleTopbar());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            homeActivity = (HomeActivity) context;
        }
    }

    @OnClick({R.id.imgMenuTopbar, R.id.imgDictionaryTopbar, R.id.imgNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgMenuTopbar:
                homeActivity.openHomeFragment(FragmentState.REPLACE);
                break;
            case R.id.imgDictionaryTopbar:
                homeActivity.openGrammerFragment(FragmentState.REPLACE);
                break;
            case R.id.imgNext:
                homeActivity.onBackPressed();
                break;
        }
    }

    private HashMap<String, Integer> readFeedBackFile2(String fileName) {

        HashMap<String, Integer> hmFeedback = new HashMap<>();

        try {
            InputStream file = getResources().getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
//                Log.e("Line",line);

                FeedbackModel feedbackModel = new FeedbackModel();
                String[] arrFeedback = line.split(";");
                String[] arrSymbolNo = arrFeedback[0].split("\\s+");

                hmFeedback.put(line, Integer.parseInt(arrSymbolNo[1]));

            }
            reader.close();

        } catch (IOException ioe) {

        }

        return hmFeedback;
    }


    private List<FeedbackModel> readFeedBackFile(String fileName) {

        List<FeedbackModel> listFeedBack = new ArrayList<>();

        try {
            InputStream file = getResources().getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
//                Log.e("Line",line);

                FeedbackModel feedbackModel = new FeedbackModel();
                String[] arrFeedback = line.split(";");
                Log.e("str1",arrFeedback[0]);
                Log.e("str2",arrFeedback[1]);
                String[] arrSymbolNo = arrFeedback[0].split("\\s+");

                Log.e("split","symbol:"+arrSymbolNo[0]+" No:"+arrSymbolNo[1]);

                feedbackModel.setLevelSymbol(arrSymbolNo[0]);
                feedbackModel.setNumber(Integer.parseInt(arrSymbolNo[1]));

                feedbackModel.setFeedbackText(arrFeedback[1].replace("\"¦\"", "\n"));

                listFeedBack.add(feedbackModel);
            }
            reader.close();

        } catch (IOException ioe) {

        }

        return listFeedBack;
    }
}
