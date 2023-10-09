package com.example.learningapplication.main.designmodel.orderModle.command;

import androidx.annotation.NonNull;

import com.example.learningapplication.main.designmodel.orderModle.barbecueguy.BarbecueGuy;

public class BarbecueChickenWingCommand extends Command {

    private int count = 1;

    public BarbecueChickenWingCommand(BarbecueGuy receiver) {
        this.receiver = receiver;
    }

    public BarbecueChickenWingCommand(BarbecueGuy receiver, int count) {
        this.receiver = receiver;
        this.count = count;
    }

    @Override
    public void execute() {
        receiver.barbecueChickenWing(count);
    }

    @NonNull
    @Override
    public String toString() {
        return "烤" + count + "串鸡翅";
    }
}

