package com.project.args;

import java.util.*;

public class ArgumentParser {
    private static final String OUTPUT_DIRECTORY_OPTION = "-o";
    private static final String PREFIX_OPTION = "-p";
    private static final String APPEND_OPTION = "-a";
    private static final String SHORT_STATS_OPTION = "-s";
    private static final String FULL_STATS_OPTION = "-f";

    private final Map<String,String> options;
    private final String[] inputFiles;

    public ArgumentParser(String[] args) {
        this.options = parseOptions(args);
        this.inputFiles = getInputFiles(args);
    }

    private Map<String, String> parseOptions(String[] args) {
        Map<String, String> options = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    options.put(arg, args[i + 1]);
                    i++;
                } else {
                    options.put(arg, null);
                }
            }
        }

        return options;
    }

    private String[] getInputFiles(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                inputFiles.add(args[i]);
            } else {
                i++;
            }
        }
        return inputFiles.toArray(new String[0]);
    }

    public String getOutputDirectory() {
        return options.getOrDefault(OUTPUT_DIRECTORY_OPTION, ".");
    }

    public String getPrefix() {
        return options.getOrDefault(PREFIX_OPTION, "");
    }

    public boolean isAppendMode() {
        return options.containsKey(APPEND_OPTION);
    }

    public boolean isShortStatsMode() {
        return options.containsKey(SHORT_STATS_OPTION);
    }

    public boolean isFullStatsMode() {
        return options.containsKey(FULL_STATS_OPTION);
    }

    public String[] getInputFiles() {
        return inputFiles;
    }
}