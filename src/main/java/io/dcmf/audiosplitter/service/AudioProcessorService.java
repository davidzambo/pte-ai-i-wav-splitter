package io.dcmf.audiosplitter.service;

import java.io.File;
import java.util.List;

public interface AudioProcessorService {
    void process(List<String> words, File file);
}
