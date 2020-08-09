package com.iblaze.deutschdeutschland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iblaze.deutschdeutschland.R;
import com.iblaze.deutschdeutschland.activity.HomeActivity;
import com.iblaze.deutschdeutschland.enumeration.FragmentState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by ANDROID on 20-09-2017.
 */

public class GrammerDetailsFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.imgMenuTopbar)
    ImageView imgMenuTopbar;
    @BindView(R.id.txtTitleTopBar)
    TextView txtTitleTopBar;
    @BindView(R.id.imgDictionaryTopbar)
    ImageView imgDictionaryTopbar;
    @BindView(R.id.bottomBarNomen)
    TextView bottomBarNomen;
    @BindView(R.id.bottomBarVerb)
    TextView bottomBarVerb;
    @BindView(R.id.bottomBarSatz)
    TextView bottomBarSatz;
    @BindView(R.id.imgPrevious)
    ImageView imgPrevious;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    @BindView(R.id.webViewGrammer)
    WebView webViewGrammer;

    ArrayList<String> grammerNomenList;
    ArrayList<String> grammerSatzList;
    ArrayList<String> grammerVerbList;

    int grammerCounter = 0;

    String fileNOMEN = "GR_NOMEN.txt";
    String fileVERB = "GR_VERB.txt";
    String fileSATZ = "GR_SATZ.txt";
    int flag;
    int clickFlag;
    private HomeActivity homeActivity;

    public static GrammerDetailsFragment newInstance(int f) {
//        Bundle args = new Bundle();
        GrammerDetailsFragment fragment = new GrammerDetailsFragment();
        fragment.setFlag(f);
        return fragment;

    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grammer_details, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        flag = 1;

        webViewGrammer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        webViewGrammer.setLongClickable(false);
        webViewGrammer.setHapticFeedbackEnabled(false);

        grammerNomenList = new ArrayList<>();
        grammerVerbList = new ArrayList<>();
        grammerSatzList = new ArrayList<>();

        grammerNomenList = getGrammerContentFromFile(fileNOMEN);
        grammerVerbList = getGrammerContentFromFile(fileVERB);
        grammerSatzList = getGrammerContentFromFile(fileSATZ);

//        setDataInWebview(grammerNomenList.get(grammerCounter));


        switch (flag) {
            case 1:
                topBarSetup(getResources().getString(R.string.namenkunde_basis));

                setDataInWebview(grammerNomenList.get(grammerCounter));
                clickFlag = grammerNomenList.size();

                break;
            case 2:
                topBarSetup(getResources().getString(R.string.namenkunde_personen));

                setDataInWebview(grammerVerbList.get(grammerCounter));
                clickFlag = grammerVerbList.size();

                break;
            case 3:
                topBarSetup(getResources().getString(R.string.namenkunde_orte));

                setDataInWebview(grammerSatzList.get(grammerCounter));
                clickFlag = grammerSatzList.size();

                break;
        }


        Log.e("Size ", "grammerNomenList : " + grammerNomenList.size()
                + "\ngrammerVerbList : " + grammerVerbList.size()
                + "\ngrammerSatzList : " + grammerSatzList.size());


    }

    private ArrayList<String> getGrammerContentFromFile(String strFileName) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        ArrayList<String> grammerList = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(strFileName)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

            String abc = String.valueOf((CharSequence) text);

            String[] items = abc.split("\\[+" + "new screen" + "\\]");
            for (String item : items) {
                grammerList.add(item.trim());
            }

//            Log.e("grammerNomenList.size ", "size  : " + grammerNomenList.size());
//            setDataInWebview(grammerNomenList.get(nomenCounter));
        }
        return grammerList;
    }

    private void setDataInWebview(String strHtmlContent) {

        String strHtml = "<html><head><meta charset='utf-8'></head><div style='font-family: sans-serif;font-size: 14px; text-align: justify;'>";
        String strHtmlEnd = "</div></html>";
        String strFinal = strHtml + strHtmlContent + strHtmlEnd;
        strFinal = strFinal.replace("<title bold>", "<center><h2>");
        strFinal = strFinal.replace("</title bold>", "</center></h2>");
        strFinal = strFinal.replace("\n", "</br>");

//        webViewGrammer.setWebViewClient(new WebViewClient());
//        WebSettings webSettings = webViewGrammer.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setAppCacheEnabled(false);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        Log.e("strFinal ", "....... " + strFinal);
        webViewGrammer.clearCache(true);
        webViewGrammer.loadDataWithBaseURL(null, strFinal, "text/html", "charset=UTF-8;", null);
    }

    private void topBarSetup(String title) {
        txtTitleTopBar.setText(title);
        txtTitleTopBar.setAllCaps(true);
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

    @OnClick({R.id.imgMenuTopbar, R.id.imgDictionaryTopbar, R.id.bottomBarNomen, R.id.bottomBarVerb, R.id.bottomBarSatz, R.id.imgPrevious, R.id.imgNext})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.imgMenuTopbar:
                grammerCounter = 0;
                homeActivity.openHomeFragment(FragmentState.REPLACE);
                break;
            case R.id.imgDictionaryTopbar:
                break;
            case R.id.bottomBarNomen:
                clickFlag = grammerNomenList.size();
                topBarSetup(getResources().getString(R.string.namenkunde_basis));
                flag = 1;
                grammerCounter = 0;
                setDataInWebview(grammerNomenList.get(grammerCounter));
                break;
            case R.id.bottomBarVerb:
                clickFlag = grammerVerbList.size();
                topBarSetup(getResources().getString(R.string.namenkunde_personen));
                flag = 2;
                grammerCounter = 0;
                setDataInWebview(grammerVerbList.get(grammerCounter));
                break;
            case R.id.bottomBarSatz:
                clickFlag = grammerSatzList.size();
                topBarSetup(getResources().getString(R.string.namenkunde_orte));
                flag = 3;
                grammerCounter = 0;
                setDataInWebview(grammerSatzList.get(grammerCounter));
                break;
            case R.id.imgPrevious:

                if (grammerCounter > 0) {
                    grammerCounter -= 1;
//                    Log.e("grammerCounter ", " ===  " + grammerCounter);
                    switch (flag) {
                        case 1:
                            setDataInWebview(grammerNomenList.get(grammerCounter));
                            break;
                        case 2:
                            setDataInWebview(grammerVerbList.get(grammerCounter));
                            break;
                        case 3:
                            setDataInWebview(grammerSatzList.get(grammerCounter));
                            break;
                    }
                }

                break;
            case R.id.imgNext:

                if (grammerCounter < (clickFlag - 1)) {
                    grammerCounter += 1;
//                    Log.e("grammerCounter ", " ===  " + grammerCounter);
                    switch (flag) {
                        case 1:
                            setDataInWebview(grammerNomenList.get(grammerCounter));
                            break;
                        case 2:
                            setDataInWebview(grammerVerbList.get(grammerCounter));
                            break;
                        case 3:
                            setDataInWebview(grammerSatzList.get(grammerCounter));
                            break;
                    }
                }

                break;
        }
    }
}
