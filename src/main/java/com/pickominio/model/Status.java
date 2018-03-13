package com.pickominio.model;

public class Status {
    private static boolean canRollDice = true;
    private static boolean canFreezeDice = false;
    private static boolean canTakeWorm = false;
    private static boolean isBunk = false;
    private static boolean isGameOver = false;

    public static boolean canFreezeDice() {
        return canFreezeDice;
    }

    public static void setCanFreezeDice(boolean canFreezeDice) {
        Status.canFreezeDice = canFreezeDice;
    }

    public static boolean canRollDice() {
        return canRollDice;
    }

    public static void setCanRollDice(boolean canRollDice) {
        Status.canRollDice = canRollDice;
    }

    public static boolean canTakeWorm() {
        return canTakeWorm;
    }

    public static void setCanTakeWorm(boolean canTakeWorm) {
        Status.canTakeWorm = canTakeWorm;
    }

    public static boolean isBunk() {
        return isBunk;
    }

    public static void setIsBunk(boolean isBunk) {
        Status.isBunk = isBunk;
    }

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void setIsGameOver(boolean gameOver) {
        Status.isGameOver = gameOver;
    }
}
