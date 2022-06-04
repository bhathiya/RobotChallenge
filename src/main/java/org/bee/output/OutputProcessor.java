package org.bee.output;

import java.io.IOException;

public interface OutputProcessor {

    void sendOutput(String message) throws IOException;

    default void complete() throws IOException {
        //do nothing
    };

}
