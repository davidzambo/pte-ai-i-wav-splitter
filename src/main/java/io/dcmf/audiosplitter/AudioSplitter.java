package io.dcmf.audiosplitter;

import io.dcmf.audiosplitter.helper.ArgsInterpreter;
import io.dcmf.audiosplitter.helper.UsageMessageProviderImpl;
import io.dcmf.audiosplitter.service.AudioSplitterService;
import io.dcmf.audiosplitter.service.AudioSplitterServiceImpl;

public class AudioSplitter {

    public static void main(String[] args) {
        try {
            ArgsInterpreter interpreter = new ArgsInterpreter(args);

            AudioSplitterService service = new AudioSplitterServiceImpl();
            service.split(interpreter.getWordListSource(), interpreter.getAudioSource());

        } catch (Exception e) {
            System.err.println((new UsageMessageProviderImpl()).getMessage());
        }
    }
}
