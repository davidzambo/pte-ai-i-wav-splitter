package io.dcmf.audiosplitter.service;

import java.io.File;
import java.util.List;

public interface AudioProcessorService {
    List<String> process(List<String> words, File file);
}
