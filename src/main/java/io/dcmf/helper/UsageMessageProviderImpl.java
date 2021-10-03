package io.dcmf.helper;

import io.dcmf.AudioSplitter;

public class UsageMessageProviderImpl implements UsageMessageProvider {
    private final StringBuilder message;

    public UsageMessageProviderImpl() {
        this.message = new StringBuilder("Usage:");
        buildUsageMessage();
    }

    private void buildUsageMessage() {
        addBasicUsageCommand();
        addFileBaseRequirements();

    }

    private void addFileBaseRequirements() {
        message.append("\t SOURCE_OF_WORDS.TXT should be a word list separated to new lines\n");
        message.append("\t AUDIO.WAV should be an uncompressed WAV audio file\n");
    }

    private void addBasicUsageCommand() {
        message.append("\tjava -jar ")
                .append(AudioSplitter.class.getSimpleName())
                .append(" -t SOURCE_OF_WORDS.TXT")
                .append(" -a AUDIO.WAV\n");
    }


    @Override
    public String getMessage() {
        return message.toString();
    }
}
