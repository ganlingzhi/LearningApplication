package com.example.learningapplication.main.designmodel.orderModle.kitchen;

import com.example.learningapplication.main.designmodel.orderModle.barbecueguy.BarbecueGuy;

public class Kitchen {
    private static volatile int MUTTON_COUNT = 200;
    private static volatile int CHICKEN_WING_COUNT = 200;

    public static int getMuttonCount() {
        return MUTTON_COUNT;
    }

    public static int getChickenWingCount() {
        return CHICKEN_WING_COUNT;
    }

    public static synchronized Boolean getMutton(int count) {
        if (MUTTON_COUNT > count) {
            MUTTON_COUNT -= count;
            return true;
        } else {
            System.out.println("当前剩余:" + MUTTON_COUNT + " 羊肉不足，差" + (count - MUTTON_COUNT) + BarbecueGuy.getCurrentTime());
            return false;
        }
    }

    public static synchronized void supMutton(int count) {
        MUTTON_COUNT += count;
    }

    public static synchronized Boolean getChickenWing(int count) {
        if (CHICKEN_WING_COUNT > count) {
            CHICKEN_WING_COUNT -= count;
            return true;
        } else {
            System.out.println("当前剩余：" + CHICKEN_WING_COUNT + " 鸡翅不足，差" + (count - CHICKEN_WING_COUNT) + BarbecueGuy.getCurrentTime());
            return false;
        }
    }

    public static synchronized void supChicken(int count) {
        CHICKEN_WING_COUNT += count;
    }
}
