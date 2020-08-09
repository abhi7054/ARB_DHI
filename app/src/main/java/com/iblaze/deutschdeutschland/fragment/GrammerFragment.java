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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ANDROID on 20-09-2017.
 */

public class GrammerFragment extends BaseFragment {

    Unbinder unbinder;

    HomeActivity homeActivity;
    @BindView(R.id.imgAboutUs)
    ImageView imgAboutUs;
    @BindView(R.id.bottomBarNomen)
    TextView bottomBarNomen;
    @BindView(R.id.bottomBarVerb)
    TextView bottomBarVerb;
    @BindView(R.id.bottomBarSatz)
    TextView bottomBarSatz;
    @BindView(R.id.linearBottom)
    LinearLayout linearBottom;

    public static GrammerFragment newInstance() {

        Bundle args = new Bundle();

        GrammerFragment fragment = new GrammerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grammer, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;

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


    @OnClick({R.id.imgAboutUs, R.id.bottomBarNomen, R.id.bottomBarVerb, R.id.bottomBarSatz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgAboutUs:
                homeActivity.openInfoFragment(FragmentState.REPLACE);
                break;
            case R.id.bottomBarNomen:
                homeActivity.openGrammerDetailsFragment(FragmentState.REPLACE,1);
                break;
            case R.id.bottomBarVerb:
                homeActivity.openGrammerDetailsFragment(FragmentState.REPLACE,2);
                break;
            case R.id.bottomBarSatz:
                homeActivity.openGrammerDetailsFragment(FragmentState.REPLACE,3);
                break;
        }
    }
}
