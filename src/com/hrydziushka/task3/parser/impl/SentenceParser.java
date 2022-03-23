package com.hrydziushka.task3.parser.impl;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.parser.TextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {
    static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).*?[.?!…]";

    private TextParser successor;

    public SentenceParser() {
        successor = new LexemeParser();
    }

    @Override
    public TextComponent parse(String data) {
        logger.log(Level.INFO, "SentenceParser start parsing paragraph into sentences");

        TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String sentenceData = matcher.group();
            TextComponent sentenceComponent = successor.parse(sentenceData);
            paragraphComponent.add(sentenceComponent);
        }

        return paragraphComponent;
    }
}
