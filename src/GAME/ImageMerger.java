package GAME;

import gui.GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kostya on 16.05.2016.
 */
public class ImageMerger {

    public static File merge(String oldImage, String newImage) {

        try {

            // load source images
            BufferedImage image = ImageIO.read(new File(GUI.class.getResource(oldImage).getFile()));
            BufferedImage overlay = ImageIO.read(new File(GUI.class.getResource(newImage).getFile()));


            // create the new image, canvas size is the max. of both image sizes
            int w = Math.max(image.getWidth(), overlay.getWidth());
            int h = Math.max(image.getHeight(), overlay.getHeight());
            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            // paint both images, preserving the alpha channels
            Graphics g = combined.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.drawImage(overlay, 0, 0, null);
            File output = new File(GUI.class.getResource("Data/combined.png").getFile());
            // Save as new image
            ImageIO.write(combined, "PNG", output);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
