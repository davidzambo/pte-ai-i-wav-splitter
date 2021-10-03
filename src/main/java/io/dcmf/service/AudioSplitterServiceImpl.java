package io.dcmf.service;

import io.dcmf.wordlist.WordListReader;
import io.dcmf.wordlist.WordListReaderImpl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class AudioSplitterServiceImpl implements AudioSplitterService {
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public void split(String wordListFilepath, String audioFilePath) {
        WordListReader wordListReader = new WordListReaderImpl();

        try {
            wordListReader.readWords(new File(wordListFilepath));
        } catch (IOException e) {
            logger.warning(e.getMessage());
            System.err.println(e.getMessage());
        }
    }
}
