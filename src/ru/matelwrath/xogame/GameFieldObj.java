package ru.matelwrath.xogame;

public class GameFieldObj {
    protected String value;
    protected boolean isUsed;
    protected boolean signX;
    protected boolean signO;

    public GameFieldObj() {
        this.value = " * ";
        this.isUsed = false;
        this.signX = false;
        this.signO = false;
    }
}
