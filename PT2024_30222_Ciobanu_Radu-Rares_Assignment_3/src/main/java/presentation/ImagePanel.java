package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage originalImage;
    private int imageWidth;
    private int imageHeight;

    public ImagePanel(String imagePath, int width, int height) {
        try {
            originalImage = ImageIO.read(new File(imagePath));
            imageWidth = width;
            imageHeight = height;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (originalImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(originalImage, 0, 0, imageWidth, imageHeight, this);
            g2d.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }
}
