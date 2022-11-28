package com.example.userdetails.controllers;

public class InitResult {
    private long took;
    private long rate;
    private int current;

    public long getRate() {
        return rate;
    }

    public long getTook() {
        return took;
    }

    public int getCurrent() {
        return current;
    }

    public InitResult(long took, long rate, int current) {
        this.took = took;
        this.rate = rate;
        this.current = current;
    }
}
