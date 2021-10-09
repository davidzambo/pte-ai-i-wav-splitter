package io.dcmf.audiosplitter.service;

import io.dcmf.audiosplitter.wordlist.WordListReader;
import io.dcmf.audiosplitter.wordlist.WordListReaderImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class AudioSplitterServiceImpl implements AudioSplitterService {
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());
    protected AudioProcessorService audioProcessorService = new AudioProcessorServiceImpl();

    @Override
    public void split(String wordListFilepath, String audioFilePath) {
        WordListReader wordListReader = new WordListReaderImpl();

        try {
            final List<String> words = wordListReader.readWords(new File(wordListFilepath));
            audioProcessorService.process(words, new File(audioFilePath));

        } catch (IOException e) {
            logger.warning(e.getMessage());
            System.err.println(e.getMessage());
        }
    }
}
