package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.AbstractTable;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

public class LeftCmd extends AbstractCommand {

    private static final Logger log = LogManager.getLogger(LeftCmd.class);

    private static final String LEFT = "LEFT";

    public LeftCmd() {
        super(LEFT);
    }

    @Override
    public void execute(Table table, OutputProcessor out) throws InvalidInputException {
        Direction previousDirection = table.getRobotDirection();
        if (table.getRobotDirection() == Direction.NORTH) {
            table.setRobotDirection(Direction.WEST);
        } else if (table.getRobotDirection() == Direction.EAST) {
            table.setRobotDirection(Direction.NORTH);
        } else if (table.getRobotDirection() == Direction.SOUTH) {
            table.setRobotDirection(Direction.EAST);
        } else if (table.getRobotDirection() == Direction.WEST) {
            table.setRobotDirection(Direction.SOUTH);
        } else {
            log.debug("Turned Left from invalid direction: {}.", previousDirection);
            throw new InvalidInputException("Turned Left without placing the robot");
        }
        log.debug("Turned Left from {} to {}.", previousDirection, table.getRobotDirection());
    }

}
