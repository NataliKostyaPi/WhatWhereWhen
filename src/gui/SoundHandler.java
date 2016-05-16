package gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by kostya on 12.05.2016.
 */
public class SoundHandler {

    static HashMap<String, Clip> sounds = new HashMap<String, Clip>();

    public static Clip loadClip(String fileName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (sounds.containsKey(fileName)) {
            return sounds.get(fileName);
        }

        File soundFile = new File(SoundHandler.class.getResource(fileName).getFile());//Звуковой файл
        //Получаем AudioInputStream
        //Вот тут могут полететь IOException и UnsupportedAudioFileException
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        //Получаем реализацию интерфейса Clip
        //Может выкинуть LineUnavailableException
        Clip clip = AudioSystem.getClip();
        //Загружаем наш звуковой поток в Clip
        //Может выкинуть IOException и LineUnavailableException
        clip.open(ais);
        sounds.put(fileName, clip);
        return clip;
    }

    public static void beepSound(String fileName) {


        try {
            Clip clip = loadClip(fileName);

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }
}
