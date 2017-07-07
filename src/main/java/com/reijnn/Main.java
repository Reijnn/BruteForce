package com.reijnn;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static String password = "";
    static List<String> chars = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
    static List<String> possibilities = new ArrayList<>();
    private static int passwordSize = 5;
    private static int threads = 6;

    public static void main(String[] args) {

        possibilities = new Generator().generateCombinations(passwordSize, (ArrayList<String>) chars);
        Collections.shuffle(possibilities);
        password = possibilities.get(new Random().nextInt(possibilities.size()));
        print();
        thread();
    }

    private static void print() {
        password = possibilities.get(new Random().nextInt(possibilities.size()));
        long startTime = System.nanoTime();
        for (String p : possibilities) {
            if (p.matches(Main.password)) {
                System.out.println("Tried " + p + " - Succes");
                System.out.println("Time elapsed: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) + " milliseconden");
                break;
            }
        }
    }

    private static void thread() {
        Collections.shuffle(possibilities);
        ExecutorService exe = Executors.newFixedThreadPool(threads);
        List<List<String>> subLists = Lists.partition(possibilities, (possibilities.size() / threads));
        for (int i = 0; i < threads; i++) {
            exe.submit(new Processor(i, subLists.get(i)));
        }
        exe.shutdown();
    }
}