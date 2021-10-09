package io.dcmf.audiosplitter.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsInterpreterTest {
    public static final String AUDIO_FILENAME = "audiotest.wav";
    public static final String WORDLIST_FILENAME = "wordlist.csv";

    String[] AUDIO_FIRST = new String[]{"-a", AUDIO_FILENAME, "-d", WORDLIST_FILENAME};
    String[] VIDEO_FIRST = new String[]{"-d", WORDLIST_FILENAME, "-a", AUDIO_FILENAME};
    String[] DUPLICATED_AUDIO = new String[]{"-a", AUDIO_FILENAME, "-a", WORDLIST_FILENAME};
    String[] MISSING_ARGUMENTS = new String[]{"-a", AUDIO_FILENAME};
    String[] TOO_MUCH_ARGUMENTS = new String[]{"-a", AUDIO_FILENAME, "-d", WORDLIST_FILENAME, "-e", "exception"};

    @Test
    void audioFileFirst() {
        ArgsInterpreter interpreter = new ArgsInterpreter(AUDIO_FIRST);
        assertEquals(AUDIO_FILENAME, interpreter.getAudioSource());
        assertEquals(WORDLIST_FILENAME, interpreter.getWordListSource());
    }

    @Test
    void videoFileFirst() {
        ArgsInterpreter interpreter = new ArgsInterpreter(VIDEO_FIRST);
        assertEquals(AUDIO_FILENAME, interpreter.getAudioSource());
        assertEquals(WORDLIST_FILENAME, interpreter.getWordListSource());
    }

    @Test
    void duplicatedAudioFile() {
        assertThrows(IllegalArgumentException.class, () -> new ArgsInterpreter(DUPLICATED_AUDIO));
    }

    @Test
    void notEnoughArguments() {
        assertThrows(IllegalArgumentException.class, () -> new ArgsInterpreter(MISSING_ARGUMENTS));
    }

    @Test
    void tooMuchArguments() {
        assertThrows(IllegalArgumentException.class, () -> new ArgsInterpreter(TOO_MUCH_ARGUMENTS));
    }
}
