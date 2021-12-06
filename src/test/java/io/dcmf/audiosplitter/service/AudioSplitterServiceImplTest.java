package io.dcmf.audiosplitter.service;

import io.dcmf.audiosplitter.AbstractSystemConsoleTest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static io.dcmf.audiosplitter.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AudioSplitterServiceImplTest extends AbstractSystemConsoleTest {
    AudioSplitterService service = new AudioSplitterServiceImpl();

    @Test
    void splitWordsNonExistingFile() {
        service.split(NOT_EXISTING_FILE, NOT_EXISTING_FILE);
        assertTrue(errorStream.toString().contains("No such file"));
    }

    @Test
    void splitWordsSuccess() {
        String wordList = Path.of(WORKDIR, TEST_WORDLIST_01).toString();
        String audioFile = Path.of(WORKDIR, TEST_AUDIO_FILE_02).toString();
        service.split(wordList, audioFile);
        assertTrue(errorStream.toString().isBlank());
        assertTrue(outputStream.toString().isBlank());
    }
}