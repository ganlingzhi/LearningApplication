package com.example.learningapplication.main.designmodel.orderModle.barbecueguy;

import com.example.learningapplication.main.designmodel.orderModle.kitchen.Kitchen;

import java.util.Date;

public class BarbecueGuy {

    /**
     * 烤羊肉串
     */
    public void barbecueMutton(int count) {
        count = Math.max(count, 1);
        System.out.print("服务员：需要烤" + count + "串羊肉串  ");
        if (!Kitchen.getMutton(count)) {
            return;
        }
        try {
            Thread.sleep(count);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("厨师：烤" + count + "串羊肉串 " + "当前剩余：" + Kitchen.getMuttonCount() + " " + getCurrentTime());
    }

    /**
     * 烤鸡翅
     */
    public void barbecueChickenWing(int count) {
        count = Math.max(count, 1);
        System.out.print("服务员：需要烤" + count + "串鸡翅  ");
        if (!Kitchen.getChickenWing(count)) {
            return;
        }
        try {
            Thread.sleep(count);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("厨师：烤" + count + "串鸡翅 " + "当前剩余：" + Kitchen.getChickenWingCount() + " " + getCurrentTime());
    }

    public static String getCurrentTime() {
        return " 当前时间:" + System.currentTimeMillis();
    }
}
