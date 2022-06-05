package org.bee.input;

import java.io.IOException;

/**
 * Processes input sources
 */
public interface InputProcessor {

    String readNextLine() throws IOException;

    default void complete() throws IOException {
        //do nothing
    };

}
