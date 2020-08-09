package com.iblaze.deutschdeutschland.model;

/**
 * Created by ADNROID1 on 22-09-2017.
 */

public class ParamResultFragment {

    int feedbackSpeed;
    int rightClick;
    int wrongClick;
    String titleTopbar;

    public String getTitleTopbar() {
        return titleTopbar;
    }

    public void setTitleTopbar(String titleTopbar) {
        this.titleTopbar = titleTopbar;
    }

    public int getFeedbackSpeed() {
        return feedbackSpeed;
    }

    public void setFeedbackSpeed(int feedbackSpeed) {
        this.feedbackSpeed = feedbackSpeed;
    }

    public int getRightClick() {
        return rightClick;
    }

    public void setRightClick(int rightClick) {
        this.rightClick = rightClick;
    }

    public int getWrongClick() {
        return wrongClick;
    }

    public void setWrongClick(int wrongClick) {
        this.wrongClick = wrongClick;
    }
}
