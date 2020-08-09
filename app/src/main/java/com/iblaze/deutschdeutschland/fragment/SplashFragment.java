package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.SplashActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ADNROID1 on 23-09-2017.
 */

public class SplashFragment extends BaseFragment {

    @BindView(R.id.txtStart)
    TextView txtStart;
    @BindView(R.id.llBottomView)
    LinearLayout llBottomView;
    Unbinder unbinder;
    @BindView(R.id.imgInfo)
    ImageView imgInfo;

    private SplashActivity splashActivity;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            visibleView(true);
        }
    };
    private Handler handler;

    public static SplashFragment newInstance() {

        Bundle args = new Bundle();

        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void visibleView(boolean isAnimation) {
        llBottomView.setVisibility(View.VISIBLE);
        txtStart.setVisibility(View.VISIBLE);
        if (isAnimation) {
//            YoYo.with(Techniques.BounceInUp).duration(800).playOn(txtStart);
            YoYo.with(Techniques.BounceInUp).duration(800).playOn(txtStart);
//            YoYo.with(Techniques.SlideInUp).duration(800).playOn(llBottomView);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spash, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (handler == null) {
            handler = new Handler();
            handler.postDelayed(runnable, 3000);
        } else {
            visibleView(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SplashActivity) {
            splashActivity = (SplashActivity) context;
        }
    }

    @OnClick({R.id.txtStart, R.id.imgInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtStart:
                splashActivity.openHomeActivity();
                break;
            case R.id.imgInfo:
                splashActivity.openInfoFragment(FragmentState.REPLACE);
                break;
        }
    }
}