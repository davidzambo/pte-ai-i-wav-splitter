package io.dcmf.wordlist;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface WordListReader {
    List<String> readWords(File file) throws IOException;
}
