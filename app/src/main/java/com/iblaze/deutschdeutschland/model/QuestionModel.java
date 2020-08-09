package com.iblaze.deutschdeutschland.model;

/**
 * Created by ADNROID1 on 21-09-2017.
 */

public class QuestionModel {

    private String queNo;
    private String answer;
    private String question;
    private OptionsForAnswer optionsForAnswer;

    public String getQueNo() {
        return queNo;
    }

    public void setQueNo(String queNo) {
        this.queNo = queNo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public OptionsForAnswer getOptionsForAnswer() {
        return optionsForAnswer;
    }

    public void setOptionsForAnswer(OptionsForAnswer optionsForAnswer) {
        this.optionsForAnswer = optionsForAnswer;
    }
}
