package io.dcmf.audiosplitter.service;

public interface AudioSplitterService {
    void split(String wordListFilepath, String audioFilePath);
}
