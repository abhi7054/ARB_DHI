package com.iblaze.deutschdeutschland.fragment;

import android.support.v4.app.Fragment;

import java.util.Random;


/**
 * Created by Chirag Solanki on 24-06-2017.
 */

public class BaseFragment extends Fragment {

    public int getRandomNumber(int minValue, int maxValue) {
        Random r = new Random();
        int randomNumber = r.nextInt(maxValue - minValue) + minValue;
        return randomNumber;

    }


}
