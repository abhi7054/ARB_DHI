package com.iblaze.deutschdeutschland.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.fragment.FeedbackFragment;
import com.iblaze.deutschdeutschland.fragment.GrammerDetailsFragment;
import com.iblaze.deutschdeutschland.fragment.GrammerFragment;
import com.iblaze.deutschdeutschland.fragment.HomeFragment;
import com.iblaze.deutschdeutschland.fragment.InfoFragment;
import com.iblaze.deutschdeutschland.fragment.QuestionFragment;
import com.iblaze.deutschdeutschland.fragment.ResultFragment;
import com.iblaze.deutschdeutschland.fragment.SplashFragment;
import com.iblaze.deutschdeutschland.listener.iActivityNavigatior;
import com.iblaze.deutschdeutschland.listener.iHomeNavigator;
import com.iblaze.deutschdeutschland.model.ParamFeedbackFragment;
import com.iblaze.deutschdeutschland.model.ParamQuestionFragment;
import com.iblaze.deutschdeutschland.model.ParamResultFragment;

public class AppNavigationActivity extends BaseActivity implements iHomeNavigator,iActivityNavigatior {

    @Override
    public void openQuestionFragment(FragmentState fragmentState, ParamQuestionFragment paramFragmentQuestion) {
        fragmentChange(QuestionFragment.newInstance(paramFragmentQuestion),fragmentState);
    }

    @Override
    public void openInfoFragment(FragmentState fragmentState) {
        fragmentChange(InfoFragment.newInstance(),fragmentState);
    }

    @Override
    public void openHomeFragment(FragmentState fragmentState) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentChange(HomeFragment.newInstance(), fragmentState);
    }

       @Override
    public void openFeedBackFragment(FragmentState fragmentState, ParamFeedbackFragment param) {
        fragmentChange(FeedbackFragment.newInstance(param), fragmentState);
    }

    @Override
    public void openGrammerFragment(FragmentState fragmentState) {
        fragmentChange(GrammerFragment.newInstance(), fragmentState);
    }

    @Override
    public void openResultFragment(FragmentState fragmentState, ParamResultFragment param) {
        fragmentChange(ResultFragment.newInstance(param), fragmentState);
    }

    @Override
    public void openGrammerDetailsFragment(FragmentState fragmentState, int flag) {
        fragmentChange(GrammerDetailsFragment.newInstance(flag), fragmentState);
    }

    @Override
    public void openSplashFragment(FragmentState fragmentState) {
        fragmentChange(SplashFragment.newInstance(), fragmentState);
    }

    @Override
    public void openHomeActivity() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
