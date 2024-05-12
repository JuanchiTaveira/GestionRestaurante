package com.example.views;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends JPanel {
    private final Image background;

    public ImagePanel(String imagePath) {
        // Carga la imagen de fondo
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        background = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen de fondo en el panel
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
