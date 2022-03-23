package com.hrydziushka.task3.parser.impl;

import com.hrydziushka.task3.entity.Symbol;
import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.parser.TextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser implements TextParser {
    static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String data) {
        logger.log(Level.INFO, "SymbolParser parse symbol");
        TextComponent symbol;
        if (Character.isLetter(data.charAt(0))) {
            symbol = new Symbol(TextComponentType.LETTER, data.charAt(0));
        } else if (Character.isDigit(data.charAt(0))) {
            symbol = new Symbol(TextComponentType.DIGIT, data.charAt(0));
        } else {
            symbol = new Symbol(TextComponentType.PUNCTUATION, data.charAt(0));
        }
        return symbol;
    }
}
