package chess.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound extends Thread {
    /* public void run() {
         try{

             AudioClip clip = Applet.newAudioClip((new File("./src/chess/gui/sounds/yinxiao.wav")).toURL());
             clip.play();
         } catch (MalformedURLException BGM) {
             BGM.printStackTrace();
         }
     }*/
    public void run() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((new File("./src/chess/gui/sounds/yinxiao.wav")).toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {

        }
    }

}
