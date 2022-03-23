package com.hrydziushka.task3.service.impl;


import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.service.TextComponentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TextComponentServiceImpl implements TextComponentService {
    static final Logger logger = LogManager.getLogger();
    private static final String VOWELS = "([aeiouyауоиэыяюеё])";
    private static final String VOWELS_KEY = "VOWELS";
    private static final String CONSONANTS_KEY = "Consonants";

    public TextComposite sortBySentencesNumber(TextComponent text) {
        logger.log(Level.INFO, "Service sort sentences, by sentence number");
        return new TextComposite(TextComponentType.TEXT, text.getChildren().stream()
                .sorted(Comparator.comparingInt(p -> p.getChildren().size())).toList());

    }

    public Map<String, Long> findWordCount(TextComponent text) {
        logger.log(Level.INFO, "Service find word count");
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.getTextComponentType().equals(TextComponentType.WORD))
                .collect(Collectors.groupingBy(TextComponent::toString, () -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER), Collectors.counting()));


    }

    public List<TextComponent> deleteSentenceWithLowCountWords(TextComponent text, int count) {
        logger.log(Level.INFO, "Service delete sentences with low count words");

        return text.getChildren().stream().flatMap(p -> p.getChildren().stream())
                .filter(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .filter(l -> l.getTextComponentType().equals(TextComponentType.WORD))
                        .count() >= count)
                .toList();


    }

    public List<TextComponent> sentencesWithLongestWord(TextComponent text) {
        logger.log(Level.INFO, "Service find sentences with longest word");
        int maxSize = findLongestWordSize(text);
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .filter(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .anyMatch(l -> l.getTextComponentType().equals(TextComponentType.WORD) && l.getChildren().size() == maxSize))
                .toList();
    }

    public Map<String, Long> countVowelAndConsonants(TextComponent text) {
        logger.log(Level.INFO, "Service count vowel and consonants");
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.getTextComponentType().equals(TextComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .collect(Collectors.groupingBy(s -> s.toString().toLowerCase().matches(VOWELS) ? VOWELS_KEY : CONSONANTS_KEY, () -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER), Collectors.counting()));

    }

    private int findLongestWordSize(TextComponent text) {
        logger.log(Level.INFO, "Service find longest word size");
        TextComponent word = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.getTextComponentType().equals(TextComponentType.WORD))
                .max(Comparator.comparingInt(w -> w.getChildren().size())).get();

        return word.getChildren().size();
    }
}
