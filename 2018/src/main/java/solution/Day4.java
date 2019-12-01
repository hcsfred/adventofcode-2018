package solution;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {

    private static Map<Integer, Integer[]> guards = new HashMap<>();
    private static Integer[] emptyRecords = new Integer[60]; // Minutes 00-59

    static {
        Arrays.fill(emptyRecords, 0);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = AdventHelper.getLines("day4.txt");
        System.out.println(part1(lines));
        System.out.println(part2());
    }

    private static void parse(List<String> lines) {
        int guardId = -1;
        int startMinute = -1;
        Collections.sort(lines); // Sort into chronological order

        for (String line : lines) {
            final int minuteIndex = 15;
            int minute = Integer.parseInt(line.substring(minuteIndex, minuteIndex + 2));
            Pattern pattern = Pattern.compile("#(\\d+)");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) guardId = Integer.parseInt(matcher.group(1));
            else if (line.contains("falls asleep")) startMinute = minute;
            else {
                // Wakes up
                Integer[] records = guards.get(guardId);
                if (records == null) guards.put(guardId, emptyRecords.clone());
                else {
                    for (int i=startMinute; i<minute; i++) records[i] += 1; // Update records
                    guards.put(guardId, records);
                }
            }
        }
    }

    private static int getMaxMinute(int maxId) {
        List<Integer> records = Arrays.stream(guards.get(maxId)).collect(Collectors.toList());
        return records.indexOf(Collections.max(records));
    }

    private static int part1(List<String> lines) {
        parse(lines);
        // Guard that has the most minutes asleep
        int maxId = Objects.requireNonNull(guards.entrySet().stream().max(
                        Comparator.comparingInt(
                        guard -> Arrays.stream(guard.getValue()).mapToInt(Integer::intValue).sum()
                     )).orElse(null)).getKey();

        return maxId * getMaxMinute(maxId);
    }

    private static int part2() {
        // Guard most frequently asleep on the same minute
        int maxId = Objects.requireNonNull(guards.entrySet().stream().max(
                        (entry1, entry2) ->
                        Arrays.stream(entry1.getValue()).mapToInt(Integer::intValue).max().orElse(-1) >
                        Arrays.stream(entry2.getValue()).mapToInt(Integer::intValue).max().orElse(-1) ? 1 : -1
                    ).orElse(null)).getKey();

        return maxId * getMaxMinute(maxId);
    }
}
