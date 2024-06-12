import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private static Clip currentClip;
    private static boolean stopRequested = false;

    public static Clip playMusic(String filepath, boolean loop) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filepath));
            clip.open(audioStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }

            currentClip = clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void stopMusic(boolean immediate) {
        if (currentClip != null && currentClip.isRunning()) {
            if (immediate) {
                currentClip.stop();
            } else {
                stopRequested = true;
            }
        }
    }

}

