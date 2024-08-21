package com.project;

import com.project.args.ArgumentParser;
import com.project.input.InputDataHandler;
import com.project.output.OutputDataWriter;

public class Main {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        InputDataHandler inputDataHandler = new InputDataHandler(argumentParser.getInputFiles());
        OutputDataWriter outputDataWriter = new OutputDataWriter(argumentParser, inputDataHandler);
        outputDataWriter.writeOutputData();
    }
}