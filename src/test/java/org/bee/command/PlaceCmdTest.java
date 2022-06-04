package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class PlaceCmdTest {

    PlaceCmd placeCmd = new PlaceCmd();

    @Test(description = "Place the bot on a valid position")
    public void testExecutePlaceAtValidPosition() throws IOException, InvalidInputException {
        final int side = 5;
        final int x = 3;
        final int y = 2;
        final Direction direction = Direction.NORTH;

        String[] args = new String[]{String.valueOf(x), String.valueOf(y), String.valueOf(direction)};
        placeCmd.setArgs(args);
        Table table = new Table(side);
        placeCmd.execute(table);

        //expectation: table should reflect the new position
        assertEquals(table.getRobotDirection(), direction);
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y);
    }

    @Test(description = "Place the bot on an invalid position")
    public void testExecutePlaceAtInvalidPosition() throws IOException, InvalidInputException {
        final int side = 5;

        //place at
        final int x = 5;
        final int y = 2;
        final Direction direction = Direction.NORTH;

        String[] args = new String[]{String.valueOf(x), String.valueOf(y), String.valueOf(direction)};
        placeCmd.setArgs(args);
        Table table = new Table(side);
        placeCmd.execute(table);

        //expectation: no change to the original position
        assertNull(table.getRobotDirection());
        assertEquals(table.getRobotX(), 0);
        assertEquals(table.getRobotY(), 0);
    }

    @Test(description = "Place the bot on an edge")
    public void testExecutePlaceAtEdgePosition() throws IOException, InvalidInputException {
        final int side = 5;

        //place at
        final int x = 4;
        final int y = 2;
        final Direction direction = Direction.EAST;

        String[] args = new String[]{String.valueOf(x), String.valueOf(y), String.valueOf(direction)};
        placeCmd.setArgs(args);
        Table table = new Table(side);
        placeCmd.execute(table);

        //expectation: table should reflect the new position
        assertEquals(table.getRobotDirection(), Direction.EAST);
        assertEquals(table.getRobotX(), 4);
        assertEquals(table.getRobotY(), 2);
    }

    @Test(description = "Place the bot on a corner")
    public void testExecutePlaceAtCornerPosition() throws IOException, InvalidInputException {
        final int side = 8;

        //place at
        final int x = 7;
        final int y = 7;
        final Direction direction = Direction.EAST;

        String[] args = new String[]{String.valueOf(x), String.valueOf(y), String.valueOf(direction)};
        placeCmd.setArgs(args);
        Table table = new Table(side);
        placeCmd.execute(table);

        //expectation: table should reflect the new position
        assertEquals(table.getRobotDirection(), direction);
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y);
    }

    @Test(description = "Get command action")
    public void testGetCommandAction() {
        //expectation: command should return "PLACE"
        assertEquals(placeCmd.getCommandAction(), "PLACE");
    }
}