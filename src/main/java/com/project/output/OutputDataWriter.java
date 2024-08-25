package com.project.output;

import com.project.args.ArgumentParser;
import com.project.input.InputDataHandler;
import com.project.input.InputDataType;
import com.project.stats.Statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OutputDataWriter {
    private final ArgumentParser argumentParser;
    private final InputDataHandler inputDataHandler;
    private final Statistics statistics;

    public OutputDataWriter(ArgumentParser argumentParser, InputDataHandler inputDataHandler, Statistics statistics) {
        this.argumentParser = argumentParser;
        this.inputDataHandler = inputDataHandler;
        this.statistics = statistics;
    }

    public void writeOutputData() {
        String outputDirectory = argumentParser.getOutputDirectory();
        String prefix = argumentParser.getPrefix();

        Map<InputDataType, Set<String>> inputData = inputDataHandler.readInputData();

        for (Map.Entry<InputDataType, Set<String>> entry : inputData.entrySet()) {
            InputDataType dataType = entry.getKey();
            Set<String> data = entry.getValue();

            if (!data.isEmpty()) {
                String outputFileName = generateOutputFileName(prefix, dataType);
                Path outputFilePath = Paths.get(outputDirectory, outputFileName);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {
                    for (String line : data) {
                        writer.write(line);
                        writer.newLine();
                        if (dataType == InputDataType.INTEGER) {
                            statistics.updateNumberStatistics(line, dataType);
                        } else if (dataType == InputDataType.FLOAT) {
                            statistics.updateNumberStatistics(line, dataType);
                        } else if (dataType == InputDataType.STRING) {
                            statistics.updateStringStatistics(line);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error writing data to file: " + outputFilePath, e);
                }
            }
        }

        statistics.printStatistics();
    }

    private String generateOutputFileName(String prefix, InputDataType dataType) {
        String baseName = dataType.name().toLowerCase() + ".txt";
        return prefix.isEmpty() ? baseName : prefix + baseName;
    }
}