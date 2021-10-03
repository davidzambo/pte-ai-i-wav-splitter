package io.dcmf.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordListReaderImpl implements WordListReader {
    private final WordValidator wordValidator = new WordValidatorImpl();

    @Override
    public List<String> readWords(File file) throws IOException {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String word;

            while (isWord(word = reader.readLine())) {
                words.add(word.trim());
            }
        }
        return words;
    }

    private boolean isWord(String word) {
        return wordValidator.isWord(word);
    }
}
