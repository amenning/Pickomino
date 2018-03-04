package com.pickominio.model;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel(ImageIcon imageicon) {
		img = imageicon.getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(new BorderLayout());
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

}
