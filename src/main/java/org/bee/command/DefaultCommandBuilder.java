package org.bee.command;

import org.bee.exception.InvalidInputException;

import java.util.Arrays;

public class DefaultCommandBuilder extends AbstractCommandBuilder {

    public AbstractCommand getCommandObject(String command) throws InvalidInputException {
        if (command.trim().contains(" ")) {
            String[] commandParts = command.split("\\s+", 2);
            AbstractCommandWithArgs commandObj = argCommandMap.get(commandParts[0]);
            String[] args = commandParts[1].split(",");
            args = Arrays.stream(args).map(String::trim).toArray(String[]::new);
            if (commandObj == null
                    || args.length != 3
                    || !args[0].matches("\\d+")
                    || !args[1].matches("\\d+")
                    || !directionSet.contains(args[2])) {
                throw new InvalidInputException("Invalid input: " + command);
            }
            commandObj.setArgs(args);
            return commandObj;
        } else {
            AbstractCommand commandObj = commandMap.get(command.trim());
            if (commandObj == null) {
                throw new InvalidInputException("Invalid input: " + command);
            }
            return commandObj;
        }
    }

}
