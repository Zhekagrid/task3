package com.hrydziushka.task3.interpreter;

import java.util.ArrayList;
import java.util.List;

import static com.hrydziushka.task3.interpreter.MathOperation.*;

public class PolishNotationInterpreter {
    public List<MathExpression> decode(List<String> polishNotation) {
        List<MathExpression> expressions = new ArrayList<>();

        polishNotation.forEach(token -> {
            switch (token) {
                case PLUS -> expressions.add(c -> c.push(c.pop() + c.pop()));
                case MINUS -> expressions.add(c -> c.push(-c.pop() + c.pop()));
                case MULTIPLY -> expressions.add(c -> c.push(c.pop() * c.pop()));
                case DIVIDE -> expressions.add(c -> c.push(1 / c.pop() * c.pop()));
                case UNAR_MINUS -> expressions.add(c -> c.push(-c.pop()));
                default -> expressions.add(c -> c.push(Double.parseDouble(token)));
            }
        });
        return expressions;
    }
}
