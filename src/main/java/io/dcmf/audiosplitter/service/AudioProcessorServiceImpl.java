package io.dcmf.audiosplitter.service;

import com.github.psambit9791.wavfile.WavFile;
import com.github.psambit9791.wavfile.WavFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AudioProcessorServiceImpl implements AudioProcessorService {
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public List<String> process(List<String> words, File file) {
        List<String> chunks = new ArrayList<>();
        try {
            WavFile wavFile = WavFile.openWavFile(file);

            final long numberOfFrames = wavFile.getNumFrames();
            long sampleRate = wavFile.getSampleRate();

            final int numberOfChannels = wavFile.getNumChannels();

            final int numFramesToRead = (int) (sampleRate / 10);

            double[] buffer = new double[numFramesToRead * numberOfChannels];

            int framesRead;
            logger.info(String.format("%d || %d || %d", numberOfFrames, sampleRate, numberOfChannels));

            int rounds = 0;
            int countOfNull = 0;

            do {
                framesRead = wavFile.readFrames(buffer, numFramesToRead);
                double partialVolumeRMS = volumeRMS(buffer);
                if (partialVolumeRMS == 0) {
                    countOfNull++;
                }
                logger.info(String.format("%d %s %s", rounds++, partialVolumeRMS, (partialVolumeRMS > 0) ? " <---------------------" : ""));
            }
            while (framesRead != 0);

            logger.info(String.format("count of nulls: %d", countOfNull));

        } catch (IOException | WavFileException e) {
            e.printStackTrace();
        }
        return chunks;
    }

    public double volumeRMS(double[] raw) {
        double sum = 0d;
        if (raw.length == 0) {
            return sum;
        }

        for (double value : raw) {
            sum += value;
        }

        double average = sum / raw.length;

        double sumMeanSquare = 0d;

        for (double v : raw) {
            sumMeanSquare += Math.pow(v - average, 2d);
        }
        double averageMeanSquare = sumMeanSquare / raw.length;

        return Math.sqrt(averageMeanSquare);
    }
}
