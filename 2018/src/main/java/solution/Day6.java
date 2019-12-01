package solution;

import java.io.IOException;
import java.util.*;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> lines = AdventHelper.getLines("day6.txt");
        int[] solutions = parts(lines); // Parts 1 and 2 amalgamated to improve efficiency
        System.out.println(solutions[0] + "\n" + solutions[1]);
    }

    private static int updateRegions(int[][] grid, int xLimit, int yLimit, List<Coordinate> coordinates) {
        int safeArea = 0;
        for (int x = 0; x<xLimit; x++) {
            for (int y = 0; y<yLimit; y++) {

                int regionId = -1;
                Coordinate current = new Coordinate(regionId, x, y);
                Coordinate closest = Objects.requireNonNull(coordinates
                                            .stream()
                                            .min(Comparator.comparingInt(current::getDistance))
                                            .orElse(null)
                                    );

                boolean onlyPart2 = false;
                int totalDistance = 0;

                for (Coordinate c : coordinates) {
                    int minimumDistance = current.getDistance(closest);
                    int distance = current.getDistance(c);
                    totalDistance += distance; // For part 2

                    if (minimumDistance == distance && !(closest.x == c.x && closest.y == c.y)) {
                        regionId = -1; // Equidistant -> invalid
                        onlyPart2 = true;
                    }
                    else if (!onlyPart2) regionId = closest.regionId;
                }
                grid[x][y] = regionId;
                final int threshold = 10_000;
                if (totalDistance < threshold) safeArea++;
            }
        }
        return safeArea;
    }

    private static int getMaxArea(int[][] grid, int xLimit, int yLimit, List<Coordinate> coordinates) {
        int max = 0;
        int[] frequencies = new int[coordinates.size() + 1];
        for (int x = 0; x<xLimit; x++) {
            for (int y = 0; y<yLimit; y++) {

                int regionId = grid[x][y];
                if (regionId == -1) continue;

                if (x == 0 || x == xLimit - 1 || y == 0 || y == yLimit - 1)
                    frequencies[regionId] = Integer.MIN_VALUE; // Infinite area

                frequencies[regionId]++;
                max = Math.max(max, frequencies[regionId]);
            }
        }
        return max;
    }

    private static int[] parts(List<String> lines) {
        int regionId = 1;
        List<Coordinate> coordinates = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(", ");
            Coordinate c = new Coordinate(regionId++, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            coordinates.add(c);
        }

        final int xLimit = Collections.max(coordinates, Comparator.comparingInt(a -> a.x)).x + 1;
        final int yLimit = Collections.max(coordinates, Comparator.comparingInt(a -> a.y)).y + 1;
        int[][] grid = new int[xLimit][yLimit];

        // Place coordinates
        for (Coordinate c : coordinates)
            grid[c.x][c.y] = c.regionId;

        // Find each location's closest coordinate
        int safeArea = updateRegions(grid, xLimit, yLimit, coordinates);

        // Size of the largest area that isn't infinite and of the safe area
        return new int[] { getMaxArea(grid, xLimit, yLimit, coordinates), safeArea };
    }

    private static class Coordinate {

        private final int regionId, x, y;

        Coordinate(int regionId, int x, int y) {
            this.regionId = regionId;
            this.x = x;
            this.y = y;
        }

        private int getDistance(Coordinate other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }
    }
}
