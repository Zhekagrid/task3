package com.hrydziushka.task3.parser.impl;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordAndPunctuationParser implements TextParser {
    static final Logger logger = LogManager.getLogger();
    private static final String DELIMITER = "";
    private static final String WORD_OR_PUNCTUATION_REGEX = "([\\p{Alpha}А-я]+)|([\\p{Punct}“”…])";


    private final TextParser successor;


    public WordAndPunctuationParser() {
        successor = new SymbolParser();
    }

    @Override
    public TextComponent parse(String data) {
        TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
        Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);


        while (matcher.find()) {
            String wordOrPunctuation = matcher.group();
            if (matcher.group(1) != null) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                List<String> symbols = Arrays.stream(wordOrPunctuation.split(DELIMITER)).toList();
                for (String symbol : symbols) {
                    TextComponent symbolComponent = successor.parse(symbol);
                    wordComponent.add(symbolComponent);
                }
                lexemeComponent.add(wordComponent);
            } else {
                TextComponent symbolComponent = successor.parse(wordOrPunctuation);
                lexemeComponent.add(symbolComponent);
            }


        }


        return lexemeComponent;
    }
}
