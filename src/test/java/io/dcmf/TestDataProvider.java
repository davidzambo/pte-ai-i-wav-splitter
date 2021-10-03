package io.dcmf;

import java.nio.file.Paths;

public class TestDataProvider {
    public static final String NOT_EXISTING_FILE = "nope.txt";
    public static final String TEST_WORDFILE_WITH_20_VALID_WORDS = "wordlist01.txt";
    public static final String TEST_AUDIO_FILE = "audio01.txt";
    public static final String WORKDIR = Paths.get("src", "test", "resources").toString();
}
