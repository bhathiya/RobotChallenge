package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

public class PlaceCmd extends AbstractCommandWithArgs {

    private static final Logger log = LogManager.getLogger(PlaceCmd.class);

    private static final String PLACE = "PLACE";

    public PlaceCmd(String[] args) {
        super(PLACE, args);
    }

    public PlaceCmd() {
        this(null);
    }

    @Override
    public void execute(Table table, OutputProcessor out) throws InvalidInputException {
        int placeX = Integer.parseInt(args[0]);
        int placeY = Integer.parseInt(args[1]);
        Direction direction = Direction.valueOf(args[2]);

        if (placeX < table.getSideX() && placeY < table.getSideX()) {
            table.setRobotX(placeX);
            table.setRobotY(placeY);
            table.setRobotDirection(direction);
            log.debug("Placed the robot at ({},{}) facing {}.", placeX, placeY, direction);
        } else {
            log.debug("Ignored placing at invalid location: ({},{}).", placeX, placeY);
        }
    }

}
