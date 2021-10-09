package io.dcmf.audiosplitter.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static io.dcmf.audiosplitter.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AudioProcessorServiceImplTest {
    AudioProcessorService service = new AudioProcessorServiceImpl();

    @Test
    void splitToProperPieces() {
        int numberOfElements = 5;
        String audio = Path.of(WORKDIR, TEST_AUDIO_FILE_02).toString();
        final List<String> chunks = service.process(createWordList(numberOfElements), new File(audio));
        assertEquals(numberOfElements, chunks.size());
    }


}