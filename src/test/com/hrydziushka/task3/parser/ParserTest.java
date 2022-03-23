package test.com.hrydziushka.task3.parser;

import com.hrydziushka.task3.entity.Symbol;
import com.hrydziushka.task3.entity.TextComponent;
import com.hrydziushka.task3.entity.TextComponentType;
import com.hrydziushka.task3.entity.TextComposite;
import com.hrydziushka.task3.parser.TextParser;
import com.hrydziushka.task3.parser.impl.ExpressionParser;
import com.hrydziushka.task3.parser.impl.ParagraphParser;
import com.hrydziushka.task3.parser.impl.WordAndPunctuationParser;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParserTest {
    @Test
    public void parseAllTextTest() {
        TextParser parser = new ParagraphParser();
        TextComponent text = parser.parse("    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "    It is a -2+3 established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.");
        String actual = text.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -8.0 essentially 7.7142857142857135 unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. \n" +
                "\tIt is a 1.0 established fact that a reader will be of a page when looking at its layout. \n" +
                "\tBye. \n";
        assertEquals(actual, expected);

    }

    @Test
    public void parseExpressionTest() {
        TextParser parser = new ExpressionParser();
        TextComponent text = parser.parse("(-2+2--3)*(-3)");
        String actual = text.toString();
        String expected = "-9.0";
        assertEquals(actual, expected);
    }

    @Test
    public void parseWordAndPunctuationTest() {
        TextParser parser = new WordAndPunctuationParser();
        TextComponent actual = parser.parse("Abc.");
        TextComponent expected = new TextComposite(TextComponentType.LEXEME);
        TextComponent word = new TextComposite(TextComponentType.WORD);
        word.add(new Symbol(TextComponentType.LETTER, 'A'));
        word.add(new Symbol(TextComponentType.LETTER, 'b'));
        word.add(new Symbol(TextComponentType.LETTER, 'c'));
        expected.add(word);
        expected.add(new Symbol(TextComponentType.PUNCTUATION, '.'));
        assertEquals(actual, expected);
    }


}
