package day14;

import common.Input;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Solver {

    public long part1(String filepath) throws IOException {
        return processFile(filepath, 10);
    }

    private long processFile(String filepath, int iterations) {
        List<String> lines = Input.readLines(filepath);

        Map<String, String> rules = parseRules(lines);
        Map<String, AtomicLong> pairs = parsePairs(lines);

//        System.out.println(pairs);
        for (int i = 0; i< iterations; i++) {
            pairs = processRules(pairs, rules);
//            System.out.println(pairs);
        }

        Map<String, AtomicLong> occurrences = new HashMap<>();
        pairs.forEach((k,v) -> {
            addLetterToOccurrences(occurrences, k.substring(0, 1), v);
            addLetterToOccurrences(occurrences, k.substring(1, 2), v);
        });

        long[] occ = occurrences.values().stream().mapToLong(AtomicLong::longValue).sorted().toArray();

        long max = occ[occ.length - 1];
        long min = occ[0];

        long correction = 0;
        if (max % 2 > 0) correction = 1; else if (min % 2 > 0) correction = -1;

        return (max - min + correction) / 2;
    }

    private Map<String, AtomicLong> parsePairs(List<String> lines) {
        Map<String, AtomicLong> pairs = new HashMap<>();
        String start = lines.get(0);
        for (int i = 0; i< start.length()-1; i++) {
            incrementOccurrence(pairs, start.substring(i, i + 2), 1);
        }
        return pairs;
    }

    private static Map<String, String> parseRules(List<String> lines) {
        Map<String, String> rules = new HashMap<>();
        lines.stream()
                .skip(2)
                .map(s -> s.split(" -> "))
                .forEach(a -> rules.put(a[0], a[1]));
        return rules;
    }

    private static void addLetterToOccurrences(Map<String, AtomicLong> occurrences, String letter, AtomicLong count) {
        if (occurrences.containsKey(letter))
            occurrences.get(letter).addAndGet(count.get());
        else {
            occurrences.put(letter, new AtomicLong(count.get()));
        }
    }


    private long processFile_old(String filepath, int iterations) {
        List<String> lines = Input.readLines(filepath);

        String polymer = lines.get(0);

        Map<String, String> rules = parseRules(lines);

        for (int i = 0; i< iterations; i++) {
            polymer = processRules_old(polymer, rules);
            //System.out.println(polymer);
        }

        Map<String, AtomicLong> occurrences = new HashMap<>();
        for (int i=0; i<polymer.length(); i++) {
            String letter = polymer.substring(i, i+1);
            if (occurrences.containsKey(letter))
                occurrences.get(letter).incrementAndGet();
            else {
                occurrences.put(letter, new AtomicLong(1));
            }
        }
        long[] occ = occurrences.values().stream().mapToLong(AtomicLong::longValue).sorted().toArray();
        return occ[occ.length - 1] - occ[0];
    }

    private Map<String, AtomicLong>  processRules(Map<String, AtomicLong> pairs, Map<String, String> rules) {
        Map<String, AtomicLong> newPairs = new HashMap<>();
        pairs.forEach((k,v) -> {
            if (rules.containsKey(k)) {
                incrementOccurrence(newPairs, k.charAt(0) + rules.get(k), v.get());
                incrementOccurrence(newPairs, rules.get(k) + k.charAt(1), v.get());
            }
            else
                incrementOccurrence(newPairs, k, v.get());
        });
        return newPairs;
    }

    private void incrementOccurrence(Map<String, AtomicLong> occurrences, String pair, long value) {
        if (occurrences.containsKey(pair))
            occurrences.get(pair).addAndGet(value);
        else {
            occurrences.put(pair, new AtomicLong(value));
        }
    }

    private String processRules_old(String occurrences, Map<String, String> rules) {
        StringBuilder newPolymer = new StringBuilder();
        for (int i=0; i<occurrences.length()-1; i++) {
            String pair = occurrences.substring(i, i + 2);
            newPolymer.append(pair.charAt(0));
            if (rules.containsKey(pair)) {
                newPolymer.append(rules.get(pair));
            }
        }
        newPolymer.append(occurrences.substring(occurrences.length()-1));

        return newPolymer.toString();
    }

    public long part2(String filepath) throws IOException {
        return processFile(filepath, 40);
    }
}
