package com.ioay.guessnumber.model;

import java.io.Serializable;

public class GuessNumber implements Serializable {

    private int numberId;
    private int guessNumber;
    private String numberRange;
    private int counter;
    private int ratioLevel;

    public GuessNumber(int numberId, int guessNumber, String numberRange, int counter, int ratioLevel) {
        this.numberId = numberId;
        this.guessNumber = guessNumber;
        this.numberRange = numberRange;
        this.counter = counter;
        this.ratioLevel = ratioLevel;
    }

    public GuessNumber() {
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getRatioLevel() {
        return ratioLevel;
    }

    public void setRatioLevel(int ratioLevel) {
        this.ratioLevel = ratioLevel;
    }

    @Override
    public String toString() {
        return "GuessNumber{" +
                "numberId=" + numberId +
                ", guessNumber=" + guessNumber +
                ", numberRange='" + numberRange + '\'' +
                ", counter=" + counter +
                ", ratioLevel=" + ratioLevel +
                '}';
    }
}
