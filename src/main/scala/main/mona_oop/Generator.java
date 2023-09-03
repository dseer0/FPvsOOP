package main.mona_oop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Generator {
    private Integer specimensNum;
    private Integer bestNum;

    private Integer loopsNum;

    private Integer width;
    private Integer height;
    private ArrayList<Byte> perfectImage;
    private Random rand = new Random();
    private Mutator mutator = new Mutator();

    private LinkedList<ArrayList<Byte>> specs = new LinkedList<>();

    public Generator(Integer specimensNum, Integer bestNum, Integer loopsNum, Integer width, Integer height, ArrayList<Byte> perfectImage) {
        this.specimensNum = specimensNum;
        this.bestNum = bestNum;
        this.loopsNum = loopsNum;
        this.width = width;
        this.height = height;
        this.perfectImage = perfectImage;

        ArrayList<Byte> empty = new ArrayList<>();
        for (int i = 0; i < width * height; i++) empty.add(Byte.valueOf("0"));

        for (int i = 0; i < specimensNum; i++) {
            specs.add(new ArrayList<>(empty));
        }
    }

    private Integer translate(Integer x, Integer y) {
        return y * width + x;
    }

    public void generate() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            mutator.mutate(specs, rand, this.width, this.height);
            score();
            fillWithBest();
            if (i % 10 == 0) {
                System.out.println("score: " + scoreImage(specs.get(0)));
                dumpBest(i);
            }
        }
    }

    private void dumpBest(int num) {

        File outputFile = new File("best" + num + ".raw");

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            for (Byte b : specs.get(0)) {
                outputStream.write(b.byteValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double scoreImage(ArrayList<Byte> image) {
        double sc = 0.0;
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                double a = image.get(j * width + i);
                double b = perfectImage.get(j * width + i);
                sc += (a - b) * (a - b);
            }
        }

        return sc;
    }

    public void score() {
        specs.sort((image1, image2) -> {
            Double image1Score = scoreImage(image1);
            Double image2Score = scoreImage(image2);
            return image1Score.compareTo(image2Score);
        });
    }



    public void fillWithBest() {
        List<ArrayList<Byte>> fillWith = specs.subList(0, bestNum);

        LinkedList<ArrayList<Byte>> newList = new LinkedList<>();

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 5; j++) {
                ArrayList<Byte> originalElement = specs.get(j);
                ArrayList<Byte> copiedElement = new ArrayList<>(originalElement); // create a copy of the element
                newList.add(copiedElement); // add the copied element to the new list
            }
        }
        specs = newList;
    }

}
