package org.bee.command;

import org.bee.exception.InvalidInputException;

/**
 * Represents commands with arguments.
 */
public abstract class AbstractCommandWithArgs extends AbstractCommand {

    /**
     * Argument array of the command.
     */
    private String[] args;
    protected AbstractCommandWithArgs(String command, String[] args) {
        super(command);
        this.args = args;
    }

    protected void setArgs(String[] args) throws InvalidInputException {
        this.args = args;
    }

    protected String[] getArgs() {
        return args;
    }
}
