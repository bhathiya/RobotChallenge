package org.bee.input.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileInputProcessor extends FileInputProcessor {

    BufferedReader fileReader;

    public TextFileInputProcessor(Path inputFilePath) throws IOException {
        super(inputFilePath);
        fileReader = Files.newBufferedReader(inputFilePath);
    }

    @Override
    public String readNextLine() throws IOException {
        String nextLine = fileReader.readLine();
        while (nextLine != null && nextLine.isEmpty()) {
            nextLine = fileReader.readLine();
        }
        return nextLine;
    }

    @Override
    public void complete() throws IOException {
        fileReader.close();
    }
}
