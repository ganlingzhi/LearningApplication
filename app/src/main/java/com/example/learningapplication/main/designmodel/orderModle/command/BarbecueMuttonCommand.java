package com.example.learningapplication.main.designmodel.orderModle.command;

import androidx.annotation.NonNull;

import com.example.learningapplication.main.designmodel.orderModle.barbecueguy.BarbecueGuy;

public class BarbecueMuttonCommand extends Command {
    private int count = 1;

    public BarbecueMuttonCommand(BarbecueGuy receiver) {
        this.receiver = receiver;
    }

    public BarbecueMuttonCommand(BarbecueGuy receiver, int count) {
        this.receiver = receiver;
        this.count = count;
    }

    @Override
    public void execute() {
        receiver.barbecueMutton(count);
    }

    @NonNull
    @Override
    public String toString() {
        return "烤" + count + "串羊肉串";
    }
}
