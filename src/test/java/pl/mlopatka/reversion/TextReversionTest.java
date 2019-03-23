package pl.mlopatka.reversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mlopatka.exception.InvalidTextException;
import pl.mlopatka.reversion.service.SimpleTextReversion;
import pl.mlopatka.reversion.service.TextReversion;
import pl.mlopatka.reversion.splitter.SpaceSplitter;
import pl.mlopatka.reversion.splitter.WordsSplitter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextReversionTest {

    TextReversion textReversion = new SimpleTextReversion();

    @ParameterizedTest
    @CsvSource({
            "abc def ghi, cba fed ihg",
            "hello stranger, olleh regnarts",
            "duel ul, leud lu",
            "ave cezar, eva razec",
            "do it quickly, od ti ylkciuq"
    })
    public void validTextShouldReturnReversedText(String text, String expected) {
        //given
        WordsSplitter spaceSplitter = new SpaceSplitter();
        ReversionStrategy strategy = new SeparateWordsReversion(spaceSplitter);

        //when
        String result = textReversion.reverse(strategy, text);

        //than
        assertEquals(expected, result);
    }

    @Test
    public void nullStringShouldThrowException() {
        //given
        String text = null;
        WordsSplitter spaceSplitter = new SpaceSplitter();
        ReversionStrategy strategy = new SeparateWordsReversion(spaceSplitter);

        //than
        assertThrows(InvalidTextException.class, () -> textReversion.reverse(strategy, text));
    }

}