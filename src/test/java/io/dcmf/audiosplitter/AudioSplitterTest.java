package io.dcmf.audiosplitter;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static io.dcmf.audiosplitter.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AudioSplitterTest extends AbstractSystemConsoleTest {
    @Test
    void noArgs() {
        AudioSplitter.main(new String[]{});
        assertTrue(errorStream.toString().startsWith("Usage"));
    }

    @Test
    void with20WordsList() {
        String wordList = Path.of(WORKDIR, TEST_WORDFILE_WITH_20_VALID_WORDS).toString();
        String audio = Path.of(WORKDIR, TEST_AUDIO_FILE_01).toString();
        AudioSplitter.main(new String[]{"-t", wordList, "-a", audio});
        assertEquals("", errorStream.toString());
        assertEquals("", outputStream.toString());
        assertTrue(errorStream.toString().isBlank());
        assertTrue(outputStream.toString().isBlank());
    }
}