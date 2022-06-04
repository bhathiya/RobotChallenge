package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class MoveCmdTest {

    MoveCmd moveCmd = new MoveCmd();

    @Test(description = "Move without placing the bot", expectedExceptions = {InvalidInputException.class},
            expectedExceptionsMessageRegExp = "Moved without placing the robot")
    public void testExecuteMoveWithoutPlacingBot() throws IOException, InvalidInputException {
        Table table = new Table(5);
        moveCmd.execute(table);
    }

    @Test(description = "Move North")
    public void testExecuteMoveNorth() throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;
        final Direction direction = Direction.NORTH;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(direction);
        moveCmd.execute(table);

        //expectation: table should reflect the new position (x,y+1)
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y + 1);
        assertEquals(table.getRobotDirection(), direction);
    }

    @Test(description = "Move South")
    public void testExecuteMoveSouth() throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;
        final Direction direction = Direction.SOUTH;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(direction);
        moveCmd.execute(table);

        //expectation: table should reflect the new position (x,y-1)
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y - 1);
        assertEquals(table.getRobotDirection(), direction);
    }

    @Test(description = "Move EAST")
    public void testExecuteMoveEast() throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;
        final Direction direction = Direction.EAST;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(direction);
        moveCmd.execute(table);

        //expectation: table should reflect the new position (x+1,y)
        assertEquals(table.getRobotX(), x + 1);
        assertEquals(table.getRobotY(), y);
        assertEquals(table.getRobotDirection(), direction);
    }

    @Test(description = "Move WEST")
    public void testExecuteMoveWest() throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;
        final Direction direction = Direction.WEST;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(direction);
        moveCmd.execute(table);

        //expectation: table should reflect the new position (x-1,y)
        assertEquals(table.getRobotX(), x - 1);
        assertEquals(table.getRobotY(), y);
        assertEquals(table.getRobotDirection(), direction);
    }

    @Test(description = "Move to an invalid position")
    public void testExecuteMoveToInvalidPosition() throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 2;
        final int y = 4;
        final Direction direction = Direction.NORTH;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(direction);
        moveCmd.execute(table);

        //expectation: no change to the original position
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y);
        assertEquals(table.getRobotDirection(), direction);
    }

    @Test(description = "Get command action")
    public void testGetCommandAction() {
        //expectation: command should return "MOVE"
        assertEquals(moveCmd.getCommandAction(), "MOVE");
    }
}