package GAME;

/**
 * Created by kostya on 28.04.2016.
 */
public class Timer {

    public Timer(final long time, final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis();

                while (true) {
                    if (System.currentTimeMillis() - timer >= time) {
                        runnable.run();
                        break;
                    }
                }

            }
        }).start();

    }
}
