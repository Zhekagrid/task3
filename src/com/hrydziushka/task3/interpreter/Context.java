package com.hrydziushka.task3.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValue;

    public Context() {
        contextValue = new ArrayDeque<>();
    }

    public Double peek() {
        return contextValue.peek();
    }

    public void push(Double aDouble) {
        contextValue.push(aDouble);
    }

    public Double pop() {
        return contextValue.pop();
    }
}
