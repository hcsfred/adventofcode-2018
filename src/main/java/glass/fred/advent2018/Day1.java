package main.java.glass.fred.advent2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    public void run(String filename, boolean part1) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {

            if (part1) part1(stream);
            else part2(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part1(Stream<String> stream) {
        int frequency = stream.mapToInt(Integer::valueOf).sum();
        System.out.println("Total frequency: " + frequency);
    }

    private void part2(Stream<String> stream) {
        int frequency = 0;
        List<Integer> cache = new ArrayList<>();
        List<Integer> frequencies = stream.map(Integer::valueOf).collect(Collectors.toList());

        while (true) {
            for (int freq : frequencies) {
                frequency += freq;
                if (cache.contains(frequency)) {
                    System.out.println("Frequency reached twice: " + frequency);
                    return;
                } else cache.add(frequency);
            }
        }
    }
}
