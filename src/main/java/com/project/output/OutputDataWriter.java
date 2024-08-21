package com.project.output;

import com.project.args.ArgumentParser;
import com.project.input.InputDataHandler;
import com.project.input.InputDataType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OutputDataWriter {
    private final ArgumentParser argumentParser;
    private final InputDataHandler inputDataHandler;

    public OutputDataWriter(ArgumentParser argumentParser, InputDataHandler inputDataHandler) {
        this.argumentParser = argumentParser;
        this.inputDataHandler = inputDataHandler;
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
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error writing data to file: " + outputFilePath, e);
                }
            }
        }
    }

    private String generateOutputFileName(String prefix, InputDataType dataType) {
        String baseName = dataType.name().toLowerCase() + ".txt";
        return prefix.isEmpty() ? baseName : prefix + baseName;
    }
}