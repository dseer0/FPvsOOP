package main.mona_oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MonaOOPMain {
    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/mona_small_gray.raw"));
        ArrayList<Byte> perfect = new ArrayList<>();

        for (byte b : bytes) {
            perfect.add(b);
        }

        long start = System.currentTimeMillis();
        Generator gen = new Generator(300, 2, 100, 256, 382, perfect);
        gen.generate();
        long end = System.currentTimeMillis();
    }
}
