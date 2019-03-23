package pl.mlopatka.reversion.splitter;

public class SpaceSplitter implements WordsSplitter {

    public boolean isSplitter(char letter) {
        return letter == ' ';
    }

    public char getSplitter() {
        return ' ';
    }
}
