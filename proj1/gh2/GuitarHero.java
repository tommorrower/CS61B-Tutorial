package gh2;
import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import static java.lang.Math.pow;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * pow(2, 3.0 / 12.0);
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        LinkedListDeque<GuitarString> keyList = new LinkedListDeque<>();
        double sound;
        for (int i = 0; i < keyboard.length(); i++) {
            sound = 440 * pow(2, (double) (i - 24) / 12);
            GuitarString stringSound = new GuitarString(sound);
            keyList.addLast(stringSound);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0 && index < 37) {
                    keyList.get(index).pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += keyList.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < keyboard.length(); i++) {
                keyList.get(i).tic();
            }
        }
    }
}

