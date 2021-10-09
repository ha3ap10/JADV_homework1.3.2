package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static final int ARRAY_SIZE = 500;
    public static final int MAX_RANDOM = 10_000;

    public static void main(String[] args) {

        final ShopReport shopReport = new ShopReport();

        LongAdder report = new LongAdder();

        Runnable addToReport = () -> shopReport.addToReport(report, generateArray());

        Thread shop1 = new Thread(null, addToReport, "Магазин 1");
        Thread shop2 = new Thread(null, addToReport, "Магазин 2");
        Thread shop3 = new Thread(null, addToReport, "Магазин 3");

        shop1.start();
        shop2.start();
        shop3.start();

        try {
            shop1.join();
            shop2.join();
            shop3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("\nВыручка по всем магазинам: %,d", report.sum());

        System.out.println("\n\n\nЗавершение.");
    }

    public static int[] generateArray() {
        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(MAX_RANDOM) + 1;
        }
//        System.out.println(Arrays.toString(array));
        return array;
    }
}
