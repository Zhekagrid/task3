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

public class ParagraphParser implements TextParser {
    static final Logger logger = LogManager.getLogger();
    private static final String DELIMITER = "\\n|\\t|\\s{4}";
    private TextParser successor;


    public ParagraphParser() {
        successor = new SentenceParser();
    }

    @Override
    public TextComponent parse(String data) {
        logger.log(Level.INFO, "ParagraphParser start parsing text into paragraphs");
        TextComponent textComponent = new TextComposite(TextComponentType.TEXT);
        List<String> paragraphs = Arrays.stream(data.split(DELIMITER)).filter(x -> !x.isBlank()).toList();
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = successor.parse(paragraph);
            textComponent.add(paragraphComponent);

        }
        return textComponent;
    }
}
