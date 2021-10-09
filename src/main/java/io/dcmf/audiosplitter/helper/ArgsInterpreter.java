package io.dcmf.audiosplitter.helper;

import java.util.Objects;

public class ArgsInterpreter {
    private String audioSource;
    private String wordListSource;

    public ArgsInterpreter(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a") && (i + 1 < args.length) && Objects.isNull(audioSource)) {
                audioSource = args[i++ + 1];
                continue;
            }

            if (args[i].equals("-d") && (i + 1 < args.length) && Objects.isNull(wordListSource)) {
                wordListSource = args[i++ + 1];
            }
        }

        if (Objects.isNull(audioSource) || Objects.isNull(wordListSource)) {
            throw new IllegalArgumentException();
        }
    }

    public String getAudioSource() {
        return audioSource;
    }

    public String getWordListSource() {
        return wordListSource;
    }
}
