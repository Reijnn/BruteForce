package com.reijnn;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by niels on 07/07/2017.
 */
public class Processor implements Runnable {
    int id;
    List<String> list;

    public Processor(int id, List<String> list) {
        this.id = id;
        this.list = list;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (String p : list) {
            if (p.matches(Main.password)) {
                System.out.println("Thread: " + id + " tried " + p + " - Succes");
                System.out.println("Time elapsed: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) + " milliseconden");
                break;
            }
        }
    }
}