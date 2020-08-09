package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.HomeActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.model.ParamQuestionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ANDROID on 20-09-2017.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.bottomBarStart)
    ImageView bottomBarStart;
    @BindView(R.id.bottomBarTimer)
    ImageView bottomBarTimer;
    @BindView(R.id.bottomBarLowLevel)
    ImageView bottomBarLowLevel;
    @BindView(R.id.bottomBarMediumLevel)
    ImageView bottomBarMediumLevel;
    @BindView(R.id.bottomBarHighLevel)
    ImageView bottomBarHighLevel;
    @BindView(R.id.imgHomeAboutUs)
    ImageView imgHomeAboutUs;
    @BindView(R.id.imgHomeGrammer)
    ImageView imgHomeGrammer;

    Unbinder unbinder;
    HomeActivity homeActivity;

    @BindView(R.id.linearBottom)
    LinearLayout linearBottom;
    @BindView(R.id.imgMenu1)
    ImageView imgMenu1;
    @BindView(R.id.imgMenu2)
    ImageView imgMenu2;
    @BindView(R.id.imgMenu3)
    ImageView imgMenu3;
    @BindView(R.id.imgMenu4)
    ImageView imgMenu4;
    @BindView(R.id.imgMenu5)
    ImageView imgMenu5;
    private boolean isTimer;
    private int catagory, level;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);

        Log.e("onCreateView", "...");

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeVariablesDafaultvalue();
    }

    private void initializeVariablesDafaultvalue() {
        isTimer = false;
        catagory = 1;
        level = 3;
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

    @OnClick({R.id.bottomBarStart, R.id.bottomBarTimer, R.id.bottomBarLowLevel, R.id.bottomBarMediumLevel, R.id.bottomBarHighLevel,
            R.id.imgHomeAboutUs, R.id.imgHomeGrammer,R.id.imgMenu1, R.id.imgMenu2, R.id.imgMenu3, R.id.imgMenu4, R.id.imgMenu5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottomBarStart:
                selectStart();
                break;
            case R.id.bottomBarTimer:
                selectTimer();
                break;
            case R.id.bottomBarLowLevel:
                selectLowLevel();
                break;
            case R.id.bottomBarMediumLevel:
                selectMediumLevel();
                break;
            case R.id.bottomBarHighLevel:
                selectHighLevel();
                break;
            case R.id.imgHomeAboutUs:
                homeActivity.openInfoFragment(FragmentState.REPLACE);
                break;
            case R.id.imgHomeGrammer:
                homeActivity.openGrammerFragment(FragmentState.REPLACE);
                break;
            case R.id.imgMenu1:
                catagory = 1;
                homeActivity.openQuestionFragment(FragmentState.REPLACE, getQuizParam());
                break;
            case R.id.imgMenu2:
                catagory = 2;
                homeActivity.openQuestionFragment(FragmentState.REPLACE, getQuizParam());
                break;
            case R.id.imgMenu3:
                catagory = 3;
                homeActivity.openQuestionFragment(FragmentState.REPLACE, getQuizParam());
                break;
            case R.id.imgMenu4:
                catagory = 4;
                homeActivity.openQuestionFragment(FragmentState.REPLACE, getQuizParam());
                break;
            case R.id.imgMenu5:
                catagory = 5;
                homeActivity.openQuestionFragment(FragmentState.REPLACE, getQuizParam());
                break;
        }
    }

    private ParamQuestionFragment getQuizParam() {
        ParamQuestionFragment param = new ParamQuestionFragment();
        param.setTimer(isTimer);
        param.setCategoty(catagory);
        param.setLevel(level);
        return param;
    }

    private void selectStart() {
        isTimer = false;
        bottomBarStart.setImageResource(R.drawable.ic_start_game_green);
        bottomBarTimer.setImageResource(R.drawable.ic_timer_white);
    }

    private void selectTimer() {
        isTimer = true;
        bottomBarTimer.setImageResource(R.drawable.ic_timer_green);
        bottomBarStart.setImageResource(R.drawable.ic_start_game_white);
    }

    private void selectLowLevel() {
        level = 1;

        bottomBarLowLevel.setImageResource(R.drawable.ic_low_level_green);
        bottomBarMediumLevel.setImageResource(R.drawable.ic_medium_level_white);
        bottomBarHighLevel.setImageResource(R.drawable.ic_high_level_white);
    }

    private void selectMediumLevel() {
        level = 2;

        bottomBarMediumLevel.setImageResource(R.drawable.ic_medium_level_green);
        bottomBarLowLevel.setImageResource(R.drawable.ic_low_level_white);
        bottomBarHighLevel.setImageResource(R.drawable.ic_high_level_white);
    }

    private void selectHighLevel() {
        level = 3;

        bottomBarHighLevel.setImageResource(R.drawable.ic_high_level_green);
        bottomBarLowLevel.setImageResource(R.drawable.ic_low_level_white);
        bottomBarMediumLevel.setImageResource(R.drawable.ic_medium_level_white);
    }


}
