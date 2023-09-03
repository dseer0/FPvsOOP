package main.mona_oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mutator {
    public void mutate(List<ArrayList<Byte>> specs, Random rand, Integer imgWidth, Integer imgHeight) {
        for (int i = 0; i < specs.size(); i++) {
            ArrayList<Byte> currentSpec = specs.get(i);
            int x = rand.nextInt(imgWidth);
            int y = rand.nextInt(imgHeight);
            int width = rand.nextInt(imgWidth - x + 1);
            int height = rand.nextInt(imgHeight - y + 1);
            byte c = Integer.valueOf(rand.nextInt(256)).byteValue();
            for (int n = y; n < y + height; n++) {
                for (int m = x; m < x + width; m++) {
                    currentSpec.set(n * imgWidth + m, c);
                }
            }
        }
    }
}
