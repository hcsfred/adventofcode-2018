package glass.fred.advent2018;

import java.io.IOException;
import java.util.*;

public class Day7 {

    private static Set<Step> stepsClone;

    public static void main(String[] args) throws IOException {
        List<String> lines = AdventHelper.getLines("day7.txt");
        System.out.println(part1(lines));
        System.out.println(part2());
    }

    private static List<Step> parse(List<String> lines) {
        List<Step> steps = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("tep ");

            char requirement = parts[1].charAt(0);
            char id = parts[2].charAt(0);

            Step step = new Step(id);
            if (steps.contains(step)) {
                int i = steps.indexOf(step);
                steps.get(i).requirements.add(requirement);
            } else {
                step.requirements.add(requirement);
                steps.add(step);
            }

            Step other = new Step(requirement);
            if (!steps.contains(other)) steps.add(other);
        }
        steps.sort(Comparator.comparingInt((Step s) -> s.id));
        return steps;
    }

    private static boolean requirementsMet(List<Character> requirements, Set<Character> path) {
        if (requirements.isEmpty()) return true;
        for (Character c : requirements)
            if (!path.contains(c)) return false;
        return true;
    }

    private static void addToPath(List<Step> steps, Set<Character> path) {
        for (int i = 0; i < steps.size(); i++) {
            Step step = steps.get(i);
            if (requirementsMet(step.requirements, path)) {
                path.add(step.id);
                steps.remove(step);
                i = -1; // Restart loop
            }
        }
    }

    private static String part1(List<String> lines) {
        List<Step> steps = parse(lines);
        stepsClone = new HashSet<>(steps); // Clone for part 2

        Set<Character> path = new LinkedHashSet<>();
        addToPath(steps, path);

        StringBuilder solution = new StringBuilder();
        for (Character c : path) solution.append(c);

        return solution.toString();
    }

    private static void assign(Worker[] workers, Set<Character> path) {
        final int baseDuration = 60;
        Iterator<Step> it = stepsClone.iterator();

        while (it.hasNext()) {
            Step step = it.next();

            for (Worker worker : workers)
                if (worker.finished()) worker.finish(path);

            if (requirementsMet(step.requirements, path)) {

                for (Worker worker : workers) {
                    if (worker.available()) {
                        worker.step = step.id;
                        it.remove();
                        worker.timeLeft = baseDuration + step.id - 'A' + 1;
                        break;
                    }
                }
            }
        }
    }

    private static int part2() {
        final int n = 5;
        Worker[] workers = new Worker[n];
        for (int i=0; i<n; i++) workers[i] = new Worker();

        Set<Character> path = new LinkedHashSet<>();
        final int pathLength = stepsClone.size();
        int totalDuration = 0;

        loop:
        while (path.size() != pathLength) {
            if (stepsClone.isEmpty()) {
                for (Worker worker : workers) {
                    if (worker.finished()) {
                        worker.finish(path);
                        break loop;
                    }
                }
            } else assign(workers, path);

            // Decrement worker time
            for (Worker worker : workers)
                if (worker.timeLeft > 0) worker.timeLeft--;

            totalDuration++;
        }
        return totalDuration;
    }

    private static class Step {

        private final char id;
        private List<Character> requirements = new ArrayList<>();

        Step(char id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Step)) return false;

            Step other = (Step) o;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private static class Worker {

        private int timeLeft = 0;
        private char step = 0;

        private boolean available() {
            return timeLeft == 0 && step == 0;
        }

        private boolean finished() {
            return timeLeft == 0 && step != 0;
        }

        private void finish(Set<Character> path) {
            path.add(step);
            step = 0;
        }
    }
}
