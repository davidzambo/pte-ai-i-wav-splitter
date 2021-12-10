package io.dcmf.audiosplitter.service;

import com.github.psambit9791.wavfile.WavFile;
import com.github.psambit9791.wavfile.WavFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AudioProcessorServiceImpl implements AudioProcessorService {
    private static final boolean DEVELOPMENT = true;
    public static final String OUTPUT_DIRECTORY = "chunks";
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public int process(List<String> words, File file) {
        int countOfWords = words.size();
        int fileNameCounter = 0;

        try (FileOutputStream fileOutputStream = new FileOutputStream("data.dat")) {
            WavFile wavFile = WavFile.openWavFile(file);

            final int numberOfFramesToRead = (int) (wavFile.getSampleRate() / 10);

            double[] buffer = new double[numberOfFramesToRead * wavFile.getNumChannels()];

            int framesRead;

            final int scale = 65536;
            String word = words.get(0);
            ensureOutputDirectoryExists();
            WavFile wavChunk = getNewWavFile(wavFile, ++fileNameCounter, word);

            boolean shouldDump = false;
            boolean inWord = false;

            List<Integer> rmsList = getRMSList(wavFile, numberOfFramesToRead, buffer, scale);

            int readIteration = 0;
            List<Integer> readedRMSList = new ArrayList<>();
            int leastSilentFrameLength = getLeastAdjacentSilentFramesLength(countOfWords, rmsList);
            wavFile = WavFile.openWavFile(file);

            do {
                framesRead = wavFile.readFrames(buffer, 0, numberOfFramesToRead);

                int currentRMS = (int) volumeRMS(buffer, scale);
                readedRMSList.add(currentRMS);

                int currentAmplitude = (int) buffer[0] * scale;

                if (DEVELOPMENT)
                    fileOutputStream.write(
                            String.format("%d\t%d\t%d%n", readIteration++, currentAmplitude, currentRMS)
                                    .getBytes(StandardCharsets.UTF_8)
                    );

                if (!inWord && currentRMS > 0) {
                    inWord = true;
                }

                if (inWord && currentRMS < 1 && !ensureInWordSilence(readedRMSList, leastSilentFrameLength)) {
                    shouldDump = true;
                }

                wavChunk.writeFrames(buffer, numberOfFramesToRead);

                if (shouldDump) {
                    wavChunk.close();
                    wavChunk = getNewWavFile(wavFile, ++fileNameCounter, getChunkName(words, fileNameCounter - 1));
                    shouldDump = false;
                    inWord = false;
                }
            }
            while (framesRead != 0);
        } catch (IOException | WavFileException e) {
            logger.warning(e.getMessage());
        }
        return fileNameCounter - 1;
    }

    private String getChunkName(List<String> words, int i) {
        return i < words.size() ? words.get(i) : "";
    }

    private void ensureOutputDirectoryExists() throws FileNotFoundException {
        File file = new File(OUTPUT_DIRECTORY);
        if (file.isDirectory()) {
            return;
        }
        boolean isCreated = file.mkdir();
        if (!isCreated) {
            throw new FileNotFoundException("Failed to create output folder: " + OUTPUT_DIRECTORY);
        }

    }

    private Integer getLeastAdjacentSilentFramesLength(int countOfWords, List<Integer> rmsList) {
        return getAdjacentSilentFrameLengthList(rmsList)
                .stream()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList())
                .get(countOfWords - 1);
    }

    private List<Integer> getAdjacentSilentFrameLengthList(List<Integer> rmsList) {
        List<Integer> silentFrameLengthList = new ArrayList<>();

        int adjacentSilences = 0;

        for (Integer val : rmsList) {
            if (val == 0) {
                adjacentSilences++;
                continue;
            }
            if (adjacentSilences > 0) {
                silentFrameLengthList.add(adjacentSilences);
                adjacentSilences = 0;
            }
        }

        return silentFrameLengthList;
    }

    private List<Integer> getRMSList(WavFile wavFile, int numFramesToRead, double[] buffer, int scale) throws IOException, WavFileException {
        List<Integer> rmsList = new ArrayList<>();

        while (wavFile.readFrames(buffer, numFramesToRead) != 0) {
            rmsList.add((int) volumeRMS(buffer, scale));
        }

        return rmsList;
    }

    private boolean ensureInWordSilence(List<Integer> rmsList, int maxSilenceLength) {
        int size = rmsList.size();

        for (int i = 1; i < size && i <= maxSilenceLength + 1; i++) {
            if (rmsList.get(size - i) > 0) {
                return true;
            }
        }

        return false;
    }

    private WavFile getNewWavFile(WavFile wavFile, int fileNameCounter, String chunkName) throws IOException, WavFileException {
        String filename = String.format("%03d_%s.wav", fileNameCounter, chunkName);
        return WavFile.newWavFile(
                new File(Paths.get(OUTPUT_DIRECTORY, filename).toString()),
                wavFile.getNumChannels(),
                wavFile.getNumFrames(),
                wavFile.getValidBits(),
                wavFile.getSampleRate()
        );
    }

    public double volumeRMS(double[] raw, int scale) {
        double sum = 0d;
        if (raw.length == 0) {
            return sum;
        }

        for (double v : raw) {
            sum += v * scale;
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
