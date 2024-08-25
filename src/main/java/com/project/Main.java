package com.project;

import com.project.args.ArgumentParser;
import com.project.input.InputDataHandler;
import com.project.input.InputDataType;
import com.project.output.OutputDataWriter;
import com.project.stats.Statistics;

public class Main {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        Statistics integerStatistics = new Statistics(argumentParser.isFullStatsMode(), InputDataType.INTEGER, true);
        Statistics floatStatistics = new Statistics(argumentParser.isFullStatsMode(), InputDataType.FLOAT, true);
        Statistics stringStatistics = new Statistics(argumentParser.isFullStatsMode(), InputDataType.STRING, false);
        InputDataHandler integerDataHandler = new InputDataHandler(argumentParser.getInputFiles(), integerStatistics);
        InputDataHandler floatDataHandler = new InputDataHandler(argumentParser.getInputFiles(), floatStatistics);
        InputDataHandler stringDataHandler = new InputDataHandler(argumentParser.getInputFiles(), stringStatistics);
        OutputDataWriter integerDataWriter = new OutputDataWriter(argumentParser, integerDataHandler, integerStatistics);
        OutputDataWriter floatDataWriter = new OutputDataWriter(argumentParser, floatDataHandler, floatStatistics);
        OutputDataWriter stringDataWriter = new OutputDataWriter(argumentParser, stringDataHandler, stringStatistics);
        integerDataWriter.writeOutputData();
        floatDataWriter.writeOutputData();
        stringDataWriter.writeOutputData();
    }
}