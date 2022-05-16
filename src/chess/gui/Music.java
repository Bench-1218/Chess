package chess.gui;

import javax.sound.sampled.*;
import java.io.File;

public class Music extends Thread {
    @Override
    public void run() {
        while (true) {
            this.playMusic();
        }
    }

    public void playMusic() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./src/chess/gui/sounds/music.wav"));
            AudioFormat aif = ais.getFormat();
            final SourceDataLine sdl;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
            sdl = (SourceDataLine) AudioSystem.getLine(info);
            sdl.open(aif);
            sdl.start();
            FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
            //value调节音量
            double value = 0.5;
            float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
            fc.setValue(dB);
            int nByte = 0;
            final int SIZE = 1024 * 64;
            byte[] buffer = new byte[SIZE];
            while (nByte != -1) {
                nByte = ais.read(buffer, 0, SIZE);
                sdl.write(buffer, 0, nByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

