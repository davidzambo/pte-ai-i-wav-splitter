package io.dcmf;

import io.dcmf.helper.UsageMessageProviderImpl;
import io.dcmf.service.AudioSplitterService;
import io.dcmf.service.AudioSplitterServiceImpl;

public class AudioSplitter {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println((new UsageMessageProviderImpl()).getMessage());
            return;
        }

        AudioSplitterService service = new AudioSplitterServiceImpl();
        service.split(args[1], args[3]);
    }
}
