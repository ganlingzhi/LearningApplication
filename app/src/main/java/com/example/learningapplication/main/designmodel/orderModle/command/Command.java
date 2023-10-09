package com.example.learningapplication.main.designmodel.orderModle.command;

import com.example.learningapplication.main.designmodel.orderModle.barbecueguy.BarbecueGuy;

public abstract class Command {
    protected BarbecueGuy receiver;

    public abstract void execute();
}
