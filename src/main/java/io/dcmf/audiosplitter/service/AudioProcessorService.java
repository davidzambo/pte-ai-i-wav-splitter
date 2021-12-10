package io.dcmf.audiosplitter.service;

import java.io.File;
import java.util.List;

public interface AudioProcessorService {
    int process(List<String> words, File file);
}
