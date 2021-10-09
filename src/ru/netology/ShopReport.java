package ru.netology;

import java.util.concurrent.atomic.LongAdder;

public class ShopReport {

    public void addToReport(LongAdder report, int[] array) {
        for (int item : array) {
            report.add(item);
        }
    }
}
