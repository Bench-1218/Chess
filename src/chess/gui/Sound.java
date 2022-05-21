package chess.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound extends Thread {

    public void run() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((new File("./src/chess/gui/sounds/yinxiao.wav")).toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {

        }
    }

}
