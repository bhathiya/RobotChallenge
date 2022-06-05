package org.bee;

import org.bee.exception.InvalidInputException;
import org.bee.input.InputProcessor;
import org.bee.input.file.TextFileInputProcessor;
import org.bee.input.stdin.StdInputProcessor;
import org.bee.output.OutputProcessor;
import org.bee.output.file.TextFileOutputProcessor;
import org.bee.output.stdout.StdOutputProcessor;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainTest {

    @Test
    public void testGetInputProcessor() throws InvalidInputException, IOException {
        Map<String, String> argMap = new HashMap<>() {{
            put("input", "TextFile");
            put("inputFilePath", "/home/me/in.txt");
        }};

        try (MockedConstruction<TextFileInputProcessor> mocked = Mockito.mockConstruction(TextFileInputProcessor.class,
                (mock, context) -> {
                })) {
            InputProcessor in = Main.getInputProcessor(argMap);
            assertTrue(in instanceof TextFileInputProcessor);
        }

        argMap = new HashMap<>();
        InputProcessor in = Main.getInputProcessor(argMap);
        assertTrue(in instanceof StdInputProcessor);

        argMap = new HashMap<>() {{
            put("input", "stdIn");
        }};
        in = Main.getInputProcessor(argMap);
        assertTrue(in instanceof StdInputProcessor);
    }

    @DataProvider(name = "Invalid input data provider")
    public Object[][] invalidInputDataProvider1() {
        return new Object[][]{
                {new HashMap<>() {{
                    put("input", "TextFile");
                }}},
                {new HashMap<>() {{
                    put("input", "invalid");
                }}}
        };
    }

    @Test(description = "Get InputProcessor with invalid data", dataProvider = "Invalid input data provider",
            expectedExceptions = {InvalidInputException.class})
    public void testGetInputProcessorErrorCases(Map<String, String> argMap)
            throws InvalidInputException, IOException {
        Main.getInputProcessor(argMap);
    }

    @Test
    public void testGetOutputProcessor() throws InvalidInputException, IOException {
        Map<String, String> argMap = new HashMap<>() {{
            put("output", "TextFile");
            put("outputFilePath", "/home/me/out.txt");
        }};

        try (MockedConstruction<TextFileOutputProcessor> mocked = Mockito.mockConstruction(TextFileOutputProcessor.class,
                (mock, context) -> {
                })) {
            OutputProcessor out = Main.getOutputProcessor(argMap);
            assertTrue(out instanceof TextFileOutputProcessor);
        }

        argMap = new HashMap<>();
        OutputProcessor out = Main.getOutputProcessor(argMap);
        assertTrue(out instanceof StdOutputProcessor);

        argMap = new HashMap<>() {{
            put("output", "stdOut");
        }};
        out = Main.getOutputProcessor(argMap);
        assertTrue(out instanceof StdOutputProcessor);
    }

    @DataProvider(name = "Invalid output data provider")
    public Object[][] invalidOutputDataProvider() {
        return new Object[][]{
                {new HashMap<>() {{
                    put("output", "TextFile");
                }}},
                {new HashMap<>() {{
                    put("output", "invalid");
                }}}
        };
    }

    @Test(description = "Get OutputProcessor with invalid data", dataProvider = "Invalid output data provider",
            expectedExceptions = {InvalidInputException.class})
    public void testGetOutputProcessorErrorCases(Map<String, String> argMap)
            throws InvalidInputException, IOException {
        Main.getOutputProcessor(argMap);
    }

    @Test
    public void testParseArguments() {
        String[] args = new String[]{
                "--input=TextFile",
                "--inputFilePath=/home/me/in.txt",
                "--outputFilePath=/home/me/out.txt",
                "--kkkk=vvvv",
                "--aaa=",
                "--debug",
                "--=",
                "--",
                "-",
                "--111=222"
        };
        Map<String, String> expectedArgMap = new HashMap<>() {{
            put("input", "TextFile");
            put("inputFilePath", "/home/me/in.txt");
            put("outputFilePath", "/home/me/out.txt");
            put("kkkk", "vvvv");
            put("aaa", null);
            put("debug", null);
            put("", null);
            put("111", "222");
        }};
        Map<String, String> argMap = Main.parseArguments(args);
        assertEquals(argMap, expectedArgMap);
    }
}