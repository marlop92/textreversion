package pl.mlopatka.reversion;

import pl.mlopatka.exception.InvalidCharacterException;
import pl.mlopatka.reversion.splitter.WordsSplitter;

import java.util.Stack;

import static java.lang.Character.isLetter;

public class SeparateWordsReversion implements ReversionStrategy {

    private static final String INVALID_CHARACTER = "Text reversion does not accept '%c' character";
    private WordsSplitter splitter;

    public SeparateWordsReversion(WordsSplitter splitter) {
        this.splitter = splitter;
    }

    public String reverse(String text) {
        Stack<Character> letters = new Stack<Character>();
        StringBuffer buffer = new StringBuffer();

        for (char letter : text.toCharArray()) {
            if (isLetter(letter)) {
                letters.push(letter);
                continue;
            }

            if (splitter.isSplitter(letter)) {
                StringBuffer reversedWord = getReversedWord(letters);
                buffer.append(reversedWord);
                buffer.append(splitter.getSplitter());
                continue;
            }

            throw new InvalidCharacterException(String.format(INVALID_CHARACTER, letter));
        }

        buffer.append(getReversedWord(letters));

        return buffer.toString();
    }

    private StringBuffer getReversedWord(Stack<Character> letters) {
        StringBuffer reversedWord = new StringBuffer();
        while (!letters.isEmpty()) {
            reversedWord.append(letters.pop());
        }

        return reversedWord;
    }
}
