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
        Timer();
    }

    private void Timer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis();

                while (true) {
                    if (System.currentTimeMillis() - timer > 2000) {
                        closeWindow();
                        break;
                    }
                }

            }
        }).start();

    }
}
