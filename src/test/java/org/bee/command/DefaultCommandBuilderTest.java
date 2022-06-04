package org.bee.command;

import org.bee.exception.InvalidInputException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefaultCommandBuilderTest {

    DefaultCommandBuilder defaultCommandBuilder = new DefaultCommandBuilder();

    @Test(description = "Get CommandWithArgs object: Place")
    public void testGetCommandWithArgsObjectPlace() throws InvalidInputException {
        String[] location = new String[]{"0", "1", "NORTH"};
        AbstractCommand placeCmd = defaultCommandBuilder.getCommandObject("PLACE "
                + location[0] + "," + location[1] + "," + location[2]);

        assertTrue(placeCmd instanceof PlaceCmd);
        assertEquals(placeCmd.getCommandAction(), "PLACE");
        assertEquals(((PlaceCmd) placeCmd).getArgs(), location);
    }

    @Test(description = "Get Command Object: Move")
    public void testGetCommandObjectMove() throws InvalidInputException {
        AbstractCommand moveCmd = defaultCommandBuilder.getCommandObject("MOVE");
        assertTrue(moveCmd instanceof MoveCmd);
        assertEquals(moveCmd.getCommandAction(), "MOVE");
    }

    @Test(description = "Get Command Object: Left")
    public void testGetCommandObjectLeft() throws InvalidInputException {
        AbstractCommand leftCmd = defaultCommandBuilder.getCommandObject("LEFT");
        assertTrue(leftCmd instanceof LeftCmd);
        assertEquals(leftCmd.getCommandAction(), "LEFT");
    }

    @Test(description = "Get Command Object: Right")
    public void testGetCommandObjectRight() throws InvalidInputException {
        AbstractCommand rightCmd = defaultCommandBuilder.getCommandObject("RIGHT");
        assertTrue(rightCmd instanceof RightCmd);
        assertEquals(rightCmd.getCommandAction(), "RIGHT");
    }

    @Test(description = "Get Command Object: Report")
    public void testGetCommandObjectReport() throws InvalidInputException {
        AbstractCommand reportCmd = defaultCommandBuilder.getCommandObject("REPORT");
        assertTrue(reportCmd instanceof ReportCmd);
        assertEquals(reportCmd.getCommandAction(), "REPORT");
    }

    @DataProvider(name = "Invalid command data provider")
    public Object[][] invalidCommandDataProvider() {
        return new String[][]{
                {"PLACE"},
                {"PLACE 1"},
                {"PLACE 1,2"},
                {"PLACE 1,A"},
                {"PLACE 1,B,EAST"},
                {"PLACE 1,2,INVALID"},
                {"LEFT 1"},
                {"LEFT A"},
                {"LEFT 1,1,NORTH"},
                {"INVALID"},
                {"INVALID 1,1,SOUTH"},
                {"PLACE 1,1,1,NORTH"}
        };
    }

    @Test(description = "Get command with invalid action", dataProvider = "Invalid command data provider",
            expectedExceptions = {InvalidInputException.class},
            expectedExceptionsMessageRegExp = "Invalid input:.*")
    public void testGetCommandWithInvalidAction(String[] invalidAction) throws InvalidInputException {
        defaultCommandBuilder.getCommandObject(invalidAction[0]);
    }
}