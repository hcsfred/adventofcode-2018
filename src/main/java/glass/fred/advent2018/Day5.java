package glass.fred.advent2018;

import java.io.IOException;

public class Day5 {

    public static void main(String[] args) throws IOException {
        String polymer = AdventHelper.getLines("day5.txt").get(0);
        System.out.println(part1(polymer));
        System.out.println(part2(polymer));
    }

    private static char caseSwap(char c) {
        return Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }

    private static int part1(String polymer) {
        for (int i = 0; i < polymer.length() - 1;) {
            char c = polymer.charAt(i);
            char next = polymer.charAt(i + 1);

            if (c == caseSwap(next)) {
                polymer = polymer.substring(0, i) + polymer.substring(i + 2);
                if (i > 0) i--;
            } else i++;
        }
        return polymer.length();
    }

    private static int part2(String polymer) {
        int min = Integer.MAX_VALUE;
        for (char c = 'a'; c <='z'; c++ ) {
            String replaced = polymer.replaceAll("[" + c + Character.toUpperCase(c) + "]", "");
            min = Math.min(min, part1(replaced));
        }
        return min;
    }
}
