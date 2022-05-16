package chess.gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound extends Thread{
    public void run() {
        try{
            AudioClip clip = Applet.newAudioClip((new File("./src/chess/gui/sounds/yinxiao.wav")).toURL());
            clip.play();
        } catch (MalformedURLException BGM) {
            BGM.printStackTrace();
        }
    }
}
