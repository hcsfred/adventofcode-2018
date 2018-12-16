package glass.fred.advent2018;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public static void main(String args[]) throws IOException {
        List<String> lines = AdventHelper.getLines("day1.txt");
        System.out.println(part1(lines));
        System.out.println(part2(lines));
    }

    private static int part1(List<String> lines) {
        return lines.stream().mapToInt(Integer::valueOf).sum();
    }

    private static int part2(List<String> lines) {
        int[] frequencies = lines.stream().mapToInt(Integer::valueOf).toArray();
        Set<Integer> seen = new HashSet<>();

        int frequency = 0;
        int i = 0;

        do {
            frequency += frequencies[i++ % frequencies.length]; // Repeats frequency list
        } while(seen.add(frequency));

        return frequency;
    }
}
