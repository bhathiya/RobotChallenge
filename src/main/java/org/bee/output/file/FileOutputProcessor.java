package org.bee.output.file;

import org.bee.output.OutputProcessor;

import java.nio.file.Path;

public abstract class FileOutputProcessor implements OutputProcessor {

    Path outputFilePath;

    public FileOutputProcessor(Path outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

}
