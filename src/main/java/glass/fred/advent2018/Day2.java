package glass.fred.advent2018;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> ids = AdventHelper.getLines("day2.txt");
        System.out.println(part1(ids));
        System.out.println(part2(ids));
    }

    private static int[] getFrequencies(String id) {
        final int alphabetLength = 26;
        int[] frequencies = new int[alphabetLength];

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            frequencies[c - 'a']++;
        }
        return frequencies;
    }

    private static int part1(List<String> ids) {
        int twice = 0;
        int thrice = 0;

        for (String id : ids) {
            int[] frequencies = getFrequencies(id);
            if (IntStream.of(frequencies).anyMatch(i -> i == 2)) twice++;
            if (IntStream.of(frequencies).anyMatch(i -> i == 3)) thrice++;
        }
        return twice * thrice;
    }

    private static int getIndex(String id, String other) {
        int differences = 1;
        int index = -1;

        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) != other.charAt(i)) {
                if (--differences < 0) break;
                index = i;
            }
        }
        return differences == 0 ? index : -1;
    }

    private static String part2(List<String> ids) {
        for (String id : ids) {
            for (String other : ids) {
                if (id.equals(other)) continue;

                int index = getIndex(id, other);
                if (index != -1) return id.substring(0, index) + id.substring(index + 1); // Remove uncommon character
            }
        }
        throw new IllegalArgumentException();
    }
}
