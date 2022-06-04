package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class LeftCmdTest {

    LeftCmd leftCmd = new LeftCmd();

    @DataProvider(name = "From-To direction data provider")
    public Object[][] dataProvider() {
        return new Direction[][]{
                {Direction.NORTH, Direction.WEST},
                {Direction.WEST, Direction.SOUTH},
                {Direction.SOUTH, Direction.EAST},
                {Direction.EAST, Direction.NORTH}
        };
    }

    @Test(description = "Turn left", dataProvider = "From-To direction data provider")
    public void testExecuteTurnLeft(final Direction fromDirection, final Direction toDirection) throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(fromDirection);
        leftCmd.execute(table);

        //expectation: table should reflect the new direction
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y);
        assertEquals(table.getRobotDirection(), toDirection);
    }

    @Test(description = "Get command action")
    public void testGetCommandAction() {
        //expectation: command should return "LEFT"
        assertEquals(leftCmd.getCommandAction(), "LEFT");
    }
}