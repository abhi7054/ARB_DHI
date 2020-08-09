package com.iblaze.deutschdeutschland.model;

/**
 * Created by ADNROID1 on 21-09-2017.
 */

public class ParamQuestionFragment {
    private boolean isTimer;
    private int categoty;
    private int level;

    public boolean isTimer() {
        return isTimer;
    }

    public void setTimer(boolean timer) {
        isTimer = timer;
    }

    public int getCategoty() {
        return categoty;
    }

    public void setCategoty(int categoty) {
        this.categoty = categoty;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
