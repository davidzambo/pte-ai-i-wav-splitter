package io.dcmf.audiosplitter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {
    public static final String NOT_EXISTING_FILE = "nope.txt";
    public static final String TEST_WORDLIST_01 = "wordlist01.txt";
    public static final String TEST_WORDLIST_02 = "wordlist02.txt";
    public static final String TEST_AUDIO_FILE_01 = "audio01.wav";
    public static final String TEST_AUDIO_FILE_01_SHORT = "audio01_short.wav";
    public static final String TEST_AUDIO_FILE_02 = "audio02.wav";
    public static final String TEST_AUDIO_FILE_02_SHORT = "audio02_short.wav";
    public static final String WORKDIR = Paths.get("src", "test", "resources").toString();

    public static List<String> createWordList(int numberOfElements) {
        var words = new ArrayList<String>();

        if (numberOfElements < 1) {
            return words;
        }

        for (int i = 0; i < numberOfElements; i++) {
            words.add("test_" + i);
        }

        return words;
    }
}
