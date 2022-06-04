package org.bee.input;

import java.io.IOException;

public interface InputProcessor {

    String readNextLine() throws IOException;

    default void complete() throws IOException {
        //do nothing
    };

}
