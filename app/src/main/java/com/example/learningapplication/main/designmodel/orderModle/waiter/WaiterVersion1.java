package com.example.learningapplication.main.designmodel.orderModle.waiter;

import com.example.learningapplication.main.designmodel.orderModle.command.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WaiterVersion1 {

    private final HashMap<String, List<Command>> commandsMap = new HashMap<>();

    public void setOrder(String customerId, Command command) {
        commandsMap.computeIfAbsent(customerId, k -> new ArrayList<>());
        if (commandsMap.get(customerId) != null) {
            commandsMap.get(customerId).add(command);
        }
    }

    public void cancelOrder(String customerId, Command command) {
        System.out.println("顾客"+customerId+"取消订单:"+command.toString());
        if (commandsMap.get(customerId) != null) {
            commandsMap.get(customerId).remove(command);
        }
    }

    public void notifyOrders(String customerId) {
        commandsMap.forEach((id, commands) -> {
            if (id.equals(customerId)) {
                System.out.println(customerId);
                commands.forEach(Command::execute);
            }
        });
    }

    public void notifyAllOrders() {
        commandsMap.forEach((id, commands) -> {
            System.out.println(id);
            commands.forEach(Command::execute);
        });
    }
}
