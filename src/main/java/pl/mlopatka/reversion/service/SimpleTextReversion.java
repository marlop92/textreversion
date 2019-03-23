package pl.mlopatka.reversion.service;

import pl.mlopatka.exception.InvalidTextException;
import pl.mlopatka.reversion.ReversionStrategy;

public class SimpleTextReversion implements TextReversion {

    public static final String EMPTY_WORD = "Word can not be null";

    public String reverse(ReversionStrategy strategy, String text) {
        if(text == null){
            throw new InvalidTextException(EMPTY_WORD);
        }

        return strategy.reverse(text);
    }
}
