package io.dcmf.audiosplitter.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static io.dcmf.audiosplitter.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AudioProcessorServiceImplTest {
    AudioProcessorService service = new AudioProcessorServiceImpl();

    @Test
    void splitToProperPieces() {
        int numberOfElements = 5;
        String audio = Path.of(WORKDIR, TEST_AUDIO_FILE_02).toString();
        try {
            int countOfSlices = service.process(createWordList(numberOfElements), new File(audio));
            assertEquals(numberOfElements, countOfSlices);
        } catch (Exception e) {
            assertNull(e);
        }
    }


}