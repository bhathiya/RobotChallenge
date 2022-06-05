package org.bee.command;

import org.bee.exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract builder class to build {@link AbstractCommand}`s.
 */
public abstract class AbstractCommandBuilder {

    /**
     * Keeps commands that don't have arguments.
     */
    Map<String, AbstractCommand> commandMap;

    /**
     * Keeps the commands that have arguments.
     */
    Map<String, AbstractCommandWithArgs> argCommandMap;

    public AbstractCommandBuilder() {
        this.commandMap = new HashMap<>();
        MoveCmd moveCmd = new MoveCmd();
        commandMap.put(moveCmd.getCommandAction(), moveCmd);
        LeftCmd leftCmd = new LeftCmd();
        commandMap.put(leftCmd.getCommandAction(), leftCmd);
        RightCmd rightCmd = new RightCmd();
        commandMap.put(rightCmd.getCommandAction(), rightCmd);
        ReportCmd reportCmd = new ReportCmd();
        commandMap.put(reportCmd.getCommandAction(), reportCmd);

        this.argCommandMap = new HashMap<>();
        PlaceCmd placeCmd = new PlaceCmd();
        argCommandMap.put(placeCmd.getCommandAction(), placeCmd);
    }

    /**
     * Build command object corresponding to given string command.
     *
     * @param command   String command
     * @return  Corresponding command object
     * @throws InvalidInputException    Invalid Input Exception
     */
    public abstract AbstractCommand getCommandObject(String command) throws InvalidInputException;

}
