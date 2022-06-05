package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

public class MoveCmd extends AbstractCommand {

    private static final Logger log = LogManager.getLogger(MoveCmd.class);

    private static final String MOVE = "MOVE";

    protected MoveCmd() {
        super(MOVE);
    }

    @Override
    public void execute(Table table, OutputProcessor out) throws InvalidInputException {
        int previousX = table.getRobotX();
        int previousY = table.getRobotY();
        if (table.getRobotDirection() == Direction.NORTH) {
            moveNorth(table);
        } else if (table.getRobotDirection() == Direction.EAST) {
            moveEast(table);
        } else if (table.getRobotDirection() == Direction.SOUTH) {
            moveSouth(table);
        } else if (table.getRobotDirection() == Direction.WEST) {
            moveWest(table);
        } else {
            log.debug("Can't move forward without placing the robot");
            throw new InvalidInputException("Moved without placing the robot");
        }
        if (previousX == table.getRobotX() && previousY == table.getRobotY()) {
            log.debug("Can't move {} from ({},{}).", table.getRobotDirection(), previousX, previousY);
        } else {
            log.debug("Moved from ({},{}) to ({},{}).", previousX, previousY, table.getRobotX(), table.getRobotY());
        }
    }

    private void moveNorth(Table table) {
        if (table.getRobotY() + 1 < table.getSideY()) {
            table.setRobotY(table.getRobotY() + 1);
        }
    }

    private void moveEast(Table table) {
        if (table.getRobotX() + 1 < table.getSideX()) {
            table.setRobotX(table.getRobotX() + 1);
        }
    }

    private void moveSouth(Table table) {
        if (table.getRobotY() - 1 >= 0) {
            table.setRobotY(table.getRobotY() - 1);
        }
    }

    private void moveWest(Table table) {
        if (table.getRobotX() - 1 >= 0) {
            table.setRobotX(table.getRobotX() - 1);
        }
    }

}
