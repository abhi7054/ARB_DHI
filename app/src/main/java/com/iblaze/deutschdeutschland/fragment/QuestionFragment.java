package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.HomeActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.model.OptionsForAnswer;
import com.iblaze.deutschdeutschland.model.ParamFeedbackFragment;
import com.iblaze.deutschdeutschland.model.ParamQuestionFragment;
import com.iblaze.deutschdeutschland.model.ParamResultFragment;
import com.iblaze.deutschdeutschland.model.QuestionModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ADNROID1 on 20-09-2017.
 */

public class QuestionFragment extends BaseFragment {

    @BindView(R.id.imgMenuTopbar)
    ImageView imgMenuTopbar;
    @BindView(R.id.txtTitleTopBar)
    TextView txtTitleTopBar;
    @BindView(R.id.imgDictionaryTopbar)
    ImageView imgDictionaryTopbar;
    @BindView(R.id.txtQuestion)
    TextView txtQuestion;
    @BindView(R.id.btnOption1)
    Button btnOption1;
    @BindView(R.id.btnOption2)
    Button btnOption2;
    @BindView(R.id.btnOption3)
    Button btnOption3;
    Unbinder unbinder;
    @BindView(R.id.viewBottomLineTopbar)
    View viewBottomLineTopbar;
    @BindView(R.id.txtlblFrage)
    TextView txtlblFrage;
    @BindView(R.id.txtFrageNo)
    TextView txtFrageNo;
    @BindView(R.id.txtTimer)
    TextView txtTimer;
    @BindView(R.id.btnStop)
    Button btnStop;

    private HomeActivity homeActivity;
    private ParamQuestionFragment quizParam;
    private boolean isTimer = false;
    private int category, level;
    private List<QuestionModel> listQuestions;
    private String answer;
    private int questionCounter;
    private int rightClickCountter;
    private int wrongClickCounter;
    private int timeCounterInMinutes;
    private CountDownTimer countDownTimer;
    private CountDownTimer infiniteTimer;
    private int frazeCounter;
    private int timeCounter;
//    private int feedbackPoint, feedbackSpeed, feedbackWrongClick;

    private boolean isOption1, isOption2, isOption3;


    public static QuestionFragment newInstance(ParamQuestionFragment paramFragmentQuestion) {

        QuestionFragment fragment = new QuestionFragment();
        fragment.setQuizParam(paramFragmentQuestion);
        return fragment;
    }

    public void setQuizParam(ParamQuestionFragment quizParam) {
        this.quizParam = quizParam;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.e("onStop", "");
        if (isTimer) {
            stopInfiniteTimer();
        } else {
            stopCountDownTimer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "");
        if (isTimer) {
            stopInfiniteTimer();
        } else {
            stopCountDownTimer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isTimer) {
            stopInfiniteTimer();
        } else {
            stopCountDownTimer();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeVariablesDefaultValues();

        //        new CountDownTimer(30000, 1000) {//30 second
        countDownTimer = new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeCounter++;

                txtTimer.setText(String.format("%02d", timeCounter / 60) + ":" + String.format("%02d", timeCounter % 60));
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (!isTimer) {
                    ParamFeedbackFragment paramFeedbackFragment = getParamFeedbackFragment();
                    homeActivity.openFeedBackFragment(FragmentState.REPLACE, paramFeedbackFragment);
                } else {
                    this.start();
                }
            }
        };

        infiniteTimer = new CountDownTimer(900000000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeCounter++;

                txtTimer.setText(String.format("%02d", timeCounter / 60) + ":" + String.format("%02d", timeCounter % 60));
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (!isTimer) {
                    ParamFeedbackFragment paramFeedbackFragment = getParamFeedbackFragment();
                    homeActivity.openFeedBackFragment(FragmentState.REPLACE, paramFeedbackFragment);
                } else {
                    this.start();
                }
            }
        };

        initializeQuiz();
    }

    private void initializeVariablesDefaultValues() {
        category = 1;
        level = 3;
        questionCounter = 0;
        rightClickCountter = 0;
        wrongClickCounter = 0;
        timeCounter = 0;
        timeCounterInMinutes = 0;
     /*   feedbackPoint = 0;
        feedbackWrongClick = 0;
        feedbackSpeed = 0;*/
        frazeCounter = 0;

        isOption1 = true;
        isOption2 = true;
        isOption3 = true;
    }

    private void initializeQuiz() {
        isTimer = quizParam.isTimer();
        category = quizParam.getCategoty();
        level = quizParam.getLevel();

        Log.e("Level", level + "...");
        Log.e("Category", category + "...");

        if (isTimer) {
            txtlblFrage.setVisibility(View.VISIBLE);
            txtFrageNo.setVisibility(View.VISIBLE);
            txtTimer.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.VISIBLE);
        }

        switch (category) {
            case 1:
                txtTitleTopBar.setText(R.string.was_waren_die_so);
                switch (level) {
                    case 1:
                        String[] arrFile = {"Q1_1.txt"};
                        readQuestionAnswerFile(arrFile);
                        break;
                    case 2:
                        String[] arrFile2 = {"Q1_2.txt"};
                        readQuestionAnswerFile(arrFile2);
                        break;
                    case 3:
                        String[] arrFile3 = {"Q1_1.txt", "Q1_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 2:
                txtTitleTopBar.setText(R.string.was_stecket_da_drin);
                switch (level) {
                    case 1:
                        String[] arrFile = {"Q2_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"Q2_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"Q2_1.txt", "Q2_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 3:
                txtTitleTopBar.setText(R.string.woher_denn);
                switch (level) {
                    case 1:
                        String[] arrFile = {"Q3_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"Q3_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"Q3_1.txt", "Q3_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 4:
                txtTitleTopBar.setText(R.string.wenn_namen_sprechen);
                switch (level) {
                    case 1:
                        String[] arrFile = {"Q4_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"Q4_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"Q4_1.txt","Q4_2.txt"};
                        readQuestionAnswerFile(arrFile3);

                        break;
                }
                break;
            case 5:
                txtTitleTopBar.setText(R.string.leicht_ratselhaft);
                switch (level) {
                    case 1:
                        String[] arrFile1 = {"Q5_1.txt"};
                        readQuestionAnswerFile(arrFile1);

                        break;
                    case 2:
                        String[] arrFile2 = {"Q5_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"Q5_1.txt", "Q5_2.txt"};
                        readQuestionAnswerFile(arrFile3);

                        break;
                }
                break;
        }

    }

   /* private void initializeQuiz() {
        isTimer = quizParam.isTimer();
        category = quizParam.getCategoty();
        level = quizParam.getLevel();

        Log.e("Level", level + "...");
        Log.e("Category", category + "...");

        if (isTimer) {
            txtlblFrage.setVisibility(View.VISIBLE);
            txtFrageNo.setVisibility(View.VISIBLE);
            txtTimer.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.VISIBLE);
        }

        switch (category) {
            case 1:
                txtTitleTopBar.setText(R.string.das_verb);
                switch (level) {
                    case 1:
                        String[] arrFile = {"W_3_1.txt"};
                        readQuestionAnswerFile(arrFile);
                        break;
                    case 2:
                        String[] arrFile2 = {"W_3_2.txt"};
                        readQuestionAnswerFile(arrFile2);
                        break;
                    case 3:
                        String[] arrFile3 = {"W_3_1.txt", "W_3_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 2:
                txtTitleTopBar.setText(R.string.das_nomen);
                switch (level) {
                    case 1:
                        String[] arrFile = {"H_3_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"H_3_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"H_3_1.txt", "H_3_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 3:
                txtTitleTopBar.setText(R.string.prapositionen);
                switch (level) {
                    case 1:
                        String[] arrFile = {"N_3_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"N_3_2.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"N_3_1.txt", "N_3_2.txt"};
                        readQuestionAnswerFile(arrFile3);
                        break;
                }
                break;
            case 4:
                txtTitleTopBar.setText(R.string.der_satz);
                switch (level) {
                    case 1:
                        String[] arrFile = {"K_3_1.txt"};
                        readQuestionAnswerFile(arrFile);

                        break;
                    case 2:
                        String[] arrFile2 = {"K_3_2.txt", "K_3_3.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"K_3_1.txt", "K_3_2.txt", "K_3_3.txt"};
                        readQuestionAnswerFile(arrFile3);

                        break;
                }
                break;
            case 5:
                txtTitleTopBar.setText(R.string.bunt_gemischt);
                switch (level) {
                    case 1:
                        String[] arrFile1 = {"W_3_1.txt", "H_3_1.txt", "K_3_1.txt", "N_3_1.txt"};
                        readQuestionAnswerFile(arrFile1);

                        break;
                    case 2:
                        String[] arrFile2 = {"H_3_2.txt", "N_3_2.txt", "W_3_2.txt", "K_3_2.txt", "K_3_3.txt"};
                        readQuestionAnswerFile(arrFile2);

                        break;
                    case 3:
                        String[] arrFile3 = {"H_3_1.txt", "H_3_2.txt", "N_3_1.txt", "N_3_2.txt", "W_3_1.txt", "W_3_2.txt", "K_3_1.txt", "K_3_2.txt", "K_3_3.txt"};
                        readQuestionAnswerFile(arrFile3);

                        break;
                }
                break;
        }

    }*/

    private void readQuestionAnswerFile(String[] arrFiles) {

        String[] arrFileName = arrFiles;
        listQuestions = new ArrayList<>();

        for (int i = 0; i < arrFileName.length; i++) {
            String fileName = arrFileName[i];

            try {
                InputStream file = getResources().getAssets().open(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
//                Log.e("Line",line);
                    QuestionModel questionModel = new QuestionModel();
                    String[] arrQue = line.split(";");
                    questionModel.setQueNo(arrQue[0]);
                    questionModel.setAnswer(arrQue[1]);
//                    questionModel.setQuestion(arrQue[2].replace("\"|\"", "\\n"));

                    questionModel.setQuestion(arrQue[2].replace("\"|\"", "\n"));

                    OptionsForAnswer optionsForAnswer = new OptionsForAnswer();
                    String[] arrOptions = arrQue[3].split("/");
                    optionsForAnswer.setOption1(arrOptions[0]);
                    optionsForAnswer.setOption2(arrOptions[1]);
                    optionsForAnswer.setOption3(arrOptions[2]);

                    questionModel.setOptionsForAnswer(optionsForAnswer);

                    listQuestions.add(questionModel);
                }
                reader.close();

            } catch (IOException ioe) {

            }
        }

        if (isTimer) {
            startInfiniteTimer();
        } else {
            startCountDownTimer();
        }
        displayQuestion();

    }

    private void displayQuestion() {

        frazeCounter++;

        txtFrageNo.setText(frazeCounter + "");

        int minNo = 1;
        int maxNo = listQuestions.size();
        int randomNo = getRandomNumber(minNo, maxNo);

        QuestionModel questionModel = listQuestions.get(randomNo);
        txtQuestion.setText(questionModel.getQuestion());
        answer = questionModel.getAnswer().trim();

        OptionsForAnswer options = questionModel.getOptionsForAnswer();
        btnOption1.setText(options.getOption1());
        btnOption2.setText(options.getOption2());
        btnOption3.setText(options.getOption3());

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

    @OnClick({R.id.imgMenuTopbar, R.id.imgDictionaryTopbar, R.id.btnOption1, R.id.btnOption2, R.id.btnOption3, R.id.btnStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgMenuTopbar:
                homeActivity.onBackPressed();
                if (isTimer) {
                    stopInfiniteTimer();
                } else {
                    stopCountDownTimer();
                }
                break;
            case R.id.imgDictionaryTopbar:
                if (isTimer) {
                    stopInfiniteTimer();
                } else {
                    stopCountDownTimer();
                }
                break;
            case R.id.btnOption1:
                if (!isTimer) {
                    stopCountDownTimer();
                    startCountDownTimer();
                }
                if (answer.equals(btnOption1.getText().toString().trim())) {
                    rightClick();
                    Log.e("right", rightClickCountter + "...");

                } else {
                    btnOption1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    if (isOption1) {
                        wrongClickCounter++;
                        if (!isTimer) {
                            isOption1 = false;
                        }
                        Log.e("wrong", wrongClickCounter + "...");

                    }

//                    wrongClick();
                }
                break;
            case R.id.btnOption2:
                if (!isTimer) {
                    stopCountDownTimer();
                    startCountDownTimer();
                }
                if (answer.equals(btnOption2.getText().toString().trim())) {
                    rightClick();
                    Log.e("right", rightClickCountter + "...");
                } else {
                    btnOption2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    if (isOption2) {
                        wrongClickCounter++;
                        if (!isTimer) {
                            isOption2 = false;
                        }
                        Log.e("wrong", wrongClickCounter + "...");

                    }
//                    wrongClick();
                }
                break;
            case R.id.btnOption3:
                if (!isTimer) {
                    stopCountDownTimer();
                    startCountDownTimer();
                }
                if (answer.equals(btnOption3.getText().toString().trim())) {
                    rightClick();
                    Log.e("right", rightClickCountter + "...");
                } else {
                    btnOption3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    if (isOption3) {
                        wrongClickCounter++;
                        if (!isTimer) {
                            isOption3 = false;
                        }
                        Log.e("wrong", wrongClickCounter + "...");
                    }
//                    wrongClick();
                }
                break;
            case R.id.btnStop:
                stopInfiniteTimer();

                ParamResultFragment paramResultFragment = getParamResultFragment();

                homeActivity.openResultFragment(FragmentState.REPLACE, paramResultFragment);
                break;
        }
    }

    private void rightClick() {

        isOption1 = true;
        isOption2 = true;
        isOption3 = true;

        rightClickCountter++;
        questionCounter++;

        if (!isTimer) {
            if (questionCounter < 25) {

                btnOption1.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                btnOption2.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                btnOption3.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                displayQuestion();

            } else {
                stopCountDownTimer();

                ParamFeedbackFragment paramFeedbackFragment = getParamFeedbackFragment();

                homeActivity.openFeedBackFragment(FragmentState.REPLACE, paramFeedbackFragment);
            }
        } else {
            btnOption1.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            btnOption2.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            btnOption3.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            displayQuestion();
        }
    }

    private ParamResultFragment getParamResultFragment() {

        ParamResultFragment paramResultFragment = new ParamResultFragment();
        paramResultFragment.setFeedbackSpeed(getMinutesPlayed());
        paramResultFragment.setRightClick(rightClickCountter);
        paramResultFragment.setWrongClick(wrongClickCounter);
        paramResultFragment.setTitleTopbar(txtTitleTopBar.getText().toString());
        return paramResultFragment;
    }

    private int getMinutesPlayed() {
        int minutes = timeCounter / 60;
        int seconds = timeCounter % 60;

        if (seconds > 0) {
            minutes++;
        }

        return minutes;
    }

    private ParamFeedbackFragment getParamFeedbackFragment() {

//        calculatePoints();

        Log.e("Speed", (int)calculateSpeed()+ "...");
        Log.e("rightClickCountter", rightClickCountter + "...");
        Log.e("wrongClickCounter", wrongClickCounter + "...");

        ParamFeedbackFragment paramFeedbackFragment = new ParamFeedbackFragment();
        paramFeedbackFragment.setFeedbackPoints((int) calculatePoints());
        paramFeedbackFragment.setFeedbackSpeed((int)calculateSpeed());
        paramFeedbackFragment.setFeedbackWrongClick(wrongClickCounter);
        paramFeedbackFragment.setTitleTopbar(txtTitleTopBar.getText().toString());

        return paramFeedbackFragment;
    }

   /* private void wrongClick() {
        wrongClickCounter++;
    }*/


    private void startCountDownTimer() {

        countDownTimer.start();
    }

    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    private double calculatePoints() {

        double denominator = Math.log(1 + timeCounter);
        double points = (10 * (rightClickCountter - wrongClickCounter)) / denominator;

        points = Math.round(points);
        points = Math.abs(points);

        return Math.max(0,points);
      /*  if (points > 0)
            return points;
        else
            return 0;*/

    }

    private double calculateSpeed() {

        /*double sumPoints = calculatePoints();
        double speed = sumPoints / (timeCounter * 1.0f);

        int speed1 = (int) Math.round(speed);

        if (speed1 > 0) {
            return speed1;
        } else {
            return 0;
        }*/

        double speed=(rightClickCountter*40.0f)/(timeCounter*1.0f);
        speed=Math.round(speed);
        return Math.max(1,speed);

    }

    private void startInfiniteTimer() {
        infiniteTimer.start();
    }


    private void stopInfiniteTimer() {
        infiniteTimer.cancel();
    }

}
