package glass.fred.advent2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    private static final Pattern PATTERN = Pattern.compile("^#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)$");
    private static final int SIZE = 1000;
    private static SquareState[][] fabric = new SquareState[SIZE][SIZE];
    private static List<Claim> noConflict = new ArrayList<>(); // Reduces number of required iterations

    static {
        for (SquareState[] row : fabric)
            Arrays.fill(row, SquareState.EMPTY);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = AdventHelper.getLines("2018/day3.txt");
        System.out.println(part1(lines));
        System.out.println(part2());
    }

    private static Claim parse(String line) {
        Matcher matcher = PATTERN.matcher(line);

        if (!matcher.matches()) throw new IllegalArgumentException();

        return new Claim(Integer.parseInt(matcher.group(1)),
                         Integer.parseInt(matcher.group(2)),
                         Integer.parseInt(matcher.group(3)),
                         Integer.parseInt(matcher.group(4)),
                         Integer.parseInt(matcher.group(5)));
    }

    private static int part1(List<String> lines) {
        int overlaps = 0;

        for (String line : lines) {
            Claim claim = parse(line);

            boolean conflict = false;
            for (int i=0; i<claim.width; i++) {
                for (int j=0; j<claim.height; j++) {

                    SquareState state = fabric[claim.x + i][claim.y + j];

                    if (state == SquareState.EMPTY) {
                        fabric[claim.x + i][claim.y + j] = SquareState.FILLED;
                    } else if (state == SquareState.FILLED) {
                        fabric[claim.x + i][claim.y + j] = SquareState.COUNTED;
                        overlaps++;
                    }

                    if (state != SquareState.EMPTY) conflict = true;
                }
            }
            if (!conflict) noConflict.add(claim);
        }
        return overlaps;
    }

    private static int part2() {
        outer:
        for (Claim claim : noConflict) {
            for (int i=0; i<claim.width; i++) {
                for (int j = 0; j < claim.height; j++) {
                    SquareState state = fabric[claim.x + i][claim.y + j];
                    if (state == SquareState.COUNTED) continue outer;
                }
            }
            return claim.id;
        }
        throw new IllegalArgumentException();
    }

    private static final class Claim {

        private final int id, x, y, width, height;

        Claim(int id, int x, int y, int width, int height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private enum SquareState {
        EMPTY,
        FILLED,
        COUNTED
    }
}
