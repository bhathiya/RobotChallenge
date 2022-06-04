package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;

import java.util.*;

public abstract class AbstractCommandBuilder {

    static Set<String> directionSet = new HashSet<>();

    static {
        for (Direction value : EnumSet.allOf(Direction.class)) {
            directionSet.add(value.toString());
        }
    }

    Map<String, AbstractCommand> commandMap;
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

    public abstract AbstractCommand getCommandObject(String command) throws InvalidInputException;

}
