package com.iblaze.deutschdeutschland.model;

/**
 * Created by ADNROID1 on 22-09-2017.
 */

public class ParamFeedbackFragment {

    int feedbackPoints;
    int feedbackSpeed;
    int feedbackWrongClick;
    String titleTopbar;

    public String getTitleTopbar() {
        return titleTopbar;
    }

    public void setTitleTopbar(String titleTopbar) {
        this.titleTopbar = titleTopbar;
    }

    public int getFeedbackPoints() {
        return feedbackPoints;
    }

    public void setFeedbackPoints(int feedbackPoints) {
        this.feedbackPoints = feedbackPoints;
    }

    public int getFeedbackSpeed() {
        return feedbackSpeed;
    }

    public void setFeedbackSpeed(int feedbackSpeed) {
        this.feedbackSpeed = feedbackSpeed;
    }

    public int getFeedbackWrongClick() {
        return feedbackWrongClick;
    }

    public void setFeedbackWrongClick(int feedbackWrongClick) {
        this.feedbackWrongClick = feedbackWrongClick;
    }
}
