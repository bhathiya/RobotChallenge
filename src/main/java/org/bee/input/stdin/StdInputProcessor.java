package org.bee.input.stdin;

import org.bee.input.InputProcessor;

import java.util.Scanner;

public class StdInputProcessor implements InputProcessor {

    Scanner stdIn;

    public StdInputProcessor() {
        this.stdIn = new Scanner(System.in);
    }

    @Override
    public String readNextLine() {
        String nextLine;
        do {
            if (!stdIn.hasNext()) {
                return null;
            }
            nextLine = stdIn.nextLine();
        } while (nextLine != null && nextLine.isEmpty());
        return nextLine;
    }

}
