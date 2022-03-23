package com.hrydziushka.task3.service;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextComponentService {
    TextComposite sortBySentencesNumber(TextComponent text);

    Map<String, Long> findWordCount(TextComponent text);

    List<TextComponent> deleteSentenceWithLowCountWords(TextComponent text, int count);

    List<TextComponent> sentencesWithLongestWord(TextComponent text);

    Map<String, Long> countVowelAndConsonants(TextComponent text);
}
