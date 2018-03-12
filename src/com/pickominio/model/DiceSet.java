package com.pickominio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceSet {
    public static final int MAX_DICE_SET_SIZE = 8;
    protected Map<Integer, ArrayList<Dice>> diceSet = new HashMap<>();

    public DiceSet() {
        this.resetDiceSet();
    }

    public void addDice(Dice dice) {
        diceSet.get(dice.getValue()).add(dice);
    }

    public void addDice(List<Dice> diceGroup) {
        for (Dice dice : diceGroup) {
            diceSet.get(dice.getValue()).add(dice);
        }
    }

    public boolean hasValue(int value) {
        return diceSet.get(value).size() != 0;
    }

    public List<Dice> getAllDiceOfValue(int value) {
        List<Dice> diceGroup = diceSet.get(value);
        diceSet.put(value, new ArrayList<Dice>());
        return diceGroup;
    }

    public int getNumberOfDice() {
        int numberOfDice = 0;
        for (ArrayList<Dice> diceGroup : diceSet.values()) {
            numberOfDice += diceGroup.size();
        }
        return numberOfDice;
    }

    public void resetDiceSet() {
        for (int value = 1; value <= Dice.MAX_DICE_VALUE; ++value) {
            this.diceSet.put(value, new ArrayList<Dice>());
        }
    }

    public String toString() {
        String setValues = "";
        for (Map.Entry<Integer, ArrayList<Dice>> entry : diceSet.entrySet()) {
            int diceGroupSize = entry.getValue().size();
            int diceValue = entry.getKey();
            setValues += new String(new char[diceGroupSize])
                    .replace("\0", diceValue + " ");
        }
        return setValues;
    }
}
