package io.dcmf.wordlist;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.dcmf.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.*;

class WordListReaderImplTest {
    WordListReader reader = new WordListReaderImpl();

    @Test
    void nonExistingFile() {
        assertThrows(IOException.class, () -> reader.readWords(new File(NOT_EXISTING_FILE)));
    }

    @Test
    void readValidTestFile() {
        try {
            final List<String> words = reader.readWords(new File(WORKDIR, TEST_WORDFILE_WITH_20_VALID_WORDS));
            assertEquals(20, words.size());
        } catch (IOException e) {
            assertNull(e);
        }
    }
}