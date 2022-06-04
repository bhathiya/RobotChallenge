package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;
import org.bee.output.stdout.StdOutputProcessor;

import java.io.IOException;

public abstract class AbstractCommand {

    private final String command;

    public AbstractCommand(String command) {
        this.command = command;
    }

    public abstract void execute(Table table, OutputProcessor out) throws IOException, InvalidInputException;

    public void execute(Table table) throws IOException, InvalidInputException {
        execute(table, new StdOutputProcessor());
    }

    public String getCommandAction() {
        return command;
    }

}
