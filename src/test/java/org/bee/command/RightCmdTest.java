package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.bee.model.Direction;
import org.bee.model.Table;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class RightCmdTest {

    RightCmd rightCmd = new RightCmd();

    @DataProvider(name = "From-To direction data provider")
    public Object[][] dataProvider() {
        return new Direction[][]{
                {Direction.NORTH, Direction.EAST},
                {Direction.WEST, Direction.NORTH},
                {Direction.SOUTH, Direction.WEST},
                {Direction.EAST, Direction.SOUTH}
        };
    }

    @Test(description = "Turn right", dataProvider = "From-To direction data provider")
    public void testExecuteTurnRight(final Direction fromDirection, final Direction toDirection) throws IOException, InvalidInputException {
        //initial state
        final int side = 5;
        final int x = 3;
        final int y = 2;

        Table table = new Table(side);
        table.setRobotX(x);
        table.setRobotY(y);
        table.setRobotDirection(fromDirection);
        rightCmd.execute(table);

        //expectation: table should reflect the new direction
        assertEquals(table.getRobotX(), x);
        assertEquals(table.getRobotY(), y);
        assertEquals(table.getRobotDirection(), toDirection);
    }

    @Test(description = "Get command action")
    public void testGetCommandAction() {
        //expectation: command should return "RIGHT"
        assertEquals(rightCmd.getCommandAction(), "RIGHT");
    }
}