package org.bee;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.bee.command.DefaultCommandBuilder;
import org.bee.exception.InvalidInputException;
import org.bee.input.InputProcessor;
import org.bee.input.file.TextFileInputProcessor;
import org.bee.input.stdin.StdInputProcessor;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;
import org.bee.output.file.TextFileOutputProcessor;
import org.bee.output.stdout.StdOutputProcessor;
import org.bee.processor.RobotNavigator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String SIDE = "side";
    private static final String DEBUG = "debug";
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String TEXT_FILE = "TextFile";
    private static final String STD_IN = "StdIn";
    private static final String STD_OUT = "StdOut";
    private static final String INPUT_FILE_PATH = "inputFilePath";
    private static final String OUTPUT_FILE_PATH = "outputFilePath";

    private static int tableSide = 5;

    public static void main(String[] args) throws InvalidInputException, IOException {

        Map<String, String> argMap = parseArguments(args);

        if (argMap.containsKey(DEBUG)) {
            setLogLevel(Level.DEBUG);
        } else {
            setLogLevel(Level.INFO);
        }

        if (argMap.containsKey(SIDE) && argMap.get(SIDE).matches("\\d+")) {
            tableSide = Integer.parseInt(argMap.get(SIDE));
        }

        Table table = new Table(tableSide);
        InputProcessor inputProcessor = getInputProcessor(argMap);
        OutputProcessor outputProcessor = getOutputProcessor(argMap);

        RobotNavigator robotNavigator = new RobotNavigator(inputProcessor, outputProcessor,
                new DefaultCommandBuilder(), table);
        robotNavigator.navigate();
    }

    protected static OutputProcessor getOutputProcessor(Map<String, String> argMap)
            throws IOException, InvalidInputException {
        OutputProcessor outputProcessor;
        if (argMap.containsKey(OUTPUT)) {
            if (argMap.get(OUTPUT).equalsIgnoreCase(TEXT_FILE)) { //Output to text file
                if (argMap.containsKey(OUTPUT_FILE_PATH)) { //filepath available
                    outputProcessor = new TextFileOutputProcessor(Paths.get(argMap.get(OUTPUT_FILE_PATH)));
                } else {
                    throw new InvalidInputException("--" + OUTPUT_FILE_PATH + "=?? argument is required.");
                }
            } else if (argMap.get(OUTPUT).equalsIgnoreCase(STD_OUT)) { //output to stdout
                outputProcessor = new StdOutputProcessor();
            } else {
                throw new InvalidInputException("Unsupported output format: " + argMap.get(OUTPUT));
            }
        } else {
            outputProcessor = new StdOutputProcessor();
        }
        return outputProcessor;
    }

    protected static InputProcessor getInputProcessor(Map<String, String> argMap)
            throws IOException, InvalidInputException {
        InputProcessor inputProcessor;
        if (argMap.containsKey(INPUT)) {
            if (argMap.get(INPUT).equalsIgnoreCase(TEXT_FILE)) { //input from text file
                if (argMap.containsKey(INPUT_FILE_PATH)) { //filepath available
                    inputProcessor = new TextFileInputProcessor(Paths.get(argMap.get(INPUT_FILE_PATH)));
                } else {
                    throw new InvalidInputException("--" + INPUT_FILE_PATH + "=?? argument is required.");
                }
            } else if (argMap.get(INPUT).equalsIgnoreCase(STD_IN)) { //input from stdin
                inputProcessor = new StdInputProcessor();
            } else {
                throw new InvalidInputException("Unsupported input format: " + argMap.get(INPUT));
            }
        } else {
            inputProcessor = new StdInputProcessor();
        }
        return inputProcessor;
    }

    protected static Map<String, String> parseArguments(String[] args) {
        Map<String, String> argMap = new HashMap<>();
        for (String arg : args) {
            if (arg.length() <= 2 || !arg.startsWith("--")) {
                continue;
            }
            String[] keyVal = arg.split("=");
            if (keyVal.length > 1) {
                argMap.put(keyVal[0].substring(2), keyVal[1]);
            } else {
                argMap.put(keyVal[0].substring(2), null);
            }
        }
        return argMap;
    }

    private static void setLogLevel(Level logLevel) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(LogManager.class.getClassLoader(), false);
        Configuration conf = ctx.getConfiguration();
        conf.getLoggerConfig("org.bee").setLevel(logLevel);
        ctx.updateLoggers(conf);
    }
}