package com.project.input;

import com.project.stats.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InputDataHandler {
    private final String[] inputFiles;
    private final Statistics statistics;

    public InputDataHandler(String[] inputFiles, Statistics statistics) {
        this.inputFiles = inputFiles;
        this.statistics = statistics;
    }

    public Map<InputDataType, Set<String>> readInputData() {
        Map<InputDataType, Set<String>> inputData = new HashMap<>();

        for (String inputFile : inputFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    InputDataType dataType = classifyData(line);
                    inputData.computeIfAbsent(dataType, k -> new HashSet<>()).add(line);
                    updateStatistics(dataType, line);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error reading input file: " + inputFile, e);
            }
        }

        return inputData;
    }

    private InputDataType classifyData(String data) {
        if (data.matches("-?\\d+")) {
            return InputDataType.INTEGER;
        } else if (data.matches("-?\\d+\\.\\d+([Ee]-?\\d+)?")) {
            return InputDataType.FLOAT;
        } else {
            return InputDataType.STRING;
        }
    }

    private void updateStatistics(InputDataType dataType, String data) {
        if (dataType == InputDataType.INTEGER || dataType == InputDataType.FLOAT) {
            statistics.updateNumberStatistics(data, dataType);
        } else if (dataType == InputDataType.STRING) {
            statistics.updateStringStatistics(data);
        }
        statistics.incrementCount();
    }
}