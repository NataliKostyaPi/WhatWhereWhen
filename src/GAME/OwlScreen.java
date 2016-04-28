package GAME;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kostya on 28.04.2016.
 */
public class OwlScreen extends Screen {
    public OwlScreen(JFrame f, String backgrundImage, String screenTitle) {
        super(f, backgrundImage, screenTitle);

    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        super.setGui(frame, jpanel);
        new Timer(2000, new Runnable() {
            @Override
            public void run() {
                closeWindow();
            }
        });
    }


}
