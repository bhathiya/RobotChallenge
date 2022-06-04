package org.bee.output.stdout;

import org.bee.output.OutputProcessor;

public class StdOutputProcessor implements OutputProcessor {

    @Override
    public void sendOutput(String message) {
        System.out.println(message);
    }

}
