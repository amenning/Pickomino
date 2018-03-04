package com.pickominio.model;

import javax.swing.*;
import java.awt.*;

public class JGradientButton extends JButton {

    private Color ButtonColor = Color.GRAY;

    public JGradientButton(String buttontext, Color color) {
        super(buttontext);
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
        ButtonColor = color;
    }

    public JGradientButton(Color color) {
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
        ButtonColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                Color.WHITE,
                new Point(0, getHeight()),
                ButtonColor.darker()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

}

