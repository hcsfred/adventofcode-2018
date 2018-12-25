package glass.fred.advent2018;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class Day9 {

    private static int nPlayers = 0;
    private static int nMarbles = 0;

    public static void main(String[] args) throws IOException {
        String line = AdventHelper.getLines("day9.txt").get(0);
        parse(line);
        System.out.println(play(false));
        System.out.println(play(true));
    }

    private static void parse(String line) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(line);

        if (!m.find()) throw new IllegalArgumentException();
        nPlayers = Integer.parseInt(m.group(0));

        if (!m.find()) throw new IllegalArgumentException();
        nMarbles = Integer.parseInt(m.group(0));
    }

    private static Marble specialCase(int i, long[] players, int playerIndex, Marble current, Set<Integer> seen) {
        players[playerIndex] += i;

        Marble other = current;
        for (int w=0; w<7; w++) other = other.prev; // 7 marbles counter-clockwise
        players[playerIndex] += other.score;

        // Remove
        Marble backup = other.next;
        Marble backup2 = other.prev;
        other.prev.next = backup;
        other.next.prev = backup2;
        seen.add(i);

        return backup;
    }

    private static Marble standardCase(int i, Marble current) {
        Marble lowest = new Marble(i);

        lowest.next = current.next.next;
        lowest.prev = current.next;
        current.next.next = lowest;
        current.next.next.next.prev = lowest;

        return lowest;
    }

    private static long play(boolean part2) {
        if (part2) nMarbles *= 100;

        Set<Integer> seen = new HashSet<>();
        Marble start = new Marble(0);
        Marble current = start;
        start.prev = start;
        start.next = start;

        long[] players = new long[nPlayers];
        int playerIndex = 0;

        for (int i=1; i< nMarbles +1;) {

            if (seen.contains(i)) {
                i++;
                continue;
            }

            final int special = 23;
            if (i % special == 0)
                current = specialCase(i, players, playerIndex, current, seen);
            else {
                current = standardCase(i, current);
                i++;
            }
            playerIndex = Math.floorMod(++playerIndex, nPlayers);
        }
        return LongStream.of(players).max().orElse(-1);
    }

    private static class Marble {
        
        private final int score;
        private Marble prev;
        private Marble next;

        Marble(int score) {
            this.score = score;
        }
    }
}
