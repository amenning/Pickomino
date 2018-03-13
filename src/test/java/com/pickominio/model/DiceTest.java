package com.pickominio.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {
    private Dice dice;

    @Before
    public void setUp() throws Exception {
        dice = Dice.buildDice(3);
    }

    @Test
    public void getValue() {
        assertEquals(3, dice.getValue());
    }

    @Test
    public void setValue() {
        dice.setValue(4);
        assertEquals(4, dice.getValue());
    }
}