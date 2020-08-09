package com.iblaze.deutschdeutschland.listener;

import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.model.ParamFeedbackFragment;
import com.iblaze.deutschdeutschland.model.ParamQuestionFragment;
import com.iblaze.deutschdeutschland.model.ParamResultFragment;

public interface iHomeNavigator {

    void openQuestionFragment(FragmentState fragmentState, ParamQuestionFragment paramFragmentQuestion);

    void openInfoFragment(FragmentState fragmentState);

    void openHomeFragment(FragmentState fragmentState);

    void openFeedBackFragment(FragmentState fragmentState, ParamFeedbackFragment param);

    void openGrammerFragment(FragmentState fragmentState);

    void openResultFragment(FragmentState fragmentState, ParamResultFragment param);

    void openGrammerDetailsFragment(FragmentState fragmentState, int flag);

    void openSplashFragment(FragmentState fragmentState);

}
