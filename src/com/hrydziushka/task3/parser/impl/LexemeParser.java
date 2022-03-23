package com.hrydziushka.task3.parser.impl;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.parser.TextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    static final Logger logger = LogManager.getLogger();
    private static final String DELIMITER = " ";
    private static final String WORD_PUNCTUATION_REGEX = "[\\p{Alpha}\\p{Punct}А-Яа-я“”…]+";
    private TextParser expressionParser;
    private TextParser wordAndPunctuationParser;

    public LexemeParser() {
        expressionParser = new ExpressionParser();
        wordAndPunctuationParser = new WordAndPunctuationParser();
    }

    @Override
    public TextComponent parse(String data) {
        logger.log(Level.INFO, "LexemeParser start parsing sentence into lexemes");
        TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
        List<String> lexemes = Arrays.stream(data.split(DELIMITER)).toList();
        for (String lexeme : lexemes) {
            Pattern pattern = Pattern.compile(WORD_PUNCTUATION_REGEX);
            Matcher matcher = pattern.matcher(lexeme);
            TextComponent lexemeComponent;
            if (matcher.matches()) {
                lexemeComponent = wordAndPunctuationParser.parse(lexeme);
            } else {
                lexemeComponent = expressionParser.parse(lexeme);
            }
            sentenceComponent.add(lexemeComponent);

        }
        return sentenceComponent;
    }
}
