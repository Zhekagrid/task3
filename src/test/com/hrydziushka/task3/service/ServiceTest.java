package test.com.hrydziushka.task3.service;

import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.parser.TextParser;
import com.hrydziushka.task3.parser.impl.ParagraphParser;
import com.hrydziushka.task3.service.TextComponentService;
import com.hrydziushka.task3.service.impl.TextComponentServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

public class ServiceTest {
    private static final String VOWELS_KEY = "VOWELS";
    private static final String CONSONANTS_KEY = "Consonants";
    private TextComponent text;
    private TextComponent shortText;
    private TextComponent textForWordCount;
    private TextComponentService textComponentService;

    @BeforeClass
    public void setUp() {
        textComponentService = new TextComponentServiceImpl();
        TextParser parser = new ParagraphParser();
        text = parser.parse("    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. Abc.\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout…");
        textForWordCount = parser.parse("\tAbc abc bc. Abcd!\n" + "\tBcd. Bc bcd.");
        List<TextComponent> firstParagraph = new ArrayList<>();
        firstParagraph.add(text.getChildren().get(0));
        shortText = new TextComposite(TextComponentType.TEXT, firstParagraph);

    }

    @AfterClass
    public void tierDown() {
        text = null;
        textComponentService = null;
    }

    @Test
    public void deleteSentencesWithLowCountWordTest() {
        List<TextComponent> sentences = textComponentService.deleteSentenceWithLowCountWords(text, 20);
        String actual = sentences.toString();

        StringBuilder stringBuilder = new StringBuilder("[It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ,");
        stringBuilder.append(" It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. ,");
        stringBuilder.append(" The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. ]");
        String expected = stringBuilder.toString();

        assertEquals(actual, expected);

    }

    @Test
    public void countVowelAndConsonantsTest() {
        Map<String, Long> actual = textComponentService.countVowelAndConsonants(shortText);
        Map<String, Long> expected = new TreeMap<>();
        expected.put(VOWELS_KEY, 108L);
        expected.put(CONSONANTS_KEY, 163L);
        assertEquals(actual, expected);
    }

    @Test
    public void sortParagraphTest() {
        TextComponent sortedText = textComponentService.sortBySentencesNumber(text);
        String actual = sortedText.toString();
        String expected = "\tIt is a established fact that a reader will be of a page when looking at its layout. \n" +
                "\tBye. \n" +
                "\tIt is a established fact that a reader will be of a page when looking at its layout… \n" +
                "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. Abc. \n";

        assertEquals(actual, expected);
    }

    @Test
    public void findWordCountTest() {
        Map<String, Long> actual = textComponentService.findWordCount(textForWordCount);
        Map<String, Long> expected = new TreeMap<>();
        expected.put("Abc", 2L);
        expected.put("Abcd", 1L);
        expected.put("bc", 2L);
        expected.put("Bcd", 2L);
        assertEquals(actual, expected);
    }

    @Test
    public void foundSentenceWithLongestWordTest() {
        List<TextComponent> sentences = textComponentService.sentencesWithLongestWord(text);
        String actual = sentences.toString();
        String expected = "[The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. ]";


        assertEquals(actual, expected);
    }

}
