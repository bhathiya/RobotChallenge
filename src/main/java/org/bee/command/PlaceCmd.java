package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PlaceCmd extends AbstractCommandWithArgs {

    private static final Logger log = LogManager.getLogger(PlaceCmd.class);

    private static final String PLACE = "PLACE";

    static Set<String> directionSet = new HashSet<>();

    static {
        for (Direction value : EnumSet.allOf(Direction.class)) {
            directionSet.add(value.toString());
        }
    }

    protected PlaceCmd() {
        super(PLACE, null);
    }

    @Override
    public void execute(Table table, OutputProcessor out) throws InvalidInputException {
        int placeX = Integer.parseInt(getArgs()[0]);
        int placeY = Integer.parseInt(getArgs()[1]);
        Direction direction = Direction.valueOf(getArgs()[2]);

        if (placeX < table.getSideX() && placeY < table.getSideX()) {
            table.setRobotX(placeX);
            table.setRobotY(placeY);
            table.setRobotDirection(direction);
            log.debug("Placed the robot at ({},{}) facing {}.", placeX, placeY, direction);
        } else {
            log.debug("Can't place at invalid location: ({},{}).", placeX, placeY);
        }
    }

    @Override
    protected void setArgs(String[] args) throws InvalidInputException {
        if (args.length != 3
                || !args[0].matches("\\d+")
                || !args[1].matches("\\d+")
                || !directionSet.contains(args[2])) {
            throw new InvalidInputException("Invalid input: PLACE " + String.join(",", args));
        }
        super.setArgs(args);
    }
}
