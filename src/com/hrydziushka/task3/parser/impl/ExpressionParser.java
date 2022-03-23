package com.hrydziushka.task3.parser.impl;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.interpreter.Context;
import com.hrydziushka.task3.interpreter.MathExpression;
import com.hrydziushka.task3.interpreter.PolishNotationInterpreter;
import com.hrydziushka.task3.parser.TextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.hrydziushka.task3.interpreter.MathOperation.*;

public class ExpressionParser implements TextParser {
    static final Logger logger = LogManager.getLogger();
    private static final String SYMBOL_DELIMITER = "";
    private static final String OPERATORS = "+-*/";
    private static final String MATH_DELIMITERS = "()" + OPERATORS;
    private static final int FIRST_PRIORITY = 1;
    private static final int SECOND_PRIORITY = 2;
    private static final int THIRD_PRIORITY = 3;
    private static final int FOURTH_PRIORITY = 4;
    public static boolean flag = true;

    private Context context;
    private TextParser successor;

    public ExpressionParser() {
        this.successor = new SymbolParser();
        this.context = new Context();
    }


    private static int priority(String token) {
        if (token.equals(OPENING_BRACKET)) {
            return FIRST_PRIORITY;
        }
        if (token.equals(PLUS) || token.equals(MINUS)) {
            return SECOND_PRIORITY;
        }
        if (token.equals(MULTIPLY) || token.equals(DIVIDE)) {
            return THIRD_PRIORITY;
        }
        return FOURTH_PRIORITY;
    }

    private static List<String> parseExpressionToPolishNotation(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, MATH_DELIMITERS, true);
        String prev = null;
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && OPERATORS.contains(curr)) {
                logger.log(Level.INFO, "Incorrect expression");
                flag = false;
                return postfix;
            } else if (MATH_DELIMITERS.contains(curr)) {
                if (curr.equals(OPENING_BRACKET)) stack.push(curr);
                else if (curr.equals(CLOSING_BRACKET)) {
                    while (!stack.peek().equals(OPENING_BRACKET)) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            logger.log(Level.INFO, "The brackets are not glossed over.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                } else {
                    if (curr.equals(MINUS) && (prev == null || (MATH_DELIMITERS.contains(prev) && !prev.equals(CLOSING_BRACKET)))) {
                        curr = UNAR_MINUS;
                    } else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(curr);
                }

            } else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (OPERATORS.contains(stack.peek())) postfix.add(stack.pop());
            else {
                logger.log(Level.INFO, "The brackets are not glossed over.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    @Override
    public TextComponent parse(String data) {
        logger.log(Level.INFO, "ExpressionParser start parsing lexeme into expression");
        TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
        TextComponent expressionComponent = new TextComposite(TextComponentType.EXPRESSION);
        List<String> pol = parseExpressionToPolishNotation(data);
        PolishNotationInterpreter interpreter = new PolishNotationInterpreter();
        List<MathExpression> expressions = interpreter.decode(pol);
        double result = handleExpression(expressions);
        for (String symbol : Double.toString(result).split(SYMBOL_DELIMITER)) {
            TextComponent symbolComponent = successor.parse(symbol);
            expressionComponent.add(symbolComponent);
        }

        lexemeComponent.add(expressionComponent);
        return lexemeComponent;
    }

    private double handleExpression(List<MathExpression> expressions) {
        for (MathExpression terminal : expressions) {
            terminal.interpret(context);
        }
        return context.pop();
    }

}
