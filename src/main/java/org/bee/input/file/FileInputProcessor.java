package org.bee.input.file;

import org.bee.input.InputProcessor;

import java.nio.file.Path;

public abstract class FileInputProcessor implements InputProcessor {

    Path inputFilePath;

    public FileInputProcessor(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

}
