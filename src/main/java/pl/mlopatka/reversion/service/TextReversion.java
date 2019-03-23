package pl.mlopatka.reversion.service;

import pl.mlopatka.reversion.ReversionStrategy;

public interface TextReversion {

    String reverse(ReversionStrategy strategy, String text);
}
