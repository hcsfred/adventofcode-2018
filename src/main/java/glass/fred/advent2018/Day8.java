package glass.fred.advent2018;

import java.io.IOException;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws IOException {
        String line = AdventHelper.getLines("2018/day8.txt").get(0);
        List<Integer> list = parse(line);
        List<Integer> clone = new ArrayList<>(list);

        System.out.println(part1(list, list.remove(0), list.remove(0)));
        System.out.println(part2(clone, clone.remove(0), clone.remove(0)));
    }

    private static List<Integer> parse(String line) {
        List<Integer> list = new ArrayList<>();
        String[] input = line.split(" ");

        for (String s : input)
            list.add(Integer.parseInt(s));

        return list;
    }

    private static int part1(List<Integer> list, int children, int entries) {
        int sum = 0;

        for (int i=0; i<children; i++)
            sum += part1(list, list.remove(0), list.remove(0));

        for (int i=0; i<entries; i++)
            sum += list.remove(0);

        return sum;
    }

    private static int part2(List<Integer> list, int children, int entries) {
        List<Integer> childValues = new ArrayList<>();

        for (int i=0; i<children; i++)
            childValues.add(part2(list, list.remove(0), list.remove(0)));

        int sum = 0;

        for (int i=0; i<entries; i++) {
            int entry = list.remove(0);

            if (children == 0) sum += entry;
            else if (entry - 1 < childValues.size())
                sum += childValues.get(entry - 1);
        }

        return sum;
    }
}
