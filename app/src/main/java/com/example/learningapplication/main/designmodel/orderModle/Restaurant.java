package com.example.learningapplication.main.designmodel.orderModle;

import com.example.learningapplication.main.designmodel.orderModle.barbecueguy.BarbecueGuy;
import com.example.learningapplication.main.designmodel.orderModle.command.BarbecueChickenWingCommand;
import com.example.learningapplication.main.designmodel.orderModle.command.BarbecueMuttonCommand;
import com.example.learningapplication.main.designmodel.orderModle.kitchen.Kitchen;
import com.example.learningapplication.main.designmodel.orderModle.waiter.WaiterVersion1;

public class Restaurant {
    public static void main(String[] args) {
        BarbecueGuy barbecueGuy = new BarbecueGuy();
        WaiterVersion1 waiter = new WaiterVersion1();

        BarbecueChickenWingCommand chickenCommand = new BarbecueChickenWingCommand(barbecueGuy);

        System.out.println("羊肉串数量："+Kitchen.getMuttonCount());
        System.out.println("鸡翅数量：" + Kitchen.getChickenWingCount());
        orderMeal("customerA", () -> {
            String id = Thread.currentThread().getName();
            waiter.setOrder(id, chickenCommand);
            waiter.setOrder(id, new BarbecueMuttonCommand(barbecueGuy, 5));
            waiter.setOrder(id, new BarbecueMuttonCommand(barbecueGuy, 100));
        });

        orderMeal("customerB", () -> {
            String id = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                waiter.setOrder(id, new BarbecueMuttonCommand(barbecueGuy));
            }
            waiter.setOrder(id, new BarbecueMuttonCommand(barbecueGuy, 200));
        });
        waiter.notifyAllOrders();
    }

    //点单
    private static void orderMeal(String name, Runnable runnable) {
        new Thread(runnable, name).start();
    }
}
