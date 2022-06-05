package org.bee.input.file;

import org.bee.input.InputProcessor;

import java.nio.file.Path;

/**
 * Processes files as inputs.
 */
public abstract class FileInputProcessor implements InputProcessor {

    Path inputFilePath;

    public FileInputProcessor(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

}
