package com.ioay.guessnumber.model;

import java.io.Serializable;

public class GuessNumber implements Serializable {

    private int numberId;
    private int guessNumber;
    private String numberRange;

    public GuessNumber() {
    }

    public GuessNumber(int numberId, int guessNumber, String numberRange) {
        this.numberId = numberId;
        this.guessNumber = guessNumber;
        this.numberRange = numberRange;
    }

    public int getNumberId() {
        return numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    public String getNumberRange() {
        return numberRange;
    }

    public void setNumberRange(String numberRange) {
        this.numberRange = numberRange;
    }

    @Override
    public String toString() {
        return "GuessNumber{" +
                "numberId=" + numberId +
                ", guessNumber=" + guessNumber +
                ", numberRange='" + numberRange + '\'' +
                '}';
    }
}
