package ru.otus.hw4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by rukbr on 26.04.2017.
 */
public class StatisticGC {
    private long totalDuration = 0;
    private int numberOfCalls = 0;
    private String name;
    Logger log = Logger.getLogger(StatisticGC.class.getName());

    public StatisticGC() {
        try {
            Path path = Paths.get("./logs");
            if (!Files.exists(path))
                Files.createDirectories(path);
            Handler handler = new FileHandler("./logs/Log.log", true);
            log.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTotalDuration(long lastDuration) {
        totalDuration += lastDuration;
    }

    public void updateNumberOfCalls() {
        numberOfCalls++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void log() {
        log.info(name + ", total duration(ms): " + totalDuration +
                ", number of calls: " + numberOfCalls);
    }

    @Override
    public String toString() {
        return name + ", total duration(ms): " + totalDuration +
                ", number of calls: " + numberOfCalls;
    }
}
