package io.dcmf.wordlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordValidatorImplTest {
    WordValidator validator = new WordValidatorImpl();

    @Test
    void failOnEmptyString() {
        assertFalse(validator.isWord(""));
    }

    @Test
    void failOnNull() {
        assertFalse(validator.isWord(null));
    }

    @Test
    void failOnSpaceWithIt() {
        assertFalse(validator.isWord("alm a"));
    }

    @Test
    void successOnSpaceWithTheEnd() {
        assertTrue(validator.isWord("alma "));
    }

    @Test
    void failOnTabWithIt() {
        assertFalse(validator.isWord("alm\ta"));
    }

    @Test
    void successOnTabWithTheEnd() {
        assertTrue(validator.isWord("alma\t"));
    }

    @Test
    void successOnMultipleTabWithTheEndAndBegin() {
        assertTrue(validator.isWord("\t\t\talma\t\t\t"));
    }

}