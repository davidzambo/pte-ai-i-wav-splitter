package io.dcmf.audiosplitter.wordlist;

import java.util.Objects;

public class WordValidatorImpl implements WordValidator {
    @Override
    public boolean isWord(String word) {
        return !(Objects.isNull(word)
                || word.isBlank()
                || word.trim().contains(" ")
                || word.trim().contains("\t")
        );
    }
}
