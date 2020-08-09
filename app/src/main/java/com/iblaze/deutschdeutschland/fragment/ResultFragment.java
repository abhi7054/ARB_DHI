package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.HomeActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;
import com.iblaze.deutschdeutschland.model.ParamResultFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ADNROID1 on 21-09-2017.
 */

public class ResultFragment extends BaseFragment {
    @BindView(R.id.imgMenuTopbar)
    ImageView imgMenuTopbar;
    @BindView(R.id.txtTitleTopBar)
    TextView txtTitleTopBar;
    @BindView(R.id.imgDictionaryTopbar)
    ImageView imgDictionaryTopbar;
    @BindView(R.id.viewBottomLineTopbar)
    View viewBottomLineTopbar;
    @BindView(R.id.txtResult)
    TextView txtResult;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    @BindView(R.id.llBottomView)
    LinearLayout llBottomView;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    Unbinder unbinder;
    int[] arrImgBg = {R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1,
            R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1, R.drawable.result1};
    private HomeActivity homeActivity;
    private ParamResultFragment paramFeedback;

    public static ResultFragment newInstance(ParamResultFragment paramFeedback) {

        ResultFragment fragment = new ResultFragment();
        fragment.setParamFeedback(paramFeedback);
        return fragment;
    }

    public void setParamFeedback(ParamResultFragment feedbackParam) {
        this.paramFeedback = feedbackParam;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        topBarSetup();

        setFeedBackValue();
    }

    private void setFeedBackValue() {
        imgBackground.setImageResource(arrImgBg[getRandomNumber(1, 15)]);
        txtResult.setText(getString(R.string.du_hast) +" "+
                paramFeedback.getFeedbackSpeed()+" "+ getString(R.string.minuten_gespielt_und_dich_mit) +" "+ paramFeedback.getRightClick()+" "+getString(R.string.fragen_befasst_dabei_hast_du_einiges_gelern_denn_du_hast)+" "+ paramFeedback.getWrongClick()+" " + getString(R.string.mal_falsch_gelegen_geht_es_noch_besser));
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
}
