package com.project.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InputDataHandler {
    private final String[] inputFiles;

    public InputDataHandler(String[] inputFiles) {
        this.inputFiles = inputFiles;
    }

    public Map<InputDataType, Set<String>> readInputData() {
        Map<InputDataType, Set<String>> inputData = new HashMap<>();

        for (String inputFile : inputFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    InputDataType dataType = classifyData(line);
                    inputData.computeIfAbsent(dataType, k -> new HashSet<>()).add(line);
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
        } else if (data.matches("-?\\d+\\.\\d+")) {
            return InputDataType.FLOAT;
        } else {
            return InputDataType.STRING;
        }
    }
}