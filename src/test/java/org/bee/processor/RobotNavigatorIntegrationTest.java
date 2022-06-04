package org.bee.processor;

import org.bee.command.DefaultCommandBuilder;
import org.bee.input.InputProcessor;
import org.bee.input.file.TextFileInputProcessor;
import org.bee.input.stdin.StdInputProcessor;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;
import org.bee.output.file.TextFileOutputProcessor;
import org.bee.output.stdout.StdOutputProcessor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.testng.Assert.assertEquals;

public class RobotNavigatorIntegrationTest {

    String inputFilePath = "src/test/resources/input.txt";
    String outputFilePath = "src/test/resources/output.txt";
    String expectedOutputFilePath = "src/test/resources/expected_output.txt";

    @Test
    public void testNavigateFileIO() throws IOException {
        InputProcessor inputProcessor = new TextFileInputProcessor(Path.of(inputFilePath));
        OutputProcessor outputProcessor = new TextFileOutputProcessor(Path.of(outputFilePath));
        Table table = new Table(5);
        RobotNavigator robotNavigator = new RobotNavigator(inputProcessor, outputProcessor,
                new DefaultCommandBuilder(), table);
        robotNavigator.navigate();

        InputProcessor outputFileInputProcessor = new TextFileInputProcessor(Path.of(outputFilePath));
        InputProcessor expectedOutputFileInputProcessor = new TextFileInputProcessor(Path.of(expectedOutputFilePath));

        String expectedLine;
        while ((expectedLine = expectedOutputFileInputProcessor.readNextLine()) != null) {
            assertEquals(outputFileInputProcessor.readNextLine(), expectedLine);
        }
    }

    @Test
    public void testNavigateStdIO() throws IOException {
        System.setIn(new FileInputStream(inputFilePath));
        System.setOut(new PrintStream(outputFilePath));

        InputProcessor inputProcessor = new StdInputProcessor();
        OutputProcessor outputProcessor = new StdOutputProcessor();
        Table table = new Table(5);
        RobotNavigator robotNavigator = new RobotNavigator(inputProcessor, outputProcessor,
                new DefaultCommandBuilder(), table);
        robotNavigator.navigate();

        InputProcessor outputFileInputProcessor = new TextFileInputProcessor(Path.of(outputFilePath));
        InputProcessor expectedOutputFileInputProcessor = new TextFileInputProcessor(Path.of(expectedOutputFilePath));

        String expectedLine;
        while ((expectedLine = expectedOutputFileInputProcessor.readNextLine()) != null) {
            assertEquals(outputFileInputProcessor.readNextLine(), expectedLine);
        }
    }

    @AfterTest
    public void cleanup() {
        new File(outputFilePath).delete();
    }
}