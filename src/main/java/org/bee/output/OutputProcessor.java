package org.bee.output;

import java.io.IOException;

/**
 * Responsible for sending responses out.
 */
public interface OutputProcessor {

    /**
     * Sends the response out
     *
     * @param message Response message
     * @throws IOException  IO Exception
     */
    void sendOutput(String message) throws IOException;

    /**
     * Completes sending messages out
     *
     * @throws IOException  IO Exception
     */
    default void complete() throws IOException {
        //do nothing
    };

}
