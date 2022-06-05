package org.bee.output.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for sending responses out to text files.
 */
public class TextFileOutputProcessor extends FileOutputProcessor {

    BufferedWriter fileWriter;

    public TextFileOutputProcessor(Path outputFilePath) throws IOException {
        super(outputFilePath);
        fileWriter = Files.newBufferedWriter(outputFilePath);
    }

    @Override
    public void sendOutput(String message) throws IOException {
        fileWriter.write(message);
    }

    @Override
    public void complete() throws IOException {
        fileWriter.close();
    }
}
