package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;
import org.bee.output.stdout.StdOutputProcessor;

import java.io.IOException;

/**
 * Abstract Command Class. Represents an input command to the robot.
 */
public abstract class AbstractCommand {

    private final String command;

    protected AbstractCommand(String command) {
        this.command = command;
    }

    /**
     * Executes what the command has to do.
     *
     * @param table Table representing the valid area of movement.
     * @param out   Output processor for output messages.
     * @throws IOException  IO Exception
     * @throws InvalidInputException    Invalid Input Exception
     */
    public abstract void execute(Table table, OutputProcessor out) throws IOException, InvalidInputException;

    /**
     * Executes what the command has to do.
     *
     * @param table Table representing the valid area of movement.
     * @throws IOException  IO Exception
     * @throws InvalidInputException    Invalid Input Exception
     */
    public void execute(Table table) throws IOException, InvalidInputException {
        execute(table, new StdOutputProcessor());
    }

    /**
     * Returns the Action of the command.
     *
     * @return Action string. Eg. PLACE, MOVE, LEFT, etc.
     */
    public String getCommandAction() {
        return command;
    }

}
