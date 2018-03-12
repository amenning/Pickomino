package com.pickominio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceSet {
    protected static Map<Integer, ArrayList<Dice>> diceSet = new HashMap<>();
    protected static final int MAX_DICE_SET = 8;

    public DiceSet() {
        for (int value = 1; value <= Dice.MAX_DICE_VALUE; ++value) {
            this.diceSet.put(value, new ArrayList<Dice>());
        }
    }

    public void addDice(Dice dice) {
        diceSet.get(dice.getValue()).add(dice);
    }

    public List<Dice> getAllDiceOfValue(int value) {
        List<Dice> diceGroup = diceSet.get(value);
        diceSet.remove(value);
        return diceGroup;
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
